package y_2020.day8;

import helpers.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day8/input.txt");
        System.out.println("Day 8a: " + day8a(input));
        System.out.print("Day 8b: ");
        for(int i = 0; i < input.size(); i++) {
            if(input.get(i).contains("jmp")) {
                input.set(i, input.get(i).replace("jmp", "nop"));
                if(day8b(input) > -1) System.out.println(day8b(input));
                input.set(i, input.get(i).replace("nop", "jmp"));
            }else if(input.get(i).contains("nop")) {
                input.set(i, input.get(i).replace("nop", "jmp"));
                if(day8b(input) > -1) System.out.println(day8b(input));
                input.set(i, input.get(i).replace("jmp", "nop"));
            }
        }
    }

    public static int day8a(List<String> input) {
        int acc = 0;
        List<Integer> executed = new ArrayList<>();
        for(int i = 0; i < input.size() && !executed.contains(i);) {
            String[] splitted = input.get(i).split(" ");
            executed.add(i);
            if(splitted[0].equals("acc")) {
                acc += Integer.parseInt(splitted[1].trim());
                i++;
            } else if(splitted[0].equals("jmp")) {
                i += (Integer.parseInt(splitted[1].trim()));
            } else {
                i++;
            }
        }
        return acc;
    }

    public static int day8b(List<String> input) {
        int acc = 0;
        List<Integer> executed = new ArrayList<>();
        int i = 0;
        while (i < input.size() && !executed.contains(i)) {
            String[] splitted = input.get(i).split(" ");
            executed.add(i);
            if(splitted[0].equals("acc")) {
                acc += Integer.parseInt(splitted[1].trim());
                i++;
            } else if(splitted[0].equals("jmp")) {
                i += (Integer.parseInt(splitted[1].trim()));
            } else {
                i++;
            }
        }
        if(i == input.size()) {
            return acc;
        }
        return -1;
    }

}
