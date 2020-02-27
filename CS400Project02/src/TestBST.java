import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@SuppressWarnings("rawtypes")
public class TestBST {

    protected STADT<Integer,String> bst;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
         bst = new BST<Integer,String>();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /** 
     * CASE 123 Insert three values in sorted order and then check 
     * the root, left, and right keys to see if insert worked 
     * correctly.
     */
    @Test
    void testBST_001_insert_sorted_order_simple() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("insert to right child of root does not work");
            
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("inserting 30 changed root");

            if (!bst.getKeyOfRightChildOf(20).equals(30)) 
                fail("inserting 30 as right child of 20");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            Assertions.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));
            Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }

    /** 
     * CASE 321 Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if insert 
     * worked in the other direction.
     */
    @Test
    void testBST_002_insert_reversed_sorted_order_simple() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyOfLeftChildOf(30).equals(20)) 
                fail("insert to left child of root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfLeftChildOf(20).equals(10)) 
                fail("inserting 10 as left child of 20");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }

    /** 
     * CASE 132 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if insert 
     * occurred correctly.
     */
    @Test
    void testBST_003_insert_smallest_largest_middle_order_simple() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(30, "30");
            if (!bst.getKeyOfRightChildOf(10).equals(30)) 
                fail("insert to right child of root does not work");
            Assert.assertEquals(bst.getKeyOfRightChildOf(10),Integer.valueOf(30));
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("inserting 20 changed root");

            if (!bst.getKeyOfLeftChildOf(30).equals(20)) 
                fail("inserting 20 as left child of 30");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }

    /** 
     * CASE 312 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if insert 
     * occurred correctly.
     */
    @Test
    void testBST_004_insert_largest_smallest_middle_order_simple() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyOfLeftChildOf(30).equals(10)) 
                fail("insert to left child of root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("inserting 20 as right child of 10");

            // the tree should have 30 at the root
            // and 10 as its left child and 20 as 10's right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }
    
    
    // TODO: Add your own tests
    
    // Add tests to make sure that bst grows as expected.
    // Does it maintain it's balance?
    // Does the height of the tree reflect it's actual height
    // Use the traversal orders to check.
    
    // Can you insert many and still "get" them back out?
    
    /**
     * Tests that the insert algorithm works correctly by inserting a key
     * smaller than the root and the a key thats larger than the root 
     */
    @Test
    void testBST_005_inserting_at_root_sorts_new_key_values_pairs() {
        try {
            
            bst.insert(20, "20");
            if(!bst.getKeyAtRoot().equals(20))
                fail("insert 20 does not work");
            
            bst.insert(10, "10");
            if(!bst.getKeyAtRoot().equals(20))
                fail("insert at root does not work");
            Assert.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

            bst.insert(30, "30");
            if(!bst.getKeyAtRoot().equals(20))
                fail("insert 30 does not work");
            Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage() );
        }
    }
    
    /**
     * Tests that the remove algorithm works correctly by inserting three
     * nodes and removing them all
     * 
     * Tests case of each node with 1 child
     */
    @Test
    void testBST_006_removing_node_with_no_children() {
        try {
            bst.insert(20, "20");
            bst.insert(10, "10");
            bst.insert(30, "30");

            bst.remove(10);
            Assert.assertEquals(bst.getKeyOfLeftChildOf(20), null);
            
            bst.remove(30);
            Assert.assertEquals(bst.getKeyOfRightChildOf(20), null);
            
        }
        catch(Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage());
        }
    }
    
    /**
     * Tests remove for a node with one child works properly by first creating
     * a bst with two nodes and removing the root
     * 
     * Then insert another node greater than the root and remove the current root
     * 
     * The only node remaining should be the large node (it should also be at root)
     */
    @Test
    void testBST_007_removing_node_with_one_child() {
        try {
            bst.insert(20, "20");
            bst.insert(10, "10");
            
            bst.remove(20);
            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            
            bst.insert(30, "30");
            bst.remove(10);
            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            
        }
        catch(Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage());
        }       
        
    }
    
    /**
     * Insert 20,10,30 into bst and remove 20 which is root
     * Check that 30 (the in, order successor) is the key and that
     * 20 is removed from the tree
     */
    @Test
    void testBST_008_removing_node_with_two_children() {
        try {
            bst.insert(20, "20");
            bst.insert(10, "10");
            bst.insert(30, "30");

            bst.remove(20);
            if(bst.getKeyAtRoot().equals(20))
                fail("removing 20 (which is at root) does not work");
            
            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
        }
        catch(Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage());
        }  
    }
    
    /**
     * Inserting several nodes and deleting a node with to children
     * 
     * This tests that the node is removed and is replaced with the inorder successor
     */
    @Test
    void testBST_009_removing_node_within_tree() {
        try {
            bst.insert(20, "20");
            bst.insert(30, "30");
            bst.insert(25, "25");
            bst.insert(67, "67");
            bst.insert(45, "45");
            
            bst.remove(30);
            
            if(bst.getKeyOfRightChildOf(20).equals(30))
                fail("removing 30 from tree failed");
            
            Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(45));
                        
        }
        catch(Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage());
        }
    }
    
    /**
     * Tests that insert and get operations of BST work as expected
     * 
     */
    @Test
    void testBST_009_get_value_of_node_in_large_tree() {
        try {
            for(int i = 0; i < 100; i++)
                bst.insert(i, ""+i);
            
            if(bst.numKeys() != 100)
                fail("size of bst is not correct");
            
            Assert.assertEquals(bst.get(86), "86");
        }
        catch(Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception 001: "+e.getMessage());
        }
    }
    
    /**
     * 
     */
    @Test
    void testBST_010_tree_traversal_is_corretct() {
        try {
            List<Integer> list = new ArrayList<Integer>();
            
            for(int i = 0; i < 20; i++) {
                list.add(i);
                bst.insert(i, ""+i);
            }
            
            List<Integer> bstList = bst.getInOrderTraversal();
            
            Assert.assertEquals(list, bstList);
        }
        catch (Exception e) {
            
        }
        
        
    }
}
