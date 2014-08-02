package filesorter;

import java.util.Comparator;

/**
 * Created by jozh on 02.08.14.
 */
public interface FileNameComparatorFactory {
    <T> Comparator<T> getComparator(SortKeyGenerator<T> sortKeyGenerator);
}
