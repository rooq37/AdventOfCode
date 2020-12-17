package y_2020.day14;

import helpers.FileHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day14/input.txt");
        System.out.println("Day 14a: " + day14a(input));
        System.out.println("Day 14b: " + day14b(input));
    }

    public static long day14a(List<String> input) {
        String mask = "";
        long[] memory = new long[100000];

        for(String row : input) {
            if(row.contains("mask")) {
                mask = row.split("=")[1].trim();
            } else if(row.contains("mem")) {
                String temp = Integer.toBinaryString(Integer.parseInt(row.split("=")[1].trim()));
                temp = String.format("%36s" , temp).replaceAll(" ", "0");
                char[] charArray = temp.toCharArray();
                for(int i = mask.length() - 1; i >= 0; i--) {
                    if(Character.isDigit(temp.charAt(i)) && mask.charAt(i) != 'X') {
                        charArray[i] = mask.charAt(i);
                    }
                }
                temp = new String(charArray);
                int index = Integer.parseInt(row.split("=")[0].replaceAll("mem\\[", "").replaceAll("]", "").trim());
                memory[index] = Long.parseLong(temp, 2);
            }
        }

        return Arrays.stream(memory).reduce(0, Long::sum);
    }

    public static long day14b(List<String> input) {
        String mask = "";
        Map<String, Long> memory = new HashMap<>();

        for(String row : input) {
            if(row.contains("mask")) {
                mask = row.split("=")[1].trim();
            } else if(row.contains("mem")) {
                int value = Integer.parseInt(getValue(row));
                String temp = toBinary(getIndex(row));
                char[] charArray = temp.toCharArray();
                int occurencesX = 0;
                for(int i = mask.length() - 1; i >= 0; i--) {
                    if(mask.charAt(i) != '0') {
                        charArray[i] = mask.charAt(i);
                        if(mask.charAt(i) == 'X') occurencesX++;
                    }
                }
                int howMany = (int) Math.pow(2, occurencesX);
                char[][] results = new char[howMany][36];

                int modulo = 1;
                for(int j = mask.length() - 1; j >= 0; j--) {
                    boolean bin = true;
                    for(int i = 0; i < howMany; i++) {
                        if(i % modulo == 0) bin = !bin;
                        if(charArray[j] == 'X') {
                            results[i][j] = (bin) ? '1' : '0';
                        }else {
                            results[i][j] = charArray[j];
                        }

                    }
                    if(charArray[j] == 'X') modulo*= 2;
                }
                for(int i = 0; i < results.length; i++) {
                    memory.put(new String(results[i]), (long) value);
                }
            }
        }

        return memory.values().stream().reduce(0L, Long::sum);
    }

    private static String getValue(String row) {
        return row.split("=")[1].trim();
    }

    private static int getIndex(String row) {
        return Integer.parseInt(row.split("=")[0]
                .replaceAll("mem\\[", "")
                .replaceAll("]", "")
                .trim());
    }

    private static String toBinary(int number) {
        String temp = Integer.toBinaryString(number);
        return String.format("%36s" , temp).replaceAll(" ", "0");
    }

}
