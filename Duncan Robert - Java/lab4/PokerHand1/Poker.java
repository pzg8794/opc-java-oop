/*
 * Poker.java
 *
 * Version:
 * $Id: Poker.java,v 1.1 2013/04/07 18:45:29 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Poker.java,v $
 * Revision 1.1  2013/04/07 18:45:29  pzg8794
 * Poker Game
 *
 */

import java.io.IOException;
import java.util.Scanner;

/**
 * A 2-card poker game played between human and a computer players
 *
 * @author pzg: Piter Garcia
 */

public class Poker{

  /*
   * Initialize a human player for 2-card poker
   */
   private static Scanner in = new Scanner(System.in);
   
   
  /**
   * a boolean toggle that tells the order of making the stand/fold
   * decision.  This flips after each hand
   */

	
	
  /**
   * constructor
   */
   public Poker(Scanner inScan) {
      // super(inScan);
   }



   /**
    * main method -- plays multiple hands of poker, after each hand
    * ask the user if they want to play again.  We also keep trak of
    * the number of games played/won by the user and print the results
    * at the end.
    *
    * @param    args      command line arguments
    * @throws IOException 
    */
    public static void main( String args[] ) throws IOException{
    	char res = ' ';
    	Deck d = new Deck();
    	Player[] p = new Player[Player.playersOnTheTable()];
    	p = Player.createPlayers(p);
   
    	int numGames = 0;
    	do {
            ++numGames;

            System.out.println();// printing title for new hand.
    	    System.out.println( "##########################################" );
    	    System.out.println( "##########       NEW HAND      ###########" );
    	    System.out.println( "##########################################" );
    	    System.out.println( "\n== Shuffling" );
    	    System.out.println( "== Dealing Cards" );
    	    
    	    Player.newHand(p);
    	    d.shuffle();
    	     
    	  	for( Player p1: p){ // adding 2 cards.
    	  		for (int j=0; j<2; j++ ){
    	  			p1.addCard( d.dealCard() );
    	  		}
        	}
    	  	int i=0;
        	for( Player p1: p){ // displaying human cards.
        		System.out.println("\nPlayer #"+ (++i));
        		if( p1.getName().charAt(0) == 'H'){
        			System.out.println("==============" +
				p1.getName() + ", Your Cards  ========" );
        			p1.printHand();
        		}
        		p1.stand();// asking player to stand or fold.
        	}

        	Player.setPlayers(p.length);
        	Player[] winners = Player.getWinner(p);//getting winners.
        	for( Player p1: winners){
        		p1.printWinner();// printing winner hand
        	}
          	p = Player.orderPlayers(p); //ordering players 
                
		do{
          	    System.out.print("Do you wish to play another hand (y/n):");
          	    res = in.next().toLowerCase().charAt(0);
                }while( res!='y' && res!='n');

          	}while( res =='y' );// playing new poker hands till user says no.
                //result table
    		System.out.println( "\n\n========== Hand Results ==========" );
    		System.out.println( "Games Played:\t " + numGames );
    		System.out.println( "----------------------");
    		System.out.println( "Winer(s)  ID |  Win(s)");
    		System.out.println( "----------------------");
    		System.out.println(Player.getAllWinners());
    	   	System.out.println( "Hand Ties   :\t " + Player.getTie());
    	   	Player.setTie(0);// ties to 0 to use it for game winner.
        	System.out.println( "\n========== Game Results ==========" );
        	System.out.println( "Games Played:\t " + numGames );
        	System.out.println( "----------------------");
    		System.out.println( "Winer(s)  ID |  Win(s)");
    		System.out.println( "----------------------");
    		System.out.println(Player.getWinner());
    		System.out.println( "Game Ties   :\t " + Player.getTie());
    }
} 
