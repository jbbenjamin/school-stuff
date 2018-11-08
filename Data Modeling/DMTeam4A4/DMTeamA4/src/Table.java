import java.util.ArrayList;

public class Table {
	private String name;
	private ArrayList<Field> fields;
	private ArrayList<Record> records;

	public Table(String name) {
		super();
		this.name = name;
		fields = new ArrayList<Field>();
		records = new ArrayList<Record>();
	}
	
	public void printTable(){
		System.out.print("CREATE TABLE "+ this.getName()+ " (");
		for(int i =0;i < this.getFields().size();i++){
			if(i == this.getFields().size()-1){
				System.out.print(this.getFields().get(i).getName());
			}
			else
				System.out.print(this.getFields().get(i).getName()+",");
		}
		System.out.print("); \n");
		for(int j=0;j<this.getRecords().size();j++){
			System.out.print("INSERT INTO "+ this.getName());
			System.out.print(" VALUES (");
			for(int i =0;i < this.getRecords().get(j).getValues().size();i++){
				if(i == this.getRecords().get(j).getValues().size()-1){
					System.out.print(this.getRecords().get(j).getValues().get(i) +"); \n");
				}
				else
				System.out.print(this.getRecords().get(j).getValues().get(i) + ",");
			}
		}
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

	// returns true if no problems
	public void addRecord(Record record){
		//for(int i = 0;i < this.getFields().size();i++){
		//	if(!record.getValues().get(i).matches(this.getFields().get(i).getRegex())){
		//		return false;
			//}
		//}
		this.records.add(record);
	}
}
