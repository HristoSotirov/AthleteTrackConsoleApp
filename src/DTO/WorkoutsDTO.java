package DTO;


public class WorkoutsDTO {
    private String athlete;
    private String club;
    private String workoutTypeId;
    private String description;
    private String doneAt;
    private String enteredBy;

    public WorkoutsDTO() {

    }

    public WorkoutsDTO(String athlete, String club, String workoutTypeId, String description, String doneAt, String enteredBy) {
        this.athlete = athlete;
        this.club = club;
        this.workoutTypeId = workoutTypeId;
        this.description = description;
        this.doneAt = doneAt;
        this.enteredBy = enteredBy;
    }

    public String getAthlete() {
        return athlete;
    }

    public void setAthlete(String athlete) {
        this.athlete = athlete;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getWorkoutTypeId() {
        return workoutTypeId;
    }

    public void setWorkoutTypeId(String workoutTypeId) {
        this.workoutTypeId = workoutTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(String doneAt) {
        this.doneAt = doneAt;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    @Override
    public String toString() {
        return "WorkoutsDTO{" +
                "athlete='" + athlete + '\'' +
                ", club='" + club + '\'' +
                ", workoutTypeId='" + workoutTypeId + '\'' +
                ", description='" + description + '\'' +
                ", doneAt='" + doneAt + '\'' +
                ", enteredBy='" + enteredBy + '\'' +
                '}';
    }
}