class DropEvent extends Event {
	private Item item = null;

	DropEvent(Item item) {
		this.item = item;
	}
	
	void execute() {
        System.out.println("made new DropEvent() and now executing");
		//GameState.instance().removeFromInventory();
		//GameState.instance().addItemToRoom(GameState.instance().getPlayerCurrRoom(), item);
	}
}
