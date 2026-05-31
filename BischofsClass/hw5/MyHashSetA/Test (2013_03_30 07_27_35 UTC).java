import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  Created:      10/07/2012
 *  Last Changed: 10/07/2012
 *  
 *  HashSet.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */


/**
 * This program calls the class called HashSet
 * It implements the AnotherHashSet interface, and inherits the class HashCode
 * This has methods to add an element to the hash set,remove an element
 * It also has methods to check if hash table contains an object, or if it is empty
 * It has method to calculate the size of the hash set as well as resize it.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
public class Test extends HashSet{
	
	public static void main( String args[] ){
		
		HashSet test = new HashSet(); 
		//Creating objects to test HashSet functions, in this case strings 
		String[ ] hashTable = {}; 
		
		
		//Obtaining objects from user
		try {
			gettingUserInput(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		displayingHashTable(test); //Displaying elements input by the user

		System.out.println("The size is " + test.size() + "\n");
		test.adds(test.get(1));
			           //Testing adding an object in the same key location
		
		
						 //Testing removing an element from the hash-table
		testingRemoveFunction(test, test.get(2));  
		displayingHashTable(test);
							//Displaying table without the element removed
		
		
									//confirming new size of the hashtable
		System.out.println("The size is " + test.size() + "\n");
  
		System.out.println("Clearing HashTable");
		test.clear();							 // Clearing the hashtable
		
		
										  //Testing if hash-table is empty
		displayingHashTable(test);		  
		if(test.isEmpty())	
		System.out.println("Your HashTable is now Empty");
		
		System.out.println("The size is " + test.size() + "\n");
  
  
									 //Testing exceeding maximum size of hash-table
		
		
		System.out.println("The Capacity of the HashTalbe is " + test.capacity);
		System.out.println("Trying to add more than ten objects \n");
		
								  //Applying the set's function when size is exceeded
		try {
			gettingUserInput(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		displayingHashTable(test);
		
		System.out.println("The size is " + test.size() + "\n");
 	}
	
	
	
	@SuppressWarnings("null")
	public static void gettingUserInput(HashSet test) throws IOException{
		System.out.println("Adding Objects in the HashTable");
		System.out.println("The Capacity of the HashTalbe is " + test.capacity);
		System.out.println("Please Enter Objets below or q to quit: \n");
		BufferedReader objects= new BufferedReader(new InputStreamReader(System.in));
		
		String object= null;
	int i=0;
		//object = objects.readLine();
		//test.add(object);
		while( i != -1){
			
			object = objects.readLine();
			
			if(object.equals("q"))
				i = -1;
			else{
				test.add(object);
			}
		}	
			                    //Adding elements to Hash-table
	}
	
	
	
	public static void displayingHashTable(HashSet test){
		
		System.out.println("Displaying Objects in the HashTable");
		for(int  i=0; i<=test.size();i++){ //Get the elements of Hash table 
			   
			if(test.get(i) != null)
				System.out.println(test.get(i));	   		
			
		}
		
	}
	
	
	  // Removes element from HashSet,returns an error message if  not found
	public static void testingRemoveFunction(HashSet test, Object obj){
	
				if(test.remove("test2") == true){
		   
					System.out.println("Object " + obj + " has been removed "
											      + "from the HashTable \n");
				}
				else{
					System.out.println("Object " + obj + " cannnot be removed"
									 + " because is not in the HashTable \n");
				}
	}
}