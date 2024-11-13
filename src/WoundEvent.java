class WoundEvent extends Event {
	// a negative changeInHealth heals the player

	private int changeInHealth = 0;

	WoundEvent(int health) {	
		this.changeInHealth = health;
	}	

	void execute() {
        System.out.println("made new WoundEvent(), changing health by " + this.changeInHealth);
		//Player.instance().setHealth(this.changeInHealth);
	}
}
