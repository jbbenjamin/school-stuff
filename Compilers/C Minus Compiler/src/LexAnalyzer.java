import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class LexAnalyzer {
	
	public static String currentLine = null;
	public static StringReader inputStream = null;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader bufInput = null;
		char currentChar;
		String temp = "";
		
		bufInput = new BufferedReader(new FileReader(args[0]));						//set up bufferedReader
		currentLine = bufInput.readLine();
		
		while(currentLine != null){													//while not at end of file
			inputStream = new StringReader(currentLine);							//set up a stringReader for this line
			if(!currentLine.isEmpty()){												//if the line isn't blank...
				System.out.println();
				System.out.println("INPUT: " + currentLine);						//print "INPUT: (line that was just read)"
			}
			currentChar = (char)inputStream.read();									//read first character of line
			while ((int)currentChar != 65535){										//while not at end of line...
				if(Character.isLetter(currentChar)){								//if last read character is a letter...
					currentChar = makeKeyword(inputStream, temp, currentChar);		//call 'makeKeyword' method
					temp = "";														//(after token is generated) reset 'temp' string
				} //end if
					
				else if(Character.isDigit(currentChar)){							//if last read character is a number...
					currentChar = makeNUM(inputStream, temp, currentChar);			//call 'makeNUM' method
					temp = "";														//(after token is generated) reset 'temp' string
				} //end else if
					
				else if(Character.isWhitespace(currentChar)){						//if last read character is whitespace...
					currentChar = (char)inputStream.read();							//read next character
				} //end else if
				
				else if(currentChar == '/'){										//if last read character is a '/'...
					temp += currentChar;
					currentChar = (char)inputStream.read();							//read next character
					
					if(currentChar == '/' || currentChar == '*'){					//if a comment...
						inputStream = comment(bufInput, inputStream, temp, currentChar);			//call 'comment' method
					currentChar = (char)inputStream.read();							//(after comment is finished) read next character
					temp = "";														//(after token is generated) reset 'temp' string
					}
					
					else{															// if just '/'
						System.out.println(temp);									//print '/'
						temp = "";
					}
				} //end else if
				
				else{																//is a special type of character
					currentChar = spcChar(inputStream, temp, currentChar);			//call special character method
					temp = "";														//(after token is generated) reset 'temp' string
				} //end else
					
			} //end while (not at end of line)
		
			currentLine = bufInput.readLine();										//end of line reached, read next line
		}	// end while (not at end of file)
	}	//end of main
//.................................................................................................		

