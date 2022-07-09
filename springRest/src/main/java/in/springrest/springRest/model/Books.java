package in.springrest.springRest.model;

public class Books {
	public long id;
	public String name;
	public String author;
	
	public Books(long id, String name, String author) {
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}
	
	

}
