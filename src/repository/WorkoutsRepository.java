package repository;

import DTO.AnalysisDTO;
import DTO.WorkoutsDTO;
import entity.Analysis;
import entity.Workouts;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutsRepository {
    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public void addWorkout(Workouts workout) {
        String query = "INSERT INTO workouts (athlete_id, club_id, workout_type_id, description, done_at, entered_by) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, workout.getAthleteId());
            statement.setLong(2, workout.getClubId());
            statement.setLong(3, workout.getWorkoutTypeId());
            statement.setString(4, workout.getDescription());
            statement.setTimestamp(5, Timestamp.valueOf(workout.getDoneAt()));
            statement.setLong(6, workout.getEnteredBy());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean workoutExists(Long athleteId, LocalDateTime doneAt) {
        String query = "SELECT COUNT(*) FROM workouts WHERE athlete_id = ? AND done_at = ?";
        boolean exists = false;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, athleteId);
            statement.setTimestamp(2, Timestamp.valueOf(doneAt));

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

    public void deleteWorkout(Long athleteId, LocalDateTime doneAt) {
        String query = "DELETE FROM workouts WHERE athlete_id = ? AND done_at = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, athleteId);
            statement.setTimestamp(2, Timestamp.valueOf(doneAt));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Analysis getAnalysisForAthlete(Long athleteId, LocalDateTime startDate, LocalDateTime endDate) {
        Analysis analysis = new Analysis();
        List<Workouts> workouts = new ArrayList<>();

        String totalWorkoutsQuery = "SELECT COUNT(*) AS total FROM workouts WHERE athlete_id = ? AND done_at BETWEEN ? AND ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(totalWorkoutsQuery)) {
            ps.setLong(1, athleteId);
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    analysis.setTotalWorkouts(rs.getInt("total"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String totalWorkoutsByTypeQuery = "SELECT wt.type AS workout_type_name, COUNT(*) AS total " +
                "FROM workouts w " +
                "JOIN workouts_types wt ON w.workout_type_id = wt.id " +
                "WHERE w.athlete_id = ? AND w.done_at BETWEEN ? AND ? " +
                "GROUP BY wt.type";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(totalWorkoutsByTypeQuery)) {

            ps.setLong(1, athleteId);
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                Map<String, Integer> totalWorkoutsByType = new HashMap<>();
                while (rs.next()) {
                    totalWorkoutsByType.put(rs.getString("workout_type_name"), rs.getInt("total"));
                }
                analysis.setTotalWorkoutsByType(totalWorkoutsByType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query = "SELECT * FROM workouts WHERE athlete_id = ? AND done_at BETWEEN ? AND ? ORDER BY athlete_id, done_at";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, athleteId);
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Workouts workout = new Workouts(
                            rs.getLong("athlete_id"),
                            rs.getLong("club_id"),
                            rs.getLong("workout_type_id"),
                            rs.getString("description"),
                            rs.getTimestamp("done_at").toLocalDateTime(),
                            rs.getLong("entered_by")
                    );
                    workouts.add(workout);
                }
            }
            analysis.setWorkouts(workouts);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return analysis;
    }
}