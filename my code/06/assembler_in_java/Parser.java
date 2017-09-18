/* The Parser class takes an input file containing lines of assembly code.
 * It can check if there are more commands, advance, and categorize the assembly
 * code line as an instruction, condition, symbol, or memory register assignment.
 * It further breaks down the assembly code line into its various components */

package org.nand2tetris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Parser {

	//definition of buffered reader that will be used to read from file
    private BufferedReader br;

    /* definition and declaration of currentLine global variable, 
       representing the line that the Parser is currently analyzing */
    private String currentLine = "";

    /* definition and declaration of currentCommand global variable,
       representing the command retrived from currentLine that the 
       Parser is currently analyzing */
    private String currentCommand = "";

	// read assembly source from input file into buffered reader
    Parser(String file) {
        try {
            FileReader text = new FileReader(file);
            br = new BufferedReader(text);
        } catch (IOException | SecurityException se) {
            System.out.println("Error -- " + se.toString());
        }
    }
 
 	// check if input file has more lines   
    public boolean hasMoreCommands() throws IOException{
        currentLine = br.readLine();
        return currentLine != null;
    }
 
 	// if input file has more lines, advance to the next line   
    public void advance() throws IOException{
    	// remove whitespace
        String clean = currentLine.trim();
        // if the line is blank or starts with a comment, skip it
        while(clean.isEmpty() || clean.substring(0,1).equals("/")){
            currentLine = br.readLine();
            clean = currentLine.trim();
        }
        /* if the line contains a comment after an assembly instruction,
           discard the comment and keep the instruction */
        int index = clean.indexOf("//");
        if(index == -1)
            currentCommand = clean;
        else
            currentCommand = clean.substring(0, index).trim();
    }

	/* return whether the current command is pointing to the A register (A_COMMAND),
	   if it is a symbol (L_COMMAND), or if it is a computation or condition check
	   (C_COMMAND) */    
    public String commandType(){
        if(currentCommand.substring(0,1).equals("@"))
            return "A_COMMAND";
        else{
            if(currentCommand.substring(0,1).equals("("))
                return "L_COMMAND";
            return "C_COMMAND";
        }
    }
 
	/* depending on the command type, return the number or variable after discarding
	   key indicating characters ("15" for ("@15"), and "num" for "(num)") */   
    public String symbol(){
        if(this.commandType().equals("A_COMMAND")){
            return currentCommand.substring(1);
        } else if(this.commandType().equals("L_COMMAND")){
            return currentCommand.substring(1,currentCommand.length()-1);           
        }
        return "";
    }

	/* assuming that the current command is a C_COMMAND, this method returns
	   the memory location where the output of the command should be stored */  
    public String dest(){
        int index = currentCommand.indexOf("=");
        if(index != -1)
            return currentCommand.substring(0,index);
        return "";
    }

	/* return the substring within an assembly instruction that specifies the 
	   computation being done in the instruction */
    public String comp(){
        if(currentCommand.substring(1,2).equals(";"))
            return currentCommand.substring(0,1);
        return currentCommand.substring(currentCommand.indexOf("=")+1);
    }
	
	/* return the substring within an assembly instruction that specifies the 
	   jump statement in the instruction */    
    public String jump(){
        if(currentCommand.substring(1,2).equals(";"))
            return currentCommand.substring(2);
        return "";
    }
}