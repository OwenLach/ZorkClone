class ItemSpecificCommand extends Command {

    private Item item = null;
    private String verb = "";

    ItemSpecificCommand (Item item, String verb) {
        this.item = item;
        this.verb = verb;
    }

    String execute() {
        String message = item.getMessageForVerb(verb);

        for (String s : this.item.getEvents(verb)) {
            Event e = EventFactory.instance().parseEvent(this.item, s);

            if (e instanceof WinEvent) {
                ((WinEvent) e).execute(message);
            }
            else if (e instanceof DieEvent) {
                ((DieEvent) e).execute(message);
            }
            else if (e instanceof DamageEvent) {
                ((DamageEvent) e).execute(message);
                return "";
            }
            else {
                e.execute();
            }
        }

        if (message == null) { 
            return "Cannot " + this.verb + this.item.getPrimaryName(); 
        }
        return message; 
    }

}
