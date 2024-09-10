package entity;

public class WorkoutsTypes {
    private Long id;
    private Long clubId;
    private String types;

    public WorkoutsTypes() {
    }

    public WorkoutsTypes(Long id, Long clubId, String types) {
        this.id = id;
        this.clubId = clubId;
        this.types = types;
    }

    public WorkoutsTypes(Long clubId, String types) {
        this.clubId = clubId;
        this.types = types;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "WorkoutsTypes{" +
               "id=" + id +
               ", clubId=" + clubId +
               ", types='" + types + '\'' +
               '}';
    }
}
