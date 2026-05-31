/*
 * BaseballCard.java
 *
 * Version:
 * $Id: BaseballCard.java,v 1.1 2013/04/17 05:34:41 pzg8794 Exp $
 *
 * Revisions:
 * $Log: BaseballCard.java,v $
 * Revision 1.1  2013/04/17 05:34:41  pzg8794
 * Time to submit the lab!
 *
 */


/**
 * A human and Computer player for 2-cards poker.
 *
 * @author pzg: Piter Garacia
 */
@SuppressWarnings("rawtypes")
public class BaseballCard implements Comparable {
	
   /*
	* Contains the number of the Baseball player in the card.
	*/
	private String name;
	
   /*
	* Contains the number hits of the Baseball player in the card.
	*/
	private int homeRun;
	
	
	
   /**
	* Constructor to initialize a BaseballCard instant.
	*/
	public BaseballCard(BaseballCard bc) {
		
	}
    //Constructor
	
	
	
   /**	
	* Constructor to initialize a Baseball Card name and home runs.
	*/	
    public BaseballCard(String name, int homers) {
    	
    	this.name = name;
    	homeRun = homers;
    }
    // Constructor

    
    
    /**
     * This method compares to Baseball Cards according the players home runs.
     *
     * @return 0, cards home runs are the same.
     * @return 1, this home run is greater than the card compared to.
     * @return -1, this home run is less than the card compared to.
     */
	@Override
	public int compareTo(Object bc) {
		if( this.getHomeRuns() > ((BaseballCard) bc).getHomeRuns())
    		return 1;
    	else if( this.getHomeRuns() < ((BaseballCard) bc).getHomeRuns())
    		return -1;
    	else
		return 0;
	}
    //Method to compare two BaseballCard objects based on home runs, then name

    
    
    /**
     * This method compares if two Baseball Cards are equal or not.
     *
     * @return true, if the two cards are equal.
     * @return false, if the two cards are not equal.
     */
	 public boolean equals(Object obj){
	        if (obj instanceof BaseballCard) {
	        	BaseballCard pp = (BaseballCard) obj;
	            return (pp.getPlayerName().equals(this.getPlayerName()) &&
	            		pp.homeRun == this.homeRun);
	        } else {
	            return false;
	        }
	 }
	//Method to test equality of two BaseballCard objects by name and home runs
	 
	 
	 
   /**
    * This method gets the number of home of a BaseballCard player.
    *
    * @return homeRun, the number of homes of a BaseballCard player.
    */ 
    public int	getHomeRuns(){
		return homeRun;
    	
    }
    //Method to get home runs of player associated with current BaseballCard
    //object

    
    
    /**
     * This method gets the name of a BaseballCard player.
     *
     * @return name, the name of a player in BaseballCard
     */
    public String	getPlayerName(){
		return name;
    	
    }
    //Method to get name of player associated with current BaseballCard object

    
    
    /**
     * This method gets the hashcode of key(name) of BaseballCard.
     *
     * @return getPlayerName().hashCode(), unique id of a BaseballCard
     * player's name.
     */
    public int	hashCode(){
		return this.getPlayerName().hashCode();
    	
    }
    //Method to compute hashCode for BaseballCard object

    
    
    /**
     * This method converts the BaseballCard object to string.
     *
     * @return this.getPlayerName()+":"+this.getHomeRuns(), the string
     * value of the name and homes of a BaseballCard object.
     */
    public String	toString(){
		return this.getPlayerName()+":"+this.getHomeRuns();
    	
    }
    //Method to convert a BaseballCard object to a String
}
