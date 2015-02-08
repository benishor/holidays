package ro.scene.hq.holidays.command;

import ro.scene.hq.holidays.Command;
import ro.scene.hq.holidays.Identity;

import java.util.List;

public class AddIdentityCommand implements Command {

    @Override
    public String getShortName() {
        return "add-identity";
    }

    @Override
    public String getUsageLine() {
        return "email name";
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() < 3) {
            throw new IllegalArgumentException("Wrong number of arguments for executing action " + getShortName());
        }

        Identity identity = new Identity(args.get(1), args.get(2));
        identity.save();
    }
}
