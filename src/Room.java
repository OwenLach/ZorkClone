
import java.util.*;

public class Room {

    private String name; 
    private String desc = ""; 
    private Hashtable<String, Exit> exits; 
    
    public Room(String name){
        this.name = name;
        exits = new Hashtable<>();
    }
    
    public String getName() {
        return this.name;
    }

    public void setDesc(String desc) {
        this.desc = desc; 
    }

    String describe() {
        return name + "\n" + desc; 
    }

    public void addExit(Exit exit) {
        exits.put(exit.getDir(), exit);
    }
    
    Room leaveBy(String dir) {
        if (exits.get(dir) != null) {
            return exits.get(dir).getDest();
        }
        else {
            return null;
        }
    }
        
    public static void main(String[] args) {
        Room sampleEntryRoom = new Room("Sample Entry Room");
        sampleEntryRoom.setDesc("Empty room with little to no light. Only one exit can be seen.");
        String description = sampleEntryRoom.describe();
        System.out.println(description);

        Exit north = new Exit("north", sampleEntryRoom, new Room("Sample North Room"));
        sampleEntryRoom.addExit(north);
        System.out.println(north.describe());

        Exit east = new Exit("east", sampleEntryRoom, new Room("Sample East Room"));
        sampleEntryRoom.addExit(east);
        System.out.println(east.describe());

        Exit south = new Exit("south", sampleEntryRoom, new Room("Sample South Room"));
        sampleEntryRoom.addExit(south);
        System.out.println(south.describe());

        Exit west = new Exit("west", sampleEntryRoom, new Room("Sample West Room"));
        sampleEntryRoom.addExit(west);
        System.out.println(west.describe());
        
        Room northernRoom = sampleEntryRoom.leaveBy("north");
        System.out.println(northernRoom.getName());
    }
}
