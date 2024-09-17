
import java.util.*;

class Interpreter {
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        char inputChar = ' '; 

        while(inputChar != 'q') {
            System.out.println("Enter input character (q to quit): ");
            String input = scnr.nextLine();

            if(!input.isEmpty()) {
                inputChar = input.charAt(0);        

                if (inputChar == 'q') {
                    System.out.println("quitting"); 
                    continue;
                }
                System.out.println("you entered " + inputChar);
            }
            else {
                continue;
            }
        }
    }


}
