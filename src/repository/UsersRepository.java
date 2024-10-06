package repository;

import client.Colors;
import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersRepository {
    private final Colors color = new Colors();

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public void addUser(Users users) {
        String query = "INSERT INTO users (name, username, password, phone, email, type_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {


            statement.setString(1, users.getName());
            statement.setString(2, users.getUsername());
            statement.setString(3, users.getPassword());
            statement.setString(4, users.getPhone());
            statement.setString(5, users.getEmail());
            statement.setLong(6, users.getUserType());

            statement.executeUpdate();
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }
    }

    public Long getUserIdByUsername(String username) {
        String query = "SELECT id FROM users WHERE username = ?";
        Long userId = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return userId;
    }

    public boolean isUsernameUnique(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        boolean isUnique = true;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isUnique = (count == 0);
                }
            }
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return isUnique;
    }

    public boolean isPhoneUnique(String phone) {
        String query = "SELECT COUNT(*) FROM users WHERE phone = ?";
        boolean isUnique = true;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, phone);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isUnique = (count == 0);
                }
            }
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return isUnique;
    }

    public boolean isEmailUnique(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        boolean isUnique = true;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isUnique = (count == 0);
                }
            }
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return isUnique;
    }

    public Long login(String username, String password) {
        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        Long userId = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return userId;
    }

    public Users getUserById(Long id) {
        String query = "SELECT name, username, phone, email, type_id FROM users WHERE id = ?";
        Users user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new Users(
                            resultSet.getString("name"),
                            resultSet.getString("username"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            resultSet.getLong("type_id")
                    );
                }
            }
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return user;
    }

    public void updateUserName(Long userId, String newName) {
        String query = "UPDATE users SET name = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newName);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }
    }

    public void updateUsername(Long userId, String newUsername) {
        String query = "UPDATE users SET username = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newUsername);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }
    }

    public void updateUserPassword(Long userId, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newPassword);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }
    }

    public void updateUserPhone(Long userId, String newPhone) {
        String query = "UPDATE users SET phone = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newPhone);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }
    }

    public void updateUserEmail(Long userId, String newEmail) {
        String query = "UPDATE users SET email = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newEmail);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }
    }

}
