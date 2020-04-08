import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * PackageManagerTest.java created by akshaybodla on MacBook Pro in CS400Project04
 * 
 * Author: Akshay Bodla (bodla@wisc.edu)
 * Date: @date
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
 * Device :  Akshay's Macbook Pro
 * OS     :  macOS High Sierra
 * Version: Version 10.13.6
 * 
 * List Collaborators: N/A
 * 
 * Other Credits: N/A
 */

/**
 * Tests the functionality of PackageManager.java
 * @author akshaybodla
 *
 */
class PackageManagerTest {
    
    PackageManager manager;
    PackageManager cyclic;
    PackageManager large;
    
    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {
        try {
            manager = new PackageManager();
            String filepath = "/Users/akshaybodla/Documents/GitHub/CS400Projects/CS400Project04/src/shared_dependencies.json";
            manager.constructGraph(filepath);
            
            cyclic = new PackageManager();
            filepath = "/Users/akshaybodla/Documents/GitHub/CS400Projects/CS400Project04/src/cyclic.json";
            cyclic.constructGraph(filepath);
            
            large = new PackageManager();
            filepath = "/Users/akshaybodla/Documents/GitHub/CS400Projects/CS400Project04/src/large_dependencies.json";
            large.constructGraph(filepath);
            
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
    void test05_test_cyclic_graph(){
        try {
            setUp();
            assertEquals("[D, B, C, A]",cyclic.getInstallationOrderForAllPackages().toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    void test06_cyclic_graph_for_all_packages(){
        try {
            setUp();
            assertEquals("[D, B, C, A]", cyclic.getInstallationOrderForAllPackages().toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    void test07(){
        try {
            setUp();
            assertEquals("[D, B, C, A]", cyclic.getInstallationOrderForAllPackages().toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    void test08_get_max_depdendency(){
        try {
            setUp();
            assertEquals("A",manager.getPackageWithMaxDependencies());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    void test09_construct_large_graph(){
        try {
            setUp();
            assertEquals("[E, L, B, G, F, D, Z, C, A]", large.getInstallationOrderForAllPackages().toString());
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            assert(true);
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
