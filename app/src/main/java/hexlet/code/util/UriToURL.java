package hexlet.code.util;

import java.net.URISyntaxException;
import java.net.URI;

public class UriToURL {
    public static String parserUriToUrl(String uri) throws  URISyntaxException {
        URI parsedUri = new URI(uri);

        String protocol = parsedUri.getScheme();
        String host = parsedUri.getHost();
        int port = parsedUri.getPort();

        StringBuilder baseUrl = new StringBuilder(protocol + "://" + host);

        if (port != -1) {
            baseUrl.append(":").append(port);
        }
        return baseUrl.toString();
    }
}
