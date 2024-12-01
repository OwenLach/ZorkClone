class SpeakCommand extends Command {
	String execute() {
	    NPC npc = GameState.instance().getNPCFromRoom(GameState.instance().getAdventurersCurrentRoom());
        if (npc != null) {
            return npc.speak();
        }
        else {
           return "Who are you trying to talk to?";
        }
	}
}
