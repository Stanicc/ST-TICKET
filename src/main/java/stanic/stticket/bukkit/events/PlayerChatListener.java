package stanic.stticket.bukkit.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import stanic.stticket.Main;
import stanic.stticket.factory.TicketFactory;
import stanic.stticket.factory.model.Ticket;
import stanic.stticket.utils.Messages;

import java.sql.SQLException;

public class PlayerChatListener implements Listener {

    private Messages messages = new Messages();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (Main.instance.staff.containsKey(player.getName())) {
            event.setCancelled(true);

            Ticket ticket = Main.instance.staff.get(player.getName());

            player.sendMessage(messages.get("ticketAnswerStaff").replace("{id}", "" + ticket.getId()).replace("{player}", ticket.getPlayer()).replace("{message}", ticket.getMessage()).replace("{answer}", message));

            Player target = Bukkit.getPlayer(ticket.getPlayer());
            if (target != null)
                target.sendMessage(messages.get("ticketAnswerPlayer").replace("{id}", "" + ticket.getId()).replace("{player}", ticket.getPlayer()).replace("{message}", ticket.getMessage()).replace("{answer}", message).replace("{staff}", player.getName()));
            else
                Main.instance.notify.put(ticket.getPlayer(), messages.get("ticketAnswerPlayer").replace("{id}", "" + ticket.getId()).replace("{player}", ticket.getPlayer()).replace("{message}", ticket.getMessage()).replace("{answer}", message).replace("{staff}", player.getName()));

            try {
                new TicketFactory().delete(ticket);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Main.instance.ticket.remove(ticket.getId());
        }
    }

}