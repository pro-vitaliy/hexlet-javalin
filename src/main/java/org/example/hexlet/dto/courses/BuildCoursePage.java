package org.example.hexlet.dto.courses;

import io.javalin.validation.ValidationError;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BuildCoursePage {
    private String name;
    private String description;
    private Map<String, List<ValidationError<Object>>> errors;
}
