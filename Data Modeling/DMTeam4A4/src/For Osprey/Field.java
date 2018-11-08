
public class Field {
	private String name;
	private int length; 
	private String type;
	private boolean required;
	private int numdecimal;

	public int getNumdecimal() {
		return numdecimal;
	}

	public void setNumdecimal(int numdecimal) {
		this.numdecimal = numdecimal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Field(String name, String type,int length,boolean isRequired) {
		super();
		this.name = name;
		this.length = length;
		this.required = isRequired;
		this.type = type;
		this.numdecimal = 255;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isrequired() {
		return required;
	}

	public void setrequired(boolean isrequired) {
		this.required = isrequired;
	}

}
