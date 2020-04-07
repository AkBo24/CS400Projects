import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    
 * 
 * Directed and unweighted graph implementation
 */

@SuppressWarnings("unused")
public class Graph implements GraphADT {
    
    //the set will store graphnodes which have 
    //fields for edges etc
    private Set<GraphNode<String>> verticies;
    
    /*
     * Default no-argument constructor
     */ 
    public Graph() {
        verticies = new HashSet<GraphNode<String>>();
    }

    
    /**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     * 
     * @param vertex the vertex to be added
     */
    @Override
    public void addVertex(String vertex) {
        
        if(vertex == null) return;
        
        //dont do anything if the vertex exists in set
//        for(GraphNode<String> i : verticies)
//            if(i.contains(vertex)) return;
        
        verticies.add(new GraphNode<String>(vertex));
    }

    /**
     * Remove a vertex and all associated 
     * edges from the graph.
     * 
     * If vertex is null or does not exist,
     * method ends without removing a vertex, edges, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     *  
     * @param vertex the vertex to be removed
     */
    @Override
    public void removeVertex(String vertex) {
        // TODO Auto-generated method stub
        
        if(vertex == null) return;
        GraphNode<String> remove = null;
        
        //
        for(GraphNode<String> i : verticies) {
            
            //if this vertex is found remove it
            if(i.contains(vertex))
                verticies.remove(i);
        }
        
//        if(remove == null) return;
//        verticies.remove(remove);
    }

    
    /**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * 
     * If either vertex does not exist,
     * VERTEX IS ADDED and then edge is created.
     * No exception is thrown.
     *
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
     *  
     * @param vertex1 the first vertex (src)
     * @param vertex2 the second vertex (dst)
     */
    @Override
    public void addEdge(String vertex1, String vertex2) {
        // TODO Auto-generated method stub
        if(vertex1 == null || vertex2 == null) return;
        
        //Verticies is a set so it will not allow duplicate nodes 
        this.addVertex(vertex1);
        this.addVertex(vertex2);
        
        GraphNode<String> v1 = null;
        GraphNode<String> v2 = null;
        
        //find the graphnode representing vertex1  and vertex2
        for(GraphNode<String> i : verticies) {
            if(i.contains(vertex1)) v1 = i;
            if(i.contains(vertex2)) v2 = i;
        }
        
        //if v1 already points to v2, do nothing: edges is a list and will allow duplicates
        if(v1 == null || v2 == null || v1.edges.contains(v2)) 
            return;
        
        v1.addEdge(v2);
    }

    
    /**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     *  
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(String vertex1, String vertex2) {
        // TODO Auto-generated method stub
        if (vertex1 == null || vertex2 == null)
            return;

        GraphNode<String> v1 = null;
        GraphNode<String> v2 = null;

        //find the graphnode representing vertex1 and vertex2
        for (GraphNode<String> i : verticies) {
            if (i.contains(vertex1)) v1 = i;
            if (i.contains(vertex2)) v2 = i;
        }
        
        if (v1 == null || v2 == null)
            return;

        //remove the edge if both exist
        v1.removeEdge(v2);
    }

    /**
     * Returns a Set that contains all the vertices
     * 
     * @return a Set<String> which contains all the vertices in the graph
     */
    @Override
    public Set<String> getAllVertices() {
        // TODO Auto-generated method stub
        
        //Iterate through verticies and add each vertex to set
        Set<String> allVert = new HashSet<String>();
        
        for(GraphNode<String> i : verticies)
            allVert.add(i.vertex);
        
        return allVert;
    }

    /**
     * Get all the neighbor (adjacent-dependencies) of a vertex
     * 
     * For the example graph, A->[B, C], D->[A, B] 
     *     getAdjacentVerticesOf(A) should return [B, C]. 
     * 
     * In terms of packages, this list contains the immediate 
     * dependencies of A and depending on your graph structure, 
     * this could be either the predecessors or successors of A.
     * 
     * @param vertex the specified vertex
     * @return an List<String> of all the adjacent vertices for specified vertex
     */
    @Override
    public List<String> getAdjacentVerticesOf(String vertex) {
        // TODO Auto-generated method stub
        GraphNode<String> v1 = null;
        List<String> allEdges = new ArrayList<String>();
        
        //iterate through verticies to find the node represented by vertex
        for(GraphNode<String> i : verticies)
            if(i.contains(vertex)) v1 = i;
        
        //iterate through the edges of v1 and add it to allEdges
        for(GraphNode<String> i : v1.edges)
            allEdges.add(i.vertex);
        
        //allEdges now has all the edges of vertex
        return allEdges;
    }

    /**
     * Returns the number of edges in this graph.
     * @return number of edges in the graph.
     */
    @Override
    public int size() {
        // TODO Auto-generated method stub
        
        //get the size of the edge list inside each graphnode and add it to total
        int total = 0;
        
        for(GraphNode<String> i : verticies)
            total += i.edges.size();
        
        return total;
    }

    /**
     * Returns the number of vertices in this graph.
     * @return number of vertices in graph.
     */
    @Override
    public int order() {
        // TODO Auto-generated method stub
        return verticies.size();
    }
     
    /**
     * Returns the number of vertices in this graph.
     * @return number of vertices in graph.
     */
    // TODO: implement all the methods declared in GraphADT
    @SuppressWarnings("unused")
    private class GraphNode<K> {
        private K vertex;
        private List<GraphNode<K>> edges;
        private String mark;
        
        private GraphNode() {
            vertex = null;
            edges = new ArrayList<GraphNode<K>>();
            mark = "UNVISITED";
        }
        
        private GraphNode(K vertex) {
            this.vertex = vertex;
            edges = new ArrayList<GraphNode<K>>();
            mark = "UNVISITED";
        }
        
        private boolean contains(K vertex) {
            return this.vertex.equals(vertex);
        }
        
        private void addEdge(GraphNode<K> vertex) {
            edges.add(vertex);
        }
        
        private void removeEdge(GraphNode<K> vertex) {
            edges.remove(vertex);
        }
    }
}
