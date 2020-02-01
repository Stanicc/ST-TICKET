package stanic.stticket.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm");

    public String getDate() {
        return date.format(now);
    }

    public String getHour() {
        return hour.format(now);
    }

}
