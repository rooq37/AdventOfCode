package y_2020.day5;

import helpers.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day5/input.txt");
        List<Integer> ids = day5a(input);
        System.out.println("Day 5a: " + Collections.max(ids));
        System.out.println("Day 5b: " + day5b(ids));
    }

    public static List<Integer> day5a(List<String> input) {
        List<Integer> ids = new ArrayList<>();
        for(String row : input) {
            String newRow = row
                    .replaceAll("B", "1")
                    .replaceAll("F", "0")
                    .replaceAll("R", "1")
                    .replaceAll("L", "0");

            int rowNum = Integer.parseInt(newRow.substring(0, 7),2);
            int colNum = Integer.parseInt(newRow.substring(7, 10),2);

            ids.add((rowNum * 8 + colNum));
        }
        return ids;
    }

    public static int day5b(List<Integer> input) {
        for (int i = 0; i < 1024; i++) {
            if(!input.contains(i) && input.contains((i - 1)) && input.contains(i + 1)) {
                return i;
            }
        }
        return -1;
    }

}
