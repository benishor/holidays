package ro.scene.hq.holidays.command;

import ro.scene.hq.holidays.Command;
import ro.scene.hq.holidays.ServiceLocator;

import java.util.List;

public class ListIdentitiesCommand implements Command {

    @Override
    public String getShortName() {
        return "list-identities";
    }

    @Override
    public String getUsageLine() {
        return "";
    }

    @Override
    public void execute(List<String> args) {
        ServiceLocator.getIdentityRepository().printAll();
    }
}
