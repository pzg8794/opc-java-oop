    /*
     * WordCount.java
     *
     * Version:
     *     $Id$
     *
     * Revisions:
     *     $Log$
     */

// Import statements are placed here
import java.util.Scanner;

    /*
     * This program counts the number of words in a file, 
     * seperated by white space(s), commas or new line(s) 
     * character.
     * 
     * If there are no words in the file, the output shall 
     * be 0.
     * 
     * @author      Piter Garcia
     * @author      Sindhu Srinivasan
     *
     */
 
import java.io.File;
import java.io.IOException;
   
   public class WordCount{
    
      public static void main(String[] args) {
    	  int counter =0;   //Counter to count the number of words in the file
         
    	  File fileName = new File("test1");
    	  Scanner input;
         
    	  try {
    		  input = new Scanner(fileName);
    
    		  // For each character read from the file, the counter is incremented by 1.
    		  // This is repeated until there are words left to be read from the file.
    		  while (input.hasNext()) {
    
    			  counter++;  
    			  input.next();
           
    	          }
    		  System.out.println("There are " + counter + " words in the file.");  
              } 
    	  //This catch block handles exceptions such as the wrong file name etc.
    	  catch (IOException e) {           
    	  		e.printStackTrace();
    	  		System.out.println("Wrong File Name!");
    	  }
      }   
 }
