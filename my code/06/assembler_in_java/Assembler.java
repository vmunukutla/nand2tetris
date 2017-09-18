/* The Assembler class uses the Parser, SymbolTable, and Code classes
 * in order to completely convert all assembly code (written in the Hack
 * language) into binary code. It uses two Parsers: the first Parser is
 * used to run through the lines of code and add all symbols to the symbol
 * table, and the second Parser is used convert assembly code to binary 
 * code while also adding any new variables to the symbol table. A BufferedWriter
 * is used to group together 16 bits at a time and print each line of assembly
 * code in its own line in binary in the output file. 
 *
 * An important note: The assembly file that will be converted must be entered
 * directly into the Assembly class by the user. When initializing Parsers, the
 * user must put in the parameter the input asm file. When initializing the
 * FileWriter, the ouput file name must be entered as a parameter, in the 
 * hack file format. */

package org.nand2tetris;

import java.io.*;

class Assembler {
    
    public static void main(String[] arguments) throws IOException{
        /* initialize first Parser and the SymbolTable, and then execute
           firstLoop to add all L_COMMANDS (symbols) to the table */
        Parser first = new Parser("Pong.asm");
        SymbolTable st = new SymbolTable();
        firstLoop(first, st);
        /* initialize the second Parser that will actually convert the 
           assembly code to binary, and initialize the FileWriter and 
           BufferedWriter to write the binary translation to a new file */
        Parser second = new Parser("Pong.asm");
        FileWriter fw = new FileWriter("Pong.hack");
        BufferedWriter wr = new BufferedWriter(fw);
        while(second.hasMoreCommands()){
            second.advance();
            // skip L_COMMANDS because those are only placeholders
            if(second.commandType().equals("L_COMMAND")){
                continue;
            }
            String line = "";
            String correctSymbol = second.symbol();
            // starting memory address for variables
            int address = 16;
            if(second.commandType().equals("A_COMMAND")){
                /* if the A_COMMAND contains a variable, check the SymbolTable.
                   If it isn't there, add it and retrieve its new memory address.
                   If it is, simply retrieve its memory address. */
                if(!Character.isDigit(second.symbol().charAt(0))){
                    if(st.contains(second.symbol())){
                        correctSymbol = "" + st.getAddress(second.symbol());
                    }else{
                        correctSymbol = "" + address;
                        st.addEntry(second.symbol(), address++);
                    }
                }
                // complete the 16-bit binary translation of an A_COMMAND
                line = Integer.toBinaryString(Integer.parseInt(correctSymbol));
                while(line.length() < 16){
                    line = "0" + line;
                }
            }
            // if C_COMMAND, translate the command to binary using its Code translations
            if(second.commandType().equals("C_COMMAND")){
                line += "111" + Code.comp(second.comp()) + Code.dest(second.dest()) + Code.jump(second.jump());
            }
            // write to the buffer and flush it
            wr.write(line);
            wr.newLine();
            wr.flush();
        }
    }

    // in firstLoop, all the L_COMMANDS (symbols) are added to the SymbolTable    
    private static void firstLoop(Parser p, SymbolTable s) throws IOException{
        int instructionAddress = -1;
        while(p.hasMoreCommands()){
            p.advance();
            instructionAddress++;
            if(p.commandType().equals("L_COMMAND")){
                s.addEntry(p.symbol(), instructionAddress);
                /* instructionAddress-- because an L_COMMAND shouldn't be counted
                   as an instructionAddress */
                instructionAddress--;
            }
        }
    }
}