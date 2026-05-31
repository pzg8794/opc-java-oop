/*
 * Import all from javax.swing
 */

import javax.swing.*;

/*
 * The class CardButton is extending JButton because it will create a button given the position
 */
public class PieceButton extends JButton{
	
	/*
	 * a private int is created for the position of a button
	 */
	private int pos;
	
	/*
	 * A button is created from that position
	 */
	public PieceButton(int pos){
		this.pos = pos;
	}
	
	/*
	 * getPos is a method that gets the position where the button will be created
	 */
	public int getPos(){
		return pos;
	}
}