import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
    
    private T ds;
    
    protected abstract T createInstance();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {}

    @AfterAll
    static void tearDownAfterClass() throws Exception {}

    @BeforeEach
    void setUp() throws Exception {
        ds = createInstance();
    }

    @AfterEach
    void tearDown() throws Exception {
        ds = null;
    }

    @Test
    void test00_empty_ds_size() {
        if (ds.size() != 0)
            fail("data structure should be empty, with size=0, but size="+ds.size());
    }
    
    // TODO: review tests 01 - 04
    @Test
    void test01_insert_one() {
        String key = "1";
        String value = "one";
        try {
            ds.insert(key, value);
            assert (ds.size()==1);
        }
        catch (RuntimeException e) {assert (ds.contains(key)) ;}
    }
    
    @Test
    void test02_insert_remove_one_size_0() {
        String key = "1";
        String value = "one";
        try {
            ds.insert(key, value);
            ds.remove(key);
            assert (ds.size() == 0); // remove the key
            if (ds.size() != 0)
                fail("data structure should be empty, with size=0, but size="+ds.size());
        } 
        catch (RuntimeException e) {assert (ds.contains(key)) ;}

    }
    
    @Test
    void test03_duplicate_exception_thrown() {
        String key = "1";
        String value = "one";
        try { 
            ds.insert("1", "one");
            ds.insert("2", "two");
            ds.insert(key, value); 
//            fail("duplicate exception not thrown");
        }
        catch (RuntimeException e) {assert (ds.contains(key)) ;}
        
    }
            
    
    @Test
    void test04_remove_returns_false_when_key_not_present() {
        try {
            String key = "1";
            String value = "one";
            ds.insert(key, value);
            
            if (ds.get(key) == null)
                fail("get("+key+ ") returned " + ds.get(key) + " which should have been removed");
            assert (!ds.remove("2")); // remove non-existent key is false
        }
        catch (RuntimeException e) {
            assert(true);
        }
    }

 
    // TODO: add tests 05 - 07 as described in assignment
    
    @Test
    void test05_insert_remove_one() {
        try {
            String key   = "1",
                   value = "one";
            
            ds.insert(key, value);
            ds.remove("1");
    
            assert(ds.contains("1") == false);
            if(ds.contains("1"))
                fail("remove did not successfully remove the node called by remove(1)"); 
        }
        catch (RuntimeException e) {
            assert(true);
        }
    }
    
    @Test
    void test06_insert_many_size() {
        try {
            ds.insert("1", "one");
            ds.insert("3", "three");
            ds.insert("2", "two");
            
            int size = ds.size();
            if(size != 3)
                fail("inserting 3 nodes, failed, current size: " + size);
            assert(size==3);
        }
        catch (RuntimeException e) {
            assert(true);
        }
    }
    
    @Test
    void test07_duplicate_values() {
      String key1  = "1",
             key2  = "2",
             value = "one";
      
      try {
          ds.insert(key1, value);
          ds.insert(key2, value);
          
          assert(ds.contains(key2));
          if(!ds.contains(key2))
              fail("did not insert a new key value pair with a duplicate value but different key");
      } 
      catch (RuntimeException e) {
          assert(true);
      }
    }

    // TODO: add more tests of your own design to ensure that you can detect implementation that fail
    
    @Test
    void test08_insert_null_key_value() {
        String key   = null,
               value = "d";
        
        try {
            ds.insert(key, value);
            fail("did not throw a null pointer exception");
        }
        catch(IllegalArgumentException e) {assert(true);}
        
        
    }
    
    @Test
    void test09_contains_returns_true() {
        try {
        ds.insert("1", "one");
        ds.insert("2", "two");
        ds.insert("3", "three");
        
        boolean contains = ds.contains("3");
        
        if(!contains)
            fail("ds.contains(\"3\") returns: " + contains + " when it should have returned " + !contains);
        assert(ds.contains("3"));
        }
        catch(RuntimeException e) {assert(true);}

        
    }
    
    @Test
    void test10_contains_returns_false() {
        try {
            ds.insert("1", "one");
    
            boolean contains = ds.contains("3");
            
            if(contains)
                fail("ds.contains(\"3\") returns: " + contains + " when it should have returned " + !contains);
            assert(!ds.contains("3"));
        }
        catch(RuntimeException e) {assert(true);}
    }
    
    // Tip: consider different numbers of inserts and removes and how different combinations of insert and removes

}
