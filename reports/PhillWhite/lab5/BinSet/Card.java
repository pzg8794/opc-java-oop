/*
 *  Created:      11/30/2012
 *  Last Changed: 12/04/2012
 *  
 *  Card.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;



/**
 * A class to represent a playing card with a rank and suit
 *
* Piter Garcia ID = 296009929, PZG8794
* Wander Bravo ID = 110006833, WMB1306
 * 
 * @author  Piter Garcia
 * @author  Wander Bravo
 */
public class Card implements Comparable<Object>{
	
	/**
	 * Suit of the Card
	 */
	private String suit;
	/**
	 * Rank of the Card
	 */
	private Ranks rank;
	
	/**
	 * @return the Suit of the Card
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * @param suit
	 * This function set the suit value of the Card
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}

	/**
	 * @return the rank of the Card
	 */
	public Ranks getRank() {
		return rank;
	}

	/**
	 * @param rank
	 * This function set the rank value of the Card
	 */
	public void setRank(Ranks rank) {
		this.rank = rank;
	}

	/**
	 * Constructor for a Card Object
	 * @param rank 
	 * @param suit
	 */
	public Card(Ranks rank,String suit)
	{
		this.suit = suit;
		this.rank = rank;
	}
	
	
	
	
    /**
    * This function converts the binary search tree ... to a string
    *
    * @param it return a string with all the keys of the tree.  
    *
    */
	public String toString()
	{
		return this.rank.getShortName() + " of " + this.suit.toUpperCase();
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Compares first by suit, then by rank, with suits 
	 * "increasing" in the order Clubs, Diamonds, Hearts, Spades.
	 */
	public int compareTo(Object e) {
		Card tmp = (Card)e;
		String Mysuit = this.suit.toUpperCase();
		String suit = tmp.getSuit().toUpperCase();
		if(Mysuit=="CLUBS")
		{
			if(suit=="CLUBS")
			{
				if(this.rank.getValue() == tmp.getRank().getValue())
					return 0;
				else if(this.rank.getValue() > tmp.getRank().getValue())
					return 1;
				else
					return -1;
			}
			else
				return 1;			
		}
		else if(Mysuit=="DIAMONDS")
		{
			if(suit=="CLUBS")
			return -1;
			if(suit=="DIAMONDS")
			{
				if(this.rank.getValue() == tmp.getRank().getValue())
					return 0;
				else if(this.rank.getValue() > tmp.getRank().getValue())
					return 1;
				else
					return -1;
			}
			else
				return 1;
		}
		else if(Mysuit=="HEARTS")
		{
			if(suit=="CLUBS")
				return -1;
			else if(suit=="DIAMONDS")
				return -1;
			if(suit=="HEARTS")
			{
				if(this.rank.getValue() == tmp.getRank().getValue())
					return 0;
				else if(this.rank.getValue() > tmp.getRank().getValue())
					return 1;
				else
					return -1;
			}
			else
				return 1;
		}
		else
		{
			if(suit=="CLUBS")
				return -1;
			else if(suit=="DIAMONDS")
				return -1;
			else if(suit=="HEARTS")
				return -1;
			else
			{
				if(this.rank.getValue() == tmp.getRank().getValue())
					return 0;
				else if(this.rank.getValue() > tmp.getRank().getValue())
					return 1;
				else
					return -1;
			}
		}			
	}
	/**
	 * @param args
	 */
	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	public static void main(String args[])
	{
		//write testing code in the main method that creates some Card objects,
		//puts them into a BinSet, and tests the methods of the BinSet class.
		Scanner select = new Scanner(System.in);
		
		ArrayList<Card> binSet = new ArrayList<Card>();
		
		binSet.add(new Card(Ranks.EIGHT,"CLUBS"));
		binSet.add(new Card(Ranks.DEUCE,"HEARTS"));
		binSet.add(new Card(Ranks.NINE,"DIAMONDS"));
		
		BinSet binset = new BinSet(binSet);
		System.out.println(binset.toString());
		
		
		System.out.println("Please Enter The Amount of Cards You Wish to Enter");
		int cards = select.nextInt();
		
		System.out.println("Testing Card Class -- Adding");
	 	Random generator = new Random(); 
    	int r=0,s=0;
    	
    	for (int i = 0; i <= cards; i++){
    		
    		r = generator.nextInt(13);
			s = generator.nextInt(4);
			
    		binSet.add( new Card(TestBinSet.ranks[r], TestBinSet.suits[s]));
    	
    	}
    	
    	System.out.println("Testing Card Class -- Iterating");
    	Iterator itr = binSet.iterator();
    	while (itr.hasNext()){	
    	
    		System.out.println(itr.next().toString());
    	}
		
    	System.out.println();
    	System.out.println("Testing Comparing Values -- Constructor");
    	for (int i = 0; i <= cards; i++){
    		
    		r = generator.nextInt(13);
			s = generator.nextInt(4);
			
			
			Card test1 = new Card(TestBinSet.ranks[r], TestBinSet.suits[s]);
			Card test11 = new Card(TestBinSet.ranks[r], TestBinSet.suits[s]);	
			
			if( test11.compareTo(test1) < 0){
				System.out.println(test11.toString() + " is smaller than " + test1.toString());
			}
			else if(test11.compareTo(test1) > 0){
				System.out.println(test11.toString() + " is greater than " + test1.toString());
			}
			else{
				
				System.out.println(test11.toString() + " is equal to " + test1.toString());
			}
				
    	}
			
		
	}
}
