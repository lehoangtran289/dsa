package leetcode.array;

import java.util.*;

public class M_1733_MinimumNumberOfPeopleToTeach {

    /**
     * Simulation, greedy
     */
    public static int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int userCount = languages.length;
        Set<Integer>[] userLanguages = new Set[userCount];

        for (int i = 0; i < userCount; ++i) {
            userLanguages[i] = new HashSet<>();

            for (int language : languages[i]) {
                userLanguages[i].add(language);
            }
        }

        // find friendship not communicate-able
        Set<Integer> needTeach = new HashSet<>();

        for (int[] friendship : friendships) {
            int user1 = friendship[0] - 1;
            int user2 = friendship[1] - 1;

            if (!isIntersect(userLanguages[user1], userLanguages[user2])) {
                needTeach.add(user1);
                needTeach.add(user2);
            }
        }

        // try all languages, check to see which one require least teach
        int res = Integer.MAX_VALUE;

        for (int lang = 1; lang <= n; ++lang) {
            int notKnowLanguage = 0;

            for (var user : needTeach) {
                if (!userLanguages[user].contains(lang)) {
                    notKnowLanguage++;
                }
            }

            res = Math.min(res, notKnowLanguage);
        }

        return res;
    }

    private static boolean isIntersect(Set<Integer> set1, Set<Integer> set2) {
        for (var num : set1) {
            if (set2.contains(num)) return true;
        }
        return false;
    }
}
