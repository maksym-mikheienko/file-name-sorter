package filesorter;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class FileSortUtil {

    private FileSortUtil() {}

    public static <T> void sort(List<T> list, FileNameComparatorFactory factory, SortKeyGenerator<T> keyGenerator) {
        Collections.sort(list, factory.getComparator(keyGenerator));
    }

    public static void sortFiles(List<File> files, FileNameComparatorFactory comparator) {
        sort(files, comparator, FILE_SORT_KEY_GENERATOR);
    }

    public static void sortStrings(List<String> strings, FileNameComparatorFactory comparator) {
        sort(strings, comparator, STRING_SORT_KEY_GENERATOR);
    }

    private static final SortKeyGenerator<File> FILE_SORT_KEY_GENERATOR = new SortKeyGenerator<File>() {
        @Override
        public String getSortKey(File file) {
            return file.getName();
        }
    };

    private static final SortKeyGenerator<String> STRING_SORT_KEY_GENERATOR = new SortKeyGenerator<String>() {
        @Override
        public String getSortKey(String entry) {
            return entry;
        }
    };
}
