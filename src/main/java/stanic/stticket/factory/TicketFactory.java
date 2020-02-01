package stanic.stticket.factory;

import stanic.stticket.Main;
import stanic.stticket.database.MySQL;
import stanic.stticket.factory.model.PlayerInfo;
import stanic.stticket.factory.model.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketFactory {

    public void saveStaff() throws SQLException {
        MySQL db = Main.sql;
        db.open();
        for (PlayerInfo playerInfo : new ArrayList<>(Main.instance.playerInfo.values())) {
            ResultSet rs = db.statement.executeQuery("SELECT * FROM ticket WHERE player='" + playerInfo.getPlayer() + "'");
            if (rs.next())
                db.statement.executeUpdate(String.format("UPDATE ticket SET player='%s', amount='%d'", playerInfo.getPlayer(), playerInfo.getAmount()));
            else
                db.statement.execute(String.format("INSERT INTO ticket (player, amount) VALUES ('%s', '%d')", playerInfo.getPlayer(), playerInfo.getAmount()));
        }

        db.close();
    }

    public void saveTicket() throws SQLException {
        MySQL db = Main.sql;
        db.open();
        for (Ticket ticket : new ArrayList<>(Main.instance.ticket.values())) {
            ResultSet rs = db.statement.executeQuery("SELECT * FROM tickets WHERE player='" + ticket.getPlayer() + "' AND id='" + ticket.getId() + "'");
            if (rs.next())
                db.statement.executeUpdate(String.format("UPDATE tickets SET player='%s', message='%s', id='%s', date='%s', hour='%s'", ticket.getPlayer(), ticket.getMessage(), ticket.getId(), ticket.getDate(), ticket.getHour()));
            else
                db.statement.execute(String.format("INSERT INTO tickets (player, message, id, date, hour) VALUES ('%s', '%s', '%s', '%s', '%s')", ticket.getPlayer(), ticket.getMessage(), ticket.getId(), ticket.getDate(), ticket.getHour()));
        }
        db.close();
    }

    public void delete(Ticket ticket) throws SQLException {
        MySQL db = Main.sql;
        db.open();

        ResultSet rs = db.statement.executeQuery("SELECT * FROM tickets WHERE player='" + ticket.getPlayer() + "' AND id='" + ticket.getId() + "'");
        if (rs.next())
            db.statement.execute("DELETE FROM tickets WHERE player='" + ticket.getPlayer() + "' AND id='" + ticket.getId() + "'");

        db.close();
    }

}