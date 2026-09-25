package com.rschao.smp.events;

import com.rschao.smp.Plugin;
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

        return null;
    }
}
