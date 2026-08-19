package com.rschao.smp.commands;

import java.util.List;
import java.util.Locale;

import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.LocationArgument;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.entity.Player;

import com.rschao.smp.logs.log;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.EntitySelectorArgument.OnePlayer;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.event.player.PlayerTeleportEvent;

public class tpworld {
    static String[] loadSuggestion(){
        List<World> worlds = Bukkit.getWorlds();
        String[] worldNames = new String[worlds.size()];
        for(int i = 0; i < worlds.size(); i++){
            worldNames[i] = worlds.get(i).getName();
        }
        return worldNames;
    }
    public static CommandAPICommand LoadCommand(){

        CommandAPICommand life = new CommandAPICommand("tpworld")
            .withPermission("smp.worlds")
            .withArguments(new StringArgument("world").replaceSuggestions(ArgumentSuggestions.strings(info -> loadSuggestion())))
            .withOptionalArguments(new OnePlayer("target"), new LocationArgument("location"))
            .executes((exec, args) -> {
                String world = (String) args.get("world");
                Player target = (Player) args.get("target");
                Location location = (Location) args.get("location");
                if(target == null){
                    exec.sendMessage("missing player!");
                }
                else{
                    if(Bukkit.getWorld(world) == null){
                        exec.sendMessage("World " + world + " does not exist!");
                        return;
                    }
                    Location finalLocation = location != null ? location.clone() : Bukkit.getWorld(world).getSpawnLocation();
                    finalLocation.setWorld(Bukkit.getWorld(world));
                    target.teleport(finalLocation, PlayerTeleportEvent.TeleportCause.COMMAND);
                    log.logToFile(target.getName() + " has teleported worlds through a command block!", "commamdtp.log");
                }
            })
            .executesPlayer((Player player, CommandArguments args) -> {
                String world = (String) args.get("world");
                Player target = (Player) args.getOrDefault("target", player);
                Location location = (Location) args.get("location");
                if(Bukkit.getWorld(world) == null){
                    player.sendMessage("World " + world + " does not exist!");
                    return;
                }
                Location finalLocation = location != null ? location.clone() : Bukkit.getWorld(world).getSpawnLocation();
                finalLocation.setWorld(Bukkit.getWorld(world));
                target.teleport(finalLocation, PlayerTeleportEvent.TeleportCause.COMMAND);
                log.logToFile(target.getName() + " has teleported to " + world + " through their own power!", "playertp.log");
            });
            
            

            
        return life;
    }
}
