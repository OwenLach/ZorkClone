class ScoreEvent extends Event {
   private int changeInScore = 0;
   
	ScoreEvent(int score) {	
		this.changeInScore = score;
	}	

	void execute() {
      //  String change = Integer.toString(changeInScore);
        Player.instance().setScore(changeInScore);
     //   System.out.println("made new ScoreEvent() with change in score of " + this.changeInScore);
    	}

}
