/*
 * Poker.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


/**
 * A Human class that plays against the Computer player.
 * Every decision taken by this class depends on the card values access through
 * the class Card. Actions are taking by the class Poker which tells the winner.
 *
 * @author pg: Piter Garcia
 */
public class Human{

	private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
	private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
	
	private Card[] cards;
	private int[] value;
	
	private String[] output;
	private int x = 0;
	private static int r = 0;
	private static int r2=0;
	
	//Initialize a human player for 2-card poker
	public Human(){
		
		value = new int[3];
		cards = new Card[3];
		output = new String[3];
		
		for (int x=0; x<cards.length; x++){
			cards[x] = null; //empty up cards[] array.
		}
		
		//System.out.println( Suits.values());
			
	}
    

	//adds a card to the human's hand
	public void	addCard(Card c) {
		Random generator = new Random(); 
	
	
		r = generator.nextInt(13);
		r2 = generator.nextInt(4);
		
			String rr = ranks[r];
			do{
				r = generator.nextInt(13);
				r2 = generator.nextInt(4);
			}while( rr != ranks[r]);
			
			
		c= new Card(ranks[r], suits[r2]);
		//c = new Card(c.getRank(),c.getSuit());
		
		
		switch(x){
		
		case 0:
			cards[0] =c;
			value[0] = c.value();
			output[0] = c.getShortName();
			break;
		case 1:
			cards[1] =c;
			value[1] = c.value();
			output[1] = c.getShortName();
			default:
		}
		
		x++;
		
		if( x == 2)
				x=0;
	}
   
	
	
	
	//Compares the humans hand with the specified computers hand for order.
	int	compareTo(Computer d) {
		
		int i= 0;
		
		while(cards[i] != null){ //cycle through values
		
			if( this.value()  < d.value() )
				return -1;
		
			if( this.value()  > d.value() )
				return 1;
			i++;
		}
			
		return 0;
	}
    
	
	
	
	/**
	* main method -- In this method I am testing all the functions of
	* the class Human to make sure that it works correctly.
	*
	* @param    args  command line arguments are ignored
	*/
	public static void	main(String[] args) {
		
		
		System.out.println( "== Dealing Cards\n" );
		
		Deck d = new Deck();
		
		Human person = new Human();
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
	}
	// main method for a test driver that should test all the methods in the class
	
	
	
	
	// clears out all the cards for the human
	public void	newHand() {
		
		//int x = 0;
		
		for (int x=0; x<cards.length; x++)
		{
			cards[x] = null; //fill up cards[] array.
		}
	}
   
	
	
	 //prints the hand in some "nice" format
	public void	printHand() {
		//int i= 0;
		
		//while( i < 2){
			System.out.print( output[0] + " " + output[1]);
			//i++;
		//}
		System.out.print("\n");
	}
   
	
	/**
	* stand() -- This method ask the user whether he wants to stand or fold.
	* If the user decides to fold, then the user lose the game and the computer wins.
	* If the user decides to stand, then the user's cards are compare with the computer's
	* cards and whoever has the best cards wins.
	*
	* @param    boolean
	*/
	public boolean	stand() {
		
		Scanner in = new Scanner( System.in );
		boolean stands = true;
    	String again;
    	String c;
    	
			System.out.print( "Do you wish to stand (y/n):" );
			again = in.nextLine();
			again = again.toLowerCase();
			c = again;
		
			switch(c.charAt(0)){
		
		case 'y':
			stands = true;
			break;
			
		case 'n':
			stands = false;
			break;
			
		default:
			System.out.println( "Wrong Answer, please try again" );
			stand();
		}
	   
		return stands;
	}   // Asks the player if they want to stand.
	
	
	
	
	// This function must come up with a single integer that represents the value of the hand.
    public int	value(){
		
		int totalValue = 0;
		int i = 0;
		
		while(cards[i] != null){
			
			value[i] = cards[i].value();
			totalValue += value[i];
			i++;
		}

		return totalValue;
	}
   
}
