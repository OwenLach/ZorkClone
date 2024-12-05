class VerboseCommand extends Command{
   
    VerboseCommand() {
    }

    String execute() {
          if (GameState.instance().isVerboseMode()) {  
            return "Verbose Mode ON";
        } else {  
            return "Verbose Mode OFF";
        }
    }
}
