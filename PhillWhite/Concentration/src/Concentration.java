/*
 *  Created:      01/31/2012
 *  Last Changed: 01/31/2012
 *  
 *  Concentration.java 
 * 
 *  Version:
 *     $Id: Concentration.java,v 1.2 2013/04/23 00:25:58 pzg8794 Exp $
 *
 *  Revisions:
 *     $Log: Concentration.java,v $
 *     Revision 1.2  2013/04/23 00:25:58  pzg8794
 *     *** empty log message ***
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;       



/**
 * Concentration - simulates a gameis a card game in which all of 
 * the cards are laid face down on a surface and two cards are flipped 
 * face up over each turn. The object of the game is to turn over every 
 * pairs of matching cards. 
 *
 * @author  Wander Bravo	ID :  wmb1306 	RIT ID : 110006833
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 * @param <JFrame>
 */
@SuppressWarnings("serial")
public class Concentration extends JFrame{
	
	public static JLabel message;
	public static JButton actual1;
	public static JButton actual2;
	public static JPanel gamePanel = new JPanel();
	public static JButton[] bucks = new JButton[16];
	
	public static int counter;
	public static int valueActual1;
	public static int valueActual2;
	public static final int MAX_BUCK = 16;
	public static int[] game = new int[MAX_BUCK];

	
	
