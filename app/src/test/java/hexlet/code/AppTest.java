package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.reopository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static hexlet.code.App.getApp;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private Javalin app;
    private MockWebServer mockWebServer;

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = getApp();
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterEach
    public void tearDown() throws IOException, SQLException {
        mockWebServer.shutdown();
    }

    @Test
    void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
            assertThat(response.code()).isEqualTo(200);
            assert response.body() != null;
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
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://some-domain.org:8080");

        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("https://some-domain.org:8080");
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    void testUrlCheck() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://example.com";
            client.post(NamedRoutes.urlsPath(), requestBody);

            var urlsResponse = client.get(NamedRoutes.urlsPath());
            assertThat(urlsResponse.code()).isEqualTo(200);

            mockWebServer.enqueue(new MockResponse().setBody("<html><title>Test Page</title><h1>Heading</h1><p>Description</p></html>"));

            var urlCheckResponse = client.post(NamedRoutes.urlsChecks("1"));
            assertThat(urlCheckResponse.code()).isEqualTo(200);

            var urlPageResponse = client.get(NamedRoutes.urlsPath("1"));
            assertThat(urlPageResponse.code()).isEqualTo(200);
            assertThat(urlPageResponse.body().string()).contains("Test Page");
        });
    }
}