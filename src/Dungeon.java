
import java.util.*;
import java.io.File;


public class Dungeon {
   
    private String title = "";
    private String filename = "";
    private Room entry = null; 
    private Hashtable<String, Room> rooms = new Hashtable<String, Room>();
    private Hashtable<String, Item> items = new Hashtable<String, Item>();

    public Dungeon(Room entry, String title) {
        this.title = title;
        this.entry = entry;
        this.add(entry);
    }

    public Dungeon (String filename) throws IllegalDungeonFormatException  {
        this.filename = filename;
        GameState GS = GameState.instance();
        

        // ----- Opening the file and making sure its in correct format -------
        try {
            File file = new File("../files/" + this.filename);
            Scanner scnr = new Scanner(file); 
            this.title = scnr.nextLine();
            String version =  scnr.nextLine();

            if (!version.equals("Zork III")) {
                throw new IllegalDungeonFormatException();
            }

            scnr.nextLine(); // skip "===" delimter
            
            // ---------- Hydrate Item objects ------------------
            System.out.println(scnr.nextLine()); // skip "Items:" delimiter 

            try {
            System.out.println("\n*********************");
                while(true) {
                    Item item = new Item(scnr);
                    //System.out.println("Adding item: " + item.getPrimaryName());
                    this.add(item);
                }
            }
            catch (NoItemException e) {
                System.out.println("\n*********************");
                System.out.println("From Dungeon.java, finished hydrating item objects");
                
                //for (Item item : this.items.values()) {
                 //   System.out.println("Items primary name: " + item.getPrimaryName());
                //}


            }

            // ---------- Hydrate all room object -------------------
            scnr.nextLine(); //skip "Rooms: " line
                             
            try {
                while (true) {
                    Room newRoom = new Room(scnr);

                    if (this.rooms.isEmpty()) {
                        this.entry = newRoom;
                    }

                    this.add(newRoom);

                }
            } catch (Room.NoRoomException e) {
            }
            
            // Initialize dungeon
            GS.initialize(this);
            scnr.nextLine(); // throwing out "Exits: " line
            

            // ------------- Hydrate all exit objects -------------------
            try {
                while (true) {
                   new Exit(scnr);
                }
            } 
            catch (Exit.NoExitException e) {
            }

        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public Room getEntry() { 
        return this.entry;  
    }
    
    public String getTitle() {
        return this.title;
    }

    public void add(Room room) {
        this.rooms.put(room.getName(), room);
    }

    public Room getRoom(String roomname) {
        return this.rooms.get(roomname);
    }

    public String getFilename() { return this.filename; }

    public Item getItem(String itemName) {
        return this.items.get(itemName);
    }

    public void add(Item item) {
        this.items.put(item.getPrimaryName(), item);
    }

    public class IllegalDungeonFormatException extends Exception { }
}
