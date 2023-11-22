package heykile.wordgame;

import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class VocabReviewerTest {
    
    VocabReviewer testReview;
    Scanner mockScanner;
    Trie mockTrie;

    @Before
    public void setUp(){
        // mocking scanner
        mockScanner = Mockito.mock(Scanner.class);
        VocabReviewer.scan = mockScanner;
        // mocking trie
        mockTrie = Mockito.mock(Trie.class);
        Mockito.when(mockTrie.useDictionaryFile(Mockito.anyString())).thenReturn(true);
        Mockito.when(mockTrie.totalWordCount).thenReturn(10);
        Mockito.when(mockTrie.getRandomWord()).thenReturn("word");
        Mockito.when(mockScanner.nextInt()).thenReturn(5);
        // creating VocabReviewer
        testReview = new VocabReviewer(mockTrie);
    }

    @After
    public void tearDown(){
        mockScanner = null;
        mockTrie = null;
        testReview = null;
    }

    @Test
    public void TestRunReview(){
        Mockito.when(mockScanner.nextLine()).thenReturn("y");
        assertTrue(testReview.runReview());
    }
    
}
