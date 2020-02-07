// TODO: Add file header here




// TODO: Add class header here
public class DS_My implements DataStructureADT< String, String > {


    // Private Fields of the class
    // TODO create field(s) here to store data pairs
    private BSTNode root;
    private BSTNode lChild;
    private BSTNode rChild;
    private int size;
    
    public DS_My() {
       root   = null;
       lChild = null;
       rChild = null;
       size   =    0;
    }

    @Override
    public void insert(String key, String value) {
        root = insert(root, key, value);
        size++;

    }

    private BSTNode insert(BSTNode n, String k, String v) {
        if(n == null) {
            return new BSTNode(k,v);
        }
        
        if(n.getKey().compareTo(k) < 0) 
            lChild = insert(lChild,k,v);
        
        if(n.getKey().compareTo(k) > 0) 
            rChild = insert(rChild,k,v);
        else
            throw new RuntimeException("no duplicates!");
        
        return n;
    }
    
    @Override
    public boolean remove(String key) {
        return false;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }
    
    
    // TODO may wish to define an inner class 
    // for storing key and value as a pair
    // such a class and its members should be "private"
    class BSTNode {
        private String key;
        private String value;
        
        public BSTNode(String key, String value) {
            this.key   = key;
            this.value = value;
        }
        
        public int compareTo(BSTNode other) {
            return this.getKey().compareTo(other.getKey());
        }
        
        public String getKey() {
            return key;
        }
    }
                                                          

}                            
    
