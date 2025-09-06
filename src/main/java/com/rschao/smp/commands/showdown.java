package com.rschao.smp.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class showdown {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("showdown")
                .withPermission("smp.lives")
                .executes((CommandSender player, CommandArguments args) -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"uaapi grant one ssmp_end @a");
                });

        return life;
    }
}
