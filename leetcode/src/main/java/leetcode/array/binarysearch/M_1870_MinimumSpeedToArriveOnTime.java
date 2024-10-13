package leetcode.array.binarysearch;

public class M_1870_MinimumSpeedToArriveOnTime {
    /**
     * BINARY SEARCH ON [a, b] template <br/>
     * xu hướng min -> lo = a - 1, hi = b <br/>
     * xu hướng max -> lo = a, hi = b + 1 <br/>
     * <pre>
     * {@code
     *      while (lo + 1 < hi) {
     *          int mid = lo + (hi - lo) / 2;
     *          if (result in [lo, mid]) {
     *              lo = mid;
     *          } else {
     *              hi = mid;
     *          }
     *      }
     * }
     * </pre>
     */
    public static void main(String[] args) {
        M_1870_MinimumSpeedToArriveOnTime obj = new M_1870_MinimumSpeedToArriveOnTime();
        System.out.println(obj.minSpeedOnTime(new int[]{1, 3, 2}, 6));
    }

    /**
     * Binary search on result space [1, 10^7] <br/>
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        int len = dist.length;
        if (len > (int) Math.ceil(hour)) return -1;

        // init
        int lo = 0;
        int hi = (int) Math.pow(10, 7);
        int res = hi;

        // binary search
        while (lo <= hi) {
            int mid = hi - (hi - lo) / 2;

            // calculate time, last dist is divided by mid -> arrive time
            double sum = 0;
            for (int i = 0; i < len - 1; ++i) {
                sum += Math.ceil((double) dist[i] / mid);
            }
            sum += (double) dist[len - 1] / mid;

            // if sum > hour -> late -> increase speed
            if (sum > hour) {
                lo = mid + 1;
            } else { // if sum <= hour -> early -> OK -> decrease speed to find min speed valid
                res = Math.min(res, mid);
                hi = mid - 1;
            }
        }
        return res;
    }
}
