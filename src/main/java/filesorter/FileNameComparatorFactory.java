package filesorter;

import java.util.Comparator;

/**
 * Factory used to create comparators with different implementation. Comparators just compare values and do no sorting.
 */
public interface FileNameComparatorFactory {
    <T> Comparator<T> getComparator(SortEntryKeyGenerator<T> sortKeyGenerator);
}
