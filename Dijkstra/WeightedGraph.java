public class WeightedGraph 
{
 
    private int [][]  edges;  // use adjacency matrix to implement the graph
    private Object [] cities;
 
    public WeightedGraph (int n) {
       edges  = new int [n][n];
       cities = new String[n];
    }
 
 
    public int size() 
    { 
    	return cities.length; 
    }
 
    public void   setCity (int vertex, String city) 
    { 
    	cities[vertex]=city; 
    }
    public Object getCity (int vertex)               
    { 
    	return cities[vertex]; 
    }
 
    public void addConnection (int source, int target, int w)  
    {
    	edges[source][target] = w; 
    }
    public int     getPrice  (int source, int target)  
    { 
    	return edges[source][target]; 
    }
 
    public int [] getNeighbors (int vertex) 
    {
       int count = 0;
       for (int i=0; i<edges[vertex].length; i++) 
       {
          if (edges[vertex][i]>0) count++;
       }
       final int[] answer = new int[count];
       count = 0;
       for (int i=0; i<edges[vertex].length; i++) 
       {
       		if (edges[vertex][i]>0) 
          		answer[count++]=i;
       }
       return answer;
    }
 
    
 
    
 
}