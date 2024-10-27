class ItemSpecificCommand extends Command {

    private Item item = null;
    private String verb = "";

    ItemSpecificCommand (Item item, String verb) {
        this.item = item;
        this.verb = verb;
    }

    String execute() {
        return this.item.getMessageForVerb(verb);
    }

}
