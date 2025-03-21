package leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class M_2115_FindAllPossibleRecipesFromGivenSupplies {
    public static void main(String[] args) {
        System.out.println(new M_2115_FindAllPossibleRecipesFromGivenSupplies().findAllRecipes(
                new String[]{"bread", "ramen"},
                List.of(List.of("yeast", "flour")),
                new String[]{"yeast", "flour", "corn"}
        ));
    }

    /**
     * Topological sort
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> supplySet = new HashSet<>();
        Map<String, Integer> recipeMap = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();

        for (String s : supplies)
            supplySet.add(s);
        for (int i = 0; i < recipes.length; ++i)
            recipeMap.put(recipes[i], i);

        int[] indegree = new int[recipes.length];

        // build dependency graph
        for (int i = 0; i < recipes.length; ++i) {
            String recipe = recipes[i];
            List<String> ingreList = ingredients.get(i);

            for (String ingre : ingreList) {
                // add edge ingre -> recipe
                if (!supplySet.contains(ingre)) {
                    graph.putIfAbsent(ingre, new ArrayList<>());
                    graph.get(ingre).add(recipe);
                    indegree[i]++;
                }
            }
        }

        // Start with recipes that have no dependencies on other recipes
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < recipes.length; ++i) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int recipeId = queue.poll();
            String recipe = recipes[recipeId];
            res.add(recipe);

            // this recipe is ingredient for other recipes
            if (graph.containsKey(recipe)) {
                for (String nextRecipe : graph.get(recipe)) {
                    int nextRecipeId = recipeMap.get(nextRecipe);

                    // update indegree
                    indegree[nextRecipeId]--;
                    if (indegree[nextRecipeId] == 0) {
                        queue.add(nextRecipeId);
                    }
                }
            }
        }

        return res;
    }

    /**
     * BFS
     */
    public List<String> findAllRecipes1(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> supplySet = new HashSet<>();
        for (String s : supplies) supplySet.add(s);

        Queue<Integer> recipeQueue = new ArrayDeque<>();
        for (int i = 0; i < recipes.length; ++i) {
            recipeQueue.add(i);
        }
        List<String> res = new ArrayList<>();

        int prevSupplySize = -1;
        while (supplySet.size() > prevSupplySize) {
            prevSupplySize = supplySet.size();

            int queueSize = recipeQueue.size();
            for (int i = 0; i < queueSize; ++i) {
                int recipeId = recipeQueue.poll();

                // check if this recipe can be prepared
                boolean isPrepareable = true;
                for (String ingre : ingredients.get(recipeId)) {
                    if (!supplySet.contains(ingre)) {
                        isPrepareable = false;
                        break;
                    }
                }

                if (isPrepareable) {
                    supplySet.add(recipes[recipeId]);
                    res.add(recipes[recipeId]);
                } else {
                    recipeQueue.add(recipeId);
                }
            }
        }

        return res;
    }
}
