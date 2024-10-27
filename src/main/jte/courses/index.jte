@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page


@template.layout.page(
    content = @`
    <main>
        <form action="${NamedRoutes.coursesPath()}" method="get">
            <label>
                <input type="search" name="term" value="${page.getTerm()}" />
            </label>
            <input type="submit" value="Искать">
        </form>
        <a href="${NamedRoutes.buildCoursePath()}">Add new course</a>
        <h1>${page.getTerm()}</h1>
        @if(page.getCourses().isEmpty())
            <p>No courses yet</p>
        @else
            @for(var course : page.getCourses())
                <div>
                    <h3><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></h3>
                </div>
            @endfor
        @endif
    </main>
    `
)
