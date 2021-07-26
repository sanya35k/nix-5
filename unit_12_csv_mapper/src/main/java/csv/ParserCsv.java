package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserCsv {
    String file;
    public ParserCsv(String file) {
        this.file = file;
    }

    public String get(int row, String title) {
        return core.get(row - 1)[titles.get(title)];
    }
    public int getSize() {
        return core.size();
    }
    public String get(int row, int cell) {
        return core.get(row - 1)[cell];
    }

    private Map<String, Integer> titles;
    private List<String[]> core;

    public void parse() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            titles = new HashMap<>();
            String[] headersArray = bufferedReader.readLine().split(",");
            for (int i = 0; i < headersArray.length; i++) {
                titles.put(headersArray[i], i);
            }
            core = new ArrayList<>();
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                core.add(nextLine.split(","));
            }
        } catch (IOException e) {
            System.out.println("The file does not meet the requirements!");
        }
    }
}