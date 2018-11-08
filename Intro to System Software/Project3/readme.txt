Project 3 - Assembler pass 1

Purpose: The purpose of this program is to create a symbol table from a SIC/XE formatted file.

Input: Name of .txt file in the format of a SIC/XE program. The file included with this program is called Example2.txt.
IMPORTANT NOTE!! This file is designed with the assumption that the input file is formatted in a very specific layout AND that each line contains AT LEAST 80 characters (including whitespace characters), or that the line is blank.

Output: No files are output. The symbol table is displayed to the console.

Description: 
-The user needs to input the name of the input .txt file and the filename "SICOPS.txt" which contains the opcode table.
-The file is supposed to contain strings of uppercase characters and integers.
-Each line SHOULD contain AT LEAST 80 characters (including whitespace characters) or be blank.
-The program uses a scanner to read through the file one line at a time.
-The program stores each line as a string in a two-dimensional array.
-The program keeps track of the "address" of each line in hexadecimal, beginning with 000, if no address is specified in the first line.
-The program displays an error message if two lines begin with the same label, or an invalid mneumonic is used. 