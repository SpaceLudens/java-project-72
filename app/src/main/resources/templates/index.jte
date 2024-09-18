@import hexlet.code.dto.MainPage
@param MainPage page

@template.layout.page(
content = @`
    <form action="/urls" method="post">
        <input id="url" autofocus type="text" required name="url" aria-label="url"
               placeholder="ссылка" autocomplete="off">
        <button type="submit">Проверить</button>
    </form>
`

)