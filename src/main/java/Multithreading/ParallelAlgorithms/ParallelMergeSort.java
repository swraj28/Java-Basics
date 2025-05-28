package Multithreading.ParallelAlgorithms;

import java.util.Arrays;
import java.util.concurrent.*;

/*
What’s happening:
At every recursive step, your code does this:

CompletableFuture.supplyAsync(() -> mergeSort(...), threadpoolExecutor);

So for each recursive call, you're trying to submit a new task to the thread pool — and since Merge Sort splits recursively, it quickly grows like this:


           mergeSort(arr)
                /             \
       mergeSort(left)     mergeSort(right)
         /      \              /      \
   ...and so on recursively for each half...
Each of these calls tries to run in a separate thread, but your thread pool is fixed at 2 threads:

Executors.newFixedThreadPool(2)
🧨 Problem:
Eventually both threads in the pool are busy waiting for child tasks to complete, but no more threads are available to run those child tasks → deadlock or infinite wait.
 */



public class ParallelMergeSort {

    private static final ExecutorService threadpoolExecutor = Executors.newFixedThreadPool(10);

    public static int[] mergeSort(int[] arr) throws ExecutionException, InterruptedException {
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // Sort both halves in parallel using CompletableFuture
        CompletableFuture<int[]> leftSortedFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("leftSortedFuture Thread Name {}" + Thread.currentThread().getName());
                return mergeSort(left);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },threadpoolExecutor);

        CompletableFuture<int[]> rightSortedFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("rightSortedFuture Thread Name {}" + Thread.currentThread().getName());
                return mergeSort(right);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },threadpoolExecutor);

        // Combine both sorted results
        CompletableFuture<int[]> resultFuture = leftSortedFuture.thenCombine(rightSortedFuture, ParallelMergeSort::merge);
        return resultFuture.get(); // Waits for both to finish
    }

    // Merge two sorted arrays
    private static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            merged[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
        }

        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        return merged;
    }

    // Driver
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] array = {5, 3, 8, 6, 2, 7, 4, 1};
        int[] sorted = mergeSort(array);

        System.out.println("Sorted array: " + Arrays.toString(sorted));

        threadpoolExecutor.shutdown();
    }
}

