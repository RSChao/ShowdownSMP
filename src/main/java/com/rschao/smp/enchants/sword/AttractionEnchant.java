package com.rschao.smp.enchants.sword;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class AttractionEnchant extends EasyEnchant {
    public AttractionEnchant() {
        super("attraction");
        CustomEnchantment enchant = makeEnchantment(ChatColor.RED + "Attraction");
        enchant.setSupportedItem("#minecraft:enchantable/sharp_weapon");
        enchant.addExcludedEnchantment("minecraft:drain");
        enchant.addExcludedEnchantment("minecraft:knockback");
        enchant.setMaxLevel(2);
        saveBukkitEnchantment(enchant);
    }
    @Override @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        if (ev.getDamager() instanceof org.bukkit.entity.Player player) {
            if (player.getInventory().getItemInMainHand().getItemMeta() == null) return;
            if (!hasEnchantment(player.getInventory().getItemInMainHand())) return;

            // Attraction logic: pull the target towards the player
            if (ev.getEntity() instanceof org.bukkit.entity.LivingEntity target) {
                int level = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.getByKey(getKey()));
                Location location = player.getLocation();
                Vector direction = location.toVector().subtract(target.getLocation().toVector()).normalize();
                target.setVelocity(direction.multiply(level/2)); // Adjust speed as needed
            }
        }
    }
}
