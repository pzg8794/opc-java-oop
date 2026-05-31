/*
 *  Created:      01/31/2012
 *  Last Changed: 01/31/2012
 *  
 *  CheatFrame.java 
 * 
 *  Version:
 *     $Id: CheatFrame.java,v 1.1 2013/04/23 00:25:57 pzg8794 Exp $
 *
 *  Revisions:
 *     $Log: CheatFrame.java,v $
 *     Revision 1.1  2013/04/23 00:25:57  pzg8794
 *     *** empty log message ***
 *
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;


/**
 * Cheat Concentration - simulates a game. This is a card game in which all of 
 * the cards are laid face up on a surface. The object of this is show all the 
 * pairs of matching cards in the concentration game. 
 *
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 */
public class CheatFrame extends JFrame {

	/**
	 *  Class Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  access variable to the concentration game methods.
	 */
	private ConcentrationModel model;
	
	/**
	 *  contains the maximum number of cards in the game.
	 */
	private static int MAX_BUCK;
	
	/**
	 *  contains a list of cards.
	 */
	private ArrayList<CardFace>  game;
	
	/**
	 *  variable to set up the panel of the frame.
	 */
	private JPanel gamePanel;
	
	/**
	 *  contains a list of buttons.
	 */
	private ArrayList<CardButton> bucks;
	
	
	
	
	/**
	 * Constructor to initialize the private parameters as well as set
	 * the size of the pane framework. Add components and defines Close 
	 * bottom operation.
	 *
	 * @param   arrayList, a list of cards.
	 * @param   size, the size of the cheat frame.
	 */
	public CheatFrame(ArrayList<CardFace> arrayList, int size) {
		
		// set title in JFrame class
		super();
					
		gamePanel 	= new JPanel();
		game 		= new ArrayList<CardFace>();
						 game.addAll(arrayList);
		bucks		= new ArrayList<CardButton>();
		MAX_BUCK 	= ConcentrationModel.NUM_CARDS;
		
		setModel(new ConcentrationModel());
		setSize(size, size);
		setLocation(100, 100);  
		setTitle("Cheat Concentration Game");

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
	 * @param  contentPane, a container to add buttons.
	 */
	private void addComponentsToPane(Container contentPane) {
		
		//Creating panel for the frame.
		contentPane.setLayout(new BorderLayout());
		JPanel head = new JPanel();
		head.setLayout(new FlowLayout(FlowLayout.LEFT));
		head.setSize(20,20);

		contentPane.add(head, BorderLayout.NORTH);
		gamePanel.setLayout(new GridLayout(4, 4, 2, 2));

		//creating buttons
		for(int i=0;i<MAX_BUCK;i++){
			CardButton tmp = new CardButton(i);
			bucks.add(tmp);
		}

		//setting each button specifications
		for(int i=0;i<MAX_BUCK;i++){

			bucks.get(i).setText(""+game.get(i).getNumber());
			bucks.get(i).setBackground(bucks.get(i).getColorByIndex(""
			+ game.get(i).getNumber()));
			bucks.get(i).setBorderPainted(false);
			bucks.get(i).setContentAreaFilled(false);
			bucks.get(i).setOpaque(true);

			gamePanel.add(bucks.get(i));	
		}
		
		
		// adding buttons to the panel.  	
		contentPane.add(gamePanel, BorderLayout.CENTER);	
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
}
