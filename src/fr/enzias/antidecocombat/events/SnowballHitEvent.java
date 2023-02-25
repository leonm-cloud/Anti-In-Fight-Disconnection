package fr.enzias.antidecocombat.events;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SnowballHitEvent implements Listener {

    private final AntiDecoCombat plugin;
    public SnowballHitEvent(AntiDecoCombat plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent event){
        if(event.isCancelled()){
            return;
        }
        //Assignation des joueurs (shooter et receveur)
        if(event.getDamager() instanceof Snowball && ((Snowball) event.getDamager()).getShooter() instanceof Player && event.getEntity() instanceof Player &&
                ((Player) ((Snowball) event.getDamager()).getShooter()).getUniqueId() != event.getEntity().getUniqueId()){

            Player shooter = (Player) ((Snowball) event.getDamager()).getShooter();
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
