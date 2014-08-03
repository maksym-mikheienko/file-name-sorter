package filesorter;

import java.util.Comparator;
import java.util.List;

/**
 * Sorting algorithm execution point.
 */
public interface SortingAlgorithm {
    <T> void sort(List<T> list, Comparator<T> c);
}
