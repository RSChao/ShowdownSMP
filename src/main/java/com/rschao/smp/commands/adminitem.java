package com.rschao.smp.commands;

import org.bukkit.entity.Player;

import com.rschao.smp.items.Items;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.executors.CommandArguments;

public class adminitem {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("adminitems")
            .withPermission("smp.admin")
            .withSubcommands(Sword())
            .executesPlayer((Player player, CommandArguments args) -> {
               HelpMsg(player);
            });
            
        return life;
    }
    public static CommandAPICommand Sword(){
        CommandAPICommand life = new CommandAPICommand("judgement_sword")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.BanSword);
                }
            });
            
        return life;
    }


    static void HelpMsg(Player player){
        player.sendMessage("/items <item> [amount]");
    }
}
