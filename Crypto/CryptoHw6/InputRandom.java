import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;


public class InputRandom implements Comparable<char[]>{

	
	double bias = 0.509;

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


	public InputRandom(){

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

		InputRandom keyG = new InputRandom();
		
		int keySize = (int) (8*(1/(0.5*0.5)));
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
	
		InputRandom keyG = new InputRandom();
		keyG.key = new char[size];


		int i = 0;
		do{
			keys(keyG.generateKeys(size));

		}while( i++ != 3);
		
		PrintWriter writer = new PrintWriter("input.txt", "UTF-8");
		String[] array = keyG.toString().split(",");
		System.out.println();
//		System.out.println("\nSet of Keys : \n"+array[0]);
		
			for(int x=0; x< array.length; x++){
				writer.println(array[x]);
				System.out.println(array[x]);
			}
		

		System.out.println("\nSet of Inputs : \n"+keyG);
		writer.close();
	}
	
	public void morekeys(){
		
		int i = 0;
		do{
			keys(generateKeys(size));

		}while( i++ != 3);

	}
	
	
	
	public char[] generateKeys(int size) {

		InputRandom keyG = new InputRandom();
		keyG.key = new char[size];

		int x = -1;
		while( ++x < size){

			int tmpK = new Random().nextInt(15);
			keyG.key[x] = keyG.list.get(tmpK);			
		}

		if(InputRandom.keyList.contains(keyG.key)){

			System.out.println("HERE");
			keyG.generateKeys(size);
		}


		System.out.print(keyList.size() + " Random Input = ");
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
