import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class AdjGraph implements Graph {
 private boolean digraph;
 private int totalNode;
 private Vector<String> nodeList;
 private int totalEdge;
 private Vector<LinkedList<Integer>>  adjList; //Adjacency list
 private Vector<Boolean> visited;
 private Vector<Integer> nodeEnum; //list of nodes pre visit
 private Vector<Integer> components; // list of components
 public AdjGraph() {
  init();
 }
 public AdjGraph(boolean ifdigraph) {
  init();
  digraph =ifdigraph;
 }
 public void init() {
  nodeList = new Vector<String>(); 
  adjList = new Vector<LinkedList<Integer>>(); 
  visited = new Vector<Boolean>();
  nodeEnum = new Vector<Integer>();
  totalNode = totalEdge = 0;
  digraph = false;
 }
 //set vertices
 public void setVertex(String[] nodes) {
  for(int i = 0; i < nodes.length; i ++) {
   nodeList.add(nodes[i]);
   adjList.add(new LinkedList<Integer>());
   visited.add(false);
   totalNode ++;
  }
 }
 //add a vertex
 public void addVertex(String label) {
  nodeList.add(label);
  visited.add(false);
  adjList.add(new LinkedList<Integer>());
  totalNode ++;
 }
 public int getNode(String node) {
  for(int i = 0; i < nodeList.size(); i ++) {
   if(nodeList.elementAt(i).equals(node)) return i;
  }
  return -1;
 }
 //return the number of vertices
 public int length() {
  return nodeList.size();
 }
 //add edge from v1 to v2
 public void setEdge(int v1, int v2, int weight) {
  LinkedList<Integer> tmp = adjList.elementAt(v1);
  if(adjList.elementAt(v1).contains(v2) == false) {
   tmp.add(v2);
   adjList.set(v1,  tmp);
   totalEdge ++;
  }
 }
 public void setEdge(String v1, String v2, int weight) {
  if((getNode(v1) != -1) && (getNode(v2) != -1)) {
   //add edge from v1 to v2
   setEdge(getNode(v1), getNode(v2), weight);
   //for digraph, add edge from v2 to v1 as well
   if(digraph == false) setEdge(getNode(v2), getNode(v1), weight);
  }
 }

 //it is important to keep track if a vertex is visited or not (for traversal)
 public void setVisited(int v) {
  visited.set(v, true);
  nodeEnum.add(v);
 }
 public boolean ifVisited(int v) {
  return visited.get(v);
 }
 public void clearWalk() {
  //clean up before traverse
  nodeEnum.clear();
  for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
 }
 public void walk(String method) {
  clearWalk();
  //traverse the graph 
  for(int i = 0; i < nodeList.size(); i ++) {
   if(ifVisited(i) == false) {
    if(method.equals("BFS")) BFS(i); //i as the start node
    else if(method.equals("DFS")) DFS(i); //i as the start node
    else {
     System.out.println("unrecognized traversal order: " + method);
     System.exit(0);
    }
   }
  }
  System.out.println(method + ":");
  displayEnum();
 }
 public void walk2(String method) {
	 components = new Vector<Integer>();
	 int nElements = 0;
	  clearWalk();
	  
	  //traverse the graph 
	  for(int i = 0; i < nodeList.size(); i ++) {
		  
	   if(ifVisited(i) == false) {
		components.add(0);
		
	    if(method.equals("BFS")) nElements = BFS(i); //i as the start node
	    else if(method.equals("DFS")) nElements = DFS(i); //i as the start node
	    else {
	     System.out.println("unrecognized traversal order: " + method);
	     System.exit(0);
	    }
	    //int nNewElements = nodeEnum.size() - nElements;
	    //nElements = nNewElements;
	    components.setElementAt(new Integer(nElements), components.size()-1) ;
	   }
	  }
	  System.out.println(method + ":");
	  displayEnum();
	 }
 public int DFS(int v) {
	 int nVisit = 1;
  setVisited(v);
  LinkedList<Integer> neighbors = adjList.elementAt(v);
  for(int i = 0; i < neighbors.size(); i ++) {
   int v1 = neighbors.get(i);
   if(ifVisited(v1) == false) { 
	   nVisit += DFS(v1);
	   }
  }
  return nVisit;
 }
 public int BFS(int s) {
	 int nVisit = 0;
  ArrayList<Integer> toVisit = new ArrayList<Integer>();
  toVisit.add(s);
  while(toVisit.size() > 0) {
   int v = toVisit.remove(0); //first-in, first-visit
   setVisited(v);
   nVisit++;
   LinkedList<Integer> neighbors = adjList.elementAt(v);
   for(int i = 0; i < neighbors.size(); i ++) {
    int v1 = neighbors.get(i);
    if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
     toVisit.add(v1);
    }
   }
  }
  return nVisit;
 }
 public void display() {
  System.out.println("total nodes: " + totalNode);
  System.out.println("total edges: " + totalEdge);
 }
 public void displayEnum() {
  for(int i = 0; i < nodeEnum.size(); i ++) {
   System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
  }
  System.out.println();
 }
 public static void main(String[] args)
 {
	 AdjGraph g = new AdjGraph();
	 // Setup g
	 String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H"};
	 g.setVertex(vertices);
	 g.setEdge(0, 1, 0); // edge AB
	 g.setEdge(1, 2, 0); // edge BC
	 g.setEdge(2, 3, 0); // edge CD
	 g.setEdge(3, 0, 0); // edge DA
	 g.setEdge(4, 5, 0); // edge EF
	 g.setEdge(5, 6, 0); // edge FG
	 g.setEdge(6, 4, 0); // edge GE
	 g.display();
	 // End setup g
	 /*
	  * To test a graph as in assignment (5 vertices, 2 edges), use following lines and comments
	  * out the setup above
	  * 
	  * String[] vertices = {"A", "B", "C", "D", "E"};
	  * g.setVertex(vertices);
	  * g.setEdge(0, 1, 0); // edge AB
	  * g.setEdge(1, 2, 0); // edge BC
	  * g.display();
	  */
	 g.walk2("DFS"); // can use BFS as well
	 System.out.println("Total components: " + g.components.size());
	 for (int i = 0; i < g.components.size(); i++)
	 {
		 System.out.println("Component " + i + " contains " + g.components.elementAt(i) + " nodes");
	 }
 }
}