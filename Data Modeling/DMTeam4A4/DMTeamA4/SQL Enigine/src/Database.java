//import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
//import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.Queue;
public class Database {
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
		
		while(sqlCommand != null){													//while not at end of file
			
			if(!sqlCommand.isEmpty()){												//if the line isn't blank...
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
			//System.out.println("Database " + strings[2] + " created");
		}	
		else if (userInput.matches("(?i:create table) (\\w+) \\((" + fieldDef + ")(, " + fieldDef + ")*\\);")){
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
			//System.out.println("Database " + strings[2] + " deleted");
		}
		else if(userInput.matches("(?i:drop table) (\\w+);")){
			q.add(userInput);
			//System.out.println("Table " + strings[2] + " deleted");
		}
		else if("dr".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid commands are 'DROP DATABASE <database_name>;' and 'DROP TABLE <table_name>;'.");
		}
		
		
		//SAVE DATABASE command
		else if(userInput.matches("(?i:save database) (\\w+);")){
			System.out.println("Database " + strings[2] + " saved");
		}
		else if("sa".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'SAVE DATABASE <database_name>;'.");
		}
		
		
		//LOAD DATABASE command
		else if(userInput.matches("(?i:load database) (\\w+);")){
			System.out.println("Database " + strings[2] + " loaded");
		}
		else if("lo".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'LOAD DATABASE <database_name>;'.");
		}	
		
		
		//INSERT command
		else if(userInput.matches("(?i:insert into) (\\w+)( \\((\\w+)(, (\\w+))*\\))? (?i:values) \\(((\\w*)|('\\w*')|(\\d+.\\d+)|(\\d{2}/\\d{2}/(\\d{2})?\\d{2}))(, ((\\w*)|('\\w*')|(\\d+.\\d+)|(\\d{2}/\\d{2}/(\\d{2})?\\d{2})))*\\);")){
			q.add(userInput);
			//System.out.println("Record inserted into  " + strings[2]);
		}
		else if("in".equalsIgnoreCase(userInput.substring(0, 2)) && !userInput.contains("inp")){
			System.out.println("Invalid command. Valid command is 'INSERT INTO <table_name> [(field[, field, ...])] VALUES (value[, value, ...]);'.");
		}
		
		
		//CONVERT XML command
		else if(userInput.matches("(?i:convert xml) (\\w+).xml(, (\\w+).xsd)? (?i:as) (\\w+).txt;")){
			q.add(userInput);
//			String xmlFileName = strings[2];
//			if(strings[3].matches("(\\w+).xsd")){
//				xsdFileName = strings[3];
//			}
//			outputFileName = strings[strings.length - 1];
//			System.out.println("XML: " + xmlFileName + "  XSD: " + xsdFileName);
//			System.out.println("XML document converted to " + outputFileName);
		}
		else if("co".equalsIgnoreCase(userInput.substring(0, 2)) && !userInput.matches("(?i:commit;)")){
			System.out.println("Invalid command. Valid command is 'CONVERT XML <XML filename>[, <XSD filename>] AS <new_filename>'.");
		}
		
		
		//INPUT XML command
		else if(userInput.matches("(?i:input) (\\w+).txt;")){
			q.add(userInput);
//			//if(!outputFileName.equals("")){
//				outputFileName = strings[1];
//				fileSQL(outputFileName);
//				System.out.println("Data successfully input");
////			}
////			else{
////				System.out.println("File " + strings[1] + " not found.");
////			}
		}
		else if("in".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'INPUT <new_filename>;'.");
		}
		
		
		//DELETE (records) command
		else if(userInput.matches("(?i:delete from) (\\w+)( (?i:where) (\\w+) (<|>|=) ((\\w+)|('\\w+')))?;")){
			q.add(userInput);
			//System.out.println("Record deleted from " + strings[2]);
		}
		else if("de".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'DELETE FROM <table_name>[ WHERE condition];'.");
		}
		
		
		//SELECT command
		else if(userInput.matches("(?i:select \\* from) (\\w+);")){
			q.add(userInput);
			//System.out.println("SELECT selected");
		}
		else if(userInput.matches("(t)?(?i:select )(\\*|\\((\\w+)(, (\\w+))*\\)) from (\\w+)( (?i:where) (\\w+) (<|>|=) ((\\w+)|('\\w+')))?;")){
			q.add(userInput);
			//System.out.println("SELECT selected");
		}
		else if("se".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'SELECT * FROM <table_name>;'.");
		}
		
		
		//tSELECT command
		else if(userInput.matches("(?i:tselect \\* from) (\\w+);")){
			q.add(userInput);
			//System.out.println("tSELECT selected");
		}
		else if("ts".equalsIgnoreCase(userInput.substring(0, 2))){
			System.out.println("Invalid command. Valid command is 'tSELECT * FROM <table_name>;'.");
		}
		
		//COMMIT command
		else if(userInput.matches("(?i:commit);")){
			String currentCommand;
			while(!q.isEmpty()){
				currentCommand = q.remove();
				sqlExecute(currentCommand);
			}
		}
		
		else{
			System.out.println("Invalid command.");
		}
	}
	
