/*
 * Card.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.ArrayList;



/**
 * A Card class that provides cards to two players, a human and a computer.
 *
 * @author pg: Piter Garcia
 */
public class Card{
	
	private static Ranks rank;
	private static Suits suit;
	private static int i = 0;
	private static int x = 0;
	
	//Card class constructor to assign a card the corresponding rank and suit.
	public Card(Ranks r, Suits s) {
		rank =  r;
		suit = s;
	}

	
	public void s(String s){
		
		switch(s.charAt(0)){
		case 'h':
			suit = Suits.HEARTS;
			break;
		case 's':
			suit = Suits.SPADES;
			break;
		case 'd':
			suit = Suits.DIAMONDS;
			break;
		case 'c':
			suit = Suits.CLUBS;
			break;
			default:	
		}
	}
	
	public void r(String r){
switch(r.charAt(0)){
		
		case 'A':
			rank = Ranks.ACE;
			break;
		case '2':
			rank = Ranks.DEUCE;
			break;
		case '3':
			rank = Ranks.THREE;
			break;
		case '4':
			rank = Ranks.FOUR;
			break;
		case '5':
			rank = Ranks.FIVE;
			break;
		case '6':
			rank = Ranks.SIX;
			break;
		case '7':
			rank = Ranks.SEVEN;
			break;
		case '8':
			rank = Ranks.EIGHT;
			break;
		case '9':
			rank = Ranks.NINE;
			break;
		case '1':
			rank = Ranks.TEN;
			break;
		case'J':
			rank = Ranks.JACK;
			break;
		case 'Q':
			rank = Ranks.QUEEN;
			break;
		case 'K':
			rank = Ranks.KING;
			break;
			default:		
		}
		// TODO Auto-generated constructor stub
	}
	public Card(String r, String s) {
		s(s);
		r(r);
	}



	//returns a short, three char, name for the card, ie " 3C", "10S" or " QH"
	public String getShortName() {
	
		return rank.getShortName() + suit.getShortName();
	}
	
	
	
	//accessor for the rank
	Ranks	getRank() {
		return rank;
	}
   
	

	//accessor for the suit
	Suits	getSuit() {
		return suit;	
	}
    
	
	/**
	* main method -- In this method I am testing all the functions of
	* the class Card to make sure that it works correctly.
	*
	* @param    args  command line arguments are ignored
	*/
	public static void	main(String[] args) {
				
		Card test = new Card(Ranks.ACE,Suits.HEARTS);
		
		System.out.println( "The Ranking of your Card is");
		System.out.println( test.getRank());
		
		System.out.println();
		System.out.println( "The Suit of your Card is");
		System.out.println( test.getSuit());
		
		System.out.println();
		System.out.println( "The value of your Card is");
		System.out.println( test.value());
		
		System.out.println();
		System.out.println( "The Long Name of your Card is");
		System.out.println( test.toString());
		
		System.out.println();
		System.out.println( "The Short Name of your Card is");
		System.out.println( test.getShortName());		
		
	}
    //main method for a test driver that should test all the methods in the class
	
	
	
	//returns a long name for the card, ie "THREE of CLUBS"
	public java.lang.String	toString() {
		return rank + " of " + suit;
	}
    
	
	// return the numerical value of the card
	int	value() {
		return rank.getValue();
	}
   
	
}
