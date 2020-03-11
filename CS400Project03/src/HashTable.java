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
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
    private LinkedList<List<V>> hash;
	private double lFThreshold;
	private double loadFactor;
	private int size;
    
	// TODO: comment and complete a default no-arg constructor
	public HashTable() {
	    hash = new LinkedList<List<V>>();
	    lFThreshold = 75;
	    loadFactor  = 0;
	    size = 0;
	}
	
	
	// TODO: comment and complete a constructor that accepts 
	// initial capacity and load factor threshold
        // threshold is the load factor that causes a resize and rehash
	public HashTable(int initialCapacity, double loadFactorThreshold) {
        hash = new LinkedList<List<V>>();	    
	    this.lFThreshold = loadFactorThreshold;
	    loadFactor = 0;
	    size = 0;
	}

    @Override
    public void insert(K key, V value) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("key is null");
        
        hash.get(getHash(key)).add(0, value);
        size++;
        loadFactor = size / hash.size();
    }

    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("key is null");
        
        List<V> temp = hash.get(getHash(key));
        
        if(temp == null || temp.size() == 0) return false;
        
        temp.remove(0);
        size--;
        loadFactor = size / hash.size();
        return true;
    }

    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        if(key == null) 
            throw new IllegalNullKeyException("key is null");
        
        List<V> temp = hash.get(getHash(key));
        
        if(temp == null || temp.size() == 0) 
            throw new KeyNotFoundException("Key not found");
        
        return temp.get(0);
    }

    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return size;
    }

    @Override
    public double getLoadFactorThreshold() {
        // TODO Auto-generated method stub
        return lFThreshold;
    }

    @Override
    public double getLoadFactor() {
        // TODO Auto-generated method stub
        return loadFactor;
    }

    @Override
    public int getCapacity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCollisionResolution() {
        // TODO Auto-generated method stub
        return 8;
    }

    
    private int getHash(K key) {
        // TODO Auto-generated method stub
        return key.hashCode();
    }
		
}
