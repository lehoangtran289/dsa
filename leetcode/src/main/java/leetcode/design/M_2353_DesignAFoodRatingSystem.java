package leetcode.design;

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
        private final Map<String, Food> foodMap;
        private final Map<String, TreeSet<Food>> cuisineMap;

        public FoodRatings(String[] foods, String[] cuisines, int[] rates) {
            this.foodMap = new HashMap<>();
            this.cuisineMap = new HashMap<>();

            for (int i = 0; i < foods.length; ++i) {
                foodMap.put(foods[i], new Food(foods[i], cuisines[i], rates[i]));

                if (cuisineMap.containsKey(cuisines[i])) {
                    cuisineMap.get(cuisines[i]).add(foodMap.get(foods[i]));
                } else {
                    TreeSet<Food> foodSet = new TreeSet<>((a, b) -> {
                        if (a.rating == b.rating) return a.food.compareTo(b.food);
                        return b.rating - a.rating;
                    });
                    foodSet.add(foodMap.get(foods[i]));
                    cuisineMap.put(cuisines[i], foodSet);
                }
            }
        }

        public void changeRating(String food, int newRating) {
            String curCuisine = foodMap.get(food).cuisine;

            TreeSet<Food> foodSet = cuisineMap.get(curCuisine);
            foodSet.remove(foodMap.get(food));
            foodMap.put(food, new Food(food, curCuisine, newRating));
            foodSet.add(foodMap.get(food));
        }

        public String highestRated(String cuisine) {
            return cuisineMap.get(cuisine).first().food;
        }
    }

    static class Food {
        String food;
        String cuisine;
        int rating;

        public Food(String food, String cuisine, int rating) {
            this.food = food;
            this.cuisine = cuisine;
            this.rating = rating;
        }

        @Override
        public boolean equals(Object obj) {
            return food.equals(((Food) obj).food);
        }
    }
}
