package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionsController;
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
            var page = new MainPage(ctx.sessionAttribute("currentUser"));
            ctx.render("index.jte", model("page", page));
        });

//        Sessions

        app.get(NamedRoutes.buildSessionPath(), SessionsController::build);

        app.post(NamedRoutes.sessionsPath(), SessionsController::create);

        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

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
