class HealthCommand {
	HealthCommand() {
	}

	String execute() {
		return Player.instance().getHealth();
	}
}
