COMP 2113 FA01, 2026 — Assignment 4
Pawaninder Kaur | 0310308 | 0310308k@acadiau.ca
Date: 2026-10-20

FILES
MaxHeap.java    Question 1: Java MAX heap using ArrayList<Integer>
min_heap.py     Question 2: Python MIN heap using a list
numbers.txt     Sample input data (whitespace-separated integers)
CodeDiary.txt   Editable dated diary draft; verify actual activity before submission

RUN FROM THE DIRECTORY CONTAINING THESE FILES

Java (requires a JDK):
  javac MaxHeap.java
  java MaxHeap numbers.txt

Python (requires Python 3):
  python3 min_heap.py numbers.txt

Both programs accept an optional input filename. With no argument, they read
numbers.txt in the current directory. They show the initial heap, root,
removed root, resulting heap, and heap after adding 42.

Data format: whole numbers separated by spaces or newlines. Negative values
and duplicates are supported. An empty file produces an empty heap and
skips removal. Java rejects non-integer tokens; Python reports invalid input.

Each heap is stored as a flat array/list representing a complete binary tree.
For node i: parent = (i-1)//2, left = 2*i+1, right = 2*i+2.

DO NOT INCLUDE .class FILES IN THE SUBMISSION ZIP.
Please inspect, understand and verify all material, including integrity statements,
before submitting it as your own work.
