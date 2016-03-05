import java.util.*;

class TopoQueue
{
	public static Queue topsort(int adjmatrix[][], int n)
	{
		// This queue is used for sorting algorithm
		Queue<Integer> q = new LinkedList<Integer>();
		// This queue is used to stored sorted vertices
		Queue<Integer> sorted = new LinkedList<Integer>();
		
		int counter = 0;
		for (int i = 0; i < n; i++)
		{
			// looking for vertices with in-degree = 0
			// TODO1: write if commend
			if (inDegree(i, adjmatrix, n) == 0)  
			{
				// every time before we enqueue, print all in in-degree of vertices to sorting table
				// TODO2: call printInDegree method
				printInDegree(adjmatrix, n);

				// enqueue
				q.add(new Integer(i)); // push all vertices that has 0 in-degree to the queue

				
			}
		}

		while (q.size() != 0)
		{
			// dequeue
			int v = Integer.valueOf(q.remove());
			// put to sorted
			sorted.add(new Integer(v));

			
			counter++;

			// walk through adjacent vertices
			for (int w = 0; w < n; w++)
			{
				// search for adjacent of v
				// w is adjacent of v if the value at [v, w] is not zero
				// TODO3: write if command
				if (adjmatrix[v][w] != 0) // w is adjacent of v: v ----> w
				{
					adjmatrix[v][w]--;
					if (inDegree(w, adjmatrix, n) == 0)
					{
						// print in-degree before enqueu
						printInDegree(adjmatrix, n);
						// enqueue
						q.add(new Integer(w));
						
					}
				}
			}
		}

		if (counter != n)
		{
			System.out.println("Graph is not acyclic!");
			return null;
		} 

		return sorted;
	}
	// This function is to return in-degree of v
	// in adjacent matrix, in-degree of v is the sum of column v
	public static int inDegree(int v, int adjmatrix[][], int n)
	{
		int indegree = 0;
		
		for (int i = 0; i < n; i++)
		{
			// TODO4: write the command to calculate sum of column v to indegree
			indegree += adjmatrix[i][v];
		}
		
		return indegree;
	}
	public static void printInDegree(int adjmatrix[][], int n)
	{
		for (int i = 0; i < n; i++)
		{
			System.out.print("deg(v" + (i+1) + ") = " + inDegree(i, adjmatrix, n) + " ");
		}
		System.out.println();
	}
	public static void printSorted(Queue<Integer> q)
	{
		System.out.println(" +++ Sorted vertices: +++");
		while (q.size() != 0)
		{
			int v = Integer.valueOf(q.remove());
			if (q.size() != 0)
				System.out.print("v" + (v+1) + " -> ");
			else
				System.out.print("v" + (v+1));
		}
		System.out.println();
	}
	public static void main(String[] args)
	{
		
		int adjmatrix94[][] = {{0, 1, 1, 1, 0, 0, 0},		// v1
							 {0, 0, 0, 1, 1, 0, 0},			// v2
							 {0, 0, 0, 0, 0, 1, 0},			// v3
							 {0, 0, 1, 0, 0, 1, 1},			// v4
							 {0, 0, 0, 1, 0, 0, 1},			// v5 <== TODO5: correct this line (should not all zeros like this)
							 {0, 0, 0, 0, 0, 0, 0},			// v6
							 {0, 0, 0, 0, 0, 1, 0}};		// v7
		
		System.out.println(" ++++++++++ Graph 9.4 +++++++++");
		System.out.println(" +++ Sorting table (in row) +++ ");
		Queue sorted = topsort(adjmatrix94, 7);
		if (sorted != null) printSorted(sorted);

							//   s  A  G  D  B  E  H  C  F  I  t 
		int adjmatrix981[][] = {{0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},  // s
								{0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},  // A
								{0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0},  // G
								{0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},  // D
								{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},  // B
								{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},  // E 
								{0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},  // H <== TODO6: correct this line (should not all zeros like this)
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},  // C
								{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},  // F
								{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},  // I
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}; // t
		System.out.println(" ++++++++++ Graph 9.81 +++++++++");
		System.out.println(" +++ Sorting table (in row) +++ ");
		sorted = topsort(adjmatrix981, 11);
		if (sorted != null) printSorted(sorted);

		
	}
}

















// TODO1: if (inDegree(i, adjmatrix, n) == 0) 



// TODO2: printInDegree(adjmatrix, n);





// TODO3: if (adjmatrix[v][w] != 0)





// TODO4: indegree += adjmatrix[i][v];




// TODO5: {0, 0, 0, 1, 0, 0, 1},




// TODO6: {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0}