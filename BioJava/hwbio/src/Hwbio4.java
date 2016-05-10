import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author dttung
 */
public class Hwbio4 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String dnaFile = "dna.txt";
        FileWriter fileWriter = new FileWriter(dnaFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        while (true)
        {
            System.out.println("Enter a GenBank accession number or gi number: ");
            String id = input.nextLine();
            if (id.equals("exit")) break;
            System.out.println("Enter DNA sequence: ");
            String dna = input.nextLine();
            saveToFile(bufferedWriter, id, dna);
        }
        bufferedWriter.close();
        readFromFile();
        System.out.println("Bye!");
    }
    public static void saveToFile(BufferedWriter bufferedWriter, String id, String dna)
    {       
        try {          
            bufferedWriter.write(">");
            bufferedWriter.write(id);
            bufferedWriter.newLine();
            bufferedWriter.write(dna);
            bufferedWriter.newLine();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file");
        }
    }
    
    public static void readFromFile()
    {
        String line;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =  new FileReader("dna.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            } 
            // Close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file ");  
        }
    }
}
