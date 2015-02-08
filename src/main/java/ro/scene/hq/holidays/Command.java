package ro.scene.hq.holidays;

import java.util.List;

public interface Command {
    
    String getShortName();

    String getUsageLine();

    void execute(List<String> args);
}