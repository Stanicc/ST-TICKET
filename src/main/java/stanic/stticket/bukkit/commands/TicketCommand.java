package stanic.stticket.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import stanic.stticket.bukkit.commands.subcmds.AvailableCommand;
import stanic.stticket.bukkit.commands.subcmds.CreateCommand;
import stanic.stticket.utils.Messages;

public class TicketCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Messages messages = new Messages();

        if (!(sender instanceof Player)) sender.sendMessage(messages.get("onlyInGame"));
        else {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(messages.get("helpTicket"));
                return true;
            }

            switch (args[0]) {
                case "criar":
                case "create": new CreateCommand().run(player, args);
                case "on":
                case "available":
                case "disponivel": new AvailableCommand().run(player);
            }
        }
        return false;
    }
}
