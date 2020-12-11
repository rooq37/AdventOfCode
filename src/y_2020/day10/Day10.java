package y_2020.day10;

import helpers.FileHelper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day10/input.txt");
        List<Integer> list = input.stream().map(Integer::parseInt).collect(Collectors.toList());
        list.add(0);
        list.add(Collections.max(list) + 3);
        Collections.sort(list);
        System.out.println("Day 10a: " + day10a(list));
        System.out.println("Day 10b: " + day10b(list));
    }

    public static int day10a(List<Integer> input) {
        int diff1 = 0;
        int diff3 = 0;
        for(int i = 1; i < input.size(); i++) {
            if(input.get(i) - input.get(i - 1) == 1) {
                diff1++;
            }else if (input.get(i) - input.get(i - 1) == 3) {
                diff3++;
            }
        }
        return diff1 * diff3;
    }

    public static long day10b(List<Integer> input) {
        long ways = 1;
        int lastIndex = 0;
        for(int i = 1; i < input.size(); i++) {
            if(input.get(i) - input.get(i - 1) == 3) {
                ways *= countWays(input, input.get(lastIndex), input.get(i - 1));
                lastIndex = i;
            }
        }
        ways *= countWays(input, lastIndex, input.size() -1);
        return ways;
    }

    public static long countWays(List<Integer> input, int currentAdapter, int finish) {
        if(finish <= currentAdapter){
            return 1;
        }

        return input.stream()
                .filter(x -> (x > currentAdapter && x <= (currentAdapter + 3) && x <= finish))
                .map(x -> countWays(input, x, finish))
                .reduce(0L, Long::sum);
    }

}
