package Multithreading.ParallelAlgorithms;

import java.util.concurrent.RecursiveTask;

public class ParallelSumExample {
    static class parallelSum extends RecursiveTask<Integer> {
        int low;
        int high;
        int[] array;
        String taskName;

        parallelSum(int[] arr, int lo, int hi, String name) {
            array = arr;
            low = lo;
            high = hi;
            taskName = name;
        }

        @Override
        protected Integer compute() {
            System.out.println("Starting " + taskName + " for range [" + low + "," + high + ")");

            if (high - low <= 5) {
                int sum = 0;
                for (int i = low; i < high; ++i) {
                    sum += array[i];
                }
                System.out.println("Direct sum for " + taskName + ": " + sum);
                return sum;
            } else {
                int mid = low + (high - low) / 2;
                parallelSum left = new parallelSum(array, low, mid, taskName + "-Left");
                parallelSum right = new parallelSum(array, mid, high, taskName + "-Right");

                // Fork the subtasks to execute in parallel
                left.fork();
                right.fork();

                // Join the results of subtasks
                int result = left.join() + right.join();

                System.out.println("Combined result for " + taskName + ": " + result);
                return result;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = java.util.stream.IntStream.range(0, 20).toArray(); // Reduced size for clarity
        parallelSum task = new parallelSum(arr, 0, arr.length, "MainTask");
        System.out.println("Final sum: " + task.compute());
    }
}