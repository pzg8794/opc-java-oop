/*
 *  Created:      10/07/2012
 *  Last Changed: 10/07/2012
 *  
 *  Driver2.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */


/**
 * This program creates a class called Driver1.
 * This program tests our own implementation of HashSet.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */

import java.util.*;
import java.util.HashSet;

public class Driver2 {
    final int MAX_TYPE = 2;
    final static int MAX_ELEMENTS = 20;
    //final static int MAX_ELEMENTS = 4;
    Object allObjects[] = new Object[MAX_ELEMENTS];
    HashSet<Object> aHashSet = new HashSet<Object>();
    long milliSeconds = 0;
    int objectKind = 0;
    Object first, middle, last;
   

    public Driver2() {
    }

    //Initializes the time
    public void init()  {
        milliSeconds = System.currentTimeMillis();
    }
    //Calculates the total elapsed time
    public void end()   {
        System.err.println("Time for all:       " + ( System.currentTimeMillis() - milliSeconds) );
    }

    public Object generateString()      {
        return new String( new Date().toString() + "_" +  Math.random() );
    }
        //Generates the object
    public Object generateObject()      {
        return new Object();
    }
    public Object generateMeanObject()  {
        return new Object();
    }

    public Object objectGenerator(boolean X)    {
        return new Object();
    }
    public Object objectGenerator()     {
        if ( objectKind == 0 )
                return generateMeanObject();
        else if ( objectKind == 1 )
                return  generateObject();
        else if ( objectKind == 2 )
                return  generateString();
        else    {
                System.out.println("unkown object type - rip");
                System.exit(1);
        }
        return null;
    }
        // Adds elements to HashSet
    public void addTest()       {
        for ( int index = 0; index < MAX_ELEMENTS ; index ++ )  {
                        aHashSet.add( allObjects[index] = objectGenerator() );
        }
    }
    
        //Checks if object is present in hash set
    public void containsTest()  {
        for ( int index = 0; index < MAX_ELEMENTS ; index ++ )  {
                        if ( aHashSet.contains( objectGenerator() ) )
                                System.out.println("contains: " + index + " failed ");
        }
        for ( int index = 0; index < MAX_ELEMENTS; index ++ )   {
                        if ( !  aHashSet.contains( allObjects[index] ) )
                                System.out.println("contains: " + index + " failed ");
        }
    }
        //Check if hash set empty
    public void isEmptyTest()   {
        for ( int index = 0; index < MAX_ELEMENTS ; index ++ )  {
                        aHashSet.isEmpty();
        }
    }
        //Check size of HashSet
    public void sizeTest()      {
        for ( int index = 0; index < MAX_ELEMENTS ; index ++ )  {
                        aHashSet.size();
                        if ( aHashSet.size() != MAX_ELEMENTS )
                                System.out.println("size: " + index + " failed ");
        }
    }
        //Remove the element from hash set
    public void removeTest()    {
        Object o;
        for ( int index = 0; index < MAX_ELEMENTS ; index ++ )  {
                if ( objectKind != 0 )  {
                        if  ( aHashSet.remove( o = objectGenerator() ) )        {
                                System.out.println("remove: not existing " + index + " failed ");
                                System.out.println("\t" + o  );
                        }
                }
        }
        for ( int index = 0; index < MAX_ELEMENTS; index ++ )   {
                aHashSet.remove( allObjects[index] ) ;
        }
    }
   
   
   public void testOneKind()    {
        long milliSeconds = System.currentTimeMillis();
                for ( int index = 0; index <= MAX_TYPE; index ++ )      {
                        objectKind = index;     
                        addTest();
                }
        System.out.println("\t" + objectKind + ":add:           " + ( System.currentTimeMillis() - milliSeconds) );
        milliSeconds = System.currentTimeMillis();
        System.out.println("\t" + objectKind + ":contains:      " + ( System.currentTimeMillis() - milliSeconds) );
        milliSeconds = System.currentTimeMillis();
                isEmptyTest();
        System.out.println("\t" + objectKind + ":isEmpty:       " + ( System.currentTimeMillis() - milliSeconds) );
        milliSeconds = System.currentTimeMillis();
                sizeTest();
        System.out.println("\t" + objectKind + ":size:          " + ( System.currentTimeMillis() - milliSeconds) );
        milliSeconds = System.currentTimeMillis();
                removeTest();
        System.out.println("\t" + objectKind + ":remove:        " + ( System.currentTimeMillis() - milliSeconds) );
   }
   public void testIt() {
        aHashSet = new HashSet<Object>();
        allObjects = new Object[MAX_ELEMENTS];

        for ( int index = 0; index <= MAX_TYPE; index ++ )      {
                objectKind = index;     
                System.out.println("kind = " + objectKind );
                long milliSeconds = System.currentTimeMillis();
                testOneKind();
                System.out.println(objectKind + ":all:          " + ( System.currentTimeMillis() - milliSeconds) );
        }
   }

   public static void main(String args[] )      {
        Driver2 aDriver = new Driver2();  //Creates an object of Driver2 for testing.
        aDriver.init();
        aDriver.testIt();
        aDriver.end();
        System.exit(0);
   }
}