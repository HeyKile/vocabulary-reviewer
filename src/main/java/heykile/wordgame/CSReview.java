package heykile.wordgame;

import java.util.*;

public class CSReview {

    Trie reviewTrie;
    ArrayList<String> userAnsers;
    ArrayList<String> correctAnswers;
    int numQuestions;
    int numCorrect;

    public CSReview(){
        userAnsers = new ArrayList<>();
        // reviewTrie = selectWordBanks();
        reviewTrie = new Trie();
        reviewTrie.useDictionaryFile("E:\\Coding Proejcts\\word-game\\wordgame\\dictionaries\\cs-dictionary.txt");
        numQuestions = selectNumQuestions();
        correctAnswers = createAnswerKey(this.numQuestions);
        numCorrect = 0;
        runReview();
    }

    public void runReview(){
        for(int i = 0; i < numQuestions; i++){
            
        }
    }

    public ArrayList<String> createAnswerKey(int numQuestions){
        ArrayList<String> answerList = new ArrayList<>();

        for(int i = 0; i < numQuestions; i++){
            
        }

        return answerList;
    }

    public void displayDefinition(String defintion){

    }

    public void displayDefinition(){

    }

    public boolean isAnswerCorrect(){

        return false;
    }

    public void displayCorrectAnswer(){

    }

    public void displayUserAnswer(){

    }

    public int getNumCorrect(){
        return numCorrect;
    }

    // public Trie selectWordBanks(){
    //     Scanner scan = new Scanner(System.in);
    //     String selection;
    //     while(true){
    //         Scanner scan = new Scanner(System.in);
    //         String



    //     }
    //     Trie trie = new Trie();

    //     return trie;
    // }

    public int selectNumQuestions(){
        return 0;
    }

}
