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


public class KeyFinder3 implements Comparable<Object> {
	private static ArrayList<Integer> cnt = new ArrayList<Integer>();
	private static int alpha[] = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
	static TreeSet<KeyFinder3> 			freqs = new TreeSet<KeyFinder3>();
	private static ArrayList<Integer> 			beta = new ArrayList<Integer>();
	private static ArrayList<Character> 		letters = new ArrayList<Character>();
	private static LinkedList<KeyFinder3> 	list = new LinkedList<KeyFinder3>();
	private static LinkedList<KeyFinder3> 	listC = new LinkedList<KeyFinder3>();
	

	 static // Create file 
	  FileWriter fstream = null;
	  static BufferedWriter cout;
	private static ArrayList<KeyFinder3> tmp;
	private ArrayList<Integer> location;
	private int loc;
	private String pair="";
	private char letter;
	private int cds;
	private int pc;

	public KeyFinder3(char a) {
		
		letter =  a;
	}

	public KeyFinder3(String temp) {
		pair =  temp;
		location = new ArrayList<Integer>();
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(br);
		
		try {
			fstream = new FileWriter("out3.txt");
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
				list.add(new KeyFinder3(a));
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
		 
		try {
			orderingInput(displacement(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int x = -1;
		while( ++x < 26){
			beta.add(x);
		}
		
		ArrayList<Integer> outa = new ArrayList<Integer>();
		ArrayList<Integer> outb = new ArrayList<Integer>();
				
				
		for( int x1 = 1;x1<alpha.length - 1;x1++){
			for( int j=1;j<beta.size();j++){
				int tmp = (alpha[x1]*letters.indexOf(freqs.first().letter)+beta.get(j))%26;
						
					if(tmp == letters.indexOf('E')){
						outa.add(alpha[x1]);
						outb.add(beta.get(j));
					}
			}
		}
				
		System.out.println("Possible Alpha List : " + outa);
		cout.write("Possible Alpha List : " + outa + "\n");
		System.out.println("Possible Beta List : " + outb);
		cout.write("Possbile Beta List : " + outb + "\n");
		  
		
//		LinkedList<KeyFinder3> tp = new LinkedList<KeyFinder3>();
//		
//		while(!list.isEmpty()){
//			try {
//				tp.addAll(orderingInput((pairSub(new ArrayList<KeyFinder3>(list), 2))));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			list.removeFirst();
//		}
//		
		LinkedList<KeyFinder3> tp1 = new LinkedList<KeyFinder3>();
		list.getFirst().cds = -1;
		KeyFinder3 first = list.getFirst();
			
		do{
			try {
				tp1.addAll(orderingInput((pairSub(new ArrayList<KeyFinder3>(list), 2))));
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.println("Locations: " + location);
			list.addFirst(list.removeLast());
			
		}while(!list.getFirst().equals(first));
		
//		try {
//		cout.close();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
		
//		
////		TreeSet<KeyFinder3> frequency = new TreeSet<KeyFinder3>(tp);
////		System.out.println(frequency);
//		
//		LinkedList<KeyFinder3>fst = new LinkedList<KeyFinder3>();
//		LinkedList<KeyFinder3>snd = new LinkedList<KeyFinder3>();
//		LinkedList<KeyFinder3>trd = new LinkedList<KeyFinder3>();
//		
//		for( KeyFinder3 s: orderingInput(pairSub2(new ArrayList<KeyFinder3>(tp), 2))){
//			System.out.println("Locations for " + s.pair + " : ");
//			System.out.println(s.location);
//			
//			fst.add(new KeyFinder3(s.pair.toCharArray()[0]));
//			snd.add(new KeyFinder3(s.pair.toCharArray()[1]));
////			trd.add(new KeyFinder3(s.pair.toCharArray()[2]));
//		}
//		
//		try {
//			orderingInput(displacement(fst));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			orderingInput(displacement(snd));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
////		try {
////			orderingInput(displacement(trd));
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//		ArrayList<Object>l = new ArrayList<Object>();
//		l.add(letters.indexOf('G'));
//		l.add(letters.indexOf('Z'));
//		l.add(letters.indexOf('G'));
//		decrypt(list, key);
		
	}
	
	private static LinkedList<KeyFinder3> orderingInput(LinkedList<KeyFinder3> linkedList) throws IOException {
		
		LinkedList<KeyFinder3>  temp = new LinkedList<KeyFinder3> (linkedList);
		
		if(!temp.isEmpty()){
			freqs.clear();
			freqs.addAll(temp);
			//	System.out.println("\n\n"+temp);
			
			System.out.println("\n\nAll Repeated: " + freqs);
			cout.write("\n\nAll Repeated: " + freqs+"\n");
			System.out.println("\n\nDescending Order Freqs: ");
			cout.write("\n\nDescending Order Freqs: \n");
			Iterator<KeyFinder3> itr = freqs.iterator();
			while(itr.hasNext()){
				KeyFinder3 tmp = itr.next();
				System.out.println(tmp);
				cout.write(tmp.toString()+"\n");
			}
	
				listC.clone();
				listC = new LinkedList<KeyFinder3>(freqs);
				int loc = listC.indexOf(freqs.first());
				
//				System.out.println("Key length is:" + loc );
//				cout.write("Key length is:" + loc +"\n");
				System.out.println("Max Coincidences is:" + listC.get(loc));
				cout.write("Max Coincidences is:" + listC.get(loc)+"\n");
		}
		return new LinkedList<KeyFinder3>(freqs);
	}

	public static ArrayList<KeyFinder3> replace( ArrayList<KeyFinder3> arrayList, char letter, char ch) throws IOException{
		
		ArrayList<KeyFinder3> list = new ArrayList<KeyFinder3>(arrayList);
		for( KeyFinder3 s: arrayList){
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
	public static int getMax(LinkedList<KeyFinder3> linkedList){
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
	
	
	
	
	public static LinkedList<KeyFinder3> displacement(LinkedList<KeyFinder3>list){
		
		int i = 0;
		ArrayList<Character> ch = new ArrayList<Character>();
		
		while( i != list.size()){
			
			if(!ch.contains(list.get(i).letter))
				ch.add(list.get(i).letter);
			i++;
		}
		// System.out.println("\n\n"+ch);
		listC.clear();
		for( Character s: ch){
			listC.add(new KeyFinder3(s));
		}
		// System.out.println("\n\n"+listC);
		
		for( int i1=0; i1< listC.size(); i1++){
			
			for( int j=0; j< list.size(); j++){
				
				if( listC.get(i1).letter == list.get(j).letter){
					listC.get(i1).cds++;
				}
				
			}
		}
		// System.out.println("\n\n"+listC);
		
		return listC;
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
				
				listC.add(new KeyFinder3(letters.get(tmp)));
				i++;
				tmp=0;
		}
		return output;
	}
	
	
	
	private static void decrypt(LinkedList<KeyFinder3> list2, ArrayList<Object>key) {
		ArrayList<KeyFinder3> temp = new ArrayList<KeyFinder3>(list2);
		int tmp = 0;
		int i = -1;
		listC.clear();
		while( ++i != list2.size()){
			tmp = 0;
			tmp = letters.indexOf(temp.get(i).letter) - letters.indexOf(key.get(i));
			if( tmp < 0)
				tmp += 26;
			listC.add(i, new KeyFinder3(letters.get(tmp)));
		}
		System.out.println(temp);
		System.out.println(listC);
		
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
			if( this.pc < ((KeyFinder3) bc).pc)
				return 1;
		}
		else{
			if( this.cds < ((KeyFinder3) bc).cds)
    		return 1;
		}
		
    	return -1;
	}
    //Method to compare two BaseballCard objects based on home runs, then name
	
	public static LinkedList<KeyFinder3> pairSub(ArrayList<KeyFinder3>sC, int cbs){
		String temp="";
		tmp = new ArrayList<KeyFinder3>();
		for( KeyFinder3 s: sC){
			
			if(temp.length() != cbs){
				temp += s.letter;
			}else{
				tmp.add(new KeyFinder3(temp));
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
			listC.add(new KeyFinder3(s));
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
		
		LinkedList<KeyFinder3> tp = new LinkedList<KeyFinder3>();
		for( KeyFinder3 s: listC){
			if( s.pc > 1)
				tp.add(s);
		}
		// System.out.println("\n\n"+listC);
		return tp;	
	}
    
	public static LinkedList<KeyFinder3> pairSub2(ArrayList<KeyFinder3>sC, int cbs){
		String temp="";
		tmp = new ArrayList<KeyFinder3>(sC);
//		for( KeyFinder3 s: sC){
//			
//			if(temp.length() != cbs){
//				temp += s.pair;
//			}else{
//				tmp.add(new KeyFinder3(temp));
//				temp="";
//			}
//		}
//			System.out.println(tmp);
		
		
		int i = 0;
		ArrayList<String> ch = new ArrayList<String>();
		
		while( i != tmp.size()){
			
			if(!ch.contains(tmp.get(i).pair))
				ch.add(tmp.get(i).pair);
			i++;
		}
//		 System.out.println("\n\n"+ch);
		
		listC.clear();
		for( String s: ch){
			listC.add(new KeyFinder3(s));
		}
		 System.out.println("\n\n"+listC);
		
		for( int i1=0; i1< listC.size(); i1++){
			
			for( int j=0; j< tmp.size(); j++){
				
				if( listC.get(i1).pair.equals(tmp.get(j).pair)){
					if( listC.get(i1).pc < tmp.get(j).pc){ 
						listC.remove(i1);
						listC.add(i1, tmp.get(j));
					}
				}

			}
		}
		
		LinkedList<KeyFinder3> tp = new LinkedList<KeyFinder3>();
		for( KeyFinder3 s: listC){
			if( s.pc > 1)
				tp.add(s);
		}
		// System.out.println("\n\n"+listC);
		return tp;	
	}
	
    /**
     * This method compares if two Baseball Cards are equal or not.
     *
     * @return true, if the two cards are equal.
     * @return false, if the two cards are not equal.
     */
	 public boolean equals(Object obj){
	        if (obj instanceof KeyFinder3) {
	        	KeyFinder3 pp = (KeyFinder3) obj;
	        	if(this.pair != "")
	        		return ( (pp.pc == this.pc) && (pp.cds == this.cds) ) && 
	        				((pp.pair.equals(this.pair)&&(pp.letter == this.letter)));
	        	else
	        		return ( (pp.pc == this.pc) && (pp.cds == this.cds) ) && 
	        				((pp.pair.equals(this.pair)&&(pp.letter == this.letter)));
	        } else {
	            return false;
	        }
	 }
    
    
	public String toString(){
		
		return "( Letter: "+letter+pair + "| Count: "+ cds + pc +")";	
	}
	
}
