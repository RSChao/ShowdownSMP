package com.rschao.smp.enchants.bow;

import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class UltraInfinityEnchant extends Enchant {

    public UltraInfinityEnchant() {
        super("infinity");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.DARK_GREEN + "Ultra Infinity";
    }

    @Override
    public int getMaxLevel() {
        return 1;
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
        return true;
    }

    @Override @EventHandler
    public void onArrowShoot(EntityShootBowEvent ev) {
        if(ev.getBow() == null || ev.getBow().getItemMeta() == null) return;
        if (!hasEnchant(ev.getBow())) return;
        if(ev.getConsumable().getType() == Material.ARROW || ev.getConsumable().getType() == Material.SPECTRAL_ARROW || ev.getConsumable().getType() == Material.TIPPED_ARROW) {

            // Prevent the arrow from being consumed

            if(!(ev.getEntity() instanceof Player p)) return;

            for(ItemStack i: p.getInventory().getContents()) {
                if(i != null && i.getType() == Material.ARROW || i.getType() == Material.SPECTRAL_ARROW || i.getType() == Material.TIPPED_ARROW) {
                    i.setAmount(i.getAmount() + 1); // Add back the arrow to the inventory
                    break;
                }
            }
            Arrow arrow = (Arrow) ev.getProjectile();
            arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);

        }

    }
}
