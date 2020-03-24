// TODO: add imports as needed

// org.junit.Assert.*; 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/** TODO: add class header comments here*/
public class HashTableTest{

    // TODO: add other fields that will be used by multiple tests
    HashTableADT<Integer, String> htIntegerKey;
    
    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {
        htIntegerKey = new HashTable<Integer,String>();
    }

    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {
        
    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
        try {
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { assert(true); } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    // TODO add your own tests of your implementation
    /**
     * 
     */
    @Test
    public void test002_Insert_Values() {
        try {
            htIntegerKey.insert(1, "1");
            htIntegerKey.insert(2, "2");
            htIntegerKey.insert(3, "3");
            
            assertEquals(htIntegerKey.get(1),"1");
            assertEquals(htIntegerKey.get(2),"2");
            assertEquals(htIntegerKey.get(3),"3");


        }
        catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    /**
     * 
     */
    @Test
    public void test003_Insert_replaces_same_key() {
        try {
            htIntegerKey.insert(1, "1");
            htIntegerKey.insert(1, "2");
            htIntegerKey.insert(1, "3");

            assertEquals(htIntegerKey.get(1), "3");
            if(!htIntegerKey.get(1).equals("3")) 
                fail("Inserting duplicate key did not update the value");
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    /**
     * 
     */
    @Test
    public void test004_Insert_same_key_remove_one() {
        try {
            htIntegerKey.insert(1, "1");
            htIntegerKey.insert(101, "2");
            htIntegerKey.insert(201, "3");

            assertEquals(htIntegerKey.get(201), "3");
            if(!htIntegerKey.get(201).equals("3")) 
                fail("Inserting duplicate key did not update the value");
            
            htIntegerKey.remove(201);
            htIntegerKey.get(201);
            
        }
        catch(KeyNotFoundException e) {
            assert(e instanceof KeyNotFoundException);
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    /**
     * 
     */
    @Test
    public void test005_HashTable_is_resized() {
        try {
            HashTable<Integer, Integer> temp = new HashTable<Integer, Integer>(5,0.6);
            
            assertEquals(5,temp.getCapacity());
            
            Integer t = 1;
            temp.insert(t, t);
            t++;
            
            t = 1;
            temp.insert(t, t);
            t++;
            
            t = 1;
            temp.insert(t, t);
            t++;
            
            t = 1;
            temp.insert(t, t);
            t++;
            
            assertEquals(11,temp.getCapacity());
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    @Test
    public void test006_After_resize_keys_rehashed() {
        try {
            HashTable<Integer, Integer> temp = new HashTable<Integer, Integer>(5,0.6);
            
            assertEquals(5,temp.getCapacity());
            
            Integer t = 1;
            temp.insert(t, t);
            t++;
            
            t = 1;
            temp.insert(t, t);
            t++;
            
            t = 1;
            temp.insert(t, t);
            t++;
            
            t = 1;
            temp.insert(t, t);
            t++;
            
            assertEquals(11,temp.getCapacity());
            
            assertEquals(Integer.parseInt("1"), temp.get(1));
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    @Test
    public void test007_get_null_key_throws_exception() {
        
        try {
            htIntegerKey.get(null);
        } catch (IllegalNullKeyException e1) {
            assert(true);
            
        } catch (KeyNotFoundException e1) {
            e1.printStackTrace();
            fail("Key not found");
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
        
    }
    
    @Test
    public void test008_insert_1_remove_1() {
        
        try {
            htIntegerKey.insert(1,"1");
            htIntegerKey.remove(1);
            htIntegerKey.get(1);
            
        } catch (IllegalNullKeyException e1) {
            e1.printStackTrace();
            
        } catch (KeyNotFoundException e1) {
            assert(true);
        } catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
        
    }

    @Test
    public void test009_get_not_existent_key() {
        try {
            htIntegerKey.insert(1,"1");
            htIntegerKey.insert(1, "2");
            htIntegerKey.get(2);
            
            if(!htIntegerKey.get(1).equals("1"))
                fail("Removing a key did not conserve other with the same key");
        } catch (IllegalNullKeyException e1) {
            e1.printStackTrace();
            fail("key is not null");
        } catch (KeyNotFoundException e1) {
            assert(e1 instanceof Exception);
        } catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    @Test
    public void test010_insert_many() {
        try {
            int t = 1;
            for(int i = 0; i < 100; i++)
                htIntegerKey.insert(t, ""+t);
            assertEquals(htIntegerKey.getCapacity(), 201);
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
}
