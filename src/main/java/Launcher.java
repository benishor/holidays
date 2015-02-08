import ro.scene.hq.holidays.*;
import ro.scene.hq.holidays.command.*;
import ro.scene.hq.holidays.repository.HolidayRequestRepository;
import ro.scene.hq.holidays.repository.IdentityRepository;
import ro.scene.hq.holidays.transport.DeliveryServiceConsole;

import java.util.Arrays;


public class Launcher {

    private static CommandRegistry commandRegistry;

    static {
        CommandRegistry.register(ListRequestsCommand.class);
        CommandRegistry.register(AddRequestCommand.class);
        CommandRegistry.register(ApproveRequestCommand.class);
        CommandRegistry.register(RejectRequestCommand.class);
        CommandRegistry.register(AddIdentityCommand.class);
        CommandRegistry.register(ListIdentitiesCommand.class);
    }

    public static void main(String... args) {
        initializeServices();
        try {
            CommandRegistry.execute(Arrays.asList(args));
        } catch (Exception e) {
            System.err.println("\nError: " + e.getMessage());
        }
    }

    private static void initializeServices() {
        IdentityRepository identityRepository = new IdentityRepository("identities.data");
        ServiceLocator.setIdentityRepository(identityRepository);

        HolidayRequestRepository holidayRequestRepository = new HolidayRequestRepository("requests.data");
        ServiceLocator.setHolidayRequestRepository(holidayRequestRepository);

        DeliveryService deliveryService = new DeliveryServiceConsole();
        ServiceLocator.setDeliveryService(deliveryService);
    }
}