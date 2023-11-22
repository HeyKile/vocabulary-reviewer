package heykile.wordgame;

import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class VocabReviewerTest {

    VocabReviewer testReview;
    Trie mockTrie;
    ScannerWrapper mockScanner;

    @Before
    public void setUp(){
        //mocking scanner
        mockScanner = Mockito.mock(ScannerWrapper.class);
        // mocking trie
        mockTrie = Mockito.mock(Trie.class);
        Mockito.when(mockTrie.useDictionaryFile(Mockito.anyString())).thenReturn(true);
        Mockito.when(mockTrie.totalWordCount).thenReturn(10);
        Mockito.when(mockTrie.getRandomWord()).thenReturn("word");
        // creating VocabReviewer
        testReview = new VocabReviewer(mockTrie);
    }

    @After
    public void tearDown(){
        mockTrie = null;
        testReview = null;
    }

    /**
     * Test case for runReview() where user does not want to play again.
     * 
     * Preconditions:
     *  1. A new testReview is created
     * 
     * Execution Steps
     *  1. Run review
     *  2. When asked "How many questions would you like?", input "1" and press enter
     *  3. When asked "Are you ready to begin? y/n", input "n" and press enter
     * 
     * Postconditions:
     *  1. runReview() returns false
     */

    
}
