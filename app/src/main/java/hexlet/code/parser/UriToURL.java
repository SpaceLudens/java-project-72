package hexlet.code.parser;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.URL;

public class UriToURL {
    public static String parserUriToUrl(String URI) throws MalformedURLException, URISyntaxException {
        URI uri = new URI(URI);
        URL url = uri.toURL();
        return url.getProtocol() + "://" + url.getAuthority();
    }
}
