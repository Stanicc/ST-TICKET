package stanic.stticket.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import stanic.stticket.utils.Menus;
import stanic.stticket.utils.Messages;

public class TicketsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Messages messages = new Messages();
        if (!(sender instanceof Player)) sender.sendMessage(messages.get("onlyInGame"));
        else {
            if (!sender.hasPermission("")) {
                sender.sendMessage(messages.get("notHasPermission"));
                return true;
            }
            Menus.openTicketsMenu((Player) sender);
        }
        return false;
    }
}
