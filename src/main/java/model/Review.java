package model;

public class Review {
	
	protected int id;
	protected String name;
	protected String desc;
	
	public Review(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
	}

	public Review(int id, String name, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


}
