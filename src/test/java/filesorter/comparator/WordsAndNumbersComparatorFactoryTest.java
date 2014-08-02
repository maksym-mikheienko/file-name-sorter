package filesorter.comparator;

import filesorter.FileSortUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordsAndNumbersComparatorFactoryTest {

    WordsAndNumbersComparatorFactory factory;
    Comparator<String> comparator;

    @Before
    public void init() {
        comparator = WordsAndNumbersComparatorFactory.getInstance().getComparator(FileSortUtil.STRING_SORT_KEY_GENERATOR);
        factory = (WordsAndNumbersComparatorFactory) WordsAndNumbersComparatorFactory.getInstance();
    }

    @Test
    public void testComparator() throws Exception {
        // Equals
        assertTrue(comparator.compare("aaa", "aaa") == 0);
        assertTrue(comparator.compare("aaa a.txt", "aaa a.txt") == 0);
        assertTrue(comparator.compare("aaa a 3.txt", "aaa a 3.txt") == 0);
        assertTrue(comparator.compare("aaa a 34b.txt", "aaa a 34b.txt") == 0);
        assertTrue(comparator.compare("aaa a 34b 123 .txt", "aaa a 34b 123 .txt") == 0);

        // Left
        assertTrue(comparator.compare("Aaa", "aaa") < 0);
        assertTrue(comparator.compare("aaa", "aaaa") < 0);
        assertTrue(comparator.compare("aaa", "AAAA") < 0);
        assertTrue(comparator.compare("AAA", "aaaa") < 0);
        assertTrue(comparator.compare("aaa a.aaa", "aaa a.aaaa") < 0);
        assertTrue(comparator.compare("aaa a.aaa", "aaa a3.aaaa") < 0);
        assertTrue(comparator.compare("aaa a.aaa", "aaa a3a.aaaa") < 0);
        assertTrue(comparator.compare("aaa a2.aaa", "aaa a3.aaaa") < 0);
        assertTrue(comparator.compare("aaa a2.aaa", "aaa a11.aaaa") < 0);
        assertTrue(comparator.compare("aaa a22f 123 .aaa", "aaa a22f 124.aaaa") < 0);
        assertTrue(comparator.compare("1", "2") < 0);
        assertTrue(comparator.compare("22", "122") < 0);

        // Right
        assertTrue(comparator.compare("aaa", "Aaa") > 0);
        assertTrue(comparator.compare("aaaa", "aaa") > 0);
        assertTrue(comparator.compare("AAAA", "aaa") > 0);
        assertTrue(comparator.compare("aaaa", "AAA") > 0);
        assertTrue(comparator.compare("aaa a.aaaa", "aaa a.aaa") > 0);
        assertTrue(comparator.compare("aaa a3.aaaa", "aaa a.aaa") > 0);
        assertTrue(comparator.compare("aaa a3a.aaaa", "aaa a.aaa") > 0);
        assertTrue(comparator.compare("aaa a3.aaaa", "aaa a2.aaa") > 0);
        assertTrue(comparator.compare("aaa a11.aaaa", "aaa a2.aaa") > 0);
        assertTrue(comparator.compare("aaa a22f 124.aaaa", "aaa a22f 123 .aaa") > 0);
        assertTrue(comparator.compare("2", "1") > 0);
        assertTrue(comparator.compare("122", "22") > 0);

    }

    @Test(expected = NullPointerException.class)
    public void testFailure() {
        comparator.compare(null, "a");
    }

    @Test(expected = NullPointerException.class)
    public void testFailure2() {
        comparator.compare("b", null);
    }

    @Test
    public void testGetFileExtension() {
        assertEquals("", factory.getFileExtension(""));
        assertEquals("test", factory.getFileExtension(".test"));
        assertEquals("test", factory.getFileExtension("asd.test"));
        assertEquals("test", factory.getFileExtension("asd.test.test"));
        assertEquals("test1", factory.getFileExtension("asd.test....test1"));
    }

    @Test
    public void testParseLong() {
        assertEquals(null, factory.parseLong(null));
        assertEquals(null, factory.parseLong(""));
        assertEquals(null, factory.parseLong("   "));
        assertEquals(null, factory.parseLong("asdasfas"));
        assertEquals(null, factory.parseLong("jg23hg"));
        assertEquals(23, (long) factory.parseLong("23"));
    }
}