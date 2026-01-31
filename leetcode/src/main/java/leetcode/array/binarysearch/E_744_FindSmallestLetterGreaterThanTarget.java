package leetcode.array.binarysearch;

public class E_744_FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        System.out.println(nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'a')); // 'c'
        System.out.println(nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c')); // 'f'
        System.out.println(nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'd')); // 'f'
        System.out.println(nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'g')); // 'j'
        System.out.println(nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'j')); // 'c'
        System.out.println(nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'k')); // 'c'
    }

    /**
     * Binary Search
     * Idea:
     * - Use binary search to find the smallest letter greater than the target
     * - If no such letter exists, return the first letter in the array
     * ----------------------------------
     * TC: O(log n) where n is the length of the letters array
     * SC: O(1)
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, r = n - 1;

        char result = 0;
        while (l <= r) {
            int mid = r - (r - l) / 2;

            if (letters[mid] > target) {
                result = letters[mid];
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return result == 0 ? letters[0] : result;
    }
}
