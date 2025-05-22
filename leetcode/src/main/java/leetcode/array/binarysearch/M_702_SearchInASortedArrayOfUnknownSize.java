package leetcode.array.binarysearch;

public class M_702_SearchInASortedArrayOfUnknownSize {

    interface ArrayReader {
        // Returns the value at index i of the array. The value is 2^31 - 1 if the index is invalid.
        int get(int index);
    }

    /**
     * Binary search
     * --------------------------
     * TC: O(logN)
     * SC: O(1)
     */
    public int search(ArrayReader reader, int target) {
        int outboundVal = (int) Math.pow(2, 31) - 1;
        int l = 0, r = (int) Math.pow(10, 4) + 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int curVal = reader.get(mid);

            if (curVal == outboundVal) {
                r = mid - 1;
                continue;
            }

            if (curVal == target) {
                return mid;
            } else if (curVal > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }
}
