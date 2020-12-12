package y_2020.day11;

import helpers.FileHelper;

import java.io.IOException;
import java.util.List;

public class Day11 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day11/input.txt");
        System.out.println("Day 11a: " + day11(convert(input), 4, 1));
        System.out.println("Day 11b: " + day11(convert(input), 5, Integer.MAX_VALUE));
    }

    public static char[][] convert(List<String> input) {
        char[][] array = new char[input.size()][input.get(0).length()];
        for(int i = 0; i < input.size(); i++) {
            array[i] = input.get(i).toCharArray();
        }
        return array;
    }

    public static char[][] copy(char[][] array) {
        char[][] newArray = new char[array.length][array[0].length];
        for(int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    public static int day11(char[][] input, int minOccupiedSeats, int range) {
        boolean changed = true;
        int occupied = 0;
        while (changed) {
            char[][] copy = copy(input);
            changed = false;
            for(int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[i].length; j++) {
                    if(input[i][j] == 'L'){
                        if(getOccupiedSeatsB(range, i, j, input) == 0) {
                            copy[i][j] = '#';
                            changed = true;
                        }
                    }else if(input[i][j] == '#') {
                        if(getOccupiedSeatsB(range, i, j, input) >= minOccupiedSeats){
                            copy[i][j] = 'L';
                            changed = true;
                        }
                    }
                }
            }

            occupied = 0;

            for(int i = 0; i < copy.length; i++) {
                for (int j = 0; j < copy[i].length; j++) {
                    if(copy[i][j] == '#') {
                        occupied++;
                    }
                }
            }
            input = copy(copy);
        }
        return occupied;
    }

    private static char getStatus(int x, int y, char[][] array) {
        if(x >= 0 && y >= 0 && x < array.length && y < array[0].length){
            return array[x][y];
        }
        return 'E';
    }

    private static int getOccupiedSeatsB(int range, int x, int y, char[][] array) {
        int occupied = 0;
        int i;

        i = 1;
        while (range > i && getStatus(x - i, y - i, array) == '.') i++;
        if(getStatus(x - i, y - i, array) == '#') occupied++;

        i = 1;
        while (range > i && getStatus(x + i, y + i, array) == '.') i++;
        if(getStatus(x + i, y + i, array) == '#') occupied++;

        i = 1;
        while (range > i && getStatus(x - i, y + i, array) == '.') i++;
        if(getStatus(x - i, y + i, array) == '#') occupied++;

        i = 1;
        while (range > i && getStatus(x + i, y - i, array) == '.') i++;
        if(getStatus(x + i, y - i, array) == '#') occupied++;

        i = 1;
        while (range > i && getStatus(x - i, y, array) == '.') i++;
        if(getStatus(x - i, y, array) == '#') occupied++;

        i = 1;
        while (range > i && getStatus(x + i, y, array) == '.') i++;
        if(getStatus(x + i, y, array) == '#') occupied++;

        i = 1;
        while (range > i && getStatus(x, y - i, array) == '.') i++;
        if(getStatus(x, y - i, array) == '#') occupied++;

        i = 1;
        while (range > i && getStatus(x, y + i, array) == '.') i++;
        if(getStatus(x, y + i, array) == '#') occupied++;

        return occupied;
    }

}
