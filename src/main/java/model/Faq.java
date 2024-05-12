package model;

public class Faq {
	
	protected int id;
	protected String category;
	protected String problem;
	protected String solution;
	protected String author;
	
	public Faq(int id, String category, String problem, String solution, String author) {
		super();
		this.id = id;
		this.category = category;
		this.problem = problem;
		this.solution = solution;
		this.author = author;
	}

	public Faq(String category, String problem, String solution, String author) {
		super();
		this.category = category;
		this.problem = problem;
		this.solution = solution;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

	
	
	

}
