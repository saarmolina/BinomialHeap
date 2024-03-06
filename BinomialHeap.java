/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers.
 * Based on exercise from previous semester.
 */
public class BinomialHeap {
    public int size;
    public HeapNode last;
    public HeapNode min;
    public int treeNum;


    /**
     * Constructor for the BinomialHeap class.
     * Initializes a new binomial heap with default values.
     */
    public BinomialHeap() {
        // Initialize the size of the heap (number of elements)
        this.size = 0;

        // Initialize the number of trees in the heap
        this.treeNum = 0;

        // Initialize the last tree in the heap
        this.last = null;

        // Initialize the minimum root node in the heap
        this.min = null;
    }


    /**
     * pre: key > 0
     * <p>
     * Insert (key,info) into the heap and return the newly generated HeapItem.
     */

    public HeapItem insert(int key, String info) {

        // Create a new HeapItem with the specified key and information
        HeapItem item = new HeapItem(null, key, info);

        // Create a new HeapNode to represent the single-node tree
        HeapNode node = new HeapNode();

        // Initialize the HeapNode properties
        node.rank = 0;
        node.item = item;
        node.next = node;
        node.parent = null;
        node.child = null;

        // Link the HeapItem to the HeapNode
        item.node = node;

        // Create a new binomial heap with the single-node tree
        BinomialHeap newHeap = new BinomialHeap();
        newHeap.size = 1;
        newHeap.treeNum = 1;
        newHeap.last = node;
        newHeap.min = node;

        // Meld the new heap with the existing heap
        this.meld(newHeap);

        // Return the newly generated HeapItem
        return item;
    }


    /**
     * Delete the minimal item
     */

    public void deleteMin() {

        // Get the HeapNode representing the minimum element
        HeapNode minNode = this.min;
        HeapNode next = minNode.next;

        // Case: Only one tree in the heap
        if (next == minNode) {

            // If the minimum node has no children, set heap properties to empty
            if (minNode.child == null) {
                this.last = null;
                this.min = null;
                this.treeNum = 0;
                this.size = 0;
            }
            else {
                // Create a new heap for the children of the minimum node
                BinomialHeap childHeap = new BinomialHeap();
                childHeap.last = minNode.child;
                HeapNode child = childHeap.last.next;

                // Remove parent links for each child and update treeNum
                while (child.parent != null) {
                    child.parent = null;
                    childHeap.treeNum += 1;
                    child = child.next;
                }

                // Update heap properties with the new heap of children
                this.last = childHeap.last;
                this.size -= 1;
                this.treeNum = childHeap.treeNum;

                // Update the minimum element in the heap
                this.updateMin();
            }
        }

        else {
            // Case: More than one tree in the heap
            HeapNode prev = minNode;

            // Find the node before the minimum node in the circular linked list
            while (prev.next != minNode) {
                prev = prev.next;
            }

            // Remove the minimum node from the circular linked list
            prev.next = next;
            this.treeNum -= 1;
            this.size -= 1;

            // If the minimum node has children, meld them with the heap
            if (minNode.child != null) {
                BinomialHeap childHeap = new BinomialHeap();
                childHeap.last = minNode.child;
                HeapNode child = childHeap.last.next;

                // Remove parent links for each child and update treeNum
                while (child.parent != null) {
                    child.parent = null;
                    childHeap.treeNum += 1;
                    child = child.next;
                }

                // Meld the heap of children with the current heap
                this.meld(childHeap);
            }

            // Update the minimum element in the heap
            this.updateMin();
        }
    }


    /**
     * Return the minimal HeapItem
     */

    public HeapItem findMin() {

        // Return the HeapItem associated with the minimal node
        return this.min.item;
    }


    /**
     * Updates the reference to the minimal HeapItem in the binomial heap.
     */

    public void updateMin() {

        // Start with the last node as the initial candidate for the minimum
        HeapNode y = this.last;
        HeapNode min = y;
        y = y.next;

        // Iterate through the circular linked list to find the new minimum
        while (y != this.last) {
            // Compare the key of the current node with the current minimum
            if (y.item.key < min.item.key) {
                // Update the minimum node if the current node has a smaller key
                min = y;
            }

            // Move to the next node in the circular linked list
            y = y.next;
        }

        // Update the reference to the minimal HeapItem in the heap
        this.min = min;
    }


    /**
     * pre: 0 < diff < item.key
     * <p>
     * Decrease the key of item by diff and fix the heap.
     */

