package com.rschao.smp.commands;

import org.bukkit.entity.Player;

import com.rschao.smp.items.Items;
import com.rschao.smp.lives.saveData;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import net.md_5.bungee.api.ChatColor;

public class withdraw {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("withdraw")
            .withArguments(new IntegerArgument("amount", 1, 9))
            .executesPlayer((Player player, CommandArguments args) -> {
                int amount = (int) args.getOrDefault("amount", 1);
                int lives = saveData.getLives(player);
                if(lives < (amount - 1)){
                    player.sendMessage("Too many lives! Try withdrawing less");
                    return;
                }
                for(int i = 0; i<amount; i++){
                    player.getInventory().addItem(Items.LifeUpGem);
                }
                saveData.SaveLives(player, lives - amount);
                player.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " has " + ChatColor.YELLOW + String.valueOf(lives - amount) + ChatColor.DARK_GREEN + " lives");
            });
            
        return life;
    }
}
