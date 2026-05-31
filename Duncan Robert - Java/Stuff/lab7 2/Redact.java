/* redact.java 
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

public class Redact {

	/**
	 * class declarations
	 */
	private static BufferedReader buff;	
	private static BufferedReader buff2;
	static FileReader wrdFile;
	static FileReader inFile;
	static FileWriter outFile;
	static LinkedList<String> wrdlist = new LinkedList<String>();
	
	/**
	 * The purpose of main is to read a file from the user and it calls the writer.
	 * @param args[0],  file with words to be redacted
	 * @param args[1]  file  to be redacted
	 * @param args[2] output file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args){
		if( args.length == 3){
		String firstArg = args[0];
		String secondArg = args[1];
		String ThirdArg = args[2];
		String Line = "";
		new Scanner(System.in);
		new LinkedList<String>();
		
			try {
				if(firstArg.contains(".txt"))
					buff = new BufferedReader(new FileReader(firstArg));
				else{
					firstArg += ".txt";
					buff = new BufferedReader(new FileReader(firstArg));
				}
				while ((Line = buff.readLine()) != null) { 
					wrdlist.add(Line);
				}   
			    if(!ThirdArg.equals("-")){
			            if(!secondArg.equals("-")){
			        		if(secondArg.contains(".txt")){
				        			buff2 = new BufferedReader(new FileReader(secondArg));
			        		}else{ 
			        			firstArg += ".txt";
				        		buff2 = new BufferedReader(new FileReader(secondArg));
			        		}
			        		Writer wrr = new FileWriter(ThirdArg);
			        		RedactWriter wr = new RedactWriter(wrr, wrdlist);
			        		while( buff2.ready()){
			        			wr.write(buff2.read());
			        		}
			        		wrr.close();
			        		buff2.close();
			        	}else{
			        		buff2 = new BufferedReader(
			        		new InputStreamReader(System.in));
			        		Writer write =  new FileWriter(ThirdArg);
			        		RedactWriter w = new RedactWriter(write, wrdlist);	
			        		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
			        		BufferedReader reader = new BufferedReader(inputStreamReader);
			        		System.out.println("Enter # to quite");
			        		char ch = 0;
			        		while(ch!= '#'){
			        			ch = (char)reader.read();	
			        			w.write(ch);
			        		}
			        		write.close();
			        		buff2.close();
			        	}
			     }else{
				     if(!secondArg.equals("-")){
				    	 if(secondArg.contains(".txt")){	 
					    		 buff2 = new BufferedReader(
					    				 new FileReader(secondArg));
				    	 }else{
				    		 secondArg += ".txt";
				    			 buff2 = new BufferedReader(new 
				    				 FileReader(secondArg));
				    	 }
				 		Writer w = new PrintWriter(System.out);
						RedactWriter wr = new RedactWriter(w, wrdlist);
						while( buff2.ready()){
							wr.write(buff2.read());
						}
						w.close();
						buff2.close();
				    
				     }else{
				    	buff = new BufferedReader(new InputStreamReader(System.in));
				 		Writer write =  new PrintWriter(System.out);
						RedactWriter w = new RedactWriter(write, wrdlist);	
						InputStreamReader inputStreamReader = new InputStreamReader(System.in);
						BufferedReader R = new BufferedReader(inputStreamReader);
						System.out.println("Enter # to quite");
						char ch = 0;
						while(ch!= '#'){
							ch = (char)R.read();	
							w.write(ch);
						}
						write.close();
						buff2.close();
				     }
			     }
			} 
			catch (IOException e) {
				System.err.println("Error: " + e);
			}
		}else{
			System.out.println("Usage: java Redact bad-words inputFile ouputFile");
	
		}
	}

	/**
	 * Constructor
	 * @param ignore string of keyword
	 * @param R reader
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * 
	 * 
	 */
	public Redact(Reader R, String ignore) throws IllegalArgumentException, IOException{
		Redact.buff2 = new BufferedReader(R);
	}
}
