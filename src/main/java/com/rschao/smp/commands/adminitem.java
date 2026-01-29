package com.rschao.smp.commands;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import com.rschao.smp.items.Items;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class adminitem {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("adminitems")
            .withPermission("smp.admin")
            .withSubcommands(Sword(), ClearCustomEnchants())
            .executesPlayer((Player player, CommandArguments args) -> {
               HelpMsg(player);
            });
            
        return life;
    }
    public static CommandAPICommand Sword(){
        CommandAPICommand life = new CommandAPICommand("judgement_sword")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.BanSword);
                }
            });
            
        return life;
    }

    public static CommandAPICommand ClearCustomEnchants(){
        CommandAPICommand life = new CommandAPICommand("nomoarenchantchit")
                .executesPlayer((Player player, CommandArguments args) -> {
                    for(ItemStack item : player.getInventory().getContents()){
                        if(item == null) continue;
                        if(item.getItemMeta().getPersistentDataContainer().isEmpty()) continue;
                        ItemMeta meta = item.getItemMeta();
                        PersistentDataContainer pdc  = meta.getPersistentDataContainer();
                        for(NamespacedKey key : pdc.getKeys()){
                            if(key.toString().contains("ench")){
                                pdc.remove(key);
                                meta.setLore(null);
                                item.setItemMeta(meta);
                            }
                        }
                    }
                });

        return life;
    }


    static void HelpMsg(Player player){
        player.sendMessage("/items <item> [amount]");
    }
}
