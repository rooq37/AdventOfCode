package y_2020.day2;

import helpers.FileHelper;

import java.io.IOException;
import java.util.List;

public class Day2 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day2/input.txt");
        day2a(input);
        day2b(input);
    }

    public static void day2a(List<String> input) {
        int counter = 0;
        for (String row : input) {
            int min = Integer.parseInt(row.split(":")[0].split(" ")[0].split("-")[0]);
            int max = Integer.parseInt(row.split(":")[0].split(" ")[0].split("-")[1]);
            char letter = row.split(":")[0].split(" ")[1].charAt(0);
            String password = row.split(":")[1].trim();
            int occurences = 0;
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == letter) {
                    occurences++;
                }
            }
            if (occurences >= min && occurences <= max) {
                counter++;
            }
        }
        System.out.println("Day 2a: " + counter);
    }

    public static void day2b(List<String> input) {
        int counter = 0;
        for (String row : input) {
            int pos1 = Integer.parseInt(row.split(":")[0].split(" ")[0].split("-")[0]) - 1;
            int pos2 = Integer.parseInt(row.split(":")[0].split(" ")[0].split("-")[1]) - 1;
            char letter = row.split(":")[0].split(" ")[1].charAt(0);
            String password = row.split(":")[1].trim();

            if ((password.charAt(pos1) == letter || password.charAt(pos2) == letter)
                    && (password.charAt(pos1) != password.charAt(pos2))) {
                counter++;
            }
        }
        System.out.println("Day 2b: " + counter);
    }

}
