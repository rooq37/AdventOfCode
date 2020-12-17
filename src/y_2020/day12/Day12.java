package y_2020.day12;

import helpers.FileHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day12 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day12/input.txt");
        System.out.println("Day 12a: " + day12a(input));
        System.out.println("Day 12b: " + day12b(input));
    }

    public static int day12a(List<String> input) {
        List<String> directions = Arrays.asList("N", "E", "S", "W");
        int ns = 0;
        int ew = 0;
        String direction = "E";

        for (String row : input) {
            int value = Integer.parseInt(row.substring(1));
            if (row.contains("R")) {
                int index = directions.indexOf(direction);
                index = (index + (value / 90)) % 4;
                direction = directions.get(index);
            } else if (row.contains("L")) {
                int index = directions.indexOf(direction);
                index = (index - (value / 90)) % 4;
                if (index < 0) index = 4 - Math.abs(index);
                direction = directions.get(index);
            } else {
                if (row.contains("E")) ew += value;
                else if (row.contains("W")) ew -= value;
                else if (row.contains("N")) ns += value;
                else if (row.contains("S")) ns -= value;
                else if (row.contains("F")) {
                    if (direction.equals("N")) ns += value;
                    if (direction.equals("S")) ns -= value;
                    if (direction.equals("E")) ew += value;
                    if (direction.equals("W")) ew -= value;
                }
            }
        }
        return Math.abs(ns) + Math.abs(ew);
    }

    public static int day12b(List<String> input) {
        int posN = 0;
        int posE = 0;
        int waypointN = 1;
        int waypointE = 10;

        for (String row : input) {
            if (row.contains("R180") || row.contains("L180")) {
                waypointN = -waypointN;
                waypointE = -waypointE;
            } else if (row.contains("R90") || row.contains("L270")) {
                int temp = waypointE;
                waypointE = waypointN;
                waypointN = -temp;
            } else if (row.contains("L90") || row.contains("R270")) {
                int temp = waypointE;
                waypointE = -waypointN;
                waypointN = temp;
            } else {
                int value = Integer.parseInt(row.substring(1));
                if (row.contains("E")) waypointE += value;
                else if (row.contains("W")) waypointE -= value;
                else if (row.contains("N")) waypointN += value;
                else if (row.contains("S")) waypointN -= value;
                else if (row.contains("F")) {
                    posN += waypointN * value;
                    posE += waypointE * value;
                }
            }
        }
        return Math.abs(posN) + Math.abs(posE);
    }

}
