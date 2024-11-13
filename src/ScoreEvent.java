class ScoreEvent extends Event {
	private int changeInScore = 0;

	ScoreEvent(int score) {	
		this.changeInScore = score;
	}	

	void execute() {
        System.out.println("made new ScoreEvent() with change in score of " + this.changeInScore);
		//Player.instance().setScore(this.changeInScore);
	}

}
