package For Osprey;

//import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.Queue;
//import XMLParser.src.*;
public class SQLParser {
	static Database currentDB;
	static Queue<String> q = new LinkedList<String>();
	public static void main(String args[]){
		
//		//command patterns
//		Pattern createDatabase = Pattern.compile("(?i:create database) (\\w*);");
//		Pattern dropDatabase = Pattern.compile("(?i:drop database) (\\w*);");
//		Pattern saveDatabase = Pattern.compile("(?i:save database) (\\w*);");
//		Pattern loadDatabase = Pattern.compile("(?i:load database) (\\w*);");
//		Pattern createTable = Pattern.compile("(?i:create table) (\\w*) \\((\\w*)(, (\\w*))*\\);");
//		Pattern dropTable = Pattern.compile("(?i:drop table) (\\w*);");
//		Pattern insert = Pattern.compile("(?i:insert into) (\\w*)( \\((\\w*)(, (\\w*))*\\))? (?i:values) \\((\\w*)(, (\\w*))*\\);");
//		Pattern convertXML = Pattern.compile("(?i:convert xml) (\\w*)(, (\\w*))? (?i:as) (\\w*);");
//		Pattern inputXML = Pattern.compile("(?i:input) (\\w*);");
//		Pattern deleteRecords = Pattern.compile("(?i:delete from) (\\w*)( (?i:where) (\\w*))?;");
//		Pattern select = Pattern.compile("(?i:select \\* from) (\\w*);");
//		Pattern tSelect = Pattern.compile("(?i:tselect \\* from) (\\w*);");
		
		
		System.out.println("Input an SQL command");
		Scanner input = new Scanner(System.in);
		
		String userInput = input.nextLine();
	
		while (!userInput.equalsIgnoreCase("exit")){
			sqlCheck(userInput);
			userInput = input.nextLine();
		}
		input.close();
		System.exit(0);
	}
	
	
	public static void fileSQL(String filename){
		
		BufferedReader bufInput = null;
		String sqlCommand = "";
		
		try {
			bufInput = new BufferedReader(new FileReader(filename));
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sqlCommand = bufInput.readLine();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(sqlCommand != null){													
			
			if(!sqlCommand.isEmpty()){												
				sqlCheck(sqlCommand);
			}
			
			try {
				sqlCommand = bufInput.readLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void sqlCheck(String userInput){
		
		String fieldDef = "(\\w+) (integer(\\((\\d*)\\))(?i: not null)?|number(\\((\\d*)(, \\d*)?\\))(?i: not null)?|character(\\((\\d*)\\))(?i: not null)?|date\\(\\d{2}/\\d{2}/(\\d{2})?\\d{2}\\)(?i: not null)?)";
		
//		String outputFileName = "";
//		String xsdFileName = "";
		
		String[] strings = userInput.split("(\\s|\\W^.|;)");
		
		if(userInput.equals("") || userInput.matches(".{1}")){
			System.out.println("Invalid command.");
		}
		
		//CREATE commands
		else if (userInput.matches("(?i:create database) (\\w+);")){
			q.add(userInput);
		}
		else if (userInput.matches("(?i:create table) (\\w+) \\((\\s*)(" + fieldDef + ")(, " + fieldDef + ")*(\\s*)\\);")){
			q.add(userInput);
//			String fieldDefString = userInput.substring((userInput.indexOf("(")) + 1, (userInput.length()) - 2) ;
//			String[] fields = fieldDefString.split(",");
//			
//			System.out.println("Table " + strings[2] + " created");
//			
//			System.out.println("Fields: ");
//			for(int i = 0; i < fields.length; i++){
//				if(fields[i].contains("not null") || fields[i].contains("NOT NULL")){
//					fields[i] = fields[i].substring(0, (fields[i].indexOf("not null") - 1)) ;
//	
//					System.out.print(fields[i]);
//				}
//			}
		}
		else if("cr".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid commands are 'CREATE DATABASE <database_name>;' and 'CREATE TABLE <table_name> (field-def[, field-def, ...]);'.");
		}
		
		
		//DROP commands
		else if(userInput.matches("(?i:drop database) (\\w+);")){
			q.add(userInput);
		}
		else if(userInput.matches("(?i:drop table) (\\w+);")){
			q.add(userInput);
		}
		else if("dr".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid commands are 'DROP DATABASE <database_name>;' and 'DROP TABLE <table_name>;'.");
		}
		
		
		//SAVE DATABASE command
		else if(userInput.matches("(?i:save database) (\\w+);")){
			if(currentDB!= null){
				currentDB.saveDatabase();
				System.out.println("Database " + strings[2] + " saved");
				}
				else
				System.out.println("Error no database loaded");
			
		}
		else if("sa".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'SAVE DATABASE <database_name>;'.");
		}
		
		
		//LOAD DATABASE command
		else if(userInput.matches("(?i:load database) (\\w+);")){
			loadDatabase(strings[2]);
			System.out.println("Database " + strings[2] + " loaded");
		}
		else if("lo".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'LOAD DATABASE <database_name>;'.");
		}	
		
		
		//INSERT command
		else if(userInput.matches("(?i:insert into) (\\w+)( \\((\\s*)(\\w+)(, (\\w+))*(\\s*)\\))? (?i:values) \\((\\s*)((\\w*)|('(.|\\s)*')|(\"(.|\\s)*\")|(\\d+.\\d+)|(\\d{2}/\\d{2}/(\\d{2})?\\d{2}))(, ((\\w*)|('(\\w|\\s)*')|(\"(\\w|\\s)*\")|(\\d+.\\d+)|(\\d{2}/\\d{2}/(\\d{2})?\\d{2})))*(\\s*)\\);")){
			q.add(userInput);
		}
		else if("in".equalsIgnoreCase(userInput.substring(0, 2)) && !userInput.contains("inp")){
			System.out.println("Invalid command. Valid command is 'INSERT INTO <table_name> [(field[, field, ...])] VALUES (value[, value, ...]);'.");
		}
		
		
		//CONVERT XML command
		else if(userInput.matches("(?i:convert xml) (\\w+).xml(, (\\w+).xsd)? (?i:as) (\\w+).txt;")){
			q.add(userInput);
		}
		else if("co".equalsIgnoreCase(userInput.substring(0, 2)) && !userInput.matches("(?i:commit;)")){
			System.out.println("Invalid command. Valid command is 'CONVERT XML <XML filename>[, <XSD filename>] AS <new_filename>'.");
		}
		
		
		//INPUT XML command
		else if(userInput.matches("(?i:input) (\\w+).txt;")){
			q.add(userInput);
		}
		else if("in".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'INPUT <new_filename>;'.");
		}
		
		
		//DELETE (records) command
		else if(userInput.matches("(?i:delete from) (\\w+)( (?i:where) (\\w+) (<|>|=) ((\\w+)|('\\w+')))?;")){
			q.add(userInput);
		}
		else if("de".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'DELETE FROM <table_name>[ WHERE condition];'.");
		}
		
		
		//SELECT command
		else if(userInput.matches("(?i:select \\* from) (\\w+);")){
			q.add(userInput);
		}
		else if(userInput.matches("(t)?(?i:select )(\\*|\\((\\w+)(, (\\w+))*\\)) from (\\w+)( (?i:where) (\\w+) (<|>|=) ((\\w+)|('\\w+')))?;")){
			q.add(userInput);
		}
		else if("se".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'SELECT * FROM <table_name>;'.");
		}

		//tSELECT command
		else if(userInput.matches("(?i:tselect \\* from) (\\w+);")){
			q.add(userInput);
		}
		else if("ts".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'tSELECT * FROM <table_name>;'.");
		}
		
		//COMMIT command
		else if(userInput.matches("(?i:commit);")){
			commit();
		}
		
		else{
			System.out.println("Invalid command.");
		}
	}
	
	//PHIL'S LOAD DATABASE
		public static void loadDatabase(String name){
			File databaseFile = new File(System.getProperty("user.dir") + "/" + name );		//set file path for new database
			
			try{
				BufferedReader reader = new BufferedReader(new FileReader(databaseFile));
				String currentLine;
				while((currentLine = reader.readLine()) != null){
					String trimmedLine = currentLine.trim();
					sqlCheck(trimmedLine);
				}
				reader.close();
			}
			catch(Exception ex){System.out.println(ex);}
		}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void sqlExecute(String sqlCommand){
		String[] strings = sqlCommand.split("(\\s|\\W^.|;)");
		Table currentTable;
		String outputFileName = "";
		String xsdFileName = "";
		
		//CREATE commands
		if (strings[0].matches("(?i:create)") && strings[1].matches("(?i:database)")){
			System.out.println("Database " + strings[2] + " created");
			currentDB = new Database(strings[2]);
		}	
		else if (strings[0].matches("(?i:create)") && strings[1].matches("(?i:table)")){
			String fieldDefString = sqlCommand.substring((sqlCommand.indexOf("(")) + 1, (sqlCommand.length()) - 2) ;
			String[] fields = fieldDefString.split("(?<=\\)), |(?<=(?i:not null)), |(?<=(?i:not null\\)))");
			
			System.out.println("Table " + strings[2] + " created");
		
			if(currentDB != null){
				currentTable = currentDB.createtable(strings[2]);
				if(currentTable != null){
					int numdecimal = 5;
					int length= 255;
					for(int i = 0; i < fields.length; i++){
						String[] field = fields[i].split(" ");
						String fieldName = field[0].trim();
						String fieldType = field[1].substring(0, field[1].indexOf("("));
						boolean fieldNull = false;
						if(!fieldType.equalsIgnoreCase("date") && !fieldType.equalsIgnoreCase("number")){
							length = Integer.parseInt(field[1].substring(field[1].indexOf("(") + 1, field[1].indexOf(")")));
							//System.out.print("Field name: " + fieldName + "   Field type: " + fieldType + "    length: " + length);
						}
						else if(fieldType.equalsIgnoreCase("date")) {
							length = field[1].substring(field[1].indexOf("(") + 1, field[1].indexOf(")")).length();
						//	System.out.println("Date length is: " + length);
						}
						else if(fieldType.equalsIgnoreCase("number")){
							String twoNumbers = fields[i].substring(fields[1].indexOf("(") + 1, fields[1].indexOf(")"));
							int firstNum = Integer.parseInt(twoNumbers.substring(twoNumbers.indexOf(",") - 1, twoNumbers.indexOf(",")));
							int secondNum = Integer.parseInt(twoNumbers.substring(twoNumbers.length() - 1));
							length = firstNum+secondNum;
							numdecimal = secondNum;
						//	System.out.println("second number is: " + secondNum);	
						}
						if(field.length>3){
							fieldNull = true;
						//	System.out.println(" Required?:  true");
						}
						else{
						//	System.out.println(" Required?:  false");
						}
						Field realfield = new Field(fieldName,fieldType,length,fieldNull);
						realfield.setNumdecimal(numdecimal);
						currentTable.addField(realfield);
					}
				}
			}
				else{
					System.out.println("Error Current Database is null");
				}
			}
		
		//DROP commands
		else if(strings[0].matches("(?i:drop)") && strings[1].matches("(?i:database)")){
			currentDB.dropDatabase();
			if(currentDB.name.equals(strings[2])){
				currentDB = null;
			}
			System.out.println("Database " + strings[2] + " deleted");
		}
		else if(strings[0].matches("(?i:drop)") && strings[1].matches("(?i:table)")){
			if(currentDB != null){
				Table tableIndex = currentDB.getTable(strings[2]);
				if(tableIndex != null){
				currentDB.tables.remove(tableIndex);
				System.out.println("Table " + strings[2] + " deleted");
			}
				else{
					System.out.println("Error table does not exists");
				}
		}
			else {
				System.out.println("Error current database is null");
			}
	}
	
				
		
		//SAVE DATABASE command
		else if(strings[0].matches("(?i:save)") && strings[1].matches("(?i:database)")){
			if(currentDB!= null){
			currentDB.saveDatabase();
			System.out.println("Database " + strings[2] + " savedd");
			}
			else
			System.out.println("Error no database loaded");
		}
		
		//LOAD DATABASE command
		else if(strings[0].matches("(?i:load)") && strings[1].matches("(?i:database)")){
			
			System.out.println("Database " + strings[2] + " loaded");
		}
		
		//INSERT command
		else if(strings[0].matches("(?i:insert)") && strings[1].matches("(?i:into)")){
			boolean error = false;
			// Get the table
			if(currentDB != null){
			currentTable = currentDB.getTable(strings[2]);
			if(currentTable == null){
				System.out.println("Error table does not exists");
			}
			
			else{
				currentTable.createRecord();
				//if fields are included
	            if(strings[3].contains("(")){
	                
	                //get field names
	                String fieldsList = sqlCommand.substring(sqlCommand.indexOf("(") + 1, sqlCommand.indexOf(")"));
	                String[] fieldNames = fieldsList.split(", ");
	                fieldNames[0] = fieldNames[0].trim();
	                
	                //get values
	                String valuesList = sqlCommand.substring(sqlCommand.lastIndexOf("(") + 1, sqlCommand.lastIndexOf(")"));
	                String[] values = valuesList.split(", ");
	                values[0] = values[0].trim();
	                
	                for(int i = 0; i < fieldNames.length; i++){
	                	boolean temp = currentTable.addValue(fieldNames[i], values[i]);
	                	if(!temp){
	                		error = true;
	                	}
	                }
	            }
	            
	            //if fields are not included
	            else if(strings[3].matches("(?i:values)")){
	                
	                //get values
	                String valuesList = sqlCommand.substring(sqlCommand.lastIndexOf("(") + 1, sqlCommand.lastIndexOf(")"));
	                String[] values = valuesList.split(", ");
	                values[0] = values[0].trim();
	                
	                 for(int i = 0; i < values.length; i++){
	                	boolean temp = currentTable.addValue(i, values[i]);
	                	if(!temp){
	                		error = true;
	                	}
	                }
	            }
			
			if (error == false){
				if(currentTable.addRecord() == null)
					System.out.println("Record inserted into  " + strings[2]);
					}	
				}				
			}
			// Add values to the record

			
			else{
				System.out.println("Error current database is null");
			}
		}
		//CONVERT XML command
		else if(strings[0].matches("(?i:convert)") && strings[1].matches("(?i:xml)")){
			String xmlFileName = strings[2].substring(0, (strings[2].length() - 1));
			if(strings[3].matches("(\\w+).xsd")){
				xsdFileName = strings[3];
			}
			outputFileName = strings[strings.length - 1];
			System.out.println("XML: " + xmlFileName + "  XSD: " + xsdFileName);
			XMLParser xmlP = new XMLParser();
			xmlP.startParsing(xsdFileName, xmlFileName, outputFileName);
		}
		
		//INPUT XML command
		else if(strings[0].matches("(?i:input)")){
			//if(!outputFileName.equals("")){
				outputFileName = strings[1];
				fileSQL(outputFileName);
//					}
//					else{
//						System.out.println("File " + strings[1] + " not found.");
//					}
		}
		
		//DELETE (records) command
		else if(strings[0].matches("(?i:delete)") && strings[1].matches("(?i:from)")){
			if(currentDB == null){
				System.out.println("No Database loaded");
			}
            else{
            String leftOp = strings[4];
			String operator = strings[5];
			String rightOp = strings[6];
			currentDB.deleteRecord(strings[2], leftOp, rightOp, operator);
		}
        }
				
		//SELECT * command
		else if(sqlCommand.matches("(t)?(?i:select \\* from) (\\w+);")){
			if(currentDB != null){
			System.out.println("SELECT * selected");
			boolean t = false;
			if(strings[0].charAt(0) == 't'){
				t=true;
			}
			currentDB.Select(strings[3],t);
		}
			else{
				System.out.println("Error, No Database selected");
			}
		
		}
		
		//SELECT with fields
		else if(sqlCommand.matches("(t)?(?i:select )(\\((\\w+)(, (\\w+))*\\)) from (\\w+)( (?i:where) (\\w+) (<|>|=) ((\\w+)|('\\w+')))?;")){
			
			if(currentDB != null){
			//With condition
			String lcCommand = sqlCommand.toLowerCase();
			if(lcCommand.contains("where")){
				
				//get field names
				ArrayList<String> fields = new ArrayList<String>();
				String fieldsList = sqlCommand.substring(sqlCommand.indexOf("(") + 1, sqlCommand.indexOf(")"));
				String[] fieldNames = fieldsList.split(", ");
				fieldNames[0] = fieldNames[0].trim();
				for(int i = 0; i < fieldNames.length; i++){
					fields.add(fieldNames[i]);
				}
				
				//get condition operands and operator
				int whereIndex = lcCommand.indexOf("where");
				String condition = sqlCommand.substring(whereIndex + 6);
				
				String[] conditionParts = condition.split("((\\s)|(;))");
				String leftOp = conditionParts[0];
				String operator = conditionParts[1];
				String rightOp = conditionParts[2];
				boolean t = false;
				if(strings[0].charAt(0) == 't'){
					t=true;
				}
				currentDB.Select(strings[3], leftOp, rightOp, operator,t,fields);
			}
			
			//without condition (just get field names)
			else{
				ArrayList<String> fields = new ArrayList<String>();
				String fieldsList = sqlCommand.substring(sqlCommand.indexOf("(") + 1, sqlCommand.indexOf(")"));
				String[] fieldNames = fieldsList.split(", ");
				fieldNames[0] = fieldNames[0].trim();
				for(int i = 0; i < fieldNames.length; i++){
					fields.add(fieldNames[i]);
				}
				
				boolean t = false;
				if(strings[0].charAt(0) == 't'){
					t=true;
				}
				currentDB.Select(strings[3],fields,t);
			}
		}
			else{
				System.out.println("Error, No Database selected");
			}
		}
		
		
		//SELECT without fields and with condition
		else if(sqlCommand.matches("(t)?(?i:select )(\\*) from (\\w+)( (?i:where) (\\w+) (<|>|=) ((\\w+)|('\\w+')))?;")){
			if(currentDB != null){
			//get condition operands and operator
			String lcCommand = sqlCommand.toLowerCase();
			int whereIndex = lcCommand.indexOf("where");
			String condition = sqlCommand.substring(whereIndex + 6);
			
			String[] conditionParts = condition.split("((\\s)|(;))");
			String leftOp = conditionParts[0];
			String operator = conditionParts[1];
			String rightOp = conditionParts[2];
			boolean t = false;
			if(strings[0].charAt(0) == 't'){
				t=true;
			}
			currentDB.Select(strings[3], leftOp, rightOp, operator,t);
		}
			else{
				System.out.println("Error, No Database selected");
			}
		}
		//tSELECT command
		else if(sqlCommand.matches("(?i:tselect \\* from) (\\w+);")){
			System.out.println("tSELECT selected");
		}
		else if(sqlCommand.matches("(t)?(?i:select )(\\*|\\((\\w+)(, (\\w+))*\\)) from (\\w+)( (?i:where) (\\w+) (<|>|=) ((\\w+)|('\\w+')))?;")){
			System.out.println("SELECT selected");
		}				
	}
	
	public static void commit(){
		String currentCmd;
		while(!q.isEmpty()){
			currentCmd = q.remove();
			sqlExecute(currentCmd);
		}
	}
}