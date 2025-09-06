package com.rschao.smp.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class WorldEditItems {
    public static ItemStack setter;
    public static ItemStack replacer;
    public static ItemStack copier;
    public static ItemStack paster;
    public static ItemStack undoer;
    public static ItemStack spherer;
    public static ItemStack cylinderer;

    public static void init(){
        setter = setter();
        replacer = replacer();
        copier = copier();
        paster = paster();
        undoer = undoer();
        spherer = spherer();
        cylinderer = cylinderer();
    }

    static ItemStack setter(){
        ItemStack item = new ItemStack(org.bukkit.Material.DIAMOND_HOE);
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lWorldEdit Setter");
        java.util.List<String> lore = new java.util.ArrayList<>();
        lore.add("§7Material used: top-left inventory slot");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey("smp", "worldedit_setter"), org.bukkit.persistence.PersistentDataType.STRING, "worldedit_setter");
        item.setItemMeta(meta);
        return item;
    }
    static ItemStack replacer(){
        ItemStack item = new ItemStack(org.bukkit.Material.GOLDEN_HOE);
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6§lWorldEdit Replacer");
        java.util.List<String> lore = new java.util.ArrayList<>();
        lore.add("§7Material used: top-left inventory slot");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey("smp", "worldedit_replacer"), org.bukkit.persistence.PersistentDataType.STRING, "worldedit_replacer");
        item.setItemMeta(meta);
        return item;
    }
    static ItemStack copier() {
        ItemStack item = new ItemStack(org.bukkit.Material.IRON_HOE);
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§lWorldEdit Copier");
        java.util.List<String> lore = new java.util.ArrayList<>();
        lore.add("§7Copies the selected region");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey("smp", "worldedit_copier"), org.bukkit.persistence.PersistentDataType.STRING, "worldedit_copier");
        item.setItemMeta(meta);
        return item;
    }
    static ItemStack paster() {
        ItemStack item = new ItemStack(Material.WOODEN_HOE);
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7§lWorldEdit Paster");
        java.util.List<String> lore = new java.util.ArrayList<>();
        lore.add("§7Pastes the copied region");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey("smp", "worldedit_paster"), org.bukkit.persistence.PersistentDataType.STRING, "worldedit_paster");
        item.setItemMeta(meta);
        return item;
    }
    static ItemStack undoer() {
        ItemStack item = new ItemStack(Material.STONE_HOE);
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8§lWorldEdit Undoer");
        java.util.List<String> lore = new java.util.ArrayList<>();
        lore.add("§7Undoes your last WorldEdit action");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey("smp", "worldedit_undoer"), org.bukkit.persistence.PersistentDataType.STRING, "worldedit_undoer");
        item.setItemMeta(meta);
        return item;
    }
    static ItemStack spherer() {
        ItemStack item = new ItemStack(Material.HEART_OF_THE_SEA);
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§lWorldEdit Spherer");
        java.util.List<String> lore = new java.util.ArrayList<>();
        lore.add("§7Creates a sphere of the material in your top-left inventory slot");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey("smp", "worldedit_spherer"), org.bukkit.persistence.PersistentDataType.STRING, "worldedit_spherer");
        item.setItemMeta(meta);
        return item;
    }
    static ItemStack cylinderer() {
        ItemStack item = new ItemStack(Material.NAUTILUS_SHELL);
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lWorldEdit Cylinderer");
        java.util.List<String> lore = new java.util.ArrayList<>();
        lore.add("§7Creates a cylinder of the material in your top-left inventory slot");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey("smp", "worldedit_cylinderer"), org.bukkit.persistence.PersistentDataType.STRING, "worldedit_cylinderer");
        item.setItemMeta(meta);
        return item;
    }

}
