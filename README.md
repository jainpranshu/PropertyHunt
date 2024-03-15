Property Hunt - SDE2
Problem:
Your aim is to build a console application for a property platform where users can login,logout, list a property, and find properties matching their requirements.

You need to provide the following options:
•	Login, and LogOut: A user can login (No need for Authentication, the name can be considered as the id of the user). Login makes users active and logout makes them inactive. User should be created automatically when he logs in for the first time.
•	ListProperty: Users can list properties they are selling. Listing will have
o	title - Name of the property
o	location - Location of property (ex : bellandur, marathahalli)
o	price - (ex : 40000, 9000000)
o	type - (ex : sell, rent)
o	size - Always in sqft (ex : 1000sqft)
o	rooms - (ex : 3BHK, 5BHK)
•	ViewListed: Users can view properties listed by them.
•	Search: Users can search for listed properties (only available properties should appear in results). They can use a combination of:
o	Location (case insensitive exact match would suffice for search).
o	Price range.
o	ListingType: sell/rent
o	Size range. (always in sqft)
o	The number of rooms: 1BHK, 2BHK, 3BHK.
o	Sort by: It could be price or size (only ascending order is required, default value is size).
Users can choose any set of parameters above for search. All above search parameters are optional. If ‘Sort by” has not been provided then the default value of ‘Sort by’ is ‘size’ but in future we can add more sorting strategies. Your code should be able to plugin new strategies. 
•	MarkSold: Users can mark a property sold which was listed by them. This is used to close the listing of both(Rent and Sell) types of properties.
•	Shortlist: Users can shortlist a property.
•	ViewShortlisted: Users can view all the shortlisted properties (including sold but with the status as “sold”).

Note : The range can be given as “X-Y” for X to Y inclusive or “X” for more than or equal to X.

Bonus:
•	At any given time show the top trending location which has the most number of listed unsold properties.
•	Accept multiple locations as nearby locations for a property listing. In that case ‘ListProperty’ will have extra parameter as : 
o	nearby - Nearby locations (ex : sarjapur|kadubeesanahalli|marathahalli, marathahalli|krpuram)
If the ‘Sort by’ is provided as ‘location’ then search should list properties with nearby locations also. Here properties which are a match in nearby locations should appear last. 

Guidelines:
•	Input can be read from a file or STDIN or coded in a driver method.
•	Output can be written to a file or STDOUT.
•	No need to create any UX.
•	Store all interim/output data in-memory. The usage of databases is not allowed.
•	Restrict internet usage to looking up syntax.
•	You are free to use the language of your choice.

Expectations:
•	The code should be demo-able (very important). The code  should be functionally correct and complete.
o	At the end of this interview round, an interviewer will provide multiple inputs to your program for which it is expected to work 
•	The code should handle edge cases properly and fail gracefully. Add suitable exception handling, wherever applicable.
o	An example would be to display an error message saying “You aren’t logged in.” when the user is trying to list a property without logging in.
•	The code should have a good object-oriented design.
•	The code should be readable, modular, testable, and extensible. Use intuitive names for your variables, methods, and classes.
o	It should be easy to add/remove functionality without rewriting a lot of code.
o	Do not write a monolithic code.
•	Don’t use any databases.
•	Bonus section should be attempted only after completing the main functionality

Sample Test Cases:

Input and Output (You may change the input-output format without changing the functionality to suit your needs):
→ is input
← is output


→ login tej
← Welcome tej

→ ListProperty
← Enter the property details:
→ title A 3BHK for sale, location bellandur, price 9000000, type sell, size 1800sqft, rooms 3BHK
← Listing created successfully.

→ ListProperty
← Enter the property details:
→ title A 2BHK for rent, location sarjapur, price 40000, type rent, size 1500sqft, rooms 2BHK, nearby bellandur|kadubeesanahalli|marathahalli
← Listing created successfully.

→ search location bellandur, pricerange 30000-50000, type rent, sizerange 150sqft, rooms 2BHK, sort price
← Id	Title			Location	Price	Size	Rooms	AvailableFor
← 2	A 2BHK for rent 	sarjapur 	40000 	1500sqf 2BHK	Rent

→ ShortList 2
← Shortlisted

← ViewShortlisted
← Id	Title			Location	Price	Size	Rooms	AvailableFor	Status
← 2	A 2BHK for rent 	sarjapur 	40000 	1500sqf 2BHK	Rent		Available

→ search
← Id	Title			Location	Price		Size		Rooms	AvailableFor
← 2	A 2BHK for rent 	sarjapur 	40000	 	1500sqft 	2BHK	Rent
← 1	A 3BHK for sale	bellandur	9000000	1800sqft 	3BHK	Sale

→ search location bellandur, sort location ##(only for Bonus section)
← Id	Title			Location	Price		Size		Rooms	AvailableFor
← 1	A 3BHK for sale	bellandur	9000000	1800sqft 	3BHK	Sale
← 2	A 2BHK for rent 	sarjapur 	40000	 	1500sqft 	2BHK	Rent

→ logout 
← Bye tej


