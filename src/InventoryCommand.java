import java.util.ArrayList;

class InventoryCommand extends Command {

     String execute() {
         ArrayList<Item> inv = GameState.instance().getInventory();

         if (inv.isEmpty()) {
             return "You are empty-handed.";
         }
         
         System.out.println("You are carrying: ");

         for (Item item : inv) {
             System.out.println("-" + item.getPrimaryName());
         }

        return "";
     
     }
}
