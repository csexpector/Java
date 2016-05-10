
package ossimulator;

import java.util.Random;

public class CPU {
    public final int LOADVALUE = 1;     // Load the value into the AC
    public final int LOADADDR = 2;      // Load the value at the address into the AC
    public final int LOADIND = 3;       // Load the value from the address found in the given address into the AC
                                        // (for example, if LoadInd 500, and 500 contains 100, then load from 100)
    public final int LOADIDXX = 4;      // Load the value at (address+X) into the AC
                                        // (for example, if LoadIdxX 500, and X contains 10, then load from 510).
    public final int LOADIDXY = 5;      // Load the value at (address+Y) into the AC
    public final int LOADSPX = 6;       // Load from (Sp+X) into the AC
    public final int STOREADDR = 7;     // Store the value in the AC into the address
    public final int GET = 8;           // Gets a random int from 1 to 100 into the AC
    public final int PUTPORT = 9;       // If port=1, writes AC as an int to the screen
                                        // If port=2, writes AC as a char to the screen
    
    public final int ADDX = 10;         // Add the value in X to AC
    public final int ADDY = 11;         // Add the value in Y to AC
    public final int SUBX = 12;         // Subtract the value in X from AC
    public final int SUBY = 13;         // Subtract the value in Y from AC
    public final int COPYTOX = 14;      // Copy AC to X
    public final int COPYFROMX = 15;    // Copy from X to AC
    public final int COPYTOY = 16;      // Copy AC to Y
    public final int COPYFROMY = 17;    // Copy Y to AC
    public final int COPYTOSP = 18;     // Copy AC to SP
    public final int COPYFROMSP = 19;   // COpy SP to AC
    public final int JUMPADDR = 20;     // Jump to address
    public final int JUMPIFEQUAL = 21;  // Jump to address if AC equal 0
    public final int JUMPNOTEQUAL = 22; // Jump to address if AC is not 0
    public final int CALLADDR = 23;     // Push return address onto stack, jump to address
    public final int RET = 24;          // Pop return address from stack, jump to address
    public final int INCX = 25;         // Increment value in X
    public final int DECX = 26;         // Decrement value in X
    public final int PUSH = 27;         // Push AC to stack
    public final int POP = 28;          // Pop from stack to AC
    public final int INT = 29;          // Set system mode, switch stack, push SP and PC, set new SP and PC
    public final int IRET = 30;         // Restore registers, set user mode
    public final int END = 50;          // End execution
    
    public final int PROGRAMADDR = 0;   // User program runs at address 0
    public final int TIMERADDR = 1000;  // Timer handler start at 1000
    public final int INTADDR = 1500;    // Interupt handler start at 1500
    
    public final int USERMODE = 1;
    public final int SYSTEMMODE = 2;
    
    private int PC, SP, IR, AC, X, Y;   // Registers

    public final int MEMSIZE = 2000;    // Memory size
    public final int USERMEM = 999;     // User memory from 1 t0 999
    
    private int memory[] = new int[MEMSIZE];                 // Memory
    
    private boolean isStop = false, isInterrupted = false;
    
    
    private int mode;       // execute in system mode (interrupt) or user mode
    private int USP;        // user stack pointer
    private int SSP;        // system stack pointer
    private int timer;      // timer interrupt
    
    
    // initialize memory (this is loading a program to run)
    public void initializeMemory(int mem[]) {
        for (int i = 0; i < mem.length && i < 2000; i++)
        {
            memory[i] = mem[i];
        }
    }
    // set the timer used in timer interrupt
    public void setTimer(int timer)
    {
        this.timer = timer;
    }
    
    // read from memory
    // interrupt if invalid range or no permission
    public int readMemory(int address)
    {
        // check permission
        if (mode == USERMODE && address > 999) {
            invalidAccessInterrupt();
            return -1;
        }
        // check range
        if (address < 0 || address >= MEMSIZE) {
            indexOutOfRangeInterrupt();
            return -1;
        }
        else return memory[address];
    }
    // write to memory
    // interrupt if invalid range or no permission
    public void writeMemory(int address, int data)
    {
        // check permission
        if (mode == USERMODE && address > 999) {
            invalidAccessInterrupt();
            return;
        }
        if (address < 0 || address >= MEMSIZE) 
        {
            indexOutOfRangeInterrupt();
        }
        else this.memory[address] = data;
    }
    
