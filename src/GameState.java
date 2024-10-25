
import java.util.*;
import java.io.*;

class GameState {

    private static GameState instance = null;
    private Dungeon dungeon = null;
    private Room currRoom = null; 
    private HashSet<Room> visited = null; 
    private ArrayList<Item> inventory = null; 
    private Hashtable<Room, HashSet<Item>> allRoomContents = null; 

    public static GameState instance() {
        if (GameState.instance == null) {
            GameState.instance = new GameState();
        } 
        return GameState.instance;
    }

    private GameState() {
        visited = new HashSet<>();
        inventory = new ArrayList<Item>();
        allRoomContents = new Hashtable<Room, HashSet<Item>>();
    }

    public void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Room getAdventurersCurrentRoom() {
        return this.currRoom;
    }

    public void setAdventurersCurrentRoom(Room room) {
        this.currRoom = room;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }

    public void setDungeon(Dungeon d) {
        this.dungeon = d;
    }

    void visit(Room room) {
        visited.add(room);
    }

    boolean hasBeenVisited(Room room) {
        return visited.contains(room);
    }

    ArrayList<Item> getInventory() { 
        return this.inventory;
    }

    void addToInventory(Item item) {
        this.inventory.add(item);
    }

    void removeFromInventory(Item item) {
        this.inventory.remove(item);
    }

    Item getItemInVicinityNamed(String name) throws NoItemException { 

        try {
            Item returnItem = this.getItemFromInventoryNamed(name);
            System.out.println("found item in inventory");
            return returnItem;
        }
        catch(NoItemException e) {
            System.out.println("Could not get item: " + name + 
                                " from inventory, searching room");
            
            for (Item item : allRoomContents.get(this.currRoom)) {
                if (item.getPrimaryName() == name || item.goesBy(name)) {
                    return item;
                }
            }
            
            System.out.println("Item not found in room either");
            throw new NoItemException();
        }

        
        /*
            // look through player inventroy and room items
            // throw noItemException if not valid
            for (Item item : inventory) { //checks items in inventory
                if (allRoomContents.containsKey(item) && inventory.contains(name)) {
                    //if Item is in room or in inventory or other solution 
                    //(if (allRoomContents.containsKey(item.getPrimaryName()) 
                    //&& inventory.contains(name)))?
                    return item;
                }else{
                    throw new NoItemException();
                }
            }
            return null;
        */
    }

    Item getItemFromInventoryNamed(String name) throws NoItemException {
        
        for (Item item : this.inventory) {
           if (item.getPrimaryName() == name || item.goesBy(name)) {
               return item;
           }

        }


       throw new NoItemException();
        //inventory = <apple, orange, banana>
        /* 
            for(Item item : inventory){
            // item is currently apple
            // name = banana
               if (inventory.contains(name)){
                  return item;
               }else{
                  throw new NoItemException();
               }
            }
            return null;
        */
        
        // only look through player inventory
        // throw noItemException if not valid
    }

    HashSet<Item> getItemsInRoom(Room room) {
        return this.allRoomContents.get(room);
    }

    void addItemToRoom(Item item, Room room) {
        // room has nothing in it yet
        if (allRoomContents.get(room) == null) {
            HashSet<Item> itemsSet = new HashSet<Item>();
            itemsSet.add(item);
            allRoomContents.put(room, itemsSet); 
        }
        // other wise, add item to already existing hashset
        else {
            allRoomContents.get(room).add(item);
        }
    }

    void removeItemFromRoom(Item item, Room room) {

       //if(allRoomContents.get(room) != null) {
    	//	allRoomContents.get(room).remove(item);

    	if (allRoomContents.get(room) != null) {
            boolean removeItem = allRoomContents.get(room).remove(item);

            if (removeItem) {
            	allRoomContents.get(room).remove(item);
            } else {
                System.out.println("Item " + item.getPrimaryName() + " not found in the room.");
            }
        } else {
            System.out.println("Room has no items.");
        }
    }

    void store(String saveName) {

        try {
            PrintWriter pw = new PrintWriter(new File(saveName));
            pw.println("Zork III save data");
            String path; 
            
            // get dungeon zork file's full path and store in save file
            try {
                File file = new File(this.dungeon.getFilename());
                path = file.getAbsolutePath();
                pw.println("Dungeon file: " + path);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
             
            pw.println("Room states:");
            for (Room room : visited) {
                pw.println(room.getName() + ":");
                pw.println("beenHere=true");
                
                HashSet<Item> roomContents = this.allRoomContents.get(room);
                if (roomContents != null && !roomContents.isEmpty()) {
                    pw.print("Contents: ");

                    // if works, remove last comma
                    for (Item item : roomContents) {
                        pw.print(item.getPrimaryName() + ",");
                    }
                    pw.println();
                }

                pw.println("---");

            }

            pw.println("===");
            pw.println("Adventurer:");
            pw.println("Current room: " + this.getAdventurersCurrentRoom().getName());
            pw.print("Inventory: ");
            for (Item item : this.inventory) {
                pw.print(item.getPrimaryName() + ",");
            }
            pw.println(); 
            pw.flush();
            pw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void restore(String filename){
        
        try {
            //open save file w/ scanner
            File file = new File(filename);
            Scanner scnr = new Scanner(file);

            scnr.nextLine(); // throw out "zork III save data" line
                            
            File dungeonFile = new File(scnr.nextLine());
            this.setDungeon(new Dungeon(dungeonFile.getName()));
        
            scnr.nextLine(); //throw out "room states:" line
            
            while (true) {
                String roomName = scnr.nextLine();
                if (roomName.equals("===")) { break; }
                roomName = roomName.substring(0, roomName.length() - 1);
                System.out.println("Room name is - " + roomName);

                //if (roomName.equals("===")) { break; }
                
                Room visitedRoom = this.dungeon.getRoom(roomName);
                this.visit(visitedRoom);

                scnr.nextLine(); // thow out "beenHere=true"
                
                String contents = scnr.nextLine();
            
                if (contents.contains("Contents:")) {
                    contents = contents.substring(contents.indexOf(":") + 2);
                    // split contetns into arary
                    //System.out.println("Contents of " + visitedRoom.getName() + " are: " + contents);
                    String[] contentsArray = contents.split(",");
                    
                    for (String item : contentsArray) {
                         Item currItem = this.dungeon.getItem(item);
                         // remove when done
                         //System.out.println("adding " + item + " to " + visitedRoom.getName());
                         this.addItemToRoom(currItem, visitedRoom);
                         
                    }
                    //System.out.println("finshed hydrating room");
                    scnr.nextLine(); // throw out "---":
                }
                else{
                    //System.out.println("Room had no contents");
                    //System.out.println("throwing out " + contents);
                }
                
            }

            scnr.nextLine(); // throw out "Adventurer: "

            String currentRoom = scnr.nextLine();
            currentRoom = currentRoom.substring(currentRoom.indexOf(":") + 2);
            this.setAdventurersCurrentRoom(this.getDungeon().getRoom(currentRoom)); 

            String[] inventoryStr = scnr.nextLine().split(" "); 
            
            if (inventoryStr.length == 1) {
                //System.out.println("Empty Inventory");
            }
            else {
                for (String string : inventoryStr[1].split(",")) {
                    Item item = this.dungeon.getItem(string);
                    // delete later
                    //System.out.println("adding " + item.getPrimaryName() + " to inventury");
                    this.addToInventory(item);
                }
            }


        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public class IllegalSaveFormatException extends Exception { }
}
