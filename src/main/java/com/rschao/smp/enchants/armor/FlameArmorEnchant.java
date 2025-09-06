package com.rschao.smp.enchants.armor;

import com.rschao.smp.enchants.definition.Enchant;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.awt.*;
import java.util.List;

public class FlameArmorEnchant extends Enchant {
    public FlameArmorEnchant() {
        super("flamearmor");
    }

    @Override
    public String getDisplayName() {
        return (ChatColor.of(new Color(255, 140, 0)) + "Burning Armor");
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public List<Material> getApplicableItems() {
        // a list of all armor types
        return List.of(
                Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
                Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
                Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
                Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
                Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
                Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS
        );
    }

    @Override
    public List<Enchant> getConflictingEnchants() {
        return List.of();
    }

    @Override @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        // Check if the damaged entity is a player
        if (!(ev.getEntity() instanceof org.bukkit.entity.Player player)) return;
        int i = 0;
        int level = 0;
        // Check if the player is wearing armor with this enchant
        for (Material material : getApplicableItems()) {
            for (org.bukkit.inventory.ItemStack armor : player.getInventory().getArmorContents()) {
                if (armor != null && armor.getType() == material && armor.getItemMeta() != null && hasEnchant(armor)) {
                    // Apply fire damage to the attacker
                    if (ev.getDamager() instanceof org.bukkit.entity.LivingEntity) {
                        int newlevel = armor.getItemMeta().getPersistentDataContainer().get(getKey(), org.bukkit.persistence.PersistentDataType.INTEGER);
                        if(newlevel > level) {
                            level = newlevel;
                        }
                        i++;
                    }
                }
            }
        }
        if (i > 0) {

            ev.getDamager().setFireTicks(40*level*i); // Set fire for 5 seconds
        }
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}
