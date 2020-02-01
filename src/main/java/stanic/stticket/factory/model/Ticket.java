package stanic.stticket.factory.model;

public class Ticket {

    private String player;
    private String message;
    private Integer id;
    private String date;
    private String hour;

    public Ticket(String player, String message, Integer id, String date, String hour) {
        this.player = player;
        this.message = message;
        this.id = id;
        this.date = date;
        this.hour = hour;
    }

    public String getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public Integer getId() { return id; }

    public String getDate() { return date; }

    public String getHour() { return hour; }

}
