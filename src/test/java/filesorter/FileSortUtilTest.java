package filesorter;

import filesorter.comparator.SimpleStringComparatorFactory;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertArrayEquals;

public class FileSortUtilTest {

    @Test
    public void basicStringPassThroughCheck() throws IOException, URISyntaxException {
        List<String> source = FileUtils.readLines(new File( this.getClass().getClassLoader().getResource("string-basic-compare.txt").toURI() ));

        List<String> refResult = new ArrayList<>(source);
        Collections.sort(refResult);

        List<String> result = new ArrayList<>(source);
        FileSortUtil.sortStrings(result, SimpleStringComparatorFactory.getInstance());

        if (log.isLoggable(Level.INFO))
            log.info("Sorting result:\n" + result.toString().replace(", ", "\n"));

        assertArrayEquals(refResult.toArray(), result.toArray());
    }

    @Test
    public void basicFilePassThroughCheck() throws IOException, URISyntaxException {
        List<File> source = new ArrayList<>(FileUtils.listFiles(new File( System.getenv().get("HOME") ), null, false));

        List<File> refResult = new ArrayList<>(source);
        Collections.sort(refResult);

        List<File> result = new ArrayList<>(source);
        FileSortUtil.sortFiles(result, SimpleStringComparatorFactory.getInstance());

        assertArrayEquals(refResult.toArray(), result.toArray());
    }

    private static Logger log = Logger.getLogger(FileSortUtilTest.class.getName());
}
