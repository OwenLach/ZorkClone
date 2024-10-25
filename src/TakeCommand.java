import java.util.HashSet;

class TakeCommand extends Command {
    private String itemName;

    TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    String execute() {
        GameState GS = GameState.instance();
        Room currentRoom = GS.getAdventurersCurrentRoom();
        Item item = GS.getAdventurersCurrentRoom().getItemNamed(itemName);
        
        HashSet<Item> roomContents = currentRoom.getContents();
        HashSet<Item> removeFromRoom = new HashSet<Item>(); 
        //code for if all items are being taken
        if (itemName.equals("all")) { 

            if (roomContents == null || roomContents.isEmpty()) {
                return "There is nothing to take.";
            }
            for (Item i : roomContents) {
                GS.addToInventory(i);
                removeFromRoom.add(i);
            }
            for (Item i : removeFromRoom) {
                GS.removeItemFromRoom(i, currentRoom);
            }
        }

        //checks if inventory already has the item
        if (GS.getInventory().contains(item)) {
            return "You already have " + itemName;
        }

        //checks if the room even has the item
        if (!currentRoom.getContents().contains(item)) {
            return "There is no " + itemName + " here.";
        }
        //adds the item to the inventory & removes it from the room.
        GS.addToInventory(item);
        GS.removeItemFromRoom(item, currentRoom);
        return itemName + " taken.";
         
    }

}
