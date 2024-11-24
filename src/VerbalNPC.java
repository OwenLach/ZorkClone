import java.util.*;

class VerbalNPC extends NPC {

	private ArrayList<String> quotes = new ArrayList<String>();
    private ArrayList<String> saysSynonyms = new ArrayList<String>(
            Arrays.asList("bequeaths","states","says","proclaims","shouts"));

	VerbalNPC(Scanner s) {
		//put quotes in array ArrayList
        super(s.nextLine());
        //this.name = s.nextLine();
        String[] quotesArr = s.nextLine().split(":")[1].split(",");

        for (String quote : quotesArr) {
            this.quotes.add(quote);
            System.out.println("added quote: " + quote + " - to NPC: " + this.name);
        }

        Room NPCroom = GameState.instance().getDungeon().getRoom(s.nextLine());
        GameState.instance().addNPCtoRoom(NPCroom, this);

        s.nextLine(); //skip ---
        
	}

	String speak() {
        Random rand = new Random();
		int randomNum = rand.nextInt(this.quotes.size());
        int randSaysSyn = rand.nextInt(this.saysSynonyms.size());
        return this.name + " " + this.saysSynonyms.get(randSaysSyn) + " to you, \"" + this.quotes.get(randomNum) + ".\"";
	}
}