public static char makeKeyword(StringReader inputStream, String stringSoFar, char thisChar) throws IOException{
	
	stringSoFar += thisChar;					//start new string
	
	switch(thisChar){
	
	case 'e':									//if letter is an 'e'
		thisChar = (char)inputStream.read();		//read next character
		if(Character.isLetter(thisChar)){				//if it is a letter...
			stringSoFar += thisChar;						//concatenate to stringSoFar
				
			if (thisChar == 'l'){								//if character is an 'l'...
				thisChar = (char)inputStream.read();				//read next character	
				if(Character.isLetter(thisChar)){						//if it is a letter...
					stringSoFar += thisChar;								//concatenate to stringSoFar

					if (thisChar == 's'){									//if character is an 's'...
						thisChar = (char)inputStream.read();					//read next character
						if(Character.isLetter(thisChar)){						//if it is a letter...
							stringSoFar += thisChar;								//concatenate to stringSoFar
							
							if(thisChar == 'e'){									//if character is an 'e'...
								thisChar = (char)inputStream.read();					//read next character
								if(Character.isLetter(thisChar)){						//if longer than keyword......
									stringSoFar += thisChar;							//concatenate to stringSoFar
									thisChar = makeID(inputStream, stringSoFar, thisChar);			//call 'makeID' method
									return thisChar;
								}	// end if (longer than a keyword)
									
								else{
									System.out.println("Keyword: " + stringSoFar);
									return thisChar;
								}	// end else
							}	// end if (fourth character is an 'e')
								
							else{
								thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
								return thisChar;
							}	// end else
						}	// end if (fourth character is a letter)	
						
						else{
							System.out.println("ID: " + stringSoFar);
							return thisChar;
						}	// end else
					}	// end if (third character is an 's')
						
					else{
						thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
						return thisChar;
					}	// end else
				}	// end if (third letter is a character)
							
				else{
					System.out.println("ID: " + stringSoFar);
					return thisChar;
				}	// end else
			}	// end if (second character is an 'l')
				
			else{
				thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
				return thisChar;
			}	// end else
		}	// end if (second character is a letter)
							
		else{
			System.out.println("ID: " + stringSoFar);
			return thisChar;
		}	// end else, case 'e'
		
		
	case 'f':									//if letter is an 'f'
		thisChar = (char)inputStream.read();		//read next character
		if(Character.isLetter(thisChar)){				//if it is a letter...
			stringSoFar += thisChar;						//concatenate to stringSoFar
				
			if (thisChar == 'l'){								//if character is an 'l'...
				thisChar = (char)inputStream.read();				//read next character	
				if(Character.isLetter(thisChar)){						//if it is a letter...
					stringSoFar += thisChar;								//concatenate to stringSoFar

					if (thisChar == 'o'){									//if character is an 'o'...
						thisChar = (char)inputStream.read();					//read next character
						if(Character.isLetter(thisChar)){						//if it is a letter...
							stringSoFar += thisChar;								//concatenate to stringSoFar
							
							if(thisChar == 'a'){									//if character is an 'a'...
								thisChar = (char)inputStream.read();					//read next character
								if(Character.isLetter(thisChar)){						//if it is a letter...
									stringSoFar += thisChar;							//concatenate to stringSoFar
									
									if(thisChar == 't'){									//if character is an 't'...
										thisChar = (char)inputStream.read();					//read next character
										if(Character.isLetter(thisChar)){						//if longer than keyword......
											stringSoFar += thisChar;							//concatenate to stringSoFar
											thisChar = makeID(inputStream, stringSoFar, thisChar);			//call 'makeID' method
											return thisChar;
										}	// end if (longer than a keyword)
											
										else{
											System.out.println("Keyword: " + stringSoFar);
											return thisChar;
										}	// end else
									}	// end if (fifth character is an 't')
										
									else{
										thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
										return thisChar;
									}	// end else
								}	// end if (fifth character is a letter)
								
								else{
									System.out.println("ID: " + stringSoFar);
									return thisChar;
								}	// end else
							}	// end if (fourth character is an 'a')
							
							else{
								thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
								return thisChar;
							}	// end else
						}	// end if (fourth letter is a character)
						
						else{
							System.out.println("ID: " + stringSoFar);
							return thisChar;
						}	// end else
					}	// end if (third character is an 'o')
								
					else{
						thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
						return thisChar;
					}	// end else
				}	// end if (third character is a letter)	
						
				else{
					System.out.println("ID: " + stringSoFar);
					return thisChar;
				}	// end else
			}	// end if (second character is an 'l')
						
			else{
				thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
				return thisChar;
			}	// end else
		}	// end if (second letter is a character)
							
		else{
			System.out.println("ID: " + stringSoFar);
			return thisChar;
		}	// end else, case 'f'
	
		
	case 'i':									//if letter is an 'i'
		thisChar = (char)inputStream.read();		//read next character
		if(Character.isLetter(thisChar)){				//if it is a letter...
			stringSoFar += thisChar;						//concatenate to stringSoFar
				
			if (thisChar == 'f'){								//if character is an 'f'...
				thisChar = (char)inputStream.read();				//read next character	
				if(Character.isLetter(thisChar)){						//if longer than keyword...
					stringSoFar += thisChar;								//concatenate to stringSoFar
					thisChar = makeID(inputStream, stringSoFar, thisChar);			//call 'makeID' method
					return thisChar;
				}	// end if (longer than a keyword)
					
				else{
					System.out.println("Keyword: " + stringSoFar);
					return thisChar;
				}	// end else
			}	// end if (second character is an 'f')	
			
			else if(thisChar == 'n'){									//if character is an 'n'...
				thisChar = (char)inputStream.read();					//read next character
				if(Character.isLetter(thisChar)){						//if it is a letter...
					stringSoFar += thisChar;							//concatenate to stringSoFar
					
					if(thisChar == 't'){									//if character is an 't'...
						thisChar = (char)inputStream.read();					//read next character
						if(Character.isLetter(thisChar)){						//if longer than keyword......
							stringSoFar += thisChar;							//concatenate to stringSoFar
							thisChar = makeID(inputStream, stringSoFar, thisChar);			//call 'makeID' method
							return thisChar;
						}	// end if (longer than a keyword)
							
						else{
							System.out.println("Keyword: " + stringSoFar);
							return thisChar;
						}	// end else
					}	// end if (third character is an 't')
						
					else{
						thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
						return thisChar;
					}	// end else
				}	// end if (third character is a letter)
				
				else{
					System.out.println("ID: " + stringSoFar);
					return thisChar;
				}	// end else
			}	// end else if (second character is an 'n')
			
			else{
				thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
				return thisChar;
			}	// end else
		}	// end if (second letter is a character)
							
		else{
			System.out.println("ID: " + stringSoFar);
			return thisChar;
		}	// end else, case 'i'
	
	case 'r':
		thisChar = (char)inputStream.read();		//read next character
		if(Character.isLetter(thisChar)){				//if it is a letter...
			stringSoFar += thisChar;						//concatenate to stringSoFar
				
			if (thisChar == 'e'){								//if character is an 'e'...
				thisChar = (char)inputStream.read();				//read next character	
				if(Character.isLetter(thisChar)){						//if it is a letter...
					stringSoFar += thisChar;								//concatenate to stringSoFar

					if (thisChar == 't'){									//if character is an 't'...
						thisChar = (char)inputStream.read();					//read next character
						if(Character.isLetter(thisChar)){						//if it is a letter...
							stringSoFar += thisChar;								//concatenate to stringSoFar
							
							if(thisChar == 'u'){									//if character is an 'u'...
								thisChar = (char)inputStream.read();					//read next character
								if(Character.isLetter(thisChar)){						//if it is a letter...
									stringSoFar += thisChar;							//concatenate to stringSoFar
									
									if(thisChar == 'r'){									//if character is an 'r'...
										thisChar = (char)inputStream.read();					//read next character
										if(Character.isLetter(thisChar)){						//if it is a letter...
											stringSoFar += thisChar;							//concatenate to stringSoFar
											
											if(thisChar == 'n'){									//if character is an 'n'...
												thisChar = (char)inputStream.read();					//read next character
												if(Character.isLetter(thisChar)){						//if longer than keyword......
													stringSoFar += thisChar;							//concatenate to stringSoFar
													thisChar = makeID(inputStream, stringSoFar, thisChar);			//call 'makeID' method
													return thisChar;
												}	// end if (longer than a keyword)
													
												else{
													System.out.println("Keyword: " + stringSoFar);
													return thisChar;
												}	// end else
											}	// end if (sixth character is an 'n')
												
											else{
												thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
												return thisChar;
											}	// end else
										}	// end if (sixth character is a letter)
										
										else{
											System.out.println("ID: " + stringSoFar);
											return thisChar;
										}	// end else
									}	// end if (fifth character is an 'r')
									
									else{
										thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
										return thisChar;
									}	// end else
								}	// end if (fifth letter is a character)
								
								else{
									System.out.println("ID: " + stringSoFar);
									return thisChar;
								}	// end else
							}	// end if (fourth character is an 'u')
										
							else{
								thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
								return thisChar;
							}	// end else
						}	// end if (fourth character is a letter)	
								
						else{
							System.out.println("ID: " + stringSoFar);
							return thisChar;
						}	// end else
					}	// end if (third character is an 't')
								
					else{
						thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
						return thisChar;
					}	// end else
				}	// end if (third letter is a character)
									
				else{
					System.out.println("ID: " + stringSoFar);
					return thisChar;
				}	// end else
			}	// end if (second letter is an 'e')
			
			else{
				thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
				return thisChar;
			}	// end else
		}	// end if (second letter is a character)
							
		else{
			System.out.println("ID: " + stringSoFar);
			return thisChar;
		}	// end else, case 'r'
		
		
	case 'v':
		thisChar = (char)inputStream.read();		//read next character
		if(Character.isLetter(thisChar)){				//if it is a letter...
			stringSoFar += thisChar;						//concatenate to stringSoFar
				
			if (thisChar == 'o'){								//if character is an 'o'...
				thisChar = (char)inputStream.read();				//read next character	
				if(Character.isLetter(thisChar)){						//if it is a letter...
					stringSoFar += thisChar;								//concatenate to stringSoFar

					if (thisChar == 'i'){									//if character is an 'i'...
						thisChar = (char)inputStream.read();					//read next character
						if(Character.isLetter(thisChar)){						//if it is a letter...
							stringSoFar += thisChar;								//concatenate to stringSoFar
							
							if(thisChar == 'd'){									//if character is an 'd'...
								thisChar = (char)inputStream.read();					//read next character
								if(Character.isLetter(thisChar)){						//if longer than keyword......
									stringSoFar += thisChar;							//concatenate to stringSoFar
									thisChar = makeID(inputStream, stringSoFar, thisChar);			//call 'makeID' method
									return thisChar;
								}	// end if (longer than a keyword)
									
								else{
									System.out.println("Keyword: " + stringSoFar);
									return thisChar;
								}	// end else
							}	// end if (fourth character is an 'd')
								
							else{
								thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
								return thisChar;
							}	// end else
						}	// end if (fourth character is a letter)	
						
						else{
							System.out.println("ID: " + stringSoFar);
							return thisChar;
						}	// end else
					}	// end if (third character is an 'i')
						
					else{
						thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
						return thisChar;
					}	// end else
				}	// end if (third letter is a character)
							
				else{
					System.out.println("ID: " + stringSoFar);
					return thisChar;
				}	// end else
			}	// end if (second character is an 'o')
				
			else{
				thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
				return thisChar;
			}	// end else
		}	// end if (second character is a letter)
							
		else{
			System.out.println("ID: " + stringSoFar);
			return thisChar;
		}	// end else, case 'v'
		
	case 'w':
		thisChar = (char)inputStream.read();		//read next character
		if(Character.isLetter(thisChar)){				//if it is a letter...
			stringSoFar += thisChar;						//concatenate to stringSoFar
				
			if (thisChar == 'h'){								//if character is an 'h'...
				thisChar = (char)inputStream.read();				//read next character	
				if(Character.isLetter(thisChar)){						//if it is a letter...
					stringSoFar += thisChar;								//concatenate to stringSoFar

					if (thisChar == 'i'){									//if character is an 'i'...
						thisChar = (char)inputStream.read();					//read next character
						if(Character.isLetter(thisChar)){						//if it is a letter...
							stringSoFar += thisChar;								//concatenate to stringSoFar
							
							if(thisChar == 'l'){									//if character is an 'l'...
								thisChar = (char)inputStream.read();					//read next character
								if(Character.isLetter(thisChar)){						//if it is a letter...
									stringSoFar += thisChar;							//concatenate to stringSoFar
									
									if(thisChar == 'e'){									//if character is an 'e'...
										thisChar = (char)inputStream.read();					//read next character
										if(Character.isLetter(thisChar)){						//if longer than keyword......
											stringSoFar += thisChar;							//concatenate to stringSoFar
											thisChar = makeID(inputStream, stringSoFar, thisChar);			//call 'makeID' method
											return thisChar;
										}	// end if (longer than a keyword)
											
										else{
											System.out.println("Keyword: " + stringSoFar);
											return thisChar;
										}	// end else
									}	// end if (fifth character is an 'e')
										
									else{
										thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
										return thisChar;
									}	// end else
								}	// end if (fifth character is a letter)
								
								else{
									System.out.println("ID: " + stringSoFar);
									return thisChar;
								}	// end else
							}	// end if (fourth character is an 'l')
							
							else{
								thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
								return thisChar;
							}	// end else
						}	// end if (fourth letter is a character)
						
						else{
							System.out.println("ID: " + stringSoFar);
							return thisChar;
						}	// end else
					}	// end if (third character is an 'i')
								
					else{
						thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
						return thisChar;
					}	// end else
				}	// end if (third character is a letter)	
						
				else{
					System.out.println("ID: " + stringSoFar);
					return thisChar;
				}	// end else
			}	// end if (second character is an 'h')
						
			else{
				thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
				return thisChar;
			}	// end else
		}	// end if (second letter is a character)
							
		else{
			System.out.println("ID: " + stringSoFar);
			return thisChar;
		}	// end else, case 'w'
						
	}	// end switch
	
	thisChar = makeID(inputStream, stringSoFar, thisChar);				//call 'makeID' method
	return thisChar;
}	// end 'makeKeyword' method
//...............................................................................................................

