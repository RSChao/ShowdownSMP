package com.rschao.smp.commands;

import com.rschao.smp.logs.EnderChestBackup;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.entity.Player;

import java.io.File;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BackupEnderChest {
    static EnderChestBackup ecb = new EnderChestBackup();
    public static CommandAPICommand LoadCommand(){
        CommandAPICommand life = new CommandAPICommand("ecvbackup")
                .withPermission("smp.admin")
                .executesPlayer((Player player, CommandArguments args) -> {
                    ecb.backupEnderChests();
                });

        return life;
    }
    /**
     * Obtiene una lista de Instants a partir del nombre de cada carpeta en el directorio de backups.
     * Se asume que cada nombre de carpeta es un string representando un instante (por ejemplo, en formato ISO-8601).
     */
    public static List<Instant> getBackupInstants(File backupsDir) {
        List<Instant> instants = new ArrayList<>();
        File[] folders = backupsDir.listFiles(File::isDirectory);
        if (folders != null) {
            for (File folder : folders) {
                try {
                    Instant instant = Instant.parse(folder.getName().replace("_", ":"));
                    instants.add(instant);
                } catch (DateTimeParseException e) {
                    // Ignorar carpetas cuyos nombres no sean fechas válidas
                }
            }
        }
        return instants;
    }

    public static CommandAPICommand LoadRestoreCommand(){
        CommandAPICommand life = new CommandAPICommand("ecvrestore")
                .withPermission("smp.admin")
                .executesPlayer((Player player, CommandArguments args) -> {
                    try {
                        //get all folders in backups
                        File backupFolder = new File(com.rschao.smp.Plugin.getPlugin(com.rschao.smp.Plugin.class).getDataFolder(), "backups/");

                        List<Instant> instants = getBackupInstants(backupFolder);
                        //get the latest instant
                        if(instants.size() == 0){
                            player.sendMessage("No backup found");
                            return;
                        }

                        Instant latest = instants.get(0);
                        for(Instant instant : instants){
                            if(instant.isAfter(latest)){
                                latest = instant;
                            }
                        }
                        File latestFolder = new File(backupFolder, latest.toString().replace(":", "_")+"/");
                        if(!latestFolder.exists()){
                            player.sendMessage("No backup found");
                            return;
                        }
                        ecb.restoreEnderChests(latestFolder);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        return life;
    }
}
