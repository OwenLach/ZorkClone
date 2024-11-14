class DieEvent extends Event {
	void execute() {
        // WIP, still needs to print the event string message somehow
        // before ending the program
        System.err.println("\n======= Y O U    D I E D =======\n");
	    System.exit(0);
    }
}
