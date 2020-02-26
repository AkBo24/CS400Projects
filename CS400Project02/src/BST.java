import java.util.ArrayList;
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
public class BST<K extends Comparable<K>, V> implements STADT<K,V> {
    
    private BSTNode root;
    int size;
   
    public BST() {
        root  = null;
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
        if(root == null) return 0;
        if(!root.haveChildren()) return 1;
        
        return 1 + max( height(root.lChild), height(root.rChild) );
    }
    
    private int height(BSTNode child) {
        // TODO Auto-generated method stub
        
        //We have reached an end of a path
        if(child == null )
            return 0;
        
        return 1 + max(height(child.lChild), height(child.rChild));
    }

    private int max(int x, int y) {
        return x>y ? x : y;
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
    	List<K> list = new ArrayList<K>();
    	if(root == null || size == 0) return list;
        
        printInorder(root, list);
        return list;
    }
    
    private void printInorder(BSTNode n, List<K> list) { 
        if(n == null)
            return;
        
        printInorder(n.lChild, list);
        list.add(n.key);
        printInorder(n.rChild, list);
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
        List<K> list = new ArrayList<K>();
        if(root == null || size == 0) return list;
        
        printPreOrderTraversal(root, list);
        return list;
    }

    private void printPreOrderTraversal(BSTNode n, List<K> list) {
        // TODO Auto-generated method stub
        if(n == null) return;
        
        list.add(n.key);
        printPreOrderTraversal(n.lChild, list);
        printPreOrderTraversal(n.rChild, list);
        
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
        List<K> list = new ArrayList<K>();
        if(root == null || size == 0) return list;
        
        printPostOrderTraversal(root, list);
        return list;
    }

    private void printPostOrderTraversal(BSTNode n, List<K> list) {
        if(n == null) return;
        
        printPostOrderTraversal(n.lChild, list);
        printPostOrderTraversal(n.rChild, list);
        
        list.add(n.key);
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
        List<K> list = new ArrayList<K>();
        if(root == null || size == 0) return list;
        
        list.add(root.key);
        
        printLevelOrderTraversal(root, list);
        return list;
    }
    
    private void printLevelOrderTraversal(BSTNode n, List<K> list) {
        if(n == null)
            return;
        
        if(n.lChild != null)
            list.add(n.lChild.key);
        if(n.rChild != null)
            list.add(n.rChild.key);
        
        printLevelOrderTraversal(n.lChild, list);
        printLevelOrderTraversal(n.rChild, list);
        
    }
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {  
      
        if(key == null) throw new IllegalNullKeyException("Key is null");
        if(contains(key)) throw new  DuplicateKeyException("Key exisits in tree");
        
        //if key is not null or not in the tree, insert!
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
        if(key == null) throw new IllegalNullKeyException("Key is null");
        
        if(lookup(key) == null) return true;
        
        root = remove(key, root);
        
        size--;
        return lookup(key) != null;
    }

    private BSTNode remove(K key, BSTNode n) {

        //Base case: if tree is empty, return node
        if(n == null) return n;
        
        //if we are not at the right node traverse down the tree to find it
        if(key.compareTo(n.key) < 0) //traverse left subtree
            n.lChild = remove(key, n.lChild);
        else if(key.compareTo(n.key) > 0) //traverse right subtree
            n.rChild = remove(key, n.rChild);
        
        //if key.equals(n.key) is true
        else {
            //case 1: if n has 1 child or no child
            if (n.lChild == null) 
                return n.rChild; 
            else if (root.rChild == null) 
                return root.lChild; 
            
            //case 3: n has two children
            BSTNode replace = inOrderSuccessor(n.rChild);
            n.setKeyValuePair(replace.key, replace.val);
            n.rChild = remove(n.key, n.rChild);          
        }     	
    	return n;
    }
    
    /** 
     * Helper function to find minimum value node in subtree rooted at curr
     * @param curr
     * @return BSTNode representing the inOrderSuccessor
     */
    private BSTNode inOrderSuccessor(BSTNode curr) {
        while(curr.lChild != null) {
            curr = curr.lChild;
        }
        return curr;
    }
    
    /**
     * Returns the value associated with the specified key.
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null) throw new IllegalNullKeyException("Key is null");
        if(!contains(key)) throw new KeyNotFoundException();
        
        return lookup(key).val;
    }

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    public boolean contains(K key) throws IllegalNullKeyException {
        if(key  == null) throw new IllegalNullKeyException("Key is null");
        if(root == null) return false;
        
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
        return size;
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
    
    /**
     * helper method written to find and return a specific KeyValuePair
     * @author akshaybodla
     * @param key
     * @return
     */
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
            return this.key.compareTo(other.key);
        }
        
        private boolean haveChildren() {
            return lChild != null || rChild != null;
        }
            
    }
    
} // copyrighted material, students do not have permission to post on public sites

//  deppeler@cs.wisc.edu
