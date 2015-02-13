package ro.scene.hq.holidays.application;

import ro.scene.hq.holidays.application.command.*;
import ro.scene.hq.holidays.domain.NotificationChannel;
import ro.scene.hq.holidays.domain.ServiceLocator;
import ro.scene.hq.holidays.domain.repository.HolidayRequestRepository;
import ro.scene.hq.holidays.domain.repository.IdentityRepository;
import ro.scene.hq.holidays.domain.transport.NotificationChannelConsole;

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

        NotificationChannel notificationChannel = new NotificationChannelConsole();
        ServiceLocator.setNotificationChannel(notificationChannel);
    }
}