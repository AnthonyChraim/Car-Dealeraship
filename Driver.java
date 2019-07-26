// -----------------------------------------------------
// Assignment 1
// Part 2
// Written by: Anthony Chraim 40091014
// ----------------------------------------------------- 

/**
* <h1>Driver Class</h1>
*This class is used to stimulate a car dealership program 
*that puts all the cars in your inventory in an array with 
*the posibility to modify the info of any cars, and also displays
*the cars depending on the make, model, and price range.
* <p>
*
* @author  Anthony Chraim
* @version 1.0
* @since   17/07/2019
*/


//program starts here
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		//defining variables
		boolean carSamePrice, carSameSpecs;
		Car sameCar;
		boolean correctPW1 = false, correctPW2 = true;
		int carPriceMax, carPriceMin;
		String specifiedMake, specifiedModel;
		double newPrice;
		int newYear;
		String newMake, newModel;
		int attributeChange;
		int updateIndex, sameIndexOption =0;
		int passwordAttempts1 = 0, passwordAttempts2 = 0;
		String password;
		int maxCars = 0, numCars, carsLeft = 0;
		int option;
		Scanner sc = new Scanner(System.in);
		Car carDatabase[];
		int inventoryNum = 0;
		
		System.out.println("Welcome to my (Anthony Chraim) car dealership program");
		System.out.println("Please input the maximum amount ou cars your company can handle");
		maxCars = sc.nextInt();
		carDatabase = new Car [maxCars];

		//using menu as a directed label to make the "continue" more organized
		menu:
		do {
			System.out.println("\nWhat do you want to do?\n" + 
					"1. Enter a new Car to the inventory (password required)\n" + 
					"2. Change information of an existing Car (password required)\n" + 
					"3. Display all the Cars with the specified make and model\n" + 
					"4. Display all the Cars within the given price range\n" + 
					"5. Quit\n" + 
					"Please enter your choice >");
			option = sc.nextInt();
			
			switch(option) {
			
			
			case 1:
				passwordAttempts2 = 0;
				
				//this loop is done as long as the password is wrong, but gets out after 3 failed consecutive attempts.
				//the program shuts down after 12 failed consecutive attempts
				do {
					System.out.println("Please enter the password");
					password = sc.next();
					
					if (checkPassword(password)) {
						correctPW1 = true;
						System.out.println("How many cars would you like to enter");
						numCars = sc.nextInt();
						
						if (maxCars -inventoryNum >= numCars) {
							for (int i = 0; i < numCars  ; i++) {
								System.out.println("Please enter the make of car at index " + inventoryNum);
								String make = sc.next();
								System.out.println("Please enter the model of car at index " + inventoryNum);
								String model = sc.next();
								System.out.println("Please enter the year of car at index " + inventoryNum);
								int year = sc.nextInt();
								System.out.println("Please enter the price of car at index " + inventoryNum);
								double price = sc.nextDouble();
								
								//creating an array of objects car that take these 4 arguments
								carDatabase[inventoryNum++] = new Car(make, model, year, price);
							}
						}
						else {
							System.out.println("You only have " + carsLeft + " cars remaining");
							continue;
						}
					}
					//incorrect password
					else {
						passwordAttempts1 ++;
						if(passwordAttempts1 >= 12) {
							System.out.println("Program detected suspicious activity and is terminating!");
							System.exit(0);
						}
						else if ((passwordAttempts1)%3.0 == 0 && passwordAttempts1 >= 3)
							break;
						else {
							correctPW1 = false;
							System.out.println("Wrong password");
						}
					}
				} while (!correctPW1);
				break;
				
			//
			case 2:
				//restting the password attempts for option 1
				passwordAttempts1 = 0;
				do {
					System.out.println("Please enter the password");
					password = sc.next();
					if (checkPassword(password)) {
						correctPW2 = true;
						sameIndexOption = 1;
						do {
							System.out.println("What index is the car that you would like to update at?");
							updateIndex = sc.nextInt();
							
							if (carDatabase[updateIndex] == null || updateIndex < 0 || (updateIndex >= inventoryNum)) {
								System.out.println("There is no car at this index. press 1 if you would like to re-enter an index, or 2 to go back to the main menu");
								sameIndexOption = sc.nextInt();
								if(sameIndexOption != 1)
									//breaks to the directed label "menu"
									continue menu;
							}
							else 
								break;
						}while (sameIndexOption == 1);
							
						System.out.println(carDatabase[updateIndex]);
						
						do {
							System.out.println("What information would you like to change?\n" + 
									"1. Car make\n" + 
									"2. Car model\n" + 
									"3. Car year\n" + 
									"4. Car price\n" + 
									"5. Quit\n" + 
									"Please enter your choice >");
							attributeChange = sc.nextInt();
							
							switch(attributeChange) {
							case 1:
								System.out.println("What is the new make?");
								newMake = sc.next();
								carDatabase[updateIndex].setMake(newMake);
								break;
								
							case 2:
								System.out.println("What is the new model?");
								newModel = sc.next();
								carDatabase[updateIndex].setModel(newModel);
								break;
								
							case 3:
								System.out.println("What is the new year?");
								newYear = sc.nextInt();
								carDatabase[updateIndex].setYear(newYear);
								break;
								
							case 4:
								System.out.println("What is the new price?");
								newPrice = sc.nextDouble();
								carDatabase[updateIndex].setPrice(newPrice);
								break;
								
							case 5: 
								break;
							
							default:
								System.out.println("Please enter a valid option");
							}
						} while (attributeChange != 5);
					}
					
					//wrong password
					else {
						passwordAttempts2++;
						if ((passwordAttempts2) % 3.0 == 0 && passwordAttempts2 >= 3)
							break;
						else {
							correctPW2 = false;
							System.out.println("Wrong password");
						}
					}
				}while (!correctPW2);
				break;
				
			case 3:
				carSameSpecs = false;
				passwordAttempts1 = 0;
				if (inventoryNum > 0) {
					System.out.println("Please enter a make");
					specifiedMake = sc.next();
					System.out.println("Please enter model");
					specifiedModel = sc.next();
					
					//printing all the cars with the same make and model as the one inputed by the user
					sameCar = new Car();
					for (int i = 0; i < inventoryNum; i++) {
						sameCar.setMake(specifiedMake);
						sameCar.setModel(specifiedModel);
						if (sameCar.equals(carDatabase[i])) {
							System.out.println("car # " + i + carDatabase[i] + "\n");
							carSameSpecs = true;
						}
					}
					if (!carSameSpecs) {
						System.out.println("There are no cars with these specifications");
					}
				}
				else
					System.out.println("Inventory is empty");
				break;
				
			case 4:
				carSamePrice = false;
				passwordAttempts1 = 0;
				if (inventoryNum > 0) {
					System.out.println("Please enter a minimum price of the car.");
					carPriceMin = sc.nextInt();
					System.out.println("Please enter a maximum price of the car.");
					carPriceMax = sc.nextInt();
					
					for (int i = 0; i < inventoryNum; i++) {
						if (carDatabase[i].priceRange(carPriceMin, carPriceMax)){
							System.out.println("car # "+ i  + carDatabase[i] + "\n");
							carSamePrice = true;
						}
					}
					if (!carSamePrice) {
						System.out.println("There are no cars at this price point");
					}
				}
				else
					System.out.println("No match, please try again");
				break;
				
			case 5:
				System.out.println("Thank you for using our service, and have a nice day!");
				break;
				
			default:
				System.out.println("Please enter a number between 1 and 5");	
			}
			
		}while (option != 5);
		
		sc.close();

	}
	/**
	   * This method is used to determine if the password entered by the user
	   * is the right one
	   * @param password This parameter is the password entered by the user
	   * @return boolean returns true if the password is right and false if it
	   * is not
	   */
	public static boolean checkPassword (String password) {
		return "comp249S19".equals(password);
	}
}
