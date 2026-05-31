import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;


public class IOC implements Comparable<Object> {

	private static LinkedList<IOC> list = new LinkedList<IOC>();
	private static ArrayList<Integer> 	 cnt = new ArrayList<Integer>();
	private static LinkedList<Integer> 	 count = new LinkedList<Integer>();

	private static ArrayList<Character>  letters = new ArrayList<Character>();
	private static LinkedList<IOC> listC;

	static // Create file 
	FileWriter fstream = null;
	static BufferedWriter cout;
	  
	private char letter=' ';
	private ArrayList<Integer> location;
	private int cds = 0;
	private int pc= 0;
	private static int dmt;
	private static ArrayList<IOC> tmp;
	static TreeSet<IOC> 			freqs = new TreeSet<IOC>();
	private String pair ="";
	private int loca;
	private String loc = "";
	
	public IOC(char a) {
		
		letter =  a;
		location = new ArrayList<Integer>();
	}
	
	public IOC(String temp) {
		pair =  temp;
		location = new ArrayList<Integer>();
	}

	public IOC(String temp, String x) {
		pair =  temp;
		loc = x;
		location = new ArrayList<Integer>();
	}

	public IOC(char a, int j) {
		letter =  a;
		loca = j;
		location = new ArrayList<Integer>();
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(br);
		
		try {
			fstream = new FileWriter("out.txt");
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
		
		
		int j = 0;
		while( sc.hasNext()){
			String tmp = sc.next();
			
			char[] l = tmp.toUpperCase().toCharArray();
			
			for( char a: l)
				list.add(new IOC(a, j));
			j++;
		}	
		
		listC = new LinkedList<IOC>(list);
		
		
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
		System.out.println();
		displacement(list);
		
		
		//shifting approach
		LinkedList<IOC> tp = new LinkedList<IOC>();
		list.getFirst().cds = -1;
		IOC first = list.getFirst();
		
		do{
			try {
				tp.addAll(orderingInput((pairSub(new ArrayList<IOC>(list), 3))));
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.println("Locations: " + location);
			list.addFirst(list.removeLast());
			
		}while(!list.getFirst().equals(first));
		
		
		
		LinkedList<IOC> tp1 = new LinkedList<IOC>(listC);
		try {
			orderingInput(pairSub(new ArrayList<IOC>(list),2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		indexOfCoincidence(tp1);
		
		
		//group divisions approach
		LinkedList<IOC>fst = new LinkedList<IOC>();
		LinkedList<IOC>snd = new LinkedList<IOC>();
		LinkedList<IOC>trd = new LinkedList<IOC>();
		
		for( IOC s: orderingInput(pairSub(new ArrayList<IOC>(list), 2))){
			//			System.out.println("Locations for " + s.pair + " : ");
			//			System.out.println(s.location);
			
			fst.add(new IOC(s.pair.toCharArray()[0]));
			snd.add(new IOC(s.pair.toCharArray()[1]));
			//			trd.add(new IOC(s.pair.toCharArray()[2]));
		}
		
		//		System.out.println(displacement(fst));
		indexOfCoincidence(displacement(fst));
		
		
		//		System.out.println(displacement(snd));
		indexOfCoincidence(displacement(snd));
		
		
		ArrayList<Object> key = new ArrayList<Object>();
		System.out.println("\n\nPlease Enter the Key");
		sc = new Scanner (System.in);
		String temp = sc.nextLine();
	
		int x = 0;
		while(x != list.size()){
			key.add(temp.toUpperCase().charAt(x%3));
			x++;
		}
		System.out.println(key);
		decrypt(list, key);
		
	}
	
	
	
	private static void decrypt(LinkedList<IOC> list2, ArrayList<Object>key) {
		ArrayList<IOC> temp = new ArrayList<IOC>(list2);
		int tmp = 0;
		int i = -1;
		listC.clear();
		while( ++i != list2.size()){
			tmp = 0;
			tmp = letters.indexOf(temp.get(i).letter) - letters.indexOf(key.get(i));
			if( tmp < 0)
				tmp += 26;
			listC.add(i, new IOC(letters.get(tmp)));
		}
		System.out.println(temp);
		System.out.println(listC);
		
	}
	
	public static LinkedList<IOC> pairSub(ArrayList<IOC>sC, int cbs){
		String temp="";
		tmp = new ArrayList<IOC>();
		for( IOC s: sC){
			
			if(temp.length() != cbs){
				temp += s.letter;
			}else{
				tmp.add(new IOC(temp));
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
		// System.out.println("\n\n"+ch);
		listC.clear();
		for( String s: ch){
			listC.add(new IOC(s));
		}
		// System.out.println("\n\n"+listC);
	
		for( int i1=0; i1< listC.size(); i1++){
			
			for( int j=0; j< tmp.size(); j++){
				
				if( listC.get(i1).pair.equals(tmp.get(j).pair)){
					listC.get(i1).pc++;
					listC.get(i1).location.add(i1);
					listC.get(i1).location.add(j);
				}
			}
		}
		
		LinkedList<IOC> tp = new LinkedList<IOC>();
		for( IOC s: listC){
			if( s.pc > 1)
				tp.add(s);
		}
		// System.out.println("\n\n"+listC);
	
		return tp;	
	}
	
	private static LinkedList<IOC> orderingInput(LinkedList<IOC> linkedList) throws IOException {
		
		LinkedList<IOC>  temp = new LinkedList<IOC> (linkedList);
		freqs.clear();
		freqs.addAll(temp);
		//	System.out.println("\n\n"+temp);
		
		System.out.println("\n\nAll Repeated: " + freqs);
		cout.write("\n\nAll Repeated: " + freqs+"\n");
		System.out.println("\n\nOrdered Frequencies: ");
		cout.write("\n\nOrdered Frequencies: \n");
		Iterator<IOC> itr = freqs.iterator();
		while(itr.hasNext()){
			IOC tmp = itr.next();
			System.out.println(tmp);
			cout.write(tmp.toString()+"\n");
		}
		
		if( !temp.isEmpty()){
		LinkedList<IOC> listC = new LinkedList<IOC>(freqs);
		int loc = listC.indexOf(freqs.first());
		
		cout.write("Key length is:" + loc +"\n");
		System.out.println("Max Coincidences is:" + listC.get(loc));
		cout.write("Max Coincidences is:" + listC.get(loc)+"\n");
		}
		
		return listC;
	}

	
	
	private static LinkedList<IOC> displacement(LinkedList<IOC> fst) {
		int i = 0;
		ArrayList<Character> ch = new ArrayList<Character>();
		
		while( i != fst.size()){
			
			if(!ch.contains(fst.get(i).letter))
				ch.add(fst.get(i).letter);
			i++;
		}
		// System.out.println("\n\n"+ch);
		listC.clear();
		for( Character s: ch){
			listC.add(new IOC(s));
		}
//		 System.out.println("\n\n"+listC);
		
		for( int i1=0; i1< listC.size(); i1++){
			
			for( int j=0; j< list.size(); j++){
				
				if( listC.get(i1).letter == list.get(j).letter){
					listC.get(i1).cds++;;
					listC.get(i1).loc += ", " + list.get(j).loca;
				}
				
			}
		}
//		 System.out.println("\n\n"+listC);
		
		return listC;
	}
	
	public static double indexOfCoincidence(LinkedList<IOC> list2){
		double index = 0;
		double N = 0;
		double pi = 0;
		double mr = 0;
		
		for( IOC s: list2){
			N += s.cds;
			//	System.out.println("N :" + N);
		}
		
		System.out.println("N : "+N);
		
		for( int i=1; i<8; i++)
		System.out.println("M " + i +" : "+ Math.pow((i/N), 2)*N);
		
		for( int x = 0; x< list2.size(); x++){
			pi += (list2.get(x).cds * (list2.get(x).cds - 1))/(N * ( N - 1 ));
			//			System.out.println("pi :" + pi);
		}
		
		System.out.println("pi :" + pi);
		//	System.out.println("pi^2 :" + (pi*pi));
		int p = 0;
		for( int x = 0; x< list2.size(); x++){
			
			p += (list2.get(x).cds * (list2.get(x).cds - 1))/(N * ( N - 1 ));
			p *= p;
			mr += (p - 0.038);
			//	System.out.println("pi :" + pi);
		}
		
		System.out.println("MR :" + mr);
		
		System.out.println("IC :" + (mr + 0.038));
		
		
		for( int i=0; i< 26; i++){
			
			index += (i*( i - 1))/(26*(26-1));
			//	System.out.println(index);
			
		}
		
		return index;
	}
	
	

    /**
     * This method compares if two Baseball Cards are equal or not.
     *
     * @return true, if the two cards are equal.
     * @return false, if the two cards are not equal.
     */
	 public boolean equals(Object obj){
	        if (obj instanceof IOC) {
	        	IOC pp = (IOC) obj;
	        	if(this.pair != "")
	        		return ( (pp.pc == this.pc) && (pp.cds == this.cds) ) && 
	        				((pp.pair.equals(this.pair)&&(pp.letter == this.letter)&&(pp.loc == this.loc))  );
	        	else
	        		return ( (pp.pc == this.pc) && (pp.cds == this.cds) ) && 
	        				((pp.pair.equals(this.pair)&&(pp.letter == this.letter)&&(pp.loc == this.loc)));
	        } else {
	            return false;
	        }
	 }
	 
	 
	 
		public int compareTo(Object bc) {
			if(this.pair != ""){
				if( (this.pc < ((IOC) bc).pc) || (this.pair.equals(((IOC) bc).pc)))
					return 1;
			}
			else{
				if( this.cds < ((IOC) bc).cds)
	    		return 1;
			}
			
	    	return -1;
		}
	    //Method to compare two BaseballCard objects based on home runs, then name
		
	
	
	public static int coincidences(){
		
		int count = 0;
		for( Integer k: cnt)
			count += k;
		
		return count;
	}
	
	public String toString(){
		
		return "( Letter: "+letter+pair + "| Count: "+ cds + pc+ " Location: " + loc +")";	
	}
}
