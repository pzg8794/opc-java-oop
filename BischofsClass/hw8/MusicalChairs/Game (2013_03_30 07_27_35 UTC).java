
/*
 *  Created:      10/26/2012
 *  Last Changed: 10/28/2012
 *  
 *  Game.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */
import java.util.Random;



/**
 * This program creates a class called Semaphore
 * This is used to implement a musical chairs game.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 * 
 */  
public class Game extends LinkedList implements Runnable{
	
	private static int chairs;
	private static int i= 0;
	private static Object remove;
	private static int amountOfKids; 
	private static int kidCounter = -1;  
	private static Object kidName = new Object();
	private static LinkedList kids = new LinkedList();
	private static LinkedList winners = new LinkedList();


  
  //Constructor for the Game class that takes kid name as object
  public Game (Object object) {
      Game.kidName = object;
  }
 
  
  
  
  //Default constructor for the Game.
  public Game() {
  }
  
  
  

  /**
  * This method sets the number of chairs
  * equal to the number of kids-1
  */
  public void setChairs(){
  
	  chairs = amountOfKids -1;
  }
  
  
  
  /**
 * This method sets the number of kids
 *
 * @param   kids    number of kids
 */
  public int setAmountOfKids(int kids){
	  
	  amountOfKids = kids;
	  
	  return kids;
  }
  
  
  
  /**
  * This method sets the number of kids
  * This method calls the playtheGame if 
  * the chairs have been set
  */ 
  public void run(){
	  
		while(!allKidsPlaying()){
			theMusicPlays();
		}  
  }
  
  
  
  /**
  * This method checks the number of chairs
  * the boolean value play is set to true,
  * if all chairs have been laid, else its
  * set to false
  */   
  private boolean allKidsPlaying() {
	  boolean play = false;
	
	  if(kidCounter == chairs){
		  play = true;
	  }
	  else {
		play = false;
	  }
	
	  return play;
  }

  
  
  
  public static void setKidName(){
	  Random allKids = new Random();
	  kidName = allKids.nextInt(amountOfKids);
  }
  
  
  /**
  * This method is where the actual game is played.
  * from the list of kids, we randomly assign kids to 
  * a linked list. the size of linked list is number of kids-1
  * It also does not allow duplicates, so each kid gets a fair chance.
  * The kid that does not secure a place in the list(which acts as chairs)
  * is out
  */ 
  public static synchronized void theMusicPlays() {

	  setKidName();
	  if(!winners.contains(kidName) && kids.contains(kidName)){
		  try{
			  Thread.sleep(1);  
			  	
		  }
		  catch (InterruptedException ie){
			  System.out.println(ie.getMessage());
		  }
		 
		  if (kidCounter++ < chairs -1){
					
				winners.addFirst2(kidName);
		  }
		  else {
				System.out.println(kidName.toString() + " is out!");
				//System.out.println(kidName.toString() + " exit! ");
				remove = kidName;
				System.out.println();
				try{
					Thread.sleep(1);
					down();  
				}catch (InterruptedException ie){
					System.out.println(ie.getMessage());
				}
					
			}
	  }
	  else{	
		  theMusicPlays();
	  }
  }
  
  
  
  /**
   * The main program.
   *
   * @param    args    command line arguments taken as number of kids
   */
  public static void main(String[] args) {
		  
	  Game judge = new Game();
	  judge.gatheringKids(Integer.parseInt(args[0]));
	 	  
	  judge.setAmountOfKids(Integer.parseInt(args[0]));
	  judge.setChairs();
	  
	  try {
		  judge.creatingKidThreads();
	  } catch (InterruptedException e) {
		e.printStackTrace();
	  }
	  
	 // judge.sort(kids); 
	 // System.out.println();	  
  }
  
  
  
  /**
  * this method takes the number from command line
  * and adds that many kids to the list
  */
  public void gatheringKids(int args){
	  
	  int i = 0;
	  while( i != args){
		  
		  kids.addLast2(i);
		  i++;
	  }
	  System.out.println( kids.size() + " are playing ");
  }
  
  
  
  
  	/**
  	* This method takes the number from command line
  	* and creates a thread for each kid
  	*/
  	public void creatingKidThreads() throws InterruptedException{
	  
		Thread kidsThread = null;
	 	//System.out.println(" Kids Going to Play! ");
		int x = 0;
		for( i = 0 ; i != amountOfKids - 1; i++){
				
			kidsThread = new Thread ( new Game(kids.get(x)));
			kidsThread.start();
			try {
				kidsThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
			}
		//System.out.println(winners.get(kidCounter) + " got a chair");
		System.out.println("\n ok, and the winner is:" + winners.get(kidCounter));
  	}
  
  	
  	
  /**
  * once each round of the game completes,
  * this method removes the kid that fails 
  * to get a chair from the game. it also 
  * reduces the number of chairs from each 
  * round.
  */    	
  public static void down(){
	  
	  	kids.rmv(remove);
		chairs--;
		
		
		System.out.print("===");kids.sort(kids);
		if(kids.size() != 1)
		System.out.println("\n" + kids.size() + " are playing");
		
		
		  try
		  {
			  Thread.sleep(1);  
	 
		  }catch (InterruptedException ie)
		  {
			  System.out.println(ie.getMessage());
		  }
		
		
		while( winners.head.getNext() != null){
			
			winners.rmvFirst();
			kidCounter--;
			winners.head.getNext();
		}
		kidCounter = 0;
  } 
}