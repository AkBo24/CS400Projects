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

@SuppressWarnings("unused")
public class PackageManager {

    private Graph graph;
    private List<String> graphVerts;
    private JSONArray getPacks;
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

        Object obj = new JSONParser().parse(new FileReader(jsonFilepath));

        jo = (JSONObject) obj;
        getPacks = (JSONArray) jo.get("packages");

        Set<String> verticies = getAllPackages();
        for(String i : verticies) {
            graph.addVertex(i);
            graphVerts.add(i);
        }
        
        //Construct the visited array list
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
        Set<String> packages = new HashSet<String>();
        
        for(int i = 0; i < getPacks.size(); i++) {
            JSONObject currPkg = (JSONObject) getPacks.get(i);
            String pkgName = (String) currPkg.get("name");
            packages.add(pkgName);
            JSONArray dependencies = (JSONArray) currPkg.get("dependencies");
            
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
        boolean isFound = false;

        for(String i : graphVerts) {
            if(i.equals(pkg)) isFound = true;
        }
        
        if(!isFound) throw new PackageNotFoundException();
        
        //Add to a set since they, by default, do not allow duplicate values
        List<String> installation = new ArrayList<String>();
        List<String> currDpn      = graph.getAdjacentVerticesOf(pkg);
        
        //Get dependencies finds the dependencies of this pkg and creates the installation list
        //recursively
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
//        graph.setMark(pkg, "IN PROGRESS");
        
        //For each dependency find if it has any others
        for(String i : currDep) {
            int iStatus = graphVerts.indexOf(i);
//            if(graph.getMark(i).equals("IN PROGRESS")) throw new CycleException();
            if(visited[iStatus].equals("IN PROGRESS")) throw new CycleException();
            
            getDependencies(i, installation);
        }
        
//        graph.setMark(pkg, "VISITED");
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
        int[] numDep = new int[this.graphVerts.size()];
        int   maxDep = 0, maxPos = 0;
        
        List<String> currDepen;
        
        for(int i = 0 ; i < graphVerts.size(); i++) {
            try {
                numDep[i] = getInstallationOrder(graphVerts.get(i)).size();
            } catch (PackageNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        //iterate through numDep to find the package w/the largest dependency
        for(int i = 0; i < numDep.length; i++) {
            if(Math.max(maxDep, numDep[i]) == numDep[i]) {
                maxDep = numDep[i];
                maxPos = i;
            }
        }
        
        return graphVerts.get(maxPos);
    }

    public static void main(String[] args) {
        System.out.println("PackageManager.main()");
        PackageManager manager = new PackageManager();

        try {
            String filepath = "/Users/akshaybodla/Documents/GitHub/CS400Projects/CS400Project04/src/shared_dependencies.json";
            manager.constructGraph(filepath);

            manager.getInstallationOrder("A");
//            manager.toInstall("A", "C");
            
            manager.getInstallationOrderForAllPackages();
            
            manager.getPackageWithMaxDependencies();

        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
