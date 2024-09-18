@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
    page =page,
content = @`
    <div style="row row-cols-md-3 mb-3">
        <table class="table table-striped">
            @if(page.getUrls().isEmpty())
                <p>Не добавлено ни одной страницы</p>
            @else
                <tr >
                    <th >ID</th>
                    <th>Name</th>
                    <th>Created At</th>
                </tr>
                @for(var url : page.getUrls())
                    <tr>
                        <td>${url.getId()}</td>
                        <td><a href="${NamedRoutes.urlsPath(url.getId())}">${url.getName()}</a></td>
                        <td>${url.getCreatedAt().toString()}</td>
                    </tr>
                @endfor
            @endif
        </table>
    </div>
`
)