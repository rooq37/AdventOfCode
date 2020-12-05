package y_2020.day4;

import helpers.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day4/input.txt");
        List<String> normalizedInput = normalizeInput(input);
        List<String> validatedPassports = validatedPassports(normalizedInput);
        day4b(validatedPassports);
    }

    public static List<String> normalizeInput(List<String> input) {
        StringBuilder tempLine = new StringBuilder();
        List<String> normalizedInput = new ArrayList<>();
        for(String row: input) {
            if(row.isEmpty()) {
                normalizedInput.add(tempLine.toString());
                tempLine = new StringBuilder();
            } else {
                tempLine.append(row).append(" ");
            }
        }
        if(tempLine.length() > 0) normalizedInput.add(tempLine.toString());
        return normalizedInput;
    }

    public static List<String> validatedPassports(List<String> input) {
        int counter = 0;
        List<String> newInput = new ArrayList<>();
        for(String row : input) {
            if(row.contains("byr")
            && row.contains("iyr")
            && row.contains("eyr")
            && row.contains("hgt")
            && row.contains("hcl")
            && row.contains("ecl")
            && row.contains("pid")) {
                counter++;
                newInput.add(row);
            }
        }
        System.out.println("Day 4a: " + counter);
        return newInput;
    }

    public static void day4b(List<String> input) {
        int counter = 0;
        for(String row : input) {
            Passport passport = new Passport(row);
            if(passport.validatePassport()) {
                counter++;
            }
        }
        System.out.println("Day 4b: " + counter);
    }

    private static class Passport{

        private String byr,iyr, eyr, hgt, hcl, ecl, pid;

        public Passport(String passportLine) {
            String[] fields = passportLine.trim().split(" ");
            this.byr = findField(fields, "byr");
            this.iyr = findField(fields, "iyr");
            this.eyr = findField(fields, "eyr");
            this.hgt = findField(fields, "hgt");
            this.hcl = findField(fields, "hcl");
            this.ecl = findField(fields, "ecl");
            this.pid = findField(fields, "pid");
        }

        public boolean validatePassport() {
            return validateByr()
                    && validateIyr()
                    && validateEyr()
                    && validateHgt()
                    && validateHcl()
                    && validateEcl()
                    && validatePid();
        }

        private String findField(String[] fields, String fieldName) {
            return Arrays.stream(fields).filter(x -> x.contains(fieldName)).findFirst().get().split(":")[1];
        }

        private boolean validateByr() {
            return (Integer.parseInt(byr) >= 1920 && Integer.parseInt(byr) <= 2002);
        }

        private boolean validateIyr() {
            return (Integer.parseInt(iyr) >= 2010 && Integer.parseInt(iyr) <= 2020);
        }

        private boolean validateEyr() {
            return (Integer.parseInt(eyr) >= 2020 && Integer.parseInt(eyr) <= 2030);
        }

        private boolean validateHgt() {
            if(hgt.contains("cm")) {
                int value = Integer.parseInt(hgt.replace("cm", ""));
                return (value >= 150 && value <= 193);
            }else {
                int value = Integer.parseInt(hgt.replace("in", ""));
                return (value >= 59 && value <= 76);
            }
        }

        private boolean validateHcl() {
            if(hcl.charAt(0) != '#') return false;
            if(hcl.length() != 7) return false;

            List<Character> chars = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f');
            for(int i = 1; i < hcl.length(); i++) {
                if(!chars.contains(hcl.charAt(i))) return false;
            }
            return true;
        }

        private boolean validateEcl() {
            List<String> allowed = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
            return allowed.contains(ecl);
        }

        private boolean validatePid() {
            if(pid.length() != 9) return false;
            for(int i = 0; i < pid.length(); i++) {
                if(!Character.isDigit(pid.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

}
