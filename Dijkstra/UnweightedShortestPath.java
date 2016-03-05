import java.util.*;

class UnweightedShortestPath
{
	private static final int INFINITY = Integer.MAX_VALUE;
	// this array stores distance from vertex s to other vertex
	private static int[] dist; 
	// this array store the shortest path from vertex s to anyother vertex
	// path[w] = v means v is adjacent to w in the path from s to w (s -> ... -> v -> w -> ...)
	// path[s] = -1 because s is the starting vertex
	private static int[] path;
	// this array stores the visiting status of vertex while finding the shortest path
	private static boolean[] visited;
	// number of vertices in graph 
	//private int nVertex;
	// Find Unweighted Shortest Path
	// parameter adjmatrix: Adjacent Matrix for the graph
	// parameter lables: Label of each vertex
	// parameter s: the starting vertex
	// paramenter n: number of vertices in graph
	public static void findShortestPath(int adjmatrix[][], String[] labels, int s, int n)
	{
		initiateDistanceAndPath(n);
		Queue<Integer> q = new LinkedList<Integer>();

		// set distance to s = 0
		dist[s] = 0;
		// enqueue s
		q.add(new Integer(s));

		// print initial table
		printTable("Initial State", q, labels, n);

		while (q.size() != 0)
		{
			// dequeue
			int v = Integer.valueOf(q.remove());			
			// set v is visited
			visited[v] = true;
			
			// walk through adjacent vertices
			for (int w = 0; w < n; w++)
			{
				// search for adjacent of v
				// w is adjacent of v if the value at [v, w] is not zero
				
				if (adjmatrix[v][w] != 0) // w is adjacent of v: v ----> w
				{
					if (dist[w] == INFINITY)
					{
						dist[w] = dist[v] + 1;
						path[w] = v;
						// enqueue w
						q.add(new Integer(w));
					}
				}
			}
			// print dequeue table
			printTable(labels[v] + " Dequeued", q, labels, n);
		}
	}
	public static void main(String[] args)
	{
		int[][] adjmatrix914 = {{0, 1, 0, 1, 0, 0, 0},  // v1
								{0, 0, 0, 1, 1, 0, 0},  // v2
								{1, 0, 0, 0, 0, 1, 0},  // v3
								{0, 0, 1, 0, 1, 1, 1},  // v4
								{0, 0, 0, 0, 0, 0, 1},  // v5
								{0, 0, 0, 0, 0, 0, 0},  // v6
								{0, 0, 0, 0, 0, 1, 0}}; // v7 
		String[] labels = {"v1", "v2", "v3", "v4", "v5", "v6", "v7"};
		// find shortest path from v3 (index is 2)
		findShortestPath(adjmatrix914, labels, 2, labels.length);

		/*
			 TODO: 	- create adjacent matrix for 9.82
					- set correct labels
					- call findShortestPath to find shortest path
		*/
	}
	public static void initiateDistanceAndPath(int n)
	{
		dist = new int[n];
		path = new int[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++)
		{
			dist[i] = INFINITY;
			visited[i] = false;
			path[i] = -1;
		}
	}
	// tblName: table name
	// q: current queue
	// labels: vertex labels
	// n: number of vertices in the graph
	public static void printTable(String tblName, Queue<Integer> q, String[] labels, int n)
	{
		System.out.println("----------------------------------------------");
		System.out.println("              " + tblName + "                 ");
		System.out.println("----------------------------------------------");
		System.out.format("%10s %10s %10s %10s\n", "v", "known", "dv", "pv");
		for (int v = 0; v < n; v++)
		{
			System.out.format("%10s ", labels[v]);
			System.out.format("%10s ", String.valueOf(visited[v]));
			if (dist[v] != INFINITY)
				System.out.format("%10d ", dist[v]);
			else
				System.out.format("%10s ", "INF");
			if (path[v] != -1)
				System.out.format("%10s \n", labels[path[v]]);
			else
				System.out.format("%10s \n", "0");
		}
		System.out.print("Queue: ");
		for (int i = 0; i < q.size(); i++)
		{
			int v = ((LinkedList<Integer>)q).get(i);
			System.out.format("%3s", labels[v]);
		}
		System.out.println();
	}
}