package com.rschao.smp.commands;

import org.bukkit.entity.Player;

import com.rschao.smp.items.Items;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.executors.CommandArguments;

public class item {
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("items")
            .withPermission("smp.items")
            .withSubcommands(equipment(), items(), EmblemTotem())
            .executesPlayer((Player player, CommandArguments args) -> {
               HelpMsg(player);
            });
            
        return life;
    }
    public static CommandAPICommand items(){
        CommandAPICommand life = new CommandAPICommand("item")
            .withSubcommand(Speedfrag())
            .withSubcommand(HealthGem15())
            .withSubcommand(HealthGem20())
            .withSubcommand(Haste())
            .withSubcommand(Stillness())
            .withSubcommand(Guardian())
            .withSubcommand(Raids())
            .withSubcommand(PureSF())
            .withSubcommand(Life())
            .withSubcommand(Beacon())
            .withSubcommand(Pearl())
            .withSubcommand(Stone())
            .executesPlayer((Player player, CommandArguments args) -> {
                HelpMsg(player);
            });
            
        return life;
    }
    public static CommandAPICommand equipment(){
        CommandAPICommand life = new CommandAPICommand("combat")
        .withSubcommand(FastSword())
        .withSubcommand(Sword())
        .withSubcommand(Helm())
        .withSubcommand(Chest())
        .withSubcommand(Legs())
        .withSubcommand(Boots())
        .withSubcommand(OBKit())
        .withSubcommand(HEX())
        .withSubcommand(Emblem())
        .withSubcommand(Glitch())
        .withSubcommand(Wither())
        .withSubcommand(Buff())
            .executesPlayer((Player player, CommandArguments args) -> {
                HelpMsg(player);
            });
            
        return life;
    }
    public static CommandAPICommand Beacon(){
        CommandAPICommand life = new CommandAPICommand("revive_beacon")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.ReviveBeacon);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Buff(){
        CommandAPICommand life = new CommandAPICommand("buff_potion")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.BuffPot);
                }
            });
            
        return life;
    }
    public static CommandAPICommand EmblemTotem(){
        CommandAPICommand life = new CommandAPICommand("powered_victory_emblem")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.TotemEmblem);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Glitch(){
        CommandAPICommand life = new CommandAPICommand("glitch_arrow")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.GlitchArrow);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Wither(){
        CommandAPICommand life = new CommandAPICommand("decay_splash")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.WitherPot);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Speedfrag(){
        CommandAPICommand life = new CommandAPICommand("speed_fragment")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.SpeedFragment);
                }
            });
            
        return life;
    }
    
    public static CommandAPICommand PureSF(){
        CommandAPICommand life = new CommandAPICommand("pure_speed_fragment")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.PureSF);
                }
            });
            
        return life;
    }
    public static CommandAPICommand HealthGem15(){
        CommandAPICommand life = new CommandAPICommand("health_essence")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.smallhgem);
                }
            });
            
        return life;
    }
    public static CommandAPICommand HealthGem20(){
        CommandAPICommand life = new CommandAPICommand("health_gem")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.HealthGem);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Haste(){
        CommandAPICommand life = new CommandAPICommand("haste_essence")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.HastePot);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Stillness(){
        CommandAPICommand life = new CommandAPICommand("stillness_essence")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.SlowPot);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Guardian(){
        CommandAPICommand life = new CommandAPICommand("guardian_essence")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.FatiguePot);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Raids(){
        CommandAPICommand life = new CommandAPICommand("stolen_shard")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.BadOmen);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Life(){
        CommandAPICommand life = new CommandAPICommand("life_feather")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.LifeUpGem);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Pearl(){
        CommandAPICommand life = new CommandAPICommand("compressed_pearl")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.CompressedPearl);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Stone(){
        CommandAPICommand life = new CommandAPICommand("magic_stone")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.MagicStone);
                }
            });
            
        return life;
    }
    public static CommandAPICommand FastSword(){
        CommandAPICommand life = new CommandAPICommand("fast_sword")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.testSword);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Sword(){
        CommandAPICommand life = new CommandAPICommand("sharp_sword")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.OPSword);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Helm(){
        CommandAPICommand life = new CommandAPICommand("helmet")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.OPHelm);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Chest(){
        CommandAPICommand life = new CommandAPICommand("chestplste")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.OPChest);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Legs(){
        CommandAPICommand life = new CommandAPICommand("leggings")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.OPLeggs);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Boots(){
        CommandAPICommand life = new CommandAPICommand("boots")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.OPBoots);
                }
            });
            
        return life;
    }
    public static CommandAPICommand OBKit(){
        CommandAPICommand life = new CommandAPICommand("overbuffed_kit")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.OPSword);
                    player.getInventory().addItem(Items.OPHelm);
                    player.getInventory().addItem(Items.OPChest);
                    player.getInventory().addItem(Items.OPLeggs);
                    player.getInventory().addItem(Items.OPBoots);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Pick(){
        CommandAPICommand life = new CommandAPICommand("lunar_pickaxe")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.lunarPick);
                }
            });
            
        return life;
    }    
    public static CommandAPICommand HEX(){
        CommandAPICommand life = new CommandAPICommand("health_extractor")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.HeartExtractor);
                }
            });
            
        return life;
    }
    public static CommandAPICommand Emblem(){
        CommandAPICommand life = new CommandAPICommand("victory_emblem")
            .withOptionalArguments(new IntegerArgument("amount", 1))
            .executesPlayer((Player player, CommandArguments args) -> {
                int i = (int) args.getOrDefault("amount", 1);
                for(int I = 0; I<i; I++){
                    player.getInventory().addItem(Items.Emblem);
                }
            });
            
        return life;
    }
    static void HelpMsg(Player player){
        player.sendMessage("/items <type> <item> [amount]");
    }
}
