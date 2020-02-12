//TODO: Add file header here




//TODO: Add class header here
public class DS_My implements DataStructureADT< String, String >{

    private BSTNode root;
    private int size;
    
    public DS_My() {
       root   = null;
       size   =    0;
    }
    
    @Override
    public void insert(String key, String value) {
        
        //ensures that value is a valid input
        if(key == null)
            throw new IllegalArgumentException("null key");
        if(contains(key))
            throw new RuntimeException("duplicate key");
        
        root = insert(root, key, value);
        size++;
    }

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

    @Override
    public boolean remove(String key) {
        // TODO Auto-generated method stub

        if(key == null)
            throw new IllegalArgumentException("null key");
        
        if(!contains(key))
            return false;
        
        //algorithm to find the BSTNode to be removed 
        //using the given key
        BSTNode curr = root;
        while(curr!=null && !curr.key.equals(key)) {
            if(curr.lChild != null && curr.key.compareTo(key) > 0)
                curr = curr.lChild;
            else
                curr = curr.rChild;
        }
        
        //Case 1: curr is root
        if(curr == root) {
            size--;
            root = null;
            return true;
        }
        
        //Case 2: curr is not root but has no children
        //make this null
        if(!curr.haveChild()) {
            size--;
            curr = null;
            return true;
        }
        
        //Case 3: curr has one child
        //copy its child's key-value pair into current & delete that child
        if(curr.rChild == null) {
            BSTNode replace = curr.lChild;
            remove(curr.rChild.key);
            curr.setKeyValue(replace.key, replace.value);
            size--;
            return true;
        }
        
        //Case 4: curr has 2 children
        //find the In-Order Successor
        //store its key-value pair, delete it, and copy that into current
        if(curr.haveChild()) {
            BSTNode replace = inOrderSuccessor(curr.lChild);
            remove(replace.key);
            curr.setKeyValue(replace.key, replace.value);
            size--;
            return true;
        }
        
        //if the remove fails,
        return false;
    }

//    private BSTNode remove(BSTNode n, String key) {
//        
//        //Case 1: n is the target node to remove, 
//        //returns null to "break" the pointer
//        if(n.key.equals(key))
//            return null;
//        
//        //Case 2: n has one child (either left or right child)
//        if(n.rChild == null)
//            return remove(n.lChild, key);
//        else if(n.lChild == null)
//            return remove(n.rChild, key);
//        
//        //Case 3: n has two children
//        //find the in-order successor to copy into the current node
//        BSTNode replace = inOrderSuccessor(n.lChild);
//        String rKey = replace.key,
//               rVal = replace.value;
//        n.lChild = remove(n.lChild, rKey);
//        n.setKeyValue(rKey, rVal);
//        return n;    
//    }
    
 // Helper function to find minimum value node in subtree rooted at curr
    public static BSTNode inOrderSuccessor(BSTNode curr)
    {
        while(curr.lChild != null) {
            curr = curr.lChild;
        }
        return curr;
    }

    

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
        
        return curr.key;
    }
    

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
    
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

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
      
      public int compareTo(BSTNode other) {
          return this.getKey().compareTo(other.getKey());
      }
      
      public String getKey() {
          return key;
      }
      
      public String getValue() {
          return value;
      }
      
      public BSTNode getLeft() {
          return lChild;
      }
      
      public BSTNode getRight() {
          return rChild;
      }
      
      public void setLeft(BSTNode child) {
          this.lChild = child;
      }
      
      public void setRight(BSTNode child) {
          this.rChild = child;
      }
      
      public void setKeyValue(String k, String v) {
          key   = k;
          value = v;
      }
      
      //true if has 2 children, false if not
      public boolean haveChild() {
          return lChild != null && rChild != null; 
      }
      
      public boolean equals(BSTNode other) {
          boolean boolKey = this.getKey().equals(other.getKey());
          boolean boolVal = this.getValue().equals(other.getValue());
          return  boolKey && boolVal;
      }
      
  }
}
