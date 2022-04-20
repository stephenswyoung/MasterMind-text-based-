/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * <Stephen Young>
 * <ssy397>
 */
package assignment2;
import static assignment2.GameConfiguration.colors;
import static assignment2.GameConfiguration.pegNumber;

public class FormatChecker {
    private String[] validColors = colors;
    private int validPegNumber = pegNumber;
    private boolean charIsValid(String guess){
        int cnt = 0;
        for(int i = 0; i < validPegNumber; i++) {
            for(int j = 0; j < validColors.length; j++){
                if(validColors[j].equals(String.valueOf(guess.charAt(i)))){
                    cnt++;
                }
            }
        }
        if (cnt == validPegNumber){
            return true;
        }
        return false;
    }
    public boolean inputIsValid(String input){
        if(input.length() != validPegNumber){
            System.out.println(input + " -> INVALID GUESS");
            return false;
        }
        if(!charIsValid(input)){
            System.out.println(input + " -> INVALID GUESS");
            return false;
        }
        return true;
    }

}
