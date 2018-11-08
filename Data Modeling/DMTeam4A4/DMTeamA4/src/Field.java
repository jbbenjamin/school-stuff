import java.util.ArrayList;

public class Field {
	private String name;
	private String regex;

	public Field(String name, String regex,boolean isRequired) {
		super();
		this.name = name;
		this.regex = regex;
		ArrayList<String> values = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

}
