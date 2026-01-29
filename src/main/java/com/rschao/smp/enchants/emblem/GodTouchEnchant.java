package com.rschao.smp.enchants.emblem;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;
import net.md_5.bungee.api.ChatColor;

public class GodTouchEnchant extends EasyEnchant {
    public GodTouchEnchant() {
        super("emblem_god_touch");
        CustomEnchantment enchant = makeEnchantment(ChatColor.LIGHT_PURPLE + "[" + ChatColor.DARK_PURPLE + "God's " + ChatColor.GOLD + "Touch" + ChatColor.LIGHT_PURPLE + "]");
        enchant.addSupportedItem("minecraft:nether_star");
        enchant.setMaxLevel(1);
        saveBukkitEnchantment(enchant);
    }
}
