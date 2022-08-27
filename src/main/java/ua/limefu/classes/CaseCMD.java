package ua.limefu.classes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.limefu.classes.Type.Ability;

public class CaseCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {

            sender.sendMessage("Only player");
            return true;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("case")) {
            Ability ability = new CaseItem().openCase();
            AbilityList.add(player, ability);

            player.sendMessage("Вы получили способность" + ability.getName());
            return true;
        }
        return false;
    }
}
