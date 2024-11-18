
import java.util.*;
import java.io.*;

class GameState {

    private static GameState instance = null;
    private Dungeon dungeon = null;
    private Room currRoom = null; 
    private HashSet<Room> visited = null; 
    private ArrayList<Item> inventory = null; 
    private Hashtable<Room, HashSet<Item>> allRoomContents = null; 
    // private Hashtable<Room, NPC> npcRoomPair = null;
    // private boolean Verbose = true;
    
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

    /*
     public void setVerboseMode(boolean isVerbose) {
        this.Verbose = isVerbose;
     }

    public boolean isVerboseMode() {
        return this.Verbose;
    }

    public NPC getNPCFromRoom(Room room) {
    }

    public Item getItemFromNPCInventory(Stirng item) throw NoItemException {
    }

    void addNPCToRoomMethod() {
        this.npcRoomPair.put(Room, NPC);
    }
    */


    public void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Room getAdventurersCurrentRoom() {
        return this.currRoom;
    }

    public void setAdventurersCurrentRoom(Room room) {
        this.currRoom = room;
        // visit the curernt room in case teleport event
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

    int getInventoryWeight() {
        int weight = 0;

        for (Item item : this.inventory) {
           weight += item.getWeight(); 
        }

        return weight;
    }

    boolean isInPlayerInventory(Item item) {
        return this.inventory.contains(item);
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
            return returnItem;
        }
        catch(NoItemException e) {
            if (allRoomContents.get(this.currRoom) != null) { 
                for (Item item : allRoomContents.get(this.currRoom)) {
                    if (item != null) {
                        if (item.getPrimaryName().equals(name) || item.goesBy(name)) {
                            return item;
                        }
                    }
                }
            }
            throw new NoItemException();
        }
   }

    Item getItemFromInventoryNamed(String name) throws NoItemException {
        
        for (Item item : this.inventory) {
           if (item.getPrimaryName().equals(name) || item.goesBy(name)) {
               return item;
           }

        }

       throw new NoItemException();
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
        this.allRoomContents.get(room).remove(item);
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
            pw.println("Health:" + Player.instance().getHealthInt());
            pw.println("Score:" + Player.instance().getScore());
            pw.print("Used Score Items:");
            for (Item i : ScoreEvent.usedScoreItems) {
                pw.print(i.getPrimaryName() + ",");
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
        Hashtable<Room, HashSet<Item>> updateRooms = new Hashtable<Room, HashSet<Item>>(); 
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

                Room visitedRoom = this.dungeon.getRoom(roomName);
                this.visit(visitedRoom);
                
                updateRooms.put(visitedRoom, new HashSet<Item>());

                scnr.nextLine(); // thow out "beenHere=true"
                
                String contents = scnr.nextLine();
            
                if (contents.contains("Contents:")) {
                    contents = contents.substring(contents.indexOf(":") + 2);
                    String[] contentsArray = contents.split(",");
                    
                    for (String item : contentsArray) {
                         Item currItem = this.dungeon.getItem(item);
                         updateRooms.get(visitedRoom).add(currItem);
                    }

                    scnr.nextLine(); // throw out "---":
                }
            }

            scnr.nextLine(); // throw out "Adventurer: "

            String currentRoom = scnr.nextLine();
            currentRoom = currentRoom.substring(currentRoom.indexOf(":") + 2);
            this.setAdventurersCurrentRoom(this.getDungeon().getRoom(currentRoom)); 

            String[] inventoryStr = scnr.nextLine().split(" "); 
            
            if (inventoryStr.length == 1) {
            }
            else {
                for (String string : inventoryStr[1].split(",")) {
                    Item item = this.dungeon.getItem(string);
                    this.addToInventory(item);
                }
            }

            int health = Integer.parseInt(scnr.nextLine().split(":")[1]);
            Player.instance().setInitHealth(health);
            int score = Integer.parseInt(scnr.nextLine().split(":")[1]);
            Player.instance().setInitScore(score);
            String scoreItemsStr = scnr.nextLine().split(":")[1];

            for (String itemName : scoreItemsStr.split(",")) {
                Item item = this.dungeon.getItem(itemName);
                ScoreEvent.usedScoreItems.add(item);
                System.out.println("added " + itemName + " to used score items");
            }


            for (Map.Entry<Room, HashSet<Item>> roomContentsPair : updateRooms.entrySet()) {
                this.clearRoom(roomContentsPair.getKey()); //clears the room
                for (Item i : roomContentsPair.getValue()){
                    this.addItemToRoom(i, roomContentsPair.getKey()); 
                }
            }

        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    void clearRoom(Room room) {
        if (this.allRoomContents.get(room) != null && !this.allRoomContents.get(room).isEmpty()) {
            this.allRoomContents.get(room).clear();
        }
    }

    public class IllegalSaveFormatException extends Exception { }
}
