package heykile.wordgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import static org.junit.Assert.*;

public class DictionaryTest {
    
    Dictionary testDict;
    Trie testTrie;

    @Before
    public void setUp(){

        testTrie = Mockito.mock(Trie.class);
        testDict = new Dictionary();

    }


}
