package fr.enzias.antidecocombat.events;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {

    private final AntiDecoCombat plugin;
    public CommandEvent(AntiDecoCombat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandWhileFighting(PlayerCommandPreprocessEvent event){

        Player player = event.getPlayer();

        //joueur dans l'arène
        if(plugin.getWorlds().contains(player.getWorld().getName())) {
            //joueur dans la HashMap
            if (plugin.getFighting().containsKey(player.getName())) {
                //joueur en combat
                if (plugin.getFighting().get(player.getName()) >= System.currentTimeMillis()) {
                    for(String command : plugin.getCommands()){
                        if(event.getMessage().toLowerCase().startsWith("/" + command)){
                            event.setCancelled(true);
                            player.sendMessage(plugin.getNoCommand());
                        }
                    }
                }
            }
        }

    }


}
