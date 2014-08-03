package filesorter.comparator;

import filesorter.FileNameComparatorFactory;
import filesorter.SortEntryKeyGenerator;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

import java.util.Comparator;

/**
 * Experimental implementation using similarity check https://github.com/rrice/java-string-similarity.
 */
public class SimilarityComparatorFactory implements FileNameComparatorFactory {

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

                SimilarityStrategy strategy = new JaroWinklerStrategy();
                StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
                double score = service.score(k1, k2);

                return (int) ((1-score) * 1E5);
            }
        };
    }

    private SimilarityComparatorFactory() {}

    private static FileNameComparatorFactory instance = new SimilarityComparatorFactory();
}
