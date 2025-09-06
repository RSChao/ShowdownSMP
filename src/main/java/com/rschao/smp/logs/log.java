package com.rschao.smp.logs;

import java.io.IOException;

import com.rschao.smp.Plugin;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

public class log {

    public log(String message, String name){
        logToFile(message, name);
    }
    public static void logToFile(String message, String name)
    {
    
        try
        {
            File dataFolder = new File(Plugin.getPlugin(Plugin.class).getDataFolder(), "logs");
            if(!dataFolder.exists())
            {
                dataFolder.mkdirs();
            }
 
            File saveTo = new File(dataFolder, name);
            if (!saveTo.exists())
            {
                saveTo.createNewFile();
            }
 
 
            FileWriter fw = new FileWriter(saveTo, true);
 
            PrintWriter pw = new PrintWriter(fw);
 
            pw.println(message);
 
            pw.flush();
 
            pw.close();
 
        } catch (IOException e)
        {
 
            e.printStackTrace();
 
        }
 
    }
    public static void logToFile(String message, String name, File folder)
    {
    
        try
        {
            File dataFolder = folder;
            if(!dataFolder.exists())
            {
                dataFolder.mkdirs();
            }
 
            File saveTo = new File(dataFolder, name);
            if (!saveTo.exists())
            {
                saveTo.createNewFile();
            }
 
 
            FileWriter fw = new FileWriter(saveTo, true);
 
            PrintWriter pw = new PrintWriter(fw);
 
            pw.println(message);
 
            pw.flush();
 
            pw.close();
 
        } catch (IOException e)
        {
 
            e.printStackTrace();
 
        }
 
    }
}
