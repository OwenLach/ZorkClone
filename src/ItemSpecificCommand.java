class ItemSpecificCommand extends Command{

   private String verb = " ";
   private String noun = " ";
   Item item = null;

   String execute(){

       String testCommand = "kick can"; // Simulated input
	    String[] commands = testCommand.split(" ", 2);

	    if (commands.length < 2) {
          return "Illegal";
       }
       
       this.verb = commands[0];
	    this.noun = commands[1];

	        // Check if the item matches the provided noun 
	    if (item == null || (!item.getPrimaryName().equals(noun) && !item.goesBy(noun))) {
          return "No item named " + noun + " can be found.";
       }

	    // Retrieve the action message for the given verb
	    String actionMessage = item.getMessageForVerb(verb);

	    if (actionMessage.isEmpty()) {
          return " not able to " + verb;
       }
       // Return the response message
       return verb  + noun ;

   }
   

}
