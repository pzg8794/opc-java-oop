/*
 * Redact.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log: 
 */
import java.io.*;
import java.nio.CharBuffer;
import java.util.*;

/**
 *  Thi program performs redactions for input file or text.
 * @author pitergarcia
 *
 */
public class Redact {

	/**
	 * buffer to contain words to be redacted. 
	 */
	private static BufferedReader br;
	
	/**
	 * buffer to contain text to redact from.
	 */
	private static BufferedReader br2;

	/**
	 * reader to read input and or file input.
	 */
	private static Reader rd;

	/**
	 * containter to give access a file to read.
	 */
	static FileReader wordFile;

	/**
	 * container to give access a file to read.
	 */
	static FileReader inputfile;
	
	/**
	 * containter to create and give acces to an outputfile. 
	 */
	static FileWriter outputfile;

	/**
	 * collection of words to be redacted.
	 */
	static LinkedList<String> wordslist = new LinkedList<String>();
	
	

	/**
	 * main method, where the files are read or text is input by the user
	 * it calls the writer to redact the input file and or text. If agrs[1]
	 * or args[2] is equal to - then these areguments are ignored and text
	 * must be input by user.
	 * @param args, args[0] file name that contains words to be redacted,
	 * args[1] file name of text to be redacted, args[2] output file name 
	 * where redacted text will be saved.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) {
        
        
		//Loop across the arguments
        
		if( args.length == 3){
            
            //Open the file for reading
            String thisLine = "";
            new Scanner(System.in);
            new LinkedList<String>();
            
			try {
				if(args[0].contains(".txt"))
					try{
                        br = new BufferedReader(new FileReader(args[0]));
				    }catch(FileNotFoundException e){
                        System.out.println("The source " +
                                           "file does not exist. ");
                        e.printStackTrace();
				    }
                
				else{
					args[0] += ".txt";
					try{
						br = new BufferedReader(new FileReader(args[0]));
					}catch(FileNotFoundException e){
                        System.out.println("The source " +
                                           "file does not exist. ");
                        e.printStackTrace();
					}
				}
                
				
				while ((thisLine = br.readLine()) != null) {
					// while loop begins here
					wordslist.add(thisLine);
				}   // end while
                
				
				
			    if(!args[2].equals("-")){
                    
                    if(!args[1].equals("-")){
                        
                        if(args[1].contains(".txt")){
                            try{
                                br2 = new BufferedReader(
                                                         new FileReader(args[1]));
                            }catch(FileNotFoundException e){
                                System.out.println("The source " +
                                                   "file does not exist. ");
                                e.printStackTrace();
                            }
                        }else{
                            try{
			        			args[0] += ".txt";
                                br2 = new BufferedReader(
                                                         new FileReader(args[1]));
                            }catch(FileNotFoundException e){
                                System.out.println("The source " +
                                                   "file does not exist. ");
                                e.printStackTrace();
                            }
                        }
                        
                        try{
                            readingOut(args[2], 1);
                        }catch (IOException e) {
                            System.err.println("Error: " + e);
                        }
                        
                    }else{
                        br2 = new BufferedReader(
                                                 new InputStreamReader(System.in));
                        
                        try{
                            writtingOut(args[2], 1);
                        }catch (IOException e) {
                            System.err.println("Error: " + e);
                        }
                    }
                    
                }else{
                    
                    if(!args[1].equals("-")){
                        
                        if(args[1].contains(".txt")){
                            try{
                                br2 = new BufferedReader(
                                                         new FileReader(args[1]));
                            }catch(FileNotFoundException e){
                                System.out.println("The source " +
                                                   "file does not exist. ");
                                e.printStackTrace();
							} 
                        }else{
                            args[1] += ".txt";
                            try{
                                br2 = new BufferedReader(new 
                                                         FileReader(args[1]));
                            }catch(FileNotFoundException e){
                                System.out.println("The source " +
                                                   "file does not exist. ");
                                e.printStackTrace();
							} 
                        }
                        
                        try{
                            readingOut(args[2], 0);
                        }catch (IOException e) {
                            System.err.println("Error: " + e);
		        		}
                        
                    }else{
                        br2 = new BufferedReader(
                                                 new InputStreamReader(System.in));
                        try{
                            writtingOut(args[2], 0);	
                        }catch (IOException e) {
                            System.err.println("Error: " + e);
                        }
                    }
                }
			} // end try
			catch (IOException e) {
				System.err.println("Error: " + e);
			}
		}else{
			System.out.println("Usage: java Redact bad-words inputFile ouputFile");
			System.out.println("Note: to write from System.in inputFile and or " +
                               "outputFile must = -");
		}
	}


	
    /**
     * This method reades from an inputfile and compares each strings.
     * @param string, name of the outpufile.
     * @param i, identify whether there is an output file name or not.
     * @throws IOException
     */
	private static void readingOut(String string, int i) throws IOException {
		
		Writer wrr = null;
		if( i == 1)
			wrr = new FileWriter(string);
		if( i == 0)
			wrr =  new PrintWriter(System.out);
		
		RedactWriter wr = new RedactWriter(wrr, wordslist);
        
		while( br2.ready()){
			wr.write(br2.read());
		}
		wrr.close();
		br2.close();
		
	}


	
    /**
     * This method reades from system.in and compares each strings.
     * @param string, name of the outpufile.
     * @param i, identify whether there is an output file name or not.
     * @throws IOException
     */
	private static void writtingOut(String string, int i) throws IOException {
		Writer wrr = null;
		if( i == 0)
			wrr =  new PrintWriter(System.out);
		if( i == 1)
			wrr =  new FileWriter(string);
		
		RedactWriter wr = new RedactWriter(wrr, wordslist);
		
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		
		System.out.println("Please Enter ~ to quite after entering text");
		char c = 0;
		while(c!= '~'){
			c = (char)reader.read();
			wr.write(c);
		}
		
		wrr.close();
		br2.close();
	}



	/**
	 * Constructor, assigns memory to a buffer reader.
     *
	 * @param rd
	 * @param keyword
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public Redact(Reader rd, String keyword) throws IllegalArgumentException, IOException{
		Redact.br2 = new BufferedReader(rd);
	}
	
	
	
	/**
	 * returns true if the buffer has strings to read, otherwise false.
	 * @return tru if it has strings to read, false if not.
	 * @throws IOException
	 */
	public boolean ready() throws IOException{
		return Redact.br2.ready();
	}



	/**
	 * resets buffer
	 * @throws IOException
	 */
	public void reset() throws IOException{
		Redact.br2.reset();		
	}


 
	/**
	 * gets reader
	 * @return rd, the reader.
	 */
	public Reader getRd() {
		return rd;
	}


	/**
	 * sets the reader
	 */
	public void setRd(Reader rd) {
		Redact.rd = rd;
	}
}
