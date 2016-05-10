// file: Memory.java
import java.util.Arrays;

public class Memory{

	private int[] memArray;
	
	// Constructor
	public Memory(){
		
		memArray = new int[1000];
		Arrays.fill( memArray, 0 );
	} // end constructor
	
	public Memory( int size ){
	    
		/* Make sure size is not too small. */
		if( size < 100 ){
			size = 1000;
		}
		memArray = new int[size];
		Arrays.fill( memArray, 0 );
	} // end constructor
	
	/* Method to get value in memory array at specified index */
	public int getValue( int index ){
		
		if( index >= memArray.length || index < 0 ){ // out of bounds
		
			throw new MemIndexOutOfBoundsException( "Program is attempting to read a non-existing memory address: "
												+ Integer.toString( index ) );
			
		}
		
		return memArray[index];
		
	} // end getValue()
	
	/* Method to set value in memory array at specified index */
	public void setValue( int index, int value ){
	
		if ( index >= memArray.length || index < 0 ){ // out of bounds
			
			throw new MemIndexOutOfBoundsException( "Program is attempting to write at the non-existing memory address: "
												+ Integer.toString( index ) );
									
		}
		
		memArray[index] = value;
	
	}
	
	public int getSize(){
		
		return memArray.length;
		
	}

}