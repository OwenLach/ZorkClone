import java.util.Scanner;

class MovementCommand extends Command {
    private String dir; 

    MovementCommand(String dir) {
        this.dir = dir;
    }

    String execute() {
        GameState GS = GameState.instance();
        //creating the instance of the current 

        Room currRoom = GS.getAdventurersCurrentRoom();
        if (currRoom.leaveBy(this.dir) != null) {
           GS.setAdventurersCurrentRoom(currRoom.leaveBy(this.dir));
           
          //player loses 1 hunger every successsful movement 
           Player.instance().setHunger(1);
            
           return "\n" + GS.getAdventurersCurrentRoom().describe();
        }
        else {
            return "cannot go " + dir;
        }
    }

}


