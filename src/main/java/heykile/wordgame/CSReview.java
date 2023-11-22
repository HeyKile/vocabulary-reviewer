package heykile.wordgame;

import java.util.*;

public class CSReview {

    CSReview review;

    Trie reviewTrie;
    ArrayList<String> userAnswers;
    ArrayList<String> answerKey;
    int numQuestions;
    int numCorrect;
    static Scanner scan = new Scanner(System.in);


    public CSReview(){
        userAnswers = new ArrayList<>();
        reviewTrie = new Trie();
        reviewTrie.useDictionaryFile("E:\\Coding Proejcts\\word-game\\wordgame\\dictionaries\\cs-dictionary.txt");
        numQuestions = selectNumQuestions();
        answerKey = createAnswerKey(this.numQuestions);
        numCorrect = 0;
    }

    private boolean runReview(){
        if(!startReview()) 
            return false;
        System.out.println("Play again? y/n");
        if(!scan.nextLine().toLowerCase().equals("y")) 
            return false;
        return true;
    }

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

    private void displayQuestions(){
        String currentAnswer = "";
        for(int i = 0; i < answerKey.size(); i++) {
            System.out.println("\n=====================================");
            System.out.println("Question " + (i+1) + ": \n" + reviewTrie.getDefinition(answerKey.get(i)) + "\n");
            System.out.print("Answer: ");
            currentAnswer = scan.nextLine();
            currentAnswer.toLowerCase();
            userAnswers.add(currentAnswer);
        }
        displayResults();
    }

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
        System.out.println("You ansered " + numCorrect + " questions correct!");
        System.out.println("=====================================");
    }

    public ArrayList<String> createAnswerKey(int numQuestions) {
        Set<String> answerKey = new HashSet<>();
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

    public int selectNumQuestions(){
        System.out.println("How many questions would you like?");
        int result = scan.nextInt();
        scan.nextLine();
        return result;
    }

    public static void main(String[] args){
        while(true){
            CSReview review = new CSReview();
            if(!review.runReview()) break;
            review = null;
        }
        scan.close();
    }
}
