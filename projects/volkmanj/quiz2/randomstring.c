/*********************
* File: randomstring.c
* Author: Jake Volkman
* CS 362
* Quiz 2


Iteration until error is thrown
1st Test:  378778
2nd Test: 2271860
3rd Test:  848297

	My solution to Quiz 2 was developed in the following way:
	
Function char inputChar() uses the rand() function to generate a
random number between 32 and 127. These are the limits of relevant
ASCII characters that will be needed to satisfy the instructor's
choice of parameters for the test.

Function char *inputString() is a C-string made from random ASCII
characters between 101(e) and 116(t). I chose these characters
to decrease run time, because running testme could take a while.
The string length is defined as constant STRING_SIZE in the 
preprocessor. STRING_SIZE is set to 5 because the end paramater 
"reset" is 5 characters long.

The testme main function will loop until 9 characters are found, then
terminate the process with "error" when the string spells "reset."
Based on the required test characters for main, I think that this
was the quickest test that does not compromise the test's integrity as
"random."


*********************/