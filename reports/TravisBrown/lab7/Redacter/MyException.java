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


import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * This program creates an exception class named MyExceptio
 * that handles the FileNotFound, IO, bad arguments, as well
 * as IlligalArment Exceptions thrown by the Redact and RedactWriter 
 * classes.
 * 
 * It takes as input the object of the respected Exception and 
 * handles it in nice and neat style for the user.
 * 
 * @author      Piter Garcia
 */
public class MyException extends Exception 
{
	/**
	 * serial number of MyException class
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Message that contains status of the thrown exception.
	 */
	private  String excstr;

	
	
	/**
	 * Constructor that takes care of the bad arguments a thrown exception.
	 */
	public MyException()
	{

		excstr = "\nException Type: Bad Number or args\n" +
				"Error description:\nUsage: java Redact <word-file> " +
				"<input-file> <output-file>";
		excstr +="\n\nNote: if you want to handle the input and or output file" +
				" \nthrough the console window, please use the symbol - instead " +
				"\ntheir actual names.";
	}

	
	
	
	/**
	 * Constructor that takes care of a thrown FileNotFound exception
	 * @param e, FileNotFound Exception object, contains information
	 * about the exception.
	 */
	public MyException(FileNotFoundException e) {
		
		excstr ="\nException Type: Nonexistant input file.";
		excstr +="\nError description:\n" + e.getMessage() + "\n";
	}

	
	
	/**
	 * Constructor that takes care of a thrown FileNotFound exception
	 * @param e, FileNotFound Exception object, contains information
	 * about the exception.
	 */
	public MyException(IOException e) {
		
		excstr ="\nException Type: Input/Output Exceptio.";
		excstr += "\nError description:\n" + e.getMessage() + "\n";
	}

	
	
	/**
	 * Constructor that takes care of thrown bad redact word exception. 
	 * @param txt, empty string, this is just used to distinguish bad
	 * redact words from bad arguments. 
	 */
	public MyException(String txt) {

  	  excstr ="\nException Type: File contains bad redact strings\n";
  	  excstr +="Error description:\n  redacts includes a " +
  	  		"string that is empty or is not exclusively comprised\n of " +
  	  		"characters in the range 'a' through 'z' and 'A' through 'Z'.";
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