	public static void sqlExecute(String sqlCommand){
		String[] strings = sqlCommand.split("(\\s|\\W^.|;)");
		
		String outputFileName = "";
		String xsdFileName = "";
		
		//CREATE commands
		if (strings[0].matches("(?i:create)") && strings[1].matches("(?i:database)")){
			System.out.println("Database " + strings[2] + " created");
		}	
		else if (strings[0].matches("(?i:create)") && strings[1].matches("(?i:table)")){
			String fieldDefString = sqlCommand.substring((sqlCommand.indexOf("(")) + 1, (sqlCommand.length()) - 2) ;
			String[] fields = fieldDefString.split(",");
			
			System.out.println("Table " + strings[2] + " created");
			
			System.out.println("Fields with 'NOT NULL': ");
			for(int i = 0; i < fields.length; i++){
				if(fields[i].contains("not null") || fields[i].contains("NOT NULL")){
					fields[i] = fields[i].substring(0, (fields[i].indexOf("not null") - 1)) ;
					System.out.print(fields[i].trim());
				}
			}
		}
		
		
		//DROP commands
		else if(strings[0].matches("(?i:drop)") && strings[1].matches("(?i:database)")){
			System.out.println("Database " + strings[2] + " deleted");
		}
		else if(strings[0].matches("(?i:drop)") && strings[1].matches("(?i:table)")){
			System.out.println("Table " + strings[2] + " deleted");
		}		
		
		//SAVE DATABASE command
		else if(strings[0].matches("(?i:save)") && strings[1].matches("(?i:database)")){
			System.out.println("Database " + strings[2] + " saved");
		}
		
		//LOAD DATABASE command
		else if(strings[0].matches("(?i:load)") && strings[1].matches("(?i:database)")){
			System.out.println("Database " + strings[2] + " loaded");
		}
		
		//INSERT command
		else if(strings[0].matches("(?i:insert)") && strings[1].matches("(?i:into)")){
			System.out.println("Record inserted into  " + strings[2]);
		}				
		
		//CONVERT XML command
		else if(strings[0].matches("(?i:convert)") && strings[1].matches("(?i:xml)")){
			String xmlFileName = strings[2];
			if(strings[3].matches("(\\w+).xsd")){
				xsdFileName = strings[3];
			}
			outputFileName = strings[strings.length - 1];
			System.out.println("XML: " + xmlFileName + "  XSD: " + xsdFileName);
			System.out.println("XML document converted to " + outputFileName);
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
			System.out.println("Record deleted from " + strings[2]);
		}
				
		//SELECT command
		else if(strings[0].matches("(?i:select)") && strings[1].matches("(\\*)")){
			System.out.println("SELECT selected");
		}
		
		//tSELECT command
		else if(strings[0].matches("(?i:tselect)") && strings[1].matches("(\\*)")){
			System.out.println("tSELECT selected");
		}
		else if(sqlCommand.matches("(t)?(?i:select )(\\*|\\((\\w+)(, (\\w+))*\\)) from (\\w+)( (?i:where) (\\w+) (<|>|=) ((\\w+)|('\\w+')))?;")){
			System.out.println("SELECT selected");
		}
				
				
				
	}
}
