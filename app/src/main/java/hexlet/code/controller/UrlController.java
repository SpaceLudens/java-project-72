package hexlet.code.controller;

import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.reopository.ChecksRepository;
import hexlet.code.reopository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import static hexlet.code.util.UriToURL.parserUriToUrl;
import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {
    public static void index(Context context) throws SQLException {
        List<Url> urls = UrlRepository.getEntities();

        List<UrlCheck> latestChecks = ChecksRepository.findLatestCheckByUrlId();

        for (Url url : urls) {
            for (UrlCheck check : latestChecks) {
                if (url.getId() == check.getUrlId()) {
                    url.setLastUrlCheck(check);
                    break;
                }
            }
        }

        var page = new UrlsPage(urls);
        page.setFlash(context.consumeSessionAttribute("flash"));
        page.setFlashType(context.consumeSessionAttribute("flashType"));
        context.render("urls/index.jte", model("page", page));
    }

    public static void show(Context context) throws SQLException {
        var id = context.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var urlChecks = ChecksRepository.getEntitiesByParentId(id);
        var page = new UrlPage(url, urlChecks);
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("urls/show.jte", model("page", page));
    }

    public static void create(Context context) throws SQLException, MalformedURLException, URISyntaxException {
        var uri = context.formParamAsClass("url", String.class).get().trim();
        try {
            var name = parserUriToUrl(uri);
            var url = new Url(name);

            if (!UrlRepository.isUrlPresent(name)) {
                UrlRepository.save(url);
                context.sessionAttribute("flash", "Страница успешно добавлена");
                context.sessionAttribute("flashType", "success");
            } else {
                context.sessionAttribute("flash", "Страница уже существует");
                context.sessionAttribute("flashType", "warning");
            }
        } catch (IllegalArgumentException exception) {
            context.sessionAttribute("flash", "Некорректный URL");
            context.sessionAttribute("flashType", "danger");
        } finally {
            context.redirect(NamedRoutes.urlsPath());
        }

    }
}
