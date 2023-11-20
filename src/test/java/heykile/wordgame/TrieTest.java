package heykile.wordgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Unit tests for Trie.java
 */
public class TrieTest {
    
    Trie testTrie;

    @Before
    public void setUp(){
        testTrie = new Trie();
        testTrie.insert(testTrie.root, "apple");
        testTrie.insert(testTrie.root, "banana");
        testTrie.insert(testTrie.root, "strawberry");
    }

    @After
    public void tearDown(){
        testTrie = null;
    }

    @Test
    public void testTrieInsert(){
        assertTrue(testTrie.insert(testTrie.root, "pear"));
        assertFalse(testTrie.insert(testTrie.root, "apple"));
    }

    @Test
    public void testDeleteWord(){
        assertTrue(testTrie.deleteWord(testTrie.root, "apple"));
        assertFalse(testTrie.deleteWord(testTrie.root, "pear"));
    }

    @Test
    public void testDoesPrefixExist(){
        assertTrue(testTrie.doesPrefixExist(testTrie.root, "ap"));
        assertTrue(testTrie.doesPrefixExist(testTrie.root, "ban"));
        assertFalse(testTrie.doesPrefixExist(testTrie.root, "x"));
    }

    @Test
    public void testSearch(){
        assertTrue(Trie.search(testTrie.root, "strawberry"));
        assertFalse(Trie.search(testTrie.root, "fortnite"));
    }

}
