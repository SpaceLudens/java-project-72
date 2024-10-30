package hexlet.code.dto.urls;

import hexlet.code.model.UrlCheck;
import hexlet.code.model.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UrlPage {
    private Url url;
    private List<UrlCheck> urlChecks;
}
