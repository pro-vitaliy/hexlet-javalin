package org.example.hexlet.dto.users;

import org.example.hexlet.model.User;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsersPage {
    private List<User> users;
    private String term;
}
