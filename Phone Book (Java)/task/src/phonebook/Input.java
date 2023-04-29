package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    private final List<String> directory = new ArrayList<>();
    private final List<String> find = new ArrayList<>();

    public List<String> getDirectory() {
        return directory;
    }

    public List<String> getFind() {
        return find;
    }

    public int getFindSize() {
        return find.size();
    }

    public void scanFiles() {
        String pathToDirectory = "directory.txt";
        String pathToFind = "find.txt";
        File directoryFile = new File(pathToDirectory);
        File findFile = new File(pathToFind);


        try (Scanner fileScanner = new Scanner(directoryFile)) {
            while (fileScanner.hasNext()) {
                directory.add(fileScanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        try (Scanner fileScanner = new Scanner(findFile)) {
            while (fileScanner.hasNext()) {
                find.add(fileScanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
