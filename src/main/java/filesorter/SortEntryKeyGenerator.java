package filesorter;

/**
 * Created by jozh on 02.08.14.
 */
public interface SortEntryKeyGenerator<T> {
    String getSortKey(T  entry);
}
