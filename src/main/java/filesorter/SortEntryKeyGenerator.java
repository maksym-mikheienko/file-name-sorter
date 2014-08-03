package filesorter;

/**
 * Defines the way how to extract String key from collection entry. For example from the {@code File} we take file name,
 * from simple {@code String} - itself.
 */
public interface SortEntryKeyGenerator<T> {
    String getSortKey(T  entry);
}
