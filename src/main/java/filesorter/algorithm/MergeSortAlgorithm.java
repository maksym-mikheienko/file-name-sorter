package filesorter.algorithm;

import filesorter.SortingAlgorithm;
import org.psjava.algo.sequence.sort.MergeSort;

import java.util.Comparator;
import java.util.List;

/**
 * Uses java collections default sorting algorithm.
 */
public class MergeSortAlgorithm extends PsjavaSortAlgorithm {

    public static SortingAlgorithm getInstance() {
        return instance;
    }

    @Override
    public <T> void sort(final List<T> list, Comparator<T> comparator) {
        MergeSort.getInstance().sort(wrapMutableArray(list), comparator);
    }

    private MergeSortAlgorithm() {}

    private static SortingAlgorithm instance = new MergeSortAlgorithm();
}
