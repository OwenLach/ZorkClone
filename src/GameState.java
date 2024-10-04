
import java.util.*;
import java.io.*;

class GameState {

    private static GameState instance = null;
    private Dungeon dungeon = null;
    private Room currRoom = null; 
    private HashSet<Room> visited = null; 

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

    public void setDungeon(Dungeon d) {

    }

    void visit(Room room) {
        visited.add(room);
    }

    boolean hasBeenVisited(Room room) {
        return visited.contains(room);
    }

    void store(String saveName) {

        try {
            PrintWriter pw = new PrintWriter(new File(saveName));
            pw.println("Zork II save data");
            String path; 

            try {
                File file = new File(this.dungeon.getFilename());
                path = file.getAbsolutePath();
                System.out.println("Full path : " + path);
                pw.println("Dungeon file: " + path);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            pw.println("Room states:");
            
            for (Room room : visited) {
                pw.println(room.getName());
                pw.println("beenHere=true");
                pw.println("---");

            }

            pw.println("===");
            pw.println("Current room: " + this.getAdventurersCurrentRoom().getName());
            
            pw.flush();
            pw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void restore(String filename){
    }

    public class IllegalSaveFormatException extends Exception { }
}
