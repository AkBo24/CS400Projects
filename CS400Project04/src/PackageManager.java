import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * PackageManager.java created by akshaybodla on MacBook Pro in CS400Project04
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
 * Filename: PackageManager.java Project: p4 Authors:
 * 
 * PackageManager is used to process json package dependency files and provide
 * function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json
 * file.
 * 
 * Package dependencies are important when building software, as you must
 * install packages in an order such that each package is installed after all of
 * the packages that it depends on have been installed.
 * 
 * For example: package A depends upon package B, then package B must be
 * installed before package A.
 * 
 * This program will read package information and provide information about the
 * packages that must be installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test
 * classes.
 */

/**
 * {@summary} This class utilizes the @see Graph.java class to determine in what order
 * a package needs to be downloaded
 * 
 * Also provides other package functionalities
 * @author akshaybodla
 *
 */
@SuppressWarnings("unused")
public class PackageManager {

    private Graph graph; //The graph a package requires
    private List<String> graphVerts; //a local copy of the all the nodes, helpful for algorithms
    private JSONArray getPacks; //finds all of the packages in this json
    private JSONObject jo;
    
    //Not instantiated in constructor on purpose,
    //we do not know how big the graph is yet
    private String[] visited;

    /*
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
        graph = new Graph();
        graphVerts = new ArrayList<String>();
        getPacks = new JSONArray();
        jo = null;
    }

    /**
     * Takes in a file path for a json file and builds the package dependency graph
     * from it.
     * 
     * @param jsonFilepath the name of json data file with package dependency
     *                     information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException           if the give file cannot be read
     * @throws ParseException        if the given json cannot be parsed
     */
    public void constructGraph(String jsonFilepath)
            throws FileNotFoundException, IOException, ParseException {
        
        //parse the json file
        Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
        
        //cast to a json object and get get the dependencies into an array
        jo = (JSONObject) obj;
        getPacks = (JSONArray) jo.get("packages");

        //seperate the dependencies and add it to graph and graphVerts
        Set<String> verticies = getAllPackages();
        for(String i : verticies) {
            graph.addVertex(i);
            graphVerts.add(i);
        }
        
        //Construct the visited array list
        //initialize each position to "unvisisted"
        visited = new String[graphVerts.size()];
        for(int i = 0; i < visited.length; i++)
            visited[i] = "UNVISITED";
    }

    /**
     * Helper method to get all packages in the graph.
     * 
     * @param packages
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPackages() {
        
        //iterate through graphVerts and add it to packages
        //use a set so we dont add a duplicate
        Set<String> packages = new HashSet<String>();
        
        //for each element in getPacks get the name of the package
        //and add it to packages
        for(int i = 0; i < getPacks.size(); i++) {
            JSONObject currPkg = (JSONObject) getPacks.get(i);
            String pkgName = (String) currPkg.get("name");
            packages.add(pkgName);
            
            //each package has a dependency array so iterate through that
            JSONArray dependencies = (JSONArray) currPkg.get("dependencies");
            
            //iterate through the dependencies array and add each dependency of pkgName
            for(int j = 0; j < dependencies.size(); j++) {
                String currDpncy = (String) dependencies.get(j);
                packages.add(currDpncy);
                graph.addEdge(pkgName, currDpncy);
            }
        }
        return packages;
    }

    /**
     * Given a package name, returns a list of packages in a valid installation
     * order.
     * 
     * Valid installation order means that each package is listed before any
     * packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException           if you encounter a cycle in the graph while
     *                                  finding the installation order for a
     *                                  particular package. Tip: Cycles in some
     *                                  other part of the graph that do not affect
     *                                  the installation order for the specified
     *                                  package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the
     *                                  dependency graph.
     */
    public List<String> getInstallationOrder(String pkg)
            throws CycleException, PackageNotFoundException {
        
        //first check to see if this pkg is in the graph
        //throw package not found if it is not found
        boolean isFound = false;

        for(String i : graphVerts) {
            if(i.equals(pkg)) isFound = true;
        }
        
        if(!isFound) throw new PackageNotFoundException();
        
        //Add to a set since they, by default, do not allow duplicate values
        List<String> installation = new ArrayList<String>();
        List<String> currDpn      = graph.getAdjacentVerticesOf(pkg);
        
        //Get dependencies finds the dependencies of this pkg and creates the installation list
        //recursively (uses a modified DFS algorithm)
        getDependencies(pkg, installation);
        
        //reset the visited array
        for(int i = 0; i < visited.length; i++)
            visited[i] = "UNVISITED";
        
        //return everything inside of installation by creating a new ArrayList
        return installation;
    }

