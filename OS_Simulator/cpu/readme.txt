file: readme.txt

Quick Start Instructions:

Load all files into the java directory of your choice. Compile, e.g. with the command javac *.java. Included is the source codes of three simple sample programs: example1.txt, example2.txt, and example3.txt. The first thing you will need to do is to create a binary file from one of these.

This is accommplished by typing in the command: "java Assembler example1.txt". The program is compiled into a binary file called "a.bin" (technically, it is a serialized object
containing the array of instructions in integer format). To run the program type the command:
"java Run". 

Useful information:
  1. register prefix is @ followed by register number, e.g. @4
  2. literal prefix is # followed by integer value, e.g. #-2389
  3. Register 0 has a fixed value of 0. Changing it should raise an exception.
  4. Every program *must* end with the instruction: break @0
  5. Instruction size is 32 bits. No overflow detection.
  6. Comments and blank lines are not parsed. Will cause error if included. Assembler very simple.
  7. Detailed instruction format information below.

______________________________________________________

ALU													 
+=======================================+
+            CODE 00XXXX Format	     	+
+=======================================+ 
+  Opcode   C     A     B     unused    +
+  XXXXXX XXXXX XXXXX XXXXX ----------  +
+  6bits  5bits 5bits 5bits   11 bits   +
========================================+

+=====================================================+
+          OPERATION TABLE code 00XXXX                +
+=====================================================+
+	Operation	Opcode	Example/Comment               
+-----------------------------------------------------+
+           ALU OPERATIONS                            +
+-----------------------------------------------------+
+   add	      	000001  addition ex. add @1 @2 @3     +
+   sub         000010  subtraction                   +  
+   mul         000011  multiply                      +  
+   div         000100  divide                        +  
+   and         000101  and                           +  
+   or          000110  inclusive or                  +   
+   xor         000111  exclusive or                  +  
+   shl         001000  shift left                    +  
+   slr         001001  logical shift right           +  
+   sar         001010  arith shft right              +  
+   ceq         001011  stores 1 if equal, else 0     +  
+   cgr		001100  stores 1 if greater, else 0   +   
+   clt         001101  stores 1 if less, else 0      +  
=======================================================


________________________________________________________
ALU/C, LOAD/STORE.

+=======================================+
+          CODE 01XXXX Format           +
+=======================================+
+  Opcode   C     A      literal        +
+  XXXXXX XXXXX XXXXX XXXXXXXXXXXXXXXX  +
+  6bits  5bits 5bits     16 bits       +
+=======================================+
			
+=====================================================+
+          OPERATION TABLE  code 01XXXX               +
+=====================================================+
+	Operation	Opcode	Example/Comment       +
+-----------------------------------------------------+
+           ALU OPERATIONS                            +
+-----------------------------------------------------+
+   addc	 010001  example. add @1 @2 #345      +       
+   subc         010010                               +  
+   mulc         010011                               +  
+   divc         010100                               +  
+   andc         010101                               +  
+   orc          010110                               +  
+   xorc         010111  		              +  
+   shlc         011000  shift left                   +  
+   slrc         011001  log shift right              +  
+   sarc         011010  arith shft right             +  
+   ceqc         011011  stores 1 if equal, else 0    +  
+   cgrc	 011100  stores 1 if greater, else 0  +  
+   cltc         011101  stores 1 if less, else 0     +  
+-----------------------------------------------------+
+           LOAD/STORE OPERATIONS                     +  
+-----------------------------------------------------+
+   ld           010000  loads from mem to register   +  
+   st           011111  stores reg to mem            +  
+=====================================================+

___________________________________________________________________________
Branch. Reg C holds the location of the branch instruction.
+=======================================+
+         CODE 10XXXX Format            +
+=======================================+
+  Opcode   C     A      literal        +
+  XXXXXX XXXXX XXXXX XXXXXXXXXXXXXXXX  +
+  6bits  5bits 5bits     16 bits       +
+=======================================+
			
+=====================================================+
+             OPERATION TABLE Code 10XXXX             +
+=====================================================+
+   bez          100000  branch if register = zero    +   
+   bnz          100001  branch if register != zero   +   
+=====================================================+


___________________________________________________________________________

Misc.
+=======================================+
+     CODE 11XXXX Format                +
+=======================================+
+  Opcode   C            unused         +
+  XXXXXX XXXXX  ---------------------  +
+  6bits  5bits         21 bits         +
+=======================================+
			
+=====================================================+
+          OPERATION TABLE Format 11XXXX              +
+=====================================================+
+   break       110000  end program. break @0         +   
+   in          110001  take input from keyboard      +  
+   out         110010  output to screen              +  
+=====================================================+


