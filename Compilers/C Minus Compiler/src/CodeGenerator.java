import java.util.ArrayList;
//import java.io.BufferedReader;
//import java.io.StringReader;
import java.io.IOException;

public class CodeGenerator {
	public static ArrayList<Token> listOfTokens;
	public static int index;
	public static String[][] iCodeTable = new String[50][4];
	public static int lineNum = 0;			//needed especially for branch
	public static Token funcItem;			//used to store functions in the symbol table
	public static int numOfParams;
	public static String tempVal;			//stores total of simple expression
	public static int tempCount;			//number of temp values created so far
	//public static String op1;					//first operand in a  simple expression
	public static String op2;					//second operand in a simple expression
	//public static String operator;				//operator
	public static int numOfExp;
	public static SymbolTable symTable = new SymbolTable();
	public static int depth = 0;
	public static int tempDepth = 0;
	
	public static void main(String[] args) throws IOException{
		
		Token currentToken;
		String filename = args[0];
		index = 0;
		try{
			LexicalAnalyzer.LexicalAnalysis(filename);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		listOfTokens = LexicalAnalyzer.tokenList;
		
		//first check for declaration-list
		if(index == listOfTokens.size()){
			//System.out.println("ACCEPT");
			System.exit(0);
		}
		currentToken = listOfTokens.get(index);
		
		if(currentToken.tName.equals("int") || currentToken.tName.equals("float") || currentToken.tName.equals("void")){
				index++;
				currentToken = listOfTokens.get(index);
				declaration(listOfTokens, currentToken);
				index++;
				if(index == listOfTokens.size()){
					for(int i = 0; i < lineNum; i++){
						System.out.println(iCodeTable[i]);
					}
					//System.out.println("ACCEPT");
				}
				else{
					currentToken = listOfTokens.get(index);
					declarationListPrime(listOfTokens, currentToken);
				}
		} // end if
		for(int i = 0; i < lineNum; i++){
			System.out.println(iCodeTable[i][0].toString() + "\t" + iCodeTable[i][1].toString() + "\t" + iCodeTable[i][2].toString() + "\t" + iCodeTable[i][3].toString() );
		}
		//System.out.println("ACCEPT");
		System.exit(0);
	} // end main
//.......................................................................................................
	
	public static void declarationListPrime(ArrayList<Token> listOfTokens, Token thisToken){
		if(thisToken.tName.equals("int") || thisToken.tName.equals("float") || thisToken.tName.equals("void")){
			index++;
			thisToken = listOfTokens.get(index);
			declaration(listOfTokens, thisToken);
			index++;
			if(index == (listOfTokens.size())){
				return;
			}
			else{
				thisToken = listOfTokens.get(index);
				declarationListPrime(listOfTokens, thisToken);
				return;
			}
		} // end if
	} // end declarationListPrime
	
//.....................................................................................................
	
	public static void declaration(ArrayList<Token> listOfTokens, Token thisToken){
		
		if(thisToken.tType.equals("id")){		//if type-identifier --> ID
			index++;							//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals(";") || thisToken.tName.equals("[")){
				varDeclaration(listOfTokens, thisToken);
				return;
			} //end if ";" or "["
			else if(thisToken.tName.equals("(")){
				funcItem = listOfTokens.get((index - 1));
				funDeclaration(listOfTokens, thisToken);
				return;
			} //end else if "("
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} //end if "int", "float", or "void"
		
		else{
			System.out.println("REJECT");
			System.exit(0);
			return;
		}
		
	} // end declaration
//......................................................................................................
	
