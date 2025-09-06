package com.rschao.smp.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.rschao.smp.lives.saveData;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.OfflinePlayerArgument;
import dev.jorel.commandapi.executors.CommandArguments;

public class revive {
    public static CommandAPICommand LoadCommand(){
        String[] string = saveData.GetAllPlayers();
        CommandAPICommand life = new CommandAPICommand("revive")
            .withPermission("smp.lives")
            .withArguments(new OfflinePlayerArgument("target").replaceSuggestions(ArgumentSuggestions.strings(string)))
            .executesPlayer((Player player, CommandArguments args) -> {
                OfflinePlayer p = (OfflinePlayer) args.getOrDefault("target", () -> player);
                int lives = saveData.getLives(p);
                if(lives <= 0){
                    saveData.SaveLives(p, 3);
                    player.sendMessage("Player " + p.getName() + " has been revived");
                }
                else{
                    player.sendMessage("Player is not banned or below 0 lives");
                }
            });
            
        return life;
    }
    
}
