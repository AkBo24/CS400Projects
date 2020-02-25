import java.util.List;


/**
 * Implements a generic Red-Black tree extension of BST<K,V>.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS
 * DO NOT ADD ANY PACKAGE LEVEL OR PUBLIC ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove.
 * If you do not override this operation,
 * you may still have the method in your type, 
 * and have the method throw new UnsupportedOperationException.
 * See https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K,V>{

	private KeyValuePair root;
	private int size;
	
    // USE AND DO NOT EDIT THESE CONSTANTS
    public static final int RED = 0;
    public static final int BLACK = 1;


    // TODO: define a default no-arg constructor
    public RBT() {
    	root.setBlack();
    	size = 0;
    }

    /**
     * Returns the color of the node that contains the specified key.
     * Returns RBT.RED if the node is red, and RBT.BLACK if the node is black.
     * Returns -1 if the node is not found.
     * @param 
     * @return
     */
    public int colorOf(K key) {
    	KeyValuePair found = lookup(key);
        return found==null ? -1 : found.color;
    }

    /**
     * Returns true if the color of the root is black.
     * If root is null, returns BLACK.
     * @return true if root is black, else returns false (for red)
     */
    public boolean rootIsBlack() {
        // TODO implement this method for your RBT 
        return root.color == BLACK;
    }

    /**
     * Returns true if the node that contains this key is RED.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is RED,
     * else return false if key is found and the node's color is BLACK.
     */
    public boolean isRed(K key) {
        return root.color == RED;
    }

    /**
     * Returns true if the node that contains this key is BLACK.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is BLACK,
     * else return false if key is found and the node's color is RED.
     * @throws IllegalNullKeyException 
     * @throws KeyNotFoundException 
     */
    public boolean isBlack(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(key == null) throw new IllegalNullKeyException();	
    	
    	KeyValuePair temp = lookup(key);
    	if(temp == null) throw new KeyNotFoundException();
    	    	
        return temp.color == BLACK;
    }

    @Override
    public K getKeyAtRoot() {
        // TODO Auto-generated method stub
        return root.key;
    }

    @Override
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
    	if(key == null) throw new IllegalNullKeyException();	
    	
    	KeyValuePair temp = lookup(key);
    	if(temp == null) throw new KeyNotFoundException();    	
    	if(temp.lChild == null) return null;
    	
        return temp.lChild.key;
    }

    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
    	if(key == null) throw new IllegalNullKeyException();	
    	
    	KeyValuePair temp = lookup(key);
    	if(temp == null) throw new KeyNotFoundException();    	
    	if(temp.lChild == null) return null;
    	
        return temp.lChild.key;
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<K> getInOrderTraversal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<K> getPreOrderTraversal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<K> getPostOrderTraversal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<K> getLevelOrderTraversal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
    	throw new UnsupportedOperationException("Unsupported Exception");
    }

    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("Key is null");
        if(!contains(key)) throw new KeyNotFoundException();
        
        return lookup(key).val;
    }

    @Override
    public boolean contains(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
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

    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return size;
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub
        
    }


    // TODO: override the insert method so that it rebalances 
    //       according to red-black tree insert algorithm.


    // TODO [OPTIONAL] you may override print() to include
    //      color R-red or B-black.
    
    
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
    
    @SuppressWarnings("unused")
    private class KeyValuePair {
        private K key; //Key
        private V val; //Value
        private int color;
        
        private KeyValuePair lChild; //Left Child
        private KeyValuePair rChild; //Right Child
        
        private KeyValuePair() {
            key    = null;
            val    = null;
            lChild = null;
            rChild = null;
            color  = 1;
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
        
        private void setBlack() {
        	this.color = BLACK;
        }
        
        private void setRed() {
        	this.color = RED;
        }
    }
}
