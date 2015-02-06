package ro.scene.hq.holidays;

import java.io.Serializable;

public class Identity implements Serializable {

    public String email;

    public String name;

    public Identity(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
