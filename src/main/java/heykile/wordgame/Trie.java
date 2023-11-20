/**
 * Class for Trie data structure to be used w/ word games.
 * Adapted from <a href="https://www.geeksforgeeks.org/introduction-to-trie-data-structure-and-algorithm-tutorials/">geeksforgeeks</a>. 
 * 
 * @author Kyle Bello (@HeyKile)
 */

package heykile.wordgame;

public class Trie {

    final int alphabetSize = 26;
    TrieNode root;
    int wordCount;

    public Trie(){
        root = new TrieNode();
        wordCount = 0;
    }

    class TrieNode{
        TrieNode[] children;
        int wordCount;

        public TrieNode(){
            children = new TrieNode[alphabetSize];
            wordCount = 0;
        }
    }
    
    public boolean insert(TrieNode root, String word){
        if(search(root, word)) return false;
        TrieNode currentNode = root;
        int index = 0;
        for(int i = 0; i < word.length(); i++){
            index = word.charAt(i) - 'a'; // converts char into index
            if(currentNode.children[index] == null) 
                currentNode.children[index] = new TrieNode();
            currentNode = currentNode.children[index];
        }
        currentNode.wordCount++;
        return true;
    }
    
    private int countChildrren(TrieNode currentNode){
        int count = 0;
        for(int i = 0; i < alphabetSize; i++){
            if(currentNode.children[i] != null)
                count++;
        }
        return count;
    }

    public boolean deleteWord(TrieNode root, String word){
        TrieNode currentNode = root;
        TrieNode lastBranchNode = null;
        char lastBranchChar = 'a';
        int index = 0;
        for(int i = 0; i < word.length(); i++){
            index = word.charAt(i) - 'a'; // converts char into index
            if(currentNode.children[index] == null)
                return false;
            else{
                int count = countChildrren(currentNode);
                if(count > 1){
                    lastBranchNode = currentNode;
                    lastBranchChar = word.charAt(i);
                }
                currentNode = currentNode.children[index];
            }
        }
        int count = countChildrren(currentNode);
        // deleted word is a prefix to other words
        if(count > 0){
            currentNode.wordCount--;
            return true;
        }
        // deleted word shared a common prefix w/ other words
        else if(lastBranchNode != null){
            lastBranchNode.children[lastBranchChar - 'a'] = null;
            return true;
        }
        // deleted word shares no common prefixes w/ other words
        else{
            root.children[word.charAt(0) - 'a'] = null;
            return true;
        }
    }

    public boolean doesPrefixExist(TrieNode root, String word){
        TrieNode currentNode = root;
        int index = 0;
        for(char c : word.toCharArray()){
            index = c - 'a'; // converts char into index
            if(currentNode.children[index] == null) 
                return false;
            currentNode = currentNode.children[index];
        }
        return true;
    }

    static boolean search(TrieNode root, String word){
        TrieNode currentNode = root;
        int index = 0;
        for(int i = 0; i < word.length(); i++){
            index = word.charAt(i) - 'a';
            if(currentNode.children[index] == null)
                return false;
            currentNode = currentNode.children[index];
        }
        return true;
    }

    public void printTrie(TrieNode node, String word) {
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
        searchTrie.insert(searchTrie.root, "apple");
        searchTrie.insert(searchTrie.root, "banana");
        searchTrie.insert(searchTrie.root, "strawberry");
        searchTrie.printTrie(searchTrie.root, "");
    }

}
