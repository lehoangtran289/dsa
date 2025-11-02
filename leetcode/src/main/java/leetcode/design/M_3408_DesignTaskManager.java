package leetcode.design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class M_3408_DesignTaskManager {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(
                Arrays.asList(
                        Arrays.asList(1, 101, 5),
                        Arrays.asList(2, 102, 3),
                        Arrays.asList(3, 103, 4)
                )
        );

        taskManager.add(4, 104, 6);
        taskManager.edit(102, 7);
        taskManager.rmv(101);
        System.out.println(taskManager.execTop()); // Output: 2
        System.out.println(taskManager.execTop()); // Output: 4
    }

    /**
     * Priority Queue + HashMap with Lazy Deletion
     * --------------------------------------------
     * TC: Initialization takes O(nlogn)
     *      Add and edit each take O(log(n+m))
     *      Rmv takes O(1).
     *      execTop has an average cost of O(log(n+m))
     * SC: O(n+m)
     */
    static class TaskManager2 {
        private final Map<Integer, Task> taskInfoMap;
        private final PriorityQueue<Task> taskPriorityHeap;

        public TaskManager2(List<List<Integer>> tasks) {
            this.taskInfoMap = new HashMap<>();
            this.taskPriorityHeap = new PriorityQueue<>();

            for (List<Integer> taskInfo : tasks) {
                int userId = taskInfo.get(0);
                int taskId = taskInfo.get(1);
                int priority = taskInfo.get(2);

                Task task = new Task(userId, taskId, priority);
                taskInfoMap.put(taskId, task);
                taskPriorityHeap.add(task);
            }
        }

        public void add(int userId, int taskId, int priority) {
            Task task = new Task(userId, taskId, priority);
            taskInfoMap.put(taskId, task);
            taskPriorityHeap.add(task);
        }

        public void edit(int taskId, int newPriority) {
            Task curTask = taskInfoMap.get(taskId);
            Task newTask = new Task(curTask.userId, taskId, newPriority);

            taskInfoMap.put(taskId, newTask);
            taskPriorityHeap.add(newTask);
        }

        public void rmv(int taskId) {
            taskInfoMap.remove(taskId);
        }

        public int execTop() {
            while (!taskPriorityHeap.isEmpty()) {
                Task curTask = taskPriorityHeap.poll();
                int taskId = curTask.taskId;

                if (
                        taskInfoMap.containsKey(taskId)
                        && curTask.priority == taskInfoMap.get(taskId).priority
                ) {
                    Task top = taskInfoMap.get(taskId);
                    taskInfoMap.remove(taskId);
                    return top.userId;
                }
            }
            return -1;
        }

        static class Task implements Comparable<Task> {
            int userId;
            int taskId;
            int priority;

            Task(int userId, int taskId, int priority) {
                this.userId = userId;
                this.taskId = taskId;
                this.priority = priority;
            }

            @Override
            public int compareTo(Task other) {
                return this.priority == other.priority ?
                        other.taskId - this.taskId :
                        other.priority - this.priority;
            }
        }
    }

    /**
     * TreeSet + HashMap
     * -------------------
     * TC: O(log n) for add, edit, rmv, execTop
     * SC: O(n)
     */
    static class TaskManager {
        private final Map<Integer, Task> taskInfoMap;
        private final TreeSet<Task> taskPrioritySet;

        public TaskManager(List<List<Integer>> tasks) {
            this.taskInfoMap = new HashMap<>();
            this.taskPrioritySet = new TreeSet<>();

            for (List<Integer> taskInfo : tasks) {
                int userId = taskInfo.get(0);
                int taskId = taskInfo.get(1);
                int priority = taskInfo.get(2);

                Task task = new Task(userId, taskId, priority);
                taskInfoMap.put(taskId, task);
                taskPrioritySet.add(task);
            }
        }

        public void add(int userId, int taskId, int priority) {
            Task task = new Task(userId, taskId, priority);
            taskInfoMap.put(taskId, task);
            taskPrioritySet.add(task);
        }

        public void edit(int taskId, int newPriority) {
            Task curTask = taskInfoMap.get(taskId);
            Task newTask = new Task(curTask.userId, taskId, newPriority);

            taskInfoMap.put(taskId, newTask);
            taskPrioritySet.remove(curTask);
            taskPrioritySet.add(newTask);
        }

        public void rmv(int taskId) {
            Task task = taskInfoMap.get(taskId);
            taskInfoMap.remove(taskId);
            taskPrioritySet.remove(task);
        }

        public int execTop() {
            if (taskPrioritySet.isEmpty()) return -1;

            Task top = taskPrioritySet.first();
            taskPrioritySet.remove(top);
            return top.userId;
        }

        static class Task implements Comparable<Task> {
            int userId;
            int taskId;
            int priority;

            Task(int userId, int taskId, int priority) {
                this.userId = userId;
                this.taskId = taskId;
                this.priority = priority;
            }

            @Override
            public int compareTo(Task other) {
                return this.priority == other.priority ?
                        other.taskId - this.taskId :
                        other.priority - this.priority;
            }
        }
    }
}
