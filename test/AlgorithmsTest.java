import java.util.Arrays;

/** Dependency-free tests. Exits with a non-zero status if any check fails. */
public class AlgorithmsTest {

    private static int total;
    private static int failed;

    public static void main(String[] args) {
        testLinearSearch();
        testBubbleSort();
        testFactorial();

        System.out.println(total + " checks, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testLinearSearch() {
        int[] values = {2, 3, 4, 10, 40};
        check("search: middle", 3, LinearSearch.search(values, 10));
        check("search: first", 0, LinearSearch.search(values, 2));
        check("search: last", 4, LinearSearch.search(values, 40));
        check("search: missing", LinearSearch.NOT_FOUND, LinearSearch.search(values, 7));
        check("search: empty array", LinearSearch.NOT_FOUND, LinearSearch.search(new int[0], 1));
        check("search: duplicates return first", 1, LinearSearch.search(new int[]{1, 7, 7}, 7));
        check("search: negative value", 2, LinearSearch.search(new int[]{4, 0, -3}, -3));
    }

    private static void testBubbleSort() {
        check("sort: unsorted", "[1, 2, 3, 4]", sorted(4, 2, 3, 1));
        check("sort: already sorted", "[1, 2, 3]", sorted(1, 2, 3));
        check("sort: reversed", "[1, 2, 3, 4, 5]", sorted(5, 4, 3, 2, 1));
        check("sort: duplicates", "[1, 1, 2, 2]", sorted(2, 1, 2, 1));
        check("sort: negatives", "[-5, -1, 0, 3]", sorted(3, -1, 0, -5));
        check("sort: empty", "[]", sorted());
        check("sort: single element", "[7]", sorted(7));
    }

    private static void testFactorial() {
        check("factorial: 0!", 1L, Factorial.factorial(0));
        check("factorial: 1!", 1L, Factorial.factorial(1));
        check("factorial: 5!", 120L, Factorial.factorial(5));
        check("factorial: 20!", 2432902008176640000L, Factorial.factorial(20));
        check("factorial: n = -1 rejected", true, rejects(-1));
        check("factorial: n = 21 rejected", true, rejects(21));
    }

    private static String sorted(int... values) {
        BubbleSort.bubbleSort(values);
        return Arrays.toString(values);
    }

    private static boolean rejects(int n) {
        try {
            Factorial.factorial(n);
            return false;
        } catch (IllegalArgumentException expected) {
            return true;
        }
    }

    private static void check(String name, Object expected, Object actual) {
        total++;
        if (!expected.equals(actual)) {
            failed++;
            System.out.println("FAIL " + name + ": expected " + expected + " but got " + actual);
        }
    }
}
