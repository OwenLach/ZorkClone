class WinEvent extends Event {
	void execute() {
	    System.out.println("\n======= V I C T O R Y  A C H I E V E D ======\n");
        System.out.println("         " + new ScoreCommand().execute());
        System.exit(0);
    }
}
