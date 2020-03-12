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
public class HashTableTester<K extends Comparable<K>, V> implements HashTableADT<K, V> {

    // TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
    DataItem<K,V>[] hash;
    private double lFThreshold;
    private double loadFactor;
    private double size;
    
    
    // TODO: comment and complete a default no-arg constructor
    public HashTableTester() {
        hash  = new DataItem[100];
        lFThreshold = 0.75;
        loadFactor = 0;
        size = 0;
    }

    // TODO: comment and complete a constructor that accepts
    // initial capacity and load factor threshold
    // threshold is the load factor that causes a resize and rehash
    public HashTableTester(int initialCapacity, double loadFactorThreshold) {
        hash = new DataItem[initialCapacity];
        lFThreshold = loadFactorThreshold;
        loadFactor  = 0;
        size = 0;
    }

    @Override
    public void insert(K key, V value) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("Key is null!");

        int hashIndex = getHash(key);
        DataItem<K,V> element = new DataItem<K,V>(key,value);
        
        if(hash[hashIndex] == null) {
            hash[hashIndex] = element;
            size++;
            loadFactor = size/hash.length;
        }
        else {
            DataItem<K,V> temp = hash[hashIndex];
            
            while(temp!=null) {
                if(temp.key.compareTo(key) == 0) {
                    temp.setValue(value);
                }
                temp = temp.next;
            }
            hash[hashIndex] = hash[hashIndex].add(element);
            size++;
            loadFactor = size/hash.length;
        }
        hashRestructure();
    }
   
    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("Key is null!");

        int hashIndex = getHash(key);
        if(hash[hashIndex] == null)
            return false;
        
        else {
            DataItem<K,V> temp = hash[hashIndex];
            
            while(temp != null) {
                if(temp.key.compareTo(key) == 0) {
                    temp = null;
                    size--;
                    loadFactor = size/hash.length;
                    return true;
                }
                temp = temp.next;
            }
        }
        
        return false;
    }

    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("Key is null!");
        
        int hashIndex = getHash(key);
        
        if(hash[hashIndex] == null) 
            throw new KeyNotFoundException("Key not found");
        else {
            DataItem<K,V> temp = hash[hashIndex];
            
            while(temp != null) {
                if(temp.key.compareTo(key) == 0)
                    return temp.val;
                temp = temp.next;
            }
        }
        
        throw new KeyNotFoundException("Key not found");
    }
    
//    public static void main(String[] args) {
//        HashTableTester<Integer,Integer> test = new HashTableTester<Integer,Integer>();
//        try {
//            test.insert(1, 1);
//            test.insert(101, 2);
//            test.insert(201, 3);
//            
//
//        } catch (IllegalNullKeyException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return (int)size;
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
        return hash.length;
    }

    @Override
    public int getCollisionResolution() {
        // TODO Auto-generated method stub
        return 5;
    }
    
    private int getHash(K key) {
        return key.hashCode() % hash.length;
    }
    
    private void hashRestructure() {
        if(loadFactor == lFThreshold) {
            DataItem<K,V>[] temp = new DataItem[hash.length*2+1];
            
            //re-insert every element in hash into temp since get hash is different
            for(int i = 0; i < hash.length; i++) {
                if(hash[i] != null)
                    temp[i] = hash[i];
            }
            hash = temp;
        }
        
    }
    
    @SuppressWarnings("unused")
    private class DataItem<K,V> {
        private K key;
        private V val; 
        private DataItem<K,V> next;
        
        private DataItem() {
            setKey(null);
            val  = null;
            setNext(null);
        }
        
        private void setValue(V val) {
            // TODO Auto-generated method stub
            this.val = val;
        }

        private DataItem(K key, V val) {
            this.key = key;
            this.val = val;
        }

        private void setKey(K key) {
            this.key = key;
        }

        private DataItem<K,V> add(DataItem<K,V> element) {
            element.setNext(this);
            return element;
        }
        
        private void setNext(DataItem<K,V> next) {
            this.next = next;
        }
        
        private void setKeyValuePair(K key, V val) {
            this.key = key;
            this.val = val;
        }
    
    }

}