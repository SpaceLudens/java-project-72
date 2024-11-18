@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.DateFormatter
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
    page =page,
content = @`
    <div class="container-lg mt-5">
        <h1>Сайты</h1>
        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Последняя проверка</th>
                <th>Код ответа</th>
            </tr>
            </thead>
            <tbody>
            @for(var url : page.getUrls())
                <tr>
                    <td>${url.getId()}</td>
                    <td><a href="${NamedRoutes.urlsPath(url.getId())}">${url.getName()}</a></td>

                    @if(page.getUrlsChecks().containsKey(url.getId()))
                        <td>${DateFormatter.formatDateTime(page.getUrlsChecks().get(url.getId()).getCreatedAt())}</td>
                        <td>${page.getUrlsChecks().get(url.getId()).getStatusCode()}</td>
                    @else
                        <td></td>
                        <td></td>
                    @endif
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`
)