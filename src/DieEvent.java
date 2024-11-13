class DieEvent extends Event {
	void execute() {
        System.out.println("made new DieEvent() and now executing");
		// tell user they died and restart, call GameState.restore() and reset the game
	}
}
