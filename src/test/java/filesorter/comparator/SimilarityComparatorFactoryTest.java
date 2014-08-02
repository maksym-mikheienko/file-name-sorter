package filesorter.comparator;

import filesorter.FileSortUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimilarityComparatorFactoryTest {

    Comparator<String> comparator;

    @Before
    public void init() {
        comparator = SimilarityComparatorFactory.getInstance().getComparator(FileSortUtil.STRING_SORT_KEY_GENERATOR);
    }

    @Test
    public void testComparator() throws Exception {
        assertEquals(0, comparator.compare("aaa", "aaa"));
        assertTrue(comparator.compare("McDonalds", "MacDonalds") < comparator.compare("McDonalds", "MacDohalds"));
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