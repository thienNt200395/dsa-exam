# Reference Solutions

Java implementations: [`src/`](../src). Each question lists the pseudocode, the idea and the
complexity. Tests: [`test/AlgorithmsTest.java`](../test/AlgorithmsTest.java).

## Question 1: Linear search
**Steps**
1. Start at the leftmost element of the array and compare each element with the target.
2. If an element equals the target, return its index.
3. If no element matches, return -1.

**Pseudocode**
```
FUNCTION linearSearch(values, target)
    FOR i FROM 0 TO length(values) - 1 DO
        IF values[i] = target THEN
            RETURN i
    RETURN -1
```
**Notes**
- Works on unsorted arrays. Returns the first match when duplicates exist.
- Time: O(n). Extra space: O(1).
- `-1` is a safe "not found" value because array indexes start at 0.

## Question 2: Bubble sort
**Steps**
1. Compare each pair of adjacent elements, starting from the first position.
2. If the right element is smaller than the left one, swap them.
3. After one pass the largest element has sunk to the end of the array.
4. Repeat on the remaining part until the array is sorted in non-decreasing order.

**Pseudocode**
```
FUNCTION bubbleSort(values, n)
    FOR pass FROM 0 TO n - 2 DO
        FOR i FROM 0 TO n - 2 - pass DO
            IF values[i] > values[i + 1] THEN
                swap values[i] and values[i + 1]
```
**Notes**
- The Java version stops early when a pass performs no swap.
- Time: O(n^2) worst case, O(n) on an already sorted array. Extra space: O(1). Stable.

## Question 3: Factorial (recursion)
**Recursive definition**
- `0! = 1` (base case)
- `n! = n * (n - 1)!` for `n > 0` (recursive step)

**Pseudocode**
```
FUNCTION factorial(n)
    IF n = 0 THEN
        RETURN 1
    RETURN n * factorial(n - 1)
```
**Example:** `3! = 3 * 2! = 3 * 2 * 1! = 3 * 2 * 1 * 0! = 6`

**Notes**
- Time: O(n). Stack depth: O(n).
- The Java version returns a `long`, so `n` must be between 0 and 20 (21! overflows `long`).
