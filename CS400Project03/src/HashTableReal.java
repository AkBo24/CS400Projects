import java.util.LinkedList;
import java.util.List;

// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//
public class HashTableReal<K extends Comparable<K>, V> implements HashTableADT<K, V> {
    
    // TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
    private V[] hash;
    private double lFThreshold;
    private double loadFactor;
    private int size;
    
    
    // TODO: comment and complete a default no-arg constructor
    public HashTableReal() {

    }
    
    // TODO: comment and complete a constructor that accepts 
    // initial capacity and load factor threshold
        // threshold is the load factor that causes a resize and rehash
    public HashTableReal(int initialCapacity, double loadFactorThreshold) {

    }

    @Override
    public void insert(K key, V value) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getLoadFactorThreshold() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getLoadFactor() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCapacity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCollisionResolution() {
        // TODO Auto-generated method stub
        return 0;
    }

    public class hashNode {
        private V[] nodeHash;
        private double lFThreshold;
        private double loadFactor;
        private int size;
        
        private hashNode() {
            nodeHash = new V[];
        }
        
        private double getThreshold
    }
    
    
    // TODO: implement all unimplemented methods so that the class can compile

        
}