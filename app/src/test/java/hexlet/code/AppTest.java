package hexlet.code;

import org.junit.jupiter.api.Test;

import static hexlet.code.App.replaceUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    void replaceTest() {
        String expected = "jdbc:postgresql://dpg-cqggigg8fa8c7387du40-a:5432/rustam_database?password=<masked>&user=rustam_database_user";
        String actual = replaceUrl(expected);
        assertEquals(expected, actual);
    }
}
