

class Command {

    private String dir;

    Command(String dir) {
        this.dir = dir;
    }

    String execute() {
        Room currRoom = GameState.instance().getAdventurersCurrentRoom();
        if (currRoom.leaveBy(this.dir) != null) {
           GameState.instance().setAdventurersCurrentRoom(currRoom.leaveBy(this.dir));
           return GameState.instance().getAdventurersCurrentRoom().describe();
        }
        else {
            return "cannot go " + dir;
        }
    }
}
