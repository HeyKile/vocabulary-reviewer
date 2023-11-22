/**
 * Command line vocabulary review game.
 * For use with the dictionaries in /dictionaries.
 * 
 * @author Kyle Bello (@HeyKile)
 */

package heykile.wordgame;

import java.util.*;

public class CSReview {

    CSReview review;

    Trie wordBank;
    ArrayList<String> userAnswers;
    ArrayList<String> answerKey;
    int numQuestions;
    int numCorrect;
    static Scanner userInput = new Scanner(System.in);

    /**
     * Constructor for CSReview.
     * Initializes the review with a dictionary file, number of questions, answer key, and sets the number of correct answers to 0.
     */
    public CSReview(){
        userAnswers = new ArrayList<>();
        wordBank = new Trie();
        wordBank.useDictionaryFile("E:\\Coding Proejcts\\word-game\\wordgame\\dictionaries\\cs-dictionary.txt");
        numQuestions = selectNumQuestions();
        answerKey = createAnswerKey(this.numQuestions);
        numCorrect = 0;
    }

    /**
     * Runs the review.
     * @return false if the review does not start or the user does not want to play again, true otherwise.
     */
    private boolean runReview(){
        if(!startReview()) 
            return false;
        System.out.println("Play again? y/n");
        if(!userInput.nextLine().toLowerCase().equals("y")) 
            return false;
        return true;
    }

    /**
     * Starts the review.
     * @return false if the user does not want to start the review, true otherwise.
     */
    private boolean startReview(){
        String startCondition = "n";
        System.out.println("=====================================");
        System.out.println("Welcome to your randomized CS review");
        System.out.println("Number of questions: " + numQuestions);
        System.out.println("Are you ready to begin? y/n");
        startCondition = userInput.nextLine();
        if(startCondition.toLowerCase().equals("n")) 
            return false;
        displayQuestions();
        return true;
    }

    /**
     * Displays the questions for the review.
     */
    private void displayQuestions(){
        String currentAnswer = "";
        for(int i = 0; i < answerKey.size(); i++) {
            System.out.println("\n=====================================");
            System.out.println("Question " + (i+1) + ": \n" + wordBank.getDefinition(answerKey.get(i)) + "\n");
            System.out.print("Answer: ");
            currentAnswer = userInput.nextLine();
            currentAnswer.toLowerCase();
            userAnswers.add(currentAnswer);
        }
        displayResults();
    }

    /**
     * Displays the results of the review.
     */
    private void displayResults(){
        System.out.println("=====================================");
        System.out.println("Here are your results");
        for(int i = 0; i < userAnswers.size(); i++){
            System.out.print("Question " + (i+1));
            System.out.println("\nCorrect answer: " + answerKey.get(i));
            System.out.println("You answered: " + userAnswers.get(i));
            if(userAnswers.get(i).equals(answerKey.get(i))){
                System.out.println("Correct!");
                numCorrect++;
            }
            else{
                System.out.println("Incorrect.");
            }
            System.out.println("\n");
        }
        System.out.println("=====================================");
        System.out.println("You answered " + numCorrect + " questions correct!");
        System.out.println("=====================================");
    }

    /**
     * Creates an answer key for the review.
     * @param numQuestions the number of questions for the review.
     * @return an ArrayList of the correct answers.
     */
    public ArrayList<String> createAnswerKey(int numQuestions) {
        Set<String> answerKey = new HashSet<>();
        if(numQuestions > wordBank.totalWordCount){
            System.out.println("Not enough words in word bank, shrinking number of questions to all words");
            numQuestions = wordBank.totalWordCount;
        }
        while(answerKey.size() < numQuestions){
            answerKey.add(wordBank.getRandomWord());
        }
        return new ArrayList<>(answerKey);
    }

    /**
     * Gets the number of correct answers.
     * @return the number of correct answers.
     */
    public int getNumCorrect(){
        return numCorrect;
    }

    /**
     * Selects the number of questions for the review.
     * @return the number of questions.
     */
    public int selectNumQuestions(){
        System.out.println("How many questions would you like?");
        int result = userInput.nextInt();
        userInput.nextLine();
        return result;
    }

    /**
     * Main method.
     * Runs the review until the user does not want to play again.
     */
    public static void main(String[] args){
        while(true){
            CSReview review = new CSReview();
            if(!review.runReview()) break;
            review = null;
        }
        userInput.close();
    }
}