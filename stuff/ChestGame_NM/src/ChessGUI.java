/**
 * ChessGUI.java
 *
 * Version:
 * $Id$
 *
 * Revision:
 * $Log$
 */
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import GViewControl.Listen;


/**
 * @author Raelynn
 *
 */
public class ChessGUI extends JFrame implements Observer{

	private Chess model;
	private ArrayList<Color> colors = new ArrayList<Color>();
	private ArrayList<PieceButton> piecelist = new ArrayList<PieceButton>();
	/**
	 * @param args
	 */
	
	public ChessGUI(Chess model){
		this.model = model;
		model.addObserver(this);
		colors.add(Color.green);
		colors.add(Color.blue);
		colors.add(Color.MAGENTA);
		colors.add(Color.orange);
		colors.add(Color.pink);
		colors.add(Color.cyan);
		colors.add(Color.RED);
		colors.add(Color.yellow);
		
		JFrame frame = new JFrame("Chess Solitaire, Raelynn Janicke, rjj9591");
		LayoutManager layout = new BorderLayout();
		frame.setLayout(layout);
		JPanel p = new JPanel();
		LayoutManager layout2 = new FlowLayout(FlowLayout.LEFT);
		p.setLayout(layout2);
		JPanel p2 = new JPanel();
		LayoutManager layout3 = new GridLayout(model.row,model.col);
		p2.setLayout(layout3);
		for(int i = 0; i < 16; i++){
			PieceButton but = new PieceButton(i);
			but.addActionListener(new Listen());
			p2.add(but);
			piecelist.add(but);
		}
		frame.add(p, BorderLayout.NORTH);
		frame.add(p2, BorderLayout.CENTER);
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
