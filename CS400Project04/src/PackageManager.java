import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

@SuppressWarnings("unused")
public class PackageManager {
    
    private Graph graph;
    private JSONArray getPacks;
    private Set<String> verticies;
    private List<String> edges;
    private JSONObject jo;
    
    /*
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
        graph     = new Graph();
        getPacks  = new JSONArray();
        verticies = new HashSet<String>();
        edges     = new ArrayList<String>();
        jo = null;
    }
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void constructGraph(String jsonFilepath) 
            throws FileNotFoundException, IOException, ParseException {
        
        Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
       
        jo = (JSONObject) obj;
        getPacks = (JSONArray) jo.get("packages");
        
        getAllPackages();
        
        for(String i : verticies)
            graph.addVertex(i);
        
        for(String i : edges)
            graph.addEdge(i.substring(0,1), i.substring(1));

        System.out.println(graph.getAllVertices());
        System.out.println(graph.getAdjacentVerticesOf("A"));
        System.out.println(graph.getAdjacentVerticesOf("B"));
        System.out.println(graph.getAdjacentVerticesOf("C"));
        System.out.println(graph.getAdjacentVerticesOf("D"));
    }
    
    /**
     * Helper method to get all packages in the graph.
     * @param packages 
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPackages() {
        int size  = getPacks.size();
        
        for(int i = 0; i < size; i++) {
            
            JSONObject temp = (JSONObject) getPacks.get(i);
            String packName = (String) temp.get("name");
            JSONArray packDepe = (JSONArray) temp.get("dependencies");
            
            if(!verticies.contains(packName))
                verticies.add(packName);
            
            for(int j = 0; j < packDepe.size(); j++) {
                String inTemp     = (String) packDepe.get(j);
                String dependency = packName+inTemp;
                
                if(!verticies.contains(inTemp))
                    verticies.add(inTemp);
                
                if(!edges.contains(dependency))
                    edges.add(dependency);
                
            }
            
        }
        
        return verticies;
    }
    
    /**
     * Given a package name, returns a list of packages in a
     * valid installation order.  
     * 
     * Valid installation order means that each package is listed 
     * before any packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    public List<String> getInstallationOrder(String pkg) 
            throws CycleException, PackageNotFoundException {
        return null;
    }
    
    /**
     * Given two packages - one to be installed and the other installed, 
     * return a List of the packages that need to be newly installed. 
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") 
     * If package A needs to be installed and packageB is already installed, 
     * return the list ["A", "C"] since D will have been installed when 
     * B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the dependencies of the given packages. If there is a cycle in some other
     * part of the graph that doesn't affect the parsing of these dependencies, 
     * cycle exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed 
     * do not exist in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg) 
            throws CycleException, PackageNotFoundException {
        return null;
    }
    
    /**
     * Return a valid global installation order of all the packages in the 
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install 
     * all the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>, order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages() throws CycleException {
        return null;
    }
    
    /**
     * Find and return the name of the package with the maximum number of dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file.  
     * The number of dependencies includes the dependencies of its dependencies.  
     * But, if a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.  
     * Then,  A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
        return "";
    }

    public static void main (String [] args) {
        System.out.println("PackageManager.main()");
        PackageManager manager = new PackageManager();
        
        try {
            String filepath = "/Users/akshaybodla/Documents/GitHub/CS400Projects/CS400Project04/src/shared_dependencies.json";
            manager.constructGraph(filepath);
            
            
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
