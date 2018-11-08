import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainClass {
	 static Database currentDB;
	public static void main(String[] args) throws IOException {
		currentDB = loadDatabase(args[0]);
		currentDB.printDatabase();
	}
	
	public static Database loadDatabase(String dbName) throws IOException{
		currentDB = new Database(dbName);	
		boolean run = true;
			BufferedReader reader = new BufferedReader(new FileReader(dbName));
			String line;
			while((line = reader.readLine())!=null){
				databaseCommandHandler(line);
			}
 
		return currentDB;
		}
	public static void databaseCommandHandler(String command){
		Table currentTable;
		StringTokenizer st = new StringTokenizer(command);
		String currentTok;
		String regex;
		while (st.hasMoreTokens()) {
			currentTok = st.nextToken();
			if(currentTok.equals("CREATE")){
				currentTok = st.nextToken();
				currentTable = currentDB.createtable(st.nextToken());
				currentTok = st.nextToken(", ").substring(1);
				String fieldtype = st.nextToken();
				Field testField = new Field(currentTok,".*",true);
				currentTable.addField(testField);
				while(st.hasMoreTokens()){
					currentTok = st.nextToken(",| |);");							
					fieldtype = st.nextToken();
					testField = new Field(currentTok,".*",true);
					currentTable.addField(testField);
					}
				}
			else {
				currentTok = st.nextToken();
				currentTable =currentDB.getTable(st.nextToken());
				ArrayList<String> values = new ArrayList<String>();
				st.nextToken();
				currentTok = st.nextToken(", ").substring(1);
				values.add(currentTok);
				while(st.hasMoreTokens()){
					currentTok = st.nextToken(",|);");
					values.add(currentTok);
				}
				Record newRecord = new Record(values);
				currentTable.addRecord(newRecord);
					
			}
		}
		
	}

}
