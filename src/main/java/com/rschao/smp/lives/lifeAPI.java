package com.rschao.smp.lives;

import org.bukkit.entity.Player;


import net.md_5.bungee.api.ChatColor;

public class lifeAPI {
    public static void SubtractLife(Player p){
        int lives = saveData.getLives(p) - 1;
        saveData.SaveLives(p, lives);
    }
    public static void addLife(Player p){
        int lives = saveData.getLives(p) + 1;
        saveData.SaveLives(p, lives);
        p.sendMessage(ChatColor.DARK_GREEN + "You have obtained a life, out of your total of " + ChatColor.YELLOW + String.valueOf(lives));
    }
}