	public static void varDeclaration(ArrayList<Token> listOfTokens, Token thisToken){
		
		if(thisToken.tName.equals(";")){
			symTable.currTable.insert(listOfTokens.get((index - 1)));
			
			iCodeTable[lineNum][0] = "Alloc";
			iCodeTable[lineNum][1] = "4";
			iCodeTable[lineNum][2] = "";
			iCodeTable[lineNum][3] = listOfTokens.get((index - 1)).tName;
			lineNum++;
			return;
		}
		
		else{	//thisToken is "["
			symTable.currTable.insert(listOfTokens.get((index - 1)));
			
			index++;								//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			
			if(thisToken.tType.equals("num")){			//if "num"
				iCodeTable[lineNum][0] = "Alloc";
				iCodeTable[lineNum][1] = "" + (4 * Integer.parseInt(thisToken.tName));
				iCodeTable[lineNum][2] = "";
				iCodeTable[lineNum][3] = listOfTokens.get((index - 2)).tName;
				lineNum++;
				
				index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
			
				if(thisToken.tName.equals("]")){				//if "]"
					index++;								//accept and...
					thisToken = listOfTokens.get(index);	//get next token
				
					if(thisToken.tName.equals(";")){				//if ";"
						return;
					} //end if ";"
					else{
						System.out.println("REJECT");
						System.exit(0);
					}
				} //end if "]"
				else{
					System.out.println("REJECT");
					System.exit(0);
				}
			} //end if "num"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} //end else "["
	} // end varDeclaration
//.....................................................................................................
	
	public static void funDeclaration(ArrayList<Token> listOfTokens, Token thisToken){
		index++;								
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("int") || thisToken.tName.equals("float") || thisToken.tName.equals("void")){
			
			params(listOfTokens, thisToken);
			iCodeTable[lineNum][0] = "Func";
			iCodeTable[lineNum][1] = "" + numOfParams;
			iCodeTable[lineNum][2] = "";
			iCodeTable[lineNum][3] = funcItem.tName;
			lineNum++;
			//index++;								
			thisToken = listOfTokens.get(index);	//get next token
			
			if(thisToken.tName.equals(")")){
				numOfParams = 0;
				index++;								//accept and...		
				thisToken = listOfTokens.get(index);	//get next token
				
				if(thisToken.tName.equals("{")){
					compoundStmt(listOfTokens, thisToken);
					iCodeTable[lineNum][0] = "END";
					iCodeTable[lineNum][1] = funcItem.tName;
					iCodeTable[lineNum][2] = "";
					iCodeTable[lineNum][3] = "";
					lineNum++;
					return;
				} //end if "{"
				else{
					System.out.println("REJECT");
					System.exit(0);
				}
			} //end if ")"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} //end if "int", "float", or "void"
		else{
			System.out.println("REJECT");
			System.exit(0);
		}
	} // end funDeclaration
//.....................................................................................................

	public static void params(ArrayList<Token> listOfTokens, Token thisToken){
		if(thisToken.tName.equals("int") || thisToken.tName.equals("float")){	
			param(listOfTokens, thisToken);
			paramListPrime(listOfTokens, thisToken);
			return;
		} //end if "int" or "float"
		else{	// "void"
			index++;								//accept and...		
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals(")")){
				//index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				return;
			} //end if "id"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		}
	} // end params
//.....................................................................................................
	
	public static void param(ArrayList<Token> listOfTokens, Token thisToken){
		index++;								//accept and...
		thisToken = listOfTokens.get(index);	//get next token
			
		if(thisToken.tType.equals("id")){
			index++;								//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			numOfParams++;
			
			if(thisToken.tName.equals("[")){
				index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				
				if(thisToken.tName.equals("]")){
					index++;
					return;
				} //end if "]"
				else{
					System.out.println("REJECT");
					System.exit(0);
				}
			} //end if "["
			else{
				return;
			}
		} //end if "id"
		else{
			System.out.println("REJECT");
			System.exit(0);
		}
	} // end param
//.....................................................................................................

	public static void paramListPrime(ArrayList<Token> listOfTokens, Token thisToken){
		//index++;								
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals(",")){
			index++;								//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			
			if(thisToken.tName.equals("int") || thisToken.tName.equals("float") || thisToken.tName.equals("void")){
				param(listOfTokens, thisToken);
				paramListPrime(listOfTokens, thisToken);
				return;
			} // end if "it", "float", or "void"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} // end if ","
		else{
			return;
		}
	} // end paramListPrime
//...................................................................................................................

	public static void compoundStmt(ArrayList<Token> listOfTokens, Token thisToken){
		symTable.incDepth();
		depth++;
		localDeclarationsPrime(listOfTokens, thisToken);
		index--;
		statementListPrime(listOfTokens, thisToken);
		thisToken = listOfTokens.get(index);
		if(thisToken.tName.equals("}")){
			symTable.decDepth();
			depth--;
			return;
		}
		else{
			System.out.println("REJECT");
			System.exit(0);
		}
	} // end compoundStmt
//....................................................................................................................

	public static void localDeclarationsPrime(ArrayList<Token> listOfTokens, Token thisToken){
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("int") || thisToken.tName.equals("float") || thisToken.tName.equals("void")){
			index++;							//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tType.equals("id")){		//if localDeclarationsPrime-->varDeclaration
				index++;							//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				if(thisToken.tName.equals(";") || thisToken.tName.equals("[")){
					varDeclaration(listOfTokens, thisToken);
					localDeclarationsPrime(listOfTokens, thisToken);
					return;
				} //end if ";" or "["
				else{
					System.out.println("REJECT");
					System.exit(0);
				}
			} //end if "id"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		}
		else{
			return;
		}
	} // end localDeclarationsPrime
