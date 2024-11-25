import java.util.*;

class TradeNPC extends NPC {

	protected ArrayList<Item> inventory = new ArrayList<Item>(); 
    
	TradeNPC(Scanner s) {
		// setup NPC
        super(s.nextLine());

        for (String item : s.nextLine().split(":")[1].split(",")) {
            Item i = GameState.instance().getDungeon().getItem(item);
            this.inventory.add(i);
            System.out.println("Added " + i.getPrimaryName() + " to NPC: " + this.name + "'s inventory");
        }

        Room NPCroom = GameState.instance().getDungeon().getRoom(s.nextLine());
        GameState.instance().addNPCtoRoom(NPCroom, this);

        //s.nextLine(); //skip ---
	}

	ArrayList<Item> getInventory() { 
		return this.inventory; 
	}

    String speak() {
        return this.name + " ignores you.";
    }

	String trade() {
        GameState GS = GameState.instance();

        this.showInventory();
        this.showPlayerInventory();
        System.out.print("Trade what item in current inventory? \n> ");
		Scanner scnr = new Scanner(System.in);
		String playerItemStr = scnr.nextLine();	

		try {
			Item playerItem = GS.getItemFromInventoryNamed(playerItemStr);
			System.out.print("Trade " + playerItem.getPrimaryName() + " for what? \n> ");
			String itemWantedStr = scnr.nextLine();
			Item itemWanted = GS.getItemFromNPCInventory(itemWantedStr);

			// add check to make sure player item has enough values to trade for the npc item
			// might have to change Item class slightly 
            this.inventory.add(playerItem);
            this.inventory.remove(itemWanted);

            GS.addToInventory(itemWanted);
            GS.removeFromInventory(playerItem);

            return "You successfullly traded " + playerItem.getPrimaryName() + " for " + itemWanted.getPrimaryName() + ".";
		}
		catch(NoItemException e) {
			// make sure it shows the right error message
			return "You don't have that item in your inventory to trade";
		}
        catch(NoNPCItemException e) {
			// make sure it shows the right error message
			return this.name + " doesn't have what you are looking for.";
		}       

	}

	void showInventory() {
        String res = "-----" + this.name + "'s Inventory----\n";
        for (Item i : this.inventory)
        {
            res += "         -" + i.getPrimaryName() + "\n";
        }

        System.out.println(res);
    }

    void showPlayerInventory() {
        String res = "------Your Inventory-----\n";
        for (Item i : GameState.instance().getInventory())
        {
            res += "      -" + i.getPrimaryName() + "\n";
        }

        System.out.println(res);
    }
}
