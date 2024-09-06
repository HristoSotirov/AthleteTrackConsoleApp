package entity;

public class UsersClubs {
    private Long userId;
    private Long clubId;

    public UsersClubs() {
    }

    public UsersClubs(Long userId, Long clubId) {
        this.userId = userId;
        this.clubId = clubId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    @Override
    public String toString() {
        return "UsersClubs{" +
               "userId=" + userId +
               ", clubId=" + clubId +
               '}';
    }
}
