package fr.enzias.antidecocombat.events;

import fr.enzias.antidecocombat.AntiDecoCombat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SplashPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class PotionHitEvent implements Listener {

    private final AntiDecoCombat plugin;
    public PotionHitEvent(AntiDecoCombat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPotionHit(PotionSplashEvent event){
        if(event.isCancelled()){
            return;
        }
        if(!(((SplashPotion) event.getEntity()).getShooter() instanceof Player)){
            return;
        }
        Player shooter = (Player) ((SplashPotion) event.getEntity()).getShooter();

        for(PotionEffect effect : event.getPotion().getEffects()) {
            if (Arrays.asList(PotionEffectType.LEVITATION, PotionEffectType.HARM, PotionEffectType.SLOW, PotionEffectType.WEAKNESS, PotionEffectType.POISON)
                    .contains(effect.getType())){

                if (event.getAffectedEntities() != null) {

                    for (Entity entity : event.getAffectedEntities()) {
                        if (entity instanceof Player && shooter.getUniqueId() != entity.getUniqueId()) {

                            Player player = (Player) entity;

                            if(!plugin.getFighting().containsKey(shooter.getName())){
                                plugin.getFighting().put(shooter.getName(), System.currentTimeMillis() + plugin.getTimer());
                            }
                            else{
                                plugin.getFighting().replace(shooter.getName(), System.currentTimeMillis() + plugin.getTimer());
                            }

                            if(!plugin.getFighting().containsKey(player.getUniqueId())){
                                plugin.getFighting().put(player.getName(), System.currentTimeMillis() + plugin.getTimer());
                            }
                            else{
                                plugin.getFighting().replace(player.getName(), System.currentTimeMillis() + plugin.getTimer());
                            }

                        }
                    }

                }
            }
        }

    }

}
