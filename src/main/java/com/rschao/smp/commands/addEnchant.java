package com.rschao.smp.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class addEnchant {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("addenchant")
                .withArguments(new EnchantmentArgument("ench"))
                .withOptionalArguments(new IntegerArgument("level"), new BooleanArgument("unsafe"))
                .withPermission("smp.admin")
                .executesPlayer((Player player, CommandArguments args) -> {
                    Enchantment enchant = (Enchantment) args.get("ench");
                    int level = (int) args.getOrDefault("level", 1);
                    boolean unsafe = (boolean) args.getOrDefault("unsafe", false);
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if(item == null){
                        player.sendMessage("&cYou must be holding an item to enchant it!");
                        return;
                    }
                    ItemMeta meta = item.getItemMeta();
                    if(unsafe){
                        meta.addEnchant(enchant, level, true);
                    } else {
                        if(level > enchant.getMaxLevel()){
                            player.sendMessage(ChatColor.RED + "The maximum level for " + enchant.getKey() + " is " + enchant.getMaxLevel());
                            return;
                        }
                        if(!enchant.canEnchantItem(item)){
                            player.sendMessage(ChatColor.RED + "You cannot enchant this item with " + enchant.getKey());
                            return;
                        }
                        for(Enchantment e : item.getEnchantments().keySet()){
                            if(enchant.conflictsWith(e)){
                                player.sendMessage(ChatColor.RED + "The enchantment " + enchant.getKey() + " conflicts with existing enchantment " + e.getKey());
                                return;
                            }
                        }
                        meta.addEnchant(enchant, level, false);
                    }
                    item.setItemMeta(meta);
                    player.sendMessage(ChatColor.GREEN + "Enchanted item with " + enchant.getKey() + " level " + level + (unsafe ? " (unsafe)" : ""));
                });

        return life;
    }
}
