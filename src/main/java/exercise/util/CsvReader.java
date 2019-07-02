package exercise.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {

    public List<String> read(String filename) throws FileNotFoundException {

        File AccountCsvFile = new File(filename);

        Scanner in = new Scanner(AccountCsvFile);
        List<String> lines = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            lines.add(line);
        }
        in.close();
        return lines;
    }
}