    private void getDependencies(String pkg, List<String> installation) throws CycleException {
        //Get all the dependencies of the curr packages
        List<String> currDep = graph.getAdjacentVerticesOf(pkg);
        
        int pkgIndx = graphVerts.indexOf(pkg);
        visited[pkgIndx] = "IN PROGRESS";
        
        //For each dependency find if it has any others
        for(String i : currDep) {
            int iStatus = graphVerts.indexOf(i);
            
            //Dependencies with the "in progress" flag indicate there is a cycle 
            //inside of this package list
            if(visited[iStatus].equals("IN PROGRESS")) throw new CycleException();
            
            //Find the dependencies the package "i" has
            getDependencies(i, installation);
        }
       
        visited[pkgIndx] = "VISITED";
        
        //Only add this package if it is not in the installation order
        if(!installation.contains(pkg))
            installation.add(pkg);
    }

    /**
     * Given two packages - one to be installed and the other installed, return a
     * List of the packages that need to be newly installed.
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") If package
     * A needs to be installed and packageB is already installed, return the list
     * ["A", "C"] since D will have been installed when B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException           if you encounter a cycle in the graph while
     *                                  finding the dependencies of the given
     *                                  packages. If there is a cycle in some other
     *                                  part of the graph that doesn't affect the
     *                                  parsing of these dependencies, cycle
     *                                  exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed do not exist
     *                                  in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg)
            throws CycleException, PackageNotFoundException {
        
        //first check that each package is inside the graph
        //if one of them aren't throw package not found
        boolean nPFound = false;
        boolean iPFound = false;
        
        for(String i : graphVerts) {
            if(i.equals(newPkg)) nPFound = true;
            if(i.equals(installedPkg)) iPFound = true;
        }
        
        if(!nPFound && !iPFound) throw new PackageNotFoundException();
        
        
        //Get two sets for each installation then do a symmetric difference to 
        //eliminate the common packages (since they are already installed)
        //the packages left over are the unique dependencies of newPkg
        Set<String> alreadyInst = new HashSet<String>(getInstallationOrder(installedPkg));
        Set<String> toInstall   = new HashSet<String>(getInstallationOrder(newPkg));
        
        //Get the symmetric difference of alreadyInst and toInstall
        //this will leave the packages left to install from getInstallationOrder(newPkg)
        Set<String> symmetricDiff = new HashSet<String>(alreadyInst);
        symmetricDiff.addAll(toInstall);
        Set<String> temp = new HashSet<String>(alreadyInst);
        symmetricDiff.retainAll(toInstall);
        symmetricDiff.removeAll(temp);
                
        return new ArrayList<String>(symmetricDiff);
    }

    /**
     * Return a valid global installation order of all the packages in the
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install all
     * the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>, order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages() throws CycleException {
        // Choose arbitrary starting vertex & do a topological ordering algorithm
        List<String> installOrder = new ArrayList<String>();
        
        //Iterate through each vertex (using graphVerts) and find its installation path
        //add each to install order
        for(String i : graphVerts)
            getDependencies(i, installOrder);
        
        return installOrder;
    }

    /**
     * Find and return the name of the package with the maximum number of
     * dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file. The
     * number of dependencies includes the dependencies of its dependencies. But, if
     * a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.
     * Then, A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
        int[] numDep = new int[this.graphVerts.size()]; //hold the number of dependencies of each vertex
        int   maxDep = 0, maxPos = 0; //used later to store the max number of dependencies
        
        for(int i = 0 ; i < graphVerts.size(); i++) {
            try {
                //for each dependency get the installation order and the size of the
                //returned list
                numDep[i] = getInstallationOrder(graphVerts.get(i)).size();
            } catch (PackageNotFoundException e) {
                // TODO Auto-generated catch block
            }
        }
        
        //iterate through numDep to find the package w/the largest dependency
        for(int i = 0; i < numDep.length; i++) {
            
            //if the package represented by numDep[i] is larger than the curr num of Dep 
            //replace it with the current dependency
            if(Math.max(maxDep, numDep[i]) == numDep[i]) {
                maxDep = numDep[i];
                maxPos = i;
            }
        }
        
        //return the package with the largest number of dependencies
        return graphVerts.get(maxPos);
    }

}
