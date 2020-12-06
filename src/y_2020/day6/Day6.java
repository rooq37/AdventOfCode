package y_2020.day6;

import helpers.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day6/input.txt");
        List<String> groups = parseToGroups(input);
        System.out.println("Day 6a: " + day6a(groups));
        System.out.println("Day 6b: " + day6b(groups));
    }

    public static List<String> parseToGroups(List<String> input) {
        List<String> groups = new ArrayList<>();
        StringBuilder group = new StringBuilder();
        for (int i = 0; i <= input.size(); i++) {
            if (i == input.size() || input.get(i).isEmpty()) {
                groups.add(group.toString());
                group = new StringBuilder();
            } else {
                group.append(input.get(i)).append("\n");
            }
        }
        return groups;
    }

    public static int day6a(List<String> groups) {
        int generalCounter = 0;
        for (String group : groups) {
            int groupCounter = 0;
            for (int i = 97; i < 123; i++) {
                if (group.contains(Character.toString((char) i))) {
                    groupCounter++;
                }
            }
            generalCounter += groupCounter;
        }
        return generalCounter;
    }

    public static int day6b(List<String> groups) {
        int generalCounter = 0;
        for (String group : groups) {
            int groupCounter = 0;
            String[] lines = group.split("\n");
            for (int i = 97; i < 123; i++) {
                boolean everyone = true;
                for (int j = 0; j < lines.length; j++) {
                    if (!lines[j].contains(Character.toString((char) i))) {
                        everyone = false;
                    }
                }
                if (everyone) groupCounter++;
            }
            generalCounter += groupCounter;
        }
        return generalCounter;
    }

}
