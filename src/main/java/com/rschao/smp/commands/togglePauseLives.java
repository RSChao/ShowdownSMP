package com.rschao.smp.commands;

import com.rschao.smp.Plugin;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.entity.Player;

public class togglePauseLives {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("setpauselives")
                .withPermission("smp.admin")
                .withArguments(new BooleanArgument("state"))
                .executesPlayer((Player player, CommandArguments args) -> {
                    boolean b = (boolean) args.get("state");
                    Plugin.setPauseLives(b);
                    player.sendMessage("Lives are now " + ( (b) ? "Paused" : "Unpaused"));
                });

        return life;
    }
}
