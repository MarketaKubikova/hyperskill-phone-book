package phonebook;

import java.util.List;
import java.util.Map;

public class Search {
    private int matchesCount;
    private long startLinearTime;
    private long endLinearTime;
    private long startJumpTime;
    private long endJumpTime;
    private long startBinaryTime;
    private long endBinaryTime;
    private long startHashMapTime;
    private long endHashMapTime;

    public long getLinearDuration() {
        return endLinearTime - startLinearTime;
    }

    public long getJumpDuration() {
        return endJumpTime - startJumpTime;
    }

    public long getBinaryDuration() {
        return endBinaryTime - startBinaryTime;
    }

    public long getHashMapDuration() {
        return endHashMapTime - startHashMapTime;
    }

    public int getMatchesCount() {
        return matchesCount;
    }

    public void linearSearch(List<String> directory, List<String> find) {
        matchesCount = 0;
        startLinearTime = System.currentTimeMillis();
        for (String value : find) {
            for (String s : directory) {
                if (s.substring(s.indexOf(" ") + 1).trim().equals(value)) {
                    matchesCount++;
                    break;
                }
            }
        }
        endLinearTime = System.currentTimeMillis();
    }

    public void jumpSearch(List<String> directory, List<String> find) {
        startJumpTime = System.currentTimeMillis();
        matchesCount = 0;
        int n = directory.size();
        // Finding block size to be jumped
        int step = (int) Math.floor(Math.sqrt(n));

        for (String name : find) {
            // If the element is present find the block where element is present
            int prevItem = 0;
            boolean notFound = false;

            while (directory.get(Math.min(step, n) - 1).substring(directory.get(Math.min(step, n) - 1).indexOf(" ") + 1).trim().compareTo(name) < 0) {
                prevItem = step;
                step += (int) Math.floor(Math.sqrt(n));
                if (prevItem >= n)
                    notFound = true;
            }
            if (notFound) continue;

            // Using a linear search for element in block beginning with previous item
            while (directory.get(prevItem).substring(directory.get(prevItem).indexOf(" ") + 1).trim().compareTo(name) < 0) {
                prevItem++;
                if (prevItem == Math.min(step, n))
                    notFound = true;
            }
            if (notFound) continue;

            // If element is found
            if (directory.get(prevItem).substring(directory.get(prevItem).indexOf(" ") + 1).trim().equals(name))
                matchesCount++;

        }
        endJumpTime = System.currentTimeMillis();
    }

    public void binarySearch(List<String> directory, List<String> find) {
        startBinaryTime = System.currentTimeMillis();
        matchesCount = 0;
        for (String name : find) {

            int low = 0;
            int high = directory.size() - 1;

            while (low <= high) {
                int mid = low + ((high - low) / 2);
                if (directory.get(mid).substring(directory.get(mid).indexOf(" ") + 1).trim().compareTo(name) < 0) {
                    low = mid + 1;
                } else if (directory.get(mid).substring(directory.get(mid).indexOf(" ") + 1).trim().compareTo(name) > 0) {
                    high = mid - 1;
                } else if (directory.get(mid).substring(directory.get(mid).indexOf(" ") + 1).trim().equals(name)) {
                    matchesCount++;
                    break;
                }
            }
        }
        endBinaryTime = System.currentTimeMillis();
    }

    public void searchHashMap(Map<String, String> phoneBook, List<String> find) {
        startHashMapTime = System.currentTimeMillis();
        matchesCount = 0;
        for (String name : find) {
            if (phoneBook.get(name) != null) {
                matchesCount++;
            }
        }
        endHashMapTime = System.currentTimeMillis();
    }
}
