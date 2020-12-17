package y_2020.day15;

import helpers.FileHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day15/input.txt");
        System.out.println("Day 15a: " + day15(getMapWithoutLast(input), getLastNumber(input), 2020));
        System.out.println("Day 15b: " + day15(getMapWithoutLast(input), getLastNumber(input), 30000000));
    }

    public static Map<Integer, Integer> getMapWithoutLast(List<String> input) {
        String[] numbers = input.get(0).split(",");
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length - 1; i++) {
            map.put(Integer.parseInt(numbers[i]), i);
        }
        return map;
    }

    public static int getLastNumber(List<String> input) {
        String[] numbers = input.get(0).split(",");
        return Integer.parseInt(numbers[numbers.length - 1]);
    }

    public static int day15(Map<Integer, Integer> input, int nextNum, int position) {
        int lastNum = nextNum;
        int newNum = 0;
        for(int i = input.size() + 1; i < position; i++) {
            if(input.containsKey(lastNum)) {
                newNum = i - input.get(lastNum) - 1;
            }else {
                newNum = 0;
            }
            input.put(lastNum, i - 1);
            lastNum = newNum;

        }
        return lastNum;
    }

}
