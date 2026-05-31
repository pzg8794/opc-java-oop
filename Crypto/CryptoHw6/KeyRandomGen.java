import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class KeyRandomGen implements Comparable<char[]>{

	ArrayList<Character> list = new ArrayList<Character>();
	static ArrayList<char[]> keyList = new ArrayList<char[]>();	
	static int size;
	private char[] key;

	static int kSize;

	public static void keys(char[] key){
		if(!keyList.contains(key))
			keyList.add(key);
		else
			System.out.println("checkinh");
	}


	public KeyRandomGen(){

		char[] temp = {'A', 'B', 'C', 'D', 'E', 'F'};

		int i =-1;
		int x = -1;
		while(++i != 16){
			String tmp = ""+i;

			if(i<10)
				list.add(tmp.charAt(0));
			else{
				list.add(temp[++x]);
			}
		}

		//		int j = 0;
		//		for( Character c: list)
		//			key[j++] = c;

	}

	public static void main(String[] args){

		KeyRandomGen keyG = new KeyRandomGen();
		int keySize = 80;
		size = keySize/4;
		try {
			keyG.generateKeySet(size);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		System.out.println(keyG.key);

	}


	public void generateKeySet(int size) throws FileNotFoundException, UnsupportedEncodingException{
	
		KeyRandomGen keyG = new KeyRandomGen();
		keyG.key = new char[size];

		PrintWriter writer = new PrintWriter("key.txt", "UTF-8");

		
		
		int i = 0;
		do{
			keys(keyG.generateKeys(size));

		}while( i++ != 3);

		String[] array = keyG.toString().split(",");
		System.out.println();
//		System.out.println("\nSet of Keys : \n"+array[0]);
		
			for(int x=0; x< array.length; x++){
				writer.println(array[x]);
				System.out.println(array[x]);
			}
		

		System.out.println("\nSet of Keys : \n"+keyG);
		writer.close();
	}
	
	public void morekeys(){
		
		int i = 0;
		do{
			keys(generateKeys(size));

		}while( i++ != 3);

	}
	
	
	
	public char[] generateKeys(int size) {

		KeyRandomGen keyG = new KeyRandomGen();
		keyG.key = new char[size];

		int x = -1;
		while( ++x < size){

			int tmpK = new Random().nextInt(15);
			keyG.key[x] = keyG.list.get(tmpK);			
		}

		if(KeyRandomGen.keyList.contains(keyG.key)){

			System.out.println("HERE");
			keyG.generateKeys(size);
		}


		System.out.print(keyList.size() + " Random Key = ");
		System.out.println(keyG.key);

		return keyG.key;
	}


	@Override
	public int compareTo(char[] o) {

		if( this.equals(o))
			return 0;
		else
			return -1;
	}

	public String toString(){
		String temp = "";

		for( char[] cl: keyList){	

			for(Character c: cl)
				temp += c;

			temp+=",";
		}

		return temp+"";
	}

}
