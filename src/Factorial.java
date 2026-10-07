import java.util.Scanner;

/**
 * Question 3: Factorial using recursion.
 *
 * Recursive definition:
 *   0! = 1                       (base case)
 *   n! = n * (n - 1)!  for n > 0 (recursive step)
 *
 * Pseudocode:
 *   factorial(n):
 *       if n == 0 then return 1
 *       else return n * factorial(n - 1)
 *
 * Example: 3! = 3 * 2! = 3 * 2 * 1! = 6.
 * The result is a long, so n is limited to 20 (21! overflows long).
 */
public class Factorial {

    public static final int MAX_N = 20;

    /** Computes n! for 0 <= n <= MAX_N; throws IllegalArgumentException otherwise. */
    public static long factorial(int n) {
        if (n < 0 || n > MAX_N) {
            throw new IllegalArgumentException("n must be in the range 0.." + MAX_N);
        }
        return factorialRecursive(n);
    }

    private static long factorialRecursive(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter n (integer): ");
            int n = scanner.nextInt();
            System.out.println(n + "! = " + factorial(n));
        }
    }
}
