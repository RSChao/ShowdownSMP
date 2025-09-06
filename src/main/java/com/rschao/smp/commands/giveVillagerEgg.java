package com.rschao.smp.commands;

//import com.rschao.smp.events.villagerEvents;
import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import com.rschao.smp.items.BookItem;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class giveVillagerEgg {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("enchanttrader")
                .withPermission("smp.items")
                .executesPlayer((Player player, CommandArguments args) -> {
                    Merchant m = Bukkit.createMerchant("§bAldeano Encantador");

                    m.setRecipes(getAllEnchantsTrades());
                    player.openMerchant(m, true);
                });

        return life;
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
