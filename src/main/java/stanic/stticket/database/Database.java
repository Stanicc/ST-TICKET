package stanic.stticket.database;

import stanic.stticket.Main;
import stanic.stticket.factory.model.PlayerInfo;
import stanic.stticket.factory.model.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    public void start() throws SQLException {
        MySQL db = Main.sql;

        db.open();
        db.statement.execute("CREATE TABLE IF NOT EXISTS ticket (player TEXT, amount INT)");
        db.statement.execute("CREATE TABLE IF NOT EXISTS tickets (player TEXT, message TEXT, id INT, date TEXT, hour TEXT)");
        db.close();
    }

    public void loadData() throws SQLException {
        MySQL db = Main.sql;
        db.open();

        ResultSet rs = db.statement.executeQuery("SELECT * FROM ticket");
        while (rs.next()) {
            String player = rs.getString("player");
            Integer amount = rs.getInt("amount");

            PlayerInfo playerInfo = new PlayerInfo(player, amount, true);
            Main.instance.playerInfo.put(player, playerInfo);
        }

        ResultSet rsTickets = db.statement.executeQuery("SELECT * FROM tickets");
        while (rsTickets.next()) {
            String player = rsTickets.getString("player");
            String message = rsTickets.getString("message");
            Integer id = rsTickets.getInt("id");
            String date = rsTickets.getString("date");
            String hour = rsTickets.getString("hour");

            Ticket ticket = new Ticket(player, message, id, date, hour);
            Main.instance.ticket.put(id, ticket);
        }

        db.close();
    }

}
