package stanic.stticket;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import stanic.stticket.bukkit.commands.TicketCommand;
import stanic.stticket.bukkit.commands.TicketsCommand;
import stanic.stticket.bukkit.events.PlayerChatListener;
import stanic.stticket.bukkit.events.PlayerJoinListener;
import stanic.stticket.bukkit.events.TicketsInventoryListener;
import stanic.stticket.database.Database;
import stanic.stticket.database.MySQL;
import stanic.stticket.factory.TicketFactory;
import stanic.stticket.factory.model.PlayerInfo;
import stanic.stticket.factory.model.Ticket;

import java.sql.SQLException;
import java.util.HashMap;

public class Main extends JavaPlugin {

    public static Main instance;
    public static MySQL sql;

    public HashMap<Integer, Ticket> ticket = new HashMap<>();
    public HashMap<String, PlayerInfo> playerInfo = new HashMap<>();
    public HashMap<String, Ticket> staff = new HashMap<>();
    public HashMap<String, String> notify = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        saveResource("settings.yml", false);

        sql = new MySQL(getConfig());
        try {
            new Database().start();
            new Database().loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadBukkit();

        Bukkit.getConsoleSender().sendMessage("§e[ST-TICKET] §fAtivado!");
    }

    @Override
    public void onDisable() {
        try {
            new TicketFactory().saveStaff();
            new TicketFactory().saveTicket();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBukkit() {
        getCommand("ticket").setExecutor(new TicketCommand());
        getCommand("tickets").setExecutor(new TicketsCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new TicketsInventoryListener(), this);
    }

}