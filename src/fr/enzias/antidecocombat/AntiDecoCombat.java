package fr.enzias.antidecocombat;

import fr.enzias.antidecocombat.events.*;
import fr.enzias.antidecocombat.files.ConfigurationFile;
import fr.enzias.antidecocombat.files.MessageFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class AntiDecoCombat extends JavaPlugin{

    private AntiDecoCombat instance;
    Map<String, Long> fighting = new HashMap<String, Long>();

    @Override
    public void onEnable() {

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        MessageFile.setup();
        MessageFile.getDefaults();
        MessageFile.get().options().copyDefaults(true);
        MessageFile.save();

        ConfigurationFile.setup();
        ConfigurationFile.getDefaults();
        ConfigurationFile.get().options().copyDefaults(true);
        ConfigurationFile.save();
        registerEvents();

        this.getServer().getLogger().info("[AntiDecoCombat] Successfully enabled.");

    }

    @Override
    public void onDisable() {

        MessageFile.save();
        ConfigurationFile.save();

        this.getServer().getLogger().info("[AntiDecoCombat] Successfully disabled.");
    }

    public AntiDecoCombat getInstance(){
        return instance;
    }

    public Map<String, Long> getFighting(){
        return fighting;
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new DamageEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new ArrowHitEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new SnowballHitEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new PotionHitEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new QuitEvent(this),this);
        this.getServer().getPluginManager().registerEvents(new CommandEvent(this),this);
        this.getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
    }

    public Integer getTimer(){
        return (ConfigurationFile.get().getInt("timer"))*1000;
    }

    public List<String> getCommands(){
        return ConfigurationFile.get().getStringList("commands");
    }

    public List<String> getWorlds(){
        return ConfigurationFile.get().getStringList("world-name");
    }

    public String getNoCommand(){
        return MessageFile.get().getString("messages.cannot-use-commands").replace("&","§");
    }

}
