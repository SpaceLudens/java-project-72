package hexlet.code.util;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static String formatDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        return FORMATTER.format(timestamp.toLocalDateTime());
    }
}
