package com.rschao.smp.lives;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.rschao.smp.Plugin;

public class saveData {
    static Plugin plugin;
    static File dataFile;
    static FileConfiguration config;
    public saveData(Plugin pl){
        plugin = Plugin.getPlugin(Plugin.class);
        dataFile = new File(plugin.getDataFolder(), "lives.yml");
        config = new YamlConfiguration();
        try{
            config.load(dataFile);
        } catch(IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    
    }
    public static void SaveLives(Player p, int l){
        config.set("players." + p.getName(), l);
        try{
            config.save(dataFile);
        } catch(IOException e){
            e.printStackTrace();
        }
        reload();
    }
    public static void SaveLives(OfflinePlayer p, int l){
        config.set("players." + p.getName(), l);
        try{
            config.save(dataFile);
        } catch(IOException e){
            e.printStackTrace();
        }
        reload();
    }
    public static int getLives(Player p){
        int l = config.getInt("players." + p.getName());
        return l;
        
    }
    public static int getLives(OfflinePlayer p){
        int l = config.getInt("players." + p.getName());
        return l;
        
    }
    public static boolean CheckExisting(Player p){
        return config.contains("players."+p.getName(), true);
    }
    public static void reload(){
        try{
            config.load(dataFile);
        } catch(IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }
    public static String[] GetAllPlayers() {
        List<String> playerNames = new ArrayList<>();
        for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
            String name = p.getName();
            if(config.contains("players." + name)){
                playerNames.add(name);
            }
        }
        return playerNames.toArray(new String[0]);
    }
}
