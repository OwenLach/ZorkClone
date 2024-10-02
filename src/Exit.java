

public class Exit {

    private String dir = null; 
    private Room src = null;
    private Room dest = null;

    public Exit(String dir, Room src, Room dest) {
        this.dir = dir;
        this.src = src;
        this.dest = dest;
    }

    String describe() {
        return "You can go " + dir + " to " + dest.getName();
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

    public class NoExitException extends Exception { }
    
}
