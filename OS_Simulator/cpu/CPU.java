// file: CPU.java
import java.util.Scanner;
import java.util.ArrayList;

public class CPU{

	private int ip = 0; // instruction pointer
	private final int workingMemoryIndex; // index of first free memory location.
	private RegisterSet regs = new RegisterSet();
	private Memory mem; // memory
	private final int EXIT_PROGRAM = 48; // BREAK opcode in int format 0...0110000 = 48
	
	/* Constructor */
	public CPU( ArrayList program , boolean debug ){
		
		mem = new Memory( program.size() + 100 ); 
		for(int i = 0; i < program.size(); i++){ // load program into memory
			mem.setValue(i, (int) program.get(i));
		}
		workingMemoryIndex = program.size(); // index of first free working memory
		
		if( debug ){ // if debugging print contents of memory.
			for(int i = 0; i<mem.getSize(); i++){
				System.out.println( mem.getValue(i) );
			}
		}
	
	} // end constructor
	
	/*
	 *   Initiates execution of program 
	 *   Return true upon completion 
	 */
	public boolean execute(){
	     
		int curr; // current instruction
		int opcode;    
		int instrType; // type of instruction ALU, ALU/C and LOAD STORE, BRANCH, or MISC
		int indexRegC; // index of register in "C" position in instruction format
		int indexRegA; // index of register in "A" position in instruction format
		int indexRegB; // index of register in "B" position in instruction format
		int literal;   // signed value of literal in literal position in instruction format
		
		while( true ){
		
			curr = mem.getValue( ip );  
			ip++;
				
			/* Get register indexes and literals, even if particular instruction type doesn't use. */
			indexRegC = (curr << 6 ) >>> 27; 
			indexRegA = (curr << 11) >>> 27;    
			indexRegB = (curr << 16 ) >>> 27;
			literal = (curr << 16 ) >> 16; // arithmetic shift to maintain signed value
			
			/* Pre-parse opcode */
			opcode = curr >>> 26; //separate out opcode from instruction 
			instrType = opcode >>> 4; //determine class of operation in bits 31 and 30. 
			
			if( opcode == 48 ){
				System.out.println( "!CPU: Program has completed execution." );
				break; 
			}
			
			switch( instrType ){
				
				case 0: // opcode: 00XXXX ALU reg reg reg
				
					// pass argumetns to ALU and store result in destination register
		
					regs.setValue( indexRegC, 
					               ALU.doOp( opcode, regs.getValue(indexRegA), regs.getValue(indexRegB) ) );
					break;
				
				case 1: // opcode 01XXXX ALUC, Load/Store
						
					// determine if ALUC or LOAD/STORE, handle appropriately
					switch( opcode ){
							
							case 16: // LOAD
								
								regs.setValue( indexRegC, // index of destingation register
								               mem.getValue( regs.getValue(indexRegA) + literal + workingMemoryIndex ) ); // value
								break;
							
							case 31: // STORE
							    
								mem.setValue( regs.getValue(indexRegA) + literal + workingMemoryIndex, // memory index
								              regs.getValue(indexRegC) ); // value
								break;
							
							default: // ALUC
								regs.setValue( indexRegC, 
								               ALU.doOp( opcode, regs.getValue( indexRegA ), literal ));
								
								break;
								
					} // end internal switch (opcode)
					break; // break case 1
				
				case 2: // opcode 10XXXX branch
										
					/* stores value of branch instruction.  */
					regs.setValue( indexRegC, ip - 1 );
					/* Determine operation and compute accordingly */
					switch( opcode ){
						
						case 32: // bez
							
							if( regs.getValue( indexRegA ) == 0 ){ // do branch
								
								ip = ip + literal;
							}
							break;
						
						case 33: // bnz
							
							if( regs.getValue( indexRegA ) != 0 ){ // do branch
								
								ip = ip + literal;
								
							}
							break;
					} // end internal switch(opcode)
					
					break; // break case 2
					
				case 3: // Miscellaneous Instructions
					
					if( opcode == 49 ){ // INPUT
						
						System.out.printf( "CPU INPUT: ");
						Scanner input = new Scanner( System.in );
						
						while( !input.hasNextInt() ){
							
							System.out.printf( "Invalid. Not an int. \n CPU INPUT: ");
							input.next();
								
						}
						
						regs.setValue( indexRegC, input.nextInt() );
						break;
					}
					if( opcode == 50 ){ // OUTPUT
						
						System.out.printf( "CPU OUTPUT: %d \n", regs.getValue( indexRegC ) );
						break;
					}
					default:
			} // end switch (instrType)
			
		} // end while
		
		return true;
	
	} // end method execute

	
	

}