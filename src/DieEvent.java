class DieEvent extends Event {
	void execute() {
        System.out.println("\n======= Y O U  D I E D =======\n");
        System.out.println("     " + new ScoreCommand().execute());
	    System.exit(0);
    }
}
