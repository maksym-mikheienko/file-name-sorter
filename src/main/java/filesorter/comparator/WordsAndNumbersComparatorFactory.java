package filesorter.comparator;

import filesorter.FileNameComparatorFactory;
import filesorter.SortEntryKeyGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Comparator;

/**
 * Comparator implementation from test task. Separately processes text and number parts. Text parts are grouped ignoring case
 * while inside group case counts.
 */
public class WordsAndNumbersComparatorFactory implements FileNameComparatorFactory {

    public static final String NUMBERS = "0124356789";

    public static FileNameComparatorFactory getInstance() {
        return instance;
    }

    @Override
    public <T> Comparator<T> getComparator(final SortEntryKeyGenerator<T> sortKeyGenerator) {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 == null || o2 == null )
                    throw new NullPointerException();

                String k1 = sortKeyGenerator.getSortKey(o1);
                String k2 = sortKeyGenerator.getSortKey(o2);

                return doCompare(k1, k2);
            }
        };
    }

    protected int doCompare(String k1, String k2) {
        // Cutting extensions
        String ext1 = getFileExtension(k1),
               ext2 = getFileExtension(k2);

        if (ext1.length() > 0)
            k1 = k1.substring(0, k1.length() - ext1.length() - 1);
        if (ext2.length() > 0)
            k2 = k2.substring(0, k2.length() - ext2.length() - 1);

        while (k1.length() > 0 && k2.length() > 0) {

            // finding number index
            int numStart1 = StringUtils.indexOfAny(k1, NUMBERS),
                numStart2 = StringUtils.indexOfAny(k2, NUMBERS);

            // Getting text before numbers
            String text1 = k1,
                   text2 = k2;
            if (numStart1 >= 0)
                text1 = text1.substring(0, numStart1);
            if (numStart2 >= 0)
                text2 = text2.substring(0, numStart2);

            // Comparing text before numbers
            if (!text1.equalsIgnoreCase(text2))
                return text1.compareToIgnoreCase(text2);
            else if (text1.equalsIgnoreCase(text2) && !text1.equals(text2))
                return text1.compareTo(text2);

            k1 = k1.substring(text1.length());
            k2 = k2.substring(text2.length());

            if (k1.length() > 0 && k2.length() > 0) {
                // Getting number finish positions
                int numFinish1 = StringUtils.indexOfAnyBut(k1, NUMBERS),
                    numFinish2 = StringUtils.indexOfAnyBut(k2, NUMBERS);

                // Getting number text
                text1 = k1;
                text2 = k2;
                if (numFinish1 >= 0)
                    text1 = text1.substring(0, numFinish1);
                if (numFinish2 >= 0)
                    text2 = text2.substring(0, numFinish2);

                Long num1 = parseLong(text1),
                     num2 = parseLong(text2);

                if (num1 == null && num2 == null)
                    continue;
                if (num1 == null)
                    return 1;
                if (num2 == null)
                    return -1;
                if (!num1.equals(num2))
                    return num1.compareTo(num2);

                k1 = k1.substring(text1.length());
                k2 = k2.substring(text2.length());
            }
        }

        return k1.length() > 0 || k2.length() > 0 ?
               k1.compareTo(k2) :
               ext1.compareTo(ext2);
    }

    protected Long parseLong(String number) {
        Long n = null;
        try {
            n = NumberUtils.createLong(number);
        } catch (NumberFormatException ignored) {}
        return n;
    }

    protected String getFileExtension(String s) {
        int i = s.lastIndexOf('.');

        if (i >= 0)
            return s.substring(i + 1);

        return "";
    }

    private WordsAndNumbersComparatorFactory() {}

    private static FileNameComparatorFactory instance = new WordsAndNumbersComparatorFactory();
}
