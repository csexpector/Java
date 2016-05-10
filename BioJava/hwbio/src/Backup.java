import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Backup {
    public static void main(String[] argvs)
    {
        String line;
        String fileName = argvs[0];
        int nWords = 0;
        HashMap<String, Integer> uniqueWords = new HashMap<String, Integer>();
        
        System.out.println("Reading file: " + fileName);
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =  new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                nWords += words.length;
                for (int i = 0; i < words.length; i++)
                {
                    Integer wc = uniqueWords.get(words[i]);
                    if (wc == null)
                        uniqueWords.put(words[i], new Integer(0));
                    else
                        uniqueWords.put(words[i], wc++);
                }
            }   
            bufferedReader.close();    
            Set set = uniqueWords.entrySet();
            Iterator it = set.iterator();
            while(it.hasNext()) 
            {
                Map.Entry me = (Map.Entry)it.next();
                System.out.print(me.getKey() + ": ");
                System.out.println(me.getValue());
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file ");  
        }
    }
}
