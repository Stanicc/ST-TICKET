package stanic.stticket.bukkit.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import stanic.stticket.Main;
import stanic.stticket.factory.model.Ticket;
import stanic.stticket.utils.Menus;
import stanic.stticket.utils.Messages;

import java.util.HashMap;

public class TicketsInventoryListener implements Listener {

    private static HashMap<Integer, Ticket> tickets = new HashMap<>();

    private FileConfiguration sett = Main.instance.getConfig();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        int slot = event.getSlot();

        int previous = sett.getInt("Menus.ticketsMenu.items.previousPage.slot");
        int next = sett.getInt("Menus.ticketsMenu.items.nextPage.slot");

        if (inventory.getTitle().equals(sett.getString("Menus.ticketsMenu.name").replace("&", "§"))) {
            if (slot == previous) {
                player.closeInventory();
                Menus.pag -= 1;
                Menus.openTicketsMenu(player);
            } else if (slot == next) {
                player.closeInventory();
                Menus.pag += 1;
                Menus.openTicketsMenu(player);
            } else {
                Ticket ticket = tickets.get(slot);

                Main.instance.staff.put(player.getName(), ticket);
                player.closeInventory();

                player.sendMessage(new Messages().get("sayAnswer").replace("{id}", "" + ticket.getId()).replace("{player}", ticket.getPlayer()).replace("{message}", ticket.getMessage()));
            }
        }
    }

}