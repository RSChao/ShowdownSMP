package com.rschao.smp.enchants.armor;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FlameArmorEnchant extends EasyEnchant {
    public FlameArmorEnchant() {
        super("flame_armor");
        CustomEnchantment enchant = makeEnchantment(ChatColor.GOLD.name() + "Flame Armor");
        enchant.setSupportedItem("#minecraft:enchantable/armor");
        enchant.setMaxLevel(2);
        saveBukkitEnchantment(enchant);
    }
    @Override @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        // Check if the damaged entity is a player
        if (!(ev.getEntity() instanceof org.bukkit.entity.Player player)) return;
        int i = 0;
        int level = 0;
        // Check if the player is wearing armor with this enchant
        for (org.bukkit.inventory.ItemStack armor : player.getInventory().getArmorContents()) {
            if (hasEnchantment(armor)) {
                // Apply fire damage to the attacker
                if (ev.getDamager() instanceof org.bukkit.entity.LivingEntity) {
                    int newlevel = armor.getEnchantmentLevel(Enchantment.getByKey(getKey()));
                    if(newlevel > level) {
                        level = newlevel;
                    }
                    i++;
                }
            }
        }
        if (i > 0) {

            ev.getDamager().setFireTicks(40*level*i); // Set fire for 5 seconds
        }
    }
}
