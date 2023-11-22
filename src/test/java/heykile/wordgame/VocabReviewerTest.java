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
        mockScanner = Mockito.mock(Scanner.class);
        mockTrie = Mockito.mock(Trie.class);
        VocabReviewer.scan = mockScanner;
        testReview = new VocabReviewer(mockTrie);
    }

    @After
    public void tearDown(){
        mockScanner = null;
        mockTrie = null;
        testReview = null;
    }

    @Test
    public void 
    
}
