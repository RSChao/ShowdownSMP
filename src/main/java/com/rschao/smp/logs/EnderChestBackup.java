package com.rschao.smp.logs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;

public class EnderChestBackup {
    //java.time.Instant lastBackup = java.time.Instant.now();
    public void backupEnderChests(){
        Plugin ecv = Bukkit.getPluginManager().getPlugin("AxVaults");
        if(ecv == null){
            throw new IllegalPluginAccessException("AxVault not found");
        }
        File file = ecv.getDataFolder();
        if(!file.exists()){
            return;
        }
        File backupFolder = new File(com.rschao.smp.Plugin.getPlugin(com.rschao.smp.Plugin.class).getDataFolder(), "backups/" + java.time.Instant.now().toString().replace(":", "_")+"/");
        if(!backupFolder.exists()){
            backupFolder.mkdirs();
        }
        //copy all files from file to backupFolder
        try {
            for(File f : file.listFiles()){
                java.nio.file.Files.copy(f.toPath(), new File(backupFolder, f.getName()).toPath());
            }
            System.out.println("Backed up " + file.listFiles().length + " enderchests to " + backupFolder.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public void restoreEnderChests(File backupFolder) throws FileNotFoundException {
        Plugin ecv = Bukkit.getPluginManager().getPlugin("AxVaults");
        if (ecv == null) {
            throw new IllegalPluginAccessException("EnderChestVault is not found");
        }
        File file = ecv.getDataFolder();
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!backupFolder.exists() || !backupFolder.isDirectory()) {
            throw new FileNotFoundException("Backup folder not found");
        }
        try {
            for (File f : backupFolder.listFiles()) {
                java.nio.file.Files.copy(f.toPath(), new File(file, f.getName()).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("Restored " + backupFolder.listFiles().length + " enderchests from " + backupFolder.getAbsolutePath());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
