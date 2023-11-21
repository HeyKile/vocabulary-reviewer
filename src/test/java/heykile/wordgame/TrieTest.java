package heykile.wordgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Trie.java
 */
public class TrieTest {
    
    Trie testTrie;

    @Before
    public void setUp(){
        testTrie = new Trie();
        Trie.insert(testTrie, "apple", "a red fruit grown in Autumn");
        Trie.insert(testTrie, "banana", "a popular fruit among primates");
        Trie.insert(testTrie, "strawberry", "funnily enough, not a berry");
    }

    @After
    public void tearDown(){
        testTrie = null;
    }


    @Test
    public void testTrieInsert(){
        testTrie = new Trie();
        Trie.insert(testTrie, "pear", "Living in apple's shadow");
        Trie.insert(testTrie, "apple", "a different apple def");
        assertTrue(Trie.search(testTrie, "pear"));
        assertTrue(Trie.search(testTrie, "pear"));
    }

    @Test
    public void testSearch(){
        assertTrue(Trie.search(testTrie, "apple"));
        assertTrue(Trie.search(testTrie, "banana"));
        assertTrue(Trie.search(testTrie, "strawberry"));
        assertFalse(Trie.search(testTrie, "fortnite"));
    }

    @Test
    public void testDeleteWord(){
        assertTrue(testTrie.remove(testTrie, "apple"));
        assertFalse(testTrie.remove(testTrie, "pear"));
        assertFalse(Trie.search(testTrie, "apple"));
    }

    @Test
    public void testRemoveDeletePrefix(){
        Trie.insert(testTrie, "app", "a small, useable peice of software");
        testTrie.remove(testTrie, "app");
        assertFalse(Trie.search(testTrie, "app"));
        assertTrue(Trie.search(testTrie, "apple"));
    }

    @Test
    public void testDoesPrefixExist(){
        assertTrue(Trie.doesPrefixExist(testTrie, "ap"));
        assertTrue(Trie.doesPrefixExist(testTrie, "ban"));
        assertFalse(Trie.doesPrefixExist(testTrie, "x"));
    }

    @Test
    public void testInputDictionary(){
        testTrie = new Trie();
        assertTrue(testTrie.useDictionaryFile("E:\\Coding Proejcts\\word-game\\wordgame\\dictionaries\\test-dictionary.txt"));
        assertTrue(Trie.search(testTrie, "peach"));
        assertTrue(Trie.search(testTrie, "banana"));
        assertFalse(Trie.search(testTrie, "pear"));
    }

}
