package phonebook;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Execute {
    private final Search search = new Search();
    private final Sort sort = new Sort();
    private final Input input = new Input();

    public void executeApp() {
        input.scanFiles();

        // Linear Search
        System.out.println("Start searching (linear search)...");
        search.linearSearch(input.getDirectory(), input.getFind());
        printSummary(search.getLinearDuration(), search);
        System.out.println();

        // Bubble sort and Jump Search
        System.out.println("Start searching (bubble sort + jump search)...");
        List<String> sortedDirectory;

        try {
            // Bubble sort
            sortedDirectory = sort.bubbleSort(input.getDirectory(), search.getLinearDuration());
            // Jump-search
            search.jumpSearch(sortedDirectory, input.getFind());
            // Print summaries
            printSummary(sort.getBubbleSortDuration() + search.getJumpDuration(), search);
            printSummary(sort.getBubbleSortDuration(), "Sorting");
            System.out.println();
            printSummary(search.getJumpDuration(), "Searching");
            System.out.println();
            System.out.println();

        } catch (OutOfTimeException e) {
            // Do linear search instead
            search.linearSearch(input.getDirectory(), input.getFind());

            // Print summaries
            printSummary(sort.getBubbleSortDuration() + search.getLinearDuration(), search);
            printSummary(sort.getBubbleSortDuration(), "Sorting");
            System.out.print(e.getMessage());
            System.out.println();
            printSummary(search.getLinearDuration(), "Searching");
            System.out.println();
            System.out.println();
        }

        // QuickSort
        System.out.println("Start searching (quick sort + binary search)...");
        sortedDirectory = sort.quickSort(input.getDirectory());
        // Binary Search
        search.binarySearch(sortedDirectory, input.getFind());
        // Print summaries
        printSummary(sort.getQuickSortDuration() + search.getBinaryDuration(), search);
        printSummary(sort.getQuickSortDuration(), "Sorting");
        System.out.println();
        printSummary(search.getBinaryDuration(), "Searching");
        System.out.println();
        System.out.println();

        // HashMap
        System.out.println("Start searching (hash table)...");
        // Creating hashMap
        Map<String, String> phoneBook = sort.createHashMap(input.getDirectory());
        // Search hashMap
        search.searchHashMap(phoneBook, input.getFind());
        // Print summaries
        printSummary(sort.getCreateHashMapDuration() + search.getHashMapDuration(), search);
        printSummary(sort.getCreateHashMapDuration(), "Creating");
        System.out.println();
        printSummary(search.getHashMapDuration(), "Searching");
    }

    private void printSummary(long duration, Search search) {
        Duration d = Duration.ofMillis(duration);
        int minutes = d.toMinutesPart();
        int seconds = d.toSecondsPart();
        int milliseconds = d.toMillisPart();
        System.out.printf("Found %d/%d entries. Time taken: %d min. %d sec. %d ms.%n", search.getMatchesCount(), input.getFindSize(), minutes, seconds, milliseconds);
    }

    private void printSummary(long duration, String operation) {
        Duration d = Duration.ofMillis(duration);
        int minutes = d.toMinutesPart();
        int seconds = d.toSecondsPart();
        int milliseconds = d.toMillisPart();
        System.out.printf("%s time: %d min. %d sec. %d ms.", operation, minutes, seconds, milliseconds);
    }
}
