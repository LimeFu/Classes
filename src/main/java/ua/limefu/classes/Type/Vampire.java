package ua.limefu.classes.Type;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import ua.limefu.classes.AbilityList;
import ua.limefu.classes.Util;

public class Vampire implements Listener, Ability {

    public static Vampire getInstance() {
        return new Vampire();
    }


    public String name = "Vampire";
    private ItemStack sword = Util.createNamedItem(Material.DIAMOND_SWORD, "Vampire Sword");

    @Override
    public void giveAbilities(Player player) {

        if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
            player.getInventory().addItem(sword);
        }
    }


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
                if (player.getInventory().getItemInMainHand().equals(sword)) {
                    if (player.getHealth() + e.getDamage() >= 20) {

                        player.setHealth(20);
                    } else {
                        player.setHealth(player.getHealth() + e.getDamage());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        if(AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            if (e.getPlayer().getInventory().contains(sword)) {
                e.setJoinMessage("Vampire attack!");
            } else {
                e.getPlayer().getInventory().addItem(sword);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        if(AbilityList.getPlayerAbility(e.getEntity()).equals(getInstance())) {
            e.getEntity().getInventory().removeItem(sword);
        }

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if (AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            e.getPlayer().getInventory().addItem(sword);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
