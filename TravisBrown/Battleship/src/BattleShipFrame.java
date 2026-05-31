/*
 * BattleShipFrame.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log: 
 *
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.*;



/**
 * BattleShipFrame - simulates a battleship game in which the user
 * wins by guessing where all ships are located and hitting them.
 * 
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 */
public class BattleShipFrame extends JFrame {


	/*
	 *  Contains the message label for the panels.
	 */
	private JLabel message, msg, allHits, trie;

	/*
	 *  variables to set up the panels of the frame.
	 */
	private JPanel gamePanel, board;

	/*
	 *  variable access to the methods of the Rush Hour Game.
	 */
	private BattleShipModel gameModel;

	/*
	 * TextView representation of the game
	 */
	private BattleShipTextView textView;

	/*
	 *  buttons to hit the next move, help the user, reset game.
	 *  and cheat to show all ships locations.
	 */
	private JButton hint, help, reset, cheat;

	/*
	 *  contains a list of buttons (Cell).
	 */
	private CellButton[] buttons;

	/*
	 * contains the size of the frame.
	 */
	private int size;

	/*
	 * Initialization board, used to reset board.
	 */
	private LinkedList<Cell> resetBoard = new LinkedList<Cell>();

	/*
	 * Unique ID 
	 */
	private static final long serialVersionUID = 4855337901799338708L;

	private boolean allHit; // checks if all ship Cell where hit
	private boolean isHint; // checks if the move is a hint or not
	private JTextField status; // keeps the current status of the game
	private JLabel pic; // Displays the ship that has been hit
	private CellButtonListener cBL; // listener for all cell buttons
	private LinkedList<Ship> sinkShips; // contains all sink ships
	private LinkedList<Water> waterHit; // contains all hit water Cell



	/**
	 * constructor to build frame for the game.
	 */
	public BattleShipFrame(BattleShipModel gameModel) {
		super();

		allHit = false;
		this.gameModel = gameModel;
		textView = new BattleShipTextView(gameModel);
		setSize(800, 800);
		setLocation(100, 100);  
		sinkShips = new LinkedList<Ship>();
		waterHit = new LinkedList<Water>();

		addComponentsToPane(this.getContentPane());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}



