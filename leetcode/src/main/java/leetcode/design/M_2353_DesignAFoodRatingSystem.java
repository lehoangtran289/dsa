package leetcode.design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class M_2353_DesignAFoodRatingSystem {
    public static void main(String[] args) {
        FoodRatings foodRatings = new FoodRatings(
                new String[]{"f1", "f2", "f3"},
                new String[]{"c1", "c1", "c1"},
                new int[]{11, 2, 15}
        );

        foodRatings.changeRating("f1", 12);
        System.out.println(foodRatings.highestRated("c1"));
        foodRatings.changeRating("f3", 8);
        foodRatings.changeRating("f2", 5);
        System.out.println(foodRatings.highestRated("c1"));
        // [null,null,"f3",null,null,"f1"]
    }

    static class FoodRatings {
        private final String[] cuisines;
        private final int[] ratings;
        private final Map<String, Integer> foodMap;
        private final Map<String, TreeSet<String>> cuisineMap;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            this.cuisines = cuisines;
            this.ratings = ratings;
            this.foodMap = new HashMap<>();
            this.cuisineMap = new HashMap<>();

            for (int i = 0; i < foods.length; ++i) {
                foodMap.put(foods[i], i);

                if (!cuisineMap.containsKey(cuisines[i])) {
                    cuisineMap.put(cuisines[i], new TreeSet<>(new FoodRatingComparator()));
                }
                cuisineMap.get(cuisines[i]).add(foods[i]);
            }
        }

        private class FoodRatingComparator implements Comparator<String> {
            @Override
            public int compare(String a, String b) {
                return ratings[foodMap.get(a)] != ratings[foodMap.get(b)]
                        ? ratings[foodMap.get(b)] - ratings[foodMap.get(a)]
                        : a.compareTo(b);
            }
        }

        public void changeRating(String food, int newRating) {
            int foodIndex = foodMap.get(food);
            String cuisine = cuisines[foodIndex];

            cuisineMap.get(cuisine).remove(food);

            ratings[foodIndex] = newRating;
            cuisineMap.get(cuisine).add(food);
        }

        public String highestRated(String cuisine) {
            return cuisineMap.get(cuisine).first();
        }
    }
}
