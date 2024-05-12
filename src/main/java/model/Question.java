package model;

public class Question {
	
	protected int id;
	protected String question;
	
		
	public Question(int id, String question) {
		super();
		this.id = id;
		this.question = question;

	}

	public Question(String question) {
		super();
		this.question = question;
	
	}

	public int getQuestionid() {
		return id;
	}

	public void setQuestionid(int questionid) {
		this.id = questionid;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	
	

}
