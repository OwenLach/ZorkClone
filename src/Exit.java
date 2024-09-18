

public class Exit {

    private String dir; 
    private Room src;
    private Room dest;

    public Exit(String dir, Room src, Room dest) {
        this.dir = dir;
        this.src = src;
        this.dest = dest;
    }

    String describe() {
        return "You can go " + dir + " to " + dest.getName() + " from " + src.getName();
    }

    public String getDir() {
        return this.dir;
    }

    public Room getSrc() {
        return this.src;
    }

    public Room getDest() {
        return this.dest;
    }
}
