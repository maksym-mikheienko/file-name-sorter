Library sorts generic lists and writes output to console or into file.
List could be mixed object types. Out of the box ```List<String>``` and ```List<File>``` support provided.

Library is memory-thrifty and doesn't generate duplicate collections. Source list gets sorted thus has to be mutual,
no proxies created for elements.

Advanced configuration permits customizing all three levels:
* entries access
* comparison strategy
* sorting algorithm

To run tests use
```./gradlew test``` or ```./gradlew test -i``` to add some verbosity and check sample output and time metrics

To get help use
```./gradlew run```
this will show the following info
```
Usage:
    ./gradlew run -Pargs="<space separated options>"

    Options:
        -d              Takes as source current directory
        -i <filename>   Input file name
        -o <filename>   Output file name
        -a <algorithm>  Sorting algorithm. Possible values:
                            -a DefaultSortAlgorithm (*default)
                            -a MergeSortAlgorithm
                            -a RandomizedQuicksortSortAlgorithm
        -c <comparator> Comparator. Possible values:
                            -c SimpleStringComparator (*default)
                            -c SimilarityComparator
                            -c WordsAndNumbersComparator

    Example usage:
        ./gradlew run -Pargs="-d -a RandomizedQuicksortSortAlgorithm -c SimilarityComparator"
        ./gradlew run -Pargs="-i input.txt -o output.txt"
```
