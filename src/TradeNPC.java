import java.util.*;

class TradeNPC extends NPC {

	private ArrayList<Item> inventory = new ArrayList<Item>(); 
    
	TradeNPC(Scanner s) {
		// setup NPC
        super(s.nextLine());
        //this.name = s.nextLine();

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
}
