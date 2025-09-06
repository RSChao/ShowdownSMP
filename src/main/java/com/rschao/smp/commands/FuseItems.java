package com.rschao.smp.commands;

import com.rschao.smp.events.CustomRecipeChecker;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import org.bukkit.inventory.ItemStack;

public class FuseItems {
    public static CommandAPICommand LoadCommand() {
        return new CommandAPICommand("fuse")
                .withPermission("smp.admin")
                .withSubcommands(Hotbar(), Inventory())
                .executesPlayer((player, args) -> {
                    player.sendMessage("/fuseitems <inventory|hotbar> <item1> <item2> <level>");

                });

    }

    static CommandAPICommand Inventory(){
        return new CommandAPICommand("inventory")
                .withArguments(new IntegerArgument("slot1"), new IntegerArgument("slot2"))
                .executesPlayer((player, args) -> {
                    int slot1 = (Integer) args.get("slot1");
                    int slot2 = (Integer) args.get("slot2");
                    slot1+= 9; // Ajuste para inventario (slots 0-8 son la barra de acceso rápido)
                    slot2+= 9; // Ajuste para inventario (slots 0-8 son la barra de acceso rápido)
                    if (slot1 < 9 || slot1 > 35 || slot2 < 9 || slot2 > 35) {
                        player.sendMessage("§cInvalid slots. Please use slots 0-26 for the inventory.");
                        return;
                    }
                    ItemStack item1 = player.getInventory().getItem(slot1);
                    ItemStack item2 = player.getInventory().getItem(slot2);
                    if (item1 == null || item2 == null) {
                        player.sendMessage("§cOne of the items is not present in the specified slots.");
                        return;
                    }
                    ItemStack result = CustomRecipeChecker.checkRecipe(item1, item2);
                    if (result == null) {
                        player.sendMessage("§cThe items cannot be fused.");
                    } else {
                        player.getInventory().setItem(slot1, result);
                        player.getInventory().setItem(slot2, null);
                        player.sendMessage("§aItems fused successfully!");
                    }
                });
    }
    static CommandAPICommand Hotbar(){
        return new CommandAPICommand("hotbar")
                .withArguments(new IntegerArgument("slot1"), new IntegerArgument("slot2"))
                .executesPlayer((player, args) -> {
                    int slot1 = (Integer) args.get("slot1");
                    int slot2 = (Integer) args.get("slot2");
                    if (slot1 < 0 || slot1 > 8 || slot2 < 0 || slot2 > 8) {
                        player.sendMessage("§cInvalid slots. Please use slots 0-8 for the inventory.");
                        return;
                    }
                    ItemStack item1 = player.getInventory().getItem(slot1);
                    ItemStack item2 = player.getInventory().getItem(slot2);
                    if (item1 == null || item2 == null) {
                        player.sendMessage("§cOne of the items is not present in the specified slots.");
                        return;
                    }
                    ItemStack result = CustomRecipeChecker.checkRecipe(item1, item2);
                    if (result == null) {
                        player.sendMessage("§cThe items cannot be fused.");
                    } else {
                        player.getInventory().setItem(slot1, result);
                        player.getInventory().setItem(slot2, null);
                        player.sendMessage("§aItems fused successfully!");
                    }
                });
    }
}
