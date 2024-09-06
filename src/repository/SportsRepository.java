package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SportsRepository {

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
            e.printStackTrace(); // Handle exceptions as needed
        }

        return sports;
    }

    public static void main(String[] args) {
        SportsRepository repository = new SportsRepository();
        List<String> sports = repository.getAllSportsInAlphabeticalOrder();

        System.out.println("Sports in alphabetical order:");
        for (int i = 0; i < sports.size(); i++) {
            System.out.println((i + 1) + ". " + sports.get(i));
        }
    }

}
