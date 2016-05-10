// Assembler.java
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Assembler{

	
	// for debugging.
	static void printList( ArrayList program ){
		
		for( int i = 0; i < program.size(); i++){
			
			System.out.printf( "%d%40s\n", program.get( i ), Integer.toBinaryString( (int) program.get(i) )  );
		}
	}

	public static void main( String[] args ){
		
		ArrayList<Integer> program = new ArrayList<Integer>();
		Scanner input = new Scanner( System.in );
		ObjectOutputStream output;
		
		// get path of source code file; load file; parse instructions into integer format; load
		// each into array; serialize array object into "binary" file.
		Path source = Paths.get( args[0] );
		Scanner s = null;
        try {
			s = new Scanner( source );

            while ( s.hasNextLine() ) {
				String line = new String();
				line = s.nextLine();
				if( !program.add( Parser.parse( line ) ) ){ 
					System.err.println("Could not add element to linked list.");
				}
				
            } //end while-loop
			
        }
		catch( IOException ioException ){
			
			System.err.printf("Could not open source file. Enter a number to exit.");
			while ( !input.hasNextInt() ){
				
					input.next();
			}
			System.exit( 1 );
			
		} // end catch
		finally {
            if (s != null) {
                s.close();
            }
        }
		
		// take list of instructions and build serialized object file
		try{  
   
            output = new ObjectOutputStream( 
					new FileOutputStream( "a.bin" ) );
		
			output.writeObject( program );
			if ( output != null )
					output.close();
		} 
		
		catch ( IOException ioException ){ 
		
			System.err.println( "Error " );
			System.exit( 1 );
			
        }		
		
	} // end main

}