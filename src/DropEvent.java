class DropEvent extends Event {
	private Item item = null;

	DropEvent(Item item) {
		this.item = item;
	}
	
	void execute() {
		//GameState.instance().removeFromInventory();
		//GameState.instance().addItemToRoom(GameState.instance().getPlayerCurrRoom(), item);
	}
}
