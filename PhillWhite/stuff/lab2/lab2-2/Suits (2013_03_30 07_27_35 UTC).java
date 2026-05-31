

/*
 * Suits.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * An enum representing the suits in a normal poker deck
 *
 * @author paw: AUTHOR_FULL_NAME_HERE
 */

public enum Suits { 
	CLUBS ( 'C' ), 
	DIAMONDS ( 'D' ) , 
	HEARTS ( 'H' ),
	SPADES ( 'S' );

   /**
    * a constant for the total number of suits
    */
    public static final int NUM_SUITS = 4;
    private final char shortName;

   /**
    * initialize the suit enums, 
    *
    * @param    n       short name for the suit
    */
    Suits( char n ){
	shortName = n;
    }

   /**
    * accessor for the name
    *
    * @return   a char with the short name for this suit
    */
    public char getShortName(){
	return shortName;
    }
    
    public static void	main(String[] args) {
    }
}
