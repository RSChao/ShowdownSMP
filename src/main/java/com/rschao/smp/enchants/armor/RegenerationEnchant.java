package com.rschao.smp.enchants.armor;

import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.List;
import java.util.Random;

public class RegenerationEnchant extends Enchant {
    public RegenerationEnchant() {
        super("regeneration");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.LIGHT_PURPLE + "Regeneration";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public List<Material> getApplicableItems() {
        //a list of all armor types
        return List.of(
                Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
                Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
                Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
                Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
                Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
                Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS, Material.ELYTRA
        );
    }

    @Override
    public List<Enchant> getConflictingEnchants() {
        return List.of();
    }

    @Override @EventHandler
    public void onArmorDamage(PlayerItemDamageEvent ev) {
        if (ev.getItem().getItemMeta() == null) return;
        if (!hasEnchant(ev.getItem())) return;

        int level = ev.getItem().getItemMeta().getPersistentDataContainer().get(getKey(), org.bukkit.persistence.PersistentDataType.INTEGER);
        if (level < 1) return;
        Random random = new Random();
        int rng = random.nextInt(100);
        if(rng > 4* level) return; // 4% chance per level to reduce damage
        // Reduce damage by 1 per level
        int reducedDamage = ev.getDamage()- ( 10 * level);
        if (reducedDamage < 0) {
            reducedDamage = 0; // Prevent negative damage
        }
        ev.setDamage(reducedDamage);
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}
