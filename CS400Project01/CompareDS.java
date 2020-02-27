/**
 * CompareDS.java created by akshaybodla on MacBook Pro in CS400Project0
 * 
 * Author: Akshay Bodla (bodla@wisc.edu)
 * Date: @date
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * IDE: Eclipse IDE for Java Developers
 * 
 * Version: 2019-06 (4.12.0)
 * Build id: 20190614-1200
 * 
 * Device:  Akshay's Macbook Pro
 * OS    :  macOS High Sierra
 * Version: Version 10.13.6
 * 
 * List Collaborators: N/A
 * 
 * Other Credits: N/A
 */

/**
 * Class to compare my data structure to an implemented one
 * @author akshaybodla
 */
public class CompareDS {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println
            ("CompareDS.main Compares work time for: DS_My and DS_Brian\n");
        
        BST    dsM = new BST();
        DS_Brian dsB = new DS_Brian();
        trial1(dsM, dsB); // //test insert for 100000 nodes
        trial2(new BST(), new DS_Brian()); //test remove for 100 nodes
        trial3(new BST(), new DS_Brian()); //test contains for 1000
        trial4(new BST(), new DS_Brian()); //test insert for 100000

    }
    
    /**
     * Tests insert for a large amount of nodes (n = 100000)
     * @param dsM
     * @param dsB
     */
    private static void trial4(BST dsM, DS_Brian dsB) {
        // TODO Auto-generated method stub
        System.out.println("Trial 1: Inserting a large amount of items\n");
        
        //insert 100000 nodes into dsM
        long startTime = System.nanoTime();
        System.out.println("DS_My is inserting 100000 nodes");
        for(int i = 0; i < 100000; i++) {
            dsM.insert(""+i, ""+i);
        }
        long endTime = System.nanoTime();
        System.out.println("It took " + compare(startTime, endTime) 
            + "ns to process 100000 items\n");
        
        //insert 100000 nodes into dsB
        startTime = System.nanoTime();
        System.out.println("DS_Brian is inserting 100000 nodes");
        for(int i = 0; i < 100000; i++) {
            dsB.insert(""+i, ""+i);
        }
        endTime = System.nanoTime();
        System.out.println("It took " + compare(startTime, endTime) 
            + "ns to process 100000 items");
    }

    /**
     * Test to compare contains operation between my data structure and an implemented DS
     * @param dsM
     * @param dsB
     */
    private static void trial3(BST dsM, DS_Brian dsB) {
        // TODO Auto-generated method stub
        System.out.println("\nTrial 3: Removing 1000 nodes\n");
        
        //insert 1000 nodes into dsM
        for(int i = 0; i < 1000; i++) {
            dsM.insert(""+i, ""+i);
        }
        long startTime = System.nanoTime();
        System.out.println("DS_My is checking if 10 nodes are present");
        for(int i = 0; i < 100; i++)
            dsM.contains(""+i);
        long endTime = System.nanoTime();
        System.out.println("It took " + compare(startTime, endTime) 
        + "ns to process 10 items\n");
        
        //insert 1000 nodes into dsB
        for(int i = 0; i < 1000; i++)
            dsB.insert(""+i, ""+i);
        startTime = System.nanoTime();
        System.out.println("DS_My is checking if 10 nodes are present");
        for(int i = 0; i < 100; i++)
            dsB.contains(""+i);
        endTime = System.nanoTime();
        System.out.println("It took " + compare(startTime, endTime) 
        + "ns to process 10 items\n");

    }

    /**
     * Test to compare remove operations between my data structure an implemented DS
     * @param dsM
     * @param dsB
     */
    private static void trial2(BST dsM, DS_Brian dsB) {
        // TODO Auto-generated method stub
        System.out.println("\nTrial 2: Removing 10 nodes\n");
        
        //insert 10 nodes into dsM
        for(int i = 0; i < 100; i++) {
            dsM.insert(""+i, ""+i);
        }
        long startTime = System.nanoTime();
        System.out.println("DS_My is removing 10 nodes");
        for(int i = 0; i < 100; i++)
            dsM.remove(""+i);
        long endTime = System.nanoTime();
        System.out.println("It took " + compare(startTime, endTime) 
            + "ns to process 10 items\n");
        
        //insert 100000 nodes into dsB
        System.out.println("DS_Brian is removing 10 nodes");
        for(int i = 0; i < 100; i++) {
            dsB.insert(""+i, ""+i);
        }
        startTime = System.nanoTime();
        for(int i = 0; i < 100; i++)
            dsB.remove(""+i);
        endTime = System.nanoTime();
        System.out.println("It took " + compare(startTime, endTime) 
            + "ns to process 10 items");
    }

    /**
     * Test to compare insert operations between my data structure and an implementedDS
     * @param dsM
     * @param dsB
     */
    private static void trial1(BST dsM, DS_Brian dsB) {
        // TODO Auto-generated method stub
        System.out.println("Trial 1: Inserting a large amount of items\n");
        
        //insert 100000 nodes into dsM
        long startTime = System.nanoTime();
        System.out.println("DS_My is inserting 10000 nodes");
        for(int i = 0; i < 10000; i++) {
            dsM.insert(""+i, ""+i);
        }
        long endTime = System.nanoTime();
        System.out.println("It took " + compare(startTime, endTime) 
            + "ns to process 10000 items\n");
        
        //insert 100000 nodes into dsB
        startTime = System.nanoTime();
        System.out.println("DS_Brian is inserting 10000 nodes");
        for(int i = 0; i < 10000; i++) {
            dsB.insert(""+i, ""+i);
        }
        endTime = System.nanoTime();
        System.out.println("It took " + compare(startTime, endTime) 
            + "ns to process 10000 items");
        
    }

    private static String compare(long startTime, long endTime) {
        // TODO Auto-generated method stub
        return "" + (endTime - startTime);
    }

}
