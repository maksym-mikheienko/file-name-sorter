package filesorter;

import java.util.Comparator;
import java.util.List;

/**
 * Created by jozh on 02.08.14.
 */
public interface SortingAlgorithm {
    <T> void sort(List<T> list, Comparator<T> c);
}
