package hexlet.code.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.URL;

public class UriToURL {
    public static String parserUriToUrl(String uri) throws MalformedURLException, URISyntaxException {
        URL url = new URI(uri).toURL();
        return url.getProtocol() + "://" + url.getAuthority();
    }
}
