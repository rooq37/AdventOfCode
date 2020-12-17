package y_2020.day13;

import helpers.FileHelper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day13 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day13/input.txt");
        System.out.println("Day 13a: " + day13a(input));
        System.out.println("Day 13b: " + day13b(input));
    }

    public static int day13a(List<String> input) {
        int timestamp = Integer.parseInt(input.get(0));
        List<Integer> busIds = Arrays.stream(input.get(1).split(","))
                .filter(x -> !x.contains("x")).map(Integer::parseInt).collect(Collectors.toList());

        int bestBusId = 0;
        int bestDiff = Integer.MAX_VALUE;
        for(int i = 0; i < busIds.size(); i++) {
            int ts = busIds.get(i);
            while (ts <= timestamp) {
                ts += busIds.get(i);
            }
            if(ts - timestamp < bestDiff) {
                bestDiff = ts - timestamp;
                bestBusId = busIds.get(i);
            }
        }
        return bestDiff * bestBusId;
    }

    public static long day13b(List<String> input) {
        List<String> busIds = Arrays.stream(input.get(1).split(",")).collect(Collectors.toList());
        long start = 0;
        long delta = 1;
        for(int i = 1; i < busIds.size(); i++) {
            List<Long> lastResults = helper(busIds.subList(0, i), start, delta);
            start = lastResults.get(0);
            delta = lastResults.get(1) - lastResults.get(0);
        }
        return helper(busIds, start, delta).get(0);
    }

    public static List<Long> helper(List<String> busIds, long start, long delta) {
        long timestamp = start;
        List<Long> results = new ArrayList<>();
        while (true) {
            boolean equal = true;
            for(int i = 0; i < busIds.size() && equal; i++) {
                if(!busIds.get(i).equals("x")){
                    if((timestamp + i) % Integer.parseInt(busIds.get(i)) != 0){
                        equal = false;
                    }
                }
            }
            if(equal) results.add(timestamp);
            if(results.size() == 2) return results;
            timestamp += delta;
        }
    }

}
