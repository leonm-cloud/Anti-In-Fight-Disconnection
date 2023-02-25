package fr.enzias.antidecocombat.events;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class QuitEvent implements Listener {

    private final AntiDecoCombat plugin;
    public QuitEvent (AntiDecoCombat plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){

        Player player = event.getPlayer();
        //joueur dans les mondes "arènes"
        if(plugin.getWorlds().contains(player.getWorld().getName())) {
            //joueur dans la HashMap ?
            if (plugin.getFighting().containsKey(player.getName())) {
                //timer écoulé ?
                if (plugin.getFighting().get(player.getName()) >= System.currentTimeMillis()) {
                    for (ItemStack itemStack : player.getInventory().getContents()) {
                        if (itemStack != null) {
                            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                        }
                    }
                    player.getInventory().clear();
                }
            }
        }

    }

}
