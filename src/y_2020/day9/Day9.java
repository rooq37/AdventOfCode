package y_2020.day9;

import helpers.FileHelper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day9/input.txt");
        long invalidNumber = day9a(input);
        System.out.println("Day 9a: " + invalidNumber);
        System.out.println("Day 9b: " + day9b(input, invalidNumber));
    }

    public static long day9a(List<String> input) {
        for(int i = 25; i < input.size(); i++) {
            List<String> temp = input.subList(i-25, i);
            boolean tempContains = false;
            for(int j = 0; j < temp.size() && !tempContains; j++) {
                for(int k = 0; k < temp.size(); k++) {
                    if(j != k) {
                        if((Long.parseLong(temp.get(j)) + Long.parseLong(temp.get(k)) == Long.parseLong(input.get(i)))) {
                            tempContains = true;
                        }
                    }
                }
            }
            if(!tempContains) return Long.parseLong(input.get(i));
        }

        return -1;
    }

    public static long day9b(List<String> input, long invalidNumber) {
        for(int i = 2; i < input.size(); i++) {
            for(int j = i; j < input.size(); j++) {
                List<Long> tempSet = input.subList(j - i, j).stream().map(Long::parseLong).collect(Collectors.toList());
                long temp = tempSet.stream().reduce(0L, Long::sum);
                if(temp == invalidNumber) {
                    return Collections.min(tempSet) + Collections.max(tempSet);
                }
            }
        }

        return -1;
    }

}
