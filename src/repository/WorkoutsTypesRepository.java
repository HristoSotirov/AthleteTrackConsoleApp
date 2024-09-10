package repository;

import entity.WorkoutsTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutsTypesRepository {
    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public WorkoutsTypes addWorkoutType(WorkoutsTypes workoutsType) {
        String query = "INSERT INTO workouts_types (club_id, type) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, workoutsType.getClubId());
            statement.setString(2, workoutsType.getTypes());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        workoutsType.setId(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workoutsType;
    }

    public List<WorkoutsTypes> findAll(Long clubId) {
        String query = "SELECT * FROM workouts_types WHERE club_id = ? ORDER BY type ASC";
        List<WorkoutsTypes> workoutsTypesList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, clubId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long clubIdFromDB = resultSet.getLong("club_id");
                String types = resultSet.getString("type");

                WorkoutsTypes workoutType = new WorkoutsTypes(id, clubIdFromDB, types);
                workoutsTypesList.add(workoutType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workoutsTypesList;
    }


    public boolean workoutTypeExists(Long clubId, String type) {
        String query = "SELECT COUNT(*) FROM workouts_types WHERE club_id = ? AND type = ?";
        boolean exists = false;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, clubId);
            statement.setString(2, type);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    public WorkoutsTypes findById(Long id) {
        String query = "SELECT * FROM workouts_types WHERE id = ?";
        WorkoutsTypes workoutType = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long clubIdFromDB = resultSet.getLong("club_id");
                String types = resultSet.getString("type");

                workoutType = new WorkoutsTypes(id, clubIdFromDB, types);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workoutType;
    }


}