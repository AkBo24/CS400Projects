//TODO: Add file header here
/**
 * DataStructureADTTest.java created by akshaybodla on MacBook Pro in CS400Project01
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
 * My implementation of a BST
 * @author akshaybodla
 *
 */
//TODO: Add class header here
public class BST implements DataStructureADT< String, String >{

    private BSTNode root;
    private int size;
    
    public BST() {
       root   = null;
       size   =    0;
    }
    
    @Override
    /**
     * Inserts a node into the BST
     * @param Key
     * @param Value
     */
    public void insert(String key, String value) {
        
        //ensures that value is a valid input
        if(key == null)
            throw new IllegalArgumentException("null key");
        if(contains(key))
            throw new RuntimeException("duplicate key");
        
        root = insert(root, key, value);
        size++;
    }

    /**
     * Helper method for insert(key, value)
     * @param n
     * @param k
     * @param v
     * @return BSTNode to be inserted
     */
    private BSTNode insert(BSTNode n, String k, String v) {
        
        //Base Case: when we reach a null leaf
        if(n == null) return new BSTNode(k,v);
        
        if(k.compareTo(n.getKey()) < 0)
            n.lChild = insert(n.lChild, k, v);
        else if(k.compareTo(n.getKey()) > 0)
            n.rChild = insert(n.rChild, k, v);
        else
            throw new RuntimeException("no duplicates!");

        return n;
    }

    /**
     * Remove operation of BST
     * @param the key of the node to be removed
     * @return whether the node is removed 
     */
    @Override
    public boolean remove(String key) {
        
      if(key == null)
          throw new IllegalArgumentException("null key");
      
      //Quickly returns false if the key is not present
      if(!contains(key))
          return false;
        
        root = remove(root, key);
        size--;
        return !this.contains(key);
    }

    /**
     * Helper method to remove(Key)
     * @param n
     * @param key
     * @return BSTNode of the key to be removed
     */
    private BSTNode remove(BSTNode n, String key) {
        
        //Base case: if tree is empty, return node
        if(n == null) return n;
        
        //if we are not at the right node traverse down the tree to find it
        if(key.compareTo(n.key) < 0) //traverse left subtree
            n.lChild = remove(n.lChild, key);
        else if(key.compareTo(n.key) > 0) //traverse right subtree
            n.rChild = remove(n.rChild, key);
        
        //if key.equals(n.key) is true
        else {
            //case 1: if n has 1 child or no child
            if (n.lChild == null) 
                return n.rChild; 
            else if (root.rChild == null) 
                return root.lChild; 
            
            //case 3: n has two children
            BSTNode replace = inOrderSuccessor(n.rChild);
            n.setKeyValue(replace.key, replace.value);
            n.rChild = remove(n.rChild, n.key);
            
        }
        return n; //the node that is removed
    }
    
    /** 
     * Helper function to find minimum value node in subtree rooted at curr
     * @param curr
     * @return BSTNode representing the inOrderSuccessor
     */
    public static BSTNode inOrderSuccessor(BSTNode curr)
    {
        while(curr.lChild != null) {
            curr = curr.lChild;
        }
        return curr;
    }

    
    /**
     * Finds the value of the associated key 
     * @param key
     * @return the value of the associated key
     */
    @Override
    public String get(String key) {
        // TODO Auto-generated method stub
        
        if(key == null)
            throw new IllegalArgumentException("null key");
        
        BSTNode curr = root;
        while(curr!=null && !curr.key.equals(key)) {
            if(curr.lChild != null && curr.key.compareTo(key) > 0)
                curr = curr.lChild;
            else
                curr = curr.rChild;
        }
        
        if(curr == null)
            return null;
        
        return curr.value;
    }
    
    /**
     * Finds whether the associated node exists in the BST
     * @param key
     * @return if the associated node exists in the BST
     */
    @Override
    public boolean contains(String key) {
        // TODO Auto-generated method stub
        if(key == null)
            return false;
        
        BSTNode curr = root;
        while(curr!=null && !curr.key.equals(key)) {
            if(curr.lChild != null && curr.key.compareTo(key) > 0)
                curr = curr.lChild;
            else
                curr = curr.rChild;
        }
        
        return curr != null;
    }
    
    /**
     * @return the size of the BST
     */
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }
    
      /**
       * A node class for the BST
       * @author akshaybodla
       *
       */
      // TODO may wish to define an inner class 
      // for storing key and value as a pair
      // such a class and its members should be "private"
      class BSTNode {
          private String  key;
          private String  value;
          private BSTNode lChild;
          private BSTNode rChild;
          
          public BSTNode(String key, String value) {
              this.key    = key;
              this.value  = value;
              this.lChild = null;
              this.rChild = null;
          }
          
          /**
           * Compares two BSTNodes
           * @param other
           * @return if other is greater or less than this
           */
          public int compareTo(BSTNode other) {
              return this.getKey().compareTo(other.getKey());
          }
          
          /**
           *  
           * @return the key of this node
           */
          public String getKey() {
              return key;
          }
          
          /**
           * 
           * @return the value of this node
           */
          public String getValue() {
              return value;
          }
          
          /**
           * Reset the key and value of this node
           * @param k
           * @param v
           */
          public void setKeyValue(String k, String v) {
              key   = k;
              value = v;
          }
          
          /**
           * find if this has any children
           * @return true if has 2 children, false if not
           */
          public boolean haveChild() {
              return lChild != null && rChild != null; 
          }
          
          /**
           * @param other
           * @return if this and other have the same value  & key
           */
          public boolean equals(BSTNode other) {
              boolean boolKey = this.getKey().equals(other.getKey());
              boolean boolVal = this.getValue().equals(other.getValue());
              return  boolKey && boolVal;
          }
          
  }
}


//@Override
//public boolean remove(String key) {
//    // TODO Auto-generated method stub
//
//    if(key == null)
//        throw new IllegalArgumentException("null key");
//    
//    if(!contains(key))
//        return false;
//    
//    //algorithm to find the BSTNode to be removed 
//    //using the given key
//    BSTNode curr = root;
//    while(curr!=null && !curr.key.equals(key)) {
//        if(curr.lChild != null && curr.key.compareTo(key) > 0)
//            curr = curr.lChild;
//        else
//            curr = curr.rChild;
//    }
//    
////    //Case 1: curr is root
////    if(curr == root) {
////        size--;
////        root = null;
////        return true;
////    }
//    
//    //Case 2: curr is not root but has no children
//    //make this null
//    if(!curr.haveChild()) {
//        String d = curr.key;
//        System.out.println(d);
//        size--;
//        System.out.println("ddd" + this.contains(d));
//        curr = null;
//        System.out.println("eee" + this.contains(d));
//        return true;
//    }
//    
//    //Case 3: curr has one child
//    //copy its child's key-value pair into current & delete that child
//    if(curr.rChild == null) {
//        BSTNode replace = curr.lChild;
//        remove(curr.rChild.key);
//        curr.setKeyValue(replace.key, replace.value);
//        size--;
//        return true;
//    }
//    
//    //Case 4: curr has 2 children
//    //find the In-Order Successor
//    //store its key-value pair, delete it, and copy that into current
//    if(curr.haveChild()) {
//        BSTNode replace = inOrderSuccessor(curr.lChild);
//        remove(replace.key);
//        curr.setKeyValue(replace.key, replace.value);
//        size--;
//        return true;
//    }
//    
//    //if the remove fails,
//    return false;
//}
