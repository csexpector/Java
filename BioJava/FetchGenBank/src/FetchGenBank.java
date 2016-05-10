import org.biojava.bio.BioException;
import org.biojavax.bio.db.ncbi.GenbankRichSequenceDB;
import org.biojavax.bio.seq.RichSequence;
import java.util.Scanner;
 
public class FetchGenBank {
   
     public static void main(String[] args) {
             RichSequence rs = null;
             GenbankRichSequenceDB grsdb = new GenbankRichSequenceDB();
             Scanner input = new Scanner(System.in);

             try{
             // get data via GenBank accession number or gi number
                 System.out.println("Enter a GenBank accession number or gi number: ");
                 String id = input.nextLine();

                 rs = grsdb.getRichSequence(id);
                 System.out.println(rs.getName()+" | "+rs.getDescription());
                 System.out.println(rs.seqString());
             }
             catch(BioException be){
                 be.printStackTrace();
                 System.exit(-1);
             }
     }
}