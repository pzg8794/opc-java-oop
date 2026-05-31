/*
 * TestFruit.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

// Import statements are placed here
import java.io.*;
import java.util.Scanner;



/**
 * This program creates an abstract class TestFruit.
 * It is used to check erroneous conditions in Apples
 * and Mangoes class.
 *
 * @author      Piter Garcia
 */
 public class TestFruit extends Fruit {
	 Scanner menuSelection = new Scanner(System.in);
  
	public TestFruit() {
		super(" ");
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	* Test user input 
	* @param    mangoObj   Object of class Mango
	*/
	public static void main(String args[]){
		TestFruit testFruitObj = new TestFruit();
		try {
			testFruitObj.getUserInput();
		} catch (FruitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FruitException2 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	* Gets user input 
	* @param    mangoObj   Object of class Mango
	 * @throws FruitException2 
	 * @throws InterruptedException 
	*/
	public void getUserInput()throws FruitException,IOException, FruitException2, InterruptedException{
		String color1, color2, selection;
		char select;
		double weight1 = 0, pricePerPound1 = 0;
		int numOfFruits1 = 0;
	
		
		/**
		* Displaying user's menu
		* @param   String
		*/
		System.out.println(" \t Piter's Fruis tMarket");
		FruitMenu();
		selection = menuSelection.nextLine();
		select = selection.charAt(0);
	  
		switch( select){
		case '1':
		  
		colorMenu("Apple(s)");
		color1 = menuSelection.nextLine();
		testingApplesParameters(color1, weight1, numOfFruits1, pricePerPound1);
		  
		break;
		  
		case '2':
		  
			colorMenu("Mango(s)");
			color1 = menuSelection.nextLine();
			testingMangosParameters(color1, pricePerPound1, numOfFruits1);
		  
		break;
		 
		case '3':
			System.out.println(" Ok, Let's First do your Apple(s) \n");
			colorMenu("Apple(s)");
			color1 = menuSelection.nextLine();
			
			char c= color1.charAt(0);
			if(c == '1' || c == '2' || c == 'q'){
				color1 = "q";
				
			}
	
			while( (color1 != "q")){
				
					System.out.println(" Wrong Collor, try again \n");
					color1 = menuSelection.nextLine();
					c= color1.charAt(0);
					
					if(c == '1' || c == '2'){
						color1 = "q";
						
					}
					
			}
			
			if( c != 'q'){
				weight1=testingWeights();
  	  			numOfFruits1 = gettingNumberOfFruit(color1);
  	  			pricePerPound1 = gettingPricePerUnitt("apple");
			}
			else{
				System.out.println("You Have Canceled this Purchase \n");
			}
			color1= "x";
			
			Thread.sleep(100);
		  
			if( !color1.equals("q")){
				System.out.println("\n\nNow, Let's do your Mango(s) \n");
				colorMenu("Mango(s)");
				color2 = menuSelection.next();	
				testingMangosParameters(color2, pricePerPound1, numOfFruits1);
			}
			System.out.println("\n\n");
			
			if (c == '1'){
				Apples appleObj1 = new Apples("red",weight1,pricePerPound1, numOfFruits1);
				Apples.displayingApplesPurchased(appleObj1, numOfFruits1);
			}
			if( c =='2'){
				Apples appleObj1 = new Apples("green",weight1,pricePerPound1, numOfFruits1);
				Apples.displayingApplesPurchased(appleObj1, numOfFruits1);
			}
			
	        
				
			break;
		  
		default:
		  
			if(selection.equals("q")){
				System.out.println(" You have canceled your purchase \n Have a good day!");
			}
			else{
				
				System.out.println(" Sorry but " + selection + " is not a valid key ");
				getUserInput();
			}
				break;
		} 
	}
  
	
	
	/**
	* Fruits Menu Display 
	* @param    String
	*/
	void FruitMenu(){
		System.out.println(" Please, Choose from below the fruit(s) you whish to Purchase");
		System.out.println(" 1: Apple(s)");
		System.out.println(" 2: Mango(s)");
		System.out.println(" 3: Apple(s) and Mango(s)");
		System.out.println(" \n Please, press q if you wish to canceled your purchase");
	}
	
	
	
	/**
	* Color Fruit Menu Display 
	* @param   String
	*/
	void colorMenu(String fruit){
		System.out.println(" Please, Choose from below the type of apple you whish to Purchase");
		System.out.println(" 1: Red " + fruit);
		System.out.println(" 2: Green " + fruit);
		System.out.println(" \n Please, press q if you wish to canceled your purchase");
	}
  
	
	
	
	/**
	* Displays user's processed input for the class Apples.
	* @param    String, double, double, int.
	 * @throws FruitException2 
	 * @throws FruitException 
	*/
	void appledisplaying(String color, double weight1, double	pricePerPound1, int numOfFruits) throws FruitException, FruitException2{	 
		Apples appleObj1 = new Apples(color,weight1,pricePerPound1, numOfFruits);
                Apples.displayingApplesPurchased(appleObj1, numOfFruits);

		//System.out.println(appleObj1.getPrice());
		//System.out.println(appleObj1.getWeight());
		//System.out.println(appleObj1.getColor());
	}	
  
	
	
	/**
	* Displays user's processed input for the class Mangos.
	* @param    String, double, double, int.
	 * @throws FruitException2 
	 * @throws FruitException 
	*/
	void mangodisplaying(String color, double	pricePerPound1, int numOfFruits) throws FruitException, FruitException2{
	      Mangos mangoObj1 = new Mangos(color,numOfFruits, pricePerPound1);
       	      mangoObj1.displayMangoInfo(mangoObj1, numOfFruits); 	
	}
  
	
	
	/**
	* Testing for Wrong Color enter by user for the any class Apples, Mangos, or both.
	* @param    String, double, double, int.
	 * @throws FruitException 
	 * @throws FruitException2 
	 * @throws InterruptedException 
	*/
	void wrongColorSelection(String fruit, String color1, double pricePerPound1, int numOfFruits) throws FruitException, FruitException2, InterruptedException{
		double weight1 = 0;
		char color =  color1.charAt(0);
		switch(color){
	  	  	case '1':
	  	  		weight1=testingWeights();
	  	  	  	numOfFruits = gettingNumberOfFruit(fruit);
	  	  	    pricePerPound1 = gettingPricePerUnitt("apple");
	  	  	    if(fruit.equals("Apple"))
	  	  	    	appledisplaying("Red", weight1, pricePerPound1, numOfFruits);
	  	  	    else
	  	  	    	mangodisplaying("Red", pricePerPound1, numOfFruits);
	  	
	  	        break;
	  		  
	  	  	case '2':
	  	  		
	  	  		numOfFruits = gettingNumberOfFruit(fruit);
	  	  		
	  	  	    if(fruit.equals("Apple")){
	  	  	    	weight1=testingWeights();
	  	  	    	pricePerPound1 = gettingPricePerUnitt("apple");
	  	  	    	appledisplaying("Green", weight1, pricePerPound1, numOfFruits);
	  	  	    }
	  	  	    else{
	  	  	    	pricePerPound1 = gettingPricePerUnitt("mango");
	  	  	    	mangodisplaying("Green", pricePerPound1, numOfFruits);
	  	  	    }
	  	        break;
	  		  
	  	  	case '3':
	  		  	if(fruit.equals("Apple")){
	  		  		weight1=testingWeights();
	  		  		System.out.println("\n Ok, Let's First do Read Apple(s) \n");
	  		  	}
	  		  	else
	  		  		System.out.println("\n Ok, Let's First do Read Mango(s) \n");	
	  		  
		  	  	numOfFruits = gettingNumberOfFruit(fruit);
		  	   
		  	    
		  	    if(fruit.equals("Apple"))
	  	  	    	appledisplaying("Red", weight1, pricePerPound1, numOfFruits);
	  	  	    else
	  	  	    	mangodisplaying("Red", pricePerPound1, numOfFruits);	
		  	    
		  	    System.out.println(" ");
		  	    
	  		  	if(fruit.equals("Apple")){ 
	  		  		weight1=testingWeights();
	  		  		System.out.println("\n Now, Let's do Green Apple(s) \n");
	  		  		pricePerPound1 = gettingPricePerUnitt("apple");
	  		  		
	  		  	}
	  		  	else{
	  		  		System.out.println("\n Now, Let's do Green Mango(s) \n");
	  		  		pricePerPound1 = gettingPricePerUnitt("mango");
	  		  	}
	  		
		  	  	numOfFruits = gettingNumberOfFruit(fruit);
		  	    
		  	    
		  	    if(fruit.equals("Apple"))
	  	  	    	appledisplaying("Red", weight1, pricePerPound1, numOfFruits);
	  	  	    else
	  	  	    	mangodisplaying("Red",  pricePerPound1, numOfFruits);	
	  		  break;
	  		  
	  	  default:
	  		  break;
	  	}
	}
  
    
  
	/**
	* Testing for Wrong Color enter by user for the any class Apples, Mangos, or both.
	* @param    String, double, double, int.
	 * @throws FruitException 
	 * @throws FruitException2 
	 * @throws InterruptedException 
	 * @throws IOException 
	*/
	void wrongFruitSelection(String selection) throws FruitException, FruitException2, InterruptedException, IOException{
		String color1 = null;
		double weight1 = 0, pricePerPound1 = 0;
		int numOfFruits1 = 0;
		char select = selection.charAt(0);
		switch( select){
			case '1':
		  
				colorMenu("Apple(s)");
				color1 = menuSelection.nextLine();
				testingApplesParameters(color1, weight1, numOfFruits1, pricePerPound1);
				break;
		  
			case '2':
		  
				colorMenu("Mango(s)");
				color1 = menuSelection.nextLine();
				testingMangosParameters(color1, pricePerPound1, numOfFruits1);
				break;
		  
			case '3':
		  
				System.out.println("\n Ok, Let's First do your Apple(s) \n");
				colorMenu("Apple(s)");
				color1 = menuSelection.nextLine();
				testingApplesParameters(color1, weight1, numOfFruits1, pricePerPound1);
				System.out.println("\n Now, Let's do your Mango(s) \n");
				colorMenu("Mango(s)");
				color1 = menuSelection.nextLine();
				testingMangosParameters(color1, pricePerPound1, numOfFruits1);		  
			break;
	  
			default:
				break;
	  } 
	}
  
	
	
	/**
	* Testing for all parameters of the Class Apples.
	* @param    String, double, double, int.
	 * @throws FruitException 
	 * @throws FruitException2 
	 * @throws InterruptedException 
	*/
	public void testingApplesParameters(String color1, double weight1, int	numOfFruits1, double pricePerPound1) throws FruitException, FruitException2, InterruptedException, IOException{
		char color = color1.charAt(0); 
		switch(color){
			case '1':
	  	  		weight1=testingWeights();
	  	  		numOfFruits1 = gettingNumberOfFruit("Apple(s)");
	  	  		pricePerPound1 = gettingPricePerUnitt("apple");
	  	  		appledisplaying("Red", weight1, pricePerPound1, numOfFruits1);	
	  	  		break;
		  
			case '2':
				weight1=testingWeights();
				numOfFruits1 = gettingNumberOfFruit("Apple(s)");
				pricePerPound1 = gettingPricePerUnitt("apple");
				appledisplaying("Green", weight1, pricePerPound1, numOfFruits1);	
				break;
		 
			default:
				if(color1.equals("q")){
					System.out.println(" You have canceled your purchase \n Have a good day!");
				} 
				while(!color1.equals("q")){
					System.out.println(" Sorry but " + color1 + " is not a valid key ");
					colorMenu("Apple(s)");
					color1 = menuSelection.nextLine();  
					wrongColorSelection("Apple", color1, pricePerPound1,numOfFruits1);  
					if(color1.equals("1") || color1.equals("2"))
						color1 = "q";
				}
				break;
	  }
	}
  
	
	
	/**
	* Testing for all parameters of the class Mangos.
	* @param    String, double, double, int.
	 * @throws FruitException 
	 * @throws FruitException2 
	 * @throws InterruptedException 
	*/
	public void testingMangosParameters(String color1, double pricePerPound1, int numOfFruits1) throws FruitException, FruitException2, InterruptedException{
		char color = color1.charAt(0);
		switch(color){
		case '1':
			numOfFruits1 = gettingNumberOfFruit("Mango(s)");
			pricePerPound1 = gettingPricePerUnitt("mango");
			mangodisplaying("Red",  pricePerPound1, numOfFruits1);				        
			break;	
			
		case '2':
	  		numOfFruits1 = gettingNumberOfFruit("Mango(s)");
	  		pricePerPound1 = gettingPricePerUnitt("mango");
	  		mangodisplaying("Green", pricePerPound1, numOfFruits1);	
		  break;
		default:
			if(color1.equals("q")){
				System.out.println(" You have canceled your purchase \n Have a good day!");
			} 
			while(!color1.equals("q")){
				System.out.println(" Sorry but " + color1 + " is not a valid key ");
				colorMenu("Mango(s)");
				color1 = menuSelection.nextLine();  
				wrongColorSelection("Mango", color1,pricePerPound1,numOfFruits1);  
					if(color1.equals("1") || color1.equals("2"))
						color1 = "q";
			}
			break;
			  
		}
	}
  
  
	
	/**
	* Testing for the weight  of any class.
	* @param    String, double, double, int.
	 * @throws InterruptedException 
	*/
	public double testingWeights() throws InterruptedException{
		double weight1= 0;
		System.out.println("Enter the weight of fruits to be purchased");
		weight1 = menuSelection.nextDouble();
  	  	
	  	try{  //weight1 = Double.parseDouble(weightInput1);
	  		if (weight1 < 0){
	  			throw new FruitException2("The weight of the fruits cannot be negative");
	  		}
	  	} 
	  	catch(FruitException2 exc) {
	  		exc.printStackTrace();
	  		Thread.sleep(100);
	  		weight1 = testingWeights();
	  	}
	  	
	  	
  	  	
  	  	return weight1;  
  }
  
	
	
  /**
  * Testing for the numbers of Apple(s) or Mangos(s) given by the user.
  * @param    int
 * @throws InterruptedException 
  */
  public int gettingNumberOfFruit(String fruit) throws InterruptedException{
	  int numOfFruits1 = 0;
	  System.out.println("Enter the number of fruits to be purchased");
      numOfFruits1 = menuSelection.nextInt();
	  
      if(fruit.equals("Apple(s)")){
    	  //An object of FruitException class is thrown
    	  try{
    		  if (numOfFruits1  > 5 || numOfFruits1 < 1){
    			  throw new FruitException(" The number of apple(s) cannot be greater than 5 ");
    		  }
    	  }
    	  catch(FruitException exc){
    		  exc.printStackTrace();
    		  Thread.sleep(100);
    		  numOfFruits1 = gettingNumberOfFruit(fruit);
    	  }     
      }
      else{
    	  //An object of FruitException class is thrown
    	  try{
    		  if (numOfFruits1  > 15 || numOfFruits1 < 1){
    			  throw new FruitException(" The number of mango(es) cannot be greater than 15 ");
    		  }
    	  }
    	  catch(FruitException exc){
    		  exc.printStackTrace();
    		  Thread.sleep(100);
    		  numOfFruits1 = gettingNumberOfFruit(fruit);
    	  } 
    	  
      }  
	  return numOfFruits1;
  }
 
  
  
  /**
   * Testing for the price per unit of Apple(s) or Mangos(s) given by the user.
   * @param    double
 * @throws FruitException 
 * @throws InterruptedException 
   */
  public double gettingPricePerUnitt(String fruitColor) throws FruitException, InterruptedException{
	 double  pricePerPound1 = 0;
	  
	  if(fruitColor == "mango"){
		  System.out.println("Enter the price of fruits per unit");
	  }
	  else{
		  System.out.println("Enter the price of fruits per pound");
	  }

	  pricePerPound1 = menuSelection.nextDouble(); 
      try {
    	  
		 if ( pricePerPound1  < 0){
			  throw new FruitException(" The Price Cannot be negative ");
		  }
		
      } catch(FruitException exc){
    	  exc.printStackTrace();
    	  Thread.sleep(100);
		  pricePerPound1 = gettingPricePerUnitt(fruitColor);
	  }
     
	  
	  return pricePerPound1;
  }
    
  

  @Override
  public double totalPrice() {
	// TODO Auto-generated method stub
	return 0;
  }

  @Override
  public String report() {
	// TODO Auto-generated method stub
	return null;
  }
}
