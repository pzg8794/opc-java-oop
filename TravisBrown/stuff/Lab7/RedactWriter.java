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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;


/*
* Abstract class for writing to character streams. The only 
* methods that a subclass must implement are write(char[], 
* int, int), flush(), and close(). Most subclasses, however,
* will override some of the methods defined here in order to 
* provide higher efficiency, additional functionality, or both.
* 
* @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
*
*/
public class RedactWriter{
	
    /**
	 * buffer to contain words to be redacted.
	 */
	private Writer wr;
    
    /**
	 * buffer to contain words to be redacted.
	 */
	private BufferedWriter buffer;
    
    /**
	 * buffer to contain words to be redacted.
	 */
	private String tempWord = "";
    
    /**
	 * buffer to contain words to be redacted.
	 */
	private LinkedList<String> keyword = new LinkedList<String>();
	

	
	/**
	 * assigns a writer and all key words into a collection.
	 * @param wr
	 * @param redacts
	 */
	RedactWriter(Writer wr, Collection<String> redacts) {
		this.wr = wr;
	    keyword.addAll(redacts);
	}
	
    

	/**
	 * closes buffer
	 */
	public void	close() {
		this.flush();
		try {
			this.buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //Closes the stream, flushing it first.
    
	
	
	/**
	 * flush buffer.
	 */
	public void	flush() {
		try {
			this.buffer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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