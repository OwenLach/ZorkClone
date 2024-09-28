
import java.util.*;

public class Dungeon {
   
    private String title = "";
    private Room entry; 
    private Hashtable<String, Room> rooms = new Hashtable<String, Room>();
    
    public Dungeon(Room entry, String title) {
        this.title = title;
        this.entry = entry;
        this.add(entry);
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
}
