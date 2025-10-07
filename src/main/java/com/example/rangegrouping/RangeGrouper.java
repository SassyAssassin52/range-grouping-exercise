package com.example.rangegrouping;
/**
 * Interface defining the contract for grouping sequential numbers into ranges.
 */
public interface RangeGrouper {

    /**
     * Groups sequential numbers in the array into ranges and returns a comma-delimited string.
     * 
     * @param numbers array of integers to be grouped (may be unsorted or contain duplicates)
     * @return comma-delimited string representing the grouped ranges
     * @throws IllegalArgumentException if the input array is null
     */
    String groupIntoRanges(int[] numbers);
}