import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table {
	private String name;
	private ArrayList<Field> fields;
	private ArrayList<Record> records;
	private Record  currentRecord;
	private String timeCreated;
	public Table(String name) {
		super();
		this.name = name;
		this.fields = new ArrayList<Field>();
		this.records = new ArrayList<Record>();
		this.timeCreated = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss").format(new Date());
		
	}
	

	public void addField(Field currentField){
		this.fields.add(currentField);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Field> getFields() {
		return fields;
	}

	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}

	public ArrayList<Record> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<Record> records) {
		this.records = records;
	}
	
	
	
	
	public int findField(String name){
		name = name.trim();
		for(int i =0; i< this.getFields().size();i++){
			if (this.getFields().get(i).getName().equals(name)){
				return i;
			}
		}
	return -1;
	}
	
	
	public Field findField(int index){
		if(index == -1){
			return null;
		}
		if(index < this.fields.size())	
		
			return this.fields.get(index);
		else
			return null;
	}
	
///// Add value when given Position and Value
	public boolean addValue(int Field,String value){
		value = value.trim();
		Field currentField = findField(Field);
	if(currentField== null){
		System.out.println("Error field does not exist");
		return false;
	}
	
	 if(currentField.getType().equals("character")){
		if(value.charAt(value.length()-1) !='"' && value.charAt(0)!= '"'){
			System.out.println("Error, field is of type character insert quotaion marks");
			return false;
		}
		else{
			value = value.substring(1, value.length()-1);
		}
	}
	
	 if(currentField.getLength()< value.length()){
		 System.out.println("Error "+ currentField.getName() +"length is " +currentField.getLength());
		 return false;
	 }
	 if(currentField.getType().equals("date")){
		 if(!value.equals("")){
		 String regex = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
		 Pattern pattern = Pattern.compile(regex);	
		 Matcher matcher = pattern.matcher(value);	
		 if(!matcher.matches()){
				System.out.println("Error " +currentField.getName() + " type is date" );
				return false;
			}
		 }
			}
	if(currentField.getType().equals("integer")&& !isInteger(value)){
		
		if(!value.equals("")){
		System.out.println("Error Field "+ currentField.getName() + " field is int");
		return false;
		}
	}
	else if(currentField.getType().equals("number")&& !isFloat(value)){
		if(!value.equals("")){
		if(!isFloat(value)){
			System.out.println("Error field "+ currentField.getName() + " is number");
			return false;
		}
		String regex = "\\d+(\\.\\d{"+ currentField.getNumdecimal()
		+ "})?|\\.\\d{"
		+ currentField.getNumdecimal()+"}";
		Pattern pattern = Pattern.compile(regex);	
		Matcher matcher = pattern.matcher(value);	
		 if(!matcher.matches()){
			 System.out.println("Error number has precision of " +currentField.getNumdecimal());
				return false;
			}
		}
	}
	
	currentRecord.setValue(value,Field);
	return true;
	}
	
	
	///// Add value when given Field name and Value
	public boolean addValue(String Field,String value){
		value = value.trim();
		int temp = findField(Field);
		if(temp == -1){
			System.out.println("Error field " + Field + " does not exists");
			return false;
		}
		Field currentField = findField(temp);
		if(currentField== null){
			System.out.println("Error too many fields");
			return false;
		}
		
		if(currentField.getType().equals("character")){
			if(value.charAt(value.length()-1) !='"' && value.charAt(0)!= '"'){
				System.out.println("Error, field is of type character insert quotaion marks");
				return false;
			}
			else{
				value = value.substring(1, value.length()-1);
			}
		}

		 if(currentField.getLength()< value.length()){
			 System.out.println("Error "+ currentField.getName() +" length is " +currentField.getLength());
			 return false;
		 }
		 
		 if(currentField.getType().equals("date")){
			 if(!value.equals("")){
			 String regex = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
			 Pattern pattern = Pattern.compile(regex);	
			 Matcher matcher = pattern.matcher(value);	
			 if(!matcher.matches()){
					System.out.println("Error " +currentField.getName() + " type is date" );
					return false;
				}
			 }
				}
		if(currentField.getType().equals("integer")&& !isInteger(value.trim())){
			System.out.println(value);
			if(!value.equals("")){
			System.out.println("Error"+ currentField.getName() + "field is int");
			return false;
			}
		}
		else if(currentField.getType().equals("number")&& !isFloat(value.trim())){
			if(!value.equals("")){
			if(!isFloat(value)){
				System.out.println("Error"+ currentField.getName() + "field is number");
				return false;
			}
			String regex = "\\d+(\\.\\d{"+ currentField.getNumdecimal()
			+ "})?|\\.\\d{"
			+ currentField.getNumdecimal()+"}";
			Pattern pattern = Pattern.compile(regex);	
			Matcher matcher = pattern.matcher(value);	
			 if(!matcher.matches()){
				 System.out.println("Error " +currentField.getNumdecimal() + " precision" );
					return false;
				}

		}
		}
		
		currentRecord.setValue(value.trim(),temp);
		return true;
		}
	
	
	/// Create a new Record and set the tables current record to it
	public void createRecord(){
		this.currentRecord = new Record(this.fields.size());
		return;
	}
	
	
	
	/// Check for values marked "not null" that are null add record if all values are supplied
	public String addRecord(){
		int tablesize = this.getFields().size();
		for(int i =0;i< tablesize;i++){
			if(this.getFields().get(i).isrequired() && currentRecord.getValues().get(i).equals("")){
				System.out.println("Error" +"the field " + this.getFields().get(i).getName() + " is required");
				return "error";
			}
		}
		String timeStamp = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss").format(new Date());
		currentRecord.TimeStamp = timeStamp;
		this.records.add(currentRecord);
		return null;
	}
	

	public void printFields(){
		String format = "%-20s";
		for(int i =0;i< this.fields.size();i++){
			System.out.printf(format,this.fields.get(i).getName()+"\t");
		}
		System.out.println();
	}
	
	public ArrayList<Integer> printFields(ArrayList<String> fields ){
		String format = "%-20s";
		ArrayList<Integer> fieldnums = new ArrayList<Integer>();
		for(int i =0;i< fields.size();i++){
			for(int j = 0; j<this.fields.size();j++){
				if(fields.get(i).equals(this.fields.get(j).getName())){
					System.out.printf(format,this.fields.get(j).getName()+"\t");
					fieldnums.add(j);
				}
			}
		}
		System.out.println();
		return fieldnums;
	}
	

	//// Returns an arraylist of records that contains values in the specified field that match the string value
	
	public ArrayList<Record> getRecords(String field, String op,String value){
		int index = findField(field);
		ArrayList<Record> results = new ArrayList<Record>();
		if(index ==-1){
			System.out.println("Error the field " + field + "does not exist\n");
			return null;
		}
		
		if(op.equals("=")){
			for(int i =0; i < records.size();i++){
				if(records.get(i).values.get(index).equals(value) ){
					results.add(records.get(i)); 
				}
			}
			return results;
		}
		if(op.equals("!=")){
			for(int i =0; i < records.size();i++){
				if(!records.get(i).values.get(index).equals(value) ){
					results.add(records.get(i)); 
				}
			}
			return results;
		}
		if(fields.get(index).getType().equals("charcter") ||fields.get(index).getType().equals("date")){
			System.out.println("Error cannot perform operation " + op+ " on type "+ fields.get(index).getType()+"\n");
			return null;
		}
		if(!isFloat(value)){
			System.out.println("Error invalid operator " + value+"\n");
			return null;
		}
		float temp = Float.parseFloat(value);
		if(op.equals(">")){
			for(int i =0; i < records.size();i++){
				if(Float.parseFloat(records.get(i).values.get(index)) > temp ){
					results.add(records.get(i)); 
				}
			}
			return results;
		}
		if(op.equals("<")){
			for(int i =0; i < records.size();i++){
				if(Float.parseFloat(records.get(i).values.get(index)) < temp ){
					results.add(records.get(i)); 
				}
			}
			return results;
		}
		if(op.equals("<=")){
			for(int i =0; i < records.size();i++){
				if(Float.parseFloat(records.get(i).values.get(index)) <= temp ){
					results.add(records.get(i)); 
				}
			}
			return results;
		}
		if(op.equals("<=")){
			for(int i =0; i < records.size();i++){
				if(Float.parseFloat(records.get(i).values.get(index)) <= temp ){
					results.add(records.get(i)); 
				}
			}
			return results;
		}
		System.out.println("Error");
		return null;
		
	}
	
	//-----Phil's-----
		public void printTable(){
			System.out.print("CREATE TABLE "+ this.getName()+ " (");
			for(int i =0;i < this.getFields().size();i++){
				if(i == this.getFields().size()-1)
					System.out.print(this.getFields().get(i).getName()+" "+this.getFields().get(i).getType());
				else
					System.out.print(this.getFields().get(i).getName()+" "+this.getFields().get(i).getType()+",");
			}
			System.out.print("); \n");
			for(int j=0;j<this.getRecords().size();j++){
				System.out.print("INSERT INTO "+ this.getName());
				System.out.print(" VALUES (");
				for(int i =0;i < this.getRecords().get(j).getValues().size();i++){
					if(i == this.getRecords().get(j).getValues().size()-1){
						System.out.print(this.getRecords().get(j).getValues().get(i) +"); \n");
					}
					else{
						System.out.print(this.getRecords().get(j).getValues().get(i) + ",");
					}
				}
			}
		}
		
		//-----Phil's-----
		public String getStringTable(){
			String currentTable = "";
			currentTable += "CREATE TABLE "+ this.getName()+ " (";
			for(int i =0;i < this.getFields().size();i++){
				currentTable += this.getFields().get(i).getName()+" "+this.getFields().get(i).getType();
				
				if(!this.getFields().get(i).getType().equalsIgnoreCase("date") && !this.getFields().get(i).getType().equalsIgnoreCase("number"))
					currentTable += "(" + Integer.toString(this.getFields().get(i).getLength()) + ")";
				else if(this.getFields().get(i).getType().equalsIgnoreCase("date"))
					currentTable += "(mm/dd/yyyy)";
				else if(this.getFields().get(i).getType().equalsIgnoreCase("number"))
					currentTable += "(" + Integer.toString(this.getFields().get(i).getLength() - this.getFields().get(i).getNumdecimal()) + ", " + Integer.toString(this.getFields().get(i).getNumdecimal()) + ")";
				
				if(this.getFields().get(i).isrequired())
					currentTable += " NOT NULL";
				
				if(i != this.getFields().size()-1)
					currentTable += ", ";
			}
			currentTable += "); \n";
			for(int j=0;j<this.getRecords().size();j++){
				currentTable += "INSERT INTO "+ this.getName();
				currentTable += " VALUES (";
				for(int i =0;i < this.getRecords().get(j).getValues().size();i++){
					if(i == this.getRecords().get(j).getValues().size()-1){
						currentTable += this.getRecords().get(j).getValues().get(i) +"); \n";
					}
					else
						currentTable += this.getRecords().get(j).getValues().get(i) + ",";
				}
			}
			
			return currentTable;
		}
	
	// method that checks if string is integer;
	public boolean isInteger( String input ) {
		try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
	
	public boolean isFloat( String input ) {
	    try {
	        Float.parseFloat( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
		
	}
	

