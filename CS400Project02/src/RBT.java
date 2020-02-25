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
    	if(temp.rChild == null) return null;
    	
        return temp.rChild.key;
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<K> getInOrderTraversal() {
    	printInorder(root);
        return null;
    }
    
    private void printInorder(KeyValuePair node) 
    { 
        if (node == null) 
            return; 
  
        /* first recur on left child */
        printInorder(node.lChild); 
  
        /* then print the data of node */
        System.out.print(node.key + " "); 
  
        /* now recur on right child */
        printInorder(node.rChild);
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
        if(key == null) throw new IllegalNullKeyException("Key is null");
        if(contains(key)) throw new  DuplicateKeyException("Key exisits in tree");
        
        //if key is not null or not in the tree, insert!
        KeyValuePair kvp = new KeyValuePair(key, value);
        
        root = insert(kvp, root);
        
        
        
        size++;
        return;
    }

    private void triNodeRestructure(KeyValuePair grandPar) {
		// TODO Auto-generated method stub
    	
    	KeyValuePair parent, kid;
    	KeyValuePair temp = new KeyValuePair();
    	temp.setKeyValue(grandPar.key, grandPar.val);
    	
    	//Tri-Node Restructure if Parent's siblings is null
    	if(!grandPar.hasRight() && grandPar.lChild.haveChildren()) {
    		parent = grandPar.lChild;
    		
    		//Case 1 of Tri-node restructure
    		if(!parent.hasRight() && parent.hasLeft()) {
    			kid = parent.lChild;
    			
        		//make sure that there is no red violation before fixing
        		if(kid.color == BLACK) return;
        		
        		//Replace grand parent with parent's Key Value pair
        		grandPar.setKeyValue(parent.key, parent.val);
        		grandPar.setBlack(); //make sure grandParent (now parent) is black
        		
        		//set grandParent (now parent)'s children to fix the Red Violation
        		kid.setRed();
        		temp.setRed();
        		grandPar.lChild = kid;
        		grandPar.rChild = temp;
    			
    		}
    		//Case 2 of Tri-node restructure
    		else if(!parent.hasLeft() && parent.hasRight()) {
    			kid = parent.rChild;
    			
        		//make sure that there is no red violation before fixing
        		if(kid.color == BLACK) return;
        		
        		//Replace grand parent with kid's Key Value pair
        		grandPar.setKeyValue(kid.key, kid.val);
        		grandPar.setBlack(); //make sure grandParent (now kid) is black
        		
        		
        		//set grandParent (now kid)'s children to fix the Red Violation
        		parent.setRed();
        		temp.setRed();
        		grandPar.lChild = parent;
        		grandPar.rChild = temp;
        		parent.rChild = null;
        		
    		}
    	}
    	// Parent's left sibling is null
    	else if(!grandPar.hasLeft() && grandPar.rChild.haveChildren()) {
    		parent = grandPar.rChild;
    		
    		//Case 3 of Tri-node Restructure:
    		if(!parent.hasLeft() && parent.hasRight()) {
    			kid = parent.rChild;
        		
        		//make sure that there is no red violation before fixing
        		if(kid.color == BLACK) return;
        		
        		//Replace grandParent with parent's Key Value pair
        		grandPar.setKeyValue(parent.key, parent.val);
        		grandPar.setBlack(); //make sure grandParent (now parent) is black
        		
        		//set grandParent (now parent)'s children to fix the Red Violation
        		temp.setRed();
        		kid.setRed();
        		grandPar.lChild = temp;
        		grandPar.rChild = kid;
    		}
    		
    		//Case 4 of Tri-Node Restructure
    		else if(!parent.hasRight() && parent.hasLeft()) {
    			kid = parent.lChild;
    			
        		//make sure that there is no red violation before fixing
        		if(kid.color == BLACK) return;
        		
        		//Replace grandParent with kid Key Value pair
        		grandPar.setKeyValue(kid.key, kid.val);
        		grandPar.setBlack(); //make sure grandParent (now kid) is black
        		
        		//set grandParent (now Kid)'s children to fix teh Red Violation
        		temp.setRed();
        		parent.setRed();
        		grandPar.lChild = temp;
        		grandPar.rChild = parent;
        		parent.lChild = null;
        		
    		} //end else if
    	} // end outer else if
	}
    
	private KeyValuePair insert(KeyValuePair kvp, KeyValuePair n) {	
		
		if(root == null) {
			root = kvp;
			root.setBlack();
			return root;
		}
        if(n == null) {
            n = kvp;            
            return n;
        }
        
        int compare = n.key.compareTo(kvp.key);
        if(compare > 0)
            n.lChild = insert(kvp, n.lChild);
        else if(compare < 0) {
            n.rChild = insert(kvp, n.rChild);
            
//            System.out.println("n: " + n.key);
//            System.out.println("rChild key:" + n.rChild.key);
            
        }
        triNodeRestructure(n);
        return n;
    }
	

    
    /**
     * Fixes Tri Node Restructure if p's sibling is null
     * @param kvp
     */
    public void childIsRedFix(KeyValuePair grandPar) {
    	
    	KeyValuePair parent, kid, temp;
    	// check if left child is null, then fix if right child is red
    	if(!grandPar.hasRight() && grandPar.hasRight()) {
    		parent = grandPar.lChild;
    		boolean hasRight = parent.hasRight();
    		boolean hasLeft  = parent.hasLeft();
    		
    		//Case 1: if the Red Violation is in grand parent's left child
    		if(!hasLeft && hasRight) {
    			kid  = parent.rChild;
    			temp = grandPar;
    			
    			if(kid.color == BLACK) return; //if kid's color is black there is no violation
    			
    			//Case 2 in RBT Notes
    			//grandPar KeyValuePair is replaced with Kid
    			//to fix problem, set grandPar (kid's) lChild to parent
    			//and rchild to grandPar's KeyValuePair (temp)
    			grandPar.setRed();
    			grandPar.setKeyValue(kid.key, kid.val);
    			grandPar.lChild = parent;
    			grandPar.rChild = temp;
    			grandPar.setBlack(); //new grandparent becomes black
    		}
    		
    		//Case 2: if the Red Violation is in grand parent's right child
    		else if(!hasRight && hasLeft) {
    			kid = parent.lChild;
    			temp = grandPar;    			
    			
    			if(kid.color == BLACK) return; //no red violation
    			
    			//Case 1 in RBT notes
    			//grandPar KeyValuePair is replaced with parent
    			//to fix problem, set grandPar (parent's) lChild to kid
    			//and rChild to grandParent
    			grandPar.setRed();
    			grandPar.setKeyValue(parent.key, parent.val);
    			grandPar.lChild = kid;
    			grandPar.rChild = temp;
    			grandPar.setBlack();
    		}
    	}
    	
    	//Check if right child is null of grandparent, fix based on two cases
    	else if(!grandPar.hasLeft() && grandPar.hasLeft()) {
    		parent = grandPar.rChild;
    		boolean hasRight = parent.hasRight();
    		boolean hasLeft  = parent.hasLeft();
    		
    		//Case 1: if the violation is parent's right child
    		if(!hasRight && hasLeft) {
    			kid  = parent.lChild;
    			temp = grandPar;    			
    			
    			if(kid.color == BLACK) return; //no violation
    			
    			//Case 4 in RBT notes
    			//grandParent is replaced with kid
    			//to fix problem, set grandPar (parent's) lChild to kid
    			//and rChild to grandParent
    			grandPar.setRed();
    			grandPar.setKeyValue(kid.key, kid.val);
    			grandPar.lChild = temp;
    			grandPar.rChild = parent;
    			grandPar.setBlack();
    		}
    		
    		//Case 2: if the violation is parent's left child
    		else if(!hasLeft && hasRight) {
    			kid  = parent.rChild;
    			temp = grandPar;
    			
    			//Case 3 in RBT notes
    			//grandParent is replaced with kid
    			//to fix problem, set grandPar (parent's) lChild to kid
    			//and rChild to grandParent
    			grandPar.setRed();
    			if(kid.color == BLACK) return;
    			grandPar.setKeyValue(parent.key, parent.val);
    			grandPar.lChild = parent;
    			grandPar.rChild = kid;
    			grandPar.setBlack();
    		}
    	}
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
    private KeyValuePair lookup(K key)  {
        if(root == null) return null;
        
        KeyValuePair curr = root;
        int compare = curr.key.compareTo(key);
        
        while(curr != null) {
            compare = curr.key.compareTo(key);
            if(compare == 0) return curr;
            else if(compare > 0)
                curr = curr.lChild;
            else if(compare < 0)
                curr = curr.rChild;
        }
        return null; //only happens when curr == null
    	
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
            color  = RED;
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
        
        private boolean hasRight() {
        	return rChild != null;
        }
        
        private boolean hasLeft() {
        	return lChild != null;
        }
        
        private void setBlack() {
        	this.color = BLACK;
        }
        
        private void setRed() {
        	this.color = RED;
        }
    }
}
