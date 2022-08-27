package ua.limefu.classes.Type;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ua.limefu.classes.AbilityList;

import java.util.Locale;

public class Jumper implements Listener, Ability {

    public String name = "Jumper";

    public static Jumper getInstance() {
        return new Jumper();
    }

    @Override
    public void giveAbilities(Player player) {
        if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 3));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            giveAbilities(e.getPlayer());
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (AbilityList.getPlayerAbility(event.getPlayer()).equals(getInstance())) {
            giveAbilities(event.getPlayer());
        }
    }

    @EventHandler
    public void onJump(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if(AbilityList.getPlayerAbility(player).equals(getInstance())) {
                if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    e.setCancelled(true);
                    Location loc = player.getLocation();
                    loc.setY(loc.getY() - 1);
                    player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getLocation(), 10, loc.getBlock());
                }
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
