// -----------------------------------------------------
// Assignment 1
// Part 1
// Written by: Anthony Chraim 40091014
// ----------------------------------------------------- 
/**
* <h1>Car Class</h1>
*This class is used to create an array of Car objects,
*all having a make, a mode, a year and a price
* <p>
*
* @author  Anthony Chraim
* @version 1.0
* @since   17/07/2019
*/
public class Car {

	private String make;
	private String model;
	private int year;
	private double price;
	public static int numCars = 0;

	public Car() {
		this(null,null, 0,0);
	}
	
	public Car (String make, String model) {
		this(make, model, 0, 0);
	}
	
	//all constructors refer this constructor so they don't need to increment the number of cars
	public Car(String make, String model, int year, double price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
		numCars ++;
	}
	/**
	   * This method is used to determine if a certain Car object has a
	   * price that lies between a certain price range
	   * @param min This parameter is the minimum price that a car should have
	   * @param max This parameter is the maximum price that a car should have
	   * @return boolean: The function returns true if a Car object has a price
	   * between the min and the max, and false if it does not.
	   */
	public boolean priceRange (double min, double max) {
		return (this.getPrice() <= max && this.getPrice() >= min);

	}
	
	@Override
	public String toString() {
		return "\nMake: " + make + "\nModel: " + model + "\nYear: " + year + "\nPrice=" + price + "$";
	}

	/**
	   * This method is used to determine if a certain Car object has the same 
	   * make and model as another. 
	   * 
	   * @param car The only parameter it takes is another Car object
	   * @return boolean: The function returns true if a Car object has the
	   * same make and model as another, and false if it does not.
	   */
	public boolean equals(Car car) {
		if (this == car)
			return true;
		if (car == null)
			return false;
		if (this.getClass() != car.getClass())
			return false;
		if (this.make == null) {
			if (car.make != null)
				return false;
		} else if (!this.make.equals(car.getMake()))
			return false;
		if (this.model == null) {
			if (car.model != null)
				return false;
		} else if (!this.model.equals(car.getModel()))
			return false;
		return true;
	}
	
	//setters and getters
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double newPrice) {
		this.price = newPrice;
	}
	
	
}
