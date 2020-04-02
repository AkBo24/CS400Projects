import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class GraphTest {
    Graph graph = new Graph();
    
    @Test
    void test00_test_initialization() {
        if(graph == null) fail("graph is null");
        
        assertEquals(0, graph.size());
        assertEquals(0, graph.order());
    }
    
    @Test
    void test01_add_two_verticies() {
        try {
            graph.addVertex("a");
            graph.addVertex("b");
            
            assertEquals(2, graph.order());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test02_add_two_verticies_and_edge() {
        try {
            String v1 = "a";
            String v2 = "b";
            graph.addVertex(v1);
            graph.addVertex(v2);
            
            graph.addEdge(v1, v2);
            
            assertEquals(1, graph.size());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test03_three_verticies_two_edges() {
        try {
            String v1 = "a";
            String v2 = "b";
            String v3 = "c";
            graph.addVertex(v1);
            graph.addVertex(v2);
            graph.addVertex(v3);
            
            graph.addEdge(v1, v2);
            graph.addEdge(v1, v3);
            
            assertEquals(2, graph.size());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test04_insert_null_verticies() {
        try {
            graph.addVertex(null);
            assertEquals(0, graph.order());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    void test05_insert_null__nonexistant_verticies_for_edges() {
        try {
            graph.addEdge(null, null);
            assertEquals(0, graph.size());
            
            String v1 = "a";
            graph.addEdge(v1, null);
            assertEquals(0, graph.size());

            graph.addEdge(v1, "b");
            assertEquals(0, graph.size());

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test06_removing_two_verticies(){
        try {
            String v1 = "a";
            String v2 = "b";
            graph.addVertex(v1);
            graph.addVertex(v2);
            
            graph.removeVertex("a");
            assertEquals(1, graph.order());
            
            graph.removeVertex("b");
            assertEquals(0, graph.order());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test07_removing_two_edges() {
        try {
            String v1 = "a";
            String v2 = "b";
            String v3 = "c";
            graph.addVertex(v1);
            graph.addVertex(v2);
            graph.addVertex(v3);
            
            graph.addEdge(v1, v2);
            graph.addEdge(v1, v3);
            
            graph.removeEdge(v1, v2);
            assertEquals(1, graph.size());
            graph.removeEdge(v1, v3);
            assertEquals(0, graph.size());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test08_removing_null_nonexistant_verticies() {
        try {
            graph.removeVertex(null);
            graph.removeVertex("a");
            assertEquals(0, graph.order());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test09_removing_null_nonexistant_edges() {
        try {
            graph.removeEdge(null, null);
            graph.removeEdge("a",  null);
            graph.removeEdge("a",  "b");
            
            assertEquals(0, graph.size());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test10_print_all_verticies() {
        try {
            String v1 = "a";
            String v2 = "b";
            String v3 = "c";
            graph.addVertex(v1);
            graph.addVertex(v2);
            graph.addVertex(v3);
            
            graph.addEdge(v1, v2);
            graph.addEdge(v1, v3);
            
            assertEquals("[a, b, c]", graph.getAllVertices().toString());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test11_print_all_edges() {
        try {
            String v1 = "a";
            String v2 = "b";
            String v3 = "c";
            graph.addVertex(v1);
            graph.addVertex(v2);
            graph.addVertex(v3);
            
            graph.addEdge(v1, v2);
            graph.addEdge(v1, v3);

            assertEquals("[b, c]", graph.getAdjacentVerticesOf(v1).toString());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
