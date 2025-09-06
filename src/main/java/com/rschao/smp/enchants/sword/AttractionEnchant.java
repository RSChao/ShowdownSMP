package com.rschao.smp.enchants.sword;

import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class AttractionEnchant extends Enchant {
    public AttractionEnchant() {
        super("attraction");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.RED + "Attraction";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public List<Material> getApplicableItems() {
        return List.of(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD);
    }

    @Override
    public List<Enchant> getConflictingEnchants() {
        return List.of(new LifeDrainEnchant());
    }

    @Override @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        //get item from the event even if entity is not player
        if (ev.getDamager() instanceof org.bukkit.entity.Player player) {
            if (player.getInventory().getItemInMainHand().getItemMeta() == null) return;
            if (!hasEnchant(player.getInventory().getItemInMainHand())) return;

            // Attraction logic: pull the target towards the player
            if (ev.getEntity() instanceof org.bukkit.entity.LivingEntity target) {
                int level = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(getKey(), org.bukkit.persistence.PersistentDataType.INTEGER);
                Location location = player.getLocation();
                Vector direction = location.toVector().subtract(target.getLocation().toVector()).normalize();
                target.setVelocity(direction.multiply(level/2)); // Adjust speed as needed
            }
        }
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}
