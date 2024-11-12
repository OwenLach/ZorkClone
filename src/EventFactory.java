class EventFactory {
	private static EventFactory instance = null;
	
	public static EventFactory instance() {
        if (EventFactory.instance == null) {
            EventFactory.instance = new EventFactory();
        }
        return EventFactory.instance;
    }

    private EventFactory() {
    }

    /*
    
	Event parseEvent(Item item, string verb) {
		String events item.events.get(verb);
		
		for Event e : event {
			if (event == transform) // transform Event
			else if (event == drop) // drop event
		...... ( check for each event type)
        }

	}
    */
}
