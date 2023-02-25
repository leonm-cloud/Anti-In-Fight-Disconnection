package fr.enzias.antidecocombat.events;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArrowHitEvent implements Listener {

    private final AntiDecoCombat plugin;
    public ArrowHitEvent(AntiDecoCombat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onArrowHit(EntityDamageByEntityEvent event){
        if(event.isCancelled()){
            return;
        }
        //Assignation des joueurs (shooter et receveur)
        if(event.getDamager() instanceof Arrow && ((Arrow) event.getDamager()).getShooter() instanceof Player && event.getEntity() instanceof Player &&
                ((Player) ((Arrow) event.getDamager()).getShooter()).getUniqueId() != event.getEntity().getUniqueId()){

            Player shooter = (Player) ((Arrow) event.getDamager()).getShooter();
            Player player = (Player) event.getEntity();

            //Assignation à la HashMap des 2 joueurs

            if(!plugin.getFighting().containsKey(shooter.getName())){
                plugin.getFighting().put(shooter.getName(), System.currentTimeMillis() + plugin.getTimer());
            }
            else{
                plugin.getFighting().replace(shooter.getName(), System.currentTimeMillis() + plugin.getTimer());
            }

            if(!plugin.getFighting().containsKey(player.getName())){
                plugin.getFighting().put(player.getName(), System.currentTimeMillis() + plugin.getTimer());
            }
            else{
                plugin.getFighting().replace(player.getName(), System.currentTimeMillis() + plugin.getTimer());
            }
        }

    }
}
