package model;

public class Ticket {
	
	protected int id;
	protected String subject;
	protected String email;
	protected String name;
	protected String issue;
	
	
	
	public Ticket(int id, String subject, String name, String issue) {
		super();
		this.id = id;
		this.subject = subject;
		this.name = name;
		this.issue = issue;
	}

	public Ticket(int id, String subject, String email, String name, String issue) {
		super();
		this.id = id;
		this.subject = subject;
		this.email = email;
		this.name = name;
		this.issue = issue;
	}

	public Ticket(String subject, String email, String name, String issue) {
		super();
		this.subject = subject;
		this.email = email;
		this.name = name;
		this.issue = issue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	
	
	

}
