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
    
    private KeyValuePair root;
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
        if(!contains(key)) throw new KeyNotFoundException();
        
        KeyValuePair temp = lookup(key);
        
        if(temp.lChild == null) return null;        
        return temp.lChild.key;
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
        if(!contains(key)) throw new KeyNotFoundException();
        
        KeyValuePair temp = lookup(key);
        
        if(temp.rChild == null) return null;        
        return temp.rChild.key;
    }
    
    /**
     * helper method written to find and return a specific KeyValuePair
     * @author akshaybodla
     * @param key
     * @return
     */
    private KeyValuePair lookup(K key) {
        
        // start at root of tree and descend down into appropriate subtrees
        KeyValuePair curr = root;
        int compare = 0;//arbitrary default value
        
        while(curr.haveChildren()) { //while curr has at least 1 child to enter
            compare = curr.compareTo(key);
            if(curr.lChild != null && compare < 0) //if current.key is less than key
                curr = curr.lChild;
            
            else if(curr.rChild != null && compare > 0) //if current.key is greater than key
                curr = curr.rChild;
            
            else if(compare == 0) // when current.key and key are the same
                return curr;
        }
           
        //if key is not found
        return null;
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
        return 0;
        
//        return 1+max(height(root.lChild, height(root.rChild)));
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
        
        if(key == null) throw new IllegalNullKeyException("Key is null");
        if(contains(key)) throw new  DuplicateKeyException("Key exisits in tree");
        
        //if key is not null or not in the tree, insert!
        KeyValuePair kvp = new KeyValuePair(key, value);
        root = insert(kvp, root);
        
        size++;
        return;
    }
    
    private KeyValuePair insert(KeyValuePair kvp, KeyValuePair n) {
        if(n == null) {
            n = kvp;
            return n;
        }
        
        int compare = n.key.compareTo(kvp.key);
        if(compare > 0)
            n.lChild = insert(kvp, n.lChild);
        else if(compare < 0)
            n.rChild = insert(kvp, n.rChild);

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

    private KeyValuePair remove(K key, KeyValuePair n) {

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
            KeyValuePair replace = inOrderSuccessor(n.rChild);
            n.setKeyValue(replace.key, replace.val);
            n.rChild = remove(n.key, n.rChild);          
        }     	
    	return n;
    }
    
    /** 
     * Helper function to find minimum value node in subtree rooted at curr
     * @param curr
     * @return BSTNode representing the inOrderSuccessor
     */
    private KeyValuePair inOrderSuccessor(KeyValuePair curr) {
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
        
        KeyValuePair curr = root;
        int compare = curr.key.compareTo(key);
        
        while(curr != null) {
            compare = curr.key.compareTo(key);
            if(compare == 0) return true;
            else if(compare > 0)
                curr = curr.lChild;
            else if(compare < 0)
                curr = curr.rChild;
        }
        return false; //only happens when curr == null
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
    
    @SuppressWarnings("unused")
    private class KeyValuePair {
        private K key; //Key
        private V val; //Value
        
        private KeyValuePair lChild; //Left Child
        private KeyValuePair rChild; //Right Child
        
        private KeyValuePair() {
            key    = null;
            val    = null;
            lChild = null;
            rChild = null;
        }

        public void setKeyValue(K key2, V val2) {
			this.key = key2;
		    this.val = val2;			
		}

		private KeyValuePair(K key, V val) {
            this.key = key;
            this.val = val;
            lChild  = null;
            rChild  = null;
        }
        
        //compare this key to other key
        private int compareTo(K other) {
            int compare = this.key.compareTo(other);
            return compare;
        }
        
        private boolean haveChildren() {
            return lChild != null || rChild != null;
        }
    }
    
} // copyrighted material, students do not have permission to post on public sites


/*
 *     	KeyValuePair temp = lookup(key); //finds the node in this BST for removal
    	
    	//both are true if temp has that child
    	boolean hasLeft = temp.lChild != null;
    	boolean hasRight = temp.rChild != null;
    	
    	//Base case: if tree is empty, return node
        if(n == null) return n;
    	
    	//Case 2: temp has 1 child
    	if(!hasLeft) //has only right child, pass up right subtree (temp.rChild)
    		return temp.rChild;
    	if(!hasRight) //has only left child, pass up left subtree (temp.lChild)
    		return temp.rChild;
    	
    	//Case 3: temp has 2 children
    	//find in-order successor, replace this node with in-order node
    	//recursively delete in-order node
    	if(hasLeft && hasRight) {
    		KeyValuePair inOrder = inOrderSuccessor(temp);
    		n.setKeyValue(inOrder.key, inOrder.val);
    		n.rChild = remove(n.rChild, n.key);
    	}
 */

//  deppeler@cs.wisc.edu
