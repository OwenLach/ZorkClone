class ScoreCommand {
	ScoreCommand() {
	}

	String execute() {
        int score = Player.instance().getScore();
        return "";
	}

}

