package filesorter.algorithm;

import org.junit.Before;

public class DefaultSortAlgorithmTest extends SortAlgorithmTest {

    @Before
    public void init() {
        algorithm = DefaultSortAlgorithm.getInstance();
    }
}