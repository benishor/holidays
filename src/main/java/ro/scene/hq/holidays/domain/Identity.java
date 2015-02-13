package ro.scene.hq.holidays.domain;

import java.io.Serializable;

public class Identity implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;

    public String email;

    public String name;

    public Identity(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public static Identity fromEmail(String email) {
        return ServiceLocator.getIdentityRepository().getByEmail(email);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void save() {
        ServiceLocator.getIdentityRepository().save(this);
    }

    @Override
    public String toString() {
        return "Identity{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
