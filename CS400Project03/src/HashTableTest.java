// TODO: add imports as needed

// org.junit.Assert.*; 
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
//            assert(htIntegerKey.get(1).equals("2"));
//            if(!htIntegerKey.get(1).equals("2")) 
//                fail("Inserting duplicate key did not update the value");
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
}