    // Fetch instruction from memory to IR
    // instruction is pointed by PC
    public void fetchInstruction()
    {
        IR = readMemory(PC++); // PC moves to next position in memory
    }
    
    public void executeInstruction()
    {
        int add, val;
        // get instruction from IR
        switch (IR)
        {
            case LOADVALUE:
                // read value from memory to AC
                AC = readMemory(PC++); // move PC to next position in memory
                break;
            case LOADADDR: // Load the value at the address into the AC
                // read address from memory
                add = readMemory(PC++); // move PC
                AC = readMemory(add);
                break;
            case LOADIND: // Load the value from the address found in the given address into the AC
                          // (for example, if LoadInd 500, and 500 contains 100, then load from 100)
                val = readMemory(PC++);
                add = readMemory(val);
                AC  = readMemory(add);
                break;
            case LOADIDXX:
                add = readMemory(PC++);
                AC  = readMemory(add + X);
                break;
            case LOADIDXY:
                add = readMemory(PC++);
                AC  = readMemory(add + Y);
                break;
            case LOADSPX:
                AC = readMemory(SP + X);
                break;
            case STOREADDR:
                add = readMemory(PC++);
                writeMemory(add, AC);
                break;
            case GET:
                Random r = new Random();
                AC = 1 + r.nextInt(100);
                break;
            case PUTPORT:
                // read port
                int port = memory[PC++];
                if (port == 1) // write as integer
                    System.out.print(AC);
                else if (port == 2) // write as char
                    System.out.print(Character.toChars(AC));
                break;
            case ADDX:
                AC += X;
                break;
            case ADDY:
                AC += Y;
                break;
            case SUBX:
                AC -= X;
                break;
            case SUBY:
                AC -= Y;
                break;
            case COPYTOX:
                X = AC;
                break;
            case COPYFROMX:
                AC = X;
                break;
            case COPYTOY:
                Y = AC;
                break;
            case COPYFROMY:
                AC = Y;
                break;
            case COPYTOSP:
                SP = AC;
                break;
            case COPYFROMSP:
                AC = SP;
                break;
            case JUMPADDR:
                add = readMemory(PC);
                PC = readMemory(add);
                break;
            case JUMPIFEQUAL:
                if (AC == 0)
                {
                    add = readMemory(PC);
                    PC  = add;
                } else PC++;
                break;
            case JUMPNOTEQUAL:
                if (AC != 0)
                {
                    add = readMemory(PC);
                    PC  = add;
                } else PC++; // by pass address
                break;
            case CALLADDR:
                // push current address (is PC)
                push(PC);
                add = readMemory(PC); // address to jump                
                // jump
                PC = add;
                break;
            case RET:
                pop(); // address poped is stored in AC
                // jump
                PC = AC;
                break;
            case INCX:
                X++;
                break;
            case DECX:
                X--;
                break;
            case PUSH:
                push(AC);
                break;
            case POP:
                pop();
                break;
            case INT:
                if (!isInterrupted) // if in interrupted, not allow
                {
                    isInterrupted = true;
                    mode = SYSTEMMODE;
                    pushRegisters();
                    PC = INTADDR; // interrupt instruction execution at address 1500
                }
                break;
            case IRET:
                isInterrupted = false;
                restoreRegisters();
                mode = USERMODE;
                break;
            case END:
                isStop = true;
                break;
            default:
                //System.out.println("Not implemented yet!");
        }    
    }
    public void run()
    {
        // set PC to 0
        PC = 0;
        // set SP to 1000
        SP = 1000; USP = 1000;  // user stack has not started yet
        SSP = 2000;             // system stack has not started yet
        // set mode to user mode
        mode = USERMODE;
        int ticktock = 0;       // count time, time-interrupt after a constant ticktock (set from running)
        while (true)
        {   
            // fetch instruction to IR
            fetchInstruction();
            
            // execute instruction
            executeInstruction();
            
            // terminal command received (500)
            if (isStop) break;
            if (isInterrupted) continue; // if in interrupt, don't handle timer interrupt
            if (++ticktock % timer == 0)  timerInterrupt();
        }
    }
    
