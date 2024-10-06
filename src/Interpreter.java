
import java.util.*;

class Interpreter {
    
    public static void main(String args[]) {
        GameState GS = GameState.instance();

        if (args.length == 0) {
            System.out.println("Usage: Interpreter zorkFile.zork|saveFile.sav.");
            return;
        }
        
        if (args[0].indexOf(".zork") != -1) {
            try {
               GS.setDungeon(new Dungeon(args[0]));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (args[0].indexOf(".sav") != -1) {
            GS.restore(args[0]);
        }
        else { 
            System.out.println("Enter valid file type");
            return;
        }

        /*    
            Dungeon testDungeon;

            try {
                testDungeon = new Dungeon(args[0]);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            //Dungeon dungeon = Interpreter.buildSampleDungeon();
            //GameState.instance().initialize(dungeon);
        */
        System.out.println("-------------------------------------");
        System.out.println(GS.getAdventurersCurrentRoom().describe());    
        
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
