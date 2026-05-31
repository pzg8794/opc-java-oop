/*
 * PrimeSieve.java
 *
 * Version:
 *     $Id: Snowflake.java,v 1.6 2013/03/13 07:03:41 pzg8794 Exp $
 *
 * Revisions:
 *      $Log: Snowflake.java,v $
 *      Revision 1.6  2013/03/13 07:03:41  pzg8794
 *      updating
 *
 *      Revision 1.5  2013/03/12 05:27:34  pzg8794
 *      Snowflake.java
 *
 */

import java.util.*;

/** 
 * The Snowflake program draws pictures using a Turtle program package developed
 * at Princeton, the class Snowflake uses two main methods to build a snowflake
 * according the properties given by the user; these methods are 
 * snowflake( S, N, T) and drawSnowflake(S, N, T), where S, N and T are given by
 * the user and are the length, depth and angle of the snowflake to draw.
 *
 *
 * @author    Piter Garcia
 */
public class Snowflake {

     /*
      * lenght of the snowflake
      */
     private int S;
     
     
     /*
      * depth of the snowflake
      */
     private int N;
      

     /**
      * This is method is the class constructor, and it is used to initilize 
      * the parameters of the class Snowflake().
      */
     public Snowflake() {

    	    S = 0;
	    N = 0;
     }




    /**
     * This method draws a snowflake part, this same method is used by another
     * method to completely draw a snowflake recursively.
     * 
     * @param S, length of the snowflake
     * @param N, depth of the snowflake
     * @param T, angle of the snowflake
     */
    public static void snowflake( int S, int N, Turtle t) {
         
          if( N > 0) {
    	  
	       t.goForward(S);
      
    	       if( N > 1) {
    		  
		    t.turnLeft(120);

    	  	    for ( int i = 0; i < 5 ; i++) {
    	               
    	  		 snowflake(S/3, N-1, t);
    	  		 t.turnRight(60);
    	  	    }
	  	    t.turnRight(180);
    	       }

    	       t.goForward(-S);
          }
    }




    /**
     * This method draws the entire snowflake, the way this is done is by 
     * drawing the sknowflake in segments and these segments are parts built
     * by the method named snowflake(S, N, T).
     *
     * @param S, length of the snowflake
     * @param N, depth of the snowflake
     * @param T, angle of the snowflake
     */
    private static void drawSnowflake(int S, int N, Turtle t) {
		
		for ( int i = 0; i < 6 ; i++) {
			
			snowflake(S, N, t);
			t.turnLeft(60);
		}
    }




    /**
     * This method is used to initialize the S parameter in the Turtle class. 
     * 
     * @param S is the parameter used to initialized the length of the snoflake
     * through the Turtle class.
     * @return t an instance of the class Turtle.
     */
    public static Turtle init( int S) {

    	Turtle t = new Turtle(0.0, 0.0, 60);
    	t.setWorldCoordinates(-(S + S/2), -(S + S/2), (S + S/2), (S + S/2));

        return t;
    }



     
    /**
     * The main method calls the function snowfrake(S, N, T) and 
     * drawsnowflake(S, N, T) to draw a snowkflakin according the properties
     * given by the user, this is done after the setting the worldcoordinates.
     *
     * @param args command line arguments (ignored).
     */
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
    	Snowflake snow = new Snowflake();

    	System.out.print("\n Enter S: ");
        snow.S = sc.nextInt();
    	
        System.out.print("\n Enter N: ");
        snow.N = sc.nextInt();
        
    	Turtle t = null;
        t = init(snow.S);

	snow.snowflake( snow.S, snow.N, t);
    	snow.drawSnowflake( snow.S, snow.N, t);
    }
    
}
