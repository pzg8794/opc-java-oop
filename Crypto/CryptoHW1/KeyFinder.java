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


public class KeyFinder implements Comparable<Object> {

	private static LinkedList<KeyFinder> list = new LinkedList<KeyFinder>();
	private static ArrayList<Integer> 	 cnt = new ArrayList<Integer>();
	private static LinkedList<Integer> 	 count = new LinkedList<Integer>();

	private static ArrayList<Character>  letters = new ArrayList<Character>();
	private static LinkedList<KeyFinder> listC;

	static // Create file 
	FileWriter fstream = null;
	static BufferedWriter cout;
	  
	private char letter=' ';
	private ArrayList<Integer> location;
	private int cds;
	private int pc;
	private static int dmt;
	private static ArrayList<KeyFinder> tmp;
	static TreeSet<KeyFinder> 			freqs = new TreeSet<KeyFinder>();
	private String pair ="";
	private String loc;
	
	public KeyFinder(char a) {
		
		letter =  a;
	}
	
	public KeyFinder(String temp) {
		pair =  temp;
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
		
		
		
		while( sc.hasNext()){
			String tmp = sc.next();
			
			char[] l = tmp.toUpperCase().toCharArray();
			
			for( char a: l)
				list.add(new KeyFinder(a));
		}	
		
		listC = new LinkedList<KeyFinder>(list);
		
		
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
		
//		ArrayList<Object> key = new ArrayList<Object>();
//		System.out.println("\n\nPlease Enter the Key");
//		sc = new Scanner (System.in);
//		String temp = sc.nextLine();
//	
//		int x = 0;
//		while(x != list.size()){
//			key.add(temp.toUpperCase().charAt(x%3));
//			x++;
//		}
//		System.out.println(key);
//		decrypt(list, key);
		
		
		int x = 1;
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		while(x != 25){
			tmp.add(x);
			System.out.println("\n");
			System.out.println("Letters Repeated: " + displacement(x));
			System.out.println("Time No Repeated: "+cnt);
			count.add(x-1,  coincidences());
			System.out.println("Number of Coincidences: " + count.get(x-1));
			x++;
		}
		
		System.out.println("\n\nDisplacement:" + tmp);
		System.out.println("Coincidences:" + count);
		
		int loc = getMax1(count)+1;
		System.out.println("Key length is:" + loc );
		System.out.println("Max Coincidences is:" + count.get(loc-1));	
//		System.out.println(split(list.toString(), loc));
		
		
		LinkedList<KeyFinder> tp = new LinkedList<KeyFinder>();
		list.getFirst().cds = -1;
		KeyFinder first = list.getFirst();
				
				
		do{
			try {
				tp.addAll(orderingInput((pairSub(new ArrayList<KeyFinder>(list), 3))));
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.println("Locations: " + location);
			list.addFirst(list.removeLast());
			
		}while(!list.getFirst().equals(first));
//		
////		TreeSet<KeyFinder> frequency = new TreeSet<KeyFinder>(tp);
////		System.out.println(frequency);
////		
//		LinkedList<KeyFinder>fst = new LinkedList<KeyFinder>();
//		LinkedList<KeyFinder>snd = new LinkedList<KeyFinder>();
//		LinkedList<KeyFinder>trd = new LinkedList<KeyFinder>();
//		
//		for( KeyFinder s: orderingInput(pairSub2(new ArrayList<KeyFinder>(tp), 3))){
//			System.out.println("Locations for " + s.pair + " : ");
//			System.out.println(s.location);
//			
//			fst.add(new KeyFinder(s.pair.toCharArray()[0]));
//			snd.add(new KeyFinder(s.pair.toCharArray()[1]));
//			trd.add(new KeyFinder(s.pair.toCharArray()[2]));
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
//		try {
//			orderingInput(displacement(trd));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		ArrayList<Object>l = new ArrayList<Object>();
		l.add('A');
		l.add('Y');
		l.add('C');
		
		
		ArrayList<Object> key = new ArrayList<Object>();
		System.out.println("\n\nPlease Enter the Key");
		
		int x1 = 0;
		while(x1 != list.size()){
			key.add(l.get(x1%3));
			x1++;
		}
		decrypt(list, key );
		
		for( KeyFinder s: listC)
			System.out.print(s.letter);
	}
	
	
	
    private static LinkedList<KeyFinder> pairSub2(
			ArrayList<KeyFinder> sC, int cbs) {
		String temp="";
		tmp = new ArrayList<KeyFinder>();
		for( KeyFinder s: sC){
			
			if(temp.length() != cbs){
				temp += s.letter;
			}else{
				tmp.add(new KeyFinder(temp));
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
			listC.add(new KeyFinder(s));
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
		
		LinkedList<KeyFinder> tp = new LinkedList<KeyFinder>();
		for( KeyFinder s: listC){
			if( s.pc > 1)
				tp.add(s);
		}
		// System.out.println("\n\n"+listC);
		return tp;	
	}

	private static LinkedList<KeyFinder> displacement(LinkedList<KeyFinder> fst) {
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
			listC.add(new KeyFinder(s));
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

    /**
     * This method compares if two Baseball Cards are equal or not.
     *
     * @return true, if the two cards are equal.
     * @return false, if the two cards are not equal.
     */
	 public boolean equals(Object obj){
	        if (obj instanceof KeyFinder) {
	        	KeyFinder pp = (KeyFinder) obj;
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
	 
	 
	 
		public int compareTo(Object bc) {
			if(this.pair != ""){
				if( this.pc < ((KeyFinder) bc).pc)
					return 1;
			}
			else{
				if( this.cds < ((KeyFinder) bc).cds)
	    		return 1;
			}
			
	    	return -1;
		}
	    //Method to compare two BaseballCard objects based on home runs, then name
		
	/**
	 * Obtaining the smallest element in the array.
	 * @param a, array of elements
	 * @return loc, the location of the smallest element.
	 */
	public static int getMax1(LinkedList<Integer> count2){
		Integer max = count2.get(0);
		int loc = 0;
		
		for( int i = 0; i< count2.size(); i++){
			
			if( max > count2.get(i)){
				
			}else{
				max = count2.get(i);
				loc = i;
			}
		}
		return loc;
	}
	private static LinkedList<KeyFinder> orderingInput(LinkedList<KeyFinder> linkedList) throws IOException {
		
		LinkedList<KeyFinder>  temp = new LinkedList<KeyFinder> (linkedList);
		
		if(!temp.isEmpty()){
			freqs.clear();
			freqs.addAll(temp);
			//	System.out.println("\n\n"+temp);
			
			System.out.println("\n\nAll Repeated: " + freqs);
			cout.write("\n\nAll Repeated: " + freqs+"\n");
			System.out.println("\n\nHighest Freq: ");
			cout.write("\n\nHighest Freq: \n");
			Iterator<KeyFinder> itr = freqs.iterator();
			while(itr.hasNext()){
				KeyFinder tmp = itr.next();
				System.out.println(tmp);
				cout.write(tmp.toString()+"\n");
			}
	
				listC.clone();
				listC = new LinkedList<KeyFinder>(freqs);
				int loc = listC.indexOf(freqs.first());
				
				System.out.println("Key length is:" + loc );
				cout.write("Key length is:" + loc +"\n");
				System.out.println("Max Coincidences is:" + listC.get(loc));
				cout.write("Max Coincidences is:" + listC.get(loc)+"\n");
		}
		return new LinkedList<KeyFinder>(freqs);
	}
	
	public static ArrayList<KeyFinder> split(String text, int keyLen)
	{
	     ArrayList<String> result = new ArrayList<String>();
	     StringBuilder[] sb = new StringBuilder[keyLen];
	     ArrayList<KeyFinder>tp = new ArrayList<KeyFinder>();
	 
	     for (int i = 0; i < keyLen; i++)
	         sb[i] = new StringBuilder();
	 
	     for (int i = 0; i < text.length(); i++)
	         sb[i % keyLen].append(text.charAt(i));
	 
	     for (StringBuilder item: sb)
	         result.add(item.toString());
	     
	     for ( String s: result)
	    	 tp.add(new KeyFinder(s));
	 
	    return tp;
	}
	
	
	public static HashMap<Character, Double>AnalyseFrequency(String text)
	{
	    if (text == null)
	        return null;
	    HashMap<Character, Double>diccionary = new HashMap<Character, Double>();
	 	new ArrayList<KeyFinder>();
	 	
	    int textLength = text.length();

	    for (int i = 0; i < textLength; i++)
	    {
	        char key = '#';


	        if (diccionary.containsKey(key))
	            diccionary.put(key, diccionary.get(key)+1);
	        else
	        	diccionary.put(key, (double) 1);
	    }
	    for (Character c : diccionary.keySet())
	    {
	    	diccionary.put(c, (diccionary.get(c)/text.length()));	
	    }

	    return diccionary;
	}

	private static void decrypt(LinkedList<KeyFinder> list2, ArrayList<Object>key) {
		ArrayList<KeyFinder> temp = new ArrayList<KeyFinder>(list2);
		int tmp = 0;
		int i = -1;
		listC.clear();
		while( ++i != list2.size()){
			tmp = 0;
			tmp = letters.indexOf(temp.get(i).letter) - letters.indexOf(key.get(i));
			if( tmp < 0)
				tmp += 26;
			listC.add(i, new KeyFinder(letters.get(tmp)));
		}
		System.out.println(temp);
		System.out.println(listC);
		
	}

	/**
	 * Obtaining the smallest element in the array.
	 * @param a, array of elements
	 * @return loc, the location of the smallest element.
	 */
	public static int getMax(LinkedList<KeyFinder> count2){
		int loc = 0;
		if(!count2.isEmpty()){
			if( count2.getFirst().pair!=""){
				int max = count2.getFirst().cds;
				
				for( int i = 0; i< count2.size(); i++){
					
					if( max > count2.get(i).pc){
						
					}else{
						max = count2.get(i).pc;
						loc = i;
					}
				}
				
			}else{
				int max = count2.getFirst().cds;
				
				for( int i = 0; i< count2.size(); i++){
					
					if( max > count2.get(i).cds){
						
					}else{
						max = count2.get(i).cds;
						loc = i;
					}
				}
			}
		}
		return loc;
	}
	
	
	public static LinkedList<KeyFinder> pairSub(ArrayList<KeyFinder>sC, int cbs){
		String temp="";
		tmp = new ArrayList<KeyFinder>();
		for( KeyFinder s: sC){
			
			if(temp.length() != cbs){
				temp += s.letter;
			}else{
				tmp.add(new KeyFinder(temp));
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
			listC.add(new KeyFinder(s));
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
		
		LinkedList<KeyFinder> tp = new LinkedList<KeyFinder>();
		for( KeyFinder s: listC){
			if( s.pc > 1)
				tp.add(s);
		}
		// System.out.println("\n\n"+listC);
		return tp;	
	}
	
	public static ArrayList<Character> displacement( int i){
		dmt = i;
		int x=0;
		ArrayList<Character> chr = new ArrayList<Character>();
		LinkedList<Character> ch = new LinkedList<Character>();
		
		
		while( i != listC.size()){
			
			if( list.get(i).letter == listC.get(x).letter){
				list.get(i).cds++;
				
				//	System.out.println(list.get(i).letter);
				
				if(!chr.contains(list.get(i).letter))
					chr.add(list.get(i).letter);
				
				ch.add(list.get(i).letter);
				
			}
			i++;
			x++;
		}	
		
		System.out.println(ch);
		cnt.clear();
		
		int i1= 0;
		while( i1 != chr.size()){
			int cn = 0;
			while( ch.contains(chr.get(i1))){
				++cn;
				ch.removeFirstOccurrence(chr.get(i1));
			}
			cnt.add(i1, cn);
			i1++;
		}
	
		return chr;
	}
	
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
