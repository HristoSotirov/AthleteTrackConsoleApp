package entity;

import java.time.LocalDateTime;

public class Workouts {
    private Long id;
    private Long athleteId;
    private Long clubId;
    private Long workoutTypeId;
    private String description;
    private LocalDateTime doneAt;
    private Long enteredBy;

    public Workouts() {
    }

    public Workouts(Long id, Long athleteId, Long clubId, Long workoutTypeId, String description, LocalDateTime doneAt, Long enteredBy) {
        this.id = id;
        this.athleteId = athleteId;
        this.clubId = clubId;
        this.workoutTypeId = workoutTypeId;
        this.description = description;
        this.doneAt = doneAt;
        this.enteredBy = enteredBy;
    }

    public Workouts(Long athleteId, Long clubId, Long workoutTypeId, String description, LocalDateTime doneAt, Long enteredBy) {
        this.athleteId = athleteId;
        this.clubId = clubId;
        this.workoutTypeId = workoutTypeId;
        this.description = description;
        this.doneAt = doneAt;
        this.enteredBy = enteredBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Long getWorkoutTypeId() {
        return workoutTypeId;
    }

    public void setWorkoutTypeId(Long workoutTypeId) {
        this.workoutTypeId = workoutTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }

    public Long getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(Long enteredBy) {
        this.enteredBy = enteredBy;
    }

    @Override
    public String toString() {
        return "Workouts{" +
                "id=" + id +
                ", athleteId=" + athleteId +
                ", clubId=" + clubId +
                ", workoutTypeId=" + workoutTypeId +
                ", description='" + description + '\'' +
                ", doneAt=" + doneAt +
                ", enteredBy=" + enteredBy +
                '}';
    }
}
