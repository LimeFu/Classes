package ua.limefu.classes;

import org.bukkit.entity.Player;
import ua.limefu.classes.Type.Ability;

import java.util.*;

public class AbilityList {

    private static Map<Ability, List<UUID>> playerClass = new HashMap<>();

    public static Ability getPlayerAbility(Player player) {
        for (Ability ability: playerClass.keySet()) {
            for (UUID playerID: playerClass.get(ability)) {
                if (playerID == player.getUniqueId()) {
                    return ability;
                }
            }

        }
        return null;
    }

    public static void add(Player player, Ability ability) {

        List<UUID> players;

        if (playerClass.containsKey(ability)) {
            players = playerClass.get(ability);
        } else {
            players = new ArrayList<>();
        }
        players.add(player.getUniqueId());
        playerClass.put(ability, players);
        ability.giveAbilities(player);

    }

}
