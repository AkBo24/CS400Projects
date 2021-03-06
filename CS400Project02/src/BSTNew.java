import java.util.List;

// DO IMPLEMENT A BINARY SEARCH TREE IN THIS CLASS

/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface 
 * that are required solely to support gray-box testing 
 * of the internal tree structure.  They must be implemented
 * as described to pass all grading tests.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
public class BSTNew<K extends Comparable<K>, V> implements STADT<K,V> {
    
    BSTNode root;
    int size;
    
    public BSTNew() {
        size = 0;
    }
    
    /**
     * Returns the key that is in the root node of this ST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    public K getKeyAtRoot() {
        return root.key;
    }
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null) throw new IllegalNullKeyException();
        if(!this.contains(key)) throw new KeyNotFoundException();
        
        BSTNode target = lookup(key);
        if(target == null || target.lChild == null ) return null;
        
        return target.lChild.key;        
    }
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null) throw new IllegalNullKeyException();
        if(!this.contains(key)) throw new KeyNotFoundException();
        
        BSTNode target = lookup(key);
        if(target == null || target.rChild == null ) return null;
        
        return target.rChild.key; 
    }
    

    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    public int getHeight() {
         return 0;
    }
    
    
    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    public List<K> getInOrderTraversal() {
        return null;
    }
    
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    public List<K> getPreOrderTraversal() {
        return null;
    }

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    public List<K> getPostOrderTraversal() {
        return null;
    }

    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
    public List<K> getLevelOrderTraversal() {
        return null;
    }
    
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        if(key == null) throw new IllegalNullKeyException();
        if(this.contains(key)) throw new DuplicateKeyException();
        
        BSTNode kvp = new BSTNode(key, value);
        root = insert(root, kvp);
        
        size++;
        return;
    }
    
    private BSTNode insert(BSTNode n, BSTNode kvp) {
        
        if(n == null) {
            n = new BSTNode();
            n.setKeyValuePair(kvp.key, kvp.val);
            return n;
        }
        
        int compare = kvp.compareTo(n);
        System.out.println("kvp: " + kvp.key);
        System.out.println("n  : " + n.key);
        System.out.println("compare: " + compare);
        System.out.println();
        
        if(compare > 0)
            n.rChild = insert(n.rChild, kvp);
        else
            n.lChild = insert(n.lChild, kvp);
        
        return n;
    }

    /** 
     * If key is found, remove the key,value pair from the data structure 
     * and decrease num keys, and return true.
     * If key is not found, do not decrease the number of keys in the data structure, return false.
     * If key is null, throw IllegalNullKeyException
     */
    public boolean remove(K key) throws IllegalNullKeyException {
        return false;
    }

    /**
     * Returns the value associated with the specified key.
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        return null;
    }

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    public boolean contains(K key) throws IllegalNullKeyException {
        
        BSTNode curr = root;
        int compare;
        
        while(curr != null) {
            compare = curr.key.compareTo(key);
            if(compare > 0)
                curr = curr.lChild;
            else if(compare < 0)
                curr = curr.rChild;
            else if(compare == 0)
                return true;
        }
        
        return false; //current reached a null leaf child, key is not in tree
    }

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    public int numKeys() {
        return 0;
    }
    
    
    /**
     * Print the tree. 
     *
     * For our testing purposes of your print method: 
     * all keys that we insert in the tree will have 
     * a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display a binary search in any of a variety of ways, 
     * but we must see a tree that we can identify left and right children 
     * of each node
     *
     * For example: 
     
           30
           /\
          /  \
         20  40
         /   /\
        /   /  \
       10  35  50 

       Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
       
       Or, you can display a tree of this kind.

       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
       
       Or, you can come up with your own orientation pattern, like this.

       10                 
               20
                       30
       35                
               40
       50                  

       The connecting lines are not required if we can interpret your tree.

     */
    
    public void print() {
        System.out.println("not yet implemented");
    }
    
    private BSTNode lookup(K key) {
        BSTNode curr = root;
        
        int compare;
        
        while(curr != null) {
            compare = curr.key.compareTo(key);

            if(compare > 0)
                curr = curr.lChild;
            else if(compare < 0)
                curr = curr.rChild;
            else if(compare == 0)
                return curr;
        }
        
        return null; //current reached a null leaf child, key is not in tree
    }
    
    private class BSTNode {
        
        K key;
        V val;
        BSTNode lChild;
        BSTNode rChild;
        
        private BSTNode() {
            key = null;
            val = null;
            
            lChild = null;
            rChild = null;
        }
        
        private BSTNode(K key, V val) {
            this.key = key;
            this.val = null;
            
            lChild = null;
            rChild = null;
        }
        
        private void setKeyValuePair(K key, V val) {
            this.key = key;
            this.val = val;
        }
        
        private int compareTo(BSTNode other) {
            
            
//            System.out.println("3333: " + this.key.compareTo(other.key));
//            System.out.println(this.key);
//            System.out.println(other.key);
//            System.out.println("2".compareTo("12"));
            
            return this.key.compareTo(other.key);
        }
        
    }
    
} // copyrighted material, students do not have permission to post on public sites




//  deppeler@cs.wisc.edu