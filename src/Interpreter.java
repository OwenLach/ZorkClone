
import java.util.*;

class Interpreter {
    
    private static Dungeon buildSampleDungeon() {
        Room entryRoom = new Room("Entry Room");
        entryRoom.setDesc("This is a sample description of the Entry Room");

        Room room2 = new Room("Room 2");
        room2.setDesc("This is a sample description of Room 2");
        
        Room room3 = new Room("Room 3");
        room3.setDesc("This is a sample description of Room 3");

        Room room4 = new Room("Room 4");
        room4.setDesc("This is a sample description of Room 4");

        Room room5 = new Room("Room 5");
        room5.setDesc("This is a sample description of Room 5");

        entryRoom.addExit(new Exit("n", entryRoom, room2));
        entryRoom.addExit(new Exit("e", entryRoom, room5));
        entryRoom.addExit(new Exit("s", entryRoom, room3));

        room3.addExit(new Exit("e", room3, room4));

        room5.addExit(new Exit("w", room5, entryRoom));
        
        Dungeon sampleDungeon = new Dungeon(entryRoom, "Sample Dungeon");
        sampleDungeon.add(room2);
        sampleDungeon.add(room3);
        sampleDungeon.add(room4);
        sampleDungeon.add(room5);

        return sampleDungeon;
    }

    public static void main(String args[]) {

        Dungeon dungeon = Interpreter.buildSampleDungeon();
        GameState.instance().initialize(dungeon);
        System.out.println(GameState.instance().getAdventurersCurrentRoom().describe());    
        
        Scanner scnr = new Scanner(System.in);
        String input;

        do {
            System.out.println();
            System.out.print("Enter directional character (q to quit) > ");
            input = scnr.nextLine();

            if (input.equals("q")) {
                continue;
            }

            Command commandRes = CommandFactory.instance().parse(input);
            if (commandRes == null) { 
                System.out.println("not a valid command."); 
                continue; 
            } else {
                //System.out.println("going " + input);
                System.out.println();
                System.out.println(commandRes.execute());
            } 
        } while (!input.equals("q"));

    }

}
