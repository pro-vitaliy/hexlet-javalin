package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class User {
    private Long id;
    private String password;

    @ToString.Include
    private String username;
    private String email;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
