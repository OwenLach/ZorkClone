class TransformEvent extends Event {
	private Item item = null;
	private boolean isInInventory = false;

	TransformEvent(Item item) {
		this.item = item;
	}
	
    
	void execute() {
        System.out.println("made new TransformEvent(), item is now changing into " + this.item.getPrimaryName());

	}
	
}