//.....................................................................................................
	
	public static void statementListPrime(ArrayList<Token> listOfTokens, Token thisToken){
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tType.equals("id") || thisToken.tName.equals("(") || thisToken.tType.equals("num")|| thisToken.tType.equals("float") || thisToken.tName.equals("{") || thisToken.tName.equals("if") ||thisToken.tName.equals("while") ||thisToken.tName.equals("return")){
			statement(listOfTokens, thisToken);
			statementListPrime(listOfTokens, thisToken);
			return;
		}
		else{
			return;
		}
	} // end statementListPrime
//.....................................................................................................
	
	public static void statement(ArrayList<Token> listOfTokens, Token thisToken){
		if(thisToken.tType.equals("id") || thisToken.tName.equals("(") || thisToken.tType.equals("num")|| thisToken.tType.equals("float") || thisToken.tName.equals(";")){
			expressionStmt(listOfTokens, thisToken);
			return;
		}
		else if(thisToken.tName.equals("{")){
			compoundStmt(listOfTokens, thisToken);
			return;
		}
		else if(thisToken.tName.equals("if")){
			selectionStmt(listOfTokens, thisToken);
			return;
		}
		else if(thisToken.tName.equals("while")){
			int whileLine = lineNum;
			iterationStmt(listOfTokens, thisToken);
			iCodeTable[(lineNum - 1)][3] = "" + (whileLine);
			return;
		}
		else{
			returnStmt(listOfTokens, thisToken);
			return;
		}
	} // end statement
//.....................................................................................................
	
	public static void expressionStmt(ArrayList<Token> listOfTokens, Token thisToken){
		if(thisToken.tType.equals("id") || thisToken.tName.equals("(") || thisToken.tType.equals("num")|| thisToken.tType.equals("float")){
			expression(listOfTokens, thisToken);
			//index++;
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals(";")){
				return;
			} //end if ";"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} //end if 'expression'
		else{
			index++;
			thisToken = listOfTokens.get(index);	//get next token
			return;
		}
	} // end expressionStmt
