package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.reopository.ChecksRepository;
import hexlet.code.reopository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.SQLException;

public class UrlCheckController {
    public static void check(Context context) throws SQLException {
        var urlId = context.pathParamAsClass("id", Long.class).get();
        try {
            String urlName = UrlRepository.findById(urlId).orElseThrow(() ->
                    new NotFoundResponse("URL не найден")).getName();

            HttpResponse<String> response = Unirest.get(urlName).asString();
            var statusCode = response.getStatus();

            Document document = Jsoup.parse(response.getBody());
            String title = document.title();
            Element h1El = document.selectFirst("h1");
            String h1 = h1El == null ? "" : h1El.text();
            Element descriptionEl = document.selectFirst("meta[name=description]");
            String description = descriptionEl == null ? "" : descriptionEl.attr("content");

            var urlCheck = new UrlCheck(statusCode, title, h1, description, urlId);
            ChecksRepository.save(urlCheck);

            context.sessionAttribute("flash", "Страница успешно проверена");
            context.sessionAttribute("flashType", "success");
        } catch (RuntimeException e) {
            context.sessionAttribute("flash", "Адрес не доступен");
            context.sessionAttribute("flashType", "danger");
        } finally {
            context.redirect(NamedRoutes.urlsPath(urlId));
        }
    }
}
