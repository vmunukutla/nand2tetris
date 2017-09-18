// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[3], respectively.)



// set variable i to 0
@i
M=0
// set variable mult to 0
@mult
M=0
// set variable R2 to 0
@R2
M=0
// set variable n to multiplier number of iterations
@n
M=D
@R0
D=M
@END
D;JEQ

(LOOP)
	// check if the loop has gone through multiplier # of loops
        @i
        D=M
        @n
        D=D-M
        @STOP
        D;JEQ
	// add R0's value (mult) to R1
        @mult
        D=M
        @R1
        D=D+M
        @mult
        M=D	
	// update variable i
        @i
        M=M+1
        @LOOP
        0;JMP

(STOP)
	// set register R2's value to the final multiplied result
        @mult
        D=M
        @R2
        M=D

(END)
	// infinite for loop to prevent continuation of program into
	// empty lines. safeguards against hacking
        @END
        0;JMP    