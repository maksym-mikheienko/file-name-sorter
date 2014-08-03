package filesorter;

import filesorter.algorithm.DefaultSortAlgorithm;

import java.io.File;
import java.util.List;

/**
 * Entry class for sorting generation. Makes possible to tune logic and has some methods for quick sorting of file and string lists.
 */
public class FileSortUtil {

    public static final SortEntryKeyGenerator<File> FILE_SORT_KEY_GENERATOR = new SortEntryKeyGenerator<File>() {
        @Override
        public String getSortKey(File file) {
            return file.getName();
        }
    };

    public static final SortEntryKeyGenerator<String> STRING_SORT_KEY_GENERATOR = new SortEntryKeyGenerator<String>() {
        @Override
        public String getSortKey(String entry) {
            return entry;
        }
    };

    public static <T> void sort(List<T> list, SortingAlgorithm algorithm, FileNameComparatorFactory comparatorFactory, SortEntryKeyGenerator<T> keyGenerator) {
        algorithm.sort(list, comparatorFactory.getComparator(keyGenerator));
    }

    /**
     * Sorts list with {@link filesorter.algorithm.DefaultSortAlgorithm}
     * @param list
     * @param factory
     * @param keyGenerator
     * @param <T>
     */
    public static <T> void sort(List<T> list, FileNameComparatorFactory factory, SortEntryKeyGenerator<T> keyGenerator) {
        sort(list, DefaultSortAlgorithm.getInstance(), factory, keyGenerator);
    }

    // Quick access routines for files and string sorting

    public static void sortFiles(List<File> files, FileNameComparatorFactory factory) {
        sort(files, factory, FILE_SORT_KEY_GENERATOR);
    }

    public static void sortStrings(List<String> strings, FileNameComparatorFactory factory) {
        sort(strings, factory, STRING_SORT_KEY_GENERATOR);
    }

    private FileSortUtil() {}
}
