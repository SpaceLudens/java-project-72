@import gg.jte.Content
@import hexlet.code.dto.BasePage
@import hexlet.code.util.NamedRoutes
@param Content content
@param BasePage page = null 

<!doctype html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Анализатор страниц</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <header class="d-flex justify-content-center py-3">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="${NamedRoutes.rootPath()}" class="nav-link active" aria-current="page">Анализатор страниц</a></li>
            <li class="nav-item"><a href="${NamedRoutes.rootPath()}" class="nav-link">Главная</a></li>
            <li class="nav-item"><a href="${NamedRoutes.urlsPath()}" class="nav-link">Сайты</a></li>
        </ul>
    </header>
</div>
<div class="mx-auto p-4 py-md-5">
    @if(page != null && page.getFlash() != null)
        <p>${page.getFlash()}</p>
    @endif
    ${content}
</div>
<footer class="footer border-top py-3 mt-5 bg-light">
    <div class="container-xl">
        <div class="text-center">
            created by
            <a href="https://github.com/SpaceLudens" target="_blank">Kurmankulov</a>
        </div>
    </div>
</footer>
</body>
</html>