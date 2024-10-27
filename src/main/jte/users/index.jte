@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
  content = @`
     <main>
       <a href="${NamedRoutes.buildUserPath()}">Register new user</a>
       @if(page.getUsers().isEmpty())
         <p>Not users yet</p>
       @else
         @for(var user : page.getUsers())
           <p>${user.toString()}</p>
         @endfor
       @endif
     </main>
    `
)