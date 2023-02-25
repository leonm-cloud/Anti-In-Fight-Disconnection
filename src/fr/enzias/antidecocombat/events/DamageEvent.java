package fr.enzias.antidecocombat.events;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {

    private final AntiDecoCombat plugin;
    public DamageEvent(AntiDecoCombat plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.isCancelled()){
            return;
        }
        //Assignation des joueurs (attaquant et attaqué)
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player && event.getDamager().getUniqueId() != event.getEntity().getUniqueId()){
            Player damager = (Player) event.getDamager();
            Player player1 = (Player) event.getEntity();

            //Assignation à la HashMap des 2 joueurs

            if(!plugin.getFighting().containsKey(damager.getName())){
                plugin.getFighting().put(damager.getName(), System.currentTimeMillis() + plugin.getTimer());
            }
            else{
                plugin.getFighting().replace(damager.getName(), System.currentTimeMillis() + plugin.getTimer());
            }

            if(!plugin.getFighting().containsKey(player1.getName())){
                plugin.getFighting().put(player1.getName(), System.currentTimeMillis() + plugin.getTimer());
            }
            else{
                plugin.getFighting().replace(player1.getName(), System.currentTimeMillis() + plugin.getTimer());
            }
        }
    }
}
