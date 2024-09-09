package repository;

import entity.Clubs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubsRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }


    public void addClub(Clubs club) throws SQLException {
        String query = "INSERT INTO clubs (name, sport_id, address, phone, email) VALUES ( ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, club.getName());
            statement.setLong(2, club.getSportId());
            statement.setString(3, club.getAddress());
            statement.setString(4, club.getPhone());
            statement.setString(5, club.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Clubs> getUnverifiedClubs() throws SQLException {
        String query = "SELECT id, name, sport_id, address, phone, email, verified_by FROM clubs WHERE verified_by = 0";
        List<Clubs> unverifiedClubs = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Clubs club = new Clubs();
                club.setId(resultSet.getLong("id"));
                club.setName(resultSet.getString("name"));
                club.setSportId(resultSet.getLong("sport_id"));
                club.setAddress(resultSet.getString("address"));
                club.setPhone(resultSet.getString("phone"));
                club.setEmail(resultSet.getString("email"));
                club.setVerifiedBy(resultSet.getLong("verified_by"));


                unverifiedClubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return unverifiedClubs;
    }

    public Clubs getClubById(Long clubId) throws SQLException {
        String query = "SELECT id, name, sport_id, address, phone, email, verified_by FROM clubs WHERE id = ?";
        Clubs club = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, clubId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    club = new Clubs();
                    club.setId(resultSet.getLong("id"));
                    club.setName(resultSet.getString("name"));
                    club.setSportId(resultSet.getLong("sport_id"));
                    club.setAddress(resultSet.getString("address"));
                    club.setPhone(resultSet.getString("phone"));
                    club.setEmail(resultSet.getString("email"));
                    club.setVerifiedBy(resultSet.getLong("verified_by"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return club;
    }

    public boolean isClubVerified(Long clubId) {
        String query = "SELECT verified_by FROM clubs WHERE id = ?";
        boolean isVerified = false;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, clubId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long verifiedBy = resultSet.getLong("verified_by");
                    isVerified = verifiedBy != 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isVerified;
    }

    public Long getClubIdByName(String name) {
        String query = "SELECT id FROM clubs WHERE name = ?";
        Long clubId = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clubId = resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubId;
    }

    public boolean isClubNameUnique(String name) {
        String query = "SELECT COUNT(*) FROM clubs WHERE name = ?";
        boolean isUnique = true;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isUnique = (count == 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUnique;
    }

    public boolean isClubPhoneUnique(String phone) {
        String query = "SELECT COUNT(*) FROM clubs WHERE phone = ?";
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
            e.printStackTrace();
        }

        return isUnique;
    }

    public boolean isClubEmailUnique(String email) {
        String query = "SELECT COUNT(*) FROM clubs WHERE email = ?";
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
            e.printStackTrace();
        }

        return isUnique;
    }

}