    public void decreaseKey(HeapItem item, int diff) {
        // Decrease the key of the specified HeapItem by the given difference
        item.key = item.key - diff;

        // Adjust the heap by performing a heapify-up operation
        heapifyUp(item.node);

        // Update the reference to the minimal HeapItem in the heap
        updateMin();
    }

    /**
     * Performs the heapify-up operation on the binomial heap starting from the specified node.
     */

    public void heapifyUp(HeapNode node) {

        // Start with the specified node and its parent
        HeapNode x = node;
        HeapNode parent = x.parent;

        // Continue heapify-up until the root is reached (parent becomes null)
        while (parent != null) {
            // Compare the key of the current node with its parent's key
            if (x.item.key < parent.item.key) {
                // Swap the items of the current node and its parent
                HeapItem temp = x.item;

                x.item = parent.item;
                x.item.node = x;

                parent.item = temp;
                parent.item.node = parent;

                // Move to the parent level for the next iteration
                x = x.parent;
                parent = x.parent;
            } else {
                // If the current node's key is not smaller than its parent's key, break the loop
                break;
            }
        }
    }

    /**
     * Delete the item from the heap.
     */

    public void delete(HeapItem item) {

        // Replace the key of the specified item with the minimum key in the heap
        item.key = this.findMin().key;

        // Decrease the key of the item by 1 to ensure it becomes the new minimum
        this.decreaseKey(item, 1);

        // Delete the new minimum item from the heap
        this.deleteMin();
    }

    /**
     * Merges two binomial heaps and returns the head of the resulting merged heap.
     */

    public HeapNode merge(BinomialHeap heap1, BinomialHeap heap2) {

        // Initialize pointers to the last nodes of each binomial heap
        HeapNode head1 = heap1.last;
        HeapNode head2 = heap2.last;
        HeapNode newHead;

        int k = 0;

        // Check if either heap is empty
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        // Determine the maximum rank among the last nodes of both heaps
        if (head1.rank < head2.rank)
            k = head2.rank;
        else
            k = head1.rank;

        // Move to the first nodes in both heaps
        head1 = head1.next;
        head2 = head2.next;

        newHead = null;
        HeapNode x = null;

        // Iterate through each rank, merging nodes with the same rank
        for (int i = 0; i < k + 1; i++) {

            // Merge nodes from heap1 with the same rank
            if (head1.rank == i) {

                if (x == null) {
                    newHead = head1;
                    x = head1;
                }
                else {
                    x.next = head1;
                    x = x.next;
                }

                head1 = head1.next;

            }

            // Merge nodes from heap2 with the same rank
            if (head2.rank == i) {

                if (x == null) {
                    newHead = head2;
                    x = head2;
                }
                else {
                    x.next = head2;
                    x = x.next;
                }

                head2 = head2.next;
            }
        }

        // Connect the last node to form a circular linked list
        x.next = newHead;

        // Return the head of the merged binomial heap
        return x;
    }


    /**
     * Meld the heap with heap2
     */

    public void meld(BinomialHeap heap2) {

        // Create a new binomial heap h to store the merged heaps
        BinomialHeap h = new BinomialHeap();

        // Update the size and treeNum properties of the new heap h
        h.size = this.size + heap2.size;
        h.treeNum = this.treeNum + heap2.treeNum;

        // Merge the two heaps and set the last node of h
        h.last = merge(this, heap2);

        // Check if the merged heap h is empty
        if (h.empty()) {
            this.last = h.last;
        }
        else {

            // Initialize pointers for merging
            HeapNode prevX = h.last;
            HeapNode x = prevX.next;
            HeapNode nextX = x.next;
            HeapNode head = h.last.next;

            // Iterate through the circular linked list of merged heap nodes
            while (nextX != head) {

                // Check conditions for merging trees with the same rank
                if ((x.rank != nextX.rank) || (nextX.next != head && nextX.next.rank == x.rank)) {
                    // Move to the next nodes if conditions are not met
                    prevX = x;
                    x = x.next;
                    nextX = nextX.next;
                }

                else {
                    // Link trees with the same rank
                    HeapNode temp = nextX.next;

                    if (temp == x) { // If we are in a loop
                        x = HeapNode.link(x, nextX);
                        h.treeNum = h.treeNum - 1;
                        head = x;
                        nextX = x.next;
                    }

                    else { // If we are not in a loop
                        boolean headChanged = false;

                        if (x == head) {
                            headChanged = true;
                        }

                        // Link the nodes and update treeNum
                        x = HeapNode.link(x, nextX);
                        h.treeNum = h.treeNum - 1;
                        prevX.next = x;
                        x.next = temp;
                        nextX = x.next;

                        if (headChanged)
                            head = x;
                    }
                }
            }

            // Update the last node and min of h
            h.last = x;
            h.updateMin();

            // Update properties of the current heap with the properties of the merged heap h
            this.last = h.last;
            this.treeNum = h.treeNum;
            this.size = h.size;
            this.min = h.min;
        }
    }


