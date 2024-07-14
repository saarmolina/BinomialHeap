# Binomial Heap

## Overview

This repository contains an implementation of a Binomial Heap, a data structure that supports efficient merging of heaps, insertion, and deletion of the minimum element. This implementation is specifically designed for non-negative integers and is based on exercises from a previous semester.

## Features

- **BinomialHeap Class**: Implements the main structure and operations of the Binomial Heap.
- **HeapNode Class**: Represents individual nodes in the Binomial Heap.
- **HeapItem Class**: Represents items stored in the heap, containing a key and associated information.

## Key Operations

- `insert(int key, String info)`: Insert a new item into the heap.
- `deleteMin()`: Remove the minimum element from the heap.
- `findMin()`: Return the minimum element in the heap.
- `decreaseKey(HeapItem item, int diff)`: Decrease the key of a given item.
- `delete(HeapItem item)`: Delete a specific item from the heap.
- `meld(BinomialHeap heap2)`: Merge the current heap with another Binomial Heap.

## Time Complexities

- Insertion: O(log n)
- Delete Minimum: O(log n)
- Find Minimum: O(1)
- Decrease Key: O(log n)
- Delete: O(log n)
- Meld: O(log n)

Where n is the number of elements in the heap.

## Usage

To use this Binomial Heap implementation, create an instance of the `BinomialHeap` class:

```java
BinomialHeap heap = new BinomialHeap();

// Insert elements
HeapItem item1 = heap.insert(5, "Five");
HeapItem item2 = heap.insert(3, "Three");
HeapItem item3 = heap.insert(7, "Seven");

// Find minimum
HeapItem min = heap.findMin();
System.out.println("Minimum: " + min.key + " - " + min.info);

// Delete minimum
heap.deleteMin();

// Decrease key
heap.decreaseKey(item3, 2);

// Delete specific item
heap.delete(item1);

// Check if heap is empty
boolean isEmpty = heap.empty();

// Get heap size
int size = heap.size();

// Get number of trees in the heap
int numTrees = heap.numTrees();
