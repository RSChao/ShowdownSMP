package com.rschao.smp.events;

import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class EnchantingTableEvent implements Listener {
    @EventHandler
    void onEnchant(EnchantItemEvent ev){
        ItemStack item = ev.getItem();
        if(ev.getExpLevelCost() < 20) return;
        Random random = new Random();
        if(random.nextInt(0, 100) < 60) return;
        Enchant enchant = com.rschao.smp.Plugin.getEnchants().get(random.nextInt(com.rschao.smp.Plugin.getEnchants().size()));
        if(enchant == null) return;
        if(enchant.isTreasure()) {
            enchant = com.rschao.smp.Plugin.getEnchants().get(random.nextInt(com.rschao.smp.Plugin.getEnchants().size()));
            if(enchant == null || enchant.isTreasure()) return;
        }
        if(!enchant.getApplicableItems().contains(item.getType())) return;
        int level = random.nextInt(1, enchant.getMaxLevel()+1);
        enchant.addEnchant(item, level);

    }
    ItemStack result = null;
    @EventHandler
    void onGrindstone(PrepareGrindstoneEvent ev){
        ev.getInventory();
        if(ev.getInventory().getItem(0) == null) return;
        ItemStack item = new ItemStack(Objects.requireNonNull(ev.getInventory().getItem(0)));
        if(item.getType().isAir()) return;
        for(Enchant e : Plugin.getEnchants()){
            if(e.hasEnchant(item)){
                ItemStack result = e.removeEnchant(item);
                if(result != null) {
                    //remove vanilla enchantments
                    for (Enchantment enchantment : item.getEnchantments().keySet()) {
                        result.removeEnchantment(enchantment);
                    }
                    ev.setResult(result);
                    this.result = result;
                    return;
                }
            }
        }
    }
    @EventHandler
    void onClickEvent(InventoryClickEvent ev){
        ev.getInventory();
        if(ev.getCurrentItem() == null) return;
        if(ev.getCurrentItem().getType().isAir()) return;
        if(!ev.getCurrentItem().hasItemMeta()) return;
        if(ev.getInventory() instanceof GrindstoneInventory inv){
            ItemStack item = inv.getItem(2);
            assert item != null;
            if(item.isSimilar(result)){
                ev.setCancelled(true);
                ev.getWhoClicked().getInventory().addItem(result);
                ev.getInventory().clear();
            }
        }
    }
}