    /**
     * Return the number of elements in the heap
     */

    public int size() {
        // Return the size property of the binomial heap
        return this.size;
    }


    /**
     * The method returns true if and only if the heap
     * is empty.
     */

    public boolean empty() {
        // Return true if the size property of the binomial heap is 0, indicating an empty heap
        return (this.size == 0);
    }


    /**
     * Return the number of trees in the heap.
     */
    public int numTrees() {
        // Return the treeNum property, representing the number of trees in the binomial heap
        return this.treeNum;
    }


    public void printHeap() {
        if (empty()) {
            System.out.println("Heap is empty");
            return;
        }
        System.out.println("Binomial Heap:");
        HeapNode currentRoot = last;
        HeapNode stopNode = last.next; // Stop condition for circular list of roots
        boolean stop = false;

        do {
            System.out.println("Root: " + currentRoot.item.key);
            System.out.println("Min: " + this.min.item.key);
            printTree(currentRoot, 0, currentRoot); // Print the tree rooted at current root
            currentRoot = currentRoot.next;
            if (currentRoot == stopNode) {
                stop = true; // We've visited all roots
            }
        } while (!stop);
    }

    private void printTree(HeapNode node, int depth, HeapNode initialRoot) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  "); // Adjust spacing for depth
        }
        sb.append(node.item.key).append(" [").append(node.rank).append("]");

        System.out.println(sb.toString());

        if (node.child != null) {
            printTree(node.child, depth + 1, node.child); // Print child recursively
        }

        if (node.next != node.parent && node.next != null && node.next != initialRoot) {
            printTree(node.next, depth, initialRoot); // Print sibling recursively until we reach the initial root
        }
    }


    /**
     * Class implementing a node in a Binomial Heap.
     */
    public class HeapNode {

        public HeapItem item;
        public HeapNode child;
        public HeapNode next;
        public HeapNode parent;
        public int rank;

        public HeapNode() {
            // Initialize the item to null and the rank to -1
            this.item = null;
            this.rank = -1;
        }


        /**
         * Links two HeapNodes and returns the resulting linked node.
         */

        public static HeapNode link(HeapNode node1, HeapNode node2) {

            // Retrieve keys of the two nodes
            int a = node1.item.key;
            int b = node2.item.key;

            // Store the next node of node2
            HeapNode next = node2.next;

            // Check if node2 is the next node of node1
            if (next == node1) {
                // Set next pointers to themselves to create a circular linked list
                node1.next = node1;
                node2.next = node2;
            }

            // Compare keys to determine the parent-child relationship
            if (a < b) {
                // If node1 has rank 0, set node2 as its child
                if (node1.rank == 0) {
                    node1.child = node2;
                    node2.parent = node1;
                    node2.next = node2;
                } else {
                    // Insert node2 as the new child of node1 and update pointers
                    HeapNode childNext = node1.child.next;
                    node1.child.next = node2;
                    node2.next = childNext;
                    node2.parent = node1;
                    node1.child = node2;
                }

                // Increment the rank of node1
                node1.rank += 1;
                return node1;
            } else {
                // If node2 has rank 0, set node1 as its child
                if (node2.rank == 0) {
                    node2.child = node1;
                    node1.parent = node2;
                    node1.next = node1;
                } else {
                    // Insert node1 as the new child of node2 and update pointers
                    HeapNode childNext = node2.child.next;
                    node2.child.next = node1;
                    node1.next = childNext;
                    node1.parent = node2;
                    node2.child = node1;
                }

                // Update node1 to be the new root, incrementing its rank
                node1 = node2;
                node2.rank += 1;
                return node2;
            }
        }

        /**
     * Class implementing an item in a Binomial Heap.
     */
    public class HeapItem {

        public HeapNode node;
        public int key;
        public String info;

        public HeapItem(HeapNode node, int key, String info) {

            // Initialize the HeapItem with the specified node, key, and additional information
            this.node = node;
            this.key = key;
            this.info = info;

        }

    }
}