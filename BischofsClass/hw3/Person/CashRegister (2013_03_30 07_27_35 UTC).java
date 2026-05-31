/*
 * CashRegister.java
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
 * This program produces a Cash Register, that adds items
 * to a list, calculates the total price of all items, and
 * also clears the item list.
 * 
 * @author      Piter Garcia
 * @author      Sindhu Srinivasan
 */

public class CashRegister{

	static double stackInp[]=new double[20];   
						   //Creates a new Stack for storing the prices of items entered.
	static int top;                           	      //Top denotes the top of the stack.

	public static void main(String[] args) { 
		int itemsCount=0;                      //inpPrice denotes the price of each item.
		gettingPrices(itemsCount);			  //Getting prices from user in command line.
		
		itemsCount= getCount();              //Gets the total count of items in register.
  		     // If the register is not empty, the total price of all items is calculated.
	
		displayingPrices(itemsCount);
		clear(); 								 //Clears the Stack for next transaction.
    }
	
	static void gettingPrices(int prices){
		
		String prices1="p";
		while(!prices1.equals("q")){      //If price entered is not -1, do the following.
			BufferedReader br =     new BufferedReader(new InputStreamReader(System.in));     													
			try {												      //Reads user input.
				System.out.println("Please Enter the Item Price, Press q to check out");
				prices1 = br.readLine();
				
				if(!prices1.equals("q"))
				prices= Integer.parseInt(prices1); //Reads each item price into inpPrice.
     
				//If the price is less than 0, the program will ask the user for a valid
				//item price or to press q to check out.
				while((prices <= 0) && !prices1.equals("q")){
					
					System.out.println(" " + prices + " is not a valid item price. ");
					System.out.println("Please enter a valid price or q to check out");
					prices1 = br.readLine();
					
					if(!prices1.equals("q"))
					prices= Integer.parseInt(prices1); //Reads each item price into inpPrice.
				}
				
				if(!prices1.equals("q") && prices > 0 )// each price will be put in the stack 
					addItem(prices); // only if it is a valid price, and the user is not ready 
									//to check out. 
			} 
			catch (IOException e1) { 				   //TODO Auto-generated catch block.       
				e1.printStackTrace();
		    }
		}
	}
	
	static void displayingPrices(int itemsAmount){
		double totalPrice= 0;          //totalPrice denotes the total price of all items.
		
		while(top!=0){
			totalPrice= totalPrice + pop();
		}									   //Displays the count of items in register.  
		System.out.println("You have a total of " + itemsAmount + " items");            
		System.out.println("and the total spent is " + totalPrice);                  
										   //Displays the total price of items purchased.
	}
	
	static void clear(){                           //clears the item count and the total.
		top= -1;          
	}
	static void addItem(double price){              //Adds an item to this cash register. 
		push(price);          
	}
	static int getCount(){                  //Returns the item count of the current sale.
  		return (top);     
	}
 
	static void push(double price){						  //Adding price(s) to the stack.
  		top++;
		stackInp[top]=price;  
	}
	static double pop(){						 	 //Getting price(s) out of the stack.
  		double price= 0;
  
		price= price + stackInp[top];
		top--;
		return price;
	}
 
}