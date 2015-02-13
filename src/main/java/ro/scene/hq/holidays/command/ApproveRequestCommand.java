package ro.scene.hq.holidays.command;

import ro.scene.hq.holidays.Command;
import ro.scene.hq.holidays.HolidayRequest;

import java.util.List;

public class ApproveRequestCommand implements Command {

    @Override
    public String getShortName() {
        return "approve-request";
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
        HolidayRequest request = HolidayRequest.fromId(requestId);
        if (request == null)
            throw new IllegalStateException("Cannot find request with id " + requestId);

        request.accept();
    }
}
