/*
 * MyException.java
 *
 * Version:
 *     $Id: MyException.java,v 1.1 2013/07/23 23:02:23 pzg8794 Exp $
 *
 * Revisions:
 *     $Log: MyException.java,v $
 *     Revision 1.1  2013/07/23 23:02:23  pzg8794
 *     Lab7
 *
 */


/**
 * This program creates an exception class named MyExceptio
 * that handles Exceptions thrown by the BattleShip game class.
 * 
 * It takes as input the object of the respected Exception and 
 * handles it in nice and neat style for the user.
 * 
 * @author      Piter Garcia
 */
public class MyException extends Exception 
{
	/*
	 * serial number of MyException class
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Message that contains status of the thrown exception.
	 */
	private  String excstr;

	
	
	
	/**
	 * Constructor that takes care of thrown exception. 
	 * @param txt, updates the message. 
	 */
	public MyException(String txt) {

  	  excstr = txt;
	}

	
	
	
	/**
	 * Illegal ship specification in configuration file at a certain line
	 * @param i, line in which the error was found
	 */
	public MyException(int i) {
		excstr = "Illegal ship specification in " +
				"configuration file at line "+i +".";
	}




	/**
	 * String representation of the thrown exception, it displays then thrown 
	 * exception in a nice and neat way.
	 */
	public String toString()
	{
		return excstr;   
	}
}