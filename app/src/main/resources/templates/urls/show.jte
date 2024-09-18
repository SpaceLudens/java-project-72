@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

@template.layout.page(
content = @`
    <div>ID: ${page.getUrl().getId()}</div>
    <div>Course name: ${page.getUrl().getName()}</div>
    <div>Created at: ${page.getUrl().getCreatedAt().toString()}</div>
`
)
