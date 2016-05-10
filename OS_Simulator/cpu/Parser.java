// file: Parser.java

public class Parser{

	public static int parse( String s ){
		String[] tokenArray = s.split(" ");
		int n = tokenArray.length;
		int[] intTokens = new int[n];
		
		for( int i = 0; i < n; i++ ){
			intTokens[i] = toInt( tokenArray[i] );
		}
		
		// now take integer tokens and form instruction in machine format
		return buildIns( intTokens );
		
	}
	
	/* 
	 * Helper method to convert string tokens to integer representations 
	 * Example: Register value r3 should have integer value 3, regardless if it is
     * in argument position C, A, or B. 
	 */	
	private static int toInt( String token ){
	
		if (token.startsWith( "@" )){ /* Pattern match register */
			
			int register = Integer.parseInt( token.substring( 1 ) );
			return register;
		}
		
		if (token.startsWith( "#" )){ /* Pattern match literal */
		
			int literal = Integer.parseInt( token.substring( 1 ) );
			return literal;
		
		}
		else{ /* Pattern match opcode */
		
			if( debug ){System.out.printf( "%s ", token );}
			switch( token ){
			
				case "add": return 1;
				case "sub": return 2;
				case "mul": return 3;
				case "div": return 4;
				case "and": return 5;
				case "or" : return 6;
				case "xor": return 7;
				case "shl": return 8;
				case "slr": return 9;
				case "sar": return 10;
				case "ceq": return 11;
				case "cgr": return 12;
				case "clt": return 13;
				case "addc" : return 17;
				case "subc" : return 18;
				case "mulc" : return 19;
				case "divc" : return 20;
				case "andc" : return 21;
				case "orc" : return 22;
				case "xorc" : return 23;
				case "shlc" : return 24;
				case "slrc" : return 25;
				case "sarc" : return 26;
				case "ceqc" : return 27;
				case "cgrc" : return 28;
				case "cltc" : return 29;
				case "ld"   : return 16;
				case "st"   : return 31;
				case "bez"  : return 32;
				case "bnz"  : return 33;
				case "break" : return 48;
				case "in"   : return 49;
				case "out"  : return 50;

				default: return 0; // probably should throw exception
			} // end switch
		} // end else
		
	} //end method toInt

	/* 
	 * Helper method to build instruction in machine readable format from the integer array
	 * constructed using the toInt helper method. 
	 */
	private static int buildIns( int[] intTokens ){
	
		int instruction = 0;
		int token = 0;
		
		// intTokens[0] is always opcode
		token = intTokens[0];
		instruction = instruction | (token << 26); // opcode 31 thru 26, move bits 5-0. 31-5 = 26
		
		// intTokens[1] is always dest. register C
		token = intTokens[1];
		instruction = instruction | (token << 21); // C is 25-20, move bits 4 thru 0. 25-4 = 21;
		
		// check to see if operation is a break, in, our out, which only has two elements
		if( intTokens[0] >= 48 && intTokens[0] <= 50 ){ // instruction is of class Misc.
			
			return instruction;
			
		}
		
		// not Misc, so has Reg arg A
		token = intTokens[2];
		instruction = instruction | (token << 16); // C is 20-16, moves bits 4 thru 0. 20-4=16.
		token = intTokens[3];
		if( intTokens[0] >= 1 && intTokens[0] <= 13 ){ // instruction is of class ALU, so has reg B
			
				instruction = instruction | (token << 11);
			
		}
		else{ // instruction is either ALU/C or Load Store or Branch all with literal

			instruction = instruction | (( token << 16 ) >>> 16);
		}
		
		return instruction;
		
	} // end method buildIns
	
} // end class Parser