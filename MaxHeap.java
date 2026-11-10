////////////////////////////////////////////
// Course: COMP 2113 FA01, 2026
// Assignment 4, Question 1
// Author: Pawaninder Kaur
// Student ID: 0310308
// Email: 0310308k@acadiau.ca
// Date: 2026-10-20
// I certify that this code is my own.
// I have not broken any rules concerning Academic Dishonesty.
////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MaxHeap {
    private final ArrayList<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Read whitespace-separated integers from a text file.
    public void readFile(String filename) throws FileNotFoundException {
        heap.clear();
        try (Scanner file = new Scanner(new File(filename))) {
            while (file.hasNext()) {
                if (file.hasNextInt()) {
                    heap.add(file.nextInt());
                } else {
                    throw new IllegalArgumentException("Non-integer input: " + file.next());
                }
            }
        }
    }

    // Convert the current list into a max heap, from the bottom up.
    public void heapify() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            pushDown(i);
        }
    }

    public int getRoot() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("The heap is empty.");
        }
        return heap.get(0);
    }

    public int removeRoot() {
        int root = getRoot();
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            pushDown(0);
        }
        return root;
    }

    // Move a value down until it is at least as large as both children.
    public void pushDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;
            if (left < size && heap.get(left) > heap.get(largest)) {
                largest = left;
            }
            if (right < size && heap.get(right) > heap.get(largest)) {
                largest = right;
            }
            if (largest == index) {
                break;
            }
            swap(index, largest);
            index = largest;
        }
    }

    // Move a newly inserted value up toward the root as needed.
    public void bubbleUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(parent) >= heap.get(index)) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
    }

    public void add(int value) {
        heap.add(value);
        bubbleUp(heap.size() - 1);
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Display the heap by levels to show its tree structure.
    public void printHeap() {
        if (heap.isEmpty()) {
            System.out.println("(empty heap)");
            return;
        }
        int levelStart = 0;
        int levelSize = 1;
        while (levelStart < heap.size()) {
            int end = Math.min(levelStart + levelSize, heap.size());
            for (int i = levelStart; i < end; i++) {
                System.out.print(heap.get(i) + " ");
            }
            System.out.println();
            levelStart = end;
            levelSize *= 2;
        }
    }

    public static void main(String[] args) {
        String filename = args.length > 0 ? args[0] : "numbers.txt";
        MaxHeap maxHeap = new MaxHeap();
        try {
            maxHeap.readFile(filename);
            maxHeap.heapify();
            System.out.println("Initial max heap:");
            maxHeap.printHeap();
            if (!maxHeap.heap.isEmpty()) {
                System.out.println("Root: " + maxHeap.getRoot());
                System.out.println("Removed root: " + maxHeap.removeRoot());
            }
            System.out.println("After removing root:");
            maxHeap.printHeap();
            maxHeap.add(42);
            System.out.println("After adding 42:");
            maxHeap.printHeap();
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println("Input error: " + e.getMessage());
        }
    }
}
