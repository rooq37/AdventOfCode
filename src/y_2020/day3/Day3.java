package y_2020.day3;

import helpers.FileHelper;

import java.io.IOException;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day3/input.txt");

        System.out.println("Day 3a: " + countTreesOnYourRoad(input, 3, 1));

        long multiplyB = countTreesOnYourRoad(input, 1, 1) *
                countTreesOnYourRoad(input, 3, 1) *
                countTreesOnYourRoad(input, 5, 1) *
                countTreesOnYourRoad(input, 7, 1) *
                countTreesOnYourRoad(input, 1, 2);

        System.out.println("Day 3b: " + multiplyB);
    }

    public static long countTreesOnYourRoad(List<String> input, int horizontal, int vertical) {
        long counter = 0;
        int lastIndex = 0;
        for (int i = 0; i < input.size(); i += vertical) {
            if (input.get(i).charAt(lastIndex) == '#') {
                counter++;
            }
            lastIndex = (lastIndex + horizontal) % input.get(i).length();
        }
        return counter;
    }

}
