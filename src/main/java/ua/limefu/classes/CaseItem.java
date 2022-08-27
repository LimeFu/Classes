package ua.limefu.classes;

import ua.limefu.classes.Type.Ability;

import java.util.List;
import java.util.Random;

public class CaseItem {
    public Ability openCase() {
        float chance = 1f / AbilityList.getPlayerClass().keySet().size();

        Random random = new Random();
            for (Ability ability : AbilityList.getPlayerClass().keySet()) {
                if (random.nextFloat() < chance) {
                    return ability;
                }
            }
            return ((List<Ability>) AbilityList.getPlayerClass().keySet()).get(0);
    }
}
