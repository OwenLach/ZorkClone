
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
        GameState GS = GameState.instance();
        exits = new Hashtable<>();
        this.name = scnr.nextLine(); 

        if (this.name.equals("===")) {
            throw new NoRoomException();
        }
        
        String lineAfterRoomName = scnr.nextLine();
        if (lineAfterRoomName.contains("Contents:")) {
            //get all items that the room has  
            String contentsString = lineAfterRoomName.split(" ")[1];
            //split items into array by ","
            String[] splitContentsString = contentsString.split(",");
            
            for (String s : splitContentsString) {
                // get item from the items hashtable in Dunegon class
                Item item = this.getItemNamed(s);
                // add item to the GameState "allRoomContents" Hashtable
                this.add(item);
            }
            
            String descPart = scnr.nextLine();
            while(!descPart.equals("---")) {
                this.desc += (descPart + " \n");
                descPart = scnr.nextLine();
            }
        }
        else {
            String descPart = lineAfterRoomName;           
            while(!descPart.equals("---")) {
                this.desc += (descPart + " \n");
                descPart = scnr.nextLine();
            }
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

            if (this.getContents() != null && !this.getContents().isEmpty()) {
                for(Item item : this.getContents()) {
                   description += "\nThere is a " + item.getPrimaryName() + " here.";
                }
            }

            description += "\nExits:";
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
    
    String lookAtRoom() {
        String description = "\n"; 
        description += this.name + "\n";
        description += this.desc;
        
        if (this.getContents() != null) {
            for (Item item : this.getContents()) {
                description += "There is a " + item.getPrimaryName() + "\n";
            }
        }

        description += "\nExits:";
        for (Exit exit : this.exits.values()) {
            description += "\n" + exit.describe();
        }
        
        return description;



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


    HashSet<Item> getContents() {
        return GameState.instance().getItemsInRoom(this);
    }

    Item getItemNamed(String name) {
        return GameState.instance().getDungeon().getItem(name);
        
    }

    void add(Item item) {
        GameState.instance().addItemToRoom(item, this);       
    }

    void remove(Item item){
        GameState.instance().removeItemFromRoom(item, this);       
    }

    public class NoRoomException extends Exception { }
    
 }
