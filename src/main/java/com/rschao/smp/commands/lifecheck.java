package com.rschao.smp.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.rschao.smp.lives.saveData;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.OfflinePlayerArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import net.md_5.bungee.api.ChatColor;

public class lifecheck {
    public static CommandAPICommand LoadCommand(){
        
        CommandAPICommand life = new CommandAPICommand("life")
            .withSubcommands(check(), add(), remove(), set(), reset(), reload())
            .executesPlayer((Player player, CommandArguments args) -> {
                player.sendMessage("/life check <player>");
                if(player.hasPermission("smp.lives")){
                    player.sendMessage("/life <add|remove> <amount> [player]");
                }
            });
            
        return life;
    }

    public static CommandAPICommand check(){
        String[] string = saveData.GetAllPlayers();
        CommandAPICommand check = new CommandAPICommand("check")
            .withOptionalArguments(new OfflinePlayerArgument("target").replaceSuggestions(ArgumentSuggestions.strings(string)))
            .executesPlayer((Player player, CommandArguments args) -> {
                OfflinePlayer p = (OfflinePlayer) args.getOrDefault("target", player);
                int lives = saveData.getLives(p);
                player.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " has " + ChatColor.YELLOW + String.valueOf(lives) + ChatColor.DARK_GREEN + " lives");
            });
        return check;
        
    }
    public static CommandAPICommand add(){
        String[] string = saveData.GetAllPlayers();
        CommandAPICommand add = new CommandAPICommand("add")
            .withArguments(new IntegerArgument("amount"))
            .withOptionalArguments(new OfflinePlayerArgument("target").replaceSuggestions(ArgumentSuggestions.strings(string)))
            .executesPlayer((Player player, CommandArguments args) -> {
                if(player.hasPermission("smp.lives")){
                    OfflinePlayer p = (OfflinePlayer) args.getOrDefault("target", player);
                    int lives = saveData.getLives(p);
                    int amnt = (int) args.getOrDefault("amount", 0);
                    saveData.SaveLives(p, lives + amnt);
                    player.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " has been granted " + ChatColor.YELLOW + String.valueOf(amnt) + ChatColor.DARK_GREEN + " lives");
                }
                else{
                    player.sendMessage("UnauthorizedUserException(at SSMP/lifesystem.java)");
                }
            });
        return add;
        
    }
    public static CommandAPICommand set(){
        String[] string = saveData.GetAllPlayers();
        CommandAPICommand set = new CommandAPICommand("set")
            .withArguments(new OfflinePlayerArgument("target").replaceSuggestions(ArgumentSuggestions.strings(string)), new IntegerArgument("amount"))
            .executesPlayer((Player player, CommandArguments args) -> {
                if(player.hasPermission("smp.lives")){
                    OfflinePlayer p = (OfflinePlayer) args.getOrDefault("target", player);
                    int amnt = (int) args.getOrDefault("amount", 0);
                    saveData.SaveLives(p, amnt);
                    player.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + "'s lives have been set to " + ChatColor.YELLOW + String.valueOf(amnt) + ChatColor.DARK_GREEN);
                }
                else{
                    player.sendMessage("UnauthorizedUserException(at SSMP/lifesystem.java)");
                }
            });
        return set;
        
    }
    public static CommandAPICommand remove(){
        String[] string = saveData.GetAllPlayers();
        CommandAPICommand remove = new CommandAPICommand("remove")
            .withArguments(new IntegerArgument("amount"))
            .withOptionalArguments(new OfflinePlayerArgument("target").replaceSuggestions(ArgumentSuggestions.strings(string)))
            .executesPlayer((Player player, CommandArguments args) -> {
                if(player.hasPermission("smp.lives")){
                    OfflinePlayer p = (OfflinePlayer) args.getOrDefault("target", player);
                    int lives = saveData.getLives(p);
                    int amnt = (int) args.getOrDefault("amount", 0);
                    saveData.SaveLives(p, lives - amnt);
                    player.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " has been revoked " + ChatColor.YELLOW + String.valueOf(amnt) + ChatColor.DARK_GREEN + " lives");
                }                
                else{
                    player.sendMessage("UnauthorizedUserException(at SSMP/lifesystem.java)");
                }
            });
        return remove;
        
    }
    public static CommandAPICommand reset(){
        String[] string = saveData.GetAllPlayers();
        CommandAPICommand reset = new CommandAPICommand("reset")
            .withArguments(new OfflinePlayerArgument("target").replaceSuggestions(ArgumentSuggestions.strings(string)))
            .executesPlayer((Player player, CommandArguments args) -> {
                if(player.hasPermission("smp.lives")){
                    OfflinePlayer p = (OfflinePlayer) args.getOrDefault("target", player);
                    int amnt = 5;
                    saveData.SaveLives(p, amnt);
                    player.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " has been reset");
                }
                else{
                    player.sendMessage("UnauthorizedUserException(at SSMP/lifesystem.java)");
                }
            });
        return reset;
        
    }
    public static CommandAPICommand reload(){
        CommandAPICommand reload = new CommandAPICommand("reload")
            .executesPlayer((Player player, CommandArguments args) -> {
                if(player.hasPermission("smp.lives")){
                    saveData.reload();
                }
                else{
                    player.sendMessage("UnauthorizedUserException(at SSMP/lifesystem.java)");
                }
            });
        return reload;
        
    }
}
