package filesorter.algorithm;

import filesorter.SortingAlgorithm;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import static org.junit.Assert.assertArrayEquals;

public abstract class SortAlgorithmTest {
    public static final int TEST_ARRAY_LENGTH = 100000;

    protected SortingAlgorithm algorithm;

    @Test
    public void test() {
        // initial array
        List<String> source = new ArrayList<>(TEST_ARRAY_LENGTH);
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++)
            source.add( RandomStringUtils.random(ThreadLocalRandom.current().nextInt(30)) );

        List<String> refResult = new ArrayList<>(source);
        Collections.sort(refResult);

        long now = System.currentTimeMillis();

        List<String> result = new ArrayList<>(source);
        algorithm.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        log.info("Sorting for " + algorithm.getClass().getName() + " took " + (System.currentTimeMillis() - now) + "ms.");

        assertArrayEquals(refResult.toArray(), result.toArray());
    }

    private static Logger log = Logger.getLogger(SortAlgorithmTest.class.getName());
}
