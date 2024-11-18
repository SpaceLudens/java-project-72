package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.util.DateFormatter;
import hexlet.code.util.NamedRoutes;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,5,5,8,8,21,21,23,23,23,24,24,24,24,24,24,24,24,24,24,24,24,26,26,27,27,27,28,28,28,29,29,32,32,34,34,38,38,38,38,38,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container-lg mt-5\">\n        <h1>Сайты</h1>\n        <table class=\"table table-bordered table-hover mt-3\">\n            <thead>\n            <tr>\n                <th>ID</th>\n                <th>Имя</th>\n                <th>Последняя проверка</th>\n                <th>Код ответа</th>\n            </tr>\n            </thead>\n            <tbody>\n            ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n                <tr>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</td>\n                    <td><a");
					var __jte_html_attribute_0 = NamedRoutes.urlsPath(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a></td>\n\n                    ");
					if (page.getUrlsChecks().containsKey(url.getId())) {
						jteOutput.writeContent("\n                        <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(DateFormatter.formatDateTime(page.getUrlsChecks().get(url.getId()).getCreatedAt()));
						jteOutput.writeContent("</td>\n                        <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getUrlsChecks().get(url.getId()).getStatusCode());
						jteOutput.writeContent("</td>\n                    ");
					} else {
						jteOutput.writeContent("\n                        <td></td>\n                        <td></td>\n                    ");
					}
					jteOutput.writeContent("\n                </tr>\n            ");
				}
				jteOutput.writeContent("\n            </tbody>\n        </table>\n    </div>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
