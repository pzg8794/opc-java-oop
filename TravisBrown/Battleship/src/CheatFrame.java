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

import javax.swing.*;


/**
 * Cheat Concentration - simulates a game. This is a battleship game in which all of 
 * the cells are laid face up on a surface. The object of this is show all the 
 * ships of matching cells in the battleship game. 
 *
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 */
public class CheatFrame extends JFrame {

	/*
	 *  Class Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * model of the battleship game
	 */
	private BattleShipModel model;

	/*
	 * size of the board
	 */
	private int size;

	/*
	 * board of the game
	 */
	private JPanel board;


	/**
	 * Constructor to initialize the private parameters as well as set
	 * the size of the pane framework. Add components and defines Close 
	 * bottom operation.
	 *
	 * @param   arrayList, a list of cells.
	 * @param   size, the size of the cheat frame.
	 */
	public CheatFrame(BattleShipModel model, int size) {

		super();

		this.model = model;
		setSize(600, 600);
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
	 * randomly add the cell characters.
	 *
	 * @param  contentPane, a container to add buttons.
	 */
	private void addComponentsToPane(Container pane) {

		//board setup.
		pane.setLayout(new BorderLayout());
		JPanel head = new JPanel();
		head.setLayout(new FlowLayout(FlowLayout.LEFT));
		head.setSize(20,20);

		size = model.getBattle().getSize();
		board = new JPanel(new GridLayout(size, size, 2, 2));

		CellButton[] buttons = new CellButton[size*size];

		int x = -1;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size ; j++){

				buttons[++x] = new CellButton(model.getBattle().getBoardC().get(x));
				buttons[x].setBackground(this.getColor(buttons[x].getCell().getSign()));
				buttons[x].setOpaque(true);
				buttons[x].setEnabled(false);
				board.add(buttons[x]);
			}
		}
		pane.add(board, BorderLayout.CENTER);

	}



	/**
	 * assigns a unique color to each ship and water cell
	 * @param c, unique character of the ship and or water cell
	 * @return unique and different color for each ship.
	 */
	private Color getColor(Character c) {

		switch(c){

		case 'A':
			return Color.GREEN;
		case 'B':
			return Color.ORANGE;
		case 'C':
			return Color.PINK;
		case 'D':
			return Color.YELLOW;
		case 'E':
			return Color.MAGENTA;
		case 'F':
			return Color.WHITE;
		case 'G':
			return Color.green;
		case 'H':
			return Color.orange;
		case 'J':
			return Color.pink;
		case 'K':
			return Color.yellow;
		case 'L':
			return Color.magenta;
		case '*':
		case '@':
			return Color.BLUE;
		case '#':
			return Color.RED;
		default :
			return Color.CYAN;
		}
	}

}
