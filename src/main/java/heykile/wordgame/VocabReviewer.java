/**
 * Simple, text-based vocab review program.
 * This program is meant for use with dictionaries in the dictionary
 * folder of this project's root.
 * Tests can be found in \src\test\java\heykile\wordgame.
 * 
 * @author Kyle Bello (@HeyKile)
 */

package heykile.wordgame;

import java.util.*;

public class VocabReviewer {

    VocabReviewer review;
    Trie reviewTrie;
    ArrayList<String> userAnswers;
    ArrayList<String> answerKey;
    int numQuestions;
    int numCorrect;
    ScannerWrapper scan = new ScannerWrapper(); // uses wrapper class for testing
    
    public VocabReviewer(Trie trie){
        this.userAnswers = new ArrayList<>();
        this.reviewTrie = trie;
        this.reviewTrie.useDictionaryFile("E:\\Coding Proejcts\\word-game\\wordgame\\dictionaries\\cs-dictionary.txt");
        this.numQuestions = selectNumQuestions();
        this.answerKey = createAnswerKey(this.numQuestions);
        this.numCorrect = 0;
    }

    /**
     * Starts the review. If the user chooses not to start the review, 
     * the method returns false. If the user chooses to play again after 
     * the review, the method returns true.
     *
     * @return true if the user chooses to play again, false otherwise
     */
    public boolean runReview(){
        if(!startReview()) 
            return false;
        System.out.println("Play again? y/n");
        if(!scan.nextLine().toLowerCase().equals("y")) 
            return false;
        return true;
    }

    /**
     * Starts the review. Displays the number of questions and asks the user 
     * if they are ready to begin. If the user is not ready, the method returns false.
     *
     * @return true if the user is ready to begin, false otherwise
     */
    private boolean startReview(){
        String startCondition = "n";
        System.out.println("=====================================");
        System.out.println("Welcome to your randomized CS review");
        System.out.println("Number of questions: " + numQuestions);
        System.out.println("Are you ready to begin? y/n");
        startCondition = scan.nextLine();
        if(startCondition.toLowerCase().equals("n")) 
            return false;
        displayQuestions();
        return true;
    }

    /**
     * Displays the questions for the review. For each question, the method 
     * displays the definition and prompts the user for an answer.
     */
    private void displayQuestions(){
        for(int i = 0; i < this.answerKey.size(); i++) {
            System.out.println("\n=====================================");
            System.out.println("Question " + (i+1) + ": \n" + this.reviewTrie.getDefinition(this.answerKey.get(i)) + "\n");
            System.out.print("Answer: ");
            this.userAnswers.add(scan.nextLine().toLowerCase());
        }
        displayResults();
    }

    /**
     * Displays the results of the review. For each question, the method 
     * displays whether the user's answer was correct or not.
     */
    private void displayResults(){
        System.out.println("=====================================");
        System.out.println("Here are your results");
        int questionNumber = 1;
        for(String userAnswer : userAnswers){
            String correctAnswer = answerKey.get(questionNumber - 1);
            boolean isCorrect = userAnswer.equals(correctAnswer);
            if(isCorrect)
                numCorrect++;
            System.out.printf("Question %d\nCorrect answer: %s\nYou answered: %s\n%s\n\n",
                              questionNumber, correctAnswer, userAnswer, isCorrect ? "Correct!" : "Incorrect.");
            questionNumber++;
        }
        System.out.println("=====================================");
        System.out.printf("You answered %d questions correct!\n", numCorrect);
        System.out.println("=====================================");
    }

    /**
     * Creates an answer key for the review. The answer key is a list of words 
     * that will be used for the review. If the number of questions is greater 
     * than the total number of words in the word bank, the number of questions 
     * is reduced to the total number of words.
     *
     * @param numQuestions the number of questions for the review
     * @return a list of words for the review
     */
    public ArrayList<String> createAnswerKey(int numQuestions) {
        Set<String> answerKey = new LinkedHashSet<>();
        if(numQuestions > reviewTrie.totalWordCount){
            System.out.println("Not enough words in word bank, shrinking number of questions to all words");
            numQuestions = reviewTrie.totalWordCount;
        }
        while(answerKey.size() < numQuestions){
            answerKey.add(reviewTrie.getRandomWord());
        }
        return new ArrayList<>(answerKey);
    }

    public int getNumCorrect(){
        return numCorrect;
    }

    /**
     * Prompts the user to select the number of questions for the review.
     *
     * @return the number of questions selected by the user
     */
    public int selectNumQuestions(){
        System.out.println("How many questions would you like?");
        int result = scan.nextInt();
        scan.nextLine();
        return result;
    }

    public static void main(String[] args){
        VocabReviewer review;
        while(true){
            review = new VocabReviewer(new Trie());
            if(!review.runReview()) break;
        }
        review.scan.close();
        review = null;
    }
}