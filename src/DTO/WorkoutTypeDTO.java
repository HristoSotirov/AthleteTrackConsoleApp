package DTO;

public class WorkoutTypeDTO {
    private Long clubId;
    private String types;

    public WorkoutTypeDTO() {
    }

    public WorkoutTypeDTO(Long clubId, String types) {
        this.clubId = clubId;
        this.types = types;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "WorkoutTypeDTO{" +
                "clubId=" + clubId +
                ", types='" + types + '\'' +
                '}';
    }
}
