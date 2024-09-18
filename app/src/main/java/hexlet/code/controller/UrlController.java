package hexlet.code.controller;

import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.reopository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static hexlet.code.parser.UriToURL.parserUriToUrl;
import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {
    public static void index(Context context) throws SQLException {
        List<Url> urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("urls/index.jte", model("page", page));
    }

    public static void show(Context context) throws SQLException {
        var id = context.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new UrlPage(url);
        context.render("urls/show.jte", model("page", page));
    }

    public static void create(Context context) throws SQLException, MalformedURLException, URISyntaxException {
        var uri = context.formParamAsClass("url", String.class).get();
        try {
            var name = parserUriToUrl(uri);
            var url = new Url(name, new Timestamp(System.currentTimeMillis()));
            if (UrlRepository.isUrlPresent(name)) {
                UrlRepository.save(url);
                context.sessionAttribute("flash", "Страница успешно добавлена");
                context.redirect(NamedRoutes.urlsPath());
            } else {
                context.sessionAttribute("flash", "Страница уже существует");
                context.redirect(NamedRoutes.urlsPath());
            }
        }  catch (IllegalArgumentException exception) {
            context.sessionAttribute("flash", "Некорректный URL");
            context.redirect(NamedRoutes.urlsPath());
        }

    }
}
