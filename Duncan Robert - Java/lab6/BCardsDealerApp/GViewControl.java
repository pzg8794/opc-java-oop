/*
 *  Created:      01/31/2012
 *  Last Changed: 01/31/2012
 *  
 *  GViewControl.java 
 * 
 *  Version:
 *     $Id: GViewControl.java,v 1.1 2013/04/23 00:25:57 pzg8794 Exp $
 *
 *  Revisions:
 *     $Log: GViewControl.java,v $
 *     Revision 1.1  2013/04/23 00:25:57  pzg8794
 *     *** empty log message ***
 *
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;  
import java.awt.event.*; 


/**
 * Concentration - simulates a gameis a card game in which all of 
 * the cards are laid face down on a surface and two cards are flipped 
 * face up over each turn. The object of the game is to turn over every 
 * pairs of matching cards. 
 *
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 */
public class GViewControl extends JFrame implements Observer {

	/**
	 *  Class Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  Contains the message label for the panel.
	 */
	private JLabel message;
	
	/**
	 *  variable to set up the panel of the frame.
	 */
	private JPanel gamePanel;
	
	/**
	 *  contains maximum number of cards.
	 */
	private static int MAX_BUCK;
	
	/**
	 *  variable access to the methods of the concentration game.
	 */
	private ConcentrationModel model;
	
	/**
	 *  contains a list of cards.
	 */
	private ArrayList<CardFace>  game;
	
	/**
	 *  button to reset the game.
	 */
	private JButton reset;
	
	/**
	 *  button to get all pairs in the game.
	 */
	private JButton cheat;
	
	/**
	 *  button to undo previous action.
	 */
	private JButton undo;
	
	/**
	 *  contains a list of buttons.
	 */
	private ArrayList<CardButton> bucks;
	
	
	/**
	 * Constructor to initialize the private parameters as well as set
	 * the size of the pane framework. Add components and defines Close 
	 * bottom operation.
	 *
	 * @param       cards, a list of cards.
	 */
	public GViewControl(ConcentrationModel cards) {
		super();
		
		MAX_BUCK 	= ConcentrationModel.NUM_CARDS;
		game 		= new ArrayList<CardFace>();
		gamePanel 	= new JPanel();
		bucks 		= new ArrayList<CardButton>();
		
		game.addAll(cards.getCards());
		setModel(new ConcentrationModel());
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
	public void addComponentsToPane(Container pane) {

		pane.setLayout(new BorderLayout());
		JPanel head = new JPanel();
		head.setLayout(new FlowLayout(FlowLayout.LEFT));
		head.setSize(20,20);

		message = new JLabel("Moves: 0 Select the first location");        
		head.add(message);

		pane.add(head, BorderLayout.NORTH);
		gamePanel.setLayout(new GridLayout(4, 4, 2, 2));

		for(int i=0;i<MAX_BUCK;i++){
			CardButton tmp = new CardButton(i);
			getBucks().add(tmp);	
		}
		model.reset();
		CardListener cardLis = new CardListener();
	
		for(int i=0;i<MAX_BUCK;i++){
		   getBucks().get(i).setFont(new Font("Serif", Font.BOLD, 28));
			getBucks().get(i).addActionListener(cardLis);
			gamePanel.add(getBucks().get(i));	
		}
		model.addObserver(this);

		// Set up the frame behavior    	
		pane.add(gamePanel, BorderLayout.CENTER);

		// Create an "Clear" button
		reset = new JButton("Reset");
		reset.addActionListener(cardLis);
		
		// Create a "Cheat" button
		cheat = new JButton("Cheat");
		cheat.addActionListener(cardLis);
		
		// Create an "Undo" button
		undo = new JButton("Undo");
		undo.addActionListener(cardLis);

		JPanel feet = new JPanel();
		feet.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// adding buttons to panel.
		feet.add(reset);
		feet.add(cheat);
		feet.add(undo);
		pane.add(feet,BorderLayout.SOUTH);
	}


	
	/**
	 * Update the window when the model to indicate if an update is
	 * required or not, then update changes such as the color and 
	 * string content of a CardButton based on the CardFaces in the 
	 * model, as well as the text in the label based on the model state.
	 * 
	 * @param arg0 - An Observable -- not used.
	 * @param arg1 - An Object -- not used.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		ArrayList<CardFace> cards = model.getCards();
		for (int i = 0; i < cards.size(); ++i) {
			CardButton cardBtn = (CardButton) getBucks().get(i);
			
			if (cards.get(i) instanceof Card) {
				cardBtn.setText("" + cards.get(i).getNumber());
				cardBtn.setBackground(cardBtn.getColorByIndex("" 
				+ cards.get(i).getNumber()));
				cardBtn.setBorderPainted(false);
				cardBtn.setContentAreaFilled(false);
				cardBtn.setOpaque(true);
				
			} else if (cards.get(i) instanceof CardBack) {
				cardBtn.setText("");
				cardBtn.setBorderPainted(true);
				cardBtn.setBackground(Color.white);
			}
		}
		validate();
	}



	/**
	 * This method returns a reference to access to the 
	 * methods in the ConcentrationModel class.
	 * 
	 * @param model, an instance of ConcentrationModel.
	 */
	public ConcentrationModel getModel() {
		return model;
	}



	/**
	 * Assigns to the model of the game "model" memory 
	 * to access the game methods.
	 * 
	 * @param model, an instance of ConcentrationModel.
	 */
	public void setModel(ConcentrationModel model) {
		this.model = model;
	}



	/**
	 * This methods returns a list of the card buttons.
	 * 
	 * @param bucks, an array of buttons.
	 */
	public ArrayList<CardButton> getBucks() {
		return bucks;
	}



	/**
	 * main(String[] args), this function is creating frame named
	 * concentration. This frame is a GUI that simulates a game 
	 * named concentration. Most of JFrame and Swing functions are 
	 * used for this game.
	 *
	 * @param       String[], args are being ignored.
	 */
	public static void main(String[] args) {
		ConcentrationModel tmp = new ConcentrationModel();
		GViewControl frame = new GViewControl(tmp);
		frame.setTitle("Concentration Game");
	}



	
	/**
	 * This class performs the action of the button being pressed
	 * at the time. 
	 *
	 * @author  Piter Garcia  ID :  pzg8794    RIT ID : 110006833
	 */
	class CardListener implements ActionListener{
	
		
		
		
		/**
		 * This method performs the action of the button being pressed
		 * at the time. The action is perform by calling the method to
		 * which the button is referred to.
		 * 
		 * @param event, the event sent by a button.
		 */
		public void actionPerformed(ActionEvent event){
				
			// reset button action.
			if( reset == event.getSource()){
				getModel().reset();
				
			// cheat button action
			}else if( cheat == event.getSource()){
				new CheatFrame(getModel().cheat(), 375);
			
				//undo button action.
			}else if( undo == event.getSource()){
				getModel().undo();
				
			}else{
				// checking for pairs while the user flip the cards.			
				CardButton tempButton = (CardButton)event.getSource();
				model.selectCard(tempButton.getPos());
				if (model.howManyCardsUp() == 1){
						message.setText("Moves: " + model.getMoveCount() 
						+ " Select the second card");
				}else{
					
					if( getModel().pairNumber == ConcentrationModel.NUM_PAIRS){
						message.setText("Moves: " + model.getMoveCount() 
								+ "\n Nice Move! You Are a Winner!");
					}else{
						message.setText("Moves: " + model.getMoveCount() 
						+ " Select the first card");
					}
				}
				tempButton.repaint();
			}
		}
	}

}
