/*
 * Dealer.java
 *
 * Version:
 * $Id: Dealer.java,v 1.1 2013/04/17 05:34:40 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Dealer.java,v $
 * Revision 1.1  2013/04/17 05:34:40  pzg8794
 * Time to submit the lab!
 *
 */


import java.util.*;

/**
 * Dealer implements the Dealer class
 * @author Aaron T Deever
 * @author Piter Garcia
 *
 */
public class Dealer {
	
	/* Dealer stores information about the baseball cards contained in this
	 * special edition.
	 */
	private HashMap<String, Integer> playerData = 
			new HashMap<String, Integer>();
	
	
	
	 /**
	  * Method to create and return a new BaseballCard object corresponding to
	  * a specific player.
      *
      * @return new BaseballCard(player, playerData.get(player)),
      * a new BaseballCard.
	  */
	 public BaseballCard buyCard(String player) {
	
		 if(playerData.containsKey(player))
			 return new BaseballCard(player, playerData.get(player));
		 else
		return null;
	 }
	 
	 
	 
	 /**
	  * Method to create a Collection of BaseballCard objects representing
	  * a pack.
	  *
	  * @return pack, a collection of BaseballCard of five random cards.
	  */
	 public Collection<BaseballCard> buyPack() {
		 
		 List <BaseballCard> card = null;
		 card = new LinkedList<BaseballCard>(getCompleteSet()); 
		 
		 List <BaseballCard> pack= new LinkedList<BaseballCard>(); 
		 Collections.shuffle(card);
		// System.out.println(card);
		 
		 for( int i=0; i< 5 ; i++ ){
		   	pack.add(card.get(i));
			//System.out.println(card.toArray()[i]);
		 }
		// System.out.println(pack);
		return pack; 
	 }
	 
	 
	 
	 /**
	  * Method to create a Collection of BaseballCard objects representing
	  * a pack.
	  *
      * @return card, an entire collection of the playerData list.
	  */
	 public Collection<BaseballCard> allCards() {
		 
			List <BaseballCard> card = new LinkedList<BaseballCard>(); 
	
			Iterator<?> itr = playerData.entrySet().iterator();
			
			while(itr.hasNext()){
				card.add(buyCard(itr.next().toString().split("=")[0]));
			}
			// System.out.println(card);
			return card; 
	}
	
	 
	 
	 
	 /**
	  * Method to create a collection of the entire special edition of cards
	  * that can be used as a basis of comparison (e.g.
	  *
	  * @return in.nextInt(); the number of players in the game.
	  */
	 public Collection<BaseballCard> getCompleteSet() {
			List <BaseballCard> card = new LinkedList<BaseballCard>(); 
			
			Iterator<?> itr = playerData.entrySet().iterator();
			
			while(itr.hasNext()){
				card.add(buyCard(itr.next().toString().split("=")[0]));
			}
			// System.out.println(card);
			return card; 
	 }
     //Method to create a collection of the entire special edition of cards
	 //that can be used as a basis of comparison (e.g.
	 
	 
	 
	 /**
	  * Method to "trade" one BaseballCard for another.
	  *
	  * @return buyCard(dealerPlayer), a new Baseball Card.
	  */
	 public BaseballCard trade(String fanPlayer, String dealerPlayer){
		return buyCard(dealerPlayer);
	 }
    
     
	
	
	/**
	 * Constructor
	 */
	public Dealer() { 
		playerData.put("Bonds", 762);
		playerData.put("Aaron",  755);
		playerData.put("Ruth", 714);
		playerData.put("Mays",  660);
		playerData.put("Rodriguez",  647);
		playerData.put("Griffey", 630);
		playerData.put("Thome",  612);
		playerData.put("Sosa", 609);
		playerData.put("Robinson", 586);
		playerData.put("McGwire", 583);
	}
	
	
	
    /**
	 * This method gets user input from the command line to set the amount of
	 * of players that is said to play in the poker game..
	 *
	 * @return in.nextInt(); the number of players in the game.
	 */
	public static void main( String[] args){
		
	}


	/**
	 * This method gets the actual playerData list.
	 *
	 * @return playerData, current list of a playerData.
	 */
	public HashMap<String, Integer> getPlayerData() {
		return playerData;
	}



	/**
	 * sets playerData list to a new playerData list.
     */
	public void setPlayerData(HashMap<String, Integer> playerData) {
		this.playerData = playerData;
	}
}