/*
 *  Created:      09/20/2012
 *  Last Changed: 09/22/2012
 *  Author:       Piter Garcia
 *  
 *  FireFighter.java 
 * 
 *  Version: 
 *     296009929 
 * 
 *  Revisions: 
 *     HomeWork #3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *           This class inheritances all the features of the class Person.java. Also, it deter-
 * mines whether a person is old enough to be a firefighter. If so, it ask the user for his/hernull
 * hight and weight. 
 * 
 * @ return This class will return the user's personal information along with firefighter qualifications.
 * @ exception any invalid given data will result in a loop until the user inputs a valid data.
 * @ see String personsFirstName, personsLastName, socialSecurityNumber.
 * @ see int day, month, year.
 * @ see double height, waigtht
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
public class FireFighter extends Person {
	
	protected String height= null;
	protected String weight= null;
	
	/**
	* @Function: setFireFightersWeight(height)
	*
	* @Description:	it stores the height of the user that is old enough to be a firefighter.
	*
	* @param String.
	* 
	* @return String.
	* 
	*/
	String setFireFightersWeight(String weight1) throws IOException{
		
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		weight1 = bufferreader.readLine(); //getting user's weight from command line

		return weight1;
	}
	
	/**
	* @Function: setFireFightersHight(height)
	*
	* @Description:	it stores the height of the user that is old enough to be a firefighter.
	*
	* @param String.
	* 
	* @return String.
	* 
	*/
	String setFireFightersHight(String height1) throws IOException{
		String height2=null;
		
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		System.out.print(" Enter feet");
		height1 = bufferreader.readLine();
		
		System.out.print(" Enter inches");
		height2 = bufferreader.readLine();
		
		return (height1 + "'" + height2); //Getting user's height from command line.
	}
	

	/**
	* @Function: getFireFightersHight()
	*
	* @Description:	it gets the height of the person that qualifies as a firefighter.
	*
	* @param String.
	* 
	* @return String.
	* 
	*/
	String getFireFightersHight()
	{
		return height;	//getting user's given height.
	}
	
	/**
	* @Function: getFireFightersWeight()
	*
	* @Description:	it store the first and last name of the user in two variables.
	*
	* @param String.
	* 
	* @return String.
	*/
	String getFireFightersWeight()
	{
		return weight;	//getting uer's give weight.
	}
	
	/**
	* @Function: isAbleToBeaFireFighter(age)
	*
	* @Description:	it takes the age of the user and determines whether is old enough 
	* to be a firefighter or not .
	*
	* @param void
	* 
	*/
	boolean isAbleToBeaFireFighter(int age) throws IOException
	{
		int answer = -1;
		
		if(age < 20)answer = 0;
		if(age > 50)answer = 1;
		if(age >= 20 && age <= 50)answer = 2;
		
	    switch(answer){
	    case 0: //Too young to be a firefighter.
			System.out.println("Sorry, but You do not Qulified to be a Firefighter.");
			System.out.println("Maybe next time!");
			System.out.println(" ");
	    	break;
	    	
	    case 1: //Too old to be a firefighter
			System.out.println("Sorry, but You do not Qulified to be a Firefighter.");
			System.out.println("Well, You Relly do not have a next time so sorry twice!");
			System.out.println(" ");
			break;
			
	    case 2:	//Good age to be a firefighter
			System.out.println(" Congratulations! You qualified to be a Firefighter");
			System.out.println(" ");
			break;
	    
	    default: //input not recognized or invalid.
	    	System.out.println(" Sorry! But Something Went Wrong!");
	    	System.out.println(" ");
		}	
	    
	    if(answer == 2)
	    	return true;
	    else
	    	return false;
	}
	
	
	/**
	* @Function: The main program.
	*
	* @Description: Call all the functions of the class Person.java, determines if user
	* Qualifies to be a firefighter and privies his/her height and weight.
	* 
	* @param    args    command line arguments (ignored)
	*/
	public static void main(String[] args) throws IOException{	
		int age=0;
		String hight1=null, weight1=null;
		FireFighter person =  new FireFighter();
		
		//Using set functions to get the user's information to analyze
		//whether or not the user qualifies to be a firefighter.
		person.setPersonsName("Sindhu", "Srinivasn");
		person.setDayOfBirth(6, 24, 1900);
		person.setSocialSecurityNumber("147-32-5867");
		
		//Obtaining user's age.
		age = person.getPersonsAge();
		//Analyzing if user qualifies to be a firefighter.
		if(person.isAbleToBeaFireFighter(age) == true){
		
		//Getting user's height from command line.
		System.out.println(" Please enter your Hight: ");
		person.height = person.setFireFightersHight(hight1);	
		
		//Getting user's weight from command line.
		System.out.println(" Please enter your Weight: ");
		person.weight = person.setFireFightersWeight(weight1);	
		}
		
		//Displaying usrer's given and processed information.
		person.displayPersonsInfo();
				
		//Displaying other user's information added to the class.
		person.displayOtherInfo(person, age);
		//person.equals(person);	
	}
}