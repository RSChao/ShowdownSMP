package com.rschao.smp.enchants.elytra;

import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class CrowdControlEnchant extends Enchant {
    public CrowdControlEnchant() {
        super("crowd");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.LIGHT_PURPLE + "Crowd Control";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public List<Material> getApplicableItems() {
        return List.of(Material.ELYTRA);
    }

    @Override
    public List<Enchant> getConflictingEnchants() {
        return List.of(new ForceFieldEnchant());
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
    @EventHandler
    void onFirework(PlayerMoveEvent ev){
        Player p = ev.getPlayer();
        if(!hasEnchant(p.getInventory().getItem(EquipmentSlot.CHEST))) return;
        if(!p.isGliding()) return;
            for(Entity e : p.getNearbyEntities(7, 7, 7)){
                if(!(e instanceof LivingEntity)) continue;
                Vector direction = e.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
                // Apply the push
                e.setVelocity(direction.multiply(10));
            }

    }
}
