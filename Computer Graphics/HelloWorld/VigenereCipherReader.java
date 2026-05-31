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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.HashSet;



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
	private static Reader rd;
	private BufferedReader buffer;
	private String keyword;
	private int index_actual_character;
	private int length_keyword;
	
	public static int A_CODE = (int)'A';
	public static int Z_CODE = (int)'Z';
	public static int a_CODE = (int)'a';
	public static int z_CODE = (int)'z';
	public static int range = z_CODE - a_CODE + 1;
	private static FileReader wordFile;
	private static FileReader inputfile;
	private static FileWriter outputfile;
	private static BufferedReader buffer1;
	private static BufferedReader buffer2;
	private static FileWriter wr; 
	
	
	
	/**
	* Creates a new Vigenere cipher reader
	*/
	public VigenereCipherReader(Reader rd, String keyword) 
			throws IllegalArgumentException{
		this.setRd(rd);
		this.buffer = new BufferedReader(rd);
		this.keyword = keyword;
		this.index_actual_character = 0;
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
		int readInt = this.buffer.read(); 
		if(isALetter((char)readInt)){
			if(Character.isLowerCase((char)readInt)){
				int decoded;
				int keyChar = (int)Character.toLowerCase( keyword.charAt
						(this.index_actual_character%length_keyword) );			
							
				decoded = ((readInt - a_CODE) - (keyChar - a_CODE));
				if(decoded>=0)
					decoded = decoded % range + a_CODE;
				else
					decoded = decoded % range + z_CODE +1;								
				this.index_actual_character ++;
				return decoded;
			}
			else{
				int decoded;
				int keyChar = (int)Character.toUpperCase( keyword.charAt
						(this.index_actual_character%length_keyword) );			
							
				decoded = ((readInt - A_CODE) - (keyChar - A_CODE));
				if(decoded >=0)
					decoded = decoded % range + A_CODE;
				else
					decoded = decoded % range + Z_CODE +1;
				this.index_actual_character ++;
				return decoded;
			}
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
		int counter =0;
		for(int i=off; i < off-len; i++){
			
			cbuf[i] = (char)this.read();

			counter++;
		}
		return counter;
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



	public Reader getRd() {
		return rd;
	}



	public void setRd(Reader rd) {
		this.rd = rd;
	}
	
	
	public static void main(String[] args) throws IllegalArgumentException, IOException{
		
		if( args.length == 3){
			try {
				wordFile = new FileReader(args[0]+".txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			try {
				inputfile = new FileReader(args[1]+".txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			try {
				outputfile = new FileWriter(args[2]+".txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Collection<String> list = new HashSet<String>();

				new Redact(wordFile, list.toString());
				buffer1 = new BufferedReader(wordFile);
			
			//	Scanner sc = new Scanner(rd);
			//	while(sc.hasNext())
			//	list.add(sc.next());
			
				rd = new FileReader(args[1]+".txt");
		
			
				new Redact(rd, inputfile.toString());
				buffer2 = new BufferedReader(rd);

			try{
				wr = new FileWriter(args[2]+".txt");
				new RedactWriter(wr, list);
				while(inputfile.ready()){
					wr.write(buffer2.read());
				}
			}
			catch (IllegalArgumentException e) {
				System.err.println("The list of redacted words has an invalid word.");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				
//				while (buffer2.ready()) {
//					outputfile.write(buffer2.read());
//				}
				try {
					outputfile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					buffer2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			System.out.println("Usage: java Redact <word-file> <input-file> " +
					"<output-file>");
		}

		
	}
}