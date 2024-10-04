
import java.util.*;

class Interpreter {
    
    private static Dungeon buildSampleDungeon() {
        Room entryRoom = new Room("Entry Room");
        entryRoom.setDesc("This is the entry to the castle. Massive pillars are on your sides and an open gate is to your front. ");

        Room tavern = new Room("Tavern");
        tavern.setDesc("A crowded room full of drunk people. It's extremely loud and smells like ale and body odor.");
        
        Room barracks = new Room("Barracks");
        barracks.setDesc("This room is filled with knight equipment. Swords, sheilds, and axes lline the walls.");

        Room throneRoom = new Room("Throne Room");
        throneRoom.setDesc("A massive throne stands before you. A crown seated on it. No one is in sight.");

        Room frontYard = new Room("Front Yard");
        frontYard.setDesc("A beautiul garden with lush flowers and bushes. It seems quite livley to the north.");

        entryRoom.addExit(new Exit("s", entryRoom, frontYard));
        entryRoom.addExit(new Exit("n", entryRoom, tavern));

        frontYard.addExit(new Exit("n", frontYard, entryRoom));

        tavern.addExit(new Exit("e", tavern, barracks));

        barracks.addExit(new Exit("s", barracks, throneRoom));
        
        throneRoom.addExit(new Exit("n", throneRoom, barracks));
        throneRoom.addExit(new Exit("w", throneRoom, entryRoom));

        Dungeon sampleDungeon = new Dungeon(entryRoom, "Sample Dungeon");
        sampleDungeon.add(frontYard);
        sampleDungeon.add(tavern);
        sampleDungeon.add(barracks);
        sampleDungeon.add(throneRoom);

        return sampleDungeon;
    }

    // "Usage: Interpreter zorkFile.zork|saveFile.sav."
    public static void main(String args[]) {
        
        if (args.length == 0) {
            System.out.println("Usage: Interpreter zorkFile.zork|saveFile.sav.");
            return;
        }

        Dungeon testDungeon;

        try {
            testDungeon = new Dungeon(args[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //Dungeon dungeon = Interpreter.buildSampleDungeon();
        //GameState.instance().initialize(dungeon);
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
                System.out.println();
                System.out.println(commandRes.execute());
            } 
        } while (!input.equals("q"));
    }
}
