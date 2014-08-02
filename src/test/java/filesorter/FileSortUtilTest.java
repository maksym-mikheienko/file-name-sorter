package filesorter;

import filesorter.comparator.SimpleStringComparatorFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FileSortUtilTest {

    @Test
    public void basicStringPassThroughCheck() throws IOException, URISyntaxException {
        List<String> source = FileUtils.readLines(new File( this.getClass().getClassLoader().getResource("string-basic-compare.txt").toURI() ));

        List<String> result1 = new ArrayList<>(source);
        FileSortUtil.sortStrings(result1, SimpleStringComparatorFactory.getInstance());

        List<String> result2 = new ArrayList<>(source);
        Collections.sort(result2);

        assertArrayEquals(result1.toArray(), result2.toArray());
    }

    @Test
    public void basicFilePassThroughCheck() throws IOException, URISyntaxException {
        List<File> source = new ArrayList<>(FileUtils.listFiles(new File( System.getenv().get("HOME") ), null, false));

        List<File> result1 = new ArrayList<>(source);
        FileSortUtil.sortFiles(result1, SimpleStringComparatorFactory.getInstance());

        List<File> result2 = new ArrayList<>(source);
        Collections.sort(result2);

        assertArrayEquals(result1.toArray(), result2.toArray());
    }

    @Test
    public void testSortStrings() throws Exception {
        // test double pass
        // test bounding conditions
        // test thread safety
    }
}