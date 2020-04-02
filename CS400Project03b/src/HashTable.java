
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
 * My implementation of a hash table using an array of linked nodes (chaining)
 * 
 * @author Askhay Bodla
 */
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

    // TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
    private DataItem[] hash; // Hash Table
    private double threshold; // the max value before rehashing is required
    private double loadFactor; // value representing "how full" the table is
    private double size; // # of elements in this table

    // TODO: comment and complete a default no-arg constructor
    /**
     * @param no parameter constructor Hash Table is set to size 1000, and rehashes
     *           when 75 elements are inserted (75% full)
     *           
     *  I have changed the initial size of this hash table from 10 in P03a since the small
     *  size did not achieve a O(1) time complexity.  However, I was able to acheive linear
     *  time once the initial hashmap size was larger.
     */
    public HashTable() {
        hash = new DataItem[1000];
        threshold = 0.75;
        loadFactor = 0;
        size = 0;
    }

    /**
     * Hash Table constructor
     * 
     * @param initialCapacity
     * @param loadFactorThreshold threshold is the load factor that causes a resize
     *                            and rehash
     */
    public HashTable(int initialCapacity, double loadFactorThreshold) {
        hash = new DataItem[initialCapacity];
        threshold = loadFactorThreshold;
        loadFactor = 0;
        size = 0;
    }

    /**
     * Insert a key value pair into this hash table
     * 
     * @param K key to insert
     * @param V val to insert
     */
    @Override
    public void insert(K key, V value) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        if (key == null)
            throw new IllegalNullKeyException();

        int hashIndex = getHash(key, hash); // where this key is to be inserted

        // if that index in hash is empty enter in there
        // otherwise traverse to the end of the list and insert this key value pair
        // there
        if (hash[hashIndex] == null) {
            hash[hashIndex] = new DataItem(key, value);
        } else {
            DataItem curr = hash[hashIndex];
            
            // iterate throught the linked nodes until
            // it finds the final linked node at this
            // bucket
            while (curr.next != null) {
                curr = curr.next;
            }
            
            curr.setNext(new DataItem<K, V>(key, value)); //insert the key value pair
        }

        size++;
        loadFactor = size / hash.length; //update load factor and check if table needs to be rehashed

        // rehash only when the load factor equals or is greater than the threshold of
        // this table
        if (loadFactor >= threshold)
            rehash();
    }

    /**
     * Remove a data item from this hash table
     * 
     * @param K key to be removed from this hash table
     * @throws IllegalNullKeyException if key is null
     */
    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        if (key == null)
            throw new IllegalNullKeyException();
        if (size == 0)
            return false;
        
        //find which bucket this key would be in
        int hashIndex = getHash(key, hash); 
        
        //if nothing is in that bucket, this key doesnt exist in the table
        if(hash[hashIndex] == null)
            return false;
        
        else if (hash[hashIndex].compareTo(key) == 0){
            hash[hashIndex] = hash[hashIndex].next; //if the first element is the key to remove
            size--;
            loadFactor = size % hash.length;
            return true;
        }
        else {
            DataItem curr = hash[hashIndex];
            
            //Iterate through the bucket to find the bucket until we find the key to remove
            while(curr.next != null) {
                if(curr.next.key.compareTo(key) == 0) { //if the next key is the key to remove
                    curr.next = curr.next.next;         //set it to the following node
                    size--;
                    loadFactor = size % hash.length;
                    return true;
                }
                curr = curr.next; //get the next node in this bucket
            }
        }
        return false;
    }

    /**
     * Get a value from this hash table
     * 
     * @param K key to get its valued pair
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException    if this key is not present in the hash table
     */
    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        if (key == null)
            throw new IllegalNullKeyException();
        
        //Find which bucket this key would be in
        int hashIndex = getHash(key, hash);

        DataItem curr = hash[hashIndex];

        //If the bucket is null, key doesnt exist in this map
        if (curr == null)
            throw new KeyNotFoundException();
        int compare = curr.compareTo(key);

        //iterate through the bucket to find the key value pair 
        while (curr != null && compare != 0) {
            curr = curr.next;
            
            if (curr == null)
                throw new KeyNotFoundException();
            compare = curr.compareTo(key);
        }

        //Curr equals null if we reached the end of the bucket, hence key value pair
        //does not exist
        if (curr == null)
            throw new KeyNotFoundException();
        else
            return (V) curr.val; //return the value associated with this node

    }

    /**
     * @return the number of elements in this table
     */
    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return (int) size;
    }

    /**
     * @return the load factor threshold of this table
     */
    @Override
    public double getLoadFactorThreshold() {
        // TODO Auto-generated method stub
        return threshold;
    }

    /**
     * @return the current load factor of this table
     */
    @Override
    public double getLoadFactor() {
        // TODO Auto-generated method stub
        return loadFactor;
    }

    /**
     * The current capacity of the table
     */
    @Override
    public int getCapacity() {
        // TODO Auto-generated method stub
        return hash.length;
    }

    /**
     * This hash table uses chaining as its collision resolution
     */
    @Override
    public int getCollisionResolution() {
        // TODO Auto-generated method stub
        return 5;
    }

    /**
     * @param key
     * @param table
     * @return
     */
    private int getHash(K key, DataItem[] table) {
        return key.hashCode() % table.length;
    }

    /**
     * When the load factor equals the threshold
     * The table needs to be rebuilt with a larger capacity
     */
    private void rehash() {
        // Create a new array that is twice the size of hash + 1
        DataItem<K, V>[] newTable = new DataItem[hash.length * 2 + 1]; 

        // Insert everything in Hash into new Table
        for (int i = 0; i < hash.length; i++) {
            DataItem<K, V> temp = this.hash[i]; //the bucket at this current position

            //if the bucket exists insert everything inside
            while (temp != null) {
                int hashIndex = getHash(temp.key, newTable); //find the index this key would be in newTable
                if (newTable[hashIndex] == null)
                    newTable[hashIndex] = new DataItem(temp.key, temp.val); //insert this into newTable
                else {
                    newTable[hashIndex] = newTable[hashIndex].add(new DataItem(temp.key, temp.val));
                }
                temp = temp.next;
            }
        }
        this.hash = newTable;
    }

    /**
     * Representation of a key value pair for a data item within this hash table
     * @author akshaybodla
     *
     * @param <K>
     * @param <V>
     */
    @SuppressWarnings("hiding")
    private class DataItem<K extends Comparable<K>, V> {
        private K key;
        private V val;
        private DataItem next;

        public DataItem() {
            key = null;
            val = null;
            next = null;
        }

        public DataItem(K key, V val) {
            this.key = key;
            this.val = val;
            next = null;
        }

        public int compareTo(K other) {
            return this.key.compareTo(other);
        }

        public void setNext(DataItem curr) {
            this.next = curr;
        }

        private DataItem<K, V> add(DataItem<K, V> element) {
            element.setNext(this);
            return element;
        }
    }

}

