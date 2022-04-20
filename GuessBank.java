/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * <Stephen Young>
 * <ssy397>
 */
package assignment2;

import static assignment2.GameConfiguration.guessNumber;

public class GuessBank {
    private String[] guesses = new String[guessNumber];
    private String[] outputs = new String[guessNumber];
    private int guessIndex = 0;
    private int outputIndex = 0;

    public void addGuess(String guess){
        guesses[guessIndex] = guess;
        guessIndex++;
    }
    public void addOutput(String output){
        output = output.substring(16, 21);
        outputs[outputIndex] = output;
        outputIndex++;
    }
    public void printHistory(){
        for(int i = 0; i < guessIndex; i++){
            System.out.println(guesses[i] + "\t\t" + outputs[i]);

        }
    }
    public void resetBank(){
        for(int i = 0; i < guessIndex; i++){
            guesses[i] = "";
            outputs[i] = "";
            guessIndex = 0;
            outputIndex = 0;
        }
    }
}
