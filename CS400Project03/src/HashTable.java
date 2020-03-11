import java.util.LinkedList;

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
    private LinkedList<DataItem> hash;
	private double lFThreshold;
	private double loadFactor;
	private int size;
    
	// TODO: comment and complete a default no-arg constructor
	public HashTable() {
	    hash = new LinkedList<DataItem>();
	    
	    //Create the linked list with size initialCapacity
        for(int i = 0; i < 100; i++)
            hash.add(new DataItem());
	    
	    lFThreshold = 75;
	    loadFactor  = 0;
	    size = 0;
	}
	
	
	// TODO: comment and complete a constructor that accepts 
	// initial capacity and load factor threshold
    // threshold is the load factor that causes a resize and rehash
	public HashTable(int initialCapacity, double loadFactorThreshold) {
	    hash = new LinkedList<DataItem>();
	    
	    //Create the linked list with size initialCapacity
	    for(int i = 0; i < initialCapacity; i++)
	        hash.add(new DataItem());
	    
	    this.lFThreshold = loadFactorThreshold;
	    loadFactor = 0;
	    size = 0;
	}

    @Override
    public void insert(K key, V value) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("Key is null!");
        
        int hashIndex = getHash(key);
        hash.get(hashIndex).add(value);
        hash.get(hashIndex).setKey(key);
        size++;
        loadFactor = size/hash.size();
        
        if(loadFactor == lFThreshold) {
            
            //creating a new linked list to replace hash
            LinkedList<DataItem> temp = new LinkedList<DataItem>();
            for(int i = 0; i < hash.size()*2+1; i++)
                temp.add(new DataItem());
            
            //re-insert every element in hash into temp since get hash is different
            for(int i = 0; i < hash.size(); i++) {
                DataItem getI = hash.get(i);
                if(getI.size() > 0) {
                    hashIndex = getHash(getI.getKey());
                    temp.get(hashIndex).add(getI.inner);
                }
            } //end for loop
            
            hash = temp;
            loadFactor = size/hash.size();
        }
    }

    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("Key is null!");
        
        int hashIndex = getHash(key);
        DataItem temp = hash.get(hashIndex);
        
        if(temp.size() == 0) 
            throw new KeyNotFoundException("Key not found");
        else 
            return temp.get();
        
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
        return hash.size();
    }

    @Override
    public int getCollisionResolution() {
        // TODO Auto-generated method stub
        return 8;
    }

    
    private int getHash(K key) {
        // TODO Auto-generated method stub
        return key.hashCode() % hash.size();
    }
	
    @SuppressWarnings("unused")
    private class DataItem {
        private LinkedList<V> inner;
        private K key;
//        private V val;
        
        private DataItem() {
            inner = new LinkedList<V>();
        }
        
//        public void add(HashTable<K, V>.DataItem dataItem) {
//            // TODO Auto-generated method stub
//            inner = dataItem;
//        }


        private void add(V val) {
            inner.add(0, val);
        }
        
        private void add(LinkedList<V> dataSet) {
            inner.addAll(dataSet);
        }
        
        private V get() {
            return inner.get(0);
        }
        
        private int size() {
            return inner.size();
        }
        
        private K getKey() {
            return key;
        }
        
        private void setKey(K key) {
            this.key = key;
        }
    }
}
