package leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class H_631P_DesignExcelSumFormula {

    private final int width;
    private final Cell[][] sheet;
    private final Map<Integer, Set<Integer>> dependants; // cell -> cells that are affected by it

    public H_631P_DesignExcelSumFormula(int height, char width) {
        this.width = width - 'A' + 1;
        this.dependants = new HashMap<>();
        this.sheet = new Cell[height][this.width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                sheet[i][j] = new Cell(encode(i, j), 0);
            }
        }
    }

    public void set(int row, char column, int val) {
        Cell curCell = sheet[row - 1][column - 'A'];

        removeDependants(curCell);

        curCell.val = val;
        curCell.formula = new HashMap<>();

        propagate(curCell);
    }

    public int get(int row, char column) {
        return sheet[row - 1][column - 'A'].val;
    }

    public int sum(int row, char column, String[] numbers) {
        Cell curCell = sheet[row - 1][column - 'A'];

        removeDependants(curCell);

        curCell.formula = parse(numbers);
        compute(curCell);

        // add curCell id to its dependants
        for (int dependantId : curCell.formula.keySet()) {
            dependants.putIfAbsent(dependantId, new HashSet<>());
            dependants.get(dependantId).add(curCell.id);
        }

        propagate(curCell);

        return curCell.val;
    }

    /**
     * Remove current cell from affected cells' dependant set
     */
    private void removeDependants(Cell curCell) {
        if (curCell.formula.isEmpty()) return;

        for (int dependantId : curCell.formula.keySet()) {
            if (dependants.containsKey(dependantId)) {
                dependants.get(dependantId).remove(curCell.id);
            }
        }
    }

    /**
     * Compute and update the value of all cells affected by input cell
     */
    private void propagate(Cell cell) {
        if (!dependants.containsKey(cell.id)) return;

        for (int dependantId : dependants.get(cell.id)) {
            int row = dependantId / width;
            int col = dependantId % width;
            Cell dependantCell = sheet[row][col];

            compute(dependantCell);
            propagate(dependantCell);
        }
    }

    /**
     * Compute and update the value of current cell
     */
    private void compute(Cell cell) {
        if (cell.formula.isEmpty()) return;

        int res = 0;
        for (var entry : cell.formula.entrySet()) {
            int dependantId = entry.getKey();
            int count = entry.getValue();

            int row = dependantId / width;
            int col = dependantId % width;
            Cell dependantCell = sheet[row][col];

            res += dependantCell.val * count;
        }
        cell.val = res;
    }

    /**
     * Parse input query to a mapping of [cell id -> count]
     */
    private Map<Integer, Integer> parse(String[] numbers) {
        Map<Integer, Integer> res = new HashMap<>(); // pos -> count

        for (String number : numbers) {
            String[] parts = number.split(":");

            if (parts.length == 1) {
                int row = Integer.parseInt(parts[0].substring(1)) - 1;
                int col = parts[0].charAt(0) - 'A';
                int id = encode(row, col);
                res.put(id, res.getOrDefault(id, 0) + 1);
            } else {
                int rowStart = Integer.parseInt(parts[0].substring(1)) - 1;
                int colStart = parts[0].charAt(0) - 'A';
                int rowEnd = Integer.parseInt(parts[1].substring(1)) - 1;
                int colEnd = parts[1].charAt(0) - 'A';

                for (int i = rowStart; i <= rowEnd; ++i) {
                    for (int j = colStart; j <= colEnd; ++j) {
                        int id = encode(i, j);
                        res.put(id, res.getOrDefault(id, 0) + 1);
                    }
                }
            }
        }

        return res;
    }

    private int encode(int row, int col) {
        return row * width + col;
    }

    static class Cell {
        int id;
        int val;
        Map<Integer, Integer> formula;

        Cell(int id, int val) {
            this.id = id;
            this.val = val;
            this.formula = new HashMap<>();
        }
    }
}
