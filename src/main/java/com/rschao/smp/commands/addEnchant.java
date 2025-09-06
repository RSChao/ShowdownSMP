package com.rschao.smp.commands;

import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class addEnchant {

    static List<String> getEnchants() {
        List<String> enchants = new ArrayList<>();

        for (String enchant : com.rschao.smp.Plugin.getAllEnchants().stream().map(e -> e.getKey().getKey()).toList()) {
            enchants.add(enchant);
        }
        return enchants;
    }
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("addenchant")
                .withArguments(new StringArgument("ench").replaceSuggestions(ArgumentSuggestions.merge(ArgumentSuggestions.strings(getEnchants()))))
                .withOptionalArguments(new IntegerArgument("level"), new BooleanArgument("unsafe"))
                .withPermission("smp.admin")
                .executesPlayer((Player player, CommandArguments args) -> {
                    String enchantName = (String) args.get("ench");
                    for(Enchant e : Plugin.getAllEnchants()){
                        ItemStack itemInUse = player.getInventory().getItemInMainHand();
                        int level = args.getOptional("level").isPresent() ? (Integer) args.get("level") : 1;
                        boolean unsafe = args.getOptional("unsafe").isPresent() ? (Boolean) args.get("unsafe"): false;
                        if(itemInUse == null || itemInUse.getType().isAir()) {
                            player.sendMessage("§cYou must hold an item to add an enchantment.");
                            return;
                        }
                        if(e.getKey().getKey().equals(enchantName)){
                            if(unsafe) {
                                e.addEnchant(itemInUse, level);
                            } else {
                                if(!e.getApplicableItems().contains(itemInUse.getType())) {
                                    player.sendMessage("§cThis enchant cannot be applied to this item.");
                                    return;
                                }
                                boolean conflict = false;
                                for (Enchant enchant : Plugin.getAllEnchants()) {
                                    if (enchant.getKey().getKey().equals(enchantName)) continue;
                                    if (enchant.getConflictingEnchants().contains(e.getKey())) {
                                        conflict = true;
                                        break;
                                    }
                                }
                                if(conflict) {
                                    player.sendMessage("§cThis enchant conflicts with another enchant on this item.");
                                    return;
                                }
                                e.addEnchant(itemInUse, level);
                            }
                        }
                    }
                });

        return life;
    }
}
