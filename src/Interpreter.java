
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
        System.out.println(dungeon.getEntry().describe());
        Room next = dungeon.getEntry().leaveBy("s");
        System.out.println("going south");
        System.out.println(next.describe());
        next = next.leaveBy("e");
        System.out.println("going east");
        System.out.println(next.describe());
        next = next.leaveBy("e");
        System.out.println("going east");
        if (next == null) {System.out.println("Cant go that way");}
        

        Scanner scnr = new Scanner(System.in);
        char inputChar = ' '; 

        while(inputChar != 'q') {
            System.out.println("Enter input character (q to quit): ");
            String input = scnr.nextLine();

            if(!input.isEmpty()) {
                inputChar = input.charAt(0);        

                if (inputChar == 'q') {
                    System.out.println("quitting"); 
                    continue;
                }
                System.out.println("you entered " + inputChar);
            }
            else {
                continue;
            }
        }
    }


}
