
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
        HashSet<String> allValidCommands= new HashSet<>(
                List.of("n", "e", "s", "w", "u", "d", "save")
                );

        HashSet<String> movementCommands= new HashSet<>(
                List.of("n", "e", "s", "w", "u", "d")
                );
        
        String saveComand = "save";

        if (!allValidCommands.contains(commandString)) {
                return new UnknownCommand(commandString); 
        }
        else { 
            if (movementCommands.contains(commandString)) { 
                return new MovementCommand(commandString);
            }
            else {
                return new SaveCommand();
            }

        }
    }
    
}
