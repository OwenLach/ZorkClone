
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
        String[] commandParts = commandString.split(" "); 
        //holds the command & its arguments separately

        String command = commandParts[0];
        //holds just the command from the split commandString

        HashSet<String> allValidCommands= new HashSet<>(
                List.of("n", "e", "s", "w", "u", "d", "save", "look", "take", "drop", "i")
                );

        HashSet<String> movementCommands= new HashSet<>(
                List.of("n", "e", "s", "w", "u", "d")
                );
        

        if (!allValidCommands.contains(command)) {
                return new UnknownCommand(command); 
        }
        else { 
            if (movementCommands.contains(command)) { 
                return new MovementCommand(command);
            }
            
            //checks if the command is either take or drop & sends the item name to
            //the respective command class
            try {
                if (command.equals("take")) {
                    
                    if (commandParts.length > 1) {
                        String itemName = commandParts[1];
                        return new TakeCommand(itemName);
                    } else {
                        throw new NoItemException();
                    }
                    //throw exception eventuallyyyyyyyyyyyy

                } else if (command.equals("drop")) {
                    
                    if (commandParts.length > 1) {
                        String itemName = commandParts[1];
                        return new DropCommand(itemName);
                    } else {
                        throw new NoItemException();
                    }
                }
            } catch (NoItemException e) {
                System.out.println(command + " what?");
                return null;
            }

            //return the simpler commands without arguments
           } if (command.equals("look")) {
               return new LookCommand();
           } else if (command.equals("i")) {
               return new InventoryCommand();
           } else {
               return new SaveCommand();
           }
               
        }   
}
