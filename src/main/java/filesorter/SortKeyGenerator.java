package filesorter;

/**
 * Created by jozh on 02.08.14.
 */
public interface SortKeyGenerator<T> {
    String getSortKey(T  entry);
}
