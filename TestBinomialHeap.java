public class TestBinomialHeap {


    public static void testInsertAndFindMin() {
        BinomialHeap binomialHeap = new BinomialHeap();

        // Insert elements into the heap
        //BinomialHeap.HeapItem item1 = binomialHeap.insert(5, "Info1");

        //System.out.println(binomialHeap.last.item.key);


        //BinomialHeap.HeapItem item2 = binomialHeap.insert(3, "Info2");

        //System.out.println(binomialHeap.last.item.key);


        //BinomialHeap.HeapItem item3 = binomialHeap.insert(7, "Info3");

        // Check if size is correct
        //System.out.println(binomialHeap.size+ " Heap size should be 3 after three insertions");

        // Check if findMin returns the correct minimum element
        //System.out.println(binomialHeap.findMin().key +" Minimum key should be 3");

        //binomialHeap.printHeap();


        BinomialHeap binomialHeap1 = new BinomialHeap();
        binomialHeap1.insert(15, "Info");
        binomialHeap1.insert(33, "Info");
        binomialHeap1.insert(28, "Info");
        binomialHeap1.insert(41, "Info");
        binomialHeap1.insert(7, "Info");
        binomialHeap1.insert(25, "Info");
        binomialHeap1.insert(12, "Info");

        binomialHeap1.printHeap();

        BinomialHeap binomialHeap2 = new BinomialHeap();
        binomialHeap2.insert(44, "Info");
        binomialHeap2.insert(6, "Info");
        binomialHeap2.insert(17, "Info");
        binomialHeap2.insert(10, "Info");
        binomialHeap2.insert(31, "Info");
        binomialHeap2.insert(29, "Info");
        binomialHeap2.insert(50, "Info");
        binomialHeap2.insert(48, "Info");
        binomialHeap2.insert(22, "Info");
        binomialHeap2.insert(8, "Info");
        binomialHeap2.insert(24, "Info");
        binomialHeap2.insert(23, "Info");
        binomialHeap2.insert(32, "Info");
        binomialHeap2.insert(30, "Info");
        binomialHeap2.insert(55, "Info");
        binomialHeap2.insert(45, "Info");
        binomialHeap2.insert(37, "Info");
        binomialHeap2.insert(3, "Info");
        binomialHeap2.insert(18, "Info");

        binomialHeap2.printHeap();

        binomialHeap1.meld(binomialHeap2);

        binomialHeap1.printHeap();


    }

    public static void testDecreaseKey() {
        BinomialHeap binomialHeap = new BinomialHeap();

        // Insert elements into the heap
        BinomialHeap.HeapItem item1 = binomialHeap.insert(5, "Info1");
        BinomialHeap.HeapItem item2 = binomialHeap.insert(3, "Info2");

        binomialHeap.printHeap();

        // Decrease the key of item1
        binomialHeap.decreaseKey(item1, 3);

        binomialHeap.printHeap();

        // Check if findMin returns the correct minimum element
        System.out.println(binomialHeap.findMin().key +" Minimum key should be 3");

        BinomialHeap binomialHeap1 = new BinomialHeap();
        binomialHeap1.insert(15, "Info");
        binomialHeap1.insert(33, "Info");
        binomialHeap1.insert(28, "Info");
        binomialHeap1.insert(41, "Info");
        binomialHeap1.insert(7, "Info");
        binomialHeap1.insert(25, "Info");
        binomialHeap1.insert(12, "Info");
        BinomialHeap binomialHeap2 = new BinomialHeap();
        binomialHeap2.insert(44, "Info");
        binomialHeap2.insert(6, "Info");
        binomialHeap2.insert(17, "Info");
        binomialHeap2.insert(10, "Info");
        binomialHeap2.insert(31, "Info");
        binomialHeap2.insert(29, "Info");
        binomialHeap2.insert(50, "Info");
        binomialHeap2.insert(48, "Info");
        binomialHeap2.insert(22, "Info");
        binomialHeap2.insert(8, "Info");
        binomialHeap2.insert(24, "Info");
        binomialHeap2.insert(23, "Info");
        binomialHeap2.insert(32, "Info");
        binomialHeap2.insert(30, "Info");
        binomialHeap2.insert(55, "Info");
        binomialHeap2.insert(45, "Info");
        binomialHeap2.insert(37, "Info");
        binomialHeap2.insert(3, "Info");
        binomialHeap2.insert(18, "Info");
        binomialHeap1.meld(binomialHeap2);

        binomialHeap1.printHeap();

        BinomialHeap.HeapItem item = binomialHeap1.last.next.item;
        System.out.println("key decreased: "+item.key);
        binomialHeap1.decreaseKey(item, 11);

        binomialHeap1.printHeap();
        System.out.println("min: "+binomialHeap1.findMin().key);

    }

    public static void testDeleteMin() {
        BinomialHeap binomialHeap = new BinomialHeap();

        // Insert elements into the heap
        BinomialHeap.HeapItem item1 = binomialHeap.insert(5, "Info1");
        BinomialHeap.HeapItem item2 = binomialHeap.insert(3, "Info2");
        BinomialHeap.HeapItem item3 = binomialHeap.insert(7, "Info3");

        binomialHeap.printHeap();

        // Delete the minimum element
        binomialHeap.deleteMin();

        binomialHeap.printHeap();


        // Check if size is correct after deletion
        System.out.println(binomialHeap.size + " Heap size should be 2 after deleting minimum element");

        // Check if findMin returns the correct minimum element after deletion
        System.out.println( binomialHeap.findMin().key + " Minimum key should be 5 after deletion");

        BinomialHeap binomialHeap1 = new BinomialHeap();
        binomialHeap1.insert(15, "Info");
        binomialHeap1.insert(33, "Info");
        binomialHeap1.insert(28, "Info");
        binomialHeap1.insert(41, "Info");
        binomialHeap1.insert(7, "Info");
        binomialHeap1.insert(25, "Info");
        binomialHeap1.insert(12, "Info");
        BinomialHeap binomialHeap2 = new BinomialHeap();
        binomialHeap2.insert(44, "Info");
        binomialHeap2.insert(6, "Info");
        binomialHeap2.insert(17, "Info");
        binomialHeap2.insert(10, "Info");
        binomialHeap2.insert(31, "Info");
        binomialHeap2.insert(29, "Info");
        binomialHeap2.insert(50, "Info");
        binomialHeap2.insert(48, "Info");
        binomialHeap2.insert(22, "Info");
        binomialHeap2.insert(8, "Info");
        binomialHeap2.insert(24, "Info");
        binomialHeap2.insert(23, "Info");
        binomialHeap2.insert(32, "Info");
        binomialHeap2.insert(30, "Info");
        binomialHeap2.insert(55, "Info");
        binomialHeap2.insert(45, "Info");
        binomialHeap2.insert(37, "Info");
        binomialHeap2.insert(3, "Info");
        binomialHeap2.insert(18, "Info");
        binomialHeap1.meld(binomialHeap2);

        binomialHeap1.printHeap();
        binomialHeap1.deleteMin();
        binomialHeap1.printHeap();

        // Check if size is correct after deletion
        System.out.println(binomialHeap1.size + " Heap size should be 25 after deleting minimum element");

        // Check if findMin returns the correct minimum element after deletion
        System.out.println( binomialHeap1.findMin().key + " Minimum key should be 6 after deletion");

        // Check if treeNum returns the correct minimum element after deletion
        System.out.println( binomialHeap1.treeNum + " Tree num should be 3 after deletion");

    }

    public static void testDelete() {

        BinomialHeap binomialHeap1 = new BinomialHeap();
        binomialHeap1.insert(15, "Info");
        binomialHeap1.insert(33, "Info");
        binomialHeap1.insert(28, "Info");
        binomialHeap1.insert(41, "Info");
        binomialHeap1.insert(7, "Info");
        binomialHeap1.insert(25, "Info");
        binomialHeap1.insert(12, "Info");
        BinomialHeap binomialHeap2 = new BinomialHeap();
        binomialHeap2.insert(44, "Info");
        binomialHeap2.insert(6, "Info");
        binomialHeap2.insert(17, "Info");
        binomialHeap2.insert(10, "Info");
        binomialHeap2.insert(31, "Info");
        binomialHeap2.insert(29, "Info");
        binomialHeap2.insert(50, "Info");
        binomialHeap2.insert(48, "Info");
        binomialHeap2.insert(22, "Info");
        binomialHeap2.insert(8, "Info");
        binomialHeap2.insert(24, "Info");
        binomialHeap2.insert(23, "Info");
        binomialHeap2.insert(32, "Info");
        binomialHeap2.insert(30, "Info");
        binomialHeap2.insert(55, "Info");
        binomialHeap2.insert(45, "Info");
        binomialHeap2.insert(37, "Info");
        binomialHeap2.insert(3, "Info");
        binomialHeap2.insert(18, "Info");
        binomialHeap1.meld(binomialHeap2);

        binomialHeap1.printHeap();

        BinomialHeap.HeapItem item = binomialHeap1.last.child.item;

        System.out.println("deleted key: "+item.key);

        binomialHeap1.delete(item);

        binomialHeap1.printHeap();

        // Check if size is correct after deletion
        System.out.println(binomialHeap1.size + " Heap size should be 25 after deleting minimum element");

        // Check if findMin returns the correct minimum element after deletion
        System.out.println( binomialHeap1.findMin().key + " Minimum key should be 3 after deletion");

        // Check if treeNum returns the correct minimum element after deletion
        System.out.println( binomialHeap1.treeNum + " Tree num should be 3 after deletion");

    }


    public static void main(String[] args) {

        System.out.println("Start");

        int i = 4;
        int n = (int) ((Math.pow(3, i+5)) - 1);
        int m = (int) ((Math.pow(2, 5)) - 1);

        System.out.println("i ="+i+" n = "+n);

        BinomialHeap binomialHeap = new BinomialHeap();

        long startTime = System.currentTimeMillis();


        for (int j = n; j >= 1; j--) {
            binomialHeap.insert(j, "info");
        }

        while (binomialHeap.size > m){
            binomialHeap.deleteMin();
        }

        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;

        System.out.println("Elapsed time: " + elapsedTime + " milliseconds");
        System.out.println(binomialHeap.link_counter);
        System.out.println(binomialHeap.treeNum);
        System.out.println(binomialHeap.sum_rank_deleted);






        System.out.println("Done");






    }

}
