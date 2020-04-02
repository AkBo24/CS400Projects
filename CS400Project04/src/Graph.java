import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    Akshay Bodla
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
	
    private Set<String>  verticies; //sets represent the edges between verticies in this graph
    private List<String> edges;     //list representing the verticies in this graph
    
	/*
	 * Default no-argument constructor
	 */ 
	public Graph() {
	    verticies = new HashSet<String>();
		edges     = new ArrayList<String>();
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
        // TODO Auto-generated method stub
//        System.out.println(vertex == null || verticies.contains(vertex));
        if(vertex == null || verticies.contains(vertex)) return;
        verticies.add(vertex);
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
        if(vertex == null || !verticies.contains(vertex)) return;
        verticies.remove(vertex);
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
        if(vertex1 == null || vertex2 == null) 
            return;
        else if(!verticies.contains(vertex1) || !verticies.contains(vertex2))
            return;
        
        String edge  = vertex1+vertex2;
        if(edges.contains(edge)) return;
        
        edges.add(edge);
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
        if(vertex1 == null || vertex2 == null) 
            return;
        else if(!verticies.contains(vertex1) || !verticies.contains(vertex2))
            return;
        
        String edge  = vertex1+vertex2;
        if(!edges.contains(edge)) return;
        edges.remove(edge);
    }

    /**
     * Returns a Set that contains all the vertices
     * 
     * @return a Set<String> which contains all the vertices in the graph
     */
    @Override
    public Set<String> getAllVertices() {
        // TODO Auto-generated method stub
        return verticies;
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
        
        List<String> vEdges = new ArrayList<>();
        
        for(String i : edges) {
            if(i.substring(0, 1).equals(vertex))
                vEdges.add(i.substring(1));
                
        }
        
        return vEdges;
    }

    /**
     * Returns the number of edges in this graph.
     * @return number of edges in the graph.
     */
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return edges.size();
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

}
