package fr.enzias.antidecocombat.files;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageFile {

    private static AntiDecoCombat plugin;

    private static File messageFile;
    private static FileConfiguration messageConfig;

    //file's creation
    public static void setup(){

        messageFile = new File(Bukkit.getServer().getPluginManager().getPlugin("AntiDecoCombat").getDataFolder(), "message.yml");

        if(!messageFile.exists()) {
            try {
                messageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        messageConfig = YamlConfiguration.loadConfiguration(messageFile);
    }

    public static void getDefaults(){

        messageConfig.addDefault("messages.cannot-use-commands", "&cVous ne pouvez pas utilisez de cette commande en combat !");
    }

    public static FileConfiguration get(){
        return messageConfig;
    }

    public static void save(){
        try{
            messageConfig.save(messageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
