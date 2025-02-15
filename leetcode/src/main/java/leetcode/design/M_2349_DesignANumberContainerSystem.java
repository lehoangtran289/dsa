package leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class M_2349_DesignANumberContainerSystem {

    static class NumberContainers {
        private final Map<Integer, Integer> containerMap;
        private final Map<Integer, TreeSet<Integer>> numberMap;

        public NumberContainers() {
            containerMap = new HashMap<>();
            numberMap = new HashMap<>();
        }

        public void change(int index, int number) {
            // update numberMap to remove index from prevNumber
            if (containerMap.containsKey(index)) {
                int prevNumber = containerMap.get(index);

                TreeSet<Integer> containers = numberMap.get(prevNumber);
                containers.remove(index);

                if (containers.isEmpty()) {
                    numberMap.remove(prevNumber);
                }
            }

            // update numberMap to include index to number
            if (numberMap.containsKey(number)) {
                numberMap.get(number).add(index);
            } else {
                TreeSet<Integer> containerSet = new TreeSet<>();
                containerSet.add(index);
                numberMap.put(number, containerSet);
            }

            // put number into index
            containerMap.put(index, number);
        }

        public int find(int number) {
            if (!numberMap.containsKey(number)) return -1;

            return numberMap.get(number).first();
        }
    }

}
