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

    public boolean inputDictionary(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                dictTrie.insert(dictTrie.root, line);
            }
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
