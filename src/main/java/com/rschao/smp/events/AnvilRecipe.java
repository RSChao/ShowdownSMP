package com.rschao.smp.events;

import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.NamespacedKey;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class AnvilRecipe implements Listener {
    @EventHandler
    void AnvilRecipes(PrepareAnvilEvent ev){
        ItemStack[] items = ev.getInventory().getContents();
        for(ItemStack i : items){
            if(i == null) return;
        }
        // --- Detección de encantamientos conflictivos ---
        // Obtiene todos los encantamientos presentes en ambos items
        boolean getEnchants = false;
        List<Enchant> enchantsPresent = new ArrayList<>();
        for (Enchant enchant : Plugin.getEnchants()) {
            if (items[0].getItemMeta() != null && items[0].getItemMeta().getPersistentDataContainer().has(enchant.getKey())) {
                enchantsPresent.add(enchant);
            }
            if (items[1].getItemMeta() != null && items[1].getItemMeta().getPersistentDataContainer().has(enchant.getKey())) {
                getEnchants = true;
                if (!enchantsPresent.contains(enchant)) enchantsPresent.add(enchant);
            }
        }
        if (!getEnchants) return; // No hay encantamientos personalizados, no hacer nada
        // Verifica conflictos
        boolean conflictFound = false;
        for (Enchant e1 : enchantsPresent) {
            for (Enchant e2 : enchantsPresent) {
                if (e1 != e2 && e1.getConflictingEnchants().stream().anyMatch(conflict -> conflict.getKey().equals(e2.getKey()))) {
                    conflictFound = true;
                    break;
                }
            }
            if (conflictFound) break;
        }
        if (conflictFound) {
            ev.setResult(null); // No se puede encantar si hay conflicto
            return;
        }
        if (items[1].getType() == Material.ENCHANTED_BOOK && items[0].getType() != Material.ENCHANTED_BOOK) {
            ItemMeta itemMeta = items[0].getItemMeta();
            ItemMeta bookMeta = items[1].getItemMeta();
            if (itemMeta == null || bookMeta == null) return;

            ItemStack result = items[0].clone();
            ItemMeta resultMeta = result.getItemMeta();
            List<String> lore = new ArrayList<>();

            for (Enchant enchant : Plugin.getAllEnchants()) {
                NamespacedKey key = enchant.getKey();
                int itemLevel = itemMeta.getPersistentDataContainer().has(key) ? itemMeta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int bookLevel = bookMeta.getPersistentDataContainer().has(key) ? bookMeta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int newLevel = Math.max(itemLevel, bookLevel);

                if (bookLevel > 0 && enchant.getApplicableItems().contains(items[0].getType())) {
                    if (newLevel > enchant.getMaxLevel()) newLevel = enchant.getMaxLevel();
                    resultMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, newLevel);
                    String loreText = enchant.getDisplayName();
                    if (enchant.getMaxLevel() > 1 && newLevel > 1) {
                        loreText += " " + newLevel;
                    }
                    lore.add(loreText);
                } else if (itemLevel > 0) {
                    // Mantener encantamientos previos aunque el libro no los tenga
                    String loreText = enchant.getDisplayName();
                    if (enchant.getMaxLevel() > 1 && itemLevel > 1) {
                        loreText += " " + itemLevel;
                    }
                    lore.add(loreText);
                }
            }
            if (!lore.isEmpty()) {
                resultMeta.setLore(lore);
                resultMeta.setEnchantmentGlintOverride(true);
                result.setItemMeta(resultMeta);
                ev.setResult(result);
                return;
            }
            // --- Fusión genérica de libros encantados personalizados ---
            // Solo si ambos son libros encantados
            if (items[0].getType() == Material.ENCHANTED_BOOK && items[1].getType() == Material.ENCHANTED_BOOK) {
                ItemMeta meta0 = items[0].getItemMeta();
                ItemMeta meta1 = items[1].getItemMeta();
                if (meta0 == null || meta1 == null) return;

                ItemStack fusedBook = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta fusedMeta = fusedBook.getItemMeta();
                lore = new ArrayList<>();

                for (Enchant enchant : Plugin.getAllEnchants()) {
                    NamespacedKey key = enchant.getKey();
                    int level0 = meta0.getPersistentDataContainer().has(key) ? meta0.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                    int level1 = meta1.getPersistentDataContainer().has(key) ? meta1.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                    int newLevel = 0;

                    if (level0 > 0 && level1 > 0 && level0 == level1 && level0 < enchant.getMaxLevel()) {
                        newLevel = level0 + 1; // mejora el nivel
                    } else {
                        newLevel = Math.max(level0, level1); // copia el mayor nivel
                    }

                    if (newLevel > 0) {
                        if (newLevel > enchant.getMaxLevel()) newLevel = enchant.getMaxLevel();
                        fusedMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, newLevel);
                        String loreText = enchant.getDisplayName();
                        if (enchant.getMaxLevel() > 1 && newLevel > 1) {
                            loreText += " " + newLevel;
                        }
                        lore.add(loreText);
                    }
                }
                if (!lore.isEmpty()) {
                    fusedMeta.setLore(lore);
                    fusedMeta.setEnchantmentGlintOverride(true);
                    fusedBook.setItemMeta(fusedMeta);
                    ev.setResult(fusedBook);
                    return;
                }
            }
        }
        if (items[0].getType() == Material.ENCHANTED_BOOK && items[1].getType() == Material.ENCHANTED_BOOK) {
            ItemMeta meta0 = items[0].getItemMeta();
            ItemMeta meta1 = items[1].getItemMeta();
            if (meta0 == null || meta1 == null) return;

            ItemStack fusedBook = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta fusedMeta = fusedBook.getItemMeta();
            List<String> lore = new ArrayList<>();

            for (Enchant enchant : Plugin.getAllEnchants()) {
                NamespacedKey key = enchant.getKey();
                int level0 = meta0.getPersistentDataContainer().has(key) ? meta0.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int level1 = meta1.getPersistentDataContainer().has(key) ? meta1.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int newLevel = 0;

                if (level0 > 0 && level1 > 0) {
                    newLevel = (level0 == level1 && level0 < enchant.getMaxLevel()) ? level0 + 1 : Math.max(level0, level1);
                } else {
                    newLevel = Math.max(level0, level1);
                }

                if (newLevel > 0) {
                    if (newLevel > enchant.getMaxLevel()) newLevel = enchant.getMaxLevel();
                    fusedMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, newLevel);
                    String loreText = enchant.getDisplayName();
                    if (enchant.getMaxLevel() > 1 && newLevel > 1) {
                        loreText += " " + newLevel;
                    }
                    lore.add(loreText);
                }
            }
            if (!lore.isEmpty()) {
                fusedMeta.setLore(lore);
                fusedMeta.setEnchantmentGlintOverride(true);
                fusedBook.setItemMeta(fusedMeta);
                ev.setResult(fusedBook);
            }
        }
        if (items[0].getType() != Material.ENCHANTED_BOOK && items[1].getType() != Material.ENCHANTED_BOOK) {
            ItemMeta meta0 = items[0].getItemMeta();
            ItemMeta meta1 = items[1].getItemMeta();
            if (meta0 == null || meta1 == null) return;
            if(items[0].getType() != items[1].getType()) return; // No fusionar diferentes tipos de items

            // Cambiado: clonar el primer item como base
            ItemStack fusedItem = items[0].clone();
            ItemMeta fusedMeta = fusedItem.getItemMeta();
            // Limpiar lore previo para regenerar
            fusedMeta.setLore(null);
            List<String> lore = new ArrayList<>();
            for (Enchant enchant : Plugin.getAllEnchants()) {
                NamespacedKey key = enchant.getKey();
                int level0 = meta0.getPersistentDataContainer().has(key) ? meta0.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int level1 = meta1.getPersistentDataContainer().has(key) ? meta1.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int newLevel = 0;

                if (level0 > 0 && level1 > 0) {
                    newLevel = (level0 == level1 && level0 < enchant.getMaxLevel()) ? level0 + 1 : Math.max(level0, level1);
                } else {
                    newLevel = Math.max(level0, level1);
                }

                if (newLevel > 0) {
                    if (newLevel > enchant.getMaxLevel()) newLevel = enchant.getMaxLevel();
                    fusedMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, newLevel);
                    String loreText = enchant.getDisplayName();
                    if (enchant.getMaxLevel() > 1 && newLevel > 1) {
                        loreText += " " + newLevel;
                    }
                    lore.add(loreText);
                }
            }
            if (!lore.isEmpty()) {
                fusedMeta.setLore(lore);
                fusedMeta.setEnchantmentGlintOverride(true);
                fusedItem.setItemMeta(fusedMeta);
                ev.setResult(fusedItem);
            }
        }
    }
    @EventHandler
    void onInventoryClick(InventoryClickEvent ev) {
        if (ev.getInventory() instanceof AnvilInventory anvilInventory) {
            if (ev.getSlotType() == InventoryType.SlotType.RESULT) {
                ItemStack result = anvilInventory.getItem(2);
                if (result != null && result.isSimilar(result)) {
                    Player player = (Player) ev.getWhoClicked();
                    player.getInventory().addItem(result);
                    anvilInventory.clear();
                    ev.setCancelled(true);
                }
            }
        }
    }
}
