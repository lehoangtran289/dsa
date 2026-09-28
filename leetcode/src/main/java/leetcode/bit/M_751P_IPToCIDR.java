package leetcode.bit;

import java.util.ArrayList;
import java.util.List;

public class M_751P_IPToCIDR {

    public List<String> ipToCIDR(String ip, int n) {
        List<String> res = new ArrayList<>();
        long x = ipToLong(ip);

        while (n > 0) {
            long lowBitVal = x & (-x); // get lowest set bit value ~ largest aligned block at x
            long highBitVal = Long.highestOneBit(n); // get highest set bit value ~ largest power of 2 <= n
            long count = lowBitVal == 0 ? highBitVal : Math.min(lowBitVal, highBitVal);

            int mask = 32 - Long.numberOfTrailingZeros(count);
            res.add(toIp(x) + "/" + mask);

            x += count;
            n -= count;
        }

        return res;
    }

    private long ipToLong(String ip) {
        long res = 0;
        for (String part : ip.split("\\.")) {
            res = res * 256 + Integer.parseInt(part);
        }
        return res;
    }

    private String toIp(long num) {
        return ((num >> 24) & 255) + "."
               + ((num >> 16) & 255) + "."
               + ((num >> 8) & 255) + "."
               + (num & 255);
    }
}
