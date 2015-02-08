package ro.scene.hq.holidays;

public class Identities {

    public static Identity getByEmail(String email) {
        return ServiceLocator.getIdentityRepository().getByEmail(email);
    }
}
