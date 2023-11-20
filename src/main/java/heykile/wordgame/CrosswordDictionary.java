/**
 * Dictionary class for crossword puzzle game
 * Uses a trie for word look-up and a hashmap for
 * matching words with their definition
 * 
 * @author Kyle Bello (@HeyKile)
 */

/*
 * TODOs:
 *  1. test trie & dictionary
 *  2. add file parser to input dictionary
 *  3. test said file parser 
 */

/*
 * Intended behavior:
 *  Dictionary in the form (word, definition) is read in from some input
 *  For each unique word:
 *      word is added to trie
 *      word & definition is added to hashmap
 *  During board creation:
 *      trie is used to build solution for imput board design
 *      once complete: 
 *          solution is saved
 *          defintions are for solution words saved
 */

package heykile.wordgame;
import java.util.*;


public class CrosswordDictionary {
    
    private HashMap<String, String> dictionaryEntries; // ArrayList of ArrayList for each letter in the alphabet
    private Trie dictionary;
    private StringBuilder currentPrefix; // current prefix being searched
    private int charsAppenedSinceValidWord; // number of chars added since prefix last on trie

    public CrosswordDictionary(){
        dictionaryEntries = new HashMap<>();
        dictionary = new Trie();
        currentPrefix = new StringBuilder();
        charsAppenedSinceValidWord = 0;
    }

}
