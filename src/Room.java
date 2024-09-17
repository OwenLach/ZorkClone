
public class Room {

    private String name; 
    private String desc = ""; 
    
    public Room(String name){
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public void setDesc(String desc) {
        this.desc = desc; 
    }

    String describe() {
        return name + "\n" + desc; 
        //add exits description
    }

    // Room leaveBy(String dir) {}
    
    // public void addExit(Exit exit) {}
    
    public static void main(String[] args) {
        Room test = new Room("Test Room");
        test.setDesc("Empty room with little to no light. Only one exit can be seen.");
        String description = test.describe();
        System.out.println(description);
    }
}
