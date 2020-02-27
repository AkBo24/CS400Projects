
public class CompareDS {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println
            ("CompareDS.main compares work time for: BST and RBT\n");
        
        trial1(new BST<Integer, Integer>(), new RBT<Integer, Integer>()); //test insert for 100000 nodes
        trial2(new BST<Integer, Integer>(), new RBT<Integer, Integer>()); //test contains for 1000
        trial3(new BST<Integer, Integer>(), new RBT<Integer, Integer>());
//        testTrial(new BST<Integer, Integer>(), new RBT<Integer, Integer>());
    }

    private static void testTrial(BST<Integer, Integer> dsM, RBT<Integer, Integer> dsB) {
        System.out.println("Test Trial");
        try {
            for(int i = 0; i < 100000; i++)
                dsM.insert(i, i);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test to compare insert operations between BST and an implementedDS
     * @param dsM
     * @param dsB
     */
    private static void trial1(BST<Integer, Integer> dsM, RBT<Integer, Integer> dsB) {
        // TODO Auto-generated method stub
        
        try {
            System.out.println("Trial 1: Inserting a large amount of items\n");
            
            //insert 100000 nodes into dsM
            long startTime = System.nanoTime();
            System.out.println("BST is inserting 10000 nodes");
            for(int i = 0; i < 1000000; i++) {
                dsM.insert(i, i);
            }
            long endTime = System.nanoTime();
            System.out.println("It took " + compare(startTime, endTime) 
                + "ns to process 10000 items\n");
            
            //insert 100000 nodes into dsB
            startTime = System.nanoTime();
            System.out.println("RBT is inserting 10000 nodes");
            for(int i = 0; i < 1000000; i++) {
                dsB.insert(i, i);
            }
            endTime = System.nanoTime();
            System.out.println("It took " + compare(startTime, endTime) 
                + "ns to process 10000 items");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Test to compare contains operation between BST and RBT
     * @param dsM
     * @param dsB
     */
    private static void trial2(BST<Integer, Integer> dsM, RBT<Integer, Integer> dsB) {
        // TODO Auto-generated method stub
        
        try {
            System.out.println("\nTrial 2: Removing 1000 nodes\n");
            
            //insert 1000 nodes into dsM
            for(int i = 0; i < 1000; i++) {
                dsM.insert(i, i);
            }
            long startTime = System.nanoTime();
            System.out.println("BST is checking if 10 nodes are present");
            for(int i = 0; i < 100; i++)
                dsM.contains(i);
            long endTime = System.nanoTime();
            System.out.println("It took " + compare(startTime, endTime) 
            + "ns to process 10 items\n");
            
            //insert 1000 nodes into dsB
            for(int i = 0; i < 1000; i++)
                dsB.insert(i, i);
            startTime = System.nanoTime();
            System.out.println("BST is checking if 10 nodes are present");
            for(int i = 0; i < 100; i++)
                dsB.contains(i);
            endTime = System.nanoTime();
            System.out.println("It took " + compare(startTime, endTime) 
            + "ns to process 10 items\n");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Test to compare traversal operation between BST and RBT
     * @param dsM
     * @param dsB
     */
    private static void trial3(BST<Integer, Integer> dsM, RBT<Integer, Integer> dsB) {
        // TODO Auto-generated method stub
        
        try {
            System.out.println("\nTrial 3: Traversing 10000 nodes\n");
            
            //insert 1000 nodes into dsM
            for(int i = 0; i < 10000; i++) {
                dsM.insert(i, i);
            }
            
            long startTime = System.nanoTime();
            System.out.println("BST is traversing 10000 nodes");
            dsM.getInOrderTraversal();
            long endTime = System.nanoTime();
            
            System.out.println("It took " + compare(startTime, endTime) 
            + "ns to traverse 10000 items\n");
            
            //insert 1000 nodes into dsB
            for(int i = 0; i < 10000; i++)
                dsB.insert(i, i);
            
            startTime = System.nanoTime();
            System.out.println("RBT is traversing 10000 nodes");
            dsB.getInOrderTraversal();
            endTime = System.nanoTime();
            
            System.out.println("It took " + compare(startTime, endTime) 
            + "ns to traverse 10000 items\n");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static String compare(long startTime, long endTime) {
        // TODO Auto-generated method stub
        return "" + (endTime - startTime);
    }

}
