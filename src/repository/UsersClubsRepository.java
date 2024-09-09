package repository;

import entity.UsersClubs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersClubsRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public void addUserClub(UsersClubs userclub) {
        String query = "INSERT INTO users_clubs (user_id, club_id) VALUES (?, ?)";

        Long userId = userclub.getUserId();
        Long clubId = userclub.getClubId();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);
            statement.setLong(2, clubId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserClubCount(Long userId) {
        String query = "SELECT COUNT(*) AS club_count FROM users_clubs WHERE user_id = ?";
        int clubCount = 0;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clubCount = resultSet.getInt("club_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubCount;
    }

    public List<Long> getUserClubIds(Long userId) {
        String query = "SELECT club_id FROM users_clubs WHERE user_id = ?";
        List<Long> clubIds = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    clubIds.add(resultSet.getLong("club_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubIds;
    }

    public Long getSingleUserClubId(Long userId) {
        String query = "SELECT club_id FROM users_clubs WHERE user_id = ? LIMIT 1";
        Long clubId = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clubId = resultSet.getLong("club_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubId;
    }
}
