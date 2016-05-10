// file: Run.java
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;

public class Run{

	// for debugging
	static void printList( ArrayList program ){
		int i;
		for( i = 0; i < program.size(); i++){
			
			System.out.printf( "%d%40s\n", program.get( i ), Integer.toBinaryString( (int) program.get(i) )  );
		}
}
	public static void main( String[] args ){
		// deserialize "binary" file, and pass program to cpu object for program execution
		try{
			ObjectInputStream input = new ObjectInputStream( new FileInputStream( "a.bin" ) );
			ArrayList<Integer> program = new ArrayList<Integer>();
			program = (ArrayList<Integer>) input.readObject();
			CPU cpu = new CPU( program, false );
			cpu.execute();
			try{ 
				if ( input != null ){ input.close(); } 
			} 
			catch ( IOException ioException ){ 
				System.err.println( "Error closing file." ); 
				System.exit(1); 
			}
		}
		catch (IOException ioException){
			
			System.err.println( "Error opening file.");
			System.exit(1);
		
		}
		catch ( ClassNotFoundException classNotFoundException ){
			
			System.err.println( "Class not found.");
			System.exit( 1 );
		}
		
	
	} // end main


} // end class