/*
 * RedactWriter.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log: 
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;


/**
 * This class is a redact writer program that writes a redacted
 * text from an input file, or console window, to an assigned out file.
 * 
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 *
 */
public class RedactWriter extends Writer{

	/*
	 * buffer to contain words to be redacted.
	 */
	private Writer wr;

	/*
	 * buffer to contain words to be redacted.
	 */
	private BufferedWriter buffer;

	/*
	 * buffer to contain words to be redacted.
	 */
	private String tempWord = "";

	/*
	 * buffer to contain words to be redacted.
	 */
	private LinkedList<String> keyword = new LinkedList<String>();



	/**
	 * assigns a writer and all key words into a collection.
	 * @param wr, writer
	 * @param redacts, collection of words to overwrite
	 * @throws MyException 
	 */
	public RedactWriter(Writer wr, Collection<String> redacts) throws IllegalArgumentException {
		this.wr = wr;
		keyword.addAll(redacts);
		
		for( String s: keyword){
			if(s.isEmpty())
				throw new IllegalArgumentException();
			else{
				
				for( Character c: s.toCharArray())
					if(!Character.isLetter(c)){
						
						throw new IllegalArgumentException();
					}
			}
		}
			
	}



	/**
	 * closes buffer
	 * @throws IOException 
	 */
	public void	close() throws IOException {
			buffer.close();
	}
	//Closes the stream, flushing it first.



	/**
	 * flush buffer.
	 * @throws IOException 
	 */
	public void	flush() throws IOException {
			buffer.flush();
	}
	//Flushes the stream.



	/**
	 * writes a list of charactesrs
	 *
	 * @param cbuf, array of caracters
	 * @param off, start of the list
	 * @param len, end of the list
	 * @throws IOException
	 */
	public void	write(char[] cbuf, int off, int len) throws IOException {
		for(int i= off; i<len; i++){
			write(cbuf[off+i]);
			// System.out.println(cbuf[off+1]);
		} 
	}
	//Writes a portion of an array of characters.




	/**
	 * This methos compare strings and writes it over if it is a key word.
	 *
	 * @param c, chater that reads in.
	 * @throws IOException
	 */
	public void	write(int c) throws IOException {
		// System.out.println(" Inside my write method");
		char x = (char) c;

		if(Character.isLetter(c)){

			// System.out.println(" it's a letter ");
			tempWord +=  x;
			//	System.out.println(tempWord);
		}
		else{
			if( keyword.contains(tempWord)){
				//	System.out.println(" it's a letter ");

				for( int i=0; i< tempWord.length(); i++){
					wr.write("X");
				}

				//	System.out.println(tempWord.toString());

			}else{
				//	System.out.println(" it's not a letter ");
				wr.write(tempWord);
				//	System.out.println(tempWord);
			}

			wr.write((char)c);
			wr.flush();
			tempWord = "";
		}
	}
	//Writes a single character.
}