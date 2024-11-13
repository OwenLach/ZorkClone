class TeleportEvent extends Event {
	// make a get random room method in gamestate or dungeon class and call item

	void execute() {
        System.out.println("made new TeleportEvent() and now executing");
		//GameState.instance().setAdventurersCurrentRoom(GameState.instance().getRandomRoom());
	}
}
