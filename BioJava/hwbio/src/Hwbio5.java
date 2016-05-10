import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Hwbio5 {
    public static void main(String[] argvs)
    {
        String line;
        String fileName = "java.txt";
        int nWords = 0;
        HashMap<String, Integer> uniqueWords = new HashMap<String, Integer>();
        
        System.out.println("Reading file: " + fileName);
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =  new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                line.trim();
                String[] words = line.split("\\s+");
                nWords += words.length;
                for (int i = 0; i < words.length; i++)
                {
                    Integer wc = uniqueWords.get(words[i]);
                    if (wc == null)
                        uniqueWords.put(words[i], new Integer(1));
                    else
                        uniqueWords.put(words[i], ++wc);
                }
            }   
            bufferedReader.close();    
            System.out.println("Total words: " + nWords);
            ValueComparator vc = new ValueComparator(uniqueWords);
            TreeMap sortedWords = new TreeMap(vc);
            sortedWords.putAll(uniqueWords);
            Set set = sortedWords.entrySet();
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
    static class ValueComparator implements Comparator {
    Map base;

    public ValueComparator(Map base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    @Override
    public int compare(Object a, Object b) {
        if ((Integer)base.get(a) >= (Integer)base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys

    }
    }
}
