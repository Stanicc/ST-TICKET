package stanic.stticket.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import stanic.stticket.Main;
import stanic.stticket.factory.model.Ticket;

import java.util.ArrayList;

public class Menus {

    public static Integer pag = 1;

    public static void openTicketsMenu(Player player) {
        FileConfiguration sett = Main.instance.getConfig();
        String ticketsMenu = "Menus.ticketsMenu.";

        Inventory inv = Bukkit.createInventory(null, 54, sett.getString(ticketsMenu + "name").replace("&", "§"));

        ArrayList<Ticket> tickets = new ArrayList<>(new ArrayList<>(Main.instance.ticket.values()));

        int slot = 11;
        int size = tickets.size();

        for (int i = 0; i != 15; i++) {
            if (i + (15 * (pag - 1)) < size) {
                ArrayList<String> lore = new ArrayList<>();
                for (String line : sett.getStringList(ticketsMenu + "items.ticketItem.lore"))
                    lore.add(line.replace("&", "§")
                            .replace("{player}", tickets.get(i + (15 * (pag - 1))).getPlayer())
                            .replace("{date}", tickets.get(i + (15 * (pag - 1))).getDate())
                            .replace("{hour}", tickets.get(i + (15 * (pag - 1))).getHour())
                            .replace("{id}", tickets.get(i + (15 * (pag - 1))).getId().toString())
                            .replace("{message}", tickets.get(i + (15 * (pag - 1))).getMessage())
                    );

                ItemStack item = new ItemBuilder(Material.SKULL_ITEM).setDurability(3).setSkullOwner(tickets.get(i + (15 * (pag - 1))).getPlayer()).setName(sett.getString(ticketsMenu + "items.ticketItem.name").replace("&", "§")
                        .replace("{player}", tickets.get(i + (15 * (pag - 1))).getPlayer())
                        .replace("{date}", tickets.get(i + (15 * (pag - 1))).getDate())
                        .replace("{hour}", tickets.get(i + (15 * (pag - 1))).getHour())
                        .replace("{id}", tickets.get(i + (15 * (pag - 1))).getId().toString())
                        .replace("{message}", tickets.get(i + (15 * (pag - 1))).getMessage())).setLore(lore).build();

                inv.setItem(slot, item);

                if (++slot == 16) slot = 20;
                if (slot == 25) slot = 29;
                if (slot == 34) break;
            }
        }

        ArrayList<String> loreNext = new ArrayList<>();
        for (String line : sett.getStringList(ticketsMenu + "items.nextPage.lore"))
            loreNext.add(line.replace("&", "§"));

        ItemStack itemNext = new ItemBuilder(Material.getMaterial(sett.getInt(ticketsMenu + "items.nextPage.id"))).setDurability(sett.getInt(ticketsMenu + "items.nextPage.data")).setName(sett.getString(ticketsMenu + "items.nextPage.name").replace("&", "§")).setLore(loreNext).build();
        inv.setItem(sett.getInt(ticketsMenu + "items.nextPage.slot"), itemNext);

        if (pag >= 2) {
            ArrayList<String> lorePrevious = new ArrayList<>();
            for (String line : sett.getStringList(ticketsMenu + "items.previousPage.lore"))
                lorePrevious.add(line.replace("&", "§"));

            ItemStack itemPrevious = new ItemBuilder(Material.getMaterial(sett.getInt(ticketsMenu + "items.previousPage.id"))).setDurability(sett.getInt(ticketsMenu + "items.previousPage.data")).setName(sett.getString(ticketsMenu + "items.previousPage.name").replace("&", "§")).setLore(loreNext).build();
            inv.setItem(sett.getInt(ticketsMenu + "items.previousPage.slot"), itemPrevious);
        }

        player.openInventory(inv);
    }

}