package com.rschao.smp.enchants.sword;

import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LifeDrainEnchant extends Enchant {
    public LifeDrainEnchant() {
        super("drain");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.DARK_RED + "Life Drain";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public List<Material> getApplicableItems() {
        return List.of(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD);
    }
    @Override @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        //get item from the event even if entity is not player
        if (ev.getDamager() instanceof org.bukkit.entity.Player player) {
            player.getInventory().getItemInMainHand();
            if (player.getInventory().getItemInMainHand().getItemMeta() == null) return;
            if (!hasEnchant(player.getInventory().getItemInMainHand())) return;
            Random random = new Random();
            if(random.nextInt(0, 100) > 30) return;
            @SuppressWarnings("DataFlowIssue") int level = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(getKey(), org.bukkit.persistence.PersistentDataType.INTEGER);
            if (level < 1) return;

            double damage = ev.getFinalDamage();
            double healAmount = damage * (0.1 * level); // 10% per level
            player.setHealth(Math.min(player.getHealth() + healAmount, Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getBaseValue()));
        }
    }

    @Override
    public List<Enchant> getConflictingEnchants() {
        return List.of(new AttractionEnchant());
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}
