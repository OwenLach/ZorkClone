

class Command {

    private String commandStr;

    Command(String command) {
        this.commandStr = command;
    }

    String execute() {
        GameState GS = GameState.instance(); 

        if (commandStr.equals("save")) {
            GS.store("asdfljsaf.sav");
            return "saved game";
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
