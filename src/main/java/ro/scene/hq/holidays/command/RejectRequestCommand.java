package ro.scene.hq.holidays.command;

import ro.scene.hq.holidays.Command;
import ro.scene.hq.holidays.HolidayRequest;
import ro.scene.hq.holidays.Holidays;

import java.util.List;

public class RejectRequestCommand implements Command {

    @Override
    public String getShortName() {
        return "reject-request";
    }

    @Override
    public String getUsageLine() {
        return "request-id";
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("Wrong number of arguments for executing action " + getShortName());
        }

        String requestId = args.get(1);
        HolidayRequest request = Holidays.getById(requestId);
        if (request == null)
            throw new IllegalStateException("Cannot find request with id " + requestId);

        request.reject();
    }
}
