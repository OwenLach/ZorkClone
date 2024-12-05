import java.util.*;

class AttackNPC extends NPC {
   
    private int health; 
    private int attack;
    
	private ArrayList<String> quotes = new ArrayList<String>();
    private ArrayList<String> responses = new ArrayList<String>(
            Arrays.asList("grunts", "steps back", "flinches", "winces", "staggers", "yelps"));
    
    Room NPCroom = null;

	AttackNPC(Scanner s) {
        super(s.nextLine());
       
        health = Integer.parseInt(s.nextLine().split(":")[1]);
        attack = Integer.parseInt(s.nextLine().split(":")[1]); 

        String lineAfterStats = s.nextLine();
        if (lineAfterStats.contains("Quotes:")) {
                String[] attackLines = lineAfterStats.split(":")[1].split(",");
            for (String line : attackLines) {
                this.quotes.add(line);
            }
            NPCroom = GameState.instance().getDungeon().getRoom(s.nextLine());
            GameState.instance().addNPCtoRoom(NPCroom, this);
        } else {
            NPCroom = GameState.instance().getDungeon().getRoom(lineAfterStats);
            GameState.instance().addNPCtoRoom(NPCroom, this);
        }
    }
    
    public int getNPCAttack() {
       return this.attack; 
    }

    public void setNPCHealth(int health) {
        Random rand = new Random();
        int randResponse = rand.nextInt(this.responses.size());

        this.health -= health;
        if (this.health <= 0) {
            System.out.println(this.name + " falls, vanquished forever.");
            GameState.instance().removeNPCFromRoom(NPCroom, this);   
        } else {
            System.out.println(this.name + " " + this.responses.get(randResponse) + " at your attack.");
        }
    }

    public int getNPCHealth(){
        return health;
    }

    //maybe add speaking
    String speak() {
        return "";
    }
    
    String trade() {
        return "";
    }
    
    ArrayList<Item> getInventory() {
        return new ArrayList<Item>();
    }
    void addToInventory(Item i) {}
    void clearInventory() {}

}
