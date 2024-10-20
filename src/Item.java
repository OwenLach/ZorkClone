import java.util.*;

public class Item {

    private String primaryName = ""; 
    private int weight = 0;
    private Hashtable<String,String> messages = new Hashtable<String, String>();
    private HashSet<String> aliases = new HashSet<String>();

    public Item(Scanner scnr) {
    }

    public boolean goesBy(String name) {
    }

    public String getPrimaryName() { return this.primaryName; }

    public String getMessageForVerb(String verb) {
    }

    public String toString() {
    }

    public int getWeight() { return weight; }


    public static void main(String args[]) {
        //this main class is for testing 
        //make a test file in the specified format
        //read in from file 
        //print out the inst vars to make sure its hydrated correctly
        //
        //maybe use .split(",") for the name and alieases
        //
        //for verb, maybe use .split(":") 

    }

}

