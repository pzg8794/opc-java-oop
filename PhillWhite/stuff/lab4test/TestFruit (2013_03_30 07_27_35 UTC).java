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



/**
 * This program creates an abstract class TestFruit.
 * It is used to check erroneous conditions in Apples
 * and Mangoes class.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */
public class TestFruit extends Fruit {
  
	public TestFruit() {
		super(" ");
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	* Test user input 
	* @param    mangoObj   Object of class Mango
	*/
	public static void main(String args[])throws FruitException,IOException {
		TestFruit testFruitObj = new TestFruit();
		testFruitObj.getUserInput();
	}
	
	
	/**
	* Gets user input 
	* @param    mangoObj   Object of class Mango
	*/
	public void getUserInput()throws FruitException,IOException{
		String color1, selection;
		double weight1 = 0, pricePerPound1 = 0;
		int numOfFruits1 = 0;
		BufferedReader menuSelection= new BufferedReader(new InputStreamReader(System.in));
  
		
		
		/**
		* Displaying user's menu
		* @param   String
		*/
		System.out.println(" \t Piter and Sindhu Fruit Market");
		FruitMenu();
		selection = menuSelection.readLine();
	  
		switch( selection){
		case "1":
		  
		colorMenu("Apple(s)");
		color1 = menuSelection.readLine();
		testingApplesParameters(color1, weight1, numOfFruits1, pricePerPound1);
		  
		break;
		  
		case "2":
		  
			colorMenu("Mango(s)");
			color1 = menuSelection.readLine();
			testingMangosParameters(color1, weight1, numOfFruits1, pricePerPound1);
		  
		break;
		 
		case "3":
			System.out.println(" Ok, Let's First do your Apple(s) \n");
			colorMenu("Apple(s)");
			color1 = menuSelection.readLine();
			testingApplesParameters(color1, weight1, numOfFruits1, pricePerPound1);
		  
			if( !color1.equals("q")){
				System.out.println("\n Now, Let's do your Mango(s) \n");
				colorMenu("Mango(s)");
				color1 = menuSelection.readLine();
				testingMangosParameters(color1, weight1, numOfFruits1, pricePerPound1);
			}
			break;
		  
		default:
		  
			if(selection.equals("q")){
				System.out.println(" You have canceled your purchase \n Have a good day!");
			}
		  
			while(!selection.equals("q")){
				System.out.println(" Sorry but " + selection + " is not a valid key ");
				FruitMenu();
				selection = menuSelection.readLine();
				wrongFruitSelection(selection);  
		  
				if(selection.equals("1") || selection.equals("2") || selection.equals("3"))
					selection = "q";
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
		System.out.println(" \n Please, press q if you wish to canceled your purchase)");
	}
	
	
	
	/**
	* Color Fruit Menu Display 
	* @param   String
	*/
	void colorMenu(String fruit){
		System.out.println(" Please, Choose from below the type of apple you whish to Purchase");
		System.out.println(" 1: Read " + fruit);
		System.out.println(" 2: Green " + fruit);
		System.out.println(" \n Please, press q if you wish to canceled your purchase)");
	}
  
	
	
	
	/**
	* Displays user's processed input for the class Apples.
	* @param    String, double, double, int.
	*/
	void appledisplaying(String color, double weight1, double	pricePerPound1, int numOfFruits){	 
		Apples appleObj1 = new Apples(color,weight1,pricePerPound1,numOfFruits);
        try {
        	appleObj1.displayApplesInfo(appleObj1);
        } catch (FruitException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        try {
        	appleObj1.testApple(appleObj1);
        } catch (FruitException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
	}	
  
	
	
	/**
	* Displays user's processed input for the class Mangos.
	* @param    String, double, double, int.
	*/
	void mangodisplaying(String color, double weight1, double	pricePerPound1, int numOfFruits){
	    Mangoes mangoObj1 = new Mangoes(color,numOfFruits,pricePerPound1,weight1);
  	    try {
  	    	mangoObj1.testMango(mangoObj1);
  	    } 
  	    catch (FruitException | IOException e1) {
  	    	// TODO Auto-generated catch block
  	    	e1.printStackTrace();
  	    }
        mangoObj1.displayMangoInfo(mangoObj1); 	
	}
  
	
	
	/**
	* Testing for Wrong Color enter by user for the any class Apples, Mangos, or both.
	* @param    String, double, double, int.
	*/
	void wrongColorSelection(String fruit, String color1, double weight1, double pricePerPound1, int numOfFruits){
		
		
		switch(color1){
	  	  	case "1":
	  	  		weight1=testingWeights();
	  	  	  	numOfFruits = gettingNumberOfFruit(fruit);
	  	  	    pricePerPound1 = gettingPricePerUnitt();
	  	  	    if(fruit.equals("Apple"))
	  	  	    	appledisplaying("Read", weight1, pricePerPound1, numOfFruits);
	  	  	    else
	  	  	    	mangodisplaying("Read", weight1, pricePerPound1, numOfFruits);
	  	
	  	        break;
	  		  
	  	  	case "2":
	  	  		weight1=testingWeights();
	  	  		numOfFruits = gettingNumberOfFruit(fruit);
	  	  		pricePerPound1 = gettingPricePerUnitt();
	  	  	    if(fruit.equals("Apple"))
	  	  	    	appledisplaying("Green", weight1, pricePerPound1, numOfFruits);
	  	  	    else
	  	  	    	mangodisplaying("Green", weight1, pricePerPound1, numOfFruits);
	  	        break;
	  		  
	  	  	case "3":
	  		  	if(fruit.equals("Apple"))
	  		  		System.out.println("\n Ok, Let's First do Read Apple(s) \n");
	  		  	else
	  		  		System.out.println("\n Ok, Let's First do Read Mango(s) \n");	
	  		  	
			  	weight1=testingWeights();
		  	  	numOfFruits = gettingNumberOfFruit(fruit);
		  	    pricePerPound1 = gettingPricePerUnitt();
		  	    
		  	    if(fruit.equals("Apple"))
	  	  	    	appledisplaying("Read", weight1, pricePerPound1, numOfFruits);
	  	  	    else
	  	  	    	mangodisplaying("Read", weight1, pricePerPound1, numOfFruits);	
		  	    
		  	    System.out.println(" ");
		  	    
	  		  	if(fruit.equals("Apple"))
	  		  		System.out.println("\n Now, Let's do Green Apple(s) \n");
	  		  	else
	  		  		System.out.println("\n Now, Let's do Green Mango(s) \n");
	  		  	
			  	weight1=testingWeights();
		  	  	numOfFruits = gettingNumberOfFruit(fruit);
		  	    pricePerPound1 = gettingPricePerUnitt();
		  	    
		  	    if(fruit.equals("Apple"))
	  	  	    	appledisplaying("Read", weight1, pricePerPound1, numOfFruits);
	  	  	    else
	  	  	    	mangodisplaying("Read", weight1, pricePerPound1, numOfFruits);	
	  		  break;
	  		  
	  	  default:
	  		  break;
	  	}
	}
  
    
  
	/**
	* Testing for Wrong Color enter by user for the any class Apples, Mangos, or both.
	* @param    String, double, double, int.
	*/
	void wrongFruitSelection(String selection){
		String color1 = null;
		double weight1 = 0, pricePerPound1 = 0;
		int numOfFruits1 = 0;
		BufferedReader menuSelection= new BufferedReader(new InputStreamReader(System.in));
	  
		switch( selection){
			case "1":
		  
				colorMenu("Apple(s)");
				try {
					color1 = menuSelection.readLine();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				testingApplesParameters(color1, weight1, numOfFruits1, pricePerPound1);
				break;
		  
			case "2":
		  
				colorMenu("Mango(s)");
				try {
					color1 = menuSelection.readLine();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				testingMangosParameters(color1, weight1, numOfFruits1, pricePerPound1);
				break;
		  
			case "3":
		  
				System.out.println("\n Ok, Let's First do your Apple(s) \n");
				colorMenu("Apple(s)");
				try {
					color1 = menuSelection.readLine();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				testingApplesParameters(color1, weight1, numOfFruits1, pricePerPound1);
				System.out.println("\n Now, Let's do your Mango(s) \n");
				colorMenu("Mango(s)");
				try {
					color1 = menuSelection.readLine();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				testingMangosParameters(color1, weight1, numOfFruits1, pricePerPound1);		  
			break;
	  
			default:
				break;
	  } 
	}
  
	
	
	/**
	* Testing for all parameters of the Class Apples.
	* @param    String, double, double, int.
	*/
	public void testingApplesParameters(String color1, double weight1, int	numOfFruits1, double pricePerPound1){
		BufferedReader menuSelection= new BufferedReader(new InputStreamReader(System.in));
	  
		switch(color1){
			case "1":
	  	  		weight1=testingWeights();
	  	  		numOfFruits1 = gettingNumberOfFruit("Apple(s)");
	  	  		pricePerPound1 = gettingPricePerUnitt();
	  	  		appledisplaying("Read", weight1, pricePerPound1, numOfFruits1);	
	  	  		break;
		  
			case "2":
				weight1=testingWeights();
				numOfFruits1 = gettingNumberOfFruit("Apple(s)");
				pricePerPound1 = gettingPricePerUnitt();
				appledisplaying("Green", weight1, pricePerPound1, numOfFruits1);	
				break;
		 
			default:
				if(color1.equals("q")){
					System.out.println(" You have canceled your purchase \n Have a good day!");
				} 
				while(!color1.equals("q")){
					System.out.println(" Sorry but " + color1 + " is not a valid key ");
					colorMenu("Apple(s)");
					try {
						color1 = menuSelection.readLine();
					} 
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					wrongColorSelection("Apple", color1, weight1,pricePerPound1,numOfFruits1);  
					if(color1.equals("1") || color1.equals("2"))
						color1 = "q";
				}
				break;
	  }
	}
  
	
	
	/**
	* Testing for all parameters of the class Mangos.
	* @param    String, double, double, int.
	*/
	public void testingMangosParameters(String color1, double weight1, int	numOfFruits1, double pricePerPound1){
		BufferedReader menuSelection= new BufferedReader(new InputStreamReader(System.in));
	  
		switch(color1){
		case "1":
			weight1=testingWeights();
			numOfFruits1 = gettingNumberOfFruit("Mango(s)");
			pricePerPound1 = gettingPricePerUnitt();
			mangodisplaying("Read", weight1, pricePerPound1, numOfFruits1);				        
			break;	
			
		case "2":
	  		weight1=testingWeights();
	  		numOfFruits1 = gettingNumberOfFruit("Mango(s)");
	  		pricePerPound1 = gettingPricePerUnitt();
	  		mangodisplaying("Green", weight1, pricePerPound1, numOfFruits1);	
		  break;
		default:
			if(color1.equals("q")){
				System.out.println(" You have canceled your purchase \n Have a good day!");
			} 
			while(!color1.equals("q")){
				System.out.println(" Sorry but " + color1 + " is not a valid key ");
				colorMenu("Mango(s)");
				try {
					color1 = menuSelection.readLine();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				wrongColorSelection("Mango", color1, weight1,pricePerPound1,numOfFruits1);  
					if(color1.equals("1") || color1.equals("2"))
						color1 = "q";
			}
			break;
			  
		}
	}
  
  
	
	/**
	* Testing for the weight  of any class.
	* @param    String, double, double, int.
	*/
	public double testingWeights(){
		BufferedReader menuSelection= new BufferedReader(new InputStreamReader(System.in));	
		String weightInput1 = null;
	  	double weight1= 0;
		System.out.println("Enter the weight of fruits to be purchased");
		
		try {
			weightInput1 = menuSelection.readLine();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		weight1 = Double.parseDouble(weightInput1);
  	  	
  	  	try{  //weight1 = Double.parseDouble(weightInput1);
  	  		if (weight1 < 0){
  	  			throw new FruitException("The weight of the fruits cannot be negative");
  	  		}
  	  	} 
  	  	catch(FruitException exc) {
		  System.out.println(exc.toString());
  	  	}	  
  	  	
  	  	return weight1;  
  }
  
	
	
  /**
  * Testing for the numbers of Apple(s) or Mangos(s) given by the user.
  * @param    int
  */
  public int gettingNumberOfFruit(String fruit){
	  BufferedReader menuSelection= new BufferedReader(new InputStreamReader(System.in));
	  String numOfFruitsInp1 = null;
	  int numOfFruits1 = 0;
	  System.out.println("Enter the number of fruits to be purchased");
      try {
		numOfFruitsInp1 = menuSelection.readLine();
      } 
      catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
      numOfFruits1 = Integer.parseInt(numOfFruitsInp1);
	  
      if(fruit.equals("Apple(s)")){
    	  //An object of FruitException class is thrown
    	  try{
    		  if (numOfFruits1  > 5){
    			  throw new FruitException(" The number of apples cannot be greater than 5 ");
    		  }
    	  }
    	  catch(FruitException exc){
    		  System.out.println(exc.toString());
      }     
    }
	  return numOfFruits1;
  }
 
  
  
  /**
   * Testing for the price per unit of Apple(s) or Mangos(s) given by the user.
   * @param    double
   */
  public double gettingPricePerUnitt(){
	  BufferedReader menuSelection= new BufferedReader(new InputStreamReader(System.in));
	  String priceEachInput1 = null;
	  double  pricePerPound1 = 0;
	  System.out.println("Enter the price of fruits per unit");
      try {
		priceEachInput1 = menuSelection.readLine();
      } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
      pricePerPound1 = Double.parseDouble(priceEachInput1);
	  
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