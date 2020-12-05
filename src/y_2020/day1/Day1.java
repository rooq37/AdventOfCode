package y_2020.day1;

import helpers.FileHelper;

import java.io.IOException;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day1/input.txt");
        day1a(input);
        day1b(input);
    }

    public static void day1a(List<String> input) {
        System.out.println("Day 1a:");
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (i != j) {
                    int a = Integer.parseInt(input.get(i));
                    int b = Integer.parseInt(input.get(j));
                    if (a + b == 2020) {
                        System.out.println(a * b);
                    }
                }
            }
        }
    }

    public static void day1b(List<String> input) {
        System.out.println("Day 1b:");
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                for (int k = 0; k < input.size(); k++) {
                    if (i != j && i != k && j != k) {
                        int a = Integer.parseInt(input.get(i));
                        int b = Integer.parseInt(input.get(j));
                        int c = Integer.parseInt(input.get(k));
                        if (a + b + c == 2020) {
                            System.out.println(a * b * c);
                        }
                    }
                }
            }
        }
    }

}
