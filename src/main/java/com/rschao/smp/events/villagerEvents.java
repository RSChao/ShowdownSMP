package com.rschao.smp.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import com.rschao.smp.items.BookItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataType;

public class villagerEvents implements Listener {

    @EventHandler
    public void onInvOpen(InventoryOpenEvent ev){
        if(ev.getInventory() instanceof MerchantInventory inv){
            Merchant merchant = inv.getMerchant();

            if(ev.getInventory().getHolder() == null) return;
            Villager v = (Villager) ev.getInventory().getHolder();
            if(!v.getProfession().equals(Villager.Profession.LIBRARIAN)) return;
            if(v.getPersistentDataContainer().getKeys().isEmpty()) return;
            if(!v.getPersistentDataContainer().getKeys().iterator().next().getNamespace().equals("villager")) return;
            String id = null;
            v.setVillagerExperience(1);
            for (NamespacedKey key : v.getPersistentDataContainer().getKeys()) {
                if (Plugin.getEnchants().stream().anyMatch(e -> e.getKey().getKey().equals(key.getKey()))) {
                    id = key.getKey();
                    break;
                }
            }
            if (id == null) return;
            NamespacedKey priceKey = new NamespacedKey("villager", id + "_price");
            Integer price = v.getPersistentDataContainer().get(priceKey, PersistentDataType.INTEGER);

            if (price == null) {
                price = new Random().nextInt(1, 64);
                v.getPersistentDataContainer().set(priceKey, PersistentDataType.INTEGER, price);
            }
            NamespacedKey levelKey = new NamespacedKey("villager", id + "_level");
            Integer enchantLevel = v.getPersistentDataContainer().get(levelKey, PersistentDataType.INTEGER);
            if (enchantLevel == null) {
                enchantLevel = new Random().nextInt(1, 3);
                v.getPersistentDataContainer().set(levelKey, PersistentDataType.INTEGER, enchantLevel);
            }
            for(Player p: Bukkit.getOnlinePlayers()){
                if(p.getPersistentDataContainer().has(new NamespacedKey("villager", id))){
                    p.sendMessage("Villager has " + id + " enchant with level " + enchantLevel);
                }
            }
            List<MerchantRecipe> recipes = getCustomTrades(id, price, enchantLevel);
            NamespacedKey customTradeKey = new NamespacedKey("villager", "custom_trade_assigned");
            if (!v.getPersistentDataContainer().has(customTradeKey, PersistentDataType.BOOLEAN)) {
                merchant.setRecipes(recipes);
                v.getPersistentDataContainer().set(customTradeKey, PersistentDataType.BOOLEAN, true);
            }
        }
    }
    @EventHandler
    void onVillagerTakeJob(VillagerCareerChangeEvent ev){
        if(ev.getProfession().equals(Villager.Profession.LIBRARIAN)){
            List<Enchant> enchants = Plugin.getEnchants();
            List<String> ids = new ArrayList<>();
            for(Enchant enchant : enchants){
                ids.add(enchant.getKey().getKey());
            }
            Random rand = new Random();
            int rng = rand.nextInt(0, 100);
            if(rng < 40){
                String id = ids.get(rand.nextInt(ids.size()));
                int maxLvl = 1;
                for(Enchant enchant: Plugin.getEnchants()){
                    NamespacedKey key = enchant.getKey();
                    if(key.getKey().equals(id)) {
                        maxLvl = enchant.getMaxLevel();
                        break;
                    }
                }
                int enchantLevel = rand.nextInt(1, maxLvl+1);
                ev.getEntity().getPersistentDataContainer().set(new NamespacedKey("villager", id), PersistentDataType.BOOLEAN, true);
                ev.getEntity().getPersistentDataContainer().set(new NamespacedKey("villager", id + "_level"), PersistentDataType.INTEGER, enchantLevel);

                for (Player p : ev.getEntity().getWorld().getPlayers()) {
                    p.sendMessage("Villager has taken a job as a librarian and offers a custom enchantment");
                }
            }
        }
    }

    private List<MerchantRecipe> getCustomTrades(String id, int price, int enchantLevel) {
        List<MerchantRecipe> list = new ArrayList<>();
        if(id == null || id.isEmpty()) return list;
        for (Enchant enchant : Plugin.getEnchants()) {
            if (id.equals(enchant.getKey().getKey())) {
                ItemStack result = BookItem.makeEnchantedBook(enchant, enchantLevel);
                ItemStack i1 = new ItemStack(Material.EMERALD_BLOCK, price);
                ItemStack i2 = new ItemStack(Material.BOOK, 1);
                list.add(makeTrade(result, i1, i2));
            }
        }
        return list;
    }
    private static MerchantRecipe makeTrade(ItemStack result, ItemStack i1, ItemStack i2) {
        MerchantRecipe recipe = new MerchantRecipe(result, 200);
        recipe.addIngredient(i1);
        recipe.addIngredient(i2);
        recipe.setExperienceReward(true);
        return recipe;
    }
    public static List<MerchantRecipe> getAllEnchantsTrades() {
        List<MerchantRecipe> trades = new ArrayList<>();
        int basePrice = 1; // Precio base por nivel
        for (Enchant enchant : Plugin.getAllEnchants()) {
            for (int lvl = 1; lvl <= enchant.getMaxLevel(); lvl++) {
                ItemStack result = BookItem.makeEnchantedBook(enchant, lvl);
                ItemStack i1 = new ItemStack(Material.EMERALD_BLOCK, basePrice * lvl);
                ItemStack i2 = new ItemStack(Material.BOOK, 1);
                trades.add(makeTrade(result, i1, i2));
            }
        }
        return trades;
    }
}
