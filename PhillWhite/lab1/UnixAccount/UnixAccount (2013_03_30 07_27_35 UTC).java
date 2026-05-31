/*
 *  Created:      11/30/2012
 *  Last Changed: 12/04/2012
 *  
 *  Test.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */


import java.util.Scanner;



/**
 * This program creates a class called UnixAccount
 * This is used to implement to ask the user for some personal data to figure out his/her Unix Account.
 *
 * @author      Piter Garcia
 * 
 */ 
public class UnixAccount {

	private String unixAccount;
	private char [] characters;
	private int counter= 0, counter1 = 0, counter2 = 0, length = 0;
	
	
	
	/**
	* Takes the information from the user and translate it into characters, also calculates the length
	* of the info input by the user.
	* @param   String[], char[], int
	* @return char[]
	*/
	public void settingInfo( ){
	
		@SuppressWarnings("resource")
		String unixAccountTemp = null;
		Scanner in = new Scanner(System.in);
		
	 	System.out.println(" Please Enter First Name, Middle Name, Last Name, and Phone Number Below :");
	 	System.out.print(" -------------------------------------------------------------------------\n ");
		
	 	unixAccount = in.nextLine();
	 	unixAccountTemp = unixAccount.toLowerCase(); 
	 	System.out.println(" -------------------------------------------------------------------------\n");
		
		length = unixAccount.length() -1;
		characters = unixAccountTemp.toCharArray();
			
	}
	
	
	
	/**
	* This section finds the second intial of the second name entered by the user,
	* @param   char[]
	* @return char[]
	*/
	public void settingSecondInitial( ){
		
		while( characters[counter] != ' '){
			
			counter++;
		}
		counter1 = counter+1;
		counter++;
		
	}
	
	
	
	/**
	* This section finds the third initial of the third name input by the user.
	* @param   char[]
	* @return char[]
	*/
	public void settingThirdInitial( ){
		
		while( characters[counter] != ' '){
			
			counter++;
		}
		counter2 = counter+1;		
		
	}
	
	
	
	/**
	* This section displays the user unix account name.
	* @param  char[]
	* @return user unix account name
	*/
	public void displayingUnixAccountName(){
		
	 	System.out.println("\n Your Unix Account Name Is The Folowing:");
	 	System.out.println(" ---------------------------------------");

	 	System.out.print(" Unix Account Name = ");
		
		System.out.print( characters[0]);
		System.out.print( characters[counter1]);
		System.out.print( characters[counter2]);
		System.out.print( characters[length - 3]);
		System.out.print( characters[length - 2]);
		System.out.print( characters[length - 1]);
		System.out.print( characters[length - 0] + "\n");
		
		System.out.println(" ---------------------------------------");
		
	}
	
	
	/**
	 *This function calls the above explained function to work out the user's 
	 *Unix Account Name.

     * The main program.
     *
     * @param    args    are ignored,
     */
	 public static void main(String[] args){
	
		 	UnixAccount user =  new UnixAccount();
	
			user.settingInfo();
			user.settingSecondInitial();
			user.settingThirdInitial();
			user.displayingUnixAccountName();
			
	 }

}