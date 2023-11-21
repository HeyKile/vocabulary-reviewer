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

    public int selectNumQuestions(){
        return 10;
    }

}
