package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;

import static io.javalin.rendering.template.TemplateUtil.model;


public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            var page = new MainPage(visited);
            ctx.render("index.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });

//        Courses

        app.get(NamedRoutes.coursesPath(), CoursesController::index);

        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);

        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);

        app.post(NamedRoutes.coursesPath(), CoursesController::create);

//        Users

        app.get(NamedRoutes.usersPath(), UsersController::index);

        app.get(NamedRoutes.buildUserPath(), UsersController::build);

        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.start(7070); // Стартуем веб-сервер
    }
}
