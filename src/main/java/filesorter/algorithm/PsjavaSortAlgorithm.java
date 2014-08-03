package filesorter.algorithm;

import filesorter.SortingAlgorithm;
import org.psjava.ds.array.MutableArray;

import java.util.Iterator;
import java.util.List;

/**
 * Base utility class for algorithms implementation for ps-java
 */
public abstract class PsjavaSortAlgorithm implements SortingAlgorithm {

    protected <T> MutableArray<T> wrapMutableArray(final List<T> list) {
        return new MutableArray<T>() {
            @Override
            public void set(int index, T value) {
                list.set(index, value);
            }

            @Override
            public T get(int index) {
                return list.get(index);
            }

            @Override
            public int size() {
                return list.size();
            }

            @Override
            public boolean isEmpty() {
                return list.isEmpty();
            }

            @Override
            public Iterator<T> iterator() {
                return list.iterator();
            }
        };
    }
}
