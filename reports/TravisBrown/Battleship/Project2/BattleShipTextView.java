/*
 * BattleShipTextView.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log: 
 *
 */



/**
 * 
 * @author piter garcia
 *
 */
public class BattleShipTextView {

	/*
	 * number of times that user has hit a cell
	 */
	private int tries;
	
	/*
	 * number of times that user has hit a ship cell
	 */
	private int hit;
	
	/*
	 * number of ship that has sink
	 */
	private int sink;
	
	/*
	 * contains the status of the game
	 */
	private String status;

	
	
	
	/**
	 * constructor to initialize the view of the battleship game
	 * @param gameModel
	 */
	public BattleShipTextView(BattleShipModel gameModel) {
		tries = 0;
		hit = 0;
		sink = 0;
	}
	
	
	
	/**
	 * increments the number of tries 
	 */
	public void tryies(){
		tries++;
	}
	
	
	
	/**
	 * increments the number of ships hit
	 */
	public void hit(){
		hit++;
	}
	
	
	
	/**
	 * gets the updated message of the ships hit
	 * @return
	 */
	public String getHits() {
		
		if(hit <10)
			return("             Hits : "+"0"+hit);
		else
			return("             Hits : "+hit);
	}

	
	
	
	/**
	 * gets update message of the number of try
	 * @return
	 */
	public String getTries() {
		return " Number of Tries is : "+ tries;
	}

	
	
	
	/**
	 * resets the view of the game.
	 */
	public void reset() {
		
		tries = 0;
		sink = 0;
		hit = 0;
	}

	
	
	
	/**
	 * gets help to inform the user how to play the game
	 * @return
	 */
	public String getHelp() {
		String help = "Pick A Cell Where You Think A Ship Is Located\n" +
				"Then Click On It, If You Guess Correctly All Cell Where" +
		"The Ships Are Located. Then You Win The Game.";
		return help;
	}


	
	
	/**
	 * gets the updated message of the ships that has sink
	 * @return
	 */
	public String getSink() {
		if(sink < 10)
			return" Ships Shink : 0"+ sink;
		else
			return" Ships Shink : "+ sink;
	}


	
	
	/**
	 * increments the number of ships that has sink
	 */
	public void sink() {
		sink++;
		
	}


	
	/**
	 * updates the board according its current status
	 * @param s, cell selected
	 * @param i, level of status.
	 */
	public void status(Cell s, int i) {
		
		switch(i){
			
		
		case 1:
			status = "The Ship "+ s +" Has Sink";
			System.out.println("The Ship "+ s +" Has Sink");
			break;
		case 2:
			status = status + " Try Another Cell" ;
			break;
		case 3:
			status = "This Cell was Already Hit";
			break;
		case 4:
			status = "Missed";
			break;
		case 5:
			status = "You Have Won!";
			break;
		case 6:
			status = "You Have Hit Ship " + s;
			break;
		default:
			status = "";
			
		}
		
	}


	
	
	/**
	 * gest the current status of the board.
	 * @return
	 */
	public String getStatus() {
		return status;
	}

}
