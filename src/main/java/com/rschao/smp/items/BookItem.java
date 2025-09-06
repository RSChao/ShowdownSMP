package com.rschao.smp.items;

import com.rschao.smp.enchants.definition.Enchant;
import org.bukkit.inventory.ItemStack;

public class BookItem {
    public static ItemStack makeEnchantedBook(Enchant enchant, int level) {
        ItemStack book = new ItemStack(org.bukkit.Material.ENCHANTED_BOOK);
        book = enchant.addEnchant(book, level);
        return book;
    }
}
