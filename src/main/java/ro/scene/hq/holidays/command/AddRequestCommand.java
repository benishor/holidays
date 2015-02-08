package ro.scene.hq.holidays.command;

import ro.scene.hq.holidays.*;

import java.time.LocalDate;
import java.util.List;

public final class AddRequestCommand implements Command {
    @Override
    public String getShortName() {
        return "add-request";
    }

    @Override
    public String getUsageLine() {
        return "employee-email manager-email start-date days";
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() < 5) {
            throw new IllegalArgumentException("Wrong number of arguments for executing action " + getShortName());
        }

        Identity employee = Identities.getByEmail(args.get(1));
        Identity manager = Identities.getByEmail(args.get(2));
        LocalDate startDate = LocalDate.parse(args.get(3));
        int days = Integer.valueOf(args.get(4));

        HolidayRequest request = new HolidayRequest()
                .fromEmployee(employee)
                .toManager(manager)
                .startingOn(startDate)
                .lastingForDays(days);
        request.submit();
    }
}