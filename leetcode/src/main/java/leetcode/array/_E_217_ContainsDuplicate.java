package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class _E_217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }

    public static void main(String[] args) {
        _E_217_ContainsDuplicate obj = new _E_217_ContainsDuplicate();
        System.out.println(obj.containsDuplicate(new int[]{1, 2, 3, 1}));
    }
}
