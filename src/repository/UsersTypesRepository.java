package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTypesRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public String getTypeById(Long id) {
        String query = "SELECT type FROM users_types WHERE id = ?";
        String type = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    type = resultSet.getString("type");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return type;
    }

    public Long getIdByType(String type) {
        String query = "SELECT id FROM users_types WHERE type = ?";
        Long id = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, type);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}