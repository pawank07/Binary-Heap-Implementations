############################################
# Course: COMP 2113 FA01, 2026
# Assignment 4, Question 2
# Author: Pawaninder Kaur
# Student ID: 0310308
# Email: 0310308k@acadiau.ca
# Date: 2026-10-20
# I certify that this code is my own.
# I have not broken any rules concerning Academic Dishonesty.
############################################

import sys


class MinHeap:
    def __init__(self):
        self.heap = []

    def readFile(self, filename):
        """Read whitespace-separated integers from a text file."""
        with open(filename, "r", encoding="utf-8") as file:
            self.heap = [int(token) for token in file.read().split()]

    def heapify(self):
        """Turn the list into a min heap from the bottom up."""
        for index in range(len(self.heap) // 2 - 1, -1, -1):
            self.pushDown(index)

    def getRoot(self):
        if not self.heap:
            raise IndexError("The heap is empty.")
        return self.heap[0]

    def removeRoot(self):
        root = self.getRoot()
        last = self.heap.pop()
        if self.heap:
            self.heap[0] = last
            self.pushDown(0)
        return root

    def pushDown(self, index):
        """Move a value toward the smaller child until order is restored."""
        size = len(self.heap)
        while True:
            left = 2 * index + 1
            right = 2 * index + 2
            smallest = index
            if left < size and self.heap[left] < self.heap[smallest]:
                smallest = left
            if right < size and self.heap[right] < self.heap[smallest]:
                smallest = right
            if smallest == index:
                break
            self.heap[index], self.heap[smallest] = self.heap[smallest], self.heap[index]
            index = smallest

    def bubbleUp(self, index):
        """Move a new value up while it is smaller than its parent."""
        while index > 0:
            parent = (index - 1) // 2
            if self.heap[parent] <= self.heap[index]:
                break
            self.heap[parent], self.heap[index] = self.heap[index], self.heap[parent]
            index = parent

    def add(self, value):
        self.heap.append(value)
        self.bubbleUp(len(self.heap) - 1)

    def printHeap(self):
        """Print each level of the heap on its own line."""
        if not self.heap:
            print("(empty heap)")
            return
        start, level_size = 0, 1
        while start < len(self.heap):
            print(*self.heap[start:start + level_size])
            start += level_size
            level_size *= 2


def main():
    filename = sys.argv[1] if len(sys.argv) > 1 else "numbers.txt"
    min_heap = MinHeap()
    try:
        min_heap.readFile(filename)
    except (OSError, ValueError) as error:
        print(f"Input error: {error}")
        return
    min_heap.heapify()
    print("Initial min heap:")
    min_heap.printHeap()
    if min_heap.heap:
        print("Root:", min_heap.getRoot())
        print("Removed root:", min_heap.removeRoot())
    print("After removing root:")
    min_heap.printHeap()
    min_heap.add(42)
    print("After adding 42:")
    min_heap.printHeap()


if __name__ == "__main__":
    main()
