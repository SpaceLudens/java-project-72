@import hexlet.code.dto.urls.UrlsPage
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
                <th class=".col-sm-6 .col-lg-8">ID</th>
                <th class=".col-sm-6 .col-lg-8">Имя</th>
                <th class=".col-sm-6 .col-lg-8">Последняя проверка</th>
                <th class=".col-sm-6 .col-lg-8">Код ответа</th>
            </tr>
            </thead>
            <tbody>
            @for(var url : page.getUrls())
                <tr>
                    <td class=".col-sm-6 .col-lg-8">${url.getId()}</td>
                    <td><a href="${NamedRoutes.urlsPath(url.getId())}">${url.getName()}</a></td>
                    <td></td>
                    <td></td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`
)