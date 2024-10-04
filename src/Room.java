
import java.util.*;

public class Room {

    private String name = null; 
    private String desc = ""; 
    private Hashtable<String, Exit> exits = null; 
    
    public Room(String name){
        this.name = name;
        exits = new Hashtable<>();
    }

    public Room(Scanner scnr) throws NoRoomException {
        exits = new Hashtable<>();
        this.name = scnr.nextLine(); 

        if (this.name.equals("===")) {
            throw new NoRoomException();
        }

        String descPart = scnr.nextLine();
        
        while(!descPart.equals("---")) {
            this.desc += (descPart + " \n");
            descPart = scnr.nextLine();
        }
    }
    
    public String getName() {
        return this.name;
    }

    public void setDesc(String desc) {
        this.desc = desc; 
    }

    String describe() {
        if (!GameState.instance().hasBeenVisited(this)) {
            String description = name + "\n" + desc;

            for (Exit exit : this.exits.values()) {
                description += "\n" + exit.describe();
            }

            GameState.instance().visit(this);

            return description;

        } 
        else {
            return name;
        }
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

    public class NoRoomException extends Exception { }
    
 }
