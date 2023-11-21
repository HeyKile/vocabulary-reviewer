package heykile.wordgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Trie.java
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

    /**
     * Test case for insert where 2 words are entered into the trie.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     * 
     * Execution Steps
     *  1. call insert() with word "pear" with definition "a green fruit"
     *  2. call insert() with word "apple" with definition "a red autumnal classic"
     * 
     * Postconditions:
     *  1. the number of words in the trie is 2
     */
    @Test
    public void testTrieInsert(){
        testTrie = new Trie();
        Trie.insert(testTrie, "pear", "a green fruit");
        Trie.insert(testTrie, "apple", "a red autumnal classic");
        assertEquals(testTrie.totalWordCount, 2);
    }

    /**
     * Test case for insert where a duplicate word is entered into the trie.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     *  2. trie object contains the words "apple", "banana", and "strawberry"
     * 
     * Execution Steps
     *  1. call insert() with word "apple" and definition "a red fruit grown in Autumn"
     * 
     * Postconditions:
     *  1. the number of words in the trie is 3
     */
    @Test
    public void testTrieInsertDuplicate(){
        Trie.insert(testTrie, "apple", "a red fruit grown in Autumn");
        assertEquals(3, testTrie.totalWordCount);
    }

    /**
     * Test case for search.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     *  2. trie object contains the words "apple", "banana", and "strawberry"
     * 
     * Execution Steps
     *  1. call search() with word "banana"
     * 
     * Postconditions:
     *  1. search() returns true
     */
    @Test
    public void testSearch(){
        assertTrue(Trie.search(testTrie, "banana"));
    }

    /**
     * Test case for search where the word does not exist in the trie.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     *  2. trie object contains the words "apple", "banana", and "strawberry"
     * 
     * Execution Steps
     *  1. call search() with word "blueberry"
     * 
     * Postconditions:
     *  1. search() returns false
     */
    @Test
    public void testSearchWordDNE(){
        assertFalse(Trie.search(testTrie, "blueberry"));
    }

    /**
     * Test case for remove() where a word in the trie is deleted.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     *  2. trie object contains the words "apple", "banana", and "strawberry"
     * 
     * Execution Steps
     *  1. call remove with word "apple"
     * 
     * Postconditions:
     *  1. remove() returns true
     *  2. number of words in the trie is 2
     */
    @Test
    public void testRemove(){
        assertTrue(testTrie.remove(testTrie, "apple"));
        assertEquals(2, testTrie.totalWordCount);
    }

    /**
     * Test case for remove() where a word in the trie is deleted that is a prefix to another word.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     *  2. trie object contains the words "apple", "banana", "strawberry"
     * 
     * Execution Steps
     *  1. call insert() with the word "app" and the definition "a small, useable peice of software"
     *  2. call remove() with word "app"
     * 
     * Postconditions:
     *  1. remove() returns true
     *  2. number of words in the trie is 3
     *  3. "apple" still exists in the trie
     */
    @Test
    public void testRemoveDeletePrefix(){
        Trie.insert(testTrie, "app", "a small, useable peice of software");
        assertTrue(testTrie.remove(testTrie, "app"));
        assertEquals(3, testTrie.totalWordCount);
        assertTrue(Trie.search(testTrie, "apple"));
    }

    /**
     * Test case for doesPrefixExist() on a prefix for a word that exists in the trie.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     *  2. trie object contains the words "apple", "banana", "strawberry"
     * 
     * Execution Steps
     *  1. call doesPrefixExist() with prefix "straw"
     * 
     * Postconditions:
     *  1. doesPrefixExist() returns true
     */
    @Test
    public void testDoesPrefixExist(){
        assertTrue(Trie.doesPrefixExist(testTrie, "straw"));
    }

    /**
     * Test case for doesPrefixExist() on a prefix for a word that does not exist in the trie.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     *  2. trie object contains the words "apple", "banana", "strawberry"
     * 
     * Execution Steps
     *  1. call doesPrefixExist() with prefix "pe"
     * 
     * Postconditions:
     *  1. doesPrefixExist() returns false
     */
    @Test
    public void testDoesPrefixExistPrefixDNE(){
        assertFalse(Trie.doesPrefixExist(testTrie, "pe"));
    }

    /**
     * Test case for useInputDictionary() on a test dictionary txt file.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     * 
     * Execution Steps
     *  1. useDictionaryFile() with the filepath to test-dictionary.txt (in the dictionaries file of this repository)
     * 
     * Postconditions:
     *  1. the number of words in the trie is 5
     *  2. the word "peach" exists in the trie
     */
    @Test
    public void testUseInputDictionary(){
        testTrie = new Trie();
        assertTrue(testTrie.useDictionaryFile("E:\\Coding Proejcts\\word-game\\wordgame\\dictionaries\\test-dictionary.txt"));
        assertEquals(5, testTrie.totalWordCount);
        assertTrue(Trie.search(testTrie, "peach"));
    }

    /**
     * Test case for useInputDictionary() on a test dictionary txt file.
     * 
     * Preconditions:
     *  1. a new Trie object is created
     *  2. trie object contains the words "apple", "banana", "strawberry"
     * 
     * Execution Steps
     *  1. call getRandomWord()
     * 
     * Postconditions:
     *  1. getRandomWord() returns a non-null string
     *  2, getRandomWord() returns a random string in the Trie
     */
    @Test
    public void testGetRandomWord(){
        String result = testTrie.getRandomWord();
        assertNotNull(result);
        assertTrue(Trie.search(testTrie, result));
    }

}
