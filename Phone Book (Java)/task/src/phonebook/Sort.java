package phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sort {
    private long startBubbleSortTime;
    private long endBubbleSortTime;
    private long startQuickSortTime;
    private long endQuickSortTime;
    private long startCreateHashMapTime;
    private long endCreateHashMapTime;

    public long getBubbleSortDuration() {
        return endBubbleSortTime - startBubbleSortTime;
    }

    public long getQuickSortDuration() {
        return endQuickSortTime - startQuickSortTime;
    }

    public long getCreateHashMapDuration() {
        return endCreateHashMapTime - startCreateHashMapTime;
    }

    public List<String> bubbleSort(List<String> original, long linearSearchDuration) throws OutOfTimeException {
        startBubbleSortTime = System.currentTimeMillis();
        List<String> directory = new ArrayList<>(original);
        long currentSortTime;
        int i, j;
        String temp;
        boolean swapped;
        for (i = 0; i < directory.size() - 1; i++) {
            currentSortTime = System.currentTimeMillis();
            if (currentSortTime - startBubbleSortTime > linearSearchDuration * 10) {
                endBubbleSortTime = currentSortTime;
                throw new OutOfTimeException(" - STOPPED, moved to linear search");
            }
            swapped = false;
            for (j = 0; j < directory.size() - i - 1; j++) {
                if (directory.get(j).substring(directory.get(j).indexOf(" ") + 1).trim().compareTo(directory.get(j + 1).replaceAll("[0-9]", "").trim()) > 0) {
                    temp = directory.get(j);
                    directory.set(j, directory.get(j + 1));
                    directory.set(j + 1, temp);
                    swapped = true;
                }
            }

            // IF no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
        endBubbleSortTime = System.currentTimeMillis();
        return directory;
    }

    // QuickSort for public use
    public List<String> quickSort(List<String> original) {
        startQuickSortTime = System.currentTimeMillis();
        List<String> directory = new ArrayList<>(original);
        quickSortFunction(directory, 0, original.size() - 1);
        endQuickSortTime = System.currentTimeMillis();
        return directory;
    }

    // Recursive part of quickSort
    private void quickSortFunction(List<String> directory, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(directory, begin, end);

            quickSortFunction(directory, begin, partitionIndex - 1);
            quickSortFunction(directory, partitionIndex + 1, end);
        }
    }

    // Partition function of quickSort
    private int partition(List<String> list, int begin, int end) {
        String pivot = list.get(end).substring(list.get(end).indexOf(" ") + 1).trim();
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (list.get(j).substring(list.get(j).indexOf(" ") + 1).trim().compareTo(pivot) <= 0) {
                i++;

                String swapTemp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, swapTemp);
            }
        }

        String swapTemp = list.get(i + 1);
        list.set(i + 1, list.get(end));
        list.set(end, swapTemp);

        return i + 1;
    }

    // Create hashMap
    public Map<String, String> createHashMap(List<String> directory) {
        startCreateHashMapTime = System.currentTimeMillis();
        // Initializing hashMap
        HashMap<String, String> phoneBook = new HashMap<>();

        // Filling hashMap
        for (String line : directory) {
            phoneBook.put(line.substring(line.indexOf(" ") + 1), line.substring(0, line.indexOf(" ")));
        }
        endCreateHashMapTime = System.currentTimeMillis();
        return phoneBook;
    }
}
