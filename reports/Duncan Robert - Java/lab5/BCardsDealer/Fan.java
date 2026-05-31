/*
 * Fan.java
 *
 * Version:
 * $Id: Fan.java,v 1.1 2013/04/17 05:34:40 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Fan.java,v $
 * Revision 1.1  2013/04/17 05:34:40  pzg8794
 * Time to submit the lab!
 *
 */


import java.util.*;

/**
 * Fan implements the Fan class
 * @author Aaron T Deever
 * @author Piter Garcia
 *
 */
public class Fan {
	
	/*
	 * Contains a reference to the dealer's cards methods.
	 */
	private Dealer cards;
	
	/*
	 * Contains the amount of money that a fan has.
	 */
	private static int	FAN_MONEY;
	
	/*
	 * reads in string from the command line.
	 */
	private Scanner in = new Scanner(System.in);
	
	
	/*
	 * Contains a set of the fans cards.
	 */
	private TreeSet<BaseballCard> fanCardsSet; 
	
	/*
	 * Contains the recent cards bought by the fan.
	 */
	private static Collection <BaseballCard> cardsBought;
	
	/*
	 * Contains all cards included repeated cards.
	 */
	private static LinkedList <BaseballCard> tempCards;
	
	/*
	 * Contains all cards that the user does not have.
	 */
	private static LinkedList <BaseballCard>dealerCards;
	
	
	
	/**
	 * Main method for Baseball card simulation
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		
		if(args.length != 0) { 
			System.out.println("Usage: java Fan");
			return;
		}
		//Fan.FAN_MONEY = Integer.parseInt(args[0]);
		
		Fan fan = new Fan();
		fan.simulate();
	}
	
	
	
	
	/**
	 * Method to simulate the interaction of a Fan and Dealer to buy/trade
	 *  cards
	 */
	public void simulate() { 
		
		boolean quit = false;
		
		do { 
			System.out.println();
			System.out.print("Options: buy (P)ack / buy (C)ard / (T)rade");
			System.out.println(" / (S)tatus / (Q)uit");
			System.out.print("Command: ");
			
			if(in.hasNext()) { 
				String cmd = in.next();
				switch(cmd.charAt(0)) { 
				case 'P':
				case 'p':
					purchasePack();
					break;
				case 'C':
				case 'c':
					purchaseCard();
					break;
				case 'T':
				case 't':
					makeTrade();
					break;
				case 'S':
				case 's':
					status();
					break;
				case 'Q':
				case 'q':
					quit = true;
					break;
				default:
					break;
				}
			}
			else { 
				return;
			}
			
		} while (!quit);
	}
	
	
	
  
    /**
	 * constructor, initializes the fan class variables and methods.
     */
	public Fan() {
		FAN_MONEY   = 50;
		cards       = new Dealer();
		cardsBought    = new LinkedList<BaseballCard>();
		tempCards   = new LinkedList<BaseballCard>();
		dealerCards = new LinkedList<BaseballCard>();
		fanCardsSet = new TreeSet<BaseballCard>();
		
		for( BaseballCard i: cards.getCompleteSet())
			dealerCards.add(i);
		
	}
	//Constructor
	 
	
	
	 /**
	  * This method takes a card from the user as well as the card requested by
	  * the user to trade them, it removes the fan's card from his list and
	  * adds the new card to the fan's list.
	  */
	 public void makeTrade(){
		String trade, receive;
		System.out.print("Input player you will trade: ");
		trade = in.next();
		System.out.print("Input player you will receive: ");
		receive = in.next();

		//trading cards
		if( cards.buyCard(receive) != null &&
				tempCards.contains(cards.buyCard(trade))){
				
				// repeated cards that the fan has
				tempCards.add(cards.trade(trade, receive));
				tempCards.remove(cards.trade(receive, trade));
				
				fanCardsSet.add(cards.trade(trade, receive));
				fanCardsSet.remove(cards.trade(receive, trade));
			
		}else {
			System.out.println("Trade not accepted or bad input: ");
		}
		 
	 }
     //Method to simulate a trade between Fan and Dealer.
	 
	 
	 
	 /**
	  * This method gets a Baseball player's name of any of the cards at the
	  * dealer, if the dealer has such card, the card is added to the fan's
	  * list and $3 dollars are deducted from his money account.
	  */
	 public void purchaseCard(){
		 
		 if(FAN_MONEY >=3){
			 System.out.print("Input name of player card to buy: ");
			 String cardsName = in.next();
			 FAN_MONEY -= 3;
			 if( cards.buyCard(cardsName)!=null){
				tempCards.add(cards.buyCard(cardsName));
				fanCardsSet.add(cards.buyCard(cardsName));
			 }else
				System.out.println(cardsName + " is not in the List");
		 }else{
			 System.out.println("Not enough money to buy a card ");
		 }
	 }
	 //Method to simulate a purchase of a card from the Dealer.	 
	 
	 
	 
	 /**
	  * This method gets five random Baseball cards and adds them to the 
	  * fan's list, then $10 dollars are deducted from his money account.
	  */
	 public void purchasePack(){
		 
		 if(FAN_MONEY >=10){
			 cardsBought.addAll(cards.buyPack());
			 for(BaseballCard i : cardsBought){
				 tempCards.add(i);
				 fanCardsSet.add(i);
			 }
			 FAN_MONEY -= 10;
			 System.out.println("Fan bought " + cardsBought);
			 cardsBought.clear();
		 }else{
			 System.out.println("Not enough money to buy a pack ");
		 }
	 }
	 //Method to simulate a purchase of a pack of cards from the Dealer.

	 
	 
	 /**
	  * This method gives the fan the status of the fan's money account,
	  * as well as the status of the list of Baseball cards that the fan
	  * has, does not have, and or are repeated.
	  */
	 @SuppressWarnings("unchecked")
	public void status() {
		
		Iterator<BaseballCard> itr = fanCardsSet.iterator();
		LinkedList<BaseballCard> temprmv = new LinkedList<BaseballCard>();
		TreeSet<BaseballCard> missingCards= new TreeSet<BaseballCard>();
		
//		fanCardsSet.addAll(tempCards);
		temprmv.addAll(tempCards);
		missingCards.addAll(dealerCards);
		while(itr.hasNext()){
			BaseballCard tp = itr.next();
			    missingCards.remove(tp);
				temprmv.remove(tp);
		}
		Collections.sort(temprmv);
		
		
		System.out.println("Fan has $"  + FAN_MONEY + " remaining");
		System.out.println("Fan has "   + fanCardsSet);
		System.out.println("Fan needs " + missingCards);
		System.out.println("Fan has extra: " + temprmv);
	 }
}