/* 
 *  Created:      02/07/2012
 *  Last Changed: 02/07/2012
 *  
 *  VigenereCipherReader.java
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;



/*
* This class should override the mark, markSupported, ready, and reset methods 
* that have implementations in the abstract base class. The VigenereCipherReader 
* class us to reflect the behavior of the underlying Reader. Abstract class for 
* reading character streams. The only methods that a subclass must implement are
* read(char[], int, int) and close(). Most subclasses, however, will override 
* some of the methods defined here in order to provide higher efficiency, 
* additional functionality, or both.
* 
* @author  Wander Bravo	ID :  wmb1306 	RIT ID : 110006833
* @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
*
*/
public class VigenereCipherReader extends Reader{
	private Reader rd;
	private BufferedReader buffer;
	private String keyword;
	
	private int index_actual_character = 0;
	private int length_keyword;
	/*
	public static int A_CODE = (int)'A';
	public static int Z_CODE = (int)'Z';
	public static int a_CODE = (int)'a';
	public static int z_CODE = (int)'z';
	
	public static int range = z_CODE - a_CODE + 1;
	*/
	public static int A_CODE = 65;
	public static int Z_CODE = 90;
	public static int a_CODE = 97;
	public static int z_CODE = 122;
	
	public static int range = 26;	
	
	
	/**
	* Creates a new Vigenere cipher reader
	*/
	public VigenereCipherReader(Reader rd, String keyword) 
			throws IllegalArgumentException{
		this.rd = rd;
		this.buffer = new BufferedReader(rd);
		this.keyword = keyword;		
		this.length_keyword = this.keyword.length();
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
		this.buffer.close();				
	}
	
	
	
	
	/**
	* Marks the present position in the stream.
	*/
	public void mark(int readAheadLimit) throws IOException{
		this.buffer.mark(readAheadLimit);
	}
	
	
	
	/**
	* Tells whether this stream supports the mark operation.
	*/
	public boolean markSupported(){
		return this.buffer.markSupported();
	}
	
	
	
	/**
	* Reads characters into a portion of an array. Reads characters 
	* from the underlying Reader into a portion of an array and
	*  decodes the read portion.
	*/
	public int read() throws IOException{
		int readInt = this.rd.read();
		int keyChar = (int) keyword.charAt(this.index_actual_character%length_keyword) - a_CODE;
		//if(isALetter((char)readInt)){
			//if(Character.isLowerCase((char)readInt)){
			if(a_CODE<=readInt && readInt<=z_CODE){
			
				int decoded;						
				decoded = readInt - a_CODE;
				decoded = (decoded - keyChar) % range;				
				
				if(decoded < 0)
					decoded = decoded + range;
				decoded = decoded + a_CODE;
				
				this.index_actual_character ++;
				return decoded;
			}
			else if(A_CODE<=readInt && readInt<=Z_CODE){
				int decoded;
				
				decoded = readInt - A_CODE;
				decoded = (decoded - keyChar)% range;				
				
				if(decoded < 0)
					decoded = decoded + range;
				decoded = decoded + A_CODE;
				
				this.index_actual_character ++;
				return decoded;
			}
		//}
			else if ((char)readInt ==' ')
			{
				this.index_actual_character ++;
				return readInt;
				}	
		else
			{
			this.index_actual_character ++;
			return readInt;
			}
	}
	
	
	
	
	/**
	* Reads characters into a portion of an array. Reads characters from the 
	* underlying Reader into a portion of an array and decodes the read portion.
	*/
	public int read(char[] cbuf, int off, int len)  throws IOException{
		try {
			if(cbuf.length < off+len)
				throw new Exception("the parameters seem kinda wrong");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		int answer = buffer.read(cbuf, off, len); 
		
		char []message = cbuf.clone();
		//
		for(int i=off;i<off+len;i++){									
				if(isALetter(cbuf[i])){
					if(Character.isLowerCase(cbuf[i])){
						int decoded;															
						int keyChar = (int)Character.toLowerCase
								( keyword.charAt(i%length_keyword) );																																														
																												
						decoded = (((int)cbuf[i] - a_CODE) - (keyChar - a_CODE));
						if(decoded>=0)
							decoded = decoded % range + a_CODE;
						else
							decoded = decoded % range + z_CODE +1;								
						this.index_actual_character ++;
						message[i]=(char)decoded;						
					}
					else{
						int decoded;															
						int keyChar = (int)Character.toUpperCase
								( keyword.charAt(i%length_keyword) );																																																																																		
						decoded = ((((int)cbuf[i]) - A_CODE) - (keyChar - A_CODE));
						if(decoded >=0)
							decoded = decoded % range + A_CODE;
						else
							decoded = decoded % range + Z_CODE +1;
						this.index_actual_character ++;
						message[i]=(char)decoded;																		
					}
				}
				else 									
					message[i] = cbuf[i];
		}
		return answer;
	}
	
	
	
	
	/**
	* Tells whether this stream is ready to be read.
	*/
	public boolean ready() throws IOException{
		return this.buffer.ready();
	}
	
	
	
	/**
	* Resets the stream.
	*/
	public void reset() throws IOException{
		this.buffer.reset();		
	}
	
	
	
	/**
	* Skips characters.
	*/
	public long skip(long n) throws IOException{
		return this.buffer.skip(n);		
	}
}