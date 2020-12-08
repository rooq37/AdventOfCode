package y_2020.day7;

import helpers.FileHelper;

import java.io.IOException;
import java.util.*;

public class Day7 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day7/input.txt");
        System.out.println("Day 7a: " + day7a("shiny gold", input));
        System.out.println("Day 7b: " + day7b("shiny gold", input));
    }

    public static int day7a(String bagColor, List<String> input) {
        Set<String> bags = new HashSet<>();
        bags.add(bagColor);
        boolean changed;
        do {
            changed = false;
            Set<String> tempBags = new HashSet<>();
            for(String bag : bags) {
                for(String newBag : findBagsThatContain(input, bag)) {
                    if(!bags.contains(newBag)) {
                        changed = true;
                        tempBags.add(newBag);
                    }
                }
            }
            bags.addAll(tempBags);
        }while (changed);
        bags.remove(bagColor);
        return bags.size();
    }

    public static List<String> findBagsThatContain(List<String> input, String bagColor) {
        Set<String> bags = new HashSet<>();
        for(String row : input) {
            String[] splitted = row.split("bags contain");
            if(splitted[1].contains(bagColor)) {
                bags.add(splitted[0].trim());
            }
        }
        return new ArrayList<>(bags);
    }

    public static int day7b(String bagColor, List<String> input) {
        return countBags(input, bagColor) - 1;
    }

    public static int countBags(List<String> input, String parentColor) {
        int sum = 1;
        for(String row : input) {
            String[] splitted = row.split("bags contain");
            if(splitted[0].contains(parentColor)) {
                if(splitted[1].contains("no other bags")) {
                    return 1;
                }else {
                    String temp = splitted[1]
                            .replaceAll("bags", "")
                            .replaceAll("bag", "")
                            .replaceAll("\\.", "");
                    String[] bags = temp.split(",");
                    for(String bag : bags) {
                        int num = Integer.parseInt(bag.trim().substring(0, 1));
                        String bagColor = bag.substring(3);
                        sum += (num * countBags(input, bagColor));
                    }
                }
            }
        }
        return sum;
    }

}
