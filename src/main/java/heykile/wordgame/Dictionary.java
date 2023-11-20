package heykile.wordgame;

import java.io.*;

public class Dictionary {
    
    Trie dictTrie;
    int wordCount;
    
    public Dictionary(){
        this(new Trie());
    }

    public Dictionary(Trie trie){
        dictTrie = trie;
        wordCount = 0;
    }

    

}
