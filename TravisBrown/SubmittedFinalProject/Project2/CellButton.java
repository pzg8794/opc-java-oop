/*
 * CellButton.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log: 
 *
 */

import java.awt.Color;

import javax.swing.JButton;


/**
 * this class is the class of buttons contain the information of
 * each cell on the board of the battleship game.
 */
public class CellButton extends JButton{
	
	
	/*
	 * contains the cell a button.
	 */
	private Cell cell;
	
	/*
	 * row, column and id of a button
	 */
	private int row, col, id;
	
	/*
	 * Class Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	

	/**
	 * constructor, assigns a cell and size to a button
	 */
	public CellButton(Cell s) {
		cell = s;
		
		this.setSize(50,50);
		this.setBackground(Color.WHITE);
		this.setText(s.getSign()+"");
	}


	/**
	 * constructor assigns id, unique character, and location to each 
	 * button.
	 * 
	 * @param id, unique id of the button
	 * @param s, character according to the cell it represents
	 * @param i, location - row
	 * @param j, location - column
	 */
	public CellButton(int id, String s, int row1, int col1) {
		
		row = row1;
		col = col1;
		this.id = id;
		
		this.setSize(50,50);
		this.setBackground(Color.WHITE);
		this.setText(s);
	}


	
	/**
	 * gets the id of the button
	 * @return id of button.
	 */
	public int getId(){
		return id;
	}
	
	
	
	
	/**
	 * gets the row of the button
	 * @return row of button.
	 */
	public int getRow(){
		return row;
	}
	
	
	
	
	/**
	 * gets the column of the button
	 * @return column of the button.
	 */
	public int getCol(){
		return col;
	}
	
	
	

	/**
	 * this method gets the position of the button pressed.
	 *
	 * @return   position,
	 */
	public Cell getPos() {
		return cell;	
	}
	
	
	
	/**
	 * getColorByIndex, it the takes a color input and assigns it to 
	 * the button as a its default color. 
	 *
	 * @param       String
	 */
	public Color getColor(int index){

		switch(index){
		case 0:
			return Color.DARK_GRAY;
		case 1:
			return Color.BLUE;
		case 2:
			return Color.GREEN;
		case 3:
			return Color.CYAN;
		case 4:
			return Color.RED;
		case 5:
			return Color.MAGENTA;
		case 6:
			return Color.ORANGE;
		case 7:
			return Color.GRAY;
		default:
			return Color.YELLOW;    
		}    	 	
	}
	
	
	
	/**
	 * string representation of the button
	 */
	public String toString(){
		return cell.toString();
	}


	public void addCell(Cell poll) {
		cell = poll;
		
	}


	public Cell getCell() {
		return cell;
	}
}