    /**
    * Constructor to initialize the private parameters as well as set
    * the size of the pane framework. Add components and defines Close 
    * bottom operation.
    *
    * @param       String title
    */
    public Concentration(String title) {
        // set title in JFrame class
		super(title);
        setSize(500, 500);
        setLocation(100, 100);        
        addComponentsToPane(this.getContentPane());
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    
    /**
    * addComponentsToPane(Container pane), set the layout of the pane
    * framework obtained from the JFrame function. 
    * Set and add the string for the JLabel to display. 
    * Add the head and layout type on the pane. Add listeners, and
    * randomly add the card values.
    *
    * @param      JPanel, JFrame
    */
    public static void addComponentsToPane(Container pane) {
					
		pane.setLayout(new BorderLayout());
		JPanel head = new JPanel();
		head.setLayout(new FlowLayout(FlowLayout.LEFT));
		head.setSize(20,20);
	

        message = new JLabel("Select the first location");        
        head.add(message);
        
        pane.add(head, BorderLayout.NORTH);
        gamePanel.setLayout(new GridLayout(4,4));
        
    	for(int i=0;i<MAX_BUCK/2;i++){
    		
    		bucks[i]= new JButton();//(""+(i+1));
    		bucks[i+8]= new JButton();//(""+(i+1));
    		game[i]= -1;
    		game[i+8]= -1;
    		
    		bucks[i].setSize(50,50);
    		bucks[i+8].setSize(50,50);
    		  		
    	}
    	
    	fillVectorRandomly();
    	
    	//printVector(); //Just to prove if the random array is filled in a right way
    	for(int i=0;i<MAX_BUCK;i++){
    		
    		bucks[i].addActionListener(
    		    	new ActionListener() {
    		    		public void actionPerformed( ActionEvent event ) {
    			    		counter = (counter+1)%2;
    			    		
    			    		
    			    		JButton actual = (JButton)event.getSource();
    			    		
    			    		int index =getIndex(actual);
		    				int value =getValueByIndex(index);
		    				
    			    		actual.setText(""+value);   
    			    		
    			    		if(counter==1){
    			    			clearSelection(actual1,actual2);
    			    			actual1 = actual;
    			    			valueActual1=value;
    			    			message.setText("Select another Card:");
    			    		}
    			    		if(counter==0){
    			    			
    			    			actual2 = actual;
    			    			valueActual2=value;
    			    			if(!actual1.equals(actual2)){
	    			    			
    			    				if(compareButtons(actual1,actual2))    			    			
	    			    				message.setText("MATCH: Select a location");    			    			
	    			    			else    				    			    			
	    			    				message.setText("NO MATCH: Select Clear Selection Button");	    			    			
    			    			}    			    			    			    			
			    			}    			    		
    			    } 
    		    	}
    	            );
    		gamePanel.add(bucks[i]);	
    	}
    	
    	
    	
        // Set up the frame behavior    	
    	pane.add(gamePanel, BorderLayout.CENTER);
    	
    	// Create an "Clear" button
    	JButton clear = new JButton("clear Selection");
    	
    	// Create an "Clear" button
    	JButton quit = new JButton("Quit");
    	
    	JPanel feet = new JPanel();
    	feet.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	
    	
    	
    	clear.addActionListener(
		    	new ActionListener() {
			    public void actionPerformed( ActionEvent event ) {
			    	clearSelection(actual1,actual2);
			    } 
		    	}
	            );
    	
    	
    	
    	quit.addActionListener(
		    	new ActionListener() {
			    public void actionPerformed( ActionEvent event ) {
			    	System.exit(0);
			    } 
		    	}
	            );    	 
    	
    	
    	
    	feet.add(clear);
    	feet.add(quit);
    	
    	//pane.add(feet);    	
    	pane.add(feet,BorderLayout.SOUTH);
    }
		
    
    
    
    /**
    * getIndex(JButton J), get the card value randomly.
    *
    * @param       int
    */
	public static int getIndex(JButton J){
		
		for(int i=0;i<MAX_BUCK;i++){
			if(bucks[i].equals(J))
				return i;
		}
		return -1;	
	}
	
	
	
	
    /**
    * clearSelection(JButton actual1, JButton actual2, clear the cards once two
    * cards have been revealed and there was not match. 
    *
    * @param       Button
    */
	public static void clearSelection(JButton actual1,JButton actual2){	
		
		if(actual1!=null && actual2!=null){
			
			if(actual1.getText().compareTo(actual2.getText())!=0){
    			actual1.setText("");
    			actual2.setText("");
    			message.setText("Select another Card:");
    			actual1=null;
    			actual2=null;
			}
		}
	}
	
	
	
	
    /**
    * getValueByIndex, it takes the index of the card location and returns
    * its corresponding value.
    *
    * @param       int
    */
	public static int getValueByIndex(int index){
		return game[index];
	}
	
	
	
	
	/**
	* compareButtons(JButton one, JButton two), campares two button
	* values and returns true if both have the same value.
	* Note: this method does not work when buttons are the same.
	*
	* @param       boolean
    */
    public static boolean compareButtons(JButton one, JButton two){    	
    	
    	if(one.getText().compareTo(two.getText())==0){
    		Color a =getColorByIndex(one.getText());
    		one.setBackground(a);
    		two.setBackground(a);
    		one.setBorderPainted(false);
    		two.setBorderPainted(false);
    		one.setContentAreaFilled(false);
    		two.setContentAreaFilled(false);
    		one.setOpaque(true);
    		two.setOpaque(true);
    		return true;
		}
    	return false;
    }
    
    
    
    /**
    * getColorByIndex, it the takes a color input and assigns it to 
    * the button as a its default color. 
    *
    * @param       String
    */
    public static Color getColorByIndex(String indexS){
    
    	int index = Integer.parseInt(indexS);
    	switch(index){
    	case 1:
    		return Color.BLUE;
    	case 2:
    		return Color.RED;
    	case 3:
    		return Color.CYAN;
    	case 4:
    		return Color.GRAY;
    	case 5:
    		return Color.GREEN;
    	case 6:
    		return Color.MAGENTA;
    	case 7:
    		return Color.ORANGE;
		default:
			return Color.YELLOW;    
    	}    	
    }
    
    
    
    
    /**
    * fillVectorRandomly(), this functions takes an array of card
    * values and shuffles the array. The array values are randomly
    * display each time the game is ran.
    *
    * @param       int
    */
    public static void fillVectorRandomly(){
    	
    	for(int i=1;i<(MAX_BUCK/2)+1;i++){
    		
    		int index =generateRandomIndex();
    		game[index]=i;
    		index =generateRandomIndex();
    		game[index]=i;
    	}
    }
    
    
    
    
    /**
    * printVector(), this function is to test our algorithm to make
    * sure it is working properly.
    *
    * @param       String
    */
    public static void printVector(){
    	
    	for(int i=0;i<MAX_BUCK;i++){
    		System.out.println("element "+i+" - "+game[i]+" -");
    	}
    }
    
    
    
    
    /**
    * generateRandomIndex(), this function generates a random number.
    * This number is then assign to card's value for the game when 
    * it is first ran.
    *
    * @param       int
    */
    public static int generateRandomIndex(){
    	
    	int Min = 0;	//Min index of the array i 0
    	int Max = 15;	//Max index of the array is 15
    	int randomIndex =-1;	
    	boolean verify = false;
    	
    	while(!verify){
    		
    		randomIndex= Min + (int)(Math.random() * ((Max - Min) + 1));	
    		//Generate and index for the array
    		verify = verifyAvailability(randomIndex);
    		//Verify if the index of the array generated is available 
    	}
    	
    	return randomIndex; 
    }
    
    
    
    
    /**
    * verifyAvailability(int a), this functions verifies whether
    * the vector is empty or not.
    *
    * @param       boolean
    */
    public static boolean verifyAvailability(int a){
    	// If the space is not available return false
    	if(game[a]!=-1)
    		return false;
    	// If the space is available, meaning empty, return true.
    	return true;
    		
    }
    
    
    
    /**
    * main(String[] args), this function is creating frame named
    * concentration. This frame is a GUI that simulates a game 
    * named concentration. Most of JFrame and Swing functions are 
    * used for this game.
    *
    * @param       String[], args are being ignored.
    */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// creating a JFrame.
		Concentration frame = new Concentration("wmb1306-pzg8794");
	}

}