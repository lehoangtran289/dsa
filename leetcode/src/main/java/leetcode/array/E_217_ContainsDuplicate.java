package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class E_217_ContainsDuplicate {
    public static void main(String[] args) {
        E_217_ContainsDuplicate obj = new E_217_ContainsDuplicate();
        System.out.println(obj.containsDuplicate(new int[]{1, 2, 3, 1}));
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }
}
