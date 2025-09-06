package com.rschao.smp.events;

import com.rschao.smp.Plugin;
import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.function.BiFunction;

public class CustomRecipeChecker {
    private static final List<BiFunction<ItemStack, ItemStack, ItemStack>> customRecipes = new ArrayList<>();

    // Permite agregar recetas personalizadas
    public static void addRecipe(BiFunction<ItemStack, ItemStack, ItemStack> recipe) {
        customRecipes.add(recipe);
    }

    // Verifica todas las recetas agregadas y luego las recetas tipo anvil
    public static ItemStack checkRecipe(ItemStack a, ItemStack b) {
        if (a == null || b == null) return null;

        // Primero verifica recetas personalizadas
        for (BiFunction<ItemStack, ItemStack, ItemStack> recipe : customRecipes) {
            ItemStack result = recipe.apply(a, b);
            if (result != null) return result;
        }

        // --- Lógica de recetas tipo anvil (idéntica a AnvilRecipe) ---
        // Detección de encantamientos conflictivos
        List<Enchant> enchantsPresent = new ArrayList<>();
        for (Enchant enchant : Plugin.getEnchants()) {
            if (a.getItemMeta() != null && a.getItemMeta().getPersistentDataContainer().has(enchant.getKey())) {
                enchantsPresent.add(enchant);
            }
            if (b.getItemMeta() != null && b.getItemMeta().getPersistentDataContainer().has(enchant.getKey())) {
                if (!enchantsPresent.contains(enchant)) enchantsPresent.add(enchant);
            }
        }
        for (Enchant e1 : enchantsPresent) {
            for (Enchant e2 : enchantsPresent) {
                if (e1 != e2 && e1.getConflictingEnchants().stream().anyMatch(conflict -> conflict.getKey().equals(e2.getKey()))) {
                    return null; // Conflicto, no se puede combinar
                }
            }
        }

        // Libro encantado + item
        if (b.getType() == Material.ENCHANTED_BOOK && a.getType() != Material.ENCHANTED_BOOK) {
            ItemMeta itemMeta = a.getItemMeta();
            ItemMeta bookMeta = b.getItemMeta();
            if (itemMeta == null || bookMeta == null) return null;

            ItemStack result = a.clone();
            ItemMeta resultMeta = result.getItemMeta();
            List<String> lore = new ArrayList<>();

            for (Enchant enchant : Plugin.getAllEnchants()) {
                NamespacedKey key = enchant.getKey();
                int itemLevel = itemMeta.getPersistentDataContainer().has(key) ? itemMeta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int bookLevel = bookMeta.getPersistentDataContainer().has(key) ? bookMeta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int newLevel = Math.max(itemLevel, bookLevel);

                if (bookLevel > 0 && enchant.getApplicableItems().contains(a.getType())) {
                    if (newLevel > enchant.getMaxLevel()) newLevel = enchant.getMaxLevel();
                    resultMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, newLevel);
                    String loreText = enchant.getDisplayName();
                    if (enchant.getMaxLevel() > 1 && newLevel > 1) {
                        loreText += " " + newLevel;
                    }
                    lore.add(loreText);
                } else if (itemLevel > 0) {
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
                return result;
            }
        }

        // Libro encantado + libro encantado
        if (a.getType() == Material.ENCHANTED_BOOK && b.getType() == Material.ENCHANTED_BOOK) {
            ItemMeta meta0 = a.getItemMeta();
            ItemMeta meta1 = b.getItemMeta();
            if (meta0 == null || meta1 == null) return null;

            ItemStack fusedBook = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta fusedMeta = fusedBook.getItemMeta();
            List<String> lore = new ArrayList<>();

            for (Enchant enchant : Plugin.getAllEnchants()) {
                NamespacedKey key = enchant.getKey();
                int level0 = meta0.getPersistentDataContainer().has(key) ? meta0.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int level1 = meta1.getPersistentDataContainer().has(key) ? meta1.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) : 0;
                int newLevel = 0;

                if (level0 > 0 && level1 > 0 && level0 == level1 && level0 < enchant.getMaxLevel()) {
                    newLevel = level0 + 1;
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
                return fusedBook;
            }
        }

        // Item + item (mismo tipo)
        if (a.getType() != Material.ENCHANTED_BOOK && b.getType() != Material.ENCHANTED_BOOK) {
            ItemMeta meta0 = a.getItemMeta();
            ItemMeta meta1 = b.getItemMeta();
            if (meta0 == null || meta1 == null) return null;
            if (a.getType() != b.getType()) return null;

            // Cambiado: clonar el primer item como base
            ItemStack fusedItem = a.clone();
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
                return fusedItem;
            }
        }

        return null;
    }
}
