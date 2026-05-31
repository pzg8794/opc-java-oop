/*
 * Card.java
 *
 * Version:
 * $Id: Card.java,v 1.1 2013/06/11 02:24:23 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Card.java,v $
 * Revision 1.1  2013/06/11 02:24:23  pzg8794
 * Lab2 done!
 *
 */

/**
 * A class to represent a playing card with a rank and suit
 *
 * @author pzg: Piter Garcia
 */

public class Card {

	/*
	 * a constant for the total number of ranks
	 */
    private Ranks rank;
    
    /*
     * a constant for the total number of suits
     */
    private Suits suit;

    
    
   /**
    * Create a specific card
    * 
    * @param    r - the Rank of the card to create
    * @param    s - the Suit of the card to create
    */
    public Card ( Ranks r, Suits s ){
        rank = r;
        suit = s;
    }

    
    /**
     * default constructor
     */
   public Card() {

   }



/**
    * return the numerical value of the card
    *
    * @return the value of the rank of the card
    */
    public int value(){
        return rank.getValue();
    }
    

    
   /**
    * accessor for the rank
    *
    * @return the Rank of the card
    */
    public Ranks getRank(){
        return rank;
    }

    
    
   /**
    * accessor for the suit
    *
    * @return the Suit of the card
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
    * @return   the short name of the card
    */
    public String getShortName(){
        String res;

        res = rank.getShortName() + suit.getShortName();

        return res;
    }

    
    
   /**
    * main method for a test driver that should test all the
    * methods in the class
    *
    * @param    args	command line arguments
    */
    public static void main( String args[] ){
    	
    	Card test = new Card(Ranks.ACE, Suits.DIAMONDS);
		
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