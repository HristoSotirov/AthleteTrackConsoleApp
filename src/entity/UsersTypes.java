package entity;

public class UsersTypes {
    private Long id;
    private String type;

    public UsersTypes() {
    }

    public UsersTypes(Long id, String name) {
        this.id = id;
        this.type = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UsersTypes{" +
               "id=" + id +
               ", type='" + type + '\'' +
               '}';
    }
}
