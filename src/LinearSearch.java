/**
 * Question 1: Linear search.
 *
 * Pseudocode:
 *   Step 1: Start at the leftmost element and compare each element with target.
 *   Step 2: If values[i] == target, return the index i.
 *   Step 3: If the end is reached without a match, return -1.
 *
 * Time complexity: O(n).
 */
public class LinearSearch {

    public static final int NOT_FOUND = -1;

    /** Returns the index of the first occurrence of target in values, or NOT_FOUND. */
    public static int search(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public static void main(String[] args) {
        int[] values = {2, 3, 4, 10, 40};
        int[] targets = {10, 7};

        for (int target : targets) {
            int index = search(values, target);
            if (index == NOT_FOUND) {
                System.out.println(target + ": not found in the array");
            } else {
                System.out.println(target + ": found at index " + index);
            }
        }
    }
}
