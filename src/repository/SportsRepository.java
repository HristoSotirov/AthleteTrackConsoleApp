package repository;

import client.Colors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SportsRepository {
    private final Colors color = new Colors();

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public List<String> getAllSportsInAlphabeticalOrder() {
        List<String> sports = new ArrayList<>();
        String query = "SELECT sport_name FROM public.sports ORDER BY sport_name ASC";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                sports.add(resultSet.getString("sport_name"));
            }

        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return sports;
    }

    public Long getSportIdByName(String sportName) {
        String query = "SELECT id FROM public.sports WHERE sport_name = ?";
        Long sportId = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, sportName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    sportId = resultSet.getLong("id");
                }
            }

        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return sportId;
    }

    public String getSportNameById(Long sportId) {
        String query = "SELECT sport_name FROM public.sports WHERE id = ?";
        String sportName = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, sportId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    sportName = resultSet.getString("sport_name");
                }
            }

        } catch (SQLException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }

        return sportName;
    }

}
