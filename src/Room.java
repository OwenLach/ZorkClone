
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
        if (lineAfterRoomName.contains("Contents:")){
            //get all items that the room has  
            String contentsString = lineAfterRoomName.split(" ")[1];
            //split items into array by ","
            String[] splitContentsString = contentsString.split(",");
            
            for (String s : splitContentsString) {
                // get item from the items hashtable in Dunegon class
                Item item = GS.getDungeon().getItem(s);
                // add item to the GameState "allRoomContents" Hashtable
                this.add(item);
                //GS.addItemToRoom(item, this);
            }

            System.out.println(this.name + " contains: ");
            for (Item item : GS.getItemsInRoom(this)) {
               System.out.println("       " + item); 
            }

            System.out.println();
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


    HashSet<Item> getContents() {
        return null;
    }

    Item getItemNamed(String name) {
        return null;
    }

    void add(Item item) {
        GameState.instance().addItemToRoom(item, this);       
    }

    void remove(Item item){
        GameState.instance().removeItemFromRoom(item, this);       
    }

    public class NoRoomException extends Exception { }
    
 }
