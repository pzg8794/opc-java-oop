import java.awt.Color;
import javax.swing.JButton;

/**
 * CarButton - simmulates a button or a JButton that represents
 * a class.
 *
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 */
public class CardButton extends JButton{

	/**
	 * Class Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contains the position of a button.
	 */
	private int position;
	
	
	
	/**
	 * constructor, assigns a position and its size to a button.
	 */
	public CardButton(int pos) {
		
		position = pos;
        
		this.setSize(50,50);
		this.setBackground(Color.WHITE);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setOpaque(true);
	}


	
	
	/**
	 * this method gets the position of the button pressed.
	 *
	 * @return   position,
	 */
	public int	getPos() {
		return position;	
	}
	
	
	
	/**
	 * getColorByIndex, it the takes a color input and assigns it to 
	 * the button as a its default color. 
	 *
	 * @param       String
	 */
	public Color getColorByIndex(String indexS){

		int index = Integer.parseInt(indexS);
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
  }
