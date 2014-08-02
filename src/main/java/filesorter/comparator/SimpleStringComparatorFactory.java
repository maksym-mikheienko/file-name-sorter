package filesorter.comparator;

import filesorter.FileNameComparatorFactory;
import filesorter.SortKeyGenerator;

import java.util.Comparator;

/**
 * Created by jozh on 02.08.14.
 */
public class SimpleStringComparatorFactory implements FileNameComparatorFactory {

    public static SimpleStringComparatorFactory getInstance() {
        return instance;
    }

    @Override
    public <T> Comparator<T> getComparator(final SortKeyGenerator<T> sortKeyGenerator) {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                String k1 = sortKeyGenerator.getSortKey(o1);
                String k2 = sortKeyGenerator.getSortKey(o2);
                return k1.compareTo(k2);
            }
        };
    }

    private SimpleStringComparatorFactory() {}

    private static SimpleStringComparatorFactory instance = new SimpleStringComparatorFactory();
}
