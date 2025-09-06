package com.rschao.smp.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.entity.Player;

import com.rschao.smp.logs.log;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.executors.CommandArguments;

public class tpworld {
    public static CommandAPICommand LoadCommand(){
        List<World> worlds = Bukkit.getWorlds();
        String[] worldNames = new String[worlds.size()];
        for(int i = 0; i < worlds.size(); i++){
            worldNames[i] = worlds.get(i).getName();
        }
        CommandAPICommand life = new CommandAPICommand("tpworld")
            .withPermission("smp.worlds")
            .withArguments(new StringArgument("world").replaceSuggestions(ArgumentSuggestions.strings(worldNames)))
            .withOptionalArguments(new PlayerArgument("target"))
            .executesCommandBlock((BlockCommandSender exec, CommandArguments args) -> {
                String world = (String) args.get("world");
                Player target = (Player) args.get("target");
                if(target == null){
                    exec.sendMessage("missing player!");
                }
                else{
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + target.getName() + " " + world);
                    log.logToFile(target.getName() + " has teleported to " + world + " through their own power!", "playertp.log");
                }
            })
            .executesConsole((exec, args) -> {
                String world = (String) args.get("world");
                Player target = (Player) args.get("target");
                if(target == null){
                    exec.sendMessage("missing player!");
                }
                else{
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + target.getName() + " " + world);
                    log.logToFile(target.getName() + " has teleported to " + world + " through a command block!", "commamdtp.log");
                }
            })
            .executesPlayer((Player player, CommandArguments args) -> {
                String world = (String) args.get("world");
                Player target = (Player) args.getOrDefault("target", player);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + target.getName() + " " + world);
                log.logToFile(target.getName() + " has teleported worlds through a command block!", "commamdtp.log");
            });
            
            

            
        return life;
    }
}
