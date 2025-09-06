package com.rschao.smp.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class BanInv implements InventoryHolder{

    private Inventory inv;

    public BanInv(){
        inv = Bukkit.createInventory(this, 45, "Ban players");
        Init();
    }

    void Init(){
        for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
            if (!p.isBanned()) {
                // Add player's head to custom inventory
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
                meta.setOwningPlayer(p);
                playerHead.setItemMeta(meta);
                
                // Add player's head to a custom inventory
                inv.addItem(playerHead);
            }
        }
        for(Player p : Bukkit.getOnlinePlayers()){
            if (!p.isBanned()) {
                // Add player's head to custom inventory
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
                meta.setOwningPlayer(p);
                playerHead.setItemMeta(meta);
                
                // Add player's head to a custom inventory
                inv.addItem(playerHead);
            }
        }
    }

    @Override
    public Inventory getInventory() {
        
        return inv;
    }
    
}
