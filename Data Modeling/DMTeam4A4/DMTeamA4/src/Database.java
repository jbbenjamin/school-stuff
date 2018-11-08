import java.util.ArrayList;

public class Database {
	String name;
	ArrayList<Table> tables;

	public Database(String name) {
		super();
		this.name = name;
		 tables = new ArrayList<Table>();
	}
	
	public Table createtable(String tableName){
		Table newTable = new Table(tableName);
		this.tables.add(newTable);
		return newTable;
	}
	
	public Table getTable(String name){
		for(int i = 0; i <this.tables.size();i++){
			if(this.tables.get(i).getName().equals(name)){
				return this.tables.get(i);
			}
		}
	return null;
	}
	public void printDatabase(){
		System.out.println("CREATE DATABASE "+ this.name + ";");
		for(int i = 0; i <this.tables.size();i++){
			tables.get(i).printTable();
		}
	}
	
	

}
