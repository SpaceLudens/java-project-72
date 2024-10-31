@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.util.NamedRoutes
@import hexlet.code.util.DateFormatter
@param UrlPage page

@template.layout.page(
    page = page,
content = @`
        <div>
            <h1>Сайт: ${page.getUrl().getName()}</h1>
        </div>

        <div>
            <table class="table table-bordered table-hover mt-3">
                <tr>
                    <td>ID</td>
                    <td>${page.getUrl().getId()}</td>
                </tr>
                <tr>
                    <td>Имя сайта</td>
                    <td>${page.getUrl().getName()}</td>
                </tr>
                <tr>
                    <td>Дата создания</td>
                    <td>${page.getUrl().getCreatedAt().toString()}</td>
                </tr>
            </table>
        </div>

        <div>
            <h2 class="mt-5">Проверки</h2>
        </div>

        <div>
            <form action="${NamedRoutes.urlsChecks(page.getUrl().getId())}" method="post">
                <button type="submit" class="btn btn-primary">Запустить проверку</button>
            </form>
        </div>

        <div>
            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr>
                    <th class="col-1">ID</th>
                    <th class="col-1">Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th class="col-2">Дата проверки</th>
                </tr>
                </thead>
                <tbody>
                @for(var check: page.getUrlChecks())
                    <tr>
                        <td>${check.getId()}</td>
                        <td>${check.getStatusCode()}</td>
                        <td>${check.getTitle()}</td>
                        <td>${check.getH1()}</td>
                        <td>${check.getDescription()}</td>
                        <td>${DateFormatter.formatDateTime(check.getCreatedAt())}</td>
                    </tr>
                @endfor
                </tbody>
            </table>
        </div>
`
)