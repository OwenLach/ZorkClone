class DisappearEvent extends Event {
	private Item item = null;

	DisappearEvent(Item item) {
		this.item = item;
	}
	
	void execute() {
        System.out.println("made new DisappearEvent() and now executing");
		//GameState.instance().removeFromInventory();
		//GameState.instance().removeItemFromRoom(// get room that item is in, item);
		//this.item = null;
	}
}
