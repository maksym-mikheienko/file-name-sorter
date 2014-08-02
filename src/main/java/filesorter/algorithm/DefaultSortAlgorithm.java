package filesorter.algorithm;

import filesorter.SortingAlgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Uses java collections default sorting algorithm.
 */
public class DefaultSortAlgorithm implements SortingAlgorithm {

    public static SortingAlgorithm getInstance() {
        return instance;
    }

    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {
        Collections.sort(list, comparator);
    }

    private DefaultSortAlgorithm() {}

    private static SortingAlgorithm instance = new DefaultSortAlgorithm();
}