public static char makeID(StringReader inputStream, String stringSoFar, char thisChar) throws IOException{
	
	thisChar = (char)inputStream.read();						//read next character
	
	if(Character.isLetter(thisChar)){							//if character is a letter
		while(Character.isLetter(thisChar)){					//...
			stringSoFar += thisChar;							//...
			thisChar = (char)inputStream.read();				//keep reading and concatenate as letters are read
		}	//	end while
		
		System.out.println("ID: " + stringSoFar);				//print identifier
		return thisChar;
	}	// end if
	
	System.out.println("ID: " + stringSoFar);					//print identifier
	return thisChar;
}	// end 'makeID' method
//.......................................................................................

public static char makeNUM(StringReader inputStream, String stringSoFar, char thisChar) throws IOException{
	
	String floating;
	
	stringSoFar += thisChar;									//start new string
	thisChar = (char)inputStream.read();						//read next character
	
	if(Character.isDigit(thisChar)){							//if character is a number
		while(Character.isDigit(thisChar)){						//...
			stringSoFar += thisChar;							//...
			thisChar = (char)inputStream.read();				//keep reading and concatenate as numbers are read
		}	//end while
	
		String num = stringSoFar;								//store stringSoFar in 'num'
	
		if(thisChar == '.'){									//if last character read is a decimal point
			stringSoFar += thisChar;							//concatenate decimal to stringSoFar
			thisChar = (char)inputStream.read();				//read next character
		
			if(Character.isDigit(thisChar)){					//if character read is a number...
				while(Character.isDigit(thisChar)){				//...
					stringSoFar += thisChar;					//...
					thisChar = (char)inputStream.read();		//...keep reading and concatenate as numbers are read
				}	//end while
			}		//end if (isDigit)
		
			else{												//if something other than a number is read...							
				System.out.println("NUM: " + num);				//print NUM and value
				System.out.println(".");						//print decimal point 
				return thisChar;								//return with the last read character
			}	//end else
		floating = stringSoFar;	
		} //end if (is ".")
		
		if(thisChar == 'E'){									//if an 'E' was read
			num = stringSoFar;
			stringSoFar += thisChar;							//concatenate to stringSoFar
			thisChar = (char)inputStream.read();				//read next character
			
			if(thisChar == '+' || thisChar == '-'){				//if a plus or minus is read
				char operator = thisChar;						//store plus or minus
				stringSoFar += thisChar;						//concatenate to stringSoFar
				thisChar = (char)inputStream.read();			//read next character
				if(Character.isDigit(thisChar)){				//if a number is read (directly after 'E')
					while(Character.isDigit(thisChar)){			//...
						stringSoFar += thisChar;				//...
						thisChar = (char)inputStream.read();	//...keep reading and concatenate as numbers are read
					} // end while
					
					if(stringSoFar.contains(".")){
						floating = stringSoFar;
						System.out.println("FLOAT: " + stringSoFar);	//print stringSoFar
						return thisChar;								//return last character read
					}	// end if (floating point)
					
					else{
					System.out.println("NUM: " + stringSoFar);			//print stringSoFar
					return thisChar;									//return last character read
					}	// end else (not floating point)
				}	// end if
				
				else{
					if(stringSoFar.contains(".")){
						floating = num;
						System.out.println("FLOAT: " + floating);		//print stringSoFar
						System.out.println("ID: E");					//print 'E'
						System.out.println(operator);					//print plus or minus
						return thisChar;								//return last character read
					}	// end if (floating point)
					
					else{
					System.out.println("NUM: " + stringSoFar);		//print stringSoFar
					System.out.println("ID: E");					//print 'E'
					System.out.println(operator);					//print plus or minus
					return thisChar;								//return last character read
					}	// end else (not floating point)
					
				}	//end else
			}	// end if (plus or minus was read)
			
			else if(Character.isDigit(thisChar)){				//if a number is read (directly after 'E')
				while(Character.isDigit(thisChar)){				//...
					stringSoFar += thisChar;					//...
					thisChar = (char)inputStream.read();		//...keep reading and concatenate as numbers are read
				} // end while
				
				if(stringSoFar.contains(".")){									//if a floating point number...
					floating = stringSoFar;
					System.out.println("FLOAT: " + stringSoFar);				//print "FLOAT: (stringSoFar)"
					return thisChar;											//return last character read
				}	// end if (floating point)
				
				else{											//if an integer...
				System.out.println("NUM: " + stringSoFar);		//print "NUM (stringSoFar)"
				return thisChar;								//return last character read
				}	// end else (not floating point)
			}	// end else if
			
			else{												//if a number isn't read after 'E'
				if(stringSoFar.contains(".")){					
					floating = num;
					System.out.println("FLOAT: " + floating);		//print "FLOAT: (stringSoFar)"
					System.out.println("ID: E");					//print 'E'
					return thisChar;								//return last character read
				}	// end if (floating point)
				
				else{
				System.out.println("NUM: " + stringSoFar);		//print "NUM: (stringSoFar)"
				System.out.println("ID: E");					//print 'E'
				return thisChar;								//return last character read
				}	// end else (not floating point)
			}	
		}	// end if ('E' was read)
	}	// end if (number was read after first entering method)
	
	else{														//if token is only a single digit number
		System.out.println("NUM: " + stringSoFar);
		return thisChar;
	}	// end else
	
	
	if(stringSoFar.contains(".")){								//if multiple digit floating point (no 'E')
		floating = stringSoFar;
		System.out.println("FLOAT: " + stringSoFar);			//print "FLOAT: (stringSoFar)"
		return thisChar;										//return last character read
	}	// end if (floating point)
	
	else{														//if multiple digit integer (no 'E')
	System.out.println("NUM: " + stringSoFar);					//print "NUM: (stringSoFar)"
	return thisChar;											//return last character read
	}	// end else (not floating point)
}	// end 'makeNUM' method
//...............................................................................

