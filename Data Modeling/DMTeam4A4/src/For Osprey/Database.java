import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database {
	String name;
	ArrayList<Table> tables;
	String timeCreated;

	public Database(String name) {
		super();
		this.name = name;
		 tables = new ArrayList<Table>();
		 this.timeCreated = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss").format(new Date());
	}
	
	public Table createtable(String tableName){
		Table newTable = new Table(tableName);
		for(int i=0;i<this.tables.size();i++){
			if (tables.get(i).getName().equals(tableName)){
				System.out.println("Error table already exists\n");
				return null;
			}
		}
		this.tables.add(newTable);
		return newTable;
	}
	
	public void Select(String tableName,String fieldName, String value, String op,boolean tSelect){
		Table currentTable = getTable(tableName);
		if(currentTable == null){
			System.out.println("Error table does not exist\n");
			return;
		}
		
		ArrayList<Record> results;
		results = currentTable.getRecords(fieldName,op, value);
		if(results == null){
			return;
		}
		currentTable.printFields();
		if(results.size() == 0){
			System.out.println("No records found\n");
			return;
		}
		for(int i =0;i<results.size();i++){
			results.get(i).printRecord(tSelect);
		}
		
	}
	public void Select(String tableName,boolean tSelect){
		Table currentTable = getTable(tableName);
		if(currentTable == null){
			System.out.println("Error table does not exist\n");
			return;
		}
		
		ArrayList<Record> results;
		results = currentTable.getRecords();
		if(results == null){
			return;
		}
		currentTable.printFields();
		if(results.size() == 0){
			System.out.println("No records found\n");
			return;
		}
		for(int i =0;i<results.size();i++){
			results.get(i).printRecord(tSelect);
		}
		
	}
	
	public void Select(String tableName,String fieldName, String value, String op,boolean tSelect,ArrayList<String> fields ){
		ArrayList<Integer> fieldnums;
		Table currentTable = getTable(tableName);
		if(currentTable == null){
			System.out.println("Error table does not exist\n");
			return;
		}
		
		ArrayList<Record> results;
		results = currentTable.getRecords(fieldName,op, value);
		if(results == null){
			return;
		}
		fieldnums = currentTable.printFields(fields);
		if(results.size() == 0){
			System.out.println("No records found\n");
			return;
		}
		if(fieldnums.size() ==0){
			System.out.println("No fields found\n");
			return;
		}
		for(int i =0;i<results.size();i++){
			results.get(i).printRecord(tSelect,fieldnums);
		}
	}
	
	public void Select(String tableName,ArrayList<String> fields ,boolean tSelect){
		ArrayList<Integer> fieldnums;
		Table currentTable = getTable(tableName);
		if(currentTable == null){
			System.out.println("Error table does not exist\n");
			return;
		}
		
		ArrayList<Record> results;
		results = currentTable.getRecords();
		if(results == null){
			return;
		}

		fieldnums = currentTable.printFields(fields);
		if(results.size() == 0){
			System.out.println("No records found\n");
			return;
		}
		if(fieldnums.size() ==0){
			System.out.println("No fields found \n");
			return;
		}
		
		for(int i =0;i<results.size();i++){
			results.get(i).printRecord(tSelect,fieldnums);
		}
		
		
	}
	
	public void deleteRecord(String tableName,String fieldName, String value, String op){
		Table currentTable = getTable(tableName);
		if(currentTable == null){
			System.out.println("Error table does not exist\n");
			return;
		}
		
		ArrayList<Record> results;
		results = currentTable.getRecords(fieldName,op, value);
		if(results == null){
			return;
		}
	
		if(results.size() == 0){
			System.out.println("No records found\n");
			return;
		}
		for(int i =0;i < results.size();i++){
			System.out.println("removing record\n");
			currentTable.getRecords().remove(results.get(i));
			}
		
	}
public void saveDatabase(){
		File databaseFile = new File(System.getProperty("user.dir") + "/" + this.name);		//set file path for new database
		File tempDatabaseFile = new File(System.getProperty("user.dir") + "databaseTempFile.txt");		//set file path for new temp database
		
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempDatabaseFile));
			
			writer.write("CREATE DATABASE "+ this.name + ";\n");
			for(int i = 0; i <this.tables.size();i++){
				writer.write(tables.get(i).getStringTable());
			}
			writer.write("COMMIT;");
			writer.close();
			
			tempDatabaseFile.renameTo(databaseFile);
		}
		catch(Exception ex){System.out.println(ex);}
	}

public void dropDatabase(){
	File databaseFile = new File(System.getProperty("user.dir") + "/" + this.name + ".txt");		//set file path for new database
	
	try{
		databaseFile.delete();
	}
	catch(Exception ex){System.out.println(ex);}
}


	public Table getTable(String name){
		for(int i = 0; i <this.tables.size();i++){
			if(this.tables.get(i).getName().equals(name)){
				return this.tables.get(i);
			}
		}
	return null;
	}
	

}
