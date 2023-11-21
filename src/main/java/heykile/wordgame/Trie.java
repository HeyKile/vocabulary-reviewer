/**
 * Class for Trie data structure to be used w/ word games.
 * Adapted from <a href="https://www.geeksforgeeks.org/introduction-to-trie-data-structure-and-algorithm-tutorials/">geeksforgeeks</a>. 
 * 
 * @author Kyle Bello (@HeyKile)
 */



// TODO:
//  1. do bounds checking of letters to ensure valid


package heykile.wordgame;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trie {

    final static int alphabetSize = 26;
    TrieNode root;
    int wordCount;

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
        wordCount = 0;
    }

    /**
     * Builds a trie dictionary using an input file.
     * Dictionary entries must be in the form "word (space) this is the definiton".
     * 
     * @param fileName the path to the input file
     * @return true if sucessful, false otherwise
     */
    public boolean useDictionaryFile(String fileName){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            while((line = br.readLine()) != null){
                int spaceIndex = line.indexOf(" ");
                if(spaceIndex > -1){
                    String word = line.substring(0, spaceIndex);
                    String def = line.substring(spaceIndex + 1);
                    Trie.insert(this, word, def);
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
     * Converts letter(a-z) into index for child array
     * 
     * @param c
     * @return index of letter in children array
     */
    private static int letterToIndex(char c){
        return c - 'a';
    }

    /**
     * Inserts a word into the Trie.
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
        currentNode.isWord = true;
        currentNode.wordCount++;
        currentNode.definition = definition;
    }


    /**
     * Searches the given Trie for a specific word.
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
        while(true){
            List<TrieNode> possibleNextNodes = new ArrayList<>();
            for(TrieNode child : currentNode.children){
                if(child != null) possibleNextNodes.add(child);
            }
            if(possibleNextNodes.isEmpty()) break;
            int randomIndex = new Random().nextInt(possibleNextNodes.size());
            TrieNode selectedNode = possibleNextNodes.get(randomIndex);
            currentNode = selectedNode;
        }
        return word.length() > 0 ? word.toString() : null;
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

    public static void main(String[] args){
        Trie searchTrie = new Trie();
        Trie.insert(searchTrie, "apple", "");
        // Trie.insert(searchTrie, "banana", "");
        // Trie.insert(searchTrie, "strawberry", "");
        // Trie.printTrie(searchTrie.root, "");
    }

}
