package ua.limefu.classes.Type;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import ua.limefu.classes.AbilityList;
import ua.limefu.classes.Util;

public class EnderWizard implements Listener, Ability{

    public static EnderWizard getInstance(){
        return new EnderWizard();
    }

    public String name = "EnderWizard";
    private ItemStack pearl = Util.createNamedItem(Material.ENDER_PEARL, "Pearl Wizard");

    @Override
    public void giveAbilities(Player player) {
        if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
            player.getInventory().addItem(pearl);
        }
    }



    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (AbilityList.getPlayerAbility(e.getEntity()).equals(getInstance())) {
            giveAbilities(e.getEntity());
        }
    }

    @EventHandler
    public void onBros(PlayerDropItemEvent e) {
        if (AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            if (e.getItemDrop().equals(pearl)) {
                e.setCancelled(true);
            }
        }
    }


    @Override
    public String getName() {
        return name;
    }

}
