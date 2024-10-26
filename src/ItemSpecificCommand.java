class ItemSpecificCommand extends Command {

    private Item item = null;
    private String action = "";

    ItemSpecificCommand (Item item, String action) {
        this.item = item;
        this.action = action;
    }

    String execute() {
        return this.item.getMessageForVerb(action);
    }

}
