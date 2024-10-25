

class DropCommand extends Command {
    
    private String itemName; 

    DropCommand(String itemName) {
        this.itemName = itemName;
    }

    String execute() {
        
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Item item;

        try {
            item = GameState.instance().getItemFromInventoryNamed(itemName);
        } catch (NoItemException e) {
           return "EXCEPTION: You don't have a " + itemName;
        }


        //checks if item is in inv
        if (!GameState.instance().getInventory().contains(item)) {
            return "You don't have a " + itemName;

        //drops item from inv & adds it to room
        } else {
            GameState.instance().removeFromInventory(item);
            GameState.instance().addItemToRoom(item, currentRoom);
            return "Dropped " + itemName;
        }
        
    
    }

}
