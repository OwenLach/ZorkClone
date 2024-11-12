class DissapearEvent extends Event {
	private Item item = null;

	DissapearEvent(Item item) {
		this.item = item;
	}
	
	void execute() {
		//GameState.instance().removeFromInventory();
		//GameState.instance().removeItemFromRoom(// get room that item is in, item);
		//this.item = null;
	}
}
