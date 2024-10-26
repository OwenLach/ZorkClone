import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.FileReader;

public class Item {
    
    private String primaryName = ""; 
    private int weight ;
    private Hashtable<String,String> messages = new Hashtable<String, String>();
    private HashSet<String> aliases = new HashSet<String>();

    public Item(Scanner scnr) throws NoItemException{

        String itemNames = scnr.nextLine();

        if (itemNames.equals("===")){
            throw new NoItemException();
        }
        
        String[] splitNames = itemNames.split(",");
        this.primaryName = splitNames[0];
        
        for(int i = 1; i < splitNames.length; i++) {
            this.aliases.add(splitNames[i]);
        }
        
        String itemWeight = scnr.nextLine();
        this.weight = Integer.parseInt(itemWeight);

        String itemAction;
        boolean possibleAction = false; //used to verify if item is able to enact action
        while(scnr.hasNextLine() && !(itemAction = scnr.nextLine().trim()).equals("---"))
        {
            possibleAction = true;
            String[] splitAction = itemAction.split(":");

            // checks to se if when line is split at ":" there is only 1
            // ":" verifying there is only two halves, the key(left) and
            // value(right) 
            if (splitAction.length == 2) { 
                // set's what's before ":" as action name
                String action = splitAction[0];

                // what's after ":" as action response
                String response = splitAction[1]; 

                //adds the action and action response to hashtable
                messages.put(action, response); 
            }
        }
        //checks to see if item has no action
        if(!possibleAction) { 
        }
	}

	public boolean goesBy(String name) {
		return aliases.contains(name);
	}

	public String getPrimaryName() {
		return this.primaryName;
	}
	public String getMessageForVerb(String verb) {
		return messages.getOrDefault(verb, "");
	}
    
    public HashSet<String> getAllItemActions() {
        HashSet<String> actions = new HashSet<String>();

        for (String action : this.messages.keySet()) {
            if (action != null) {
                actions.add(action);
            }
        }

        return actions;
    }

	public String toString() {
		return "Item: " + primaryName + "'s weight is " + weight;
	}

	public int getWeight() {
		return weight;
	}

}

