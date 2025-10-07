package com.example.rangegrouping;
import java.util.*;
import java.util.stream.Collectors;


//Implementation of RangeGrouper interface that efficiently groups sequential numbers into ranges.
public class RangeGrouperImpl implements RangeGrouper {
    @Override
    public String groupIntoRanges(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        if (numbers.length == 0) {
            return "";
        }

        // Remove duplicates and sort using Java 8 streams
        List<Integer> sortedUniqueNumbers = Arrays.stream(numbers)
                .distinct()
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        return buildRangeString(sortedUniqueNumbers);
    }

    /**
     * Builds the comma-delimited range string from sorted unique numbers.
     * 
     * @param sortedNumbers list of sorted unique integers
     * @return comma-delimited string with ranges
     */
    private String buildRangeString(List<Integer> sortedNumbers) {
        List<String> ranges = new ArrayList<>();
        int start = 0;

        while (start < sortedNumbers.size()) {
            int end = findEndOfRange(sortedNumbers, start);
            ranges.add(formatRange(sortedNumbers.get(start), sortedNumbers.get(end)));
            start = end + 1;
        }

        return String.join(",", ranges);
    }

    /**
     * Finds the end index of a continuous range starting from the given start index.
     * 
     * @param numbers list of sorted numbers
     * @param startIndex starting index for the range
     * @return ending index of the continuous range
     */
    private int findEndOfRange(List<Integer> numbers, int startIndex) {
        int endIndex = startIndex;
        
        while (endIndex + 1 < numbers.size() && 
               numbers.get(endIndex + 1) == numbers.get(endIndex) + 1) {
            endIndex++;
        }
        
        return endIndex;
    }

    /**
     * Formats a range based on start and end values.
     * If start equals end, returns single number.
     * If they differ by 1, returns both numbers separated by comma logic handled by caller.
     * Otherwise returns range in format "start-end".
     * 
     * @param start starting number of the range
     * @param end ending number of the range
     * @return formatted range string
     */
    private String formatRange(int start, int end) {
        if (start == end) {
            return String.valueOf(start);
        } else {
            return start + "-" + end;
        }
    }
}