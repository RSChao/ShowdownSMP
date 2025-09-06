package com.rschao.smp.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import com.rschao.smp.Plugin;
import com.rschao.smp.inventories.BanInv;
import com.rschao.smp.inventories.ReviveSelect;
import com.rschao.smp.inventories.UnbanInv;
import com.rschao.smp.lives.saveData;

import net.md_5.bungee.api.ChatColor;

public class invEvents implements Listener{

    NamespacedKey BeaconKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "beakon");
    NamespacedKey UnbanBeaconKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "unbankon");
    NamespacedKey BanBeaconKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "bankon");
    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent ev){
        Player player = ev.getPlayer();
        if(ev.getItemInHand().getItemMeta().getPersistentDataContainer().has(BeaconKey, PersistentDataType.BOOLEAN)){
            ev.setCancelled(true);
            ReviveSelect gui = new ReviveSelect();
            player.openInventory(gui.getInventory());
        }
        if(ev.getItemInHand().getItemMeta().getPersistentDataContainer().has(UnbanBeaconKey, PersistentDataType.BOOLEAN)){
            ev.setCancelled(true);
            UnbanInv gui = new UnbanInv();
            player.openInventory(gui.getInventory());
        }
        if(ev.getItemInHand().getItemMeta().getPersistentDataContainer().has(BanBeaconKey, PersistentDataType.BOOLEAN)){
            ev.setCancelled(true);
            BanInv gui = new BanInv();
            player.openInventory(gui.getInventory());
        }
    }
    @EventHandler
    void onClick(InventoryClickEvent ev){
        Player player = (Player) ev.getWhoClicked();
        if(ev.getClickedInventory() == null) return;
        if(ev.getClickedInventory().getHolder() instanceof ReviveSelect){
            ev.setCancelled(true);
            if(ev.getCurrentItem() == null){
                player.closeInventory();
                return;
            }
            SkullMeta meta = (SkullMeta) ev.getCurrentItem().getItemMeta();
            OfflinePlayer skullOwner = meta.getOwningPlayer();
            saveData.SaveLives(skullOwner, 3);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), " execute at " + player.getName() + " run playsound block.beacon.activate master " + player.getName());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Player " + ChatColor.DARK_PURPLE + skullOwner.getName() + ChatColor.LIGHT_PURPLE + " has been revived");
            player.closeInventory();
            int newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
            player.getInventory().getItemInMainHand().setAmount(newAmount);
            
        }
        if(ev.getClickedInventory().getHolder() instanceof UnbanInv){
            ev.setCancelled(true);
            SkullMeta meta = (SkullMeta) ev.getCurrentItem().getItemMeta();
            OfflinePlayer skullOwner = meta.getOwningPlayer();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "essentials:unban " + skullOwner.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound block.beacon.activate master " + player.getName());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Player " + ChatColor.DARK_PURPLE + skullOwner.getName() + ChatColor.LIGHT_PURPLE + " has been unbanned");
            player.closeInventory();
            int newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
            player.getInventory().getItemInMainHand().setAmount(newAmount);
            
        }
        if(ev.getClickedInventory().getHolder() instanceof BanInv){
            ev.setCancelled(true);
            SkullMeta meta = (SkullMeta) ev.getCurrentItem().getItemMeta();
            OfflinePlayer skullOwner = meta.getOwningPlayer();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "essentials:ban " + skullOwner.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound entity.lightning_bolt.thunder master " + player.getName());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Player " + ChatColor.DARK_PURPLE + skullOwner.getName() + ChatColor.LIGHT_PURPLE + " has been banned");
            player.closeInventory();
            int newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
            player.getInventory().getItemInMainHand().setAmount(newAmount);
            
        }
    }
    public int setAmount(Player p, int amount){
      int x = 0;
      if(p.getGameMode().equals(GameMode.CREATIVE)){
         x = amount;
      }
      else{
         x = p.getInventory().getItemInMainHand().getAmount() - 1;
      }
      return x;
    }
    
}
