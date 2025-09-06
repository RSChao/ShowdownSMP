package com.rschao.smp.commands;

import com.rschao.smp.items.Items;
import com.rschao.smp.items.WorldEditItems;
import com.rschao.smp.lives.saveData;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class Worldeditkit {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("wekit")
                .executesPlayer((Player player, CommandArguments args) -> {
                    if(!player.hasPermission("smp.admin")){
                        player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                        return;
                    }
                    player.getInventory().addItem(WorldEditItems.setter);
                    player.getInventory().addItem(WorldEditItems.replacer);
                    player.getInventory().addItem(WorldEditItems.copier);
                    player.getInventory().addItem(WorldEditItems.paster);
                    player.getInventory().addItem(WorldEditItems.undoer);
                    player.getInventory().addItem(WorldEditItems.spherer);
                    player.getInventory().addItem(WorldEditItems.cylinderer);
                    player.sendMessage(ChatColor.GREEN + "You have been given the WorldEdit kit.");
                });

        return life;
    }
}
