package com.rschao.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.entity.Player;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;

public class time {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("smpday")
            .withPermission("smp.time")
            .executesCommandBlock((BlockCommandSender exec, CommandArguments args) -> {
                //get the number of days in the world
                int days = (int) (Bukkit.getWorlds().get(0).getTime() / 24000);
                //set the time to day
                Bukkit.getWorlds().get(0).setTime(24000 * (days + 1));
            })
            .executesPlayer((Player player, CommandArguments args) -> {
                //get the number of days in the world
                int days = (int) (Bukkit.getWorlds().get(0).getTime() / 24000);
                //set the time to day
                Bukkit.getWorlds().get(0).setTime(24000 * (days + 1));

                //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set day");
            });
           
        return life;
    }
    public static CommandAPICommand night(){
      CommandAPICommand life = new CommandAPICommand("smpnight")
          .withPermission("smp.time")
          .executesCommandBlock((BlockCommandSender exec, CommandArguments args) -> {
              //get the number of days in the world
              int days = (int) (Bukkit.getWorlds().get(0).getTime() / 24000);
              //set the time to night
              Bukkit.getWorlds().get(0).setTime(24000 * (days + 1) + 14000);
          })
          .executesPlayer((Player player, CommandArguments args) -> {
              
                //get the number of days in the world
                int days = (int) (Bukkit.getWorlds().get(0).getTime() / 24000);
                //set the time to night
                Bukkit.getWorlds().get(0).setTime(24000 * (days + 1) + 14000);
                //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set night");
                

          });
          
        return life;
    }

}

