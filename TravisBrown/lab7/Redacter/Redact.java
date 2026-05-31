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
import java.util.*;

/**
 * This program redacts a text from an input file or console window.
 * @author piter garcia
 *
 */
public class Redact {

	
	
	/*
	 * buffer to contain words to be redacted. 
	 */
	private static BufferedReader br;

	
	
	/*
	 * buffer to contain text to redact from.
	 */
	private static BufferedReader br2;

	
	
	/*
	 * Container to give access a file to read.
	 */
	static FileReader wordFile;

	
	
	/*
	 * container to give access a file to read.
	 */
	static FileReader inputfile;

	
	
	/*
	 * Container to create and give access to an outputfile. 
	 */
	static FileWriter outputfile;

	
	
	/*
	 * collection of words to be redacted.
	 */
	static LinkedList<String> wordslist = new LinkedList<String>();

	

	/**
	 * Constructor, assigns memory to a buffer reader.
	 *
	 * @param rd. reader
	 * @param keyword, bad words
	 */
	public Redact(Reader rd, String keyword){
		Redact.br2 = new BufferedReader(rd);
	}

	
	
	/**
	 * main method, where the files are read or text is input by the user
	 * it calls the writer to redact the input file and or text. If agrs[1]
	 * or args[2] is equal to - then these arguments are ignored and text
	 * must be input by user.
	 * @param args, args[0] file name that contains words to be redacted,
	 * args[1] file name of text to be redacted, args[2] output file name 
	 * where redacted text will be saved.
	 */
	public static void main(String[] args){

		try {
			
			if( args.length == 3){

				//Open the file for reading
				String thisLine = "";

				if(args[0].contains(".txt"))
					br = new BufferedReader(new FileReader(args[0]));

				else{
					args[0] += ".txt";
					br = new BufferedReader(new FileReader(args[0]));
				}

				while ((thisLine = br.readLine()) != null)
					wordslist.add(thisLine);


				if(!args[2].equals("-")){
					
					if(!args[2].contains(".txt"))
						args[2] +=".txt";

					if(!args[1].equals("-")){

						if(args[1].contains(".txt")){
							
							br2 = new BufferedReader(new FileReader(args[1]));
						}else{
							
							args[1] += ".txt";
							br2 = new BufferedReader(new FileReader(args[1]));
						}

						//IO Exception, if any, is caught when the try ends. 
						readingOut(args[2], 1);

					}else{

						br2 = new BufferedReader(
								new InputStreamReader(System.in));

						//IO Exception, if any, is caught when the try ends. 
						writtingOut(args[2], 1);
					}

				}else{
					
					if(!args[1].equals("-")){

						if(args[1].contains(".txt")){
							
							br2 = new BufferedReader(new FileReader(args[1]));
						}else{
							
							args[1] += ".txt";
							br2 = new BufferedReader(new FileReader(args[1]));
						}

						//IO Exception, if any, is caught when the try ends. 
						readingOut(args[2], 0);

					}else{
						br2 = new BufferedReader(
								new InputStreamReader(System.in));

						//IO Exception, if any, is caught when the try ends. 
						writtingOut(args[2], 0);	
					}
				}

			}else{

				//this throws an exception if the file args are bad.
				throw new MyException();
			}
			
		} catch(FileNotFoundException e){ 
			//catching FileNotFound Exception and handling on a neat way
			try {
				
				throw new MyException(e);
			} catch (MyException e1) {
				
				System.out.println(e1);
			}
		} 
		catch (IOException e) {
			//catching IO Exception and handling on a neat way
			try {
				
				throw new MyException(e);
			} catch (MyException e1) {
				
				System.out.println(e1);
			}

		}catch(MyException e){
			
			System.out.println(e);
		}
	}



	/**
	 * This method reades from an inputfile and compares each strings.
	 * @param string, name of the outpufile.
	 * @param i, identify whether there is an output file name or not.
	 * @throws IOException
	 * @throws MyException 
	 */
	@SuppressWarnings("resource")
	private static void readingOut(String string, int i) throws IOException, MyException {

		Writer wrr = null;
		if( i == 1)
			wrr = new FileWriter(string);
		if( i == 0)
			wrr =  new PrintWriter(System.out);

		RedactWriter wr = null;
		try{
			wr = new RedactWriter(wrr, wordslist);
		}catch(IllegalArgumentException e){
			throw new MyException("");
		}

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
	 * @throws MyException 
	 */
	@SuppressWarnings("resource")
	private static void writtingOut(String string, int i) throws IOException, MyException {
		Writer wrr = null;
		if( i == 0)
			wrr =  new PrintWriter(System.out);
		if( i == 1)
			wrr =  new FileWriter(string);

		RedactWriter wr = null;
		try{
			wr = new RedactWriter(wrr, wordslist);
		}catch(IllegalArgumentException e){
			throw new MyException("");
		}

		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);

		System.out.println("Please Enter ~ to quite after entering text");
		ArrayList<Character> ch = new ArrayList<Character>();
		char c = 0;
		while(c!= '~'){
			c = (char)reader.read();

			if( c!='~')
				ch.add(c);
		}

		for( Character c1: ch)
			wr.write(c1);


		inputStreamReader.close();
		reader.close();
		wrr.close();
		br2.close();
	}

}
