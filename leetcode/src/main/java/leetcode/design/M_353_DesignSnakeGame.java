package leetcode.design;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class M_353_DesignSnakeGame {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U, D, L, R

    private final int width;
    private final int height;
    private final Set<Cell> snakePath;
    private final ArrayDeque<Cell> snake;
    private final int[][] food;

    private int foodId;
    private int score;

    public M_353_DesignSnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.snakePath = new HashSet<>();
        this.snake = new ArrayDeque<>();
        this.food = food;
        this.foodId = 0;
        this.score = 0;

        // init snake
        snake.addFirst(new Cell(0, 0));
        snakePath.add(new Cell(0, 0));
    }

    public int move(String direction) {
        int[] dir = getDir(direction);

        // process snake next location
        Cell curHead = snake.peekFirst();
        int nextX = curHead.x + dir[0];
        int nextY = curHead.y + dir[1];

        // assume that the snake does not eat food -> reduce its tail temporarily
        Cell tail = snake.pollLast();
        snakePath.remove(new Cell(tail.x, tail.y));

        // check if the snake step is valid (no wall touch, no eat itself)
        if (!isValidStep(nextX, nextY)) {
            return -1;
        }

        // check if food is eaten
        if (
                foodId < food.length
                && nextX == food[foodId][0] && nextY == food[foodId][1] // eat food
        ) {
            score++;
            foodId++;

            // give back the previously reduced tail
            snakePath.add(new Cell(tail.x, tail.y));
            snake.add(tail);
        }

        // add new step at head
        snake.addFirst(new Cell(nextX, nextY));
        snakePath.add(new Cell(nextX, nextY));

        return score;
    }

    private int[] getDir(String d) {
        switch (d) {
            case "U":
                return DIRS[0];
            case "D":
                return DIRS[1];
            case "L":
                return DIRS[2];
            default:  // R
                return DIRS[3];
        }
    }

    private boolean isValidStep(int x, int y) {
        return  x >= 0 && x < height && y >= 0 && y < width &&
                !snakePath.contains(new Cell(x, y)); // snake eats itself
    }

    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            Cell cell = (Cell) obj;
            return this.x == cell.x && this.y == cell.y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }
}
