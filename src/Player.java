class Player {
	private static Player instance = null;
	private static int score = 0;
	private static int health = 10;
	private static int hunger = 10;
	
	public static Player instance() {
		if (Player.instance == null) {
					Player.instance = new Player();
		}
		return Player.instance;
    }

	private Player() {
	}

	public void setScore (int score) {
		this.score += score;
	}

	public void setHealth (int health) {
		//this.health += health;
	}

	public int getScore () {
        return this.score;
	}

	public String getHealth() {
        return "your Current Health is " + health ;
		// return a message bases on player health
	}

	public void setHunger(int hunger) {
		//this.hunger += hunger;
		// check for if hunger < 0
	}

	public int getHunger() {
		return this.hunger;
	}
}
