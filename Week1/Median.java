import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Median {
    public static double median(int[] num1, int[] num2) {
        // Unable to process null arrays.
        if (num1 == null && num2 == null) {
            return 0;
        }
        // If both are empty, nothing to process
        if (num1.length == 0 && num2.length == 0) {
            return 0;
        }
        // Median of two numbers is just their average
        if (num1.length == 1 && num2.length == 1) {
            return (num1[0] + num2[0]) / 2.0;
        }
        // If one of the arrays is empty/null, return median of the second
        if (num1 == null || num1.length == 0) {
            if (num2.length == 1) {
                return num2[0];
            }
            int mid = (num2.length - 1)/2;
            return (num2.length % 2 == 0) ?
                    (num2[mid] + num2[mid+1]) / 2.0 :
                    num2[mid];
        }
        // Ditto for above but with the arrays switched.
        if (num2 == null || num2.length == 0) {
            if (num1.length == 1) {
                return num1[0];
            }
            int mid = (num1.length - 1)/2;
            return (num1.length % 2 == 0) ?
                    (num1[mid] + num1[mid+1]) / 2.0 :
                    num1[mid];
        }
        // For ease of processing, make num2 the largest array if it isn't
        if (num1.length > num2.length) {
            return median(num2, num1);
        }

        // Find the combined length. Will be used in determining
        //  how to calculate median later, plus in length calculations
        int combinedLength = num1.length + num2.length;
        int medPos = (combinedLength + 1) / 2;
        // We're looking for how many elements from num2 are in
        //  the left partition, which includes the median
        //  or at least the left half of the median.
        //  end is the maximum number, which is that num2's elements
        //  are all less than the elements in num1, so it contributes
        //  all its elements up to the median.
        int end = Math.min(num1.length, medPos);

        // What is the smallest contribution num1 can make to the left
        //  partition? It's when num2 contributes its full amount to the
        //  partition.
        int start = 0;

        // In classic binary search fashion. Start in the middle, work outward.
        int mid = -1;
        int otherIndex = -1;

        // We are currently checking to see if this is a valid state.
        mid = start + (end - start) / 2;
        otherIndex = medPos - mid;
        while (start <= end) {
            // Contradiction: the next value in the next array would be
            //  added before the current value in the current array got added
            //  this state is invalid as it breaks the sorting constraint.
            if (mid > 0 && num1[mid - 1] > num2[otherIndex]) {
                // Since this state is invalid, and specifically
                //  because it over-counts num1's contributions
                //  end -> mid
                end = mid - 1;
            } else if (mid < num1.length && num2[otherIndex - 1] > num1[mid]) {
                // Since this state over-counts num2's contributions,
                // start -> mid
                start = mid + 1;
            } else {
                // We've found a valid state, calculate and return median
                return calcMedian(num1, mid, num2, otherIndex);
            }

            // We are currently checking to see if this is a valid state.
            mid = start + (end - start) / 2;
            otherIndex = medPos - mid;
        }

        // We had to exhaust our search but there is a valid solution
        //  (assuming the sorted constraint is met...), so since we've ran
        //  out of options, assuming it's valid, calculate and return median.
        return Integer.MIN_VALUE;
    }

    private static double calcMedian(int[] num1, int mid1, int[] num2, int mid2) {
        double med = -1;

        if (mid1 == 0) {
            med = num2[mid2 - 1];
        } else if (mid2 == 0) {
            med = num1[mid1 - 1];
        } else {
            med = Math.max(num1[mid1 - 1], num2[mid2 - 1]);
        }

        if ((num1.length + num2.length) % 2 == 1) {
            return med;
        }

        int medPair = -1;

        if (mid1 == num1.length) {
            medPair = num2[mid2];
        } else if (mid2 == num2.length) {
            medPair = num1[mid1];
        } else {
            medPair = Math.min(num1[mid1], num2[mid2]);
        }

        return (med + medPair) / 2.0;
    }
}