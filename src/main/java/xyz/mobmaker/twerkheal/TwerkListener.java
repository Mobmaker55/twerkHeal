package xyz.mobmaker.twerkheal;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Collection;

public class TwerkListener implements Listener {

    @EventHandler
    public void onPlayerCrouch(PlayerToggleSneakEvent event) {
        Player croucher = event.getPlayer();
        World world = croucher.getWorld();
        Collection<Player> nearbyPlayers = world.getNearbyPlayers(croucher.getLocation(), 1);
        for (Player player : nearbyPlayers) {
            if (player.equals(croucher)) {
                continue;
            }
            double targetHealth = player.getHealth();
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            if (((maxHealth == 0.0d) ? 20.0d : maxHealth) <= targetHealth + .5) {
                continue;
            }
            Location location = player.getLocation();
            world.spawnParticle(Particle.HEART, location.add(0,.5,0), 3);
            float saturation = croucher.getSaturation();
            int foodLevel = croucher.getFoodLevel();
            if (saturation > 0) {
                croucher.setSaturation(saturation - .25f);
            } else if (foodLevel > 0){
                croucher.setFoodLevel(croucher.getFoodLevel() - 1);
            } else {
                return;
            }
            player.setHealth(targetHealth + .5);
        }
    }

}
