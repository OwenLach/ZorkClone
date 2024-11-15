class ScoreCommand extends Command{
	ScoreCommand() {
	}

	String execute() {
        //int score = Player.instance().getScore();
        return String.valueOf(Player.instance().getScore());
	}

}

