/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * <Stephen Young>
 * <ssy397>
 */
package assignment2;

import java.util.Scanner;

import static assignment2.GameConfiguration.guessNumber;
import static assignment2.GameConfiguration.pegNumber;


public class Game {
    private Scanner scanner;
    private int testMode;
    public boolean isGameWon = false;
    public String playAgain = "Y";


    public Game(Scanner scanner, int testMode){
        this.scanner = scanner;
        this.testMode = testMode;
    }

    public String newCode() {
        return SecretCodeGenerator.getInstance().getNewSecretCode();
    }

    public String checkGuess(String guess, String solution) {
        int black = 0;
        int white = 0;
        String tempSolution = solution;
        String tempGuess = guess;
        for (int i = 0; i < pegNumber; i++) {                                                    // Goes through guess and compares to solution
            if (guess.charAt(i) == tempSolution.charAt(i)) {                                     // If there is a match, replaces the match with a 0 in solution to
                black++;                                                                         // Avoid problems with extra whites down the road
                tempSolution = tempSolution.substring(0, i) + "0" + tempSolution.substring(i + 1);
                guess = guess.substring(0, i) + "1" + guess.substring(i + 1);
            }
        }

        for (int i = 0; i < pegNumber; i++) {                                                    // Goes through again and compares all positions to see how many whites
            for (int j = 0; j < pegNumber; j++) {
                if (guess.charAt(i) == tempSolution.charAt(j)) {
                    white++;
                    tempSolution = tempSolution.substring(0, j) + "2" + tempSolution.substring(j + 1);
                    break;
                }
            }
        }
        if (black == pegNumber) {
            isGameWon = true;
        }
        return(tempGuess + " -> Result: " + black + "B_" + white + "W");
    }

    public void runGame() {
        String input;
        String output;
        FormatChecker currentFormat = new FormatChecker();
        GuessBank currentBank = new GuessBank();
        while (playAgain.equals("Y")) {
            String solution = newCode();
            System.out.println();
            System.out.print("Generating secret code ...");
            if (testMode == 1) {
                System.out.println("for this example the secret code is " + solution + ")");
            }
            System.out.println();
            int curGuess = guessNumber;
            while ((curGuess != 0) && !(isGameWon)) {
                System.out.println("You have " + curGuess + " guesses left.");
                System.out.println("What is your next guess?");
                System.out.println("Type in the characters for your guess and press enter.");
                System.out.println("Enter guess: ");
                input = scanner.nextLine();
                if(input.equals("HISTORY")){
                    currentBank.printHistory();
                    continue;
                }
                if (currentFormat.inputIsValid(input)) {
                    currentBank.addGuess(input);
                    output = checkGuess(input, solution);
                    System.out.println(output);
                    currentBank.addOutput(output);
                    curGuess--;
                }
            }
            if (isGameWon) {
                System.out.println("You Win!");
            } else if (curGuess == 0) {
                System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
            }
            System.out.println("Are you ready for another game (Y/N): ");
            playAgain = scanner.nextLine();
            isGameWon = false;
            currentBank.resetBank();
        }
    }
}

