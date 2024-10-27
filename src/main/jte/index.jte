@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
  content = @`
        <form action="${NamedRoutes.coursesPath()}" method="get">
          <label>
            <input type="search" required name="term">
          </label>
          <input type="submit" value="Искать">
        </form>
        <h1>Hello, Hexlet!</h1>
        <p>Main page</p>
        @if(!page.isVisited())
          Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif
    `
)