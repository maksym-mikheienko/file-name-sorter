package filesorter.algorithm;

import org.junit.Before;

public class MergeSortAlgorithmTest extends SortAlgorithmTest {

    @Before
    public void init() {
        algorithm = MergeSortAlgorithm.getInstance();
    }
}