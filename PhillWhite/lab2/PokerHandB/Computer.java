/*
 * Poker.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.Random;
import java.util.Scanner;



/**
 * A Computer class that plays against the human player.
 * Every decision taken by this class depends on the card values access through
 * the class Card. Actions are taking by the class Poker which tells the winner.
 *
 * @author pg: Piter Garcia
 */
public class Computer {

	private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
	private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
	
	private Card[] cards;
	private int[] value;
	private int i = 0;
		
	private String[] output;
	private int x = 0;
	private static int r = 0;
	private static int r2=0;
	//Initalize a computer player for 2-card poker
	public Computer() {
		
		value = new int[3];
		cards = new Card[3];
		output = new String[3];
		
		for (int x=0; x<2; x++){
			cards[x] = null; //empty up cards[] array.
		}
	}
	
	
	
	//adds a card to the computer's hand
	public void addCard(Card dealCard) {
			
			Random generator = new Random(); 
		
		
			r = generator.nextInt(13);
			r2 = generator.nextInt(4);
			
				String rr = ranks[r];
				do{
					r = generator.nextInt(13);
					r2 = generator.nextInt(4);
				}while( rr != ranks[r]);
				
				
			dealCard =  new Card(ranks[r], suits[r2]);
			//c = new Card(c.getRank(),c.getSuit());
			
			
			switch(x){
			
			case 0:
				cards[0] =dealCard;
				value[0] = dealCard.value();
				output[0] = dealCard.getShortName();
				break;
			case 1:
				cards[1] =dealCard;
				value[1] = dealCard.value();
				output[1] = dealCard.getShortName();
				default:
			}
			
			x++;
			
			if( x == 2)
					x=0;
		
	} 

	
	
	//clears out all the cards for the computer
	public void newHand() {
		
		for (int x=0; x<cards.length; x++){
			cards[x] = null; //empty up cards[] array.
		}
		
	} 

	
	
	//prints the hand in some "nice" format
	public void printHand() {
		
		int i = 0;
		//while(cards[i] != null){
			
			System.out.print( output[0] + " " + output[1]);
			i++;
		//}
		System.out.print("\n");
		
	}

	
	/**
	* stand() -- This method determines whether the Computer player should fold or not.
	* This is decision is taken according to the Computer's Cards obtained from the
	* Class Card. if the Computer player gets a pair of cards with the same suit, or a 
	* pair of cards with the same rank, or one or two cards higher than 10 then the 
	* Computer player is said not to fold and stand to keep playing. Otherwise, the 
	* Computer player folds and loses the hand(round.)
	*
	* @param    boolean
	*/
	//determines if the computer should stand (vs fold).
	public boolean stand() {
		//int i = 0;
		//System.out.print(" asking pc \n");
		
		if( cards[0].getRank() == cards[1].getRank() ){
			return true;
			}
		else if (cards[0].getSuit() == cards[1].getSuit() ){
			return true;
			}
		else if (cards[0].value() > 11 || cards[1].value() > 11){
			return true;
			}
	
		return false;
	} 
	
	
	
	/**
	* main method -- In this method I am testing all the functions of
	* the class Computer to make sure that it works correctly.
	*
	* @param    args  command line arguments are ignored
	*/
	public static void	main(String[] args) {
	
		System.out.println( "== Dealing Cards\n" );
		
		Deck d = new Deck();
		Computer person = new Computer();
		char c;
		do{
			//give initial cards 
			for (int j=0; j<2; j++ ){
				person.addCard( d.dealCard() );
			}

			System.out.println( " \n ==============  Your Cards  ========" );
			person.printHand();
			System.out.println( " The value of your cards are " + person.value[0]
				+ " and " + person.value[1]);
			System.out.println( " Your total value is " + person.value() + "\n");

			// ask player if they want to stand
			
			if(person.stand() ){
				System.out.println( "You can Win" );
				
			}
			else {
				System.out.println( "You have lost" );
			}

			// have everyone throw in their cards
			person.newHand();
			System.out.print( "You have no cards \n" );
			
			System.out.print( "Do you wish to play " +
			    "another hand (y/n):" );
			
			Scanner in = new Scanner( System.in );
	    	String again;
	    	
	    	
			again = in.nextLine();
			again = again.toLowerCase();
			c = again.charAt( 0 );
			
		}while( c != 'n' );
		
		System.out.println( "Game Over! \n" );
	//main method for a test driver that should test all the methods in the class	
	}
    
	
	
	
	//This function must come up with a single integer that represents the value of the hand.
	public int	value() {
		
		int totalValue = 0;
		
		for (int i=0; i<2; i++){
			
			value[i] = cards[i].value();
			totalValue += value[i];
		}
		
		return totalValue;
    }
    }
