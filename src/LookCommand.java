import java.util.HashSet;

class LookCommand extends Command {
        
    String execute() {


        NPC currentNPC = GameState.instance().getNPCFromRoom(GameState.instance().getAdventurersCurrentRoom());

        // If there is an AttackNPC in the room, apply damage
        if (currentNPC != null && currentNPC instanceof AttackNPC) {
            Player.instance().setHealth(((AttackNPC) currentNPC).getNPCAttack());
            System.out.println(currentNPC.getName() + " strikes you!");
        }
    
       return GameState.instance().getAdventurersCurrentRoom().lookAtRoom();
    
   
    }

}
