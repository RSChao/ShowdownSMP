package com.rschao.smp.enchants.bow;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;
import org.bukkit.ChatColor;

public class UltraInfinityEnchant extends EasyEnchant {

    public UltraInfinityEnchant() {
        super("ultra_infinity");
        CustomEnchantment enchant = makeEnchantment(ChatColor.DARK_GREEN + "Ultra Infinity");
        enchant.addSupportedItem("minecraft:bow");
        enchant.addSupportedItem("minecraft:crossbow");
        enchant.setMaxLevel(1);
        saveBukkitEnchantment(enchant);
    }
}
