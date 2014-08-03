package filesorter.console;

import filesorter.FileNameComparatorFactory;
import filesorter.FileSortUtil;
import filesorter.SortEntryKeyGenerator;
import filesorter.SortingAlgorithm;
import filesorter.algorithm.DefaultSortAlgorithm;
import filesorter.algorithm.MergeSortAlgorithm;
import filesorter.algorithm.RandomizedQuicksortSortAlgorithm;
import filesorter.comparator.SimilarityComparatorFactory;
import filesorter.comparator.SimpleStringComparatorFactory;
import filesorter.comparator.WordsAndNumbersComparatorFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class to use library from console
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\n\n");
        try {
            run(args);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid arguments: " + e.getMessage());
            printHelp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run(String[] args) throws Exception {
        if (args.length == 0) {
            printHelp();
            return;
        }

        // default args settings
        boolean isDirectoryDisplay = true;
        String inputFileName = null;
        String outputFileName = null;
        String algorithm = "DefaultSortAlgorithm";
        String comparator = "SimpleStringComparator";

        // analysing arguments
        int i = 0;
        while (i < args.length) {
            String arg = args[i];

            switch (arg) {
                case "-d":      isDirectoryDisplay = true;
                                inputFileName = null;
                                break;
                
                case "-i":      inputFileName = nextArgSafe(args, ++i, arg);
                                isDirectoryDisplay = false;
                                break;
                
                case "-o":      outputFileName = nextArgSafe(args, ++i, arg);
                                break;
                
                case "-a":      algorithm = nextArgSafe(args, ++i, arg);
                                break;

                case "-c":      comparator = nextArgSafe(args, ++i, arg);
                                break;

                default:        throw new IllegalArgumentException("Invalid argument '" + arg + "'");
            }

            i++;
        }

        doSorting(isDirectoryDisplay, inputFileName, outputFileName, algorithm, comparator);
    }

    private static void doSorting(boolean isDirectoryDisplay, String inputFileName, String outputFileName, String algorithmName, String comparatorName) throws IOException {
        SortingAlgorithm algorithm = DefaultSortAlgorithm.getInstance();
        switch (algorithmName) {
            case "DefaultSortAlgorithm":                break;
            case "MergeSortAlgorithm":                  algorithm = MergeSortAlgorithm.getInstance(); break;
            case "RandomizedQuicksortSortAlgorithm":    algorithm = RandomizedQuicksortSortAlgorithm.getInstance(); break;
            default:                                    throw new IllegalArgumentException("Invalid algorithm '" + algorithmName + "'");
        }

        FileNameComparatorFactory comparatorFactory = SimpleStringComparatorFactory.getInstance();
        switch (comparatorName) {
            case "SimpleStringComparator":              break;
            case "SimilarityComparator":                comparatorFactory = SimilarityComparatorFactory.getInstance(); break;
            case "WordsAndNumbersComparator":           comparatorFactory = WordsAndNumbersComparatorFactory.getInstance(); break;
            default:                                    throw new IllegalArgumentException("Invalid comparator '" + comparatorName + "'");
        }

        if (isDirectoryDisplay) {
            List<File> files = new ArrayList<>(FileUtils.listFiles(new File( System.getenv().get("HOME") ), null, false));
            FileSortUtil.sort(files, algorithm, comparatorFactory, FileSortUtil.FILE_SORT_KEY_GENERATOR);

            try ( OutputStream outStream = outputFileName != null ?  FileUtils.openOutputStream(new File(outputFileName)) : System.out) {
                for (File file : files) {
                    outStream.write( (file.getName() + "\n").getBytes("utf8") );
                }
            }
        } else {
            List<String> strings = FileUtils.readLines(new File(inputFileName), "utf8");
            FileSortUtil.sort(strings, algorithm, comparatorFactory, FileSortUtil.STRING_SORT_KEY_GENERATOR);

            try ( OutputStream outStream = outputFileName != null ?  FileUtils.openOutputStream(new File(outputFileName)) : System.out) {
                for (String s : strings) {
                    outStream.write( (s + "\n").getBytes("utf8") );
                }
            }
        }
    }

    private static String nextArgSafe(String[] args, int i, String arg) {
        if (args.length <= i)
            throw new IllegalArgumentException("Missing argument afer " + arg);
        
        return args[i];
    }

    private static void printHelp() {
        try {
            System.out.println(FileUtils.readFileToString(
                    new File(Main.class.getClassLoader().getResource("console-help.txt").toURI())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
