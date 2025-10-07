package com.example.rangegrouping;

/**
 * Demonstration class showing usage of the RangeGrouper implementation.
 */
public class RangeGrouperDemo {

    // Demonstration of different scenarios
    public static void main(String[] args) {
        RangeGrouper rangeGrouper = new RangeGrouperImpl();
        
        System.out.println("START OF DEMO\n");
        
        demonstrateRangeGrouping(rangeGrouper, new int[]{1, 2, 3, 5, 7, 8, 9});
        demonstrateRangeGrouping(rangeGrouper, new int[]{1, 3, 5, 7, 9});
        demonstrateRangeGrouping(rangeGrouper, new int[]{1, 2, 3, 4, 5});
        demonstrateRangeGrouping(rangeGrouper, new int[]{});
        demonstrateRangeGrouping(rangeGrouper, new int[]{42});
        demonstrateRangeGrouping(rangeGrouper, new int[]{3, 1, 4, 1, 5, 9, 2, 6});
        demonstrateRangeGrouping(rangeGrouper, new int[]{-5, -4, -3, -1, 0, 1, 2});
        demonstrateRangeGrouping(rangeGrouper, new int[]{100, 99, 98, 105, 106, 107});
        
        System.out.println("DONE");
    }
    
    private static void demonstrateRangeGrouping(RangeGrouper rangeGrouper, int[] numbers) {
        System.out.print("Input:  [");
        for (int i = 0; i < numbers.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(numbers[i]);
        }
        System.out.println("]");
        
        String result = rangeGrouper.groupIntoRanges(numbers);
        System.out.println("Output: \"" + result + "\"");
        System.out.println();
    }
}