package leetcode.bit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Minimum Set Cover problem
 */
public class H_1125_SmallestSufficientTeam {

    /**
     * Idea: Bitmask DP
     * dp[requireSkillMask] = optimal people mask that can cover this skillMask
     * -----------------------
     * TC: O(2^M * N), M = number of skills, N = number of people
     * SC: O(2^M)
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int requireLength = req_skills.length;
        int peopleLength = people.size();

        // build skill to id mapping
        Map<String, Integer> skillIdMap = new HashMap<>();
        for (int i = 0; i < requireLength; ++i) {
            skillIdMap.put(req_skills[i], i);
        }

        // build each person skills' mask
        int[] peopleSkillMasks = new int[peopleLength];
        for (int i = 0; i < peopleLength; ++i) {
            for (String skill : people.get(i)) {
                int skillId = skillIdMap.get(skill);
                peopleSkillMasks[i] |= 1 << skillId;
            }
        }

        // init dp states; dp[skillMask] = optimal people mask that can cover skillMask
        int requireFullMask = (1 << requireLength) - 1;
        long peopleFullMask = (1L << peopleLength) - 1;
        long[] dp = new long[requireFullMask + 1];

        Arrays.fill(dp, peopleFullMask); // init dp with all people mask (max num)
        dp[0] = 0;

        for (int skillMask = 1; skillMask <= requireFullMask; ++skillMask) {
            for (int i = 0; i < peopleLength; ++i) {
                // try unset bits of current person skill mask
                int prevSkillMask = skillMask & ~peopleSkillMasks[i];

                // if this person can affect result of skill mask
                if (prevSkillMask != skillMask) {
                    long newPeopleMask = dp[prevSkillMask] | (1L << i); // add this person to people mask
                    if (countBits(newPeopleMask) < countBits(dp[skillMask])) {
                        dp[skillMask] = newPeopleMask;
                    }
                }
            }
        }

        // construct answer from result dp[requireFullMask]
        long resultPeopleMask = dp[requireFullMask];
        int[] res = new int[countBits(resultPeopleMask)];
        int ptr = 0;

        int i = 0;
        while (resultPeopleMask > 0) {
            if ((resultPeopleMask & 1) == 1) res[ptr++] = i;
            i++;
            resultPeopleMask >>= 1;
        }

        return res;
    }

    private int countBits(long mask) {
        int res = 0;
        while (mask != 0) {
            if ((mask & 1) == 1) res++;
            mask >>= 1;
        }
        return res;
    }
}
