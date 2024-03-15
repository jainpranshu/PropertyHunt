package Entites;

public class User {
	private final String name; //id primary key

	private boolean active;

	public User(String name){
		this.name = name;
		this.active = true;
	}

	public String getName(){
		return name;
	}

	public  boolean isActive(){
		return active;
	}

	public void setActive(boolean active){
		this.active = active;
	}

}
