/* When parsing assembly code, sometimes, we have to deal with symbols
 * that represent variables, or symbols that point to instruction addresses 
 * to allow for looping. This class stores those symbols and their respective
 * addresses when it encounters them while reading assembly code */

package org.nand2tetris;

import java.util.HashMap;

class SymbolTable {
    
    /* HashMap is the primary structure in the SymbolTable class 
       used to map symbols to memory addresses */
    private HashMap<String, Integer> myMap;
 
    // initializes the HashMap and adds predefined symbols to it   
    SymbolTable(){
        myMap = new HashMap<>();
        myMap.put("SP", 0);
        myMap.put("LCL", 1);
        myMap.put("ARG", 2);
        myMap.put("THIS", 3);
        myMap.put("THAT", 4);
        myMap.put("SCREEN", 16384);
        myMap.put("KBD", 24576);
        for(int i = 0; i < 16; i++){
            myMap.put("R"+i, i);
        }
    }

    // adds a new symbol and its address to the map    
    public void addEntry(String symbol, int address){
        myMap.put(symbol, address);
    }

    // checks if the SymbolTable contains a symbol    
    public boolean contains(String symbol){
        return myMap.containsKey(symbol);
    }

    // returns the address at which a symbol is stored
    public int getAddress(String symbol){
        return myMap.get(symbol);
    }   
}