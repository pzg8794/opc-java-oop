/*
 *  Created:      09/20/2012
 *  Last Changed: 09/22/2012
 *  Author:       Piter Garcia
 *  
 *  Person.java 
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
import java.util.Calendar;

/**
 *           This program first stores the full name, birthday, and social security of users.                           
 * Then, calculates the user's age while determining whether the user is able to vote or not.
 * In the same way, it also determines if the user is a senior citizen or not. All these 
 * functions are done taking into account users' possible mistakes while inputing their info.
 * 
 * @ return This class will return the user's personal information.
 * @ exception any invalid given data will result in a loop until the user inputs a valid data.
 * @ see String personsFirstName, personsLastName, socialSecurityNumber.
 * @ see int day, month, year.
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
public class Person {
	//User's full name.
	private	String personsFirstName= new String();
	private	String personsLastName= new String();
	//User's date of birth.
	private	int dayOfBirth =0;
	private	int monthOfBirth=0;
	private	int yearOfBirth=0;
	//User's social security number.
	private	String socialSecurityNumber= new String();
	//Availability to vote.
	protected	boolean ableToVote= false;
	//Status according to user's age.
	protected	boolean seniorCitizen= false;
	
	//Constructor to directly assigned the full name, date of birth, and social security of the user.
	public Person( String firstname, String lastname, int inputMonthOfBirth, int inputDayOfBirth, 
			       int inputYearOfBirth, String inputSocialSecurityNumber) throws IOException{
		
		//Initializing buffer to read from the keyboard 
		//Variable used to temporarily store the date of birth and social security of the user.
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		String temporaryInput;  
		
		personsFirstName= firstname;
		personsLastName= lastname;
		
		//Validating Input Year of Birth.
		inputYearOfBirth = validatingPersonsYearOfBirth(inputYearOfBirth);
				
		//Loop to test if the user has entered a wrong month.
		while(!((inputMonthOfBirth>0) && (inputMonthOfBirth<13))){
			
			//The loop will stop once the user inputs a valid month.
			System.out.println(" Please input the right Month Of Birth: ");
			temporaryInput = bufferreader.readLine();
			inputMonthOfBirth = Integer.parseInt(temporaryInput);
			
			//Loop to test if the user has entered a valid day for months whose last day is 30.
			while(((inputMonthOfBirth == 4 || inputMonthOfBirth == 6 || inputMonthOfBirth == 9 || 
							inputMonthOfBirth == 11) && inputDayOfBirth == 31)){
				
					//Loop will stop once a valid day is input by the user.
					System.out.println(" Please input the right day Of Birth: ");
					temporaryInput = bufferreader.readLine();
					inputDayOfBirth = Integer.parseInt(temporaryInput);	
			}
		}	
		
		//Loop to test if the user has entered a wrong day.
		inputDayOfBirth = validatingPersonsDayfBirth(inputDayOfBirth);
		
		
		//Test to make sure the user enters a valid day for February during a leap year in.
		if((inputMonthOfBirth == 2)){
			
			//Testing if the input year is a leap year.
			if((inputYearOfBirth % 400 == 0) || ((inputYearOfBirth % 4 == 0) && 
					                           (inputYearOfBirth % 100 != 0))){
		
				//Loop to test if the user has entered a valid day for the month 
													
				//of February in a leap year.
				while(!(inputDayOfBirth <30)){
							
					System.out.println(inputYearOfBirth +" is a Leap Year, " +
										"please input the right Day Of Birth ");
					System.out.println(inputDayOfBirth + " is not a valid day." + 
					" Input the right Day Of Birth Now: ");
					temporaryInput = bufferreader.readLine();
					inputDayOfBirth = Integer.parseInt(temporaryInput);	
				}
			}
			else{
				//Loop to test if the user has entered a valid day for the month of 
													
				//February in a not leap year.
				while(!(inputDayOfBirth <29)){
							
					System.out.println(inputYearOfBirth +" is a not Leap Year, " +
											"please input the right Day Of Birth ");
					System.out.println(inputDayOfBirth + " is not a valid day." 
											+ " Input the right Day Of Birth Now: ");
					temporaryInput = bufferreader.readLine();
					inputDayOfBirth = Integer.parseInt(temporaryInput);	
				}
				
			}
		}
		monthOfBirth= inputMonthOfBirth;  //Assigning a valid user's input to the user's month of birth.
		dayOfBirth= inputDayOfBirth;	    //Assigning a valid user's input to the user's day of birth.
		yearOfBirth= inputYearOfBirth;     //Assigning a valid user's input to the user's year of birth.
		socialSecurityNumber= inputSocialSecurityNumber;	
		//Assigning a valid user's input to the user's social security number.
	}  
	
	/**
	* Name: public Person()
	* Description: It initializes full name, birth date, and social security number of the user.
	* @param String, and integers.
	*/
	public Person(){
		personsFirstName= null;
		personsLastName= null;
		dayOfBirth= 0;
		monthOfBirth= 0;
		yearOfBirth= 0;
		socialSecurityNumber= null;
	}
	
	 /**
	 * @Function: clear()
	 *
	 * @Description:  Sets all user parameters to zero.
	 *
	 * @param String, and integers.
	 */
	void clear(){
		personsFirstName= null;
		personsLastName= null;
		dayOfBirth= 0;
		monthOfBirth= 0;
		yearOfBirth= 0;
		socialSecurityNumber= null;
	}	
	
	/**
	* @Function: setPersonsName(firstname, lastname)
	*
	* @Description:	it store the first and last name of the user in two variables.
	*
	* @param String inputFirstName, String inputLastName.
	* 
	*/
	void setPersonsName(String inputFirstName, String inputLastName){
		personsFirstName= inputFirstName;
		personsLastName= inputLastName;
	}
	
	/**
	* @Function: setDayOfBirth(day, month, year)
	*
	* @Discription: this function inputs the month, day, and year of birth of the user.
	* 
	* @param    integers.
	 * @throws IOException 
	*
	*/	
	void setDayOfBirth(int inputMonthOfBirth, int inputDayOfBirth, int inputYearOfBirth) throws IOException{
		
		//Initializing buffer to read from the keyboard 
		//Variable used to temporarily store the date of birth and social security of the user.
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		String temporaryInput; 
		
		//Validating Input Year of Birth.
		inputYearOfBirth = validatingPersonsYearOfBirth(inputYearOfBirth);
				
		//Loop to test if the user has entered a wrong month.
		while(!((inputMonthOfBirth>0) && (inputMonthOfBirth<13))){
			
			//The loop will stop once the user inputs a valid month.
			System.out.println(inputMonthOfBirth + " is invalid, Please input the right Month Of Birth: ");
			temporaryInput = bufferreader.readLine();
			inputMonthOfBirth = Integer.parseInt(temporaryInput);
			
			//Loop to test if the user has entered a valid day for months whose last day is 30.
			while(((inputMonthOfBirth == 4 || inputMonthOfBirth == 6 || inputMonthOfBirth == 9 ||
					inputMonthOfBirth == 11) && inputDayOfBirth == 31)){
				
					//Loop will stop once a valid day is input by the user.
					System.out.println(" Please input the right day Of Birth: ");
					temporaryInput = bufferreader.readLine();
					inputDayOfBirth = Integer.parseInt(temporaryInput);	
			}
		}	
		
		//Loop to test if the user has entered a wrong day.
		inputDayOfBirth = validatingPersonsDayfBirth(inputDayOfBirth);
		
		
		//Test to make sure the user enters a valid day for February during a leap year in.
		if((inputMonthOfBirth == 2)){
			
			//Testing if the input year is a leap year.
			if((inputYearOfBirth % 400 == 0) || ((inputYearOfBirth % 4 == 0) && 
												 (inputYearOfBirth % 100 != 0))){
		
				//Loop to test if the user has entered a valid day for the month of February 
																			
				//in a leap year.
				while(!(inputDayOfBirth <30)){
							
					System.out.println(inputYearOfBirth + " is a Leap Year, please input the right Day Of Birth ");
					System.out.println(inputDayOfBirth + " is not a valid day." 
					+ " Input the right Day Of Birth Now: ");
					temporaryInput = bufferreader.readLine();
					inputDayOfBirth = Integer.parseInt(temporaryInput);	
				}
			}
			else{
				//Loop to test if the user has entered a valid day for the month of February in 
																			  
				//a not leap year.
				while(!(inputDayOfBirth <29)){
							
					System.out.println(inputYearOfBirth + " is not a Leap Year, " +
							               "please input the right Day Of Birth ");
					System.out.println(inputDayOfBirth + " is not a valid day." 
										   + " Input the right Day Of Birth Now; ");
					temporaryInput = bufferreader.readLine();
					inputDayOfBirth = Integer.parseInt(temporaryInput);	
				}
				
			}
		}
		monthOfBirth= inputMonthOfBirth;  //Assigning a valid user's input to the user's month of birth.
		dayOfBirth= inputDayOfBirth;	    //Assigning a valid user's input to the user's day of birth.
		yearOfBirth= inputYearOfBirth;     //Assigning a valid user's input to the user's year of birth.
	}
	
	/**
	* @Function: setSocialSecurityNumber(social security number)
	*
	* @Description: this function allows the user to input the social security.
	*
	* @param    String
	*
	*/
	void setSocialSecurityNumber(String inputSocialSecurityNumber){
		socialSecurityNumber= inputSocialSecurityNumber;
	}

	
	/**
	* @Function: getPersonsName()
	* 
	* @Discription: this function returns the user's full name.
	*
	* @param    String, String.
	*
	* @return   String.
	*/
	String getPersonsNames(){	
		return (personsFirstName + " " + personsLastName);
	}	
	
	/**
	* @Function: getPersonsDateOfBirthday()
	* 
	* @Discription: this function returns the user's birth date.
	*
	* @param    integer, integer, integer.
	*
	* @return   String.
	*/
	String getPersonsDateOfBirthday(){
		return (dayOfBirth + "/" + monthOfBirth + "/" + yearOfBirth); 
	}
	
	/**
	* @Function: getPersonsSocialSecurityNumber()
	* 
	* @Discription: this function returns the user's social security number.
	*
	* @param    String.
	*
	* @return   String
	*/
	String getPersonsSocialSecurityNumber(){
		return socialSecurityNumber;
	}

	/**
	* @Function: getPersonsAge()
	* 
	* @Discription: this function returns the user's age.
	*
	* @param    Switch(integer)
	*
	* @return   integer.
	*/
	int getPersonsAge(){
		int age = 0;
		
		age = validatingPersonsAge(age);
		
		return age;						
	}
	
	/**
	* @Function: validatingPersonsAge(age of the user)
	* 
	* @Discription: this function returns the user's age.
	*
	* @param    Switch(integer)
	*
	* @return   integer.
	*/
	int validatingPersonsAge(int age){
		
		Calendar calendar = Calendar.getInstance();			  //Accessing actual date through the calendar.
		int answer = 2;						      // Answer is a variable that can be explain as following:
		
		if(yearOfBirth <= calendar.get(Calendar.YEAR))answer = 0; // for answer = 0 the year will be <=.
		if(yearOfBirth > calendar.get(Calendar.YEAR))answer = 1;  // And for answer = 1 the year will be >.
		// For any other condition answer will always be 2 which will sent the switch to a default mode.

		// Switch to compare the given date with the actual date and figure out the user's age the given data.
		switch(answer){
		case (0) : //Year of birth is less or equal to actual year.
			
			//1st Condition: the month of birth is equal to the current month.
			if(monthOfBirth == (calendar.get(Calendar.MONTH)+1)){
				
				//day of birth > the actual day while the year or birth = the current year.
				if(dayOfBirth > calendar.get(Calendar.DAY_OF_MONTH) && yearOfBirth == calendar.get(Calendar.YEAR)){
					System.out.println(" You have not even been Born ");
					age= -1;
				}
				//day of birth > the actual day while the year < the current year.
				if(dayOfBirth > calendar.get(Calendar.DAY_OF_MONTH) && yearOfBirth < calendar.get(Calendar.YEAR)){
					age= (calendar.get(Calendar.YEAR) - yearOfBirth) - 1;
				}
				//day of birth = the actual day while the year = the current year.
				if(dayOfBirth == calendar.get(Calendar.DAY_OF_MONTH) && yearOfBirth == calendar.get(Calendar.YEAR)){
					System.out.println(" You have just been Born ");
				}
				//day of birth = the actual day while the year < the current year.
				if(dayOfBirth == calendar.get(Calendar.DAY_OF_MONTH) && yearOfBirth < calendar.get(Calendar.YEAR)){
					System.out.println(" Happy Birthday! ");
					age= (calendar.get(Calendar.YEAR) - yearOfBirth);
				}
				//day of birth < the actual day while the year < the current year.
				if(dayOfBirth < calendar.get(Calendar.DAY_OF_MONTH) && yearOfBirth < calendar.get(Calendar.YEAR)){
					age= (calendar.get(Calendar.YEAR) - yearOfBirth);
				}
				//day of birth < the actual day while the year = the current year.
				if(dayOfBirth < calendar.get(Calendar.DAY_OF_MONTH) && yearOfBirth == calendar.get(Calendar.YEAR)){
					age= (calendar.get(Calendar.YEAR) - yearOfBirth);
					System.out.println(" You are just " + (calendar.get(Calendar.DAY_OF_MONTH) - dayOfBirth) +
					" day(s) Old!!!");	
				}
				System.out.println(" ");
			}
			//2nd-1 Condition: Month of birth < current month while year of birth < current year.
			//Note: In this case, the age will always be the actual year - the year of birth. Since the month
			// of birth is less than the actual month the days will not affect the age result in this case.
			else if(monthOfBirth < (calendar.get(Calendar.MONTH)+1) && yearOfBirth < calendar.get(Calendar.YEAR)){
				age= (calendar.get(Calendar.YEAR) - yearOfBirth);
			}
			//2nd-2 Condition: Month of birth < the actual month while the year = the current year.
			else if(monthOfBirth < (calendar.get(Calendar.MONTH)+1) && yearOfBirth == calendar.get(Calendar.YEAR)){
			
				//day of birth < the actual day.
				if(dayOfBirth < calendar.get(Calendar.DAY_OF_MONTH)){
					age= (calendar.get(Calendar.YEAR) - yearOfBirth);
				System.out.println(" You are just " + ((calendar.get(Calendar.MONTH)+1) - monthOfBirth) +
				" month(s) Old!!!");
				}
				//day of birth = the actual day.
				if(dayOfBirth == calendar.get(Calendar.DAY_OF_MONTH)){
					age= (calendar.get(Calendar.YEAR) - yearOfBirth);
				
				System.out.println(" You are just " + ((calendar.get(Calendar.MONTH)+1) - monthOfBirth) +
				" month(s) Old!!!");
				}
				//day of birth > the actual day.
				if(dayOfBirth > calendar.get(Calendar.DAY_OF_MONTH) ){
					age= (calendar.get(Calendar.YEAR) - yearOfBirth);
				System.out.println(" You are just " + ((calendar.get(Calendar.MONTH)+1) - monthOfBirth) +
			    " month(s) and " + (dayOfBirth - calendar.get(Calendar.DAY_OF_MONTH)) + " day(s) old!!!");
				}
			}
			//3rd Condition: Month of birth > than actual month while year of birth = actual year.
			
			if(monthOfBirth > (calendar.get(Calendar.MONTH)+1) && yearOfBirth == calendar.get(Calendar.YEAR)){
				System.out.println(" You have not even been Born ");
				age= -1; 
				// age = -1 means that this person has not been born and does not exist. In this case, 
		  	         //However, the user will be forced to input a valid year the validation function.
			}
			//4th Condition: Month of birth > the actual month while the year of birth < the actual year.
			else if(monthOfBirth > (calendar.get(Calendar.MONTH)+1) && yearOfBirth < calendar.get(Calendar.YEAR)){
				age= (calendar.get(Calendar.YEAR) - yearOfBirth)-1;
				//In this case, one year is subtracted from the difference between both years since the person 
				//has not had his/her birthday yet.
			}
			break;
				
		case (1): //Year of Birth is greater than the actual year. In this case, there is no way the person exist.
				  //However, the user will be forced to input a valid year the validation function.
				System.out.println(" You have not even been Born ");
				System.out.println(" ");
				age= -1;
			break;
				
		default: //Any other strange input will send the switch to a default mode.
				System.out.println("Wrong Key");
		}
		
		//If user's age is above or equals 18, the variable ableToVote is set to true to be accessed from main.
		if(age >= 18){
			ableToVote= true;
		}
		//If the user's age is above or = 65, the variable seniorCitizen is set to true and accessed from main.
		if(age >= 65){
			seniorCitizen= true;
		}
		
		return age;
	}
	
	/**
	* @Function: validatingPersonsYearOfBirth(inputYearOfBirth)
	* 
	* @Discription: this function confirms whether the user's year of birth is valid or not.
	*
	* @param    while(integer)
	*
	* @return   integer.
	*/
	int validatingPersonsYearOfBirth(int inputYearOfBirth) throws IOException
	{
		Calendar calendar = Calendar.getInstance();	
		//Initializing buffer to read from the keyboard 
		
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		String temporaryInput;  
		//Variable used to temporarily store the date of birth and social security of the user.
		
		//Loop to test whether the user has entered a wrong year or not.
		while(inputYearOfBirth>2012 || inputYearOfBirth<1900){
			
			//The loop will stop once the user inputs a valid year
			System.out.println(inputYearOfBirth + " is not a Valid Year, try again!");
			System.out.println(" Please input the right Year Of Birth now: ");
			temporaryInput = bufferreader.readLine();
			inputYearOfBirth = Integer.parseInt(temporaryInput);
			
			//User is between 112-113 years old.
			if(inputYearOfBirth == 1900){
				System.out.println(" Are You For Real? If so, God Bless Your Experience!");
			}
			//User is between 6 years old to a newborn.
			if(inputYearOfBirth > 2005 || inputYearOfBirth < Calendar.YEAR ){
				System.out.println(" Aren't You Too Young To Be Using the Keyboard? I Hope an Adult is with You!");
			}
		}
		
		return inputYearOfBirth;
	}
	
	/**
	* @Function: validatingPersonsDayOfBirth(inputDayOfBirth)
	* 
	* @Discription: this function confirms whether the user's year of birth is valid or not.
	*
	* @param    while(integer)
	*
	* @return   integer.
	*/
	int validatingPersonsDayfBirth(int inputDayOfBirth) throws IOException
	{
		//Initializing buffer to read from the keyboard 
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		String temporaryInput;  
		//Variable used to temporarily store the date of birth and social security of the user.
				
		
		//Loop to test if the user has entered a valid day for months whose last day is 31.
		while(!(inputDayOfBirth>0 && inputDayOfBirth<32)){
			
			//Loop will stop once a valid day is input by the user.
			System.out.println(" Please input the right day Of Birth ");
			temporaryInput = bufferreader.readLine();
			inputDayOfBirth = Integer.parseInt(temporaryInput);	
		}
		return inputDayOfBirth;
	}
	
	/**
	* @Function: displayOtherInfo(Person, int)
	* 
	* @Discription: this function displays other features of the person.
	*
	* @param    while(integer)
	*
	* @return   integer.
	*/
	void displayOtherInfo(Person person, int age){
		
		System.out.println(" ");
		if(age!=-1)
			System.out.println(" The Person's Age is " + age + " Years Old");
		else
			System.out.println(" Sorry, but Your Are Not Supposed To Be Alive!");
	
		//System.out.println(" ");
		if(!ableToVote)
			System.out.println(" This Person is not able to Vote");
		else
			System.out.println(" Congratulations!, You are Old Enough to Vote!");

		//System.out.println(" ");
		if(!seniorCitizen)
			System.out.println(" This Person is not a Senior Citizen");
		else
			System.out.println(" This Person is a Senior Citizen, do not worry I will treat you nice!");
		//person.clear();	
	System.out.println(" ");
	}
	   
	/**
	* @Function: equals(Second Object)
	* 
	* @Discription: this function compares the first object with another object.
	* This comparison is done mainly by comparing objects parameters. If each 
	* parameter of both objects happen to be equal, the result will be true.
	* 
	* @param    String, integer, String.
	*
	* @return   boolean.
	*/
	public boolean equals(Person secondObject){
		
		boolean isEqual = false;
		
		if ((secondObject.getPersonsNames()).equals(this.getPersonsNames()) &&
		   (secondObject.getPersonsSocialSecurityNumber()).equals(this.getPersonsSocialSecurityNumber())){
			isEqual = true;
			System.out.println(" These are both the same Person");
		}
		else{
			System.out.println(" These are not both the same Person");
		}
		return isEqual;
	}

	/**
	* @Function: The main program.
	*
	* @Description: Call functions to save user's full name, birth date and social security number.
	* while obtaining the user's age and comparing the actual object with another object.
	* 
	* @param    args    command line arguments (ignored)
	*/
	public static void main(String[] args) throws IOException{		
		int age=0;
		Person person = new Person("Peter", "Garcia", 2, 29, 2013, "147-21-5040");
		person.displayPersonsInfo();
		age = person.getPersonsAge();
	
		person.displayOtherInfo(person, age);
		
		/**Person person2 = new Person("Peter", "Garcia", 8, 23, 1988, "147-22-5040");
		person2.displayPersonsInfo();
		age = person2.getPersonsAge();		
		person2.displayOtherInfo(person2, age);
		 */
		
		//person.equals(person);
	}												
	
	/**
	* @Function: getPersonsName()
	* 
	* @Discription: this function output on the screen the user's full name, birth date, and social security number.
	*
	* @param    String, integer, String.
	*
	* @return   users full name, birth date, and social security number.
	*/
	void displayPersonsInfo(){	
		System.out.println(" Name of the Person : " + personsFirstName + " " + personsLastName);
		System.out.println(" Date of Birth : " + monthOfBirth + "/" + dayOfBirth + "/" + yearOfBirth);
		System.out.println(" Social Security Number :" + socialSecurityNumber);	
	}
}