	/**
	 * methods to add components, buttons and action listeners.
	 * @param pane, the pane of this frame.
	 */
	private void addComponentsToPane(Container pane) {

		//FRAME
		//board setup.
		pane.setLayout(new BorderLayout());
		ButtonsListener bl = new ButtonsListener();
		JPanel north = new JPanel(new BorderLayout());

		//-> gameModel should have its own size
		size = gameModel.getBattle().getSize();
		help = new JButton("Help");
		help.addActionListener(bl);
		trie = new JLabel();
		trie.setText(textView.getTries());

		//FRAME
		north.add(trie, BorderLayout.WEST);
		north.add(help, BorderLayout.EAST);
		pane.add(north, BorderLayout.NORTH);

		//FRAME
		cBL = new CellButtonListener();
		buttons = new CellButton[size*size];
		board = new JPanel(new GridLayout(size, size, 2, 2));

		//FRAME
		int x = -1;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size ; j++){
				buttons[++x] = new CellButton(x, 
						gameModel.getUpdatedBoard().get(i), i, j);
				board.add(buttons[x]);
				buttons[x].addActionListener(cBL);
			}
		}
		pane.add(board, BorderLayout.CENTER);

		//FRAME
		JPanel update = new JPanel(new GridLayout(3,1, 5, 5));
		JPanel updates = new JPanel(new GridLayout(1,3, 10, 10));


		allHits = new JLabel();
		msg = new JLabel();
		allHits.setText(textView.getHits());
		msg.setText(textView.getSink());

		//FRAME
		update.add(allHits);
		update.add(msg);
		updates.add(update);

		//FRAME
		JPanel temp = new JPanel(new FlowLayout());
		gamePanel = new JPanel(new GridLayout(3,1));
		status = new JTextField(20);
		status.addActionListener(bl);
		JPanel temp1 = new JPanel(new FlowLayout());
		temp1.add(status);
		gamePanel.add(temp1);

		//Frame
		message = new JLabel("GAME STATUS");
		temp.add(message);
		gamePanel.add(temp, BorderLayout.NORTH);

		//FRAME
		JPanel south = new JPanel(new FlowLayout());
		// Create an "Clear" button
		reset = new JButton("Reset");
		reset.addActionListener(bl);
		// Create a "Cheat" button
		cheat = new JButton("Cheat");
		cheat.addActionListener(bl);
		// Create a"Hint" button
		hint = new JButton("Hint");
		hint.addActionListener(bl);

		// adding buttons to panel.
		south.add(cheat);
		south.add(hint);
		south.add(reset);
		gamePanel.add(south);
		updates.add(gamePanel, BorderLayout.CENTER);

		pic = new JLabel(new ImageIcon("battleship.jpeg"));
		JPanel tmp = new JPanel(new FlowLayout());
		tmp.add(pic);
		pic.setPreferredSize(new Dimension(250,100));
		pic.revalidate();
		tmp.setPreferredSize(pic.getSize());
		tmp.revalidate();
		updates.add(tmp);
		pane.add(updates, BorderLayout.SOUTH);
	}





	/**
	 * class that performs the action of all Cell buttons.
	 */
	class CellButtonListener implements ActionListener {

		//FRAME
		public void actionPerformed(ActionEvent ae) {
			CellButton temp = (CellButton) ae.getSource();
			isHint = false;
			move(temp, isHint);
		}
	}




	/**
	 * main method
	 * @param args, ignored
	 */
	public static void main(String[] args){

	}




	/**
	 * This class performs the action of the button being pressed
	 * at the time. 
	 *
	 * @author  Piter Garcia  ID :  pzg8794    RIT ID : 110006833
	 */
	class ButtonsListener implements ActionListener{




		/**
		 * This method performs the action of the button being pressed
		 * at the time. The action is perform by calling the method to
		 * which the button is referred to.
		 * 
		 * @param event, the event sent by a button.
		 */
		public void actionPerformed(ActionEvent event){

			//FRAME
			if( cheat.equals(event.getSource())){
				new CheatFrame(gameModel, 375);

			}else if( hint.equals(event.getSource())){
				isHint = true;
				move(buttons[new Random().nextInt(buttons.length)], isHint);

			}else if( reset.equals(event.getSource())){

				textView.reset();  //TEXT
				gameModel.reset(); //MODEL
				resetBoard(); 

			}else if( help.equals(event.getSource())){

				//FRAME
				JLabel label = new JLabel(textView.getHelp());
				JOptionPane.showMessageDialog(null, label);
			}
			repaint();
		}
	}




	//FRAME
	/**
	 * set all buttons to its default view (hiding ships)
	 */
	private void setButtons() {

		int x = -1;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size ; j++){

				buttons[++x].setText("~");
				buttons[x].setBackground(new JButton().getBackground());
				buttons[x].setEnabled(true);
			}
		}
	}




	//FRAME
	/**
	 * resets the board to its default view
	 */
	private void resetBoard() {

		setButtons();//reset Buttons
		allHit = false;
		waterHit.clear(); // not using yet
		sinkShips.clear(); // not using yet 
		status.setText(" ");
		msg.setText(textView.getSink());
		trie.setText(textView.getTries());
		allHits.setText(textView.getHits());
		pic.setIcon(new ImageIcon("battleship.jpeg"));
	}



	/**
	 * moves a button according to the cell selected
	 * @param temp, cell selected by the user.
	 * @param isHint2, true if it is a move hint
	 */
	public void move(CellButton temp, boolean isHint2){
		boolean skip = false;

		if(!allHit){

			if(isHint2){
				temp = buttons[new Random().nextInt(buttons.length)];
			}
			//HITTING CELL GIVEN BY PLAYER
			for( Cell s: gameModel.getBoardC()){

				if(s.getStartRow() == temp.getRow() && s.getStartCol()
						== temp.getCol()){

					if(!s.wasHit()){//CHECKING IF THE CELL WAS ALREADY HIT

						textView.status(s, 6);
						if(!isHint)
							s.hit();
						//MODEL AND TEXT
						gameModel.hitAcell(this, s, textView, isHint);
						break;

					}else{

						if((skip = alreadyHit()))
							break;
					}
				}
			}
			skipOrNot(skip);
			//SET SHIP BUTTONS
			try {
				updateButtons(new LinkedList<Cell>(gameModel.getBoardC()), "#");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setWaterButtons(); //SET WATER BUTTONS

		}else{
			setWinner();
		}

		//		test();

		//TEXT - UPDATES
		status.setText(textView.getStatus());
		trie.setText(textView.getTries());
		allHits.setText(textView.getHits());
		msg.setText(textView.getSink());
		repaint();
	}



	/**
	 * Only use for testing purpose.
	 */
	@SuppressWarnings("unused")
	private void test() {
		for( Cell s: gameModel.getShips())
			System.out.println(s + " " + s.getSize());


		LinkedList<Cell> tempboardC = new LinkedList<Cell>(gameModel.getCell());
		int x = 0;
		for( int i= 0; i < size; i++){
			for( int j=0; j< size; j++){

				if(! tempboardC.isEmpty()){

					if( tempboardC.peek().toString().equals("#"))
						++x;

					System.out.print(tempboardC.poll());
				}
			}
			System.out.println();
		}
		System.out.println(x + " size = "+ gameModel.getCell().size() );
		if(x == gameModel.getCell().size()-1){
			allHit = true;
		}
	}




	// COULD BE A MODEL METHOD
	/**
	 * this methods is use to skip out of the loop if the user wins.
	 * @param skip, user wins if it is false
	 */
	private void skipOrNot(boolean skip) {
		if(!skip){
			//MODEL
			allHit = gameModel.winner();
			if(allHit && !isHint){
				setWinner();
			}

		}else{
			textView.status(null, 2); //TEXT - status 2
			allHit = false;
		}

	}




	/**
	 * checks if a cell was already hit
	 * @return true if was already hit.
	 */
	private boolean alreadyHit() {

		return gameModel.alreadyHit(this, isHint, textView);
	}




	/**
	 * checks if the cell selected miss the target (ship cell)
	 * @param s, cell selected.
	 */
	public void missHit(Cell s) {
		waterHit = gameModel.missHit(s, waterHit, textView);
	}



	//FRAME
	/**
	 * sets all the hit water Cell to its hit cell symbol
	 */
	private void setWaterButtons() {
		for( Cell c: gameModel.getWater()){

			for( CellButton cb: buttons){

				if( c.getStartCol() == cb.getCol() && c.getStartRow() == cb.getRow()){
					if( c.getHits() > 0){
						cb.setText("@");
						cb.setBackground(Color.BLUE);
						cb.setOpaque(true);
						cb.setEnabled(false);
					}
				}
			}
		}
	}




	//FRAME
	/**
	 * let the user know that has won the game in nice format.
	 */
	public void setWinner() {

		setNotHitWaterButtons();
		textView.status(null, 5);//TEXT - status 5

		JLabel label = new JLabel("You Have Won");
		JOptionPane.showMessageDialog(null, label);
	}



	//FRAME
	/**
	 * changes the state of not hit buttons water Cell once the game 
	 * is over
	 */
	private void setNotHitWaterButtons() {

		for(CellButton cb: buttons){

			if(cb.isEnabled()){
				cb.setBackground(Color.BLUE);
				cb.setOpaque(true);
				cb.setEnabled(false);
			}
		}
	}


	//FRAME
	/**
	 * updates all ships buttons if hit and let user know whether or not
	 * a ship cell has been hit.
	 * @param tempboardC, current board on display
	 * @param c, unique symbol for ship hit Cell.
	 */
	private void updateButtons(LinkedList<Cell> tempboardC, String c) throws IOException {

		String picN = "";

		int x = 0;
		for( int i= 0; i < size; i++){
			for( int j=0; j< size; j++){
				CellButton button = buttons[x++];

				if(! tempboardC.isEmpty()){

					picN = tempboardC.peek().getSign()+".jpg";
					button.addCell(tempboardC.poll());
					if( button.getCell().wasHit() ){
						button.setText(c);

						if(button.getText().equals("#")){
							button.setBackground(Color.gray);
							//							System.out.println(picN);
							if(button.isEnabled()){
								ImageIcon myIcon = new ImageIcon(picN);
								pic.setIcon(myIcon);
							}
							button.setOpaque(true);
							button.setEnabled(false);
						}

						for( Cell cs : gameModel.getShips()){

							if(cs.hasSink() && button.getCell().getSign() == cs.getSign()){

								button.setText(cs.getSign()+"");
								button.setBackground(Color.RED);
								button.setOpaque(true);
							}
						}

						if(button.getCell().hasSink()){
							System.out.println("HERE2");
							button.setBackground(Color.RED);
							button.setOpaque(true);
						}

					}else{
						button.setText("~");
					}
				}
			}
		}


	}




	/**
	 * gets the initialization board to reset the updated board
	 * @return initialization board.
	 */
	public LinkedList<Cell> getResetBoard() {
		return resetBoard;
	}



	/**
	 * gets the sink ships
	 * @return sink ships list.
	 */
	public LinkedList<Ship> getSinkShips() {
		return sinkShips;
	}

}
