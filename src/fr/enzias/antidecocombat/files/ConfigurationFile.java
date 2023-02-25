package fr.enzias.antidecocombat.files;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConfigurationFile {

    private static AntiDecoCombat plugin;

    private static File configurationFile;
    private static FileConfiguration configurationConfig;



    //file's creation
    public static void setup(){

        configurationFile = new File(Bukkit.getServer().getPluginManager().getPlugin("AntiDecoCombat").getDataFolder(), "configuration.yml");

        if(!configurationFile.exists()){
            try{
                configurationFile.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        configurationConfig = YamlConfiguration.loadConfiguration(configurationFile);

    }

    public static void getDefaults(){

        configurationConfig.addDefault("timer", 15);
        configurationConfig.addDefault("commands", "" );
        configurationConfig.addDefault("world-name","");
        List<String> list =Arrays.asList("spawn","is go");
        List<String> list1 =Arrays.asList("world");
        configurationConfig.set("commands", list);
        configurationConfig.set("world-name", list1);
    }

    public static FileConfiguration get(){
        return configurationConfig;
    }
    public static void save(){
        try{
            configurationConfig.save(configurationFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