public static char spcChar(StringReader inputStream, String stringSoFar, char thisChar) throws IOException{

	if(thisChar == '<' || thisChar == '>' || thisChar == '='){	//if it can be a two-character symbol
		stringSoFar += thisChar;
		thisChar = (char)inputStream.read();					//read next character
		if(thisChar == '='){									//if it is '='....
			stringSoFar += thisChar;							//concatenate to stringSoFar
			System.out.println(stringSoFar);					//print stringSoFar
			thisChar = (char)inputStream.read();				//read next character
			return thisChar;
		}	// end if 
	
		else{													//if character is not '='
			System.out.println(stringSoFar);					//print stringSoFar
			return thisChar;									
		}	// end else
	}	// end if
	
	else if(thisChar == '!'){
		stringSoFar += thisChar;
		thisChar = (char)inputStream.read();					//read next character
		if(thisChar == '='){									//if it is '='....
			stringSoFar += thisChar;							//concatenate to stringSoFar
			System.out.println(stringSoFar);					//print stringSoFar
			thisChar = (char)inputStream.read();				//read next character
			return thisChar;
		}	// end if ('!=' is read)
	
		else if(Character.isDigit(thisChar) || Character.isLetter(thisChar)){				//if a number or letter is read
			while(Character.isDigit(thisChar) || Character.isLetter(thisChar)){				//...
				stringSoFar += thisChar;					//...
				thisChar = (char)inputStream.read();		//...keep reading and concatenate as numbers and letters are read
			} // end while
			
			System.out.println("Error: " + stringSoFar);		//print stringSoFar
			return thisChar;									
		}	// end else if
		
		else{
			System.out.println("Error: " + stringSoFar);		//print '!'
		}
	}	// end else if ('!' is read)
	
	else if(thisChar == '+' || thisChar == '-' || thisChar == '*' || thisChar == ';' || thisChar == ',' || thisChar == '(' || thisChar == ')' || thisChar == '[' || thisChar == ']' || thisChar == '{' || thisChar == '}'){
		stringSoFar += thisChar;							//concatenate to stringSoFar
		System.out.println(stringSoFar);					//print stringSoFar
		thisChar = (char)inputStream.read();				//read next character
		return thisChar;
	}	// end else if
	
	else{													//non-valid character is read
		stringSoFar += thisChar;							//concatenate to strtingSoFar
		thisChar = (char)inputStream.read();				//read next character...
		while(Character.isDigit(thisChar) || Character.isLetter(thisChar)){				//...
			stringSoFar += thisChar;						//...
			thisChar = (char)inputStream.read();			//...keep reading and concatenate as numbers and letters are read
		} // end while
		System.out.println("Error: " + stringSoFar);		//print error
		return thisChar;
	}	// end else
	return thisChar;
}	// end 'spcChar' method
//................................................................................................