//.....................................................................................................
	
	public static void expression(ArrayList<Token> listOfTokens, Token thisToken){
		
		int temp = index;
		String lftExp;								//left side of expression
		String rghtExp;								//right side of expression
		if(thisToken.tType.equals("id")){
			index++;								//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals("=")){
				lftExp = listOfTokens.get(index - 1).tName;
				index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				expression(listOfTokens, thisToken);
				rghtExp = tempVal;
				iCodeTable[lineNum][0] = "ASSN";
				iCodeTable[lineNum][1] = rghtExp;
				iCodeTable[lineNum][2] = "";
				iCodeTable[lineNum][3] = lftExp;
				lineNum++;
				return;
			} //end if "=" (given var-->ID)
			
			else if(thisToken.tName.equals("[")){
				index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				expression(listOfTokens, thisToken);
				thisToken = listOfTokens.get(index);	//get next token;
				if(thisToken.tName.equals("]")){
					index++;								//accept and...
					thisToken = listOfTokens.get(index);	//get next token
					
					if(thisToken.tName.equals("=")){
						index++;								//accept and
						thisToken = listOfTokens.get(index);	//get next token
						expression(listOfTokens, thisToken);
						return;
					} //end if "=" (given expression-->var = expression and var-->ID[expression])
					else if(thisToken.tName.equals(";")){
						return;
					}
					else{ //expression-->simpleExpression-->...-->var-->ID[expression]
						index = temp;
						thisToken = listOfTokens.get(index);	//get previous token
						simpleExpression(listOfTokens, thisToken);
						return;
					}
				} //end if "]" (given var-->ID[expression]
				else{
					System.out.println("REJECT");
					System.exit(0);
				}
			} //end else if "["
			
			else if(thisToken.tName.equals(";")){
				tempVal = listOfTokens.get(index - 1).tName;
				return;
			}
			else{
				index--;
				thisToken = listOfTokens.get(index);	//get previous token
				simpleExpression(listOfTokens, thisToken);
				return;
			}
		} //end if "id"
		
		else if(thisToken.tName.equals("(")){
			simpleExpression(listOfTokens, thisToken);
		} //end else if "("
		
		else{ // "num" or "float"
			simpleExpression(listOfTokens, thisToken);
		}
	} // end expression
//.....................................................................................................
	//static String op1;
	//String op2;
	
	public static void simpleExpression(ArrayList<Token> listOfTokens, Token thisToken){
		String op1 = "";
		String operator;
		
		additiveExpression(listOfTokens, thisToken);
		op1 = tempVal;
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("<=") || thisToken.tName.equals("<") || thisToken.tName.equals(">") || thisToken.tName.equals(">=") || thisToken.tName.equals("==") || thisToken.tName.equals("!=")){
			operator = thisToken.tName;
			index++;								//accept and
			thisToken = listOfTokens.get(index);	//get next token
			additiveExpression(listOfTokens, thisToken);
			op2 = tempVal;
			iCodeTable[lineNum][0] = "COMP";
			iCodeTable[lineNum][1] = op1;
			iCodeTable[lineNum][2] = op2;
			iCodeTable[lineNum][3] = "t" + tempCount;
			lineNum++;
			
			if(operator.contains("<")){
				iCodeTable[lineNum][0] = "BLT";
				iCodeTable[lineNum][1] = "";
				iCodeTable[lineNum][2] = "";
				iCodeTable[lineNum][3] = "" + (lineNum + 2);
				lineNum++;
				
				iCodeTable[lineNum][0] = "BR";
				iCodeTable[lineNum][1] = "";
				iCodeTable[lineNum][2] = "";
				iCodeTable[lineNum][3] = "";
				lineNum++;
			}
			
			else{
				iCodeTable[lineNum][0] = "BGT";
				iCodeTable[lineNum][1] = "";
				iCodeTable[lineNum][2] = "";
				iCodeTable[lineNum][3] = "" + (lineNum + 2);
				lineNum++;
				
				iCodeTable[lineNum][0] = "BR";
				iCodeTable[lineNum][1] = "";
				iCodeTable[lineNum][2] = "";
				iCodeTable[lineNum][3] = "";
				lineNum++;
			}
			
			return;
		} //end if 'additiveExpression-->relop'
		else{
			return;
		}
	} // end simpleExpression
//.....................................................................................................
	
	public static void additiveExpression(ArrayList<Token> listOfTokens, Token thisToken){
		String op1;
		String operator;
		//String op2;
		
		term(listOfTokens, thisToken);
		op1 = tempVal;
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("+") || thisToken.tName.equals("-")){
			operator = thisToken.tName;
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			term(listOfTokens, thisToken);
			op2 = tempVal;
			//tempCount++;
			if(operator.equals("+")){
				iCodeTable[lineNum][0] = "ADD";
			}
			else{
				iCodeTable[lineNum][0] = "SUB";
			}
			iCodeTable[lineNum][1] = op1;
			iCodeTable[lineNum][2] = op2;
			iCodeTable[lineNum][3] = "t" + tempCount;
			tempVal = "t" + tempCount;
			tempCount++;
			lineNum++;
			additiveExpressionPrime(listOfTokens, thisToken);
			return;
		} //end if "+" or "-"
		else{
			index--;
			return;
		}
	}
