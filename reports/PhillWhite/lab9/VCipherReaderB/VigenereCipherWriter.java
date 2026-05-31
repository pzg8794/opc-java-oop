/* 
 *  Created:      02/07/2012
 *  Last Changed: 02/07/2012
 *  
 *  VigenereCipherWriter.java
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;


/*
* Abstract class for writing to character streams. The only 
* methods that a subclass must implement are write(char[], 
* int, int), flush(), and close(). Most subclasses, however,
* will override some of the methods defined here in order to 
* provide higher efficiency, additional functionality, or both.
* 
* @author  Wander Bravo	ID :  wmb1306 	RIT ID : 110006833
* @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
*
*/
public class VigenereCipherWriter extends Writer{
	
	private Writer wr;
	private BufferedWriter buffer;
	private String keyword;
	private int index_actual_character;
	private int length_keywork;
	public static int A_CODE = (int)'A';
	public static int Z_CODE = (int)'Z';
	public static int a_CODE = (int)'a';
	public static int z_CODE = (int)'z';
	public static int range = z_CODE - a_CODE + 1; 
	
	
	/**
	* Creates a new Vigenere cipher writer.
	*/
	public VigenereCipherWriter(Writer wr, String keyword) throws IllegalArgumentException{
		this.setWr(wr);
		this.buffer = new BufferedWriter(wr);
		this.keyword = keyword;
		this.index_actual_character = 0;
		this.length_keywork = this.keyword.length();
	} 
	
	
	
	/**
	* Function which takes the input string and compares whether it is a 
	* letter or not. If it is a letter it will check whether it is
	* upper-case or lower-case, the output will be generated according to 
	* the input the answer.
	*/
	public boolean isALetter(char letter){
		int test = (int)letter;
		if((test>=a_CODE && test <=z_CODE) || (test>=A_CODE && test <=Z_CODE))
			return true;
		return false;
	}
	
	
	
	/**
	* Closes the stream and releases any system resources associated with it.
	*/
	public void close() throws IOException{
		this.flush();
		this.buffer.close();
	}
	
	
	
	/**
	*  Flushes the stream.
	*/
	public void flush() throws IOException{
		this.buffer.flush();
	}
	
	
	
	/**
	* Writes a single character. Encodes a single character and writes 
	* it to the underlying Writer.
	*/
	public void write(char[] cbuf, int off, int len) throws IOException {						
			try {
				if(cbuf.length < off+len)
					throw new Exception("the parameters seem kinda wrong");
			} catch (Exception e) {
				e.printStackTrace();
			}		
		char []cipherText = cbuf.clone();		
		
		for(int i=off;i<off+len;i++){			
			if(isALetter(cbuf[i])){			
				if(Character.isLowerCase(cbuf[i])){
					int encoded;
					int cbuf_actual = cbuf[i];					
					int keyChar = (int)Character.toLowerCase( keyword.charAt(i%len) );
												
					encoded = ((cbuf_actual - a_CODE + keyChar - a_CODE) % range) + a_CODE;
					cipherText[i]=(char)encoded;					
				}
				else{
					int encoded;
					int cbuf_actual = cbuf[i];					
					int keyChar = (int)Character.toUpperCase( keyword.charAt(i%len) );
												
					encoded = ((cbuf_actual - A_CODE + keyChar - A_CODE) % range) + A_CODE;
					cipherText[i]=(char)encoded;					
				}
			}
			else 
				cipherText[i] = cbuf[i];
		}
		buffer.write(cipherText, off, len);
	}
	
	
	
	/**
	* Writes a single character. Encodes a single 
	* character and writes it to the underlying Writer.
	*/
	public void write(int ci) throws IOException{
		
		if(isALetter((char)ci)){
			if(Character.isLowerCase((char)ci)){
				int encoded;
				int keyChar = (int)Character.toLowerCase
						( keyword.charAt(this.index_actual_character%length_keywork) );			
							
				encoded = ((ci - a_CODE + keyChar - a_CODE) % range) + a_CODE;									
				
				buffer.write(encoded);
				this.index_actual_character ++;
			}
			else{
				int encoded;
				int keyChar = (int)Character.toUpperCase( keyword.charAt
						(this.index_actual_character%length_keywork) );			
							
				encoded = ((ci - A_CODE + keyChar - A_CODE) % range) + A_CODE;									
				
				buffer.write(encoded);
				this.index_actual_character ++;
			}
		}
		else
			{
				buffer.write(ci);
				this.index_actual_character ++;
			}
	}



	public Writer getWr() {
		return wr;
	}



	public void setWr(Writer wr) {
		this.wr = wr;
	}	
}
