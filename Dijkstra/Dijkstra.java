public class Dijkstra 
{
// Dijkstra's algorithm to find shortest path from s to all other nodes
	public static int [] findShortestPath (WeightedGraph G, String[] labels, int s) 
	{
		// this array stores shortest known distance from starting vertex
		final int [] dist = new int [G.size()];  
		// this array will be used to store preceeding node in path
		final int [] pred = new int [G.size()];  
		// this array store visiting status of vertices, true means we have visited this vertex, 
		// false means we have not visited it yet
		final boolean [] visited = new boolean [G.size()]; 
	 
		// First, initiate all vertex with infinite values
		for (int i=0; i<dist.length; i++) 
		{
	    	dist[i] = Integer.MAX_VALUE;
	    	pred[i] = -1;
	    	visited[i] = false;
	    }
		
		// the shortest distance from s to s of course is zero (assume that weights are positive)
		dist[s] = 0;

		// Print initial table
		printTable("Initial state", dist, pred, visited, labels, G.size());

		// let follow the algorithm
		// visit each vertex of graph
		for (int i=0; i<dist.length; i++) 
		{
			// find the adjacent vertex which has the shortest path to s
			final int next = getSmallestVertex (dist, visited);
			visited[next] = true;

			// The shortest path to next is dist[next] and via pred[next].

			final int [] n = G.getNeighbors (next);
			for (int j=0; j<n.length; j++) 
			{
				final int v = n[j];
				final int d = dist[next] + G.getPrice(next,v);
				if (dist[v] > d) 
				{
					dist[v] = d;
					pred[v] = next;
				}
			}

			printTable("Visiting " + labels[next], dist, pred, visited, labels, G.size());
		}
		
		return pred;  
    }
    /* Find the smallest unknown distance vertex */
    // return vertex index
 
    private static int getSmallestVertex (int [] dist, boolean [] v) {
       int x = Integer.MAX_VALUE;
       // graph not connected, or no unvisited vertices
       int y = -1;   
       for (int i=0; i<dist.length; i++) 
       {
			if (!v[i] && dist[i]<x) 
			{
				y=i; x=dist[i];
			}
       }
       return y;
    }
 
 	// print a shortest path from vetex s to vertex e
    public static void printPath (WeightedGraph G, int [] pred, int s, int e) 
    {
		final java.util.ArrayList path = new java.util.ArrayList();
		int x = e;
		int sumw = 0;
		// we go back from end to start, at each node store its precedent node until we meet start
		while (x!=s) 
		{
		  path.add (0, G.getCity(x));
		  sumw += G.getPrice(pred[x], x); // sum all the weight on the way
		  x = pred[x];
		}
		// print the price (sum of weights)
		System.out.println("Price: " + sumw);
		// print connections (size of path - 1)
		System.out.println("Connections: " + (path.size() - 1));
		// add the last node to path
		path.add (0, G.getCity(s));


		// now print the path from start to end
		System.out.print(path.get(0) );

		for (int i = 1; i < path.size(); i++)
			System.out.print(" -> " + path.get(i));
		System.out.println ();
    }
 
 	// print current table
 	public static void printTable(String tblName, int[] dist, int [] pred, boolean[] visited, String[] labels, int n)
 	{
 		System.out.println(tblName);
 		System.out.format("%10s%10s%10s%10s\n", "v", "known", "dv", "pv");
 		for (int v = 0; v < n; v++)
 		{
 			System.out.format("%10s", labels[v]);
 			System.out.format("%10s", String.valueOf(visited[v]));
 			if (dist[v] != Integer.MAX_VALUE)
 				System.out.format("%10d", dist[v]);
 			else
 				System.out.format("%10s", "INF");
 			if (pred[v] != -1)
 				System.out.format("%10s\n", labels[pred[v]]);
 			else
 				System.out.format("%10s\n", "0");
 		}

 	}
 }