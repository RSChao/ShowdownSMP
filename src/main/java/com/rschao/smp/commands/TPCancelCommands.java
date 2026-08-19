package com.rschao.smp.commands;

import com.rschao.smp.tpcancel.TPCancelHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;

public class TPCancelCommands {

    public static void registerCommands() {
        // Register your commands here
        tpcancelCommand.register("showdownsmp");
    }

    static CommandAPICommand tpcancelCommand = new CommandAPICommand("tpcancel")
            .withPermission("smp.tpcancel")
            .withArguments(new EntitySelectorArgument.OnePlayer("player"), new BooleanArgument("state").setOptional(true))
            .executes((player, args) -> {
                String targetPlayerName = ((org.bukkit.entity.Player) args.get("player")).getName();
                boolean state = (boolean) args.getOrDefault("state", false);

                if (state) {
                    TPCancelHandler.addPlayer(targetPlayerName);
                    player.sendMessage("Added " + targetPlayerName + " to the teleport cancel list.");
                } else {
                    TPCancelHandler.removePlayer(targetPlayerName);
                    player.sendMessage("Removed " + targetPlayerName + " from the teleport cancel list.");
                }
            });
}
