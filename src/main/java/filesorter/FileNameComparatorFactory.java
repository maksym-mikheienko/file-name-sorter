package filesorter;

import java.util.Comparator;

/**
 */
public interface FileNameComparatorFactory {
    <T> Comparator<T> getComparator(SortEntryKeyGenerator<T> sortKeyGenerator);
}
