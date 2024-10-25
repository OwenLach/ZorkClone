import java.util.*;

class TakeCommand extends Command {
    private String itemName;

    TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    String execute() {
        GameState GS = GameState.instance();
        Room currentRoom = GS.getAdventurersCurrentRoom();
        HashSet<Item> roomContents = currentRoom.getContents();
        HashSet<Item> removeFromRoom = new HashSet<Item>(); 

        //code for if all items are being taken
        if (itemName.equals("all")) { 
            String returnDesc = "";

            if (roomContents == null || roomContents.isEmpty()) {
                return "There is nothing to take.";
            }
            for (Item i : roomContents) {
                if (i.getWeight() + GS.getInventoryWeight() > 40) {
                    return returnDesc + "\nWeight limit reached, can't take " + i.getPrimaryName();
                }
                GS.addToInventory(i);
                returnDesc += "-" + i.getPrimaryName() + " taken\n";
                removeFromRoom.add(i);
            }
            for (Item i : removeFromRoom) {
                GS.removeItemFromRoom(i, currentRoom);
            }
            
            returnDesc += "\nAll Items taken";
            return returnDesc;
        }

        // check if player already has in inventory
        ArrayList<Item> currInventory = GS.getInventory();
        if (currInventory != null) {
            for (Item item : currInventory) {
                if (item != null) {
                    if (item.getPrimaryName().equals(itemName) || item.goesBy(itemName)) {
                        return itemName + " is already in inventory";
                    }
                }
            }
        }

        // check room for item
        HashSet<Item> currRoomContents = currentRoom.getContents();
        if (currRoomContents != null) {
            for (Item item : currRoomContents) {
                if (item != null) {
                    if (item.getPrimaryName().equals(itemName) || item.goesBy(itemName)) {
                        if (GS.getInventoryWeight() + item.getWeight() > 40) {
                           return "\nWeight limit reached, can't take " + item.getPrimaryName();
                        }
                        else {
                            GS.addToInventory(item);
                            GS.removeItemFromRoom(item, currentRoom);
                            return "Added " + itemName + " to inventory";
                        }
                    }
                }
            }
        }
        
        return "Can't add " + itemName + " to inventory";
        /*
            //checks if the room even has the item
            if (!currentRoom.getContents().contains(item)) {
                return "There is no " + itemName + " here.";
            }
            //adds the item to the inventory & removes it from the room.
            GS.addToInventory(item);
            GS.removeItemFromRoom(item, currentRoom);
            return itemName + " taken.";
        */ 
    }

}
