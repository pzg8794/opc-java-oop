/*
 * Human.java
 *
 * Version:
 * $Id: Player.java,v 1.1 2013/04/07 18:45:29 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Player.java,v $
 * Revision 1.1  2013/04/07 18:45:29  pzg8794
 * Poker Game
 *
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A human and Computer player for 2-cards poker.
 *
 * @author pzg: Piter Garacia
 */
 public abstract class Player {

   /*
    * Contains the number of wins that each player has.
    */
    private int win;

   /*
    * contains the number of ties in hand, and also in a game.
    */
    private static int tie;
	
   /*
    * to check whetHer a player stands or folds.
    */
    private boolean stand= true;
	
   /*
    * Contains winner(s) of a game.
    */
    private static Player[] winners;
	
   /*
    * contain winner(s) of a hand.
    */
    private static LinkedList<Player> allWinners = new LinkedList<Player>();
	
   /*
    * assigns every human player an id.
    */
    private static int humanId = -1;
	
   /*
    * assigns every computer player an id.
    */
    private static int computerId = -1;
	
   /*
    * contains every player's name
    */
    private String playersName = "";

   /*
    * contains players cards
    */
    private PokerHand myCards;
    
   /*
    * retrives input from the command line for user purpose 
    */
    protected static Scanner in = new Scanner(System.in);

    private int value = 0;
    private static int numOfPlayers = 0;

    public static void setPlayers(int x){
    	numOfPlayers = x;
    }
    
   /**
    * Constructor to initilize players cards
    */
    public Player(){
    	myCards = new PokerHand();
    }

    
    
   /**
    * Constructor to initialize the winner players when they are stock for 
    * comparison purpose.
    */
    public Player ( String name, int value ){
     	playersName = name;
     	this.value =  value;
    }
     
    
     
     
   /**
    * This method gets user input from the command line to set the amount of
    * of players that is said to play in the poker game..
    *
    * @return in.nextInt(); the number of players in the game.
    */
    public static int playersOnTheTable() {
  		
  	do{		
  		System.out.print("How many players for this poker game   : ");
  		numOfPlayers = in.nextInt();	
  			
  		if( numOfPlayers==0){
  			System.out.println("Sorry!\nYou Missed a Good Game!\n");
  			System.exit(0);
  		}
  			
  		if(numOfPlayers <2){
  			System.out.println("Wrong Input!\n Please Enter At "
			+ "Least 2 + Players or 0 To Quit");	
  		}
  			
  		}while(numOfPlayers < 2);
  		
  	return numOfPlayers ;
    }
      
     
     
   /**
    * This method creates a list o human and or computer players according to
    * the user specifications.
    *
    * @return p, list of human and or computer players.
    */
    public static Player[] createPlayers(Player[] p) {
 	  char c;
 	  Integer[] x = new Integer[p.length];
 		
 	      for(int i =0; i<p.length; i++){
 			
 		   do{
 			System.out.print("Is player #"+(i+1)+" a human or a"+
 						        " computer (h/c): ");
 			c = in.next().toLowerCase().charAt(0);		

 			if(c =='h'){
 				x[i] = 0;
 			}
 			if( c == 'c'){
 				x[i] = 1;
 			}
 			}while( c!= 'h' && c!= 'c');
 		}
    	
 		for(int i = 0; i<p.length ; i++){ //adding a human player
 			if(x[i]%2 ==0){
 				p[i] = new Human();
 				p[i].setName("Human    #"+p[i].setHumanID());
 				//numOfPlayers++;
 		
 			}else{ //adding a computer player
 				p[i] = new Computer();
 				p[i].setName("Computer #"+p[i].setComputerID());
 				//numOfPlayers++;
 			}	
 		}
 		return p;
    }
    
    
    
    /**
     * This method set the name of each player, whether is a human or computer
     * also the name of the player is appended with the player's id.
     */
     public void setName(String string) {
	      playersName = string;
			
     }

	
	
    /**
     * This method sets the only a human ID player to then be appended to the
     * human player's name when setting the player's name.
     *
     *@return humanId, this is a unique  number assigned to a human player.
     */
     public int setHumanID() {
	      humanId++;
	  return humanId;
     }

	
	
    /**
     * This method sets the only a computer ID player to then be appended to the
     * human player's name when setting the player's name.
     *
     *@return computerId, this is a unique  number assigned to a computer player.
     */
     public int setComputerID() {
	      computerId++;
	  return computerId;
     }
    
    
    
    /**
     * adds a card to the human's hand
     *
     * @param c  the card to add
     */
     public void addCard(Card c){
    	  myCards.addCard(c);
     }

   
   
    
    /**
     * prints the hand in some "nice" format
     */
     public void printHand(){
    	  myCards.printHand(); 
     }
    
    
    
    /**
     * Asks the player if they want to stand.  It prompts the Human
     * player with a suitable message, and then read the players response
     * from standard input. On the other hand, the computer player decides
     * whether it folds or not, when the number of players is more than two,
     * algorithmically.
     *
     * @return  a boolean value specifying if the human or computer stands
     */
     public boolean stand(){
    		Human pp = new Human();
    		Computer pp1 = new Computer();
    		
    		if( this.getName().charAt(0) == 'C'){
		        //computer can fold only if players are 2 or more.
    			if(!pp1.stand(this.myCards) && numOfPlayers>1){
    				this.stand = false;
    				System.out.println(this.getName() + " Folds");
    				numOfPlayers--;
    			}
    			else{
    				System.out.println(this.getName() + " Stands");
    				this.stand = true;
    			}
    		}
    		else{
    			if( numOfPlayers <= 1)
    				return true;
    			else{
    				this.stand = pp.stand(in); 
    				if(!this.stand)
    					numOfPlayers--;
    			}
    		}
    		return this.stand;
     }
    
    
    
    
    /**
     * It checks in the variable allWinners for the winner players and it
     * displays them all for the user tosee the hand and game winner players.
     *
     * @return winner, contains the winner(s) of a hand and or a game.
     */
     public static String getAllWinners(){
    	 String winner = " ";
    	 for( Player p: allWinners){
    		winner += p.getName() + " |   " + p.getValue() + "\n "; 
    	 }
    	return winner;
     }

    
    
    
    /**
     * This method first goes through the list of players to separate apart 
     * those players that decided to fold, then it goes through the list once
     * again to compare players card values and determine a/or winner(s) of 
     * a poker hand and or game.
     *
     * @return winner, a list of only winning players.
     */
     public static Player[] getWinner(Player[] players) {
 	 LinkedList<Player> loosers = new LinkedList<Player>();
 	 LinkedList<Player> looser = new LinkedList<Player>();
 				
 	 int i=0; // displaying human or compure cards before determining a/or 
 	 // hand or game poker winner(s).
 	 System.out.println("\n************  RESULTS *****************");
 	 for( Player p1: players){
 	        System.out.println("\nPlayer #"+ (++i));
 	        System.out.println("--------------" + p1.getName() 
 			+ ", Your Cards  --------" );
 	                p1.printHand();
 	        }
 	        	
 	  for(Player p: players) // a compute of the players
 		loosers.addFirst(p);
 				
 		int x = 0;
 		while(x != loosers.size()){
 		// checking for folded players to include in the cards' compares.
 			if( !loosers.get(x).stand){
 				loosers.remove(loosers.get(x));
 				x--;
 			}
 			x++;
 		}
 		looser.addAll(loosers);//creating a copy of the players standing.
 		int ties = 0;
 					
 		while(loosers.size() >=2){ //comparing cards till 2 players left
 		        if(loosers.getFirst().myCards.value()<
 			     loosers.getLast().myCards.value()){
 			     loosers.removeFirst();
 			     loosers.addFirst(loosers.removeLast());
 			 }
 			 else
 			     loosers.removeLast();
 		}
 		// removing winning player from the copy of standing players.
 		if(!loosers.isEmpty())
 			looser.remove(loosers.getFirst());
 			// comparing winner and standing players to check for tie hands
 		for( Player p : looser){
 			if(p.myCards.value()==loosers.getFirst().myCards.value()){
 				loosers.add(p);
 				ties++;
 			}
 		}
 		setTie(ties);// set the number of ties in a poker hand.
 				
 		int i1=0;
 		winners = new Player[loosers.size()];
 		Iterator<Player> itr1 = loosers.iterator();
 		while(itr1.hasNext()){
 			winners[i1] = itr1.next();
 			winners[i1].setwin();
 			for( int x1 = 0; x1 < allWinners.size(); x1++){
 			    if (allWinners.get(x1).getName().equals(winners[i1].playersName))
 				   allWinners.remove(allWinners.get(x1));
 			}//adding all winners in a list for fure display and game winner.
 			allWinners.add(new Winner(winners[i1].playersName, 
 			winners[i1].getWin()));
 			i1++;
 		}	
 		//System.out.println("Tie Hand = "+ ties);
 		return winners;
 	}
	
  

    
   /**
    * This method orders the players in sequence so the last player in the 1st
    * hand is the first player in the 2nd hand.
    *
    * @return newOrder,list of human and or computer players ordered differently
    */
    public static Player[] orderPlayers(Player[] players) {
		Player[] newOrder = new Player[players.length];
		int i = 0;
		for(i=0; i<players.length-1;i++){
			newOrder[i+1] = players[i];
		}
		newOrder[0] = players[i];
		return newOrder;
    }

    
    
   /**
    * This function must come up with a single integer that represents the
    * value of the hand.  You want the value to work such that a higher
    * hand has a higher value. So the values should fall from highest
    * to lowest as:
    * <ul>
    *   <li>pair of Aces
    *   <li>pair of Kings
    *   <br>...
    *   <li>pair of Twos
    *   <li>Ace/King flush (the same suit)
    *   <li>Ace/Queen flush
    *   <br>...
    *   <li>three/two flush
    *   <li>Ace/King high card (not the same suit)
    *   <li>Ace/Queen high card
    *   <br>...
    *   <li>Three/Two high card
    * </ul>
    *
    * @return  the integer representing the value of the hand
    */
    public int value() {
	return myCards.value();
    }

    
    
   /**
    * Compares the humans hand with the specified computers hand for order. 
    * Returns a:	<table BORDER="1" CELLPADDING="1" CELLSPACING="1">
    *		  <tr><td>negative integer<td>player hand < computers hand	
    *		  <tr><td>zero<td>player hand == computers hand	
    *		  <tr><td>positive integer<td>player hand > computers hand	
    *		</table>
    *
    * @return	a negative integer, zero, or a positive integer as the
    *		human is less than, equal to, or greater than the computer
    */
    public int compareTo( Player d ){
	return myCards.value() - d.value();
    }

	
    
   /**
    * This method increase the number of winning of each player by one 
    * whenever the player has won a hand.
    */
    public void setwin() {
	   win++;	
    }
     
	
	
	
    /**
     * This method returns the winning numbers that a player has got. 
     * @return win, number of winning of a player.
     */
     public int getWin() {
	  return win;
     }



    /**
     * This method gets winner of a poker game.
     *
     * @return Player.getAllinners(), it contains the winner(s) of all poker
     * hands played.
     */
     protected static String getWinner() {
           
   		LinkedList<Player> winner = new LinkedList<Player>();
   		winner.addAll(allWinners);//copying winners to another list.
   		int ties = 0;
   		//comparing cards till only two players are left.
   		while(allWinners.size() >=2){
   			if( allWinners.getFirst().getValue() < 
   			allWinners.getLast().getValue() ){
   				allWinners.removeFirst();
   				allWinners.addFirst(allWinners.getLast());
   			}
   			else{
   				allWinners.removeLast();
   			}
   		}
   		winner.remove(allWinners.getFirst());
		//removing the obtained winner from copy.
   		//comparing if any of the previous winners have the same cards
		//as the winner.
   		for( Player p: winner){
   			if(p.getValue() == allWinners.getFirst().getValue()){
   				allWinners.add(p);
   				ties++;
   			}
   		}
   		setTie(ties);
 		
   		return Player.getAllWinners();
     }
       
       
       
    /**
     * This method gets the number of tie hand(s) and or poker game(s).
     *
     * @return tie, number of poker hand(s) and or game(s) tie.
     */
     public static int getTie() {
   		return tie;
     }
       
       
       
    /**
     * This method sets the number of tie hand(s) and or poker game(s).
     */
     public static void setTie(int tie) {
   		Player.tie = tie;
     }
    
    
    
    /**
     * This method gets the name of a human or computer player.
     *
     * @return  playersName, contains the name of a human or computer player.
     */
     public String getName() {

    	return playersName;
     }

    
    
    
    /**
     * This method gets the cards value of a winner player.
     *
     * @return  value, cards value of a winner player.
     */
     public int getValue() {

    	return value;
     }
    
    
    
    
    /**
     * This method prints a human and or computer player card.
     */
     public void printWinner() {
	    System.out.println("**************************************");
	    this.printHand();
	    System.out.println("**** " + this.getName() + " Won ****");
     }


    
	
    /**
     * This methods creates a new hand for human and or computer players.
     */
     public static void newHand(Player[] players) {
		  for( Player p: players)
			   p.myCards = new PokerHand();
			
     }
	
	
    
    /**
     * main method for a test all player methods for human and or computer
     * players.
     *
     * @param    args    command line arguments
     */
     public static void main( String args[] ){
          char res = ' ';
    	  Deck d = new Deck();
    	  Player[] p = new Player[playersOnTheTable()];
    	  p = createPlayers(p);
   
    	  int numGames = 0;
    	  do {
    	    numGames = numGames + 1;

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
        	
        	setPlayers(p.length);
        	Player[] winners = Player.getWinner(p);//getting winners.
        	for( Player p1: winners){
        		p1.printWinner();// printing winner hand
        	}
          	p = Player.orderPlayers(p); //ordering players 
    
          	System.out.print("Do you wish to play another hand (y/n):");
          	res = in.next().toLowerCase().charAt(0);

          	}while( res =='y' );// playing new poker hands till user says no.
                //result table
    		System.out.println( "\n\n========== Hand Results ==========" );
    		System.out.println( "Games Played:\t " + numGames );
    		System.out.println( "----------------------");
    		System.out.println( "Winer(s)  ID |  Win(s)");
    		System.out.println( "----------------------");
    		System.out.println(Player.getAllWinners());
    	   	System.out.println( "Hand Ties   :\t " + getTie());
        	setTie(0);// ties to 0 to use it for game winner.
        	System.out.println( "\n========== Game Results ==========" );
        	System.out.println( "Games Played:\t " + numGames );
        	System.out.println( "----------------------");
    		System.out.println( "Winer(s)  ID |  Win(s)");
    		System.out.println( "----------------------");
    		System.out.println(Player.getWinner());
    		System.out.println( "Game Ties   :\t " + getTie());
    		
     }
}
