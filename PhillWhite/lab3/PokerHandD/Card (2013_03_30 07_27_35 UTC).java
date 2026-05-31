/*
 * Card.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * A class to represent a playing card with a rank and suit
 *
 * @author paw: Phil White
 */

public class Card {

    private Ranks rank;
    private Suits suit;

   /**
    * Create a spesific card
    * 
    * @param    r	the Rank of the card to create
    * @param    s	the Suit of the card to create
    */
    public Card ( Ranks r, Suits s ){
        rank = r;
        suit = s;
    }

   public Card() {
	// TODO Auto-generated constructor stub
}

/**
    * return the numerical value of the card
    *
    * @return    the value of the rank of the card
    */
    public int value(){
        return rank.getValue();
    }

   /**
    * accessor for the rank
    *
    * @return    the Rank of the card
    */
    public Ranks getRank(){
        return rank;
    }

   /**
    * accessor for the suit
    *
    * @return    the Suit of the card
    */
    public Suits getSuit(){
        return suit;
    }

   /**
    * returns a long name for the card, ie "THREE of CLUBS"
    *
    * @return    the long name of the card
    */
    public String toString(){
        return rank + " of " + suit;
    }

   /**
    * returns a short, three char, name for the card, ie 
    * " 3C", "10S" or " QH"
    *
    * @return    the short name of the card
    */
    public String getShortName(){
        String res;

	res = rank.getShortName() + suit.getShortName();

        return res;
    }

   /**
    * main method for a test driver that should test all the methods
    * in the class
    *
    * @param    args	command line arguments
    */
    public static void main( String args[] ){
    	
    	Card r = new Card();
    	Card test = new Card(r.rank.ACE,r.suit.DIAMONDS);
		
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

} // Card