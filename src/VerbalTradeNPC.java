import java.util.*;

class VerbalTradeNPC extends NPC {

	private ArrayList<String>  quotes = new ArrayList<String>();
	private ArrayList<Item> inventory = new ArrayList<Item>();
    private ArrayList<String> saysSynonyms = new ArrayList<String>(
            Arrays.asList("bequeaths","states","says","proclaims","shouts"));

	VerbalTradeNPC(Scanner s) {
        super(s.nextLine());
        //this.name = s.nextLine();

        String[] quotesArr = s.nextLine().split(":")[1].split(",");

        for (String quote : quotesArr) {
            this.quotes.add(quote);
            System.out.println("added quote: " + quote + " - to NPC: " + this.name);
        }

        for (String item : s.nextLine().split(":")[1].split(",")) {
            Item i = GameState.instance().getDungeon().getItem(item);
            this.inventory.add(i);
            System.out.println("Added " + i.getPrimaryName() + " to NPC: " + this.name + "'s inventory");
        }
 

        Room NPCroom = GameState.instance().getDungeon().getRoom(s.nextLine());
        GameState.instance().addNPCtoRoom(NPCroom, this);

        s.nextLine(); //skip ---
	}

    ArrayList<Item> getInventory() {
		return this.inventory;
	}

	String trade(Item playerTradeItem, Item itemPlayerWants) {
        /*
		this.inventory.add(playerTradeItem);
		this.inventory.remove(itemPlayerWants);

		GameState.instance().addItemToInventory(itemPlayerWants);
		GameState.instance().removeItemFromInventory(playerTradeItem);

		return "you successfullly traded .."
        */
        return "";
	}

	String showInventory() {
        return "";
	}

    String speak() {
        Random rand = new Random();
		int randomNum = rand.nextInt(this.quotes.size());
        int randSaysSyn = rand.nextInt(this.saysSynonyms.size());
		return this.name + " " + this.saysSynonyms.get(randSaysSyn) + " to you, \"" + this.quotes.get(randomNum) + ".\"";
	}
}
