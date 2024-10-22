import java.util.Scanner;

class MovementCommand extends Command {
    private String dir; 
    
    MovementCommand(String dir) {
        this.dir = dir;
    }

    String execute() {
        GameState GS = GameState.instance();

        Room currRoom = GS.getAdventurersCurrentRoom();
        if (currRoom.leaveBy(this.dir) != null) {
           GS.setAdventurersCurrentRoom(currRoom.leaveBy(this.dir));

           return "\n" + GS.getAdventurersCurrentRoom().describe();
        }
        else {
            return "cannot go " + dir;
        }
    }

}


