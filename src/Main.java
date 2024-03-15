import Entites.Property;
import Entites.User;

import java.awt.*;
import java.util.*;
import java.util.List;

import static service.functinality.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

	private  static final Map<String, User> users = new HashMap<>();

	private  static User currentUser;

	private static final Scanner sc=  new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			System.out.println("1. Login");
			System.out.println("2. Logout");
			System.out.println("3. List Property");
			System.out.println("4. View Listed Property");
			System.out.println("5. Search Property");
			System.out.println("6. Mark Properties Sold");
			System.out.println("7. Shortlist Property");
			System.out.println("8. View ShortListed");
			System.out.println("9. Show Top Trending Location"); // Bonus implementation
			System.out.println("10. Exit");
			//choose option to perform

			System.out.println("Enter your Functionality");
			int option = sc.nextInt();

			//take new Line
			sc.nextLine();

			switch(option){
				case 1:
					login();
					break;
				case 2:
					logout();
					break;
				case 3:
					if(!currentUser.isActive()){
						System.out.println("No Active User");
					}
					listProperty(currentUser);
					break;
				case 4:
					viewListedProperties(currentUser);
					break;
				case 5:
					searchProperties();
					break;
				case 6:
					markPropertyAsSold(currentUser);
					break;
				case 7:
					shortlistProperty(currentUser);
					break;
				case 8:
					viewShortlistedProperties(currentUser);
					break;
				case 9:
					showTopTrendingLocation(); //bonus
					break;
				case 10:
					System.out.println("----Exit-----");
					break;
				default:
					System.out.println("INVALID INPUT");
					break;
			}
		}
	}

	private static void login(){
		System.out.println("ENter your Name");
		String name = sc.nextLine();
		if(!users.containsKey(name)){
			users.put(name,new User(name));
			System.out.println("Welcome "+ name);
		}else {
			users.get(name).setActive(true);
			System.out.println("User login successfully");
		}
		currentUser = users.get(name);
	}

	private static void logout() throws RuntimeException {

		if(currentUser == null){
			System.out.println("No Active User");
			login();

		}else{
			currentUser.setActive(false);
			currentUser = null;
			System.out.println("Logout successfully ");
		}

	}
}