package com.rschao.smp.enchants.sword;

import com.rschao.smp.enchants.definition.Enchant;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BanEnchant extends Enchant {

    public BanEnchant() {
        super("ban");
    }

    @Override
    public String getDisplayName() {
        return "Ban";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public List<Material> getApplicableItems() {
        return List.of();
    }

    @Override
    public List<Enchant> getConflictingEnchants() {
        return List.of();
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @EventHandler
    public void OnDeath(PlayerDeathEvent e) {
        if (e.getEntity().isDead()) {
            Player p = e.getEntity();
            if(e.getEntity().getKiller() == null) return;
            ItemStack i = e.getEntity().getKiller().getInventory().getItemInMainHand();
            if (hasEnchant(i)) {
                if (i.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Sword of Judgement")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " " + i.getItemMeta().getDisplayName());
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "essentials:tempban " + p.getName() + " " + i.getItemMeta().getDisplayName());
                }
            }
        }
    }
}
