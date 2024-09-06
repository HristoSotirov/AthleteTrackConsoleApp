package entity;

import java.util.Date;

public class Athletes {
    private Long athleteId;
    private Date birtDate;
    private Long coachId;

    public Athletes() {
    }

    public Athletes(Long athleteId, Date birtDate, Long coachId) {
        this.athleteId = athleteId;
        this.birtDate = birtDate;
        this.coachId = coachId;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public Date getBirtDate() {
        return birtDate;
    }

    public void setBirtDate(Date birtDate) {
        this.birtDate = birtDate;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    @Override
    public String toString() {
        return "Athletes{" +
               "athleteId=" + athleteId +
               ", birtDate=" + birtDate +
               ", coachId=" + coachId +
               '}';
    }
}
