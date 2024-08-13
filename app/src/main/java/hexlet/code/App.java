package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.reopository.BaseRepository;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class App {
    public static Javalin getApp() throws Exception {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getUrl());

        var dataSource = new HikariDataSource(hikariConfig);

        var url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));

        try (var connection = dataSource.getConnection();
            var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
            javalinConfig.fileRenderer(new JavalinJte());
        });
        return app;
    }

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    private static String getUrl() {
        String url = System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
        return url.replaceAll("(\\$\\{)|(})", "");
    }

    public static void main(String[] args) throws Exception {
        var app = getApp();
        app.get("/", context -> context.result("Hello, World!"));
        app.start(getPort());
    }
}
