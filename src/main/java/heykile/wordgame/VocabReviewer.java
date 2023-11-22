package heykile.wordgame;

import java.util.*;

public class VocabReviewer {

    VocabReviewer review;
    Trie reviewTrie;
    ArrayList<String> userAnswers;
    ArrayList<String> answerKey;
    int numQuestions;
    int numCorrect;
    Scanner scan;

    // variables for testing
    List<String> testInputs;
    int currentLineIndex = 0;
    
    public VocabReviewer(Trie trie){
        this(trie, new ArrayList<String>());
    }
    
    public VocabReviewer(Trie trie, List<String> inputLines){
        this.userAnswers = new ArrayList<>();
        this.reviewTrie = trie;
        this.reviewTrie.useDictionaryFile("E:\\Coding Proejcts\\word-game\\wordgame\\dictionaries\\cs-dictionary.txt");
        this.testInputs = inputLines;
        if (this.testInputs.isEmpty()) {
            this.scan = new Scanner(System.in);
        }
        this.numQuestions = selectNumQuestions();
        this.answerKey = createAnswerKey(this.numQuestions);
        this.numCorrect = 0;
    }

    private String getNextLine(){
        if(scan != null)
            return scan.nextLine();
        else
            return testInputs.remove(0);
    }

    private int getNextInt(){
        if(scan != null){
            int number = scan.nextInt();
            return number;
        }
        else if(testInputs != null && !testInputs.isEmpty())
            return Integer.parseInt(testInputs.get(currentLineIndex++));
        else
            throw new NoSuchElementException("No more inputs available");
    }

    public boolean runReview(){
        if(!startReview()) 
            return false;
        System.out.println("Play again? y/n");
        if(!getNextLine().toLowerCase().equals("y")) 
            return false;
        return true;
    }

    private boolean startReview(){
        String startCondition = "n";
        System.out.println("=====================================");
        System.out.println("Welcome to your randomized CS review");
        System.out.println("Number of questions: " + numQuestions);
        System.out.println("Are you ready to begin? y/n");
        startCondition = getNextLine();
        if(startCondition.toLowerCase().equals("n")) 
            return false;
        displayQuestions();
        return true;
    }

    private void displayQuestions(){
        for(int i = 0; i < this.answerKey.size(); i++) {
            System.out.println("\n=====================================");
            System.out.println("Question " + (i+1) + ": \n" + this.reviewTrie.getDefinition(this.answerKey.get(i)) + "\n");
            System.out.print("Answer: ");
            this.userAnswers.add(getNextLine().toLowerCase());
        }
        displayResults();
    }

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

    public int selectNumQuestions(){
        System.out.println("How many questions would you like?");
        int result = getNextInt();
        getNextLine();
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
