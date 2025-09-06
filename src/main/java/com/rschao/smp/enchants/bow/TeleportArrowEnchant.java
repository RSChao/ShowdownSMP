package com.rschao.smp.enchants.bow;

import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class TeleportArrowEnchant extends Enchant {
    public TeleportArrowEnchant() {
        super("teleport_arrow");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.GOLD + "Teleport";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
    @Override @EventHandler
    public void onArrowShoot(EntityShootBowEvent ev) {
        ItemStack item = ev.getBow();
        if(item == null || item.getItemMeta() == null) return;
        if(ev.getForce() < 2.0) return;
        if(!hasEnchant(item)) return;
        Arrow arrow = (Arrow) ev.getProjectile();
        Location tpLocation = arrow.getLocation().add(0, 1, 0); // Teleport location is 1 block above the arrow's initial position
        new BukkitRunnable() {
            @Override
            public void run() {
                tpLocation.setX(arrow.getLocation().getX());
                tpLocation.setY(arrow.getLocation().getY() + 1);
                tpLocation.setZ(arrow.getLocation().getZ());
                tpLocation.setYaw(arrow.getLocation().getYaw());
                tpLocation.setPitch(arrow.getLocation().getPitch());
                if(arrow.isOnGround() || arrow.isDead()){
                    ev.getEntity().teleport(tpLocation);
                    this.cancel();
                }
            }
        }.runTaskTimer(Plugin.getPlugin(Plugin.class), 10, 2);
    }

    @Override
    public List<Material> getApplicableItems() {
        return List.of(Material.BOW, Material.CROSSBOW);

    }

    @Override
    public List<Enchant> getConflictingEnchants() {
        return List.of();
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}
