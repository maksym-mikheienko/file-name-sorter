package filesorter.comparator;

import filesorter.FileSortUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class SimpleStringComparatorFactoryTest {

    Comparator<String> comparator;

    @Before
    public void init() {
        comparator = SimpleStringComparatorFactory.getInstance().getComparator(FileSortUtil.STRING_SORT_KEY_GENERATOR);
    }

    @Test
    public void testComparator() throws Exception {
        assertEquals(0, comparator.compare("aaa", "aaa"));
        assertEquals("aaa".compareTo("bbb"), comparator.compare("aaa", "bbb"));
        assertEquals("ccc".compareTo("bbb"), comparator.compare("ccc", "bbb"));
        assertEquals("".compareTo(""), comparator.compare("", ""));
        assertEquals("".compareTo("e"), comparator.compare("", "e"));
        assertEquals("f".compareTo(""), comparator.compare("f", ""));
    }

    @Test(expected = NullPointerException.class)
    public void testFailure() {
        comparator.compare(null,"a");
    }

    @Test(expected = NullPointerException.class)
    public void testFailure2() {
        comparator.compare("b",null);
    }
}