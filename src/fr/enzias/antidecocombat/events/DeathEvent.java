package fr.enzias.antidecocombat.events;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    private final AntiDecoCombat plugin;

    public DeathEvent(AntiDecoCombat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();

        //joueur dans l'arène
        if (plugin.getWorlds().contains(player.getWorld().getName())) {
            //joueur dans la HashMap
            if (plugin.getFighting().containsKey(player.getName())) {
                //joueur en combat
                if (plugin.getFighting().get(player.getName()) >= System.currentTimeMillis()) {
                    plugin.getFighting().remove(player.getName());
                }
            }
        }
    }
}