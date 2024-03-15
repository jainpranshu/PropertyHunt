package service;

import Entites.Property;
import Entites.User;

import java.util.*;

public class functinality {
	private  static  Scanner scanner = new Scanner(System.in);
	private  static final List<Property> properties =new ArrayList<>();
	private static List<Property> shortListedProperties = null;
	public static void listProperty(User currentUser){

		if(!currentUser.isActive()){
			return;
		}
		System.out.println("Enter the Property Details");
//		String inpStr= scanner.nextLine();
//		String[] inp = inpStr.trim().split(",");
//
//
//		String title = inp[0].trim().substring(6);
//		String location = inp[1].trim().substring(9);
//		int price = Integer.parseInt(inp[2].trim().substring(6));
//		String type = inp[3].trim().substring(5);
//		String size1 = inp[4].trim().substring(5);
//
//		String[] sizearr = size1.split("s");
//		int size = Integer.parseInt(sizearr[0]);
//		String rooms = inp[5].trim().substring(6);



			System.out.println("Enter property Title");
			String title = scanner.nextLine();



			System.out.println("Enter property location");
			String location = scanner.nextLine();

			System.out.println("Enter property Price");
			int price = scanner.nextInt();
			scanner.nextLine();

			System.out.println("Enter property Type");
			String type = scanner.nextLine();

			System.out.println("Enter property Size");
			int size =  scanner.nextInt();
					scanner.nextLine();

			System.out.println("Enter property rooms");
			String rooms = scanner.nextLine();

		Property property = new Property(title,location,price,type,size,rooms,currentUser.getName(),false);
		properties.add(property);
	}

	public static void viewListedProperties(User currentUser){
		if(!currentUser.isActive()){
			return;
		}
		System.out.println("Properties Listed by "+ currentUser.getName()+ " :");
		for(Property property: properties){
			if(property.getOwner().equals(currentUser.getName())){

				System.out.println("Title "+property.getTitle()+"\t Location "+property.getLocation()+"\t type "+property.getType()+"\t Price "+ property.getPrice()+"\t rooms "+property.getRooms());
			}
		}
	}

	public static void searchProperties(){
		System.out.println("Enter Location or Leave Blank for any");
		String location = scanner.nextLine().trim().toLowerCase();
		System.out.println("Enter the range X-Y or X, or leave blank for any");
		String priceRangeInput = scanner.nextLine().trim();
		int minPrice,maxPrice = -1;
		if(!priceRangeInput.isEmpty()){
			String[] parts = priceRangeInput.split("-");
			if(parts.length == 1){
				minPrice = maxPrice = Integer.parseInt(parts[0]);
			} else if (parts.length == 2) {
				minPrice = Integer.parseInt(parts[0]);
				maxPrice = Integer.parseInt(parts[1]);

			} else {
				minPrice = -1;
			}
		} else {
			minPrice = -1;
		}

		System.out.println("Enter type sell/rent or leave blank for any");
		String type = scanner.nextLine().trim().toLowerCase();
		System.out.println("Enter size range X-Y or X or leave blank for any");
		String sizeRange = scanner.nextLine().trim();
		int minsize,maxSize = -1;
		if(!sizeRange.isEmpty()){
			String[] parts = sizeRange.split("-");
			if(parts.length == 1){
				minsize = maxSize = Integer.parseInt(parts[0]);
			} else if (parts.length == 2) {
				minsize = Integer.parseInt(parts[0]);
				maxSize = Integer.parseInt(parts[1]);

			} else {
				minsize = -1;
			}
		} else {
			minsize = -1;
		}


		System.out.println("Enter number of rooms 1BHK,2BHK or... etc");
		String rooms = scanner.nextLine().trim().toLowerCase();
		System.out.println("Sort by Price/size , leave blank for any");
		String sortBy = scanner.nextLine().trim();


		List<Property> result = new ArrayList<>(properties);
		result.removeIf(p -> !p.getLocation().toLowerCase().equals(location) && !p.getLocation().toLowerCase().contains(location));
		result.removeIf(p -> minPrice != -1 && p.getPrice() < minPrice);
		int finalMaxPrice = maxPrice;
		result.removeIf(p -> finalMaxPrice != -1 && p.getPrice() > finalMaxPrice);
		result.removeIf(p ->!type.isEmpty() && !p.getType().toLowerCase().equals(type));
		result.removeIf(p -> minsize != -1 && p.getSize()< minsize);
		int finalMaxSize = maxSize;
		result.removeIf(p -> finalMaxSize != -1 && p.getSize()> finalMaxSize);
		result.removeIf(p -> !rooms.isEmpty() && !p.getRooms().toLowerCase().equals(rooms));


		if(sortBy.equals("price")){
			result.sort(Comparator.comparingInt(Property::getPrice));
		}else {
			result.sort(Comparator.comparingInt(Property::getSize));
		}

		for(Property property: result){
			System.out.println(property.getTitle()+ " " + property.getLocation()+" "+ property.getPrice()+" "+ property.getType());
		}
	}

	public static void markPropertyAsSold(User currentUser) {
		if (currentUser == null) {
			System.out.println("You aren't logged in.");
			return;
		}

		System.out.print("Enter property title to mark as sold: ");
		String title = scanner.nextLine();

		boolean found = false;
		for (Property property : properties) {
			if (property.getOwner().equals(currentUser.getName()) && property.getTitle().equalsIgnoreCase(title)) {
				property.setSold(true);
				System.out.println("Property marked as sold.");
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Property not found or you don't have permission to mark it as sold.");
		}
	}

	public static void shortlistProperty(User currentUser) {
		if (currentUser == null) {
			System.out.println("You aren't logged in.");
			return;
		}

		System.out.print("Enter property title to shortlist: ");
		String title = scanner.nextLine();

		boolean found = false;
		shortListedProperties = new ArrayList<>();
		for (Property property : properties) {
			if (property.getTitle().equalsIgnoreCase(title)) {
				shortListedProperties.add(property);
				System.out.println("Property shortlisted: " + property.getTitle());
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Property not found.");
		}
	}

	public static void viewShortlistedProperties(User currentUser) {
		if (currentUser == null) {
			System.out.println("You aren't logged in.");
			return;
		}
		for(Property shortListedProperties: shortListedProperties){
			System.out.println(shortListedProperties.getTitle());
		}

		System.out.println("Viewing shortlisted properties ");
	}

	public static void showTopTrendingLocation() {
		Map<String, Integer> locationCount = new HashMap<>();
		for (Property property : properties) {
			if (!property.isSold()) {
				String[] locations = property.getLocation().split("\\|");
				for (String location : locations) {
					locationCount.put(location, locationCount.getOrDefault(location, 0) + 1);
				}
			}
		}

		if (locationCount.isEmpty()) {
			System.out.println("No properties listed yet.");
			return;
		}

		String topTrendingLocation = Collections.max(locationCount.entrySet(), Map.Entry.comparingByValue()).getKey();
		int propertyCount = locationCount.get(topTrendingLocation);
		System.out.println("Top Trending Location: " + topTrendingLocation + " (" + propertyCount + " properties listed)");
	}


}
