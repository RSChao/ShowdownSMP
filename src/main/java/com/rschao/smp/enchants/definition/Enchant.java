package com.rschao.smp.enchants.definition;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Enchant implements Listener {
    private final NamespacedKey key;
    public Enchant(String key) {
        this.key = makeEnchantKey(key);
    }

    public NamespacedKey getKey() {
        return key;
    }
    public abstract String getDisplayName();
    public abstract int getMaxLevel();
    public ItemStack addEnchant(ItemStack item, int level) {
        if(item == null) return null;
        if (item.getItemMeta() == null) return null;
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(getKey(), org.bukkit.persistence.PersistentDataType.INTEGER, level);
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        } else {
            lore = new ArrayList<>(lore); // copia mutable
        }
        meta.setEnchantmentGlintOverride(true);
        String loreText = getDisplayName();
        if (level > 1) {
            loreText += " " + level;
        }
        lore.add(loreText);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        // Implementación vacía opcional
    }

    @EventHandler
    public void onArrowShoot(EntityShootBowEvent ev) {
        // Implementación vacía opcional
    }
    @EventHandler
    public void onArmorDamage(PlayerItemDamageEvent ev){

    }
    public boolean hasEnchant(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().has(getKey());
    }
    public ItemStack removeEnchant(ItemStack item) {
        if (Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().has(getKey())) {
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            for(String line : Objects.requireNonNull(meta.getLore())) {
                if (!line.startsWith(getDisplayName())) {
                    lore.add(line);
                }
            }
            meta.setLore(lore);
            meta.getPersistentDataContainer().remove(getKey());
            meta.setEnchantmentGlintOverride(false);
            item.setItemMeta(meta);
            return item;
        }
        return null;
    }
    public int getEnchantLevel(ItemStack item) {
        if (item == null || item.getItemMeta() == null || !item.getItemMeta().getPersistentDataContainer().has(getKey())) {
            return 0;
        }
        return item.getItemMeta().getPersistentDataContainer().get(getKey(), org.bukkit.persistence.PersistentDataType.INTEGER);
    }
    public static NamespacedKey makeEnchantKey(String key) {
        return new NamespacedKey("ench", key);

    }
    public abstract List<Material> getApplicableItems();
    public abstract List<Enchant> getConflictingEnchants();
    public abstract boolean isTreasure();
}
