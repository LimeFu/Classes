package ua.limefu.classes.Type;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import ua.limefu.classes.AbilityList;
import ua.limefu.classes.Util;

public class Snake implements Listener, Ability {

    public static Snake getInstance(){
        return new Snake();
    }

    public String name = "Snake";
    private ItemStack totem = Util.createNamedItem(Material.TOTEM, "2 Head");
    @Override
    public void giveAbilities(Player player) {
        if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
            player.getInventory().setItemInOffHand(totem);
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e)  {
        if (AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
            if(e.getSlot() == 40) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if (AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            giveAbilities(e.getPlayer());
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (AbilityList.getPlayerAbility(e.getPlayer()).equals(getInstance())) {
            if (e.getItemDrop().equals(totem)) {
                e.setCancelled(true);
            }
        }

    }

    @Override
    public String getName() {
        return name;
    }
}