public static StringReader comment(BufferedReader bufInput, StringReader inputStream, String stringSoFar, char thisChar) throws IOException{
	
	int commentCount = 0;
	if(thisChar == '/'){									//if it is '/' (meaning single line comment)
		while((int)thisChar != 65535){						//then, until the end of the line...
			thisChar = (char)inputStream.read();			//...keep reading characters
		}	// end while
		return inputStream;
	}	// end if (single-line comment)
	
	else{													//if character is '*' (meaning block comment)
		commentCount++;										//nested comment level +1
		while(commentCount > 0){							//while nested comment level > 0
			thisChar = (char)inputStream.read();				//read next character
			while(thisChar != '*' && thisChar != '/'){			//until a * or / is read...
				thisChar = (char)inputStream.read();				//...keep reading characters
				if((int)thisChar == 65535){							//if end of line is reached...
					currentLine = bufInput.readLine();								//read next line
					System.out.println("INPUT: " + currentLine);					//print "INPUT: (line that was just read)"
					inputStream = new StringReader(currentLine);					//read characters on this line
				}
			}	// end while (a '*' or '/' hasn't been read)
			
			if(thisChar == '/'){									//if '/' was read
				thisChar = (char)inputStream.read();				//read next character
				if(thisChar == '*'){								//if '*' is read
					commentCount++;									//nested comment level +1
				}	// end if (nested comment level +1)
			}	//end if
			
			else{													//if '*' was read
				thisChar = (char)inputStream.read();				//read next character
				if(thisChar == '/'){								//if '/' is read
					commentCount--;									//nested comment level -1
				}	// end if (nested comment level decrements)
				else if(thisChar == '*'){							//if multiple '*' in a row...
					while(thisChar == '*'){							//...
						thisChar = (char)inputStream.read();		//...
					}	// end while								//...keep reading '*' until something else is read
					if(thisChar == '/'){							//if a '/' is read
						commentCount--;								//nested comment level -1
					}	// end if (nested comment level decrements)
				}	// end else if (multiple * in a row)
				else{
					;
				}	// end else
			}	// end else
		}	// end while (commentCount > 0)
		return inputStream;									
	}	// end else if (block comment)
		
}	// end 'comment' method

}	// end Compiler class