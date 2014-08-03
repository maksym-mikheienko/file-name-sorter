package filesorter.algorithm;

import filesorter.SortingAlgorithm;
import org.psjava.algo.sequence.sort.RandomizedQuicksort;

import java.util.Comparator;
import java.util.List;

/**
 * Uses {@link org.psjava.algo.sequence.sort.RandomizedQuicksort} sorting algorithm.
 */
public class RandomizedQuicksortSortAlgorithm extends PsjavaSortAlgorithm {

    public static SortingAlgorithm getInstance() {
        return instance;
    }

    @Override
    public <T> void sort(final List<T> list, Comparator<T> comparator) {
        RandomizedQuicksort.getInstance().sort(wrapMutableArray(list), comparator);
    }

    private RandomizedQuicksortSortAlgorithm() {}

    private static SortingAlgorithm instance = new RandomizedQuicksortSortAlgorithm();
}
