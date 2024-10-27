@import org.example.hexlet.NamedRoutes

@template.layout.page(content = @`
  <form action="${NamedRoutes.sessionsPath()}" method="post">
    <label>
      <input type="text" placeholder="Nickname" name="nickname">
    </label>
    <label>
      <input type="password" placeholder="Password" name="password">
    </label>
    <input type="submit" class="btn btn-primary" value="Login">
  </form>
`)