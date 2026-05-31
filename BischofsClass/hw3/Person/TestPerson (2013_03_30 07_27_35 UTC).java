/*
 *  Created:      09/20/2012
 *  Last Changed: 09/22/2012
 *  	
 *  TestPerson.java 
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *           This class inheritances all the features of the class Person.java. and the class FireFighter.java. 
 * This program test each feature of both the Person and FireFighter java programs to test their functionality. 
 * 
 * @ return This class will return user's personal information from the classes Person and FireFighter.
 * @ exception any invalid given data will result in a loop until the user inputs a valid data.
 * @ see String personsFirstName, personsLastName, socialSecurityNumber.
 * @ see int day, month, year.
 * @ see double height, waigtht
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
public class TestPerson extends FireFighter {
		
	/**
	* @Function: FireFighter()
	*
	* @Description:	this function takes the first and last name from the user.
	*
	* @param String.
	* 
	* @return String, String.
	* 
	*/
	String getNameKeyBoardInput() throws IOException{
	
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		String name = " ";

		name = bufferreader.readLine(); //Reading first and last name from command line.
		
		return name;
	}
	
	
	/**
	* @Function: getBirthDateKeyBoardInput()
	*
	* @Description:	this function is used to get month, day, and year of birth from user on 
	* the command line.
	* 
	* @param String.
	* 
	* @return int, int, int.
	* 
	*/
	int getBirthDateKeyBoardInput() throws IOException{
		
		int birthdate= 0;
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		String date = " ";
		
		date = bufferreader.readLine();
		birthdate = Integer.parseInt(date); //Reads month, day and year from command line.
		
	return birthdate;	
	}
	
	/**
	* @Function: testPersonProgram(Person person1)
	*
	* @Description:	This functions test all key features of the class Person.java.
	*
	* @param String, int.
	* 
	* @return FireFighter key features.
	* 
	*/
	void testPersonProgram(Person person1) throws IOException{
		
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		String firstName= " ", lastName= " ";
		int day=0, month=0, year=0;
		String socialSecurityNumber=" ";
		TestPerson person = new TestPerson();

		//Getting User's First Name from the Command Line
		System.out.print("Please Enter Your First Name: ");
		firstName = person.getNameKeyBoardInput();

		//Getting User's Last Name from the Command Line
		System.out.print("Please Enter Your Last Name: ");
		lastName = person.getNameKeyBoardInput();

		//Getting User's Month of Birth from the Command Line
		System.out.println("Please Enter Your Brithday ");
		System.out.print("Month: ");
		month = person.getBirthDateKeyBoardInput(); 
		
		//Getting User's day of birth from the Command Line
		System.out.print("Day: ");
		day= person.getBirthDateKeyBoardInput(); 

		//Getting User's year of birth from the Command Line
		System.out.print("Year: ");
		year= person.getBirthDateKeyBoardInput(); 

		//Getting User's social security number from the Command Line
		System.out.print("Please Enter Your Social Security Number: ");
		socialSecurityNumber= bufferreader.readLine();
		System.out.println(" ");

		//Using constructor to directly assign the above given values. 
		person1 = new Person(firstName, lastName, month, day, year, socialSecurityNumber);
		//Obtaining user's age from given data
		int age = person1.getPersonsAge();
		//Displaying given and processed user information.
		person1.displayPersonsInfo();
		person1.displayOtherInfo(person1, age);
	}
	
	/**
	* @Function: testFireFighterProgram(FireFighter)
	*
	* @Description:	This functions test all key features of the class FireFighter.java.
	*
	* @param String.
	* 
	* @return FireFighter key features.
	* 
	*/
	void testFireFighterProgram(FireFighter person1) throws IOException{
		
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		String firstName= " ", lastName= " ";
		int day=0, month=0, year=0, age=0;
		String height1=" ", weight=" ";
		String socialSecurityNumber=" ";
		TestPerson person= new TestPerson ();

		//Getting User's First Name from the Command Line
		System.out.print("Please Enter Your First Name: ");
		firstName = person.getNameKeyBoardInput();

		//Getting User's Last Name from the Command Line
		System.out.print("Please Enter Your Last Name: ");
		lastName = person.getNameKeyBoardInput();

		//Getting User's Month of Birth from the Command Line
		System.out.println("Please Enter Your Brithday ");
		System.out.print("Month: ");
		month = person.getBirthDateKeyBoardInput(); 
		
		//Getting User's day of birth from the Command Line
		System.out.print("Day: ");
		day= person.getBirthDateKeyBoardInput(); 

		//Getting User's year of birth from the Command Line
		System.out.print("Year: ");
		year= person.getBirthDateKeyBoardInput(); 

		//Getting User's social security number from the Command Line
		System.out.print("Please Enter Your Social Security Number: ");
		socialSecurityNumber= bufferreader.readLine();
		System.out.println(" ");
		
		//Using set function to assign user's first and last name.
		person1.setPersonsName(firstName, lastName);
		//Using set function to assign user'month, day and year of birth.
		person1.setDayOfBirth(month, day, year);
		//Using set function to assign user's social security number.
		person1.setSocialSecurityNumber(socialSecurityNumber);
		
		//Obtaining user's age.
		age = person1.getPersonsAge();
		//Analyzing if user qualifies to be a firefighter.
		if(person1.isAbleToBeaFireFighter(age) == true){
		
		//Getting user's height from command line.
		System.out.println(" Please enter your Hight: ");
		person1.height = person1.setFireFightersHight(height1);	
		
		//Getting user's weight from command line.
		System.out.println(" Please enter your Weight: ");
		person1.weight = person1.setFireFightersWeight(weight);	
		}
		
		//Displaying User's information from both classes Person and FireFighter.java programs.
		person1.displayPersonsInfo();
		person1.displayOtherInfo(person, age);
	}
	
	/**
	* @Function: The main program.
	*
	* @Description: Call all the functions of the class TestPerson.java to test
	* both classess the Person.java and FireFighter.java.
	* 
	* @param    args    command line arguments (ignored)
	*/
	public static void main(String[] args) throws IOException{	
		TestPerson test = new TestPerson();
		
		Person person = new Person();
		test.testPersonProgram(person); //Testing Person.java program.
		
		FireFighter firefighter = new FireFighter();
		test.testFireFighterProgram(firefighter);	//Testing FireFighter.java program.
		
		//person.equals(firefighter);
	}
}
