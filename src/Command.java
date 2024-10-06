
import java.util.Scanner;

class Command {

    private String commandStr;

    Command(String command) {
        this.commandStr = command;
    }

    String execute() {
        GameState GS = GameState.instance(); 
        Scanner scnr = new Scanner(System.in);

        if (commandStr.equals("save")) {
            String filename = "";

            do {
                System.out.println("Enter save file name: ");
                filename = scnr.nextLine();     
            } while (filename.equals(""));

            GS.store(filename + ".sav");

            return "\ngame saved successfully";
        }
        else {
            Room currRoom = GS.getAdventurersCurrentRoom();
            if (currRoom.leaveBy(this.commandStr) != null) {
               GS.setAdventurersCurrentRoom(currRoom.leaveBy(this.commandStr));
               return GS.getAdventurersCurrentRoom().describe();
            }
            else {
                return "cannot go " + commandStr;
            }
        }
        
    }
}
