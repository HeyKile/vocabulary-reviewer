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

    @Before
    public void setUp(){
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

    @Test
    public void TestRunReview(){
        assertFalse(testReview.runReview());
    }
    
    @Test
    public void testSelectNumQuestions(){
        assertEquals(5, testReview.selectNumQuestions());
    }



    @Test
    public void testCreateAnswerKey(){

    }


    @Test
    public void testGetNumCorrect(){
        assertEquals(0, testReview.getNumCorrect());
    }
    
}
