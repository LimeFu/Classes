package ua.limefu.classes.Type;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ua.limefu.classes.AbilityList;
import ua.limefu.classes.Util;

import java.util.ArrayList;
import java.util.List;

public class Archer implements Ability, Listener {
    public static Archer getInstance() { return new Archer(); }

    private static List<Player> playerOnCooldown = new ArrayList<>();

    private static ItemStack bow = Util.createEnchantItem(Material.BOW , Enchantment.ARROW_DAMAGE, 7);

    @Override
    public void giveAbilities(Player player) {
        if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
            player.getInventory().addItem(bow);
        }
    }

    public void stoper(Player player, int radius, double duration, boolean cooldownNeddeed) {
        if (!playerOnCooldown.contains(player)) {
            List<Entity> entityList = (List<Entity>) player.getLocation().getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius);

            for (Entity entity : entityList) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) (duration * 20), 10));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (int) (duration * 20), 10));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (duration * 20), 10));
                }
            }
            if (cooldownNeddeed) {
                startCooldown(player, 10);
            }
        }
    }

    public void startCooldown (Player player, double duration) {
        playerOnCooldown.add(player);
        new Thread(() -> {
            double ctr = duration;
            while (ctr > 0) {
                ctr--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            playerOnCooldown.remove(player);
        }).start();
    }

    @EventHandler
    public void onStretch(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (AbilityList.getPlayerAbility(player).equals(getInstance())) {
            if (player.getInventory().getItemInMainHand() == bow) {
                stoper(player, 10, 0.05 , false);

            }
        }
    }

    @Override
    public String getName() {
        return "Archer";
    }
}
