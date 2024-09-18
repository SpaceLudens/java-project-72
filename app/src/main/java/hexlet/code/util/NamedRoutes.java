package hexlet.code.util;

public class NamedRoutes {
    public static String rootPath() {
        return "/";
    }

    public static String urlsPath() {
        return "/urls";
    }

    public static String urlsPath(String id) {
        return "/urls/" + id;
    }

    public static String urlsPath(Long id) {
        return urlsPath(String.valueOf(id));
    }
}
