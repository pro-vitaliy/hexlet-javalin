package org.example.hexlet.dto.courses;

import lombok.Getter;
import lombok.AllArgsConstructor;
import org.example.hexlet.model.Course;

@AllArgsConstructor
@Getter
public class CoursePage {
    private Course course;
}