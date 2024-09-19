package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.reopository.UrlRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static hexlet.code.App.getApp;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    Javalin app;

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = getApp();
    }

    @Test
    void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    void testUrlsListPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://some-domain.org:8080/example/path";
            var response = client.post("/urls",requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://some-domain.org:8080");

        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("https://some-domain.org:8080", new Timestamp(System.currentTimeMillis()));
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
        });
    }
}
