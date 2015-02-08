package ro.scene.hq.holidays;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CommandRegistry {

    private static Map<String, Command> commands = new LinkedHashMap<>();

    public static <T extends Command> void register(Class<T> clazz) {
        try {
            T command = clazz.newInstance();
            commands.put(command.getShortName(), command);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void execute(List<String> args) {
        if (args.isEmpty()) {
            showUsage();
            throw new IllegalStateException("No action provided");
        }

        for (Command command : commands.values()) {
            if (command.getShortName().equals(args.get(0))) {
                command.execute(args);
                return;
            }
        }

        showUsage();
    }

    private static void showUsage() {
        System.out.println("Usage: holidays action ARGS");
        System.out.println("");
        System.out.println("Actions: ");
        for (Command command : CommandRegistry.commands.values()) {
            System.out.println("\t" + command.getShortName() + "\t" + command.getUsageLine());
        }
    }
}
