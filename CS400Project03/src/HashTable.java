/**
 * HashTable.java created by akshaybodla on MacBook Pro in CS400Project0
 * 
 * Author: Akshay Bodla (bodla@wisc.edu)
 * Date: March 12, 2020
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * IDE: Eclipse IDE for Java Developers
 * 
 * Version: 2019-06 (4.12.0)
 * Build id: 20190614-1200
 * 
 * Device:  Akshay's Macbook Pro
 * OS    :  macOS High Sierra
 * Version: Version 10.13.6
 * 
 * List Collaborators: N/A
 * 
 * Other Credits: N/A
 * 
 * Known Bugs: n/a
 */

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

/**
 * My implementation of a hash table using an array of linked nodes
 * (chaining)
 * @author Askhay Bodla
 */
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
    
    // TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
    private DataItem[] hash;   //Hash Table
    private double threshold;  //the max value before rehashing is required
    private double loadFactor; //value representing "how full" the table is
    private double size;       //# of elements in this table
    
    
    public static void main(String[] args) {
        HashTable<Integer, Integer> test = new HashTable<Integer,Integer>();
        
        try {
//            test.insert(10, 10);
//            test.insert(20, 20);
//            
//            System.out.println(test.get(10));
//            System.out.println(test.get(20));
            int num = 1000000;

            for(int i = 0; i < num; i++)
                test.insert(i, i);
            
            for(int i = 0; i < num; i++)
                System.out.println(test.get(i));
            
        } catch (IllegalNullKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    // TODO: comment and complete a default no-arg constructor
    /**
     * @param no parameter constructor
     * Hash Table is set to size 100, and rehashes when
     * 75 elements are inserted (75% full)
     */
    public HashTable() {
        hash       = new DataItem[10];
        threshold  = 0.75;
        loadFactor = 0;
        size       = 0;
    }
    
    /**
     * Hash Table constructor
     * @param initialCapacity
     * @param loadFactorThreshold 
     * threshold is the load factor that causes a resize and rehash
     */
    public HashTable(int initialCapacity, double loadFactorThreshold) {
        hash       = new DataItem[initialCapacity];
        threshold  = loadFactorThreshold;
        loadFactor = 0;
        size       = 0;     
    }

    /**
     * Insert a key value pair into this hash table
     * @param K key to insert
     * @param V val to insert
     */
    @Override
    public void insert(K key, V value) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        
        if(key == null) throw new IllegalNullKeyException("Key is null!");
        
        int hashIndex   = getHash(key, hash); //where this key is to be inserted
        
        //if that index in hash is empty enter in there
        //otherwise traverse to the end of the list and insert this key value pair there
        if(hash[hashIndex] == null) {
            hash[hashIndex] = new DataItem(key, value);
        }
        else { 
            DataItem curr = hash[hashIndex];
            
            //iterate throught the linked nodes
            while(curr.next != null) {
                curr = curr.next;
            }
            
            curr.setNext(new DataItem<K,V>(key, value));
        }
        
        size++;
        loadFactor = size/hash.length;
        
        //rehash only when the load factor equals the threshold of this table
        if(loadFactor == threshold)
            rehash();
    }

    /**
     * Remove a data item from this hash table
     * @param K key to be removed from this hash table
     * @throws IllegalNullKeyException if key is null
     */
    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        if(key == null) throw new  IllegalNullKeyException("Key is null!");
        if(size == 0) return false;
        
        //The supposed index in this hash table
        int hashIndex = getHash(key, hash);
        
        //Curr represents the linked node where this key is supposed to be
        DataItem curr = hash[hashIndex];
        
        //if curr is null then this key is not found
        //as there is no data item @ key's index
        if(curr == null) 
            throw new  IllegalNullKeyException("Key not found");      
        int compare = curr.compareTo(key);
        
        //There is at least one node at this index
        while(curr != null) {
            
            //If curr's key is the same as the one inserted
            if(curr.next.key.compareTo(key) == 0) {
                curr.next = curr.next.next; //get the next node
                size--;
                loadFactor = size/hash.length;
                return true;
            }
            
            curr = curr.next;
            compare = curr.compareTo(key);
        }
        
        return false;
    }

    /**
     * Get a value from this hash table
     * @param K key to get its valued pair
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if this key is not present in the hash table
     */
    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        if(key == null) throw new IllegalNullKeyException("Key is null!");
        int hashIndex = getHash(key, hash);
             
        DataItem curr = hash[hashIndex];
        
        if(curr == null) 
            throw new KeyNotFoundException("Key not found!");        
        int compare = curr.compareTo(key);
        
        while(curr != null && compare != 0) {
            curr = curr.next;
            
            if(curr == null) throw new KeyNotFoundException("Key is null!");
           
            compare = curr.compareTo(key);
        }
        
        if(curr == null) 
            throw new KeyNotFoundException("Key not found!");
        else
            return (V) curr.val;
        
    }

    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return (int)size;
    }

    @Override
    public double getLoadFactorThreshold() {
        // TODO Auto-generated method stub
        return threshold;
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

    // TODO: implement all unimplemented methods so that the class can compile
    private int getHash(K key, DataItem[] table) {
        return key.hashCode()%table.length;
    }
    
    private void rehash() {
        // TODO Auto-generated method stub
        DataItem<K,V>[] newTable = new DataItem[hash.length*2+1];
        
        for(int i = 0; i < newTable.length; i++) {
            DataItem<K,V> temp = this.hash[i];
            
            while(temp != null) {
                int hashIndex = getHash(temp.key, newTable);
                if(newTable[hashIndex] == null)
                    newTable[hashIndex] = new DataItem(temp.key, temp.val);
                else {
                    newTable[hashIndex] = newTable[hashIndex].add(
                            new DataItem(temp.key, temp.val));
                }
                temp = temp.next;
            }
        }
        this.hash = newTable;
    }
    
    @SuppressWarnings("hiding")
    private class DataItem <K extends Comparable<K>, V> {
        private K key;
        private V val;
        private DataItem next;
        
        public DataItem() {
            key  = null;
            val  = null;
            next = null;
        }
        
        public DataItem(K key, V val) {
            this.key =  key;
            this.val =  val;
            next     = null;
        }
        
        public int compareTo(K other) {
            return this.key.compareTo(other);
        }
        
        public void setNext(DataItem next) {
            this.next = next;
        }
        
        public void deleteThis() {
            
        }
        
        private DataItem<K,V> add(DataItem<K,V> element) {
            element.setNext(this);
            return element;
        }
    }
    

}
