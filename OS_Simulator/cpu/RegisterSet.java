// file: RegisterSet.java

import java.util.Arrays;

public class RegisterSet{

	private int[] registers;
	
	
	/* Constructor */
	public RegisterSet(){
	
		registers = new int[32];
		Arrays.fill( registers, 0 );
		
	} // end constructor
	
	/* Set value of specified register */
	public void setValue( int index, int value ){
		
		if( index == 0 ){
			
			throw new RegisterIllegalArgumentException( "Program attempting to write REG-0" );
			
		}
		
		if( index > 31 ){
			
			throw new RegisterIllegalArgumentException( "Program attempting to write non-existing register-" 
														+ Integer.toString( index ) );
		}
		registers[index] = value;
	} // end setValue()
	
	/* Get value of specified register */
	public int getValue( int index ){
	
		if( index < 0 || index > 31 ){
		
			throw new RegisterIllegalArgumentException( "Program attempting to read non-existing register-"
														+ Integer.toString( index ) );
		}
		return registers[index];
	
	} // end getValue()
	
	/* Reset all registers to 0 */
	public void reset(){
		
		Arrays.fill( registers, 0 );
	
	} // end reset()
	

} // end class