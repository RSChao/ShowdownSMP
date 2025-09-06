package com.rschao.smp.enchants.elytra;

import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ForceFieldEnchant extends Enchant {
    public ForceFieldEnchant() {
        super("forcefield");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.AQUA + "Force Field";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public List<Material> getApplicableItems() {
        return List.of(Material.ELYTRA);
    }

    @Override
    public List<Enchant> getConflictingEnchants() {
        return List.of(new CrowdControlEnchant());
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @EventHandler
    void onFirework(PlayerInteractEvent ev){
        Player p = ev.getPlayer();
        if(!hasEnchant(p.getInventory().getItem(EquipmentSlot.CHEST))) return;
        if(!p.isGliding()) return;
        if(ev.getItem().getType() == Material.FIREWORK_ROCKET || ev.getItem().getType() == Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE){
            new BukkitRunnable() {
                @Override
                public void run() {
                    int level = getEnchantLevel(p.getInventory().getItem(EquipmentSlot.CHEST));
                    Set<Block> set = sphereAround(p.getLocation(), 2*level);
                    for(Block block : set){
                        if(block.getType() == Material.BEDROCK) continue;
                        block.setType(Material.AIR);
                    }
                }
            }.runTaskTimer(Plugin.getPlugin(Plugin.class), 3, 0);
        }

    }

    public static Set<Block> sphereAround(Location location, int radius) {
        Set<Block> sphere = new HashSet<Block>();
        Block center = location.getBlock();
        for(int x = -radius; x <= radius; x++) {
            for(int y = -radius; y <= radius; y++) {
                for(int z = -radius; z <= radius; z++) {
                    Block b = center.getRelative(x, y, z);
                    double dist = center.getLocation().distance(b.getLocation());
                    if(dist <= radius && dist > (radius - 2)) {
                        sphere.add(b);
                    }
                }
            }
        }
        return sphere;
    }
}
