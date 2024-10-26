
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
        String command = commandParts[0];
        
        Hashtable<String, Item> itemActions = new Hashtable<String, Item>();
        for (Item item : GameState.instance().getDungeon().getAllItems()) {
            for (String action : item.getAllItemActions()) {
                itemActions.put(action, item);
            }
        }
    
        HashSet<String> movementCommands= new HashSet<>(List.of("n", "e", "s", "w", "u", "d"));

        if (movementCommands.contains(command)) { 
            return new MovementCommand(command);
        }
        else if (command.equals("take")) {
            try {
                if (commandParts.length > 1) {
                    String itemName = commandParts[1];
                    return new TakeCommand(itemName);
                } else {
                    throw new NoItemException();
                }
            }
            catch (NoItemException e) {
                System.out.println(command + " what?");
                return null;
            }
        }
        else if (command.equals("drop")) {
            try {
                if (commandParts.length > 1) {
                    String itemName = commandParts[1];
                    return new DropCommand(itemName);
                } else {
                    throw new NoItemException();
                }
            }
            catch (NoItemException e) {
                System.out.println(command + " what?");
                return null;
            }
        }
        else if (command.equals("look")) {
           return new LookCommand();
        }
        else if (command.equals("i")) {
           return new InventoryCommand();
        }
        else if (command.equals("save")) {
            return new SaveCommand();
        }
        else {
            for (String action : itemActions.keySet()) {
                if ((commandParts[0].equals(action) && 
                    (commandParts[1].equals(itemActions.get(action).getPrimaryName()) ||
                    itemActions.get(action).goesBy(commandParts[1]))))
                {
                    try {
                        Item item = GameState.instance().getItemInVicinityNamed(itemActions.get(action).getPrimaryName()); 
                        return new ItemSpecificCommand(itemActions.get(action), action);
                    }
                    catch (NoItemException e) {
                        return new UnknownCommand("\"" + commandString + "\"");
                    }
                }
            }

            return new UnknownCommand(commandString);
        }

    }

}
