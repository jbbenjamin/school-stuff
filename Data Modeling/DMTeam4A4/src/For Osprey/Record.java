import java.util.ArrayList;

public class Record {
	ArrayList<String> values;
	String Error;
	String TimeStamp;
	
	public Record(int size) {
		values = new ArrayList<String>();
		while(this.values.size() < size){
			String initialize = "";
			this.values.add(initialize);
		}
		
	}
	public void setValue(String value,int index){
		this.values.set(index, value);
		
	}
	public ArrayList<String> getValues() {
		return values;
	}
	
	public void printRecord(boolean tStamp){
		String format = "%-20s";	
		for(int i =0;i <this.values.size();i++){
				
				System.out.printf(format,this.values.get(i));
			}
			if(tStamp)
				System.out.print(this.TimeStamp);
			System.out.println();
		
	}
	public void printRecord(boolean tStamp,ArrayList<Integer> integers ){
		String format = "%-20s";
		for(int i =0;i <integers.size();i++){
			System.out.printf(format,this.values.get(integers.get(i)));
		}
		if(tStamp)
			System.out.print(this.TimeStamp);
		System.out.println();
	}
}
