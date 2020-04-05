import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class PackageManagerTest {
    
    PackageManager manager;
    PackageManager cyclic;
    
    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {
        try {
            manager = new PackageManager();
            String filepath = "/Users/akshaybodla/Documents/GitHub/CS400Projects/CS400Project04/src/shared_dependencies.json";
            manager.constructGraph(filepath);
            
            cyclic = new PackageManager();
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {
        
    }
    
    @Test
    void test00_get_installation_order_for_a() {
        try {
            setUp();
            assertEquals("[D, B, C, A]",manager.getInstallationOrder("A").toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
    @Test
    void test01_manager_throws_package_not_found() {
        
        try {
            setUp();
            assertEquals("[D, B, C, A]",manager.getInstallationOrder("Z").toString());
            fail("Package not found Exception was not thrown");
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    void test02_manager_gives_this_package_with_no_dependencies() {
        
        try {
            setUp();
            assertEquals("[D]",manager.getInstallationOrder("D").toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    void test03_to_install_only_give_uninstalled_dependencies(){
        
        try {
            setUp();
            assertEquals("[A, B]",manager.toInstall("A", "C").toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    void test04_gives_full_installation_list(){
        
        try {
            setUp();
            assertEquals("[D, B, C, A]",manager.getInstallationOrderForAllPackages().toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    void test05(){
        
        try {
            setUp();
            assertEquals("[D, B, C, A]",manager.getInstallationOrderForAllPackages().toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
