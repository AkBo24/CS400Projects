// TODO: Add file header here




// TODO: Add class header here
public class DS_My implements DataStructureADT< String, String > {


    // Private Fields of the class
    // TODO create field(s) here to store data pairs
    private BSTNode root;
    private int size;
    
    public DS_My() {
       root   = null;
       size   =    0;
    }

    @Override
    public void insert(String key, String value) {
        root = insert(root, key, value);
        size++;

    }

    private BSTNode insert(BSTNode n, String k, String v) {

        if(k == null || v == null) 
            throw new NullPointerException("Either Key or Value is null");
        
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
        
        if(key == null)
            throw new NullPointerException("Key is null");
        if(!contains(key))
            return false;
        
        root = remove(root,key);

        //if the remove is successful it will remove a non-null object
        //else it will return null
        size--;        
        return root == null;
    }
    
    private BSTNode remove(BSTNode n, String k) {
        if(n == null) return null;
        BSTNode curr   = n;
        
        //case 1: node to remove has no children
        if(!n.haveChild()) {
            return null;
        }
        else if(n.haveChild()) { // has at 2 children
            //find in-order successor
            //store the key-value pair in a temporary node
            //replace the current node's key-value pair with the temporary node
            //delete the successor
            BSTNode succ = findSuccessor(curr);
            n.setKeyValue(succ.getKey(), succ.getValue());
            remove(curr.rChild, succ.getKey());
        }
        else { // has 1 child
            if(curr.rChild == null) //has only left child
                return n.lChild;
            else
                return n.rChild;
        }
        
        return n;
    }
    
    private BSTNode findSuccessor(BSTNode n) {
        while(n.lChild != null) {
            n = n.lChild;
        }
        return n;
    }
    
    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public boolean contains(String key) {
        if(root == null) return false;
        
        BSTNode curr = root;
        while(!curr.getKey().equals(key)) {
            if(curr.rChild != null && curr.getKey().compareTo(key) < 0)
                curr = curr.rChild;
            else if(curr.lChild != null && curr.getKey().compareTo(key) > 0)
                curr = curr.lChild;
            else //only possible if current has reached a leaf which isnt that key
                return false; //not found in the tree
        }
        
        return true; //was found and exited while loop
    }

    @Override
    public int size() {
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

//old remove algo
//BSTNode parent = null;
//BSTNode curr   = n;
//
//while(curr != null && !curr.getKey().equals(k)) {
//    parent = curr;
//    if(n.getKey().compareTo(k) < 0)
//        curr = curr.lChild;
//    else
//        curr = curr.rChild;
//}
//
//// return if key is not found in the tree
//if (curr == null)
//    return root;
//
//// Case 1: node to be deleted has no children i.e. it is a leaf node
//if(!curr.haveChild()) {
//    if(curr.equals(root))
//        root = null;
//    else {
//        if(parent.lChild == null)
//            parent.lChild = null;
//        else
//            parent.rChild = null;
//    }
//}
//
//// Case 2: node to be deleted has two children
//else if (curr.haveChild()) {
//    /*
//     * 1) find the current node's in-order successor
//     * 2) get the key-value pair of the successor & delete the successor
//     * 3) copy the key-value pair to the current node
//     */
//    BSTNode succ = findSuccessor(curr);
//    
//    String succKey = succ.getKey();
//    String succVal = succ.getValue();
//    
//    remove(succ, succKey);
//    curr.setKeyValue(succKey, succVal);
//}
//
//// Case 3: node to be deleted has only one child
//else {
//    
//    BSTNode child = (curr.lChild != null)? curr.lChild:curr.rChild;
//    
//    // if node to be deleted is not a root node, then set its parent
//    // to its child
//    if(curr.equals(root))
//        root = child;
//    else if(curr.equals(parent.lChild))
//        parent.lChild = child;
//    else //(curr.equals(parent.rChild))
//        parent.rChild = child;
//}
//return root;

//old insert algo
//if(n == null)
//return new BSTNode(k,v, null, null);
//
//if(n.getKey().compareTo(k) < 0) 
//lChild = insert(lChild,k,v);
//
//else if(n.getKey().compareTo(k) > 0) 
//rChild = insert(rChild,k,v);
//else
//throw new RuntimeException("no duplicates!");
//
//return n; 
 