/**
* NotHealthy.java
*
*
* @version $Id$
*
*
* Revisions:
* $Log$
*/

// Import statements are placed here

import java.util.Random;
import java.util.Vector;

public class NotHealthy extends Thread {
  
 /**
* This program is used to simulate a cigarette smoking system.
* It has an agent thread that places ingredients on a table.
* It also has three smoker threads, which are short of one ingredient.
* Once the smoker, gets that ingredient, it can smoke. once complete,it notifies
* all other threads. the agent thread also notifies all other threads, once it has 
* placed ingredients on the table.
*
* @author Piter Garcia
* @author Sindhu Srinivasan
*/

 private static String ingridient;
 private static String[] ingridients = {"Tabacco", "Paper", "Matches"};  //Creates an array of ingredients.
 private static int swuap = 0;
 private static Vector aVector = new Vector<Object>();

 // Creates a constructor for the NotHealthy class
 public NotHealthy(Vector aVector2) {
  
  aVector = aVector2;
 
 }

 // Creates a constructor for the NotHealthy class with ingridient and vector
 public NotHealthy(String ingridient2, Vector  aVector2) {
  ingridient = ingridient2;
  aVector = aVector2;
 }

// This method generates three random numbers, and assings to swuap.
 public static void agentTable() throws InterruptedException {
  
  Random generator = new Random();
    swuap = generator.nextInt(3);
 }
 
/**
* The random integer generated in last method is used to get an ingredient
* randomly from the ingredient array. this is then compared with the ingredient desired by 
* the three smokers. if the ingredient is the one desired by smoker, he is allowed to smoke.
* Else he waits, and notifies the other threads to check ingredients.
* 
* @param swap is the ingredient missing for each smoker
*/
 public static void ingridientCheck(int swuap){
  
        synchronized ( aVector ){
            if (  ingridient == ingridients[swuap] ){
             
                    System.out.println("\n"+ ingridient + " is the missing ingridient");
                    System.out.println("\n Making Cigarette ... \n Now Your Are Smoking");
                    System.out.println(" Hey! Just so you know, you are a future cancer patient!");
                    aVector.notifyAll();
                    
                    System.out.println(" ");
            } else {
                    System.out.println(ingridient + " is not the ingridient missing \n Good for you!");
                    
                    try {
                      System.out.println("\n Notifying all the other threads");
                      aVector.notifyAll();
                      System.out.println(" Thread is waiting \n");
                      
                    } catch ( IllegalMonitorStateException  e ){
                            System.out.println(ingridient + ": IllegalMonitorStateException");
                            
                    }
                    
            }
        }
 }
 
 
 public void run () {
  
  ingridientCheck(swuap);
  
 }
 
/**
* The main program.
*
* @param args command line arguments (ignored)
*/
 public static void main (String args []) throws InterruptedException {
  
   Vector aVector = new Vector(); //Creates a new vector object
     
   NotHealthy agent = new NotHealthy (aVector); //Creates a new thread agent
   agent.join();
   agentTable();
  
/** Creates three smoker threads using the for loop.the loop also allows to start and join the three threads
 * The parameter ingredient assingns the an ingredient to the thread, that is considered as missing ingredioent for that 
 * thread.
 * Join allows each thread to complete smoking till its done, without getting interrupted.
 */
   
   for ( int i=0 ; i < 3 ; i++){   
    NotHealthy smoker = new NotHealthy (ingridients[i], aVector); 
    smoker.start();
    smoker.join();
   }
  
   
 }
} 