package com.rschao.smp.events;

import com.rschao.smp.items.WorldEditItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WorldEditEvents implements Listener {
    @EventHandler
    void onUse(PlayerInteractEvent ev){
        ItemStack item = ev.getItem();
        Player player = ev.getPlayer();
        if(item == null) return;
        if(!item.hasItemMeta()) return;
        if(!item.getItemMeta().getPersistentDataContainer().isEmpty()) return;
        if(item.isSimilar(WorldEditItems.setter)){
            ItemStack topLeft = ev.getPlayer().getInventory().getItem(9);
            if(topLeft == null || topLeft.getType() == org.bukkit.Material.AIR) return;
            ev.getPlayer().sendMessage("§aSet position 1 to " + topLeft.getType());
            Bukkit.dispatchCommand(player, "/set " + topLeft.getType().toString().toLowerCase());
        }
        if(item.isSimilar(WorldEditItems.replacer)){
            ItemStack topLeft = ev.getPlayer().getInventory().getItem(9);
            ItemStack blockToReplace = ev.getPlayer().getInventory().getItem(10);
            if(topLeft == null || topLeft.getType() == org.bukkit.Material.AIR) return;
            ev.getPlayer().sendMessage("§aSet replacer material to " + topLeft.getType());
            Bukkit.dispatchCommand(player, "/replace " + blockToReplace.getType().toString().toLowerCase() + " " + topLeft.getType().toString().toLowerCase());
        }
        if(item.isSimilar(WorldEditItems.copier)){
            ev.getPlayer().sendMessage("§aCopied selection");
            Bukkit.dispatchCommand(player, "/copy");
        }
        if(item.isSimilar(WorldEditItems.paster)){
            ev.getPlayer().sendMessage("§aPasted selection");
            Bukkit.dispatchCommand(player, "/paste");
        }
        if(item.isSimilar(WorldEditItems.undoer)){
            ev.getPlayer().sendMessage("§aUndid last action");
            Bukkit.dispatchCommand(player, "/undo");
        }
        if(item.isSimilar(WorldEditItems.spherer)){
            ItemStack topLeft = ev.getPlayer().getInventory().getItem(9);
            if(topLeft == null || topLeft.getType() == org.bukkit.Material.AIR) return;
            int radius = topLeft.getAmount();
            ev.getPlayer().sendMessage("§aSet sphere material to " + topLeft.getType());
            Bukkit.dispatchCommand(player, "/sphere " + topLeft.getType().toString().toLowerCase() + " " + radius);
        }
        if(item.isSimilar(WorldEditItems.cylinderer)){
            ItemStack topLeft = ev.getPlayer().getInventory().getItem(9);
            if(topLeft == null || topLeft.getType() == org.bukkit.Material.AIR) return;
            int radius = topLeft.getAmount();
            ev.getPlayer().sendMessage("§aSet cylinder material to " + topLeft.getType());
            Bukkit.dispatchCommand(player, "/cyl " + topLeft.getType().toString().toLowerCase() + " " + radius);
        }
    }
}
