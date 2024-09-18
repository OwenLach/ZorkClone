
import java.util.*;

class CommandFactory {
    
    private static CommandFactory instance = null;

    public static CommandFactory instance() {
        if (CommandFactory.instance == null) {
            CommandFactory.instance = new CommandFactory();
        }
        return CommandFactory.instance;
    }

    private CommandFactory() {
    }

    Command parse(String commandString) {
        HashSet<String> validCommands= new HashSet<>(List.of("n", "e", "s", "w", "u", "d"));

        if (!validCommands.contains(commandString)) { return null; }
        else { return new Command(commandString); }
    }
    
}
