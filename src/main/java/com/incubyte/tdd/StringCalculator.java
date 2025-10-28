package com.incubyte.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiterRegex = ",|\n";
        String numbers = input;

        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf('\n');
            String header = input.substring(2, newlineIndex);
            numbers = input.substring(newlineIndex + 1);

            Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(header);
            List<String> delimiters = new ArrayList<>();

            while (matcher.find()) {
                delimiters.add(Pattern.quote(matcher.group(1)));
            }

            if (!delimiters.isEmpty()) {
                delimiterRegex = String.join("|", delimiters);
            } else {
                delimiterRegex = Pattern.quote(header);
            }
        }

        String[] tokens = numbers.split(delimiterRegex);
        List<Integer> parsedNumbers = new ArrayList<>();

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            try {
                parsedNumbers.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number: " + token);
            }
        }

        List<Integer> negatives = parsedNumbers.stream()
                .filter(n -> n < 0)
                .collect(Collectors.toList());

        if (!negatives.isEmpty()) {
            String msg = negatives.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("negative numbers not allowed: " + msg);
        }

        return parsedNumbers.stream().mapToInt(Integer::intValue).sum();
    }
}
