import java.util.ArrayList;
//import java.io.BufferedReader;
//import java.io.StringReader;
import java.io.IOException;

public class Parser {
	public static ArrayList<Token> listOfTokens;
	public static int index;
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
			System.out.println("ACCEPT");
			System.exit(0);
		}
		currentToken = listOfTokens.get(index);
		
		if(currentToken.tName.equals("int") || currentToken.tName.equals("float") || currentToken.tName.equals("void")){
				index++;
				currentToken = listOfTokens.get(index);
				declaration(listOfTokens, currentToken);
				index++;
				if(index == listOfTokens.size()){
					System.out.println("ACCEPT");
				}
				else{
					currentToken = listOfTokens.get(index);
					declarationListPrime(listOfTokens, currentToken);
				}
		} // end if
		System.out.println("ACCEPT");
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
			return;
		}
		
		else{	//thisToken is "["
			index++;								//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			
			if(thisToken.tType.equals("num")){			//if "num"
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
			//index++;								
			thisToken = listOfTokens.get(index);	//get next token
			
			if(thisToken.tName.equals(")")){
				index++;								//accept and...		
				thisToken = listOfTokens.get(index);	//get next token
				
				if(thisToken.tName.equals("{")){
					compoundStmt(listOfTokens, thisToken);
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
				index++;								//accept and...
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
		localDeclarationsPrime(listOfTokens, thisToken);
		index--;
		statementListPrime(listOfTokens, thisToken);
		thisToken = listOfTokens.get(index);
		if(thisToken.tName.equals("}")){
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
			iterationStmt(listOfTokens, thisToken);
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
		
		if(thisToken.tType.equals("id")){
			index++;								//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals("=")){
				index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				expression(listOfTokens, thisToken);
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
	
	public static void simpleExpression(ArrayList<Token> listOfTokens, Token thisToken){
		additiveExpression(listOfTokens, thisToken);
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("<=") || thisToken.tName.equals("<") || thisToken.tName.equals(">") || thisToken.tName.equals(">=") || thisToken.tName.equals("==") || thisToken.tName.equals("!=")){
			index++;								//accept and
			thisToken = listOfTokens.get(index);	//get next token
			additiveExpression(listOfTokens, thisToken);
			return;
		} //end if 'additiveExpression-->relop'
		else{
			return;
		}
	} // end simpleExpression
//.....................................................................................................
	
	public static void additiveExpression(ArrayList<Token> listOfTokens, Token thisToken){
		term(listOfTokens, thisToken);
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("+") || thisToken.tName.equals("-")){
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			term(listOfTokens, thisToken);
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
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("+") || thisToken.tName.equals("-")){
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			term(listOfTokens, thisToken);
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
		factor(listOfTokens, thisToken);
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("*") || thisToken.tName.equals("/")){
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			factor(listOfTokens, thisToken);
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
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("*") || thisToken.tName.equals("/")){
			index++;								//accept and....
			thisToken = listOfTokens.get(index);	//get next token
			factor(listOfTokens, thisToken);
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
			index++;
			thisToken = listOfTokens.get(index);	//get next token
			if(thisToken.tName.equals("[")){			//factor-->var-->ID [expression]
				index++;								//accept and....
				thisToken = listOfTokens.get(index);	//get next token
				expression(listOfTokens, thisToken);
				//index++;
				thisToken = listOfTokens.get(index);	//get next token
				if(thisToken.tName.equals("]")){
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
				index--;
				return;
			}
		} //end else if "id"
		
		else if(thisToken.tType.equals("num") || thisToken.tType.equals("float")){
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
				statement(listOfTokens, thisToken);
				index++;
				thisToken = listOfTokens.get(index);	//get next token
				
				if(thisToken.tName.equals("else")){
					index++;								//accept and...
					thisToken = listOfTokens.get(index);	//get next token
					statement(listOfTokens, thisToken);
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
		index++;
		thisToken = listOfTokens.get(index);	//get next token
		if(thisToken.tName.equals("(")){
			index++;								//accept and...
			thisToken = listOfTokens.get(index);	//get next token
			expression(listOfTokens, thisToken);
			index++;
			thisToken = listOfTokens.get(index);	//get next token
			
			if(thisToken.tName.equals(")")){
				index++;								//accept and...
				thisToken = listOfTokens.get(index);	//get next token
				statement(listOfTokens, thisToken);
				thisToken = listOfTokens.get(index);
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
