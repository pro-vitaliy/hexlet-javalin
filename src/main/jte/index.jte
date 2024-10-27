@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
  content = @`
        <form action="/courses" method="get">
          <input type="search" required name="term">
          <input type="submit" value="Искать">
        </form>
        <h1>Hello, Hexlet!</h1>
        <p>Main page</p>
        @if(!page.isVisited())
          Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif
    `
)