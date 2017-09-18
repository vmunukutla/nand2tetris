// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.


// set variable s to the default initial memory register for the screen
@SCREEN
D=A
@s
M=D

// if the keyboard is pressed (the keyboard memory register
// value is nonzero), go to the ON loop
@KBD
D=M
@ON
D;JNE
@WHITECHILL
D;JEQ

(ON)
        // reset variable s to the default initial memory register
	// for the screen
	@SCREEN
	D=A
	@s
	M=D
	(BLACK)
		// if a key is not pressed anymore, go to the OFF loop
		@KBD
		D=M
		@OFF
		D;JEQ
		// set the screen memory register values to -1,
		// which is 1111111111111111. This changes 16 pixels
		// on the screen from white to black
		@s
		A=M
		M=-1
		// increment the screen memory address by one for next loop
		@s
		D=M
		M=M+1
		// if all the pixels on the screen have turned black, go to
		// the BLACKCHILL loop. otherwise, repeat current loop
		@KBD
		D=D-A
		@BLACKCHILL
		D;JEQ		
		@BLACK
		D;JNE

(OFF)
        // reset variable s to the default initial memory register
	// for the screen
	@SCREEN
	D=A
	@s
	M=D
	(WHITE)
		// if a key is pressed, go to the ON loop
		@KBD
		D=M
		@ON
		D;JNE
		// set the screen memory register values to 0,
		// which is 0000000000000000. This changes 16 pixels
		// on the screen from black or white
		@s
		A=M
		M=0
		// increment the screen memory address by one for next loop
		@s
		D=M
		M=M+1
		// if all the pixels on the screen have turned white, go to
		// the WHITECHILL loop. otherwise, repeat current loop
		@KBD
		D=D-A
		@WHITECHILL
		D;JEQ
		@WHITE
		D;JNE	

(BLACKCHILL)
	// keep staying in loop until key is released
	@KBD
	D=M
	@BLACKCHILL
	D;JNE
	@OFF
	D;JEQ

(WHITECHILL)
	// keep staying in loop until key is pressed
	@KBD
	D=M
	@WHITECHILL
	D;JEQ
	@ON
	D;JNE			