//.....................................................................................................
	
	public static void additiveExpressionPrime(ArrayList<Token> listOfTokens, Token thisToken){
		String op1 = "";
		String operator = "";
		//numOfExp++;
		
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("+") || thisToken.tName.equals("-")){
			operator = thisToken.tName;
			op1 = tempVal;
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			term(listOfTokens, thisToken);
			op2 = tempVal;
			if(operator.equals("+")){
				iCodeTable[lineNum][0] = "ADD";
			}
			else{
				iCodeTable[lineNum][0] = "SUB";
			}
			iCodeTable[lineNum][1] = op1;
			iCodeTable[lineNum][2] = op2;
			iCodeTable[lineNum][3] = "t" + tempCount;
			tempVal = "t" + tempCount;
			tempCount++;
			lineNum++;
			additiveExpressionPrime(listOfTokens, thisToken);
			return;
		} //end if "+" or "-"
		else{
			index--;
			return;
		}
	}
//.....................................................................................................
	
	
	public static void term(ArrayList<Token> listOfTokens, Token thisToken){
		String op1 = "";
		String operator;
		
		factor(listOfTokens, thisToken);
		op1 = tempVal;
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("*") || thisToken.tName.equals("/")){
			operator = thisToken.tName;
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			factor(listOfTokens, thisToken);
			op2 = tempVal;
			//tempCount++;
			if(operator.equals("*")){
				iCodeTable[lineNum][0] = "MULT";
			}
			else{
				iCodeTable[lineNum][0] = "DIV";
			}
			iCodeTable[lineNum][1] = op1;
			iCodeTable[lineNum][2] = op2;
			iCodeTable[lineNum][3] = "t" + tempCount;
			tempVal = "t" + tempCount;
			tempCount++;
			lineNum++;
			termPrime(listOfTokens, thisToken);
			return;
		} //end if "*" or "/"
		else{
			index--;
			return;
		}
	}
//.....................................................................................................
	
	public static void termPrime(ArrayList<Token> listOfTokens, Token thisToken){
		String op1 = "";
		String operator;
		
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("*") || thisToken.tName.equals("/")){
			operator = thisToken.tName;
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			factor(listOfTokens, thisToken);
			op2 = tempVal;
			if(operator.equals("*")){
				iCodeTable[lineNum][0] = "MULT";
			}
			else{
				iCodeTable[lineNum][0] = "DIV";
			}
			iCodeTable[lineNum][1] = op1;
			iCodeTable[lineNum][2] = op2;
			iCodeTable[lineNum][3] = "t" + tempCount;
			tempVal = "t" + tempCount;
			tempCount++;
			lineNum++;
			termPrime(listOfTokens, thisToken);
			return;
		} //end if "*" or "/"
		else{
			index--;
			return;
		}
	}
//.....................................................................................................
	
	public static void factor(ArrayList<Token> listOfTokens, Token thisToken){
		//index++;
		
		tempDepth = depth;
		
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("(")){
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			expression(listOfTokens, thisToken);
			index++;
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals(")")){
				return;
			} //end if ")"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} //end if "("
		
		else if(thisToken.tType.equals("id")){		
			
			if(symTable.Check(symTable.currTable, thisToken) == true){
				 tempVal = thisToken.tName;
			}
			else{									
				if(depth > 0){
					while(depth > 0){
						symTable.goUp();
						depth--;
						if(symTable.Check(symTable.currTable, thisToken) == true){
							while(depth < tempDepth){
								symTable.goDown();
								depth++;
							}
							tempVal = thisToken.tName;
							break;
						}
						else{
							;//variable not declared (shouldn't happen)
						}
					}
				}
			}
			
			index++;
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals("[")){			//factor-->var-->ID [expression]
				index++;								//accept and....
				thisToken = listOfTokens.get(index);	//get next token
				iCodeTable[lineNum][0] = "Disp";
				iCodeTable[lineNum][1] = listOfTokens.get((index - 2)).tName;
				iCodeTable[lineNum][2] = "" + (4 * Integer.parseInt(thisToken.tName));
				iCodeTable[lineNum][3] = "t" + tempCount;
				lineNum++;
				tempVal = "t" + tempCount;
				tempCount++;
				expression(listOfTokens, thisToken);
				//index++;
				thisToken = listOfTokens.get(index);	//get next token
				if(thisToken.tName.equals("]")){
					tempVal = "t" + (tempCount - 1);
					return;
				} //end if "]"
				else{
					System.out.println("REJECT");
					System.exit(0);
				}	
			} //end if "["
			
			else if(thisToken.tName.equals("(")){		//factor-->call
				index++;								//accept and....
				thisToken = listOfTokens.get(index);	//get next token
				args(listOfTokens, thisToken);
				thisToken = listOfTokens.get(index);	//get next token
				if(thisToken.tName.equals(")")){
					return;
				} //end if "]"
				else{
					System.out.println("REJECT");
					System.exit(0);
				}
			}// end else if "("
			
			else{ //factor-->var-->ID
				tempVal = listOfTokens.get(index - 1).tName;
				index--;
				return;
			}
		} //end else if "id"
		
		else if(thisToken.tType.equals("num") || thisToken.tType.equals("float")){
			tempVal = thisToken.tName;
			//index++;
			return;
		} //end else if "num" or "float"
		
		else{
			System.out.println("REJECT");
			System.exit(0);
		}
	} // end factor
//.....................................................................................................
	
	public static void args(ArrayList<Token> listOfTokens, Token thisToken){
		if(thisToken.tName.equals(")")){ //args-->empty
			return;
		} //end if ")"
		
		else{
			expression(listOfTokens, thisToken);
			//index++;
			thisToken = listOfTokens.get(index);	//get next token
			argsListPrime(listOfTokens, thisToken);
			return;
		}
	} // end args
//.....................................................................................................	
	
	public static void argsListPrime(ArrayList<Token> listOfTokens, Token thisToken){
		if(thisToken.tName.equals(",")){
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			expression(listOfTokens, thisToken);
			//index--;
			thisToken = listOfTokens.get(index);	//get next token
			argsListPrime(listOfTokens, thisToken);
			return;
		}
		else{
			return;
		}
	}
//.....................................................................................................
	
	public static void selectionStmt(ArrayList<Token> listOfTokens, Token thisToken){
		int elseLine = 0;
		
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("(")){
			index++;
			thisToken = listOfTokens.get(index);
			expression(listOfTokens, thisToken);
			index++;
			thisToken = listOfTokens.get(index);	//get next token
			
			if(thisToken.tName.equals(")")){
				index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				
				elseLine = lineNum;
				statement(listOfTokens, thisToken);
				index++;
				thisToken = listOfTokens.get(index);	//get next token
				iCodeTable[(elseLine - 1)][1] = "" + (lineNum + 1);
				elseLine = lineNum;
				
				if(thisToken.tName.equals("else")){
					iCodeTable[lineNum][0] = "BR";
					iCodeTable[lineNum][1] = "";
					iCodeTable[lineNum][2] = "";
					iCodeTable[lineNum][3] = "";
					lineNum++;
					index++;								//accept and...
					thisToken = listOfTokens.get(index);	//get next token
					statement(listOfTokens, thisToken);
					iCodeTable[(elseLine)][1] = "" + (lineNum + 1);
					return;
				} //end if "else"
				else{
					index--;
					return;
				}
			} //end if ")"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} //end if "("
		else{
			System.out.println("REJECT");
			System.exit(0);
		}
	} // end selectionStmt
//.....................................................................................................
	
	public static void iterationStmt(ArrayList<Token> listOfTokens, Token thisToken){
		int tempLine = lineNum;
		
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("(")){
			index++;								//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			expression(listOfTokens, thisToken);
			index++;
			thisToken = listOfTokens.get(index);	//get next token
			
			
			if(thisToken.tName.equals(")")){
				tempLine = lineNum;
				index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				statement(listOfTokens, thisToken);
				iCodeTable[lineNum][0] = "BR";
				iCodeTable[lineNum][1] = "";
				iCodeTable[lineNum][2] = "";
				iCodeTable[lineNum][3] = "" + tempLine;
				lineNum++;
				thisToken = listOfTokens.get(index);
				iCodeTable[(tempLine - 1)][3] = "" + (lineNum);
				return;
			} //end if ")"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} //end if "("
		else{
			System.out.println("REJECT");
			System.exit(0);
		}
	} // end iterationStmt
//.....................................................................................................
	
	public static void returnStmt(ArrayList<Token> listOfTokens, Token thisToken){
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals(";")){
			return;
		}
		else if(thisToken.tType.equals("id") || thisToken.tName.equals("(") || thisToken.tType.equals("num") || thisToken.tType.equals("float")){
			expression(listOfTokens, thisToken);
			//index++;
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals(";")){
				return;
			} //end if ";"
			else{
				System.out.println("REJECT");
				System.exit(0);
			}
		} // end else if 'expression'
		else{
			System.out.println("REJECT");
			System.exit(0);
		}
	} // end returnStmt
//.....................................................................................................
} // end Parser
///////////////////////////////////////////////////////////////////////////////////////////////////////


class SymbolTable{
	public static HashTable table; 
	public HashTable currTable;
	public static HashTable nextTable;
	public HashTable prevTable;
	public String idName;
	
	SymbolTable(){
		currTable = new HashTable(50);
	}
	
	boolean Check(HashTable tabl, Token currToken){
		idName = currToken.tName;
		
		if(tabl.find(idName) == null){
			return false;
		}
		else{
			return true;
		}
	}
	
	void incDepth(){
		table = new HashTable(50);
		table.prevTable = currTable;
		currTable.nextTable = table;
		currTable = table;
	}
	
	void decDepth(){
		table = currTable;
		currTable = table.prevTable;
	}
	
	void goUp(){
		currTable = currTable.prevTable;
	}
	
	void goDown(){
		currTable = currTable.nextTable;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////////

class HashTable {
	public Token[] inputList;
	public int listSize;
	public HashTable currTable;
	public HashTable nextTable;
	public HashTable prevTable;
	
	HashTable(int size){
		listSize = size;
		inputList = new Token[listSize];
		
	}
	
	public int hashFunc(String key){
		int sum = key.hashCode();
		sum = sum % listSize;
		return sum;
	}

	public void insert(Token token){
		String keyName = token.tName;
		int hashVal = hashFunc(keyName);
		while(inputList[hashVal] != null){
			++hashVal;
			hashVal %= listSize;
		}
		
		inputList[hashVal] = token;
		
		//System.out.println("stored " + token.tName + " " + " at location " + hashVal);
	}


	public Token find(String searchString){
		int hashVal = hashFunc(searchString);
		
		if(inputList[hashVal] != null){
			while(inputList[hashVal] != null){
				if((inputList[hashVal].tName).equalsIgnoreCase(searchString)){
					//System.out.println(searchString + " was found at location " + hashVal);
					return inputList[hashVal];
				}
				else{
					++hashVal;
					hashVal %= listSize;
				}
			}	
			return inputList[hashVal];
		}
		
		else{
			return null;
		}
	}	

	public boolean check(String sampleString, HashTable sampleTable){
		int x = sampleString.lastIndexOf(" ");
		String chString = (sampleString.substring(0,x));
		int hashVal = hashFunc(chString);
	
		if(inputList[hashVal] != null){
			while(inputList[hashVal] != null){
				if((inputList[hashVal].tName).equalsIgnoreCase(chString)){
					//System.out.println(chString + " already exists at location " + hashVal);
					return true;
				}
				else{
					++hashVal;
					hashVal %= listSize;
				}	
			}
		}
		return false;
	}
}

