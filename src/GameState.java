
import java.util.*;

class GameState {

    private static GameState instance = null;
    private Dungeon dungeon = null;
    private Room currRoom = null; 
    private HashSet<Room> visited; 

    public static GameState instance() {
        if (GameState.instance == null) {
            GameState.instance = new GameState();
        } 
        return GameState.instance;
    }

    private GameState() {
        visited = new HashSet<>();
    }

    public void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.currRoom = this.dungeon.getEntry();
    }

    public Room getAdventurersCurrentRoom() {
        return this.currRoom;
    }

    public void setAdventurersCurrentRoom(Room room) {
        this.currRoom = room;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }

    void visit(Room room) {
        visited.add(room);
    }

    boolean hasBeenVisited(Room room) {
        return visited.contains(room);
    }
}
