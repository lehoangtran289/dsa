package leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class M_355_DesignTwitter {

    private final Map<Integer, List<int[]>> userTweetsMap;
    private final Map<Integer, Set<Integer>> userFollowingMap;
    private int time;

    /**
     * Idea: {user -> [{time, tweetIds}]}, {user -> {following}}
     * User merge k sorted list with max heap to construct top 10 tweets
     */
    public M_355_DesignTwitter() {
        this.userTweetsMap = new HashMap<>();
        this.userFollowingMap = new HashMap<>();
    }

    /**
     * TC: O(1)
     */
    public void postTweet(int userId, int tweetId) {
        userTweetsMap.putIfAbsent(userId, new ArrayList<>());
        userTweetsMap.get(userId).add(new int[]{time++, tweetId});
    }

    /**
     * TC: O(k log k), k = size max heap
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();

        // heap entries: [userId, index] - index points into that user's tweet list
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
                Integer.compare(
                        userTweetsMap.get(b[0]).get(b[1])[0],
                        userTweetsMap.get(a[0]).get(a[1])[0]
                )
        );

        // if user has tweet(s), add to heap
        if (userTweetsMap.containsKey(userId)) {
            maxHeap.add(new int[]{userId, userTweetsMap.get(userId).size() - 1});
        }

        // add following tweets to heap
        if (userFollowingMap.containsKey(userId)) {
            for (int followingId : userFollowingMap.get(userId)) {
                if (userTweetsMap.containsKey(followingId)) {
                    maxHeap.add(new int[]{followingId, userTweetsMap.get(followingId).size() - 1});
                }
            }
        }

        while (!maxHeap.isEmpty() && result.size() < 10) {
            int[] top = maxHeap.poll();
            result.add(userTweetsMap.get(top[0]).get(top[1])[1]);

            top[1]--;
            if (top[1] >= 0) maxHeap.add(top);
        }

        return result;
    }

    /**
     * TC: O(1)
     */
    public void follow(int followerId, int followeeId) {
        userFollowingMap.putIfAbsent(followerId, new HashSet<>());
        userFollowingMap.get(followerId).add(followeeId);
    }

    /**
     * TC: O(1)
     */
    public void unfollow(int followerId, int followeeId) {
        if (!userFollowingMap.containsKey(followerId)) return;
        userFollowingMap.get(followerId).remove(followeeId);
    }
}
