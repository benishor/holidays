package ro.scene.hq.holidays;

import java.io.Serializable;
import java.text.MessageFormat;

public class Identity implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;

    public String email;

    public String name;

    public Identity(String email, String name) {
        this.email = email;
        this.name = name;
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

    public void print() {
        System.out.println(MessageFormat.format("{0} \t{1}\t{2}", id, email, name));
    }
}
