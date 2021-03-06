package filesorter.comparator;

import filesorter.FileNameComparatorFactory;
import filesorter.SortEntryKeyGenerator;

import java.util.Comparator;

/**
 * Simple implementation which uses {@link String#compareTo(String)} as comparator which gives natural string order.
 */
public class SimpleStringComparatorFactory implements FileNameComparatorFactory {

    public static FileNameComparatorFactory getInstance() {
        return instance;
    }

    @Override
    public <T> Comparator<T> getComparator(final SortEntryKeyGenerator<T> sortKeyGenerator) {
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

    private static FileNameComparatorFactory instance = new SimpleStringComparatorFactory();
}
