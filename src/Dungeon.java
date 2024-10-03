
import java.util.*;
import java.io.File;


public class Dungeon {
   
    private String title = "";
    private Room entry = null; 
    private Hashtable<String, Room> rooms = new Hashtable<String, Room>();
    
    public Dungeon(Room entry, String title) {
        this.title = title;
        this.entry = entry;
        this.add(entry);
    }

    public Dungeon (String filename) throws IllegalDungeonFormatException  {
        try {
            Scanner scnr = new Scanner(new File(filename));
            this.title = scnr.nextLine();
            System.out.println("sample dungeon's title is: " + this.title);
            String version = scnr.nextLine();
            System.out.println("Version: " + version);

            if (!version.equals("Zork II")) {
                throw new IllegalDungeonFormatException();
            }
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
