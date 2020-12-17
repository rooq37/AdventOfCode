package y_2020.day16;

import helpers.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 {

    public static void main(String[] args) throws IOException {
        List<String> input = FileHelper.readFile("src/y_2020/day16/input.txt");
        day16(input);
    }

    public static void day16(List<String> input) {
        List<Rule> rules = new ArrayList<>();
        String yourTicket;
        List<String> nearbyTickets = new ArrayList<>();
        int i = 0;
        while (!input.get(i).isEmpty())
            rules.add(new Rule(input.get(i++)));
        i+=2;
        yourTicket = input.get(i);
        i+=3;
        while (i < input.size())
            nearbyTickets.add(input.get(i++));

        List<Integer> invalidValues = new ArrayList<>();
        List<String> validTickets = new ArrayList<>();
        for (String nearbyTicket : nearbyTickets) {
            String[] values = nearbyTicket.split(",");
            boolean validTicket = true;
            for (String value : values) {
                if (!checkValue(Integer.parseInt(value), rules)) {
                    invalidValues.add(Integer.parseInt(value));
                    validTicket = false;
                }
            }
            if(validTicket) validTickets.add(nearbyTicket);
        }

        System.out.println("Day 16a: " + invalidValues.stream().reduce(0, Integer::sum));

        Map<String, Integer> ruleIndexes = new HashMap<>();

        while (rules.size() > 0) {
            for(int j = 0; j < yourTicket.split(",").length; j++) {
                List<Rule> tempRules = new ArrayList<>(rules);
                for(String validTicket : validTickets) {
                    int value = Integer.parseInt(validTicket.split(",")[j]);
                    for(Rule rule : rules) {
                        if(!rule.isCorrect(value))
                            tempRules.remove(rule);
                    }
                }
                if(tempRules.size() == 1) {
                    ruleIndexes.put(tempRules.get(0).getName(), j);
                    rules.remove(tempRules.get(0));
                }
            }
        }

        long multiplier = 1;
        for(Map.Entry<String, Integer> entry : ruleIndexes.entrySet()) {
            if(entry.getKey().contains("departure")) {
                multiplier *= Integer.parseInt(yourTicket.split(",")[entry.getValue()]);
            }
        }

        System.out.println("Day 16b: " + multiplier);
    }

    private static boolean checkValue(int value, List<Rule> rules) {
        for (Rule rule : rules) {
            if (rule.isCorrect(value)) {
                return true;
            }
        }
        return false;
    }

    private static class Rule{

        private String name;
        private int min1, max1, min2, max2;

        public Rule(String row) {
            this.name = row.split(":")[0];
            this.min1 = Integer.parseInt(row.split(":")[1].split("or")[0].split("-")[0].trim());
            this.max1 = Integer.parseInt(row.split(":")[1].split("or")[0].split("-")[1].trim());
            this.min2 = Integer.parseInt(row.split(":")[1].split("or")[1].split("-")[0].trim());
            this.max2 = Integer.parseInt(row.split(":")[1].split("or")[1].split("-")[1].trim());
        }

        public boolean isCorrect(int value) {
            return (value >= min1 && value <= max1) || (value >= min2 && value <= max2);
        }

        public String getName() {
            return name;
        }
    }

}
