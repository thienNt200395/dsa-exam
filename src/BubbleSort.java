import java.util.Arrays;

/**
 * Question 2: Bubble sort (non-decreasing order).
 *
 * Pseudocode:
 *   For each pass from 0 to n-2:
 *       For each position i from 0 to n-2-pass:
 *           If values[i] > values[i+1], swap the two elements.
 *   After each pass the largest remaining element "sinks" to the end.
 *   (Optimisation: stop early if a pass makes no swap.)
 *
 * Time complexity: O(n^2) worst case, O(n) when the array is already sorted.
 */
public class BubbleSort {

    /** Sorts values in place in non-decreasing order. */
    public static void bubbleSort(int[] values) {
        for (int pass = 0; pass < values.length - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < values.length - 1 - pass; i++) {
                if (values[i] > values[i + 1]) {
                    swap(values, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
        }
    }

    private static void swap(int[] values, int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public static void main(String[] args) {
        int[] values = {64, 34, 25, 12, 22, 11, 90, 11};
        System.out.println("Before: " + Arrays.toString(values));
        bubbleSort(values);
        System.out.println("After : " + Arrays.toString(values));
    }
}
