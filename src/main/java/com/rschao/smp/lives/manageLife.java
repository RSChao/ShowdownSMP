package com.rschao.smp.lives;

import com.rschao.smp.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.md_5.bungee.api.ChatColor;

public class manageLife implements Listener {
    @EventHandler
    void OnDeath(PlayerDeathEvent ev){
        if(Plugin.getPauseLives()) return;
        lifeAPI.SubtractLife(ev.getEntity());
        if(!ev.getEntity().getKiller().equals(null)){
            if(saveData.getLives(ev.getEntity().getKiller()) >= 10){
                ev.getEntity().getKiller().sendMessage(ChatColor.RED + "You reached the maximun amount of lives!");
                return;
            }
            lifeAPI.addLife(ev.getEntity().getKiller());
        }
    }
    @EventHandler
    void onRespawn(PlayerRespawnEvent ev){
        if(saveData.getLives(ev.getPlayer()) <= 0){
            ev.getPlayer().kickPlayer(ChatColor.RED + "You have been " + ChatColor.DARK_RED + "Death Banned. " + ChatColor.RED + "Wait for a friend to use a revive beacon on you.");
        }
    }
}
