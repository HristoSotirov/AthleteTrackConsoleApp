package entity;

public class Clubs {
    private Long id;
    private String name;
    private Long sportId;
    private String address;
    private String phone;
    private String email;

    public Clubs() {
    }

    public Clubs(Long id, String name, Long sportId, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.sportId = sportId;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

    @Override
    public String toString() {
        return "Clubs{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", sportId=" + sportId +
               ", address='" + address + '\'' +
               ", phone='" + phone + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
