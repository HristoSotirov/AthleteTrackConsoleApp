package DTO;

public class RegisterClubAdminDTO {
    private String clubName;
    private Long sportId;
    private String address;
    private String clubPhone;
    private String clubEmail;
    private String userName;
    private String userUsername;
    private String userPassword;
    private String userPhone;
    private String userEmail;

    public RegisterClubAdminDTO() {
    }

    public RegisterClubAdminDTO(String clubName, Long sportId, String address, String clubPhone, String clubEmail, String userName, String userUsername, String userPassword, String userPhone, String userEmail) {
        this.clubName = clubName;
        this.sportId = sportId;
        this.address = address;
        this.clubPhone = clubPhone;
        this.clubEmail = clubEmail;
        this.userName = userName;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClubPhone() {
        return clubPhone;
    }

    public void setClubPhone(String clubPhone) {
        this.clubPhone = clubPhone;
    }

    public String getClubEmail() {
        return clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "RegisterClubAdmin{" +
                "clubName='" + clubName + '\'' +
                ", sportId=" + sportId +
                ", address='" + address + '\'' +
                ", clubPhone='" + clubPhone + '\'' +
                ", clubEmail='" + clubEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userUsername='" + userUsername + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
