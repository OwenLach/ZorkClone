class ScoreEvent extends Event {
	private int changeInScore = 0;

	ScoreEvent(int score) {	
		this.changeInScore = score;
	}	

	void execute() {
		//Player.instance().setScore(this.changeInScore);
	}

}
