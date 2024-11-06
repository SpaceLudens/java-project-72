package hexlet.code.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URI;

public class UriToURL {
    public static String parserUriToUrl(String uri) throws MalformedURLException, URISyntaxException {
        URI parsedUri = new URI(uri);
        parsedUri.toURL();
        return parsedUri.getScheme() + "://" + parsedUri.getHost();
    }
}
