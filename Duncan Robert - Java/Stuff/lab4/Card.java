/*
 * Card.java
 *
 * Version:
 * $Id: Card.java,v 1.1 2013/04/07 18:45:30 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Card.java,v $
 * Revision 1.1  2013/04/07 18:45:30  pzg8794
 * Poker Game
 *
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
    }

} // Card