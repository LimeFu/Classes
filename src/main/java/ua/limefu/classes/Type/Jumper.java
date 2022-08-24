package ua.limefu.classes.Type;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ua.limefu.classes.AbilityList;

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

    @Override
    public String getName() {
        return name;
    }
}
