class ItemSpecificCommand extends Command {

    private Item item = null;
    private String verb = "";

    ItemSpecificCommand (Item item, String verb) {
        this.item = item;
        this.verb = verb;
    }

    String execute() {
        for (String s : this.item.getEvents(verb)) {
        Event e = EventFactory.instance().parseEvent(this.item, s);
            e.execute();
        }

        String message = item.getMessageForVerb(verb);
        if (message == null) { 
            return "Cannot " + this.verb + " on " + this.item.getPrimaryName(); 
        }
        return message; 
    }

}
