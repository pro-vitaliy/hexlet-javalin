package org.example.hexlet.repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import org.example.hexlet.model.User;

public class UserRepository extends BaseRepository {
    @Getter
    private static List<User> entities = new ArrayList<>();

    public static void save(User user) throws SQLException {
        var sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            var generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<User> find(Long id) {
        return entities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findAny();
    }

    public static List<User> search(String term) {
        return entities.stream()
                .filter(entity -> entity.getUsername().startsWith(term))
                .toList();
    }

}
