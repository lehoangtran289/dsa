package leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class M_1244_DesignALeaderboard {

    /**
     * Approach 1: TreeSet
     * Idea: keep players sorted by score using TreeSet
     * Since TreeSet not sorted by value, we need to maintain a map of playerId -> score
     * -----------
     * addScore: O(log N)
     * top: O(K)
     * reset: O(log N)
     */
    static class Leaderboard_1 {
        private final Map<Integer, Integer> scores;
        private final TreeSet<Integer> topPlayers;

        public Leaderboard_1() {
            this.scores = new HashMap<>();
            this.topPlayers = new TreeSet<>((a, b) -> {
                int scoreDiff = scores.getOrDefault(b, 0) - scores.getOrDefault(a, 0);
                if (scoreDiff != 0) return scoreDiff;
                return a - b;
            });
        }

        public void addScore(int playerId, int score) {
            if (scores.containsKey(playerId)) {
                topPlayers.remove(playerId);
            }
            scores.put(playerId, scores.getOrDefault(playerId, 0) + score);
            topPlayers.add(playerId);
        }

        public int top(int K) {
            int res = 0;
            int count = 0;
            for (int id : topPlayers) {
                if (count++ == K) break;
                res += scores.get(id);
            }
            return res;
        }

        public void reset(int playerId) {
            topPlayers.remove(playerId);
            scores.remove(playerId);
        }
    }

    /**
     * Approach 2: TreeMap
     * Idea: count frequencies of scores to optimize top K calculation
     * ------------
     * addScore: O(log M)
     * top: O(M) where M is number of unique scores
     * reset: O(log M)
     */
    static class Leaderboard_2 {

        private final Map<Integer, Integer> scores; // playerId -> score
        private final TreeMap<Integer, Integer> topScores; // score -> count

        public Leaderboard_2() {
            this.scores = new HashMap<>();
            this.topScores = new TreeMap<>((a, b) -> b - a);
        }

        public void addScore(int playerId, int score) {
            int curScore = scores.getOrDefault(playerId, 0);
            int newScore = curScore + score;

            scores.put(playerId, newScore);

            removeFromTreeMap(topScores, curScore);
            topScores.put(newScore, topScores.getOrDefault(newScore, 0) + 1);
        }

        public int top(int K) {
            int res = 0;
            int count = 0;

            for (var entry : topScores.entrySet()) {
                if (count >= K) break;

                int score = entry.getKey();
                int scoreCount = entry.getValue();

                int remain = Math.min(K - count, scoreCount);
                res += remain * score;
                count += remain;
            }

            return res;
        }

        public void reset(int playerId) {
            int curScore = scores.get(playerId);
            scores.remove(playerId);
            removeFromTreeMap(topScores, curScore);
        }

        private void removeFromTreeMap(TreeMap<Integer, Integer> map, int key) {
            if (!map.containsKey(key)) return;

            map.put(key, topScores.get(key) - 1);
            if (topScores.get(key) == 0) topScores.remove(key);
        }
    }
}
