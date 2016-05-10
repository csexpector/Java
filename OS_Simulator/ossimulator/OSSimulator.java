/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ossimulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author dttung
 */
public class OSSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int MAXCOMMANDS = 2000;
        if (args.length != 2)
        {
            System.out.println("Arguments missing. To run: java OSSimulater filename time");
            return;
        }
        String fileName = args[0], line;
        int intTime = Integer.valueOf(args[1]);
        int mem[] = new int[MAXCOMMANDS];
        int mindex = 0;
        // read instructions from program file to memory mem
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String num = "";
                int i = 0;
                boolean jump = false;
                boolean minus = false;
                if (line.length() == 0) continue; // avoid empty line
                // check if it's a jump command?
                if (line.charAt(i) == '.') {
                    jump = true;
                    i++;
                }
                // check if it's negative number
                if (line.charAt(i) == '-') {
                    minus = true;
                    i++;
                }
                // read integer
                while (i < line.length())
                {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) num += c;
                    else break;
                    i++;
                }
                if (!num.equals(""))
                {
                    int val = Integer.valueOf(num);
                    if (minus) val = 0 - val;
                    if (!jump)
                        mem[mindex++] = val;
                    else
                        mindex = val; // jump to address
                }
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");   
            return;
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"  + fileName + "'");      
            return;
        }
        
        CPU cpu = new CPU();
        cpu.setTimer(intTime);        
        
        cpu.initializeMemory(mem);
        //cpu.registerTimerHandler(timerHandler);
        cpu.run();
    }
}
