package com.rschao.smp.inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.rschao.smp.lives.saveData;

import net.md_5.bungee.api.ChatColor;

public class ReviveSelect implements InventoryHolder{

    private Inventory inv;

    public ReviveSelect(){
        inv = Bukkit.createInventory(this, 45, "Revive dead players");
        Init();
    }

    void Init(){
        for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
            if (saveData.getLives(p) < 1) {
                if(p.isBanned()) continue;
                // Add player's head to custom inventory
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
                meta.setOwningPlayer(p);
                List<String> list = new ArrayList<String>();
                list.add(p.getName());
                meta.setLore(list);
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

    public final ItemStack returnHead(Player p) {
        ItemStack head = getHead(p);
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setItemName(ChatColor.translateAlternateColorCodes('&', "&7" + p.getName()));
        head.setItemMeta(headMeta);
        return head;
    }

    private ItemStack getHead(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setItemName(player.getName());
        skull.setOwningPlayer(player);
        item.setItemMeta(skull);
        return item;
    }
    
}
