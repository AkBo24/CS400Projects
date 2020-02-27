import java.util.ArrayList;
import java.util.List;

/**
 * Implements a generic Red-Black tree.
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
    
    private RBTNode root = null;
    private int size = 0;

    // USE AND DO NOT EDIT THESE CONSTANTS
    public static final int RED = 0;
    public static final int BLACK = 1;

    /**
     * Returns the color of the node that contains the specified key.
     * Returns RBT.RED if the node is red, and RBT.BLACK if the node is black.
     * Returns -1 if the node is not found.
     * @param 
     * @return
     */
    public int colorOf(K key) {
        // TODO: implement private helper method as you see fit
        // From Deb's solution
        //Node found = getNodeWith(root,key); // TODO Auto-generated method stub
        //return found==null ? -1 : found.color;
        
        RBTNode found = lookup(key);
        return found == null ? -1 : found.color;
    }

    /**
     * Returns true if root is null or the color of the root is black.
     * If root is null, returns true.
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
    public boolean isRed(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null) throw new IllegalNullKeyException();
        if(!this.contains(key)) throw new KeyNotFoundException();
        
        RBTNode found = lookup(key);
        if(found == null) return false;
        
        return found == null ? false : found.color == RED;
    }

    /**
     * Returns true if the node that contains this key is BLACK.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is BLACK,
     * else return false if key is found and the node's color is RED.
     */
    public boolean isBlack(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null) throw new IllegalNullKeyException();
        if(!this.contains(key)) throw new KeyNotFoundException();
        
        RBTNode found = lookup(key);
        if(found == null) return false;
        
        return found == null ? false : found.color == BLACK;
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
        
        RBTNode temp = lookup(key);
        if(temp == null) throw new KeyNotFoundException();      
        if(temp.lChild == null) return null;
        
        return temp.lChild.key;
    }

    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException();    
        
        RBTNode temp = lookup(key);
        if(temp == null) throw new KeyNotFoundException();      
        if(temp.rChild == null) return null;
        
        return temp.rChild.key;
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
    
    private int height(RBTNode child) {
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
    
    private void printInorder(RBTNode n, List<K> list) { 
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

    private void printPreOrderTraversal(RBTNode n, List<K> list) {
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

    private void printPostOrderTraversal(RBTNode n, List<K> list) {
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
    
    private void printLevelOrderTraversal(RBTNode n, List<K> list) {
        if(n == null)
            return;
        
        if(n.lChild != null)
            list.add(n.lChild.key);
        if(n.rChild != null)
            list.add(n.rChild.key);
        
        printLevelOrderTraversal(n.lChild, list);
        printLevelOrderTraversal(n.rChild, list);
        
    }

    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        // TODO Auto-generated method stub
        
        if(key == null) throw new IllegalNullKeyException("Key is null");
        if(contains(key)) throw new  DuplicateKeyException("Key exisits in tree");
        
        //if key is not null or not in the tree, insert!
        RBTNode kvp = new RBTNode(key, value);
        root = insert(root, kvp);
        
        if(root.color != BLACK) root.color = BLACK;
        
        size++;
        return;
    }
    
    private RBTNode insert(RBTNode  n, RBTNode kvp) {
        if(n == null) {
            
            if(n == root) {
                n = new RBTNode();
                n.setKeyValuePair(kvp.key, kvp.val);
                n.setBlack();
                return n;
            }
            
            n = new RBTNode();
            n.setKeyValuePair(kvp.key, kvp.val);
            return n;
        }
        
        int compare = kvp.compareTo(n);
        
        if(compare > 0)
            n.rChild = insert(n.rChild, kvp);
        else
            n.lChild = insert(n.lChild, kvp);
        
        triNodeRestructure(n);
        recolorFix(n);
        
        return n;
    }
    
    private void triNodeRestructure(RBTNode grandPar) {
        RBTNode parent, kid;
        RBTNode  temp = new RBTNode();
        temp.setKeyValuePair(grandPar.key, grandPar.val);
        
        //Tri-Node Restructure if Parent's siblings is null
        if(!grandPar.hasRight() && grandPar.lChild.haveChildren()) {
            parent = grandPar.lChild;
            
            //Case 1 of Tri-node restructure
            if(!parent.hasRight() && parent.hasLeft()) {
                kid = parent.lChild;
                
                //make sure that there is no red violation before fixing
                if(kid.color == BLACK) return;
                
                //Replace grand parent with parent's Key Value pair
                grandPar.setKeyValuePair(parent.key, parent.val);
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
                grandPar.setKeyValuePair(kid.key, kid.val);
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
                grandPar.setKeyValuePair(parent.key, parent.val);
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
                grandPar.setKeyValuePair(kid.key, kid.val);
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
    
    private void recolorFix(RBTNode grandPar) {
        RBTNode parent, sibling;
        
        boolean hasBoth = grandPar.hasLeft() && grandPar.hasRight();
        
        if(hasBoth) {
            
            boolean bothRed = grandPar.lChild.color == RED && grandPar.rChild.color == RED;
            
//            System.out.println("grandPar:" + grandPar.key);
//            System.out.println(bothRed);
            if(bothRed) {
                
                //Case 1: parent is left child of grandParent has a red left child
                parent  = grandPar.lChild;
                sibling = grandPar.rChild;
                
                //if condition true, re-color to create red, black, red sandwich
                if(parent.hasLeft() && parent.lChild.color == RED) {
                    
                    grandPar.setRed();
                    parent.setBlack();
                    sibling.setBlack();
                    parent.lChild.setRed();
                }
                
                //Case 2: parent has a red right child
                else if(parent.hasRight() && parent.rChild.color == RED) {
                    
                    grandPar.setRed();
                    parent.setBlack();
                    sibling.setBlack();
                    parent.rChild.setRed();
                    return;
                }
                
                //Case 1: parent is right child of grandParent has a red left child
                parent  = grandPar.rChild;
                sibling = grandPar.lChild;
                if(parent.hasLeft() && parent.lChild.color == RED) {
                    grandPar.setRed();
                    parent.setBlack();
                    sibling.setBlack();
                    parent.lChild.setRed();
                    return;
                }
                
                //Case 4: parent has a red right child
                if(parent.hasRight() && parent.rChild.color == RED) {
                    grandPar.setRed();
                    parent.setBlack();
                    sibling.setBlack();
                    parent.rChild.setRed();
                    return;
                }
            }
            
            if(!rootIsBlack()) root.setBlack();
        }
    }

    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
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
        RBTNode curr = root;
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

    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return size;
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub
        
    }

    /**
     * helper method written to find and return a specific KeyValuePair
     * @author akshaybodla
     * @param key
     * @return
     */
    private RBTNode lookup(K key) {
        RBTNode curr = root;
        
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
    
    private class RBTNode {
        
        K key;
        V val;
        int color;
        
        RBTNode lChild;
        RBTNode rChild;
        
        private RBTNode() {
            key = null;
            val = null;
            color = RED;
            
            lChild = null;
            rChild = null;
        }
        
        private RBTNode(K key, V val) {
            this.key = key;
            this.val = val;
            color = RED;
            
            lChild = null;
            rChild = null;
        }
        
        private void setKeyValuePair(K key, V val) {
            this.key = key;
            this.val = val;
        }
        
        private int compareTo(RBTNode other) {
            return this.key.compareTo(other.key);
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
           
        private boolean hasRight() {
            return rChild != null;
        }
        
        private boolean hasLeft() {
            return lChild != null;
        }
        
    }
    

    // TODO: override the insert method so that it rebalances 
    //       according to red-black tree insert algorithm.


    // TODO [OPTIONAL] you may override print() to include
    //      color R-red or B-black.
    
}