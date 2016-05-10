// file: ALU.java

public class ALU{

	public ALU(){
	
	}
	
	public static int doOp( int opcode, int op1, int op2 ){
	
		/* Clear all but four lowest-order bits from opcode, removing 5th and 4th bits from opcode */
		opcode = opcode & 0x0f; 
		
		/* Choose operation to perform. Do not check overflows */
		switch( opcode ){
			
			case 1: // add/addc
				
				return op1 + op2;
			
			case 2: // sub/subc
			
				return op1 - op2;
				
			case 3: // mul/mulc
			
				return op1 * op2;
			
			case 4: // div/divc
			
				return op1 / op2; 
			
			case 5: // and/andc
			
				return op1 & op2;
			
			case 6: // or/orc
			
				return op1 | op2;
			
			case 7: // xor/xorc
			
				return op1 ^ op2;
				
			case 8: // shl/shlc
			
				return op1 << op2;
			
			case 9: // slr/slrc
			
				return op1 >>> op2;
				
			case 10: // sar/sarc
			
				return op1 >> op2;
				
			case 11: // ceq/ceqc
			
				if( op1 == op2 )
					return 1;
				else
					return 0;
			
			case 12: // cgr/cgrc
			
				if ( op1 > op2 )
					return 1;
				else
					return 0;
			
			case 13: // clt/cltc
		
				if ( op1 < op2 )
					return 1;
				else
					return 0;
			
			default: // error
				
				throw new ALUIllegalArgumentException( "Opcode does not match any existing operation in the ALU op-table." );
				
		}
	
	}

}

