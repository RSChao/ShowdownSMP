package com.rschao.smp.tpcancel;

import com.rschao.smp.Plugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class TPCancelHandler {

    public static final String path = Plugin.getPlugin(Plugin.class).getDataFolder() + File.separator + "tpcancel.yml";

    public static FileConfiguration getFile() {
        return YamlConfiguration.loadConfiguration(new File(path));
    }

    public static void saveFile(FileConfiguration config) {
        try {
            config.save(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPlayer(String playerName) {
        FileConfiguration config = getFile();
        config.set(playerName, true);
        saveFile(config);
    }

    public static void removePlayer(String playerName) {
        FileConfiguration config = getFile();
        config.set(playerName, null);
        saveFile(config);
    }

    public static boolean isPlayerInList(String playerName) {
        FileConfiguration config = getFile();
        return config.contains(playerName);
    }
}
