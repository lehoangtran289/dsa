package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_2491_DividePlayersIntoTeamsOfEqualSkill {
    public static void main(String[] args) {
        System.out.println(dividePlayers(new int[]{3, 2, 5, 1, 3, 4})); // 22
        System.out.println(dividePlayers(new int[]{3, 4})); // 12
        System.out.println(dividePlayers(new int[]{1, 1, 2, 3})); // -1
    }

    /**
     * HashMap
     * Idea: For each player with skill "s", we need to find a complement player with skill "targetSum - s"
     * ----------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static long dividePlayers(int[] skill) {
        int n = skill.length;
        int totalSum = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        // Calculate total sum and frequency of each skill level
        for (int num : skill) {
            totalSum += num;
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        if (totalSum % (n / 2) != 0) return -1;

        // try to pair players
        int targetSum = totalSum / (n / 2);
        long res = 0;

        for (int num : skill) {
            if (freq.get(num) <= 0) continue;
            int complement = targetSum - num;

            if (!freq.containsKey(complement) || freq.get(complement) <= 0) return -1;
            if (num == complement && freq.get(num) < 2) return -1;

            res += (long) num * complement;
            freq.put(num, freq.get(num) - 1);
            freq.put(complement, freq.get(complement) - 1);
        }

        return res;
    }

    /**
     * Frequency Array
     * Idea: Similar to HashMap approach but using an array for frequency counting since skill levels are bounded.
     * ----------------------------------
     * TC: O(n)
     * SC: O(m), m = max skill level
     */
    public static long dividePlayers2(int[] skill) {
        int n = skill.length;
        int totalSum = 0;
        int[] skillFreq = new int[5001];

        for (int num : skill) {
            totalSum += num;
            skillFreq[num]++;
        }
        if (totalSum % (n / 2) != 0) return -1;

        int targetSum = totalSum / (n / 2);
        long res = 0;

        for (int num : skill) {
            if (skillFreq[num] == 0) continue;
            int complement = targetSum - num;

            if (skillFreq[complement] <= 0) return -1;
            if (num == complement && skillFreq[num] < 2) return -1;

            res += (long) num * complement;
            skillFreq[num]--;
            skillFreq[complement]--;
        }

        return res;
    }

    /**
     * Sort + Two-pointer
     * Idea: After sorting, the weakest and strongest players must form a team to achieve equal skill sum.
     * ----------------------------------
     * TC: O(n log n)
     * SC: O(1)
     */
    public static long dividePlayers1(int[] skill) {
        Arrays.sort(skill);

        int n = skill.length;
        int targetSum = skill[0] + skill[n - 1];
        long res = 0;

        int i = 0, j = n - 1;
        while (i < j) {
            if (skill[i] + skill[j] != targetSum) return -1;
            res += (long) skill[i] * skill[j];
            i++;
            j--;
        }

        return res;
    }
}
