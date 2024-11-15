class WoundEvent extends Event {
	// a negative changeInHealth heals the player

	private int changeInHealth = 0;

	WoundEvent(int health) {	
		this.changeInHealth = health;
	}	

	void execute() {
       // System.out.println("made new WoundEvent(), changing health by " + this.changeInHealth);
		  int currHealth = Integer.parseInt(Player.instance().getHealth());
        Player.instance().setHealth(currHealth - changeInHealth);
	}
}
