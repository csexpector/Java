import java.io.*;
import java.util.*;

class TestDijkstra
{      
      public static void main (String args[]) throws IOException
      {
             WeightedGraph t = new WeightedGraph(0);
      
            // TODO: create the file graph982 with format look like 920
            // then replace the file name to graph982 in the command bellow
            String fileName = "graphJa.txt";
            String[] vertexlines = new String[1000];
            String[] vertices = new String[0];
            // This will reference one line at a time
            String line = null;

            // Read input file
            // input file contains n lines corresponding to n vertices
            // v1 v2 4  v4 6
            // v4
            // v5 v1 3
            // That means: v1 -> v2 with weight = 4, v1 -> v4 with weight = 6
            // v4 has no adjacent vertex
            // v5 -> v1 with weight = 3
            try {
                  // FileReader reads text files in the default encoding.
                  FileReader fileReader = new FileReader(fileName);

                  // Always wrap FileReader in BufferedReader.
                  BufferedReader bufferedReader = new BufferedReader(fileReader);
                  int nVertex = 0;
                  while((line = bufferedReader.readLine()) != null) {
                        vertexlines[nVertex] = line;
                        nVertex++;
                  }
                  bufferedReader.close(); 
                  t = new WeightedGraph(nVertex);
                  vertices = new String[nVertex];
                  for (int i = 0; i  < nVertex; i++)
                  {
                        StringTokenizer tokenizer = new StringTokenizer(vertexlines[i]);
                        // 1st word in line is the city
                        String city = tokenizer.nextToken();
                        vertices[i] = city;
                        t.setCity(i, city);
                  }

                  for (int vid = 0; vid < nVertex; vid++)
                  {
                        StringTokenizer tokenizer = new StringTokenizer(vertexlines[vid]);
                        // 1st word in line is the city
                        String city = tokenizer.nextToken();
                        
                        
                        while(tokenizer.hasMoreTokens()) {
                              String adjCity = tokenizer.nextToken();
                              int    adjWeight = Integer.valueOf(tokenizer.nextToken()); 
                              // find adjid
                              int adjid = 0;
                              for (; adjid < nVertex; adjid++)
                              {
                                    if (vertices[adjid].equals(adjCity)) break;
                              }
                              t.addConnection(vid, adjid, adjWeight);   
                                                     
                        }
                  }    

                  // Always close files.
                  bufferedReader.close();            
            }
            catch(FileNotFoundException ex) {
                  System.out.println("Unable to open file '" + fileName + "'");                
            }
            catch(IOException ex) 
            {
                  System.out.println("Error reading file '"  + fileName + "'");                   
            }

            // Now, content of input file is stored in the graph
            // Find shortest path from start city
            // TODO: set labels to corresponding vertices of graph 982, also set sid to correct vertex A of graph 982
            int sid = 4; // v1
            String[] labels = {"SF", "SLC", "LA", "SD", "DA", "HOU", "ATL", "CHI", "MIA", "NYC"};
            final int [] pred = Dijkstra.findShortestPath (t, labels, sid);
            
      }
}