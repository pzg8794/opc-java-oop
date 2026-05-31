/*
 * Apple.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

//Import statements are placed here
import java.text.DecimalFormat;
import java.util.Scanner;



/**
 * This program creates a class Apples that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */

public class Apples extends Fruit{

	private double weight;
	private int numberOfFruits;
	private double pricePerPound;
	
  
	
	
	public Apples(String color,double weight,double pricePerPound, int numOfFruits) throws FruitException, FruitException2 {
		super(color);
		
  		if (weight < 0){
  			throw new FruitException2("The weight of the fruits cannot be negative");
  		}
  		else
  			this.weight = weight;
  		
  		
  		if (numOfFruits  > 5 || numOfFruits < 1){
			  throw new FruitException(" The number of apple(s) cannot be negative or greater than 5  ");
		}
  		else
  			this.numberOfFruits = numOfFruits;
  		
  		 if ( pricePerPound < 0){
			  throw new FruitException2(" The Price Cannot be negative ");
		  }
  		 else
  			 this.pricePerPound = pricePerPound;
  		
	}
  
	public Apples() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public double getWeight(){
		return weight;
	} 
		
	
	public double getPrice(){
		return pricePerPound;
	}
		
	
	
	public double totalPrice(){
		return weight * pricePerPound;
	}
  
	public String report(){
		return "I bought "+ getColor() + " apples.";
	}

	
	
	//Method to display the Apple Information
	public  void displayApplesInfo(Apples appleObj){ 
		
		System.out.println("\nThe apple(s) color is: "+ appleObj.getColor());
		appleObj.getPrice();  
		System.out.println("The weight of all apples is: "+ weight +" pound.");
		System.out.println("The total cost of the apple(s) purchsed is:  $"+ appleObj.totalPrice());
		System.out.println("\n" + report());
  }

	
	
   /**
   * Gets user input 
   * @param    appleObj   Object of class Apple
   */
  
  public static void main(String args[]) {

		Apples test = new Apples();
		while(true){
			int x = 0;
			try {
				Thread.sleep(100);
				test.gettingUserInput();
			} 
			catch (FruitException2 e) {
				e.printStackTrace();
				x = 1;
			
			} 
			catch (FruitException e) {
				e.printStackTrace();
				x = 2;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(x == 0)
					break;
			
			}
		}
  } 
  
  private void gettingUserInput() throws FruitException, FruitException2{
	  
	  String color1, fruitName1;
	  System.out.println("\nEnter the fruit to be purchased");
	 
	  Scanner appleReader = new Scanner(System.in);
	  fruitName1 = appleReader.nextLine();
    
	  if (( fruitName1.equals("Apples")) || ( fruitName1.equals("Apple")) ||
    		( fruitName1.equals("apples")) || ( fruitName1.equals("apple"))) {
		 
		  System.out.println("Enter the color of the apple(s) to be purchased");
		  color1 = appleReader.nextLine();
		  
		  System.out.println("Enter the number of apple(s) to be purchased");
		  this.numberOfFruits = appleReader.nextInt();
			if (this.numberOfFruits  > 5 || this.numberOfFruits < 1){
				
				throw new FruitException(" The number of apple(s) cannot be less than 1 \n" +
						" or greater than 15  ");
			}
					
		  System.out.println("Enter the weight(in pounds) of all apples to be purchased");
		  this.weight = appleReader.nextDouble();
		  if (this.weight  < 0){
				
				throw new FruitException(" The weight of apple(s) cannot be negative ");
		  }
				
		  
		  System.out.println("Enter the price of each apple per pound");
		  this.pricePerPound = appleReader.nextDouble();
		  if (this.pricePerPound < 0){
				
				throw new FruitException2(" The price of apple(s) cannot be negative ");
		  }
      
		  Apples appleObj1 = new Apples(color1, this.weight, this.pricePerPound, this.numberOfFruits);
		  appleObj1.displayApplesInfo(appleObj1); 
	  }
	  else{
			System.out.println("Wron name, Please Enter the Apple(s) or apple(s)");
			gettingUserInput();
	  }
  }


/**
* @Function: displayingApplesPurchased(Apple[])
*
* @Description:	it gets the total price of read & green apple(s) purchased.
* Then, it displays the total price.
*
* @param int, double.
* 
*/
public static void displayingApplesPurchased(Apples appleObj1, int numOfFruits){
    

    DecimalFormat df = new DecimalFormat("#.##");
        
    //Displaying total price spent in red apple(s).
	//Displaying total price spent in red apple(s).
    System.out.println("\t APPLE(S) BOUGHT ");
	System.out.printf("Total Amount Spent in " + numOfFruits + " " + appleObj1.getColor() + " Apple(s) is $" + df.format(appleObj1.getWeight()*appleObj1.getPrice()));
	System.out.println(); 
	System.out.printf("Total Weight of All Apple(s) is $" + df.format(appleObj1.getWeight()));
	System.out.println();
	//Displaying total price spent in green apple(s).
	System.out.printf("\nPrice per Pound is $" + df.format(appleObj1.getPrice()));
	System.out.println(); 
	System.out.printf("Total Price Spent is $" + df.format(appleObj1.getWeight()*appleObj1.getPrice())); 
	  
}
}