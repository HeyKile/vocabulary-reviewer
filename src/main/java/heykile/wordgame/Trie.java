/**
 * Class for Trie data structure to be used w/ word games.
 * Adapted from https://www.geeksforgeeks.org/introduction-to-trie-data-structure-and-algorithm-tutorials/
 * 
 * @author Kyle Bello (@HeyKile)
 */

package heykile.wordgame;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trie {

    final static int alphabetSize = 29; // 26 letters plus dash, space, and forward slash
    TrieNode root;
    int totalWordCount;

    static class TrieNode{
        TrieNode parent;
        TrieNode[] children;
        int wordCount;
        char letter;
        boolean isWord;
        String definition;

        public TrieNode(){
            children = new TrieNode[alphabetSize];
            this.parent = null;
            this.wordCount = 0;
            this.letter = '\0';
            this.isWord = false;
            this.definition = null;
        }

        public TrieNode(TrieNode parent, char letter){
            children = new TrieNode[alphabetSize];
            this.parent = parent;
            this.wordCount = 0;
            this.letter = letter;
            this.isWord = false;
            this.definition = null;
        }
    }

    public Trie(){
        this(new TrieNode());
    }

    public Trie(TrieNode node){
        root = node;
        totalWordCount = 0;
    }

    /**
     * Builds a trie dictionary using an input file.
     * Dictionary entries must be in the form "word|definition".
     * 
     * @param fileName the path to the input file
     * @return true if sucessful, false otherwise
     */
    public boolean useDictionaryFile(String fileName){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            while((line = br.readLine()) != null){
                int spaceIndex = line.indexOf("|");
                if(spaceIndex > -1){
                    String word = line.substring(0, spaceIndex);
                    String def = line.substring(spaceIndex + 1);
                    Trie.insert(this, word.toLowerCase(), def.toLowerCase());
                }
                else{
                    if(this.root == null) return false;
                    else return true;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Converts letter into index for child array.
     * 
     * Note: '-' and ' ' and '/' have reserved spaces on array.
     * 
     * @param c
     * @return index of letter in children array
     */
    private static int letterToIndex(char c){
        switch(c){
            case '-':
                return alphabetSize - 3;
            case ' ':
                return alphabetSize - 2;
            case '/':
                return alphabetSize - 1;
            default:
                return c - 'a';
        }
    }

    /**
     * Inserts a word into the Trie.
     * 
     * If the input word is already in the trie, it is not added, even if the
     * definition is different.
     * 
     * @param root the root node of the Trie
     * @param word the word to be added
     * @param definition the definition of word
     * @return true if successful new addition, false if otherwise
     */
    static void insert(Trie trie, String word, String definition){
        TrieNode currentNode = trie.root;
        int childrenArrIndex = 0;
        for(char c : word.toCharArray()){
            childrenArrIndex = letterToIndex(c);
            boolean found = false;
            for(TrieNode child : currentNode.children){
                if(child != null && child.letter == c){
                    currentNode = child;
                    found = true;
                    break;
                }
            }
            if(!found){
                currentNode.children[childrenArrIndex] = new TrieNode(currentNode, c);
                currentNode = currentNode.children[childrenArrIndex];
            }
        }
        if(!currentNode.isWord){
            currentNode.isWord = true;
            currentNode.wordCount++;
            currentNode.definition = definition;
            trie.totalWordCount++;
        }
        else{
            System.out.println("Word not added: " + word);
        }
    }


    /**
     * Searches the given Trie to see if input word exists.
     * 
     * @param trie the trie to search for the word
     * @param word the word to search for
     */
    static boolean search(Trie trie, String word){
        TrieNode currentNode = trie.root;
        for(char c : word.toCharArray()){
            boolean foundCurrentLetter = false;
            for(TrieNode child : currentNode.children){
                if(child != null && child.letter == c){
                    currentNode = child;
                    foundCurrentLetter = true;
                }
            }
            if(!foundCurrentLetter) return false;
        }
        return currentNode.isWord;
    }

    /**
     * Finds the definition of the given word using the trie
     * 
     * @param word the word to search for
     * @return the ending node of a word if it exists, null otherwise
     */
    public String getDefinition(String word){
        TrieNode currentNode = this.root;
        for(char c : word.toCharArray()){
            boolean foundCurrentLetter = false;
            for(TrieNode child : currentNode.children){
                if(child != null && child.letter == c){
                    currentNode = child;
                    foundCurrentLetter = true;
                }
            }
            if(!foundCurrentLetter) return null;
        }
        return currentNode.isWord ? currentNode.definition : null;
    }
    
    /**
     * Counts the number of non-null children from a given node.
     * 
     * @param currentNode the starting node
     * @return the number of children from the given node
     */
    private int countChildren(TrieNode currentNode){
        int count = 0;
        for(int i = 0; i < alphabetSize; i++){
            if(currentNode.children[i] != null)
                count++;
        }
        return count;
    }

    /**
     * Deletes the given word from the dictionary.
     * 
     * @param root the root of the trie
     * @param word the word to be deleted
     * @return true if successful, false if otherwise
     */
    public boolean remove(Trie trie, String word){
        TrieNode currentNode = trie.root;
        Stack<TrieNode> nodeDeletionStack = new Stack<>();
        for(char c : word.toCharArray()){
            int childrenArrIndex = letterToIndex(c);
            if(currentNode.children[childrenArrIndex] == null)
                return false;
            currentNode = currentNode.children[childrenArrIndex];
            nodeDeletionStack.push(currentNode);
        }
        if(!currentNode.isWord)
            return false;
        else{
            currentNode.isWord = false;
            currentNode.wordCount--;
            trie.totalWordCount--;
            while(!nodeDeletionStack.isEmpty()){
                currentNode = nodeDeletionStack.pop();
                if(countChildren(currentNode) == 0 && !currentNode.isWord)
                    currentNode.parent.children[letterToIndex(currentNode.letter)] = null;
                else
                    break;
            }
        }
        return true;
    }

    /**
     * Checks if the current prefix is part of a valid word in the Trie.
     * 
     * @param root the root node of the Trie
     * @param currentPrefix the current prefix 
     * @return true if a word exists after the current prefix, false if otherwise
     */
    static boolean doesPrefixExist(Trie trie, String currentPrefix){
        TrieNode currentNode = trie.root;
        int index = 0;
        for(char c : currentPrefix.toCharArray()){
            index = c - 'a'; // converts char into index
            if(currentNode.children[index] == null) 
                return false;
            currentNode = currentNode.children[index];
        }
        return true;
    }


    public String getRandomWord(){
        TrieNode currentNode = this.root;
        StringBuilder word = new StringBuilder();
        String lastCompleteWord = null;
        Random random = new Random();
        while(true){
            ArrayList<TrieNode> possibleWordPaths = new ArrayList<>();
            // for all children of current node, get indexes of possible paths
            for(TrieNode child : currentNode.children){
                if(child != null)
                    possibleWordPaths.add(child);
            }
            // no more paths, return lastCompletedWord
            if(possibleWordPaths.size() == 0) 
                return lastCompleteWord != null ? lastCompleteWord : null;
            // choose child node
            int randomIndex = random.nextInt(possibleWordPaths.size());
            TrieNode selectedNode = possibleWordPaths.get(randomIndex);
            word.append(selectedNode.letter);
            if(selectedNode.isWord)
                lastCompleteWord = word.toString();
            currentNode = selectedNode;
        }
    }

    public static void printTrie(TrieNode node, String word) {
        if (node == null) return;
    
        // If the node represents the end of a word, print it
        if (node.wordCount > 0) {
            System.out.println(word);
        }
    
        // Traverse through all the children nodes recursively
        for (int i = 0; i < alphabetSize; i++) {
            TrieNode child = node.children[i];
            if (child != null) {
                char ch = (char) (i + 'a');
                printTrie(child, word + ch);
            }
        }
    }

    // public static void main(String[] args){
    //     Trie searchTrie = new Trie();
    //     searchTrie.useDictionaryFile("E:\\Coding Proejcts\\word-game\\wordgame\\dictionaries\\test-dictionary-large.txt");
    // }

}
