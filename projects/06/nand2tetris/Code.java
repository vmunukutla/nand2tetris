/* The Code class contains static methods that take as input
 * substrings from assembly code and returns their binary representations. */

package org.nand2tetris;

class Code {

    // takes the destination substring and returns its binary version        
    static String dest (String destmn){
        switch (destmn){
            case "":
                return "000";
            case "M":
                return "001";
            case "D":
                return "010";
            case "MD":
                return "011";
            case "A":
                return "100";
            case "AM":
                return "101";
            case "AD":
                return "110";
            case "AMD":
                return "111";
        }
        return null;
    }

    // takes the computation substring and returns its binary version      
    static String comp (String compmn){
        switch (compmn) {
            case "0":
                return "0101010";
            case "1":
                return "0111111";
            case "-1":
                return "0111010";
            case "D":
                return "0001100";
            case "!D":
                return "0001101";
            case "-D":
                return "0001111";
            case "D+1":
                return "0011111";
            case "D-1":
                return "0001110";
            case "A":
                return "0110000";
            case "M":
                return "1110000";
            case "!A":
                return "0110001";
            case "!M":
                return "1110001";
            case "-A":
                return "0110011";
            case "-M":
                return "1110011";
            case "A+1":
                return "0110111";
            case "M+1":
                return "1110111";
            case "A-1":
                return "0110010";
            case "M-1":
                return "1110010";
            case "D+A":
                return "0000010";
            case "D+M":
                return "1000010";
            case "D-A":
                return "0010011";
            case "D-M":
                return "1010011";
            case "A-D":
                return "0000111";
            case "M-D":
                return "1000111";
            case "D&A":
                return "0000000";
            case "D&M":
                return "1000000";
            case "D|A":
                return "0010101";
            case "D|M":
                return "1010101";
        }
        return null;
    }

    // takes the jump substring and returns its binary version     
    static String jump (String jumpmn) {
        switch (jumpmn){
            case "":
                return "000";
            case "JGT":
                return "001";
            case "JEQ":
                return "010";
            case "JGE":
                return "011";
            case "JLT":
                return "100";
            case "JNE":
                return "101";
            case "JLE":
                return "110";
            case "JMP":
                return "111";
        }
        return null;
    }
    
}