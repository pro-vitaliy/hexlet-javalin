package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {
    public static void index(Context ctx) {
        var term = ctx.queryParam("term");
        List<Course> filteredCourses;
        if (term != null) {
            filteredCourses = CourseRepository.search(term);
        } else {
            filteredCourses = CourseRepository.getEntities();
        }
        var page = new CoursesPage(filteredCourses, term);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("courses/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Course with id " + id + " not found"));
        var page = new CoursePage(course);
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name").trim();
        var description = ctx.formParam("description");

        try {
            var nameCheck = ctx.formParamAsClass("name", String.class)
                    .check(value -> {
                        return CourseRepository.getEntities().stream()
                                .noneMatch(course -> course.getName().equals(value));
                    }, "Course " + name + " already added")
                    .get();
            var descriptionCheck = ctx.formParamAsClass("description", String.class)
                    .check(value -> value.length() > 10, "Description length too short")
                    .get();
            var course = new Course(nameCheck, descriptionCheck);
            CourseRepository.save(course);
            ctx.sessionAttribute("flash", "Course has been created!");
            ctx.redirect(NamedRoutes.coursesPath());
        } catch (ValidationException e) {
            var page = new BuildCoursePage(name, description, e.getErrors());
            ctx.render("courses/build.jte", model("page", page));
        }
    }
}