    // to perform timer interrupt
    public void timerInterrupt()
    {
        if (!isInterrupted) // if in interrupted, not allow
        {
            isInterrupted = true;
            mode = SYSTEMMODE;
            pushRegisters();
            PC = TIMERADDR; // interrupt instruction execution at address 1000
            if (memory[TIMERADDR] == 0) // no timer handler
                memory[TIMERADDR] = IRET; // default handler do nothing, just return            
        }
    }
    
    // to load a range of instructions
    public void loadInstructions(int start, int instructions[])
    {
        for (int i = 0; i < instructions.length; i++)
        {
            memory[start + i] = instructions[i];
        }
    }
    
    // push to stack (choose between system stack or user stack)
    public void push(int val) // put value to stack
    {
        if (mode == USERMODE)
        {
            memory[--USP] = val;
            SP = USP;
        } else
        {
            memory[--SSP] = val;
            SP = SSP;
        }
    }
    // save register (used before interrupt)
    public void pushRegisters()
    {
        push(AC);
        push(PC);
        push(SP);
        push(IR);        
        push(X);
        push(Y);
    }
    // restore register (used after interrupt)
    public void restoreRegisters()
    {
        pop();
        Y = AC;
        
        pop();
        X = AC;
        
        pop();
        IR = AC;
        
        pop();
        SP = AC;
        
        pop();
        PC = AC;
        
        pop(); // AC = AC
        
    }
    // pop from stack (system stack or user stack)
    public void pop()
    {
        if (mode == USERMODE)
        {
            AC = memory[USP++];
            SP = USP;
        } else
        {
            AC = memory[SSP++];
            SP = SSP;
        }
    }
    // this is to perform index out of range interrupt
    public void indexOutOfRangeInterrupt()
    {
        IR = INT; // interrupt!
        int indexOutHandler[] = { // print "Invalid index!"
            1, 73,  // I
            9, 2,
            1, 110, // n
            9, 2,
            1, 118, // v
            9, 2,
            1, 97,  // a
            9, 2,
            1, 108, // l
            9, 2,
            1, 105, // i
            9, 2,
            1, 100, // d
            9, 2,
            1, 32,  // _
            9, 2,
            1, 105, // i
            9, 2,
            1, 110, // n
            9, 2,
            1, 100, // d
            9, 2,
            1, 101, // e
            9, 2,
            1, 120, // x
            9, 2,
            1, 33,  // !
            9, 2,
            1, 10,  // \n
            9, 2,
            50      // END program
        };
        // load interrupt instructions
        loadInstructions(INTADDR, indexOutHandler);
        // execute interrupt instructions
        executeInstruction();
    }
    // this is to perform invalid access interrupt
    public void invalidAccessInterrupt()
    {
        IR = INT;
        int invalidAccessHandler[] = { // print "Access error!"
            1, 65,      // A
            9, 2,
            1, 99,      // c
            9, 2,
            1, 99,      // c
            9, 2,
            1, 101,     // e
            9, 2,
            1, 115,     // s
            9, 2,
            1, 115,     // s
            9, 2,
            1, 32,      // _
            9, 2,
            1, 101,     // e
            9, 2,
            1, 114,     // r
            9, 2,
            1, 114,     // r
            9, 2,
            1, 111,     // o
            9, 2,
            1, 114,     // r
            9, 2,
            1, 33,      // !
            9, 2,
            1, 10,      // \n
            9, 2,
            50          // End program!
        };
        // load interrupt instructions
        loadInstructions(INTADDR, invalidAccessHandler);
        // execute interrupt instructions
        executeInstruction();
    }
}
