import java.util.*;

class NPCFactory {

	private static NPCFactory instance = null;
	
	public static NPCFactory instance() {
        if (NPCFactory.instance == null) {
            NPCFactory.instance = new NPCFactory();
        }
        return NPCFactory.instance;
    }

    private NPCFactory() {
    }
    
    NPC parse(Scanner scnr) throws NoNPCException {
		String npcType = scnr.nextLine();

		if (npcType.equals("===")) {
			throw new NoNPCException();
		}

		if (npcType.equals("verbal")) {
            System.out.println("making new verbal npc");
			return new VerbalNPC(scnr);
		}
		else if (npcType.equals("tradeable")) {
            System.out.println("making new trade npc");
			return new TradeNPC(scnr);
		}
		else {
            System.out.println("making new verbal/trade npc");
			return new VerbalTradeNPC(scnr);
		}
	}
}
