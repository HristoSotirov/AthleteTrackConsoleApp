package entity;

import java.util.List;
import java.util.Map;

public class Analysis {
    private int totalWorkouts;
    private Map<String, Integer> totalWorkoutsByType;
    private List<Workouts> workouts;

    public Analysis() {
    }

    public Analysis(int totalWorkouts, Map<String, Integer> totalWorkoutsByType, List<Workouts> workouts) {
        this.totalWorkouts = totalWorkouts;
        this.totalWorkoutsByType = totalWorkoutsByType;
        this.workouts = workouts;
    }

    public int getTotalWorkouts() {
        return totalWorkouts;
    }

    public void setTotalWorkouts(int totalWorkouts) {
        this.totalWorkouts = totalWorkouts;
    }

    public Map<String, Integer> getTotalWorkoutsByType() {
        return totalWorkoutsByType;
    }

    public void setTotalWorkoutsByType(Map<String, Integer> totalWorkoutsByType) {
        this.totalWorkoutsByType = totalWorkoutsByType;
    }

    public List<Workouts> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workouts> workouts) {
        this.workouts = workouts;
    }

    @Override
    public String toString() {
        return "AnalysisDTO{" +
                "totalWorkouts=" + totalWorkouts +
                ", totalWorkoutsByType=" + totalWorkoutsByType +
                ", workouts=" + workouts +
                '}';
    }
}
