package Entites;

public class Property {
	private final String title;
	private final String location;
	private final int price;

	private final String type;
	private final int size;
	private final String rooms;
	private final String owner;
	private  boolean sold;

	public Property(String title,String location, int price, String type, int size, String rooms,String owner,boolean sold){
		this.title = title;
		this.location = location;
		this.price = price;
		this.type = type;
		this.size = size;
		this.rooms = rooms;
		this.owner = owner;
		this.sold = false;
	}

	public  String getTitle(){
		return title;
	}

	public  String getLocation(){
		return location;
	}

	public int getPrice(){
		return price;
	}

	public String getType(){
		return type;
	}

	public int getSize(){
		return size;
	}

	public String getRooms(){
		return rooms;
	}

	public String getOwner(){
		return owner;
	}

	public boolean isSold(){
		return sold;
	}

	public void setSold(boolean sold){
		this.sold = sold;
	}


}
