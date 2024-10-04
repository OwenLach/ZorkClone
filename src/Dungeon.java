
import java.util.*;
import java.io.File;


public class Dungeon {
   
    private String title = "";
    private String filename = "";
    private Room entry = null; 
    private Hashtable<String, Room> rooms = new Hashtable<String, Room>();
    
    public Dungeon(Room entry, String title) {
        this.title = title;
        this.entry = entry;
        this.add(entry);
    }

    public Dungeon (String filename) throws IllegalDungeonFormatException  {
        this.filename = filename;
        GameState GS = GameState.instance();
            
        try {
            Scanner scnr = new Scanner(new File(this.filename)); 
            this.title = scnr.nextLine();
            System.out.println("sample dungeon's title is: " + this.title);
            String version = scnr.nextLine();
            System.out.println("Version: " + version);

            if (!version.equals("Zork II")) {
                throw new IllegalDungeonFormatException();
            }

            scnr.nextLine(); // skip "===" delimter
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
                System.out.println("no more rooms to hydrate");
            }

           /*
            for (Room room : rooms.values()) {
                System.out.println(room.describe());
                System.out.println();
            }
            */
            
            GS.initialize(this);
            
            scnr.nextLine(); // throwing out "Exits: " line

            try {
                while (true) {
                   new Exit(scnr);
                }
            } 
            catch (Exit.NoExitException e) {
            }

            System.out.println("finished hydrating exits\n***************");
            for (Room room : rooms.values()) {
                System.out.println(room.describe());
                System.out.println();
            }
            System.out.println("***************");
        } 

        catch (Exception e) {
            e.printStackTrace();
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

    public class IllegalDungeonFormatException extends Exception { }
}
