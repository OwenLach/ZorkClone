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
       
		String firstLine = scnr.nextLine(); // skips "Items:"

		while(scnr.hasNextLine()) {
			String itemName = scnr.nextLine();
			if (itemName.equals("===")){
            throw new NoItemException();
         }

			this.primaryName = itemName; // item name is set to primary name
			System.out.println("Item Name is " + itemName);

			//			String itemName = scan.nextLine(); // scans next line in file 
			//			itemName.equals(this.primaryName); // sets line to item name
			//			System.out.println(itemName); // prints out item name
			//			
			String itemWeight = scnr.nextLine();
			this.weight = Integer.parseInt(itemWeight);
			System.out.println("item weight is " + this.weight);

			String itemAction;
			boolean possibleAction = false; //used to verify if item is able to enact action
			while(scnr.hasNextLine() && !(itemAction = scnr.nextLine().trim()).equals("---")) {
				possibleAction = true;
				String[] splitAction = itemAction.split(":");

				if (splitAction.length ==2) { //checks to see if when line is split at ":" there is only 1 ":" verifying there is only two halves the key(left) and value(right)
					String action = splitAction[0]; //sets what's before ":" as action name
					String response = splitAction[1]; //what's after ":" as action response
					messages.put(action, response); //adds the action and action response to hashtable
					System.out.println("Action is " + action + " the ability " + response); //temp for texting
				}
			}
			if(!possibleAction) { //checks to see if item has no action
				System.out.println("this is a boring item"); //temp for testing
			}
		}
	}

	public boolean goesBy(String name) {
		return aliases.contains(name);
	}

	public String getPrimaryName() {
		return this.primaryName;
	}
	public String getMessageForVerb(String verb) {
		return messages.getOrDefault(verb, ""); // returns the contents of the key that is called 
	}

	public String toString() {
		return "Item " + primaryName + "'s weight is" + weight;
	}

	public int getWeight() {
		return weight;
	}


	public static void main(String args[]) throws Exception {
      try {
         Scanner scan = new Scanner(new FileReader("../files/items.zork"));
         Item item = new Item(scan);
         scan.close(); // Close the scanner to avoid resource leaks
      } catch (Exception e) {
      }

   }
}

