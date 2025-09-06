package com.rschao.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.entity.Player;

import com.rschao.smp.Plugin;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.executors.CommandArguments;

public class smpworld {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("smpworld")
            .withPermission("tp.smpworld")
            .withOptionalArguments(new PlayerArgument("target"))
            .executesCommandBlock((BlockCommandSender exec, CommandArguments args) -> {
                Player target = (Player) args.get("target");
                String worldName = Plugin.getPlugin(Plugin.class).getConfig().getString("smpworld.name", "Showdown");
                if(target == null){
                    exec.sendMessage("missing player!");
                }
                else{
                    target.setGameMode(GameMode.SURVIVAL);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + target.getName() + " " + worldName);
                }
            })
            .executesPlayer((Player player, CommandArguments args) -> {
                Player target = (Player) args.getOrDefault("target", player);
                String worldName = Plugin.getPlugin(Plugin.class).getConfig().getString("smpworld.name", "Showdown");
                target.setGameMode(GameMode.SURVIVAL);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + target.getName() + " " + worldName);
            });
            
        return life;
    }
}
