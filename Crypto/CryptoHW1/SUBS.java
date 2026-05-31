import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;


public class SUBS implements Comparable<Object> {
	private static ArrayList<Integer> cnt = new ArrayList<Integer>();
	private static int alpha[] = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
	static TreeSet<SUBS> 			freqs = new TreeSet<SUBS>();
	private static ArrayList<Integer> 			beta = new ArrayList<Integer>();
	private static ArrayList<Character> 		letters = new ArrayList<Character>();
	private static LinkedList<SUBS> 	list = new LinkedList<SUBS>();
	private static LinkedList<SUBS> 	listC = new LinkedList<SUBS>();
	static ArrayList<SUBS> temp = new ArrayList<SUBS>(listC.size());
	

	 static // Create file 
	  FileWriter fstream = null;
	  static BufferedWriter cout;
	private static ArrayList<SUBS> tmp;
	private static ArrayList<SUBS> listr;
	  
	private String pair="";
	private char letter;
	private int cds;
	private int pc;

	public SUBS(char a) {
		
		letter =  a;
	}

	public SUBS(String temp) {
		pair =  temp;
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(br);
		
		try {
			fstream = new FileWriter("out2.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		cout = new BufferedWriter(fstream);
		
		for( int i=0; i< 26; i++){
			
			switch(i){
			case 0:
				letters.add('A');break;
			case 1:
				letters.add('B');break;
			case 2:
				letters.add('C');break;
			case 3:
				letters.add('D');break;
			case 4:
				letters.add('E');break;
			case 5:
				letters.add('F');break;
			case 6:
				letters.add('G');break;
			case 7:
				letters.add('H');break;
			case 8:
				letters.add('I');break;
			case 9:
				letters.add('J');break;
			case 10:
				letters.add('K');break;
			case 11:
				letters.add('L');break;
			case 12:
				letters.add('M');break;
			case 13:
				letters.add('N');break;
			case 14:
				letters.add('O');break;
			case 15:
				letters.add('P');break;
			case 16:
				letters.add('Q');break;
			case 17:
				letters.add('R');break;
			case 18:
				letters.add('S');break;
			case 19:
				letters.add('T');break;
			case 20:
				letters.add('U');break;
			case 21:
				letters.add('V');break;
			case 22:
				letters.add('W');break;
			case 23:
				letters.add('X');break;
			case 24:
				letters.add('Y');break;
			case 25:
				letters.add('Z');break;
			default:
					break;
			}
		}
		
		
		while( sc.hasNext()){
			String tmp = sc.next();
			
			char[] l = tmp.toCharArray();
			
			for( char a: l){
				list.add(new SUBS(a));
			}
		}	
		
		
		int i = 0;
		while(i != list.size()){
			
			System.out.print(list.get(i).letter);
			cout.write(list.get(i).letter);
			i++;
			
			if( i == list.size()/3 || i == 2*(list.size()/3)){
				System.out.println();
				cout.write("\n");
			}
		}	  
		// System.out.println(list);
		//	System.out.println(pairSub(new ArrayList<SUBS>(list),1));
		
		 
		try {
			orderingInput(pairSub(new ArrayList<SUBS>(list),1));
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean stop = false;

		Scanner in = new Scanner(System.in);
		stop = false;
		
		while(!stop){
			System.out.println("Please Enter the LETTER to replace");
			cout.write("Please Enter the LETTER to replace\n");
			String tmp1 = in.next();
			cout.write(tmp1+"\n");
			System.out.println("Please Enter the replacement letter");
			cout.write("Please Enter the replacement letter\n");
			String tmp2 = in.next();
			cout.write(tmp2+"\n");
			
			replace(new ArrayList<SUBS>(tmp), tmp1, tmp2);
			System.out.println("Are You Done?");
			cout.write("Are You Done?\n");
			if( in.next().toLowerCase().charAt(0) == 'y')
				stop = true;
			else
				stop = false;
			cout.write((stop == true)?"yes\n":"no\n");
		}
		
		try {
			cout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<SUBS> replace(ArrayList<SUBS> arrayList,
			String string, String string2) throws IOException {
		
		listr = new ArrayList<SUBS>(arrayList);
		
		for( SUBS s: arrayList){
			if( s.pair.equals(string))
				s.pair = string2;
			
			//			System.out.println(s.pair);
		}
		//		System.out.println(arrayList);
		
		
		int x = 0;
		if(temp.isEmpty()){
			while( x != listC.size()){
				temp.add(x, new SUBS(" "));
				x++;
			}
		}
		
		
		x = 0;
		System.out.print("Descending Order of Repeated Letters: \n Alphabet : [");
		cout.write("Descending Order of Repeated Letters: \n Alphabet : [");
		
		for( SUBS s: freqs){
			System.out.print(" ," + s.pair);
			cout.write(" ," + s.pair);
			
			if( s.pair.equals(string))
				temp.add(x, new SUBS(string2));
			
			x++;
		}
		System.out.println("]");
		cout.write("]\n");
		
		System.out.print(" Alphabet : [");
		cout.write(" Alphabet : [");
		for( SUBS s: temp){
			System.out.print(" ," + s.pair);
			cout.write(" ," + s.pair);
		}
		System.out.println("]\n");
		cout.write("]\n");
		
		
		int i = 0;
		while(i != arrayList.size()){
			
			if( i == arrayList.size()/3 || i == 2*(arrayList.size()/3)){
				System.out.println();
				cout.write("\n");
			}
				
			System.out.print(arrayList.get(i).pair);
			cout.write(arrayList.get(i).pair);
			i++;
		}
		
		System.out.println("\n\n");
		cout.write("\n\n\n");
		
		return listr;
	}

	private static void orderingInput(LinkedList<SUBS> linkedList) throws IOException {
		
		LinkedList<SUBS>  temp = new LinkedList<SUBS> (linkedList);
		freqs.addAll(temp);
		//	System.out.println("\n\n"+temp);
		
		System.out.println("\n\nAll Repeated: " + freqs);
		cout.write("\n\nAll Repeated: " + freqs+"\n");
		System.out.println("\n\nOrdered Frequencies: ");
		cout.write("\n\nHighest Freq: \n");
		Iterator<SUBS> itr = freqs.iterator();
		while(itr.hasNext()){
			SUBS tmp = itr.next();
			System.out.println(tmp);
			cout.write(tmp.toString()+"\n");
		}
		
		
		listC.clone();
		listC = new LinkedList<SUBS>(freqs);
		int loc = listC.indexOf(freqs.first());
		
		cout.write("Key length is:" + loc +"\n");
		System.out.println("Max Coincidences is:" + listC.get(loc));
		cout.write("Max Coincidences is:" + listC.get(loc)+"\n");	
	}

	public static ArrayList<SUBS> replace( ArrayList<SUBS> arrayList, char letter, char ch) throws IOException{
		
		ArrayList<SUBS> list = new ArrayList<SUBS>(arrayList);
		for( SUBS s: arrayList){
			if( s.letter == letter)
				s.letter = ch;
		}
		
		int i = 0;
		while(i != arrayList.size()){
			
			System.out.print(arrayList.get(i).letter);
			cout.write(arrayList.get(i).letter);
			i++;
			
			if( i == arrayList.size()/3 || i == 2*(arrayList.size()/3))
				System.out.println();
				cout.write("\n");
		}
		
		System.out.println("\n\n");
		cout.write("\n\n\n");
		
		return list;
	}
	
	
	
	
	/**
	 * Obtaining the smallest element in the array.
	 * @param a, array of elements
	 * @return loc, the location of the smallest element.
	 */
	public static int getMax(LinkedList<SUBS> linkedList){
		int loc = 0;
		
		if( linkedList.get(0).pair!=""){
			int max = linkedList.get(0).cds;
			
			for( int i = 0; i< linkedList.size(); i++){
				
				if( max > linkedList.get(i).pc){
					
				}else{
					max = linkedList.get(i).pc;
					loc = i;
				}
			}
			
		}else{
			int max = linkedList.get(0).cds;
			
			for( int i = 0; i< linkedList.size(); i++){
				
				if( max > linkedList.get(i).cds){
					
				}else{
					max = linkedList.get(i).cds;
					loc = i;
				}
			}
		}
		return loc;
	}
	
	
	
	
	public static ArrayList<SUBS> displacement(){
		
		int i = 0;
		ArrayList<Character> ch = new ArrayList<Character>();
		
		while( i != list.size()){
			
			if(!ch.contains(list.get(i).letter))
				ch.add(list.get(i).letter);
			i++;
		}
		// System.out.println("\n\n"+ch);
		
		for( Character s: ch){
			listC.add(new SUBS(s));
		}
		// System.out.println("\n\n"+listC);
		
		for( int i1=0; i1< listC.size(); i1++){
			
			for( int j=0; j< list.size(); j++){
				
				if( listC.get(i1).letter == list.get(j).letter)
					listC.get(i1).cds++;
			}
		}
		// System.out.println("\n\n"+listC);
		
		return new ArrayList<SUBS>(listC);
	}
	
	
	public static String decrypt(int x, int j){
		
		String output = "";
		int i=0;
		listC.clear();
		
		while(i != list.size()){
				int temp = letters.indexOf(list.get(i).letter);
				
				//	System.out.print( temp+ " " + j + " " + x + " \n");
				int tmp = (temp - j);
				tmp *= x; tmp %= 26;
				
				if(tmp < 0)
					tmp +=26;
				output += letters.get(tmp);
				//	System.out.println(letters.get(tmp));
				
				listC.add(new SUBS(letters.get(tmp)));
				i++;
				tmp=0;
		}	
		return output;
	}
	
	
	public static int inverse(int alp){
		int x=0;
		alp %= 26;
		while(true){
			
			if((alp*x)%26 == 1%26){
				return x;
			}
			x++;
		}
	}
	
	
	public static int coincidences(){
		
		int count = 0;
		for( Integer k: cnt)
			count += k;
		
		return count;
	}
	
	
	public int compareTo(Object bc) {
		if(this.pair != ""){
			if( this.pc < ((SUBS) bc).pc)
				return 1;
		}
		else{
			if( this.cds < ((SUBS) bc).cds)
    		return 1;
		}
		
    	return -1;
	}
    //Method to compare two BaseballCard objects based on home runs, then name
	
	public static LinkedList<SUBS> pairSub(ArrayList<SUBS>sC, int cbs) throws IOException{
		String temp="";
//		System.out.println("Alphabet Used in the Text: " + sC);
		
		tmp = new ArrayList<SUBS>();
		for( SUBS s: sC){
			
			if( cbs != 1){
				if(temp.length() != cbs){
					temp += s.letter;
				}else{
					tmp.add(new SUBS(temp));
					temp="";
				}
			}else{
				temp += s.letter;
				tmp.add(new SUBS(temp));
				temp="";
			}
		}
		//	System.out.println(tmp);
		
		
		int i = 0;
		ArrayList<String> ch = new ArrayList<String>();
		
		while( i != tmp.size()){
			
			if(!ch.contains(tmp.get(i).pair))
				ch.add(tmp.get(i).pair);
			i++;
		}
		 System.out.println("\n\nAlphabet Used: "+ch);
		 cout.write("\n\nAlphabet Used: "+ch);;
		 
		listC.clear();
		for( String s: ch){
			listC.add(new SUBS(s));
		}
		//System.out.println("\n\n"+listC);
		
		for( int i1=0; i1< listC.size(); i1++){
			
			for( int j=0; j< tmp.size(); j++){
				
				if( listC.get(i1).pair.equals(tmp.get(j).pair))
					listC.get(i1).pc++;
			}
		}
		// System.out.println("\n\n"+listC);
		return listC;	
	}
    
    
    /**
     * This method compares if two Baseball Cards are equal or not.
     *
     * @return true, if the two cards are equal.
     * @return false, if the two cards are not equal.
     */
	 public boolean equals(Object obj){
	        if (obj instanceof SUBS) {
	        	SUBS pp = (SUBS) obj;
	        	if(this.pair != "")
	        		return (pp.pc == this.pc) && (pp.pair.equals(this.pair));
	        	else
	        		return (pp.cds == this.cds) && (pp.letter == this.letter);
	        } else {
	            return false;
	        }
	 }
    
    
	public String toString(){
		
		return "( Letter: "+letter+pair + "| Count: "+ cds + pc+")";	
	}
	
}