
public class Question implements Complexity{
	
private String question, answer;
private int complexityLevel;
	
public Question(String query, String result){
		question = query;
		answer = result;
		complexityLevel = 1;
}
	
public void setComplexityLevel(int level){
	complexityLevel = level;
}

public int getComplexity(){
	return complexityLevel;
}

public String getQuestion(){
	return question;
}

public String getAnswer(){
	return answer;
}

public boolean answerCorrect(String candidiateAnswer){
	return answer.equals(candidiateAnswer);
}

public String toString(){
	return "Yes";
}

@Override
public void setComplexity() {
	// TODO Auto-generated method stub
	
}
}
