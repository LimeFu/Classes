package ua.limefu.classes.Type;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ua.limefu.classes.AbilityList;

public class SpeedMan implements Listener, Ability {

    public static SpeedMan getInstance() {
        return new SpeedMan();
    }

    public String name = "SpeedMan";

    @Override
    public void giveAbilities(Player player) {
        if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 5));
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            giveAbilities(e.getPlayer());
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if(AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            giveAbilities(e.getPlayer());
        }
    }

    @Override
    public String getName() {
        return name;
    }

}
