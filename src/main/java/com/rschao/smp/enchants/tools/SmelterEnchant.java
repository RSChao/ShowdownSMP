package com.rschao.smp.enchants.tools;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.util.ColorCodes;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class SmelterEnchant extends EasyEnchant {
    CustomEnchantment e;
    public SmelterEnchant() {
        super("smelter");
        CustomEnchantment enchant = makeEnchantment(ColorCodes.BLACK.getCode() + "Smelter");
        enchant.setSupportedItem("#minecraft:enchantable/mining");
        enchant.setMaxLevel(1);
        saveBukkitEnchantment(enchant);
        this.e = enchant;
    }


    @EventHandler
    void onBlockMine(BlockBreakEvent event) {
        Player p = event.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        int level = item.getEnchantmentLevel(e.toBukkitEnchantment());
        if (level <= 0) return;
        Material material = event.getBlock().getType();
        if(material.toString().contains("IRON_ORE")) {
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, 1));
        }
        else if(material.toString().contains("GOLD_ORE")) {
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 1));
        }

    }
}
