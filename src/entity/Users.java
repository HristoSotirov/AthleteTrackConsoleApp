package entity;

public class Users {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Long userType;

    public Users() {
    }

    public Users(Long id, String name, String username, String password, String phone, String email, Long userType) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
    }

    // register users
    public Users(String name, String username, String password, String phone, String email, Long userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
    }

    public Users(String name, String username, String phone, String email, Long userType) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Users{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", phone='" + phone + '\'' +
               ", email='" + email + '\'' +
               ", userType=" + userType +
               '}';
    }
}
