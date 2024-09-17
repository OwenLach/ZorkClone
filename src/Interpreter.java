
import java.util.*;

class Interpreter {
    
    private static Dungeon buildSampleDungeon() {
        Room entryRoom = new Room("entryRoom");
        entryRoom.setDesc("This is a sample description of the Entry Room");

        Dungeon sampleDungeon = new Dungeon(entryRoom, "Sample Dungeon");

        Room r = sampleDungeon.getRoom("entryRoom");
        System.out.println(r.describe());
        
        return sampleDungeon;
    }

    public static void main(String args[]) {

        Dungeon dungeon = Interpreter.buildSampleDungeon();


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
