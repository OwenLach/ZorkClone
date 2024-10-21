
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
        //this.currRoom = this.dungeon.getEntry();
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

    ArrayList<Item> getInventory() { return this.inventory; }

    void addToInventory(Item item) {}

    void removeFromInventory(Item item) {}

    Item getItemInVicinityNamed(String name) { return null; }

    Item getItemFromInventoryNamed(String name) { return null; }

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

    void removeItemFromRoom(Item item, Room room) {}

    void store(String saveName) {

        try {
            PrintWriter pw = new PrintWriter(new File(saveName));
            pw.println("Zork II save data");
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
                pw.println(room.getName());
                pw.println("beenHere=true");
                pw.println("---");

            }

            pw.println("===");
            pw.println("Current room: " + this.getAdventurersCurrentRoom().getName());
            
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

            scnr.nextLine(); // throw out "zork II save data" line
                            
            File dungeonFile = new File(scnr.nextLine());
            this.setDungeon(new Dungeon(dungeonFile.getName()));
        
            scnr.nextLine(); //throw out "room states:" line
            
            while (true) {
                String roomName = scnr.nextLine();
                if (roomName.equals("===")) { break; }
                
                Room visitedRoom = this.dungeon.getRoom(roomName);
                this.visit(visitedRoom);

                scnr.nextLine(); // thow out "beenHere=true"
                scnr.nextLine(); // throw out "---":
            }


            String currentRoom = scnr.nextLine();
            currentRoom = currentRoom.substring(currentRoom.indexOf(":") + 2);
            this.setAdventurersCurrentRoom(this.getDungeon().getRoom(currentRoom)); 

        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public class IllegalSaveFormatException extends Exception { }
}
