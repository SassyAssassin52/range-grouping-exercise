package com.example.rangegrouping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Comprehensive unit tests for RangeGrouperImpl class.
 * 
 * Tests cover various scenarios including edge cases, boundary conditions
 */
@DisplayName("RangeGrouperImpl Tests")
class RangeGrouperImplTest {

    private RangeGrouper rangeGrouper;

    @BeforeEach
    void setUp() {
        rangeGrouper = new RangeGrouperImpl();
    }

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Should return empty string for empty array")
        void shouldReturnEmptyStringForEmptyArray() {
            String result = rangeGrouper.groupIntoRanges(new int[]{});
            assertThat(result, is(equalTo("")));
        }

        @Test
        @DisplayName("Should return single number for array with one element")
        void shouldReturnSingleNumberForOneElement() {
            String result = rangeGrouper.groupIntoRanges(new int[]{5});
            assertThat(result, is(equalTo("5")));
        }

        @Test
        @DisplayName("Should return two numbers separated by comma for non-sequential pair")
        void shouldReturnTwoNumbersSeparatedByComma() {
            String result = rangeGrouper.groupIntoRanges(new int[]{1, 3});
            assertThat(result, is(equalTo("1,3")));
        }

        @Test
        @DisplayName("Should return range for sequential numbers")
        void shouldReturnRangeForSequentialNumbers() {
            String result = rangeGrouper.groupIntoRanges(new int[]{1, 2, 3});
            assertThat(result, is(equalTo("1-3")));
        }
    }

    @Nested
    @DisplayName("Complex Scenarios Tests")
    class ComplexScenariosTests {

        @Test
        @DisplayName("Should handle mixed ranges and individual numbers")
        void shouldHandleMixedRangesAndIndividualNumbers() {
            int[] input = {1, 2, 3, 5, 7, 8, 9};
            String result = rangeGrouper.groupIntoRanges(input);
            assertThat(result, is(equalTo("1-3,5,7-9")));
        }

        @Test
        @DisplayName("Should handle unsorted input array")
        void shouldHandleUnsortedInput() {
            int[] input = {3, 1, 2, 9, 7, 8, 5};
            String result = rangeGrouper.groupIntoRanges(input);
            assertThat(result, is(equalTo("1-3,5,7-9")));
        }

        @Test
        @DisplayName("Should handle duplicate numbers")
        void shouldHandleDuplicateNumbers() {
            int[] input = {1, 2, 2, 3, 3, 5, 5};
            String result = rangeGrouper.groupIntoRanges(input);
            assertThat(result, is(equalTo("1-3,5")));
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void shouldHandleNegativeNumbers() {
            int[] input = {-3, -2, -1, 1, 2};
            String result = rangeGrouper.groupIntoRanges(input);
            assertThat(result, is(equalTo("-3--1,1-2")));
        }

        @Test
        @DisplayName("Should handle large ranges")
        void shouldHandleLargeRanges() {
            int[] input = new int[100];
            for (int i = 0; i < 100; i++) {
                input[i] = i + 1;
            }
            String result = rangeGrouper.groupIntoRanges(input);
            assertThat(result, is(equalTo("1-100")));
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should throw IllegalArgumentException for null input")
        void shouldThrowExceptionForNullInput() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> rangeGrouper.groupIntoRanges(null)
            );
            assertThat(exception.getMessage(), containsString("Input array cannot be null"));
        }

        @Test
        @DisplayName("Should handle maximum integer values")
        void shouldHandleMaxIntegerValues() {
            int[] input = {Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
            String result = rangeGrouper.groupIntoRanges(input);
            String expected = (Integer.MAX_VALUE - 2) + "-" + Integer.MAX_VALUE;
            assertThat(result, is(equalTo(expected)));
        }

        @Test
        @DisplayName("Should handle minimum integer values")
        void shouldHandleMinIntegerValues() {
            int[] input = {Integer.MIN_VALUE, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2};
            String result = rangeGrouper.groupIntoRanges(input);
            String expected = Integer.MIN_VALUE + "-" + (Integer.MIN_VALUE + 2);
            assertThat(result, is(equalTo(expected)));
        }

        @Test
        @DisplayName("Should handle single element ranges scattered")
        void shouldHandleSingleElementRangesScattered() {
            int[] input = {1, 3, 5, 7, 9};
            String result = rangeGrouper.groupIntoRanges(input);
            assertThat(result, is(equalTo("1,3,5,7,9")));
        }
    }

    @ParameterizedTest(name = "Input: {0} => Expected: {1}")
    @MethodSource("provideTestCases")
    @DisplayName("Should produce correct output for various inputs")
    void shouldProduceCorrectOutputForVariousInputs(int[] input, String expected) {
        String result = rangeGrouper.groupIntoRanges(input);
        assertThat(result, is(equalTo(expected)));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            Arguments.of(new int[]{}, ""),
            Arguments.of(new int[]{1}, "1"),
            Arguments.of(new int[]{1, 2}, "1-2"),
            Arguments.of(new int[]{1, 3}, "1,3"),
            Arguments.of(new int[]{1, 2, 3, 4, 5}, "1-5"),
            Arguments.of(new int[]{1, 2, 4, 5, 7}, "1-2,4-5,7"),
            Arguments.of(new int[]{10, 9, 8, 7, 6}, "6-10"),
            Arguments.of(new int[]{1, 1, 2, 2, 3, 3}, "1-3"),
            Arguments.of(new int[]{-5, -4, -3, -1, 0, 1}, "-5--3,-1-1"),
            Arguments.of(new int[]{100, 99, 98, 105, 106}, "98-100,105-106")
        );
    }

    @Test
    @DisplayName("Should handle large arrays efficiently")
    void shouldHandleLargeArraysEfficiently() {
        // Create array with 10000 elements having mixed ranges
        int[] largeArray = new int[10000];
        int index = 0;
        
        // Add ranges and gaps
        for (int i = 0; i < 100; i++) {
            // Add a range of 50 consecutive numbers
            for (int j = 0; j < 50; j++) {
                largeArray[index++] = i * 100 + j;
            }
            // Add a gap, then scattered numbers
            for (int j = 0; j < 50; j++) {
                largeArray[index++] = i * 100 + j + 60; // scattered with gaps
            }
        }

        long startTime = System.currentTimeMillis();
        String result = rangeGrouper.groupIntoRanges(largeArray);
        long endTime = System.currentTimeMillis();

        assertThat(endTime - startTime, is(lessThan(1000L)));
        assertThat(result, is(not(emptyString())));
        assertThat(result.length(), is(greaterThan(0)));
    }
}