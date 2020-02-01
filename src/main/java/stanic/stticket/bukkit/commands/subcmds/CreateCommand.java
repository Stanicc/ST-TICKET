package stanic.stticket.bukkit.commands.subcmds;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import stanic.stticket.Main;
import stanic.stticket.factory.model.Ticket;
import stanic.stticket.utils.Messages;
import stanic.stticket.utils.TimeUtils;

public class CreateCommand {

    private Messages messages = new Messages();

    public void run(Player player, String[] args) {
        if (args.length < 2) player.sendMessage(messages.get("usageCreate"));
        else {
            String message = StringUtils.join(args, " ", 2, args.length);
            Integer id = Main.instance.ticket.size() + 1;

            Ticket ticket = new Ticket(player.getName(), message, id, new TimeUtils().getDate(), new TimeUtils().getHour());
            Main.instance.ticket.put(id, ticket);

            player.sendMessage(messages.get("ticketCreated").replace("{message}", message).replace("{id}", "" + id));

            Bukkit.getOnlinePlayers().stream().filter(it -> it.hasPermission("stticket.admin")).forEach(staff -> {
                        if (!Main.instance.playerInfo.get(staff.getName()).getAvailable() && Main.instance.getConfig().getBoolean("Config.notifyNotAvailable"))
                            staff.sendMessage(messages.get("ticketWarn").replace("{message}", message).replace("{id}", "" + id).replace("{player}", player.getName()));
                        else if (Main.instance.playerInfo.get(staff.getName()).getAvailable())
                            staff.sendMessage(messages.get("ticketWarn").replace("{message}", message).replace("{id}", "" + id).replace("{player}", player.getName()));
                    }
            );
        }
    }

}