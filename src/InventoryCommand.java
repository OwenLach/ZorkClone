import java.util.ArrayList;

class InventoryCommand extends Command {

     String execute() {
         ArrayList<Item> inv = GameState.instance().getInventory();


         if (inv.isEmpty()) {
             return "You are empty-handed.";
         } else {
             StringBuilder items = new StringBuilder();


             for (Item item : inv) {
                items.append(item.getPrimaryName()).append("\n");   
             }

         return "You are carrying: \n" + items.toString();
         }
     }
}
