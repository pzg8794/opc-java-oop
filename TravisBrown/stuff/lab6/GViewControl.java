/*
 *  Created:      01/31/2012
 *  Last Changed: 01/31/2012
 *  
 *  GViewControl.java 
 * 
 *  Version:
 *     $Id: GViewControl.java,v 1.1 2013/07/16 22:52:25 pzg8794 Exp $
 *
 *  Revisions:
 *     $Log: GViewControl.java,v $
 *     Revision 1.1  2013/07/16 22:52:25  pzg8794
 *     lab6
 *
 *     Revision 1.1  2013/04/23 00:25:57  pzg8794
 *     *** empty log message ***
 *
 */

import java.awt.*;
import javax.swing.*;  
import java.awt.event.*; 
import java.io.FileNotFoundException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




/**
 *  GViewControl- simulates a book store.
 *
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 */
public class GViewControl extends JFrame {

	/**
	 *  Class Serial Version ID
	 */
	private static final long serialVersionUID = 1L;


	//J list containing the books
	private JList list;
	// book selected
	private Book tempBook;
	// checks if a book has been selected
	private boolean selected;
	// the model containing the bookstore
	private BarneysBooks model;
	//a panel containing details of a selected book
	private JPanel bookDetails;
	// fields to search and display book status
	private JTextField textl, txtL;
	// scroll list to contain list of books
	private JScrollPane scrollList;
	// buttons to search, buy or rent a book
	private JButton search, buy, rent;
	// panel containing detail labels of a book
	private JPanel detailsL[] = new JPanel[4];
	// panel containing the detail text fields of a book
	private JTextField detailsTF[] = new JTextField[4];
	// a list of radio buttons to make a specific search, either author,
	// title, or media of the book.
	private JRadioButton[] radioButtons = new JRadioButton[3];
	// a list of names of the radio buttons.
	private String names[] = {"Title", "Author", "Media Format", "Price"};
	
	
	
	/**
	 * Constructor to initialize the private parameters as well as set
	 * the size of the pane framework. Add components and defines Close 
	 * bottom operation.
	 *
	 * @param       bBooks, the model containing the store.
	 */
	public GViewControl(BarneysBooks bBooks) {
		super();

		new JPanel();
		list = new JList();
		model = bBooks;

		setSize(550, 500);
		setLocation(100, 100);  

		addComponentsToPane(this.getContentPane());
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}



	/**
	 * addComponentsToPane(Container pane), set the layout of the pane
	 * framework obtained from the JFrame function. 
	 * Set and add the string for the JLabel to display. 
	 * Add north, center, and south panel of the frame to create the GUI
	 * for the store
	 *
	 * @param      pane, the container of the frame.
	 */
	public void addComponentsToPane(Container pane) {

		//LISTENER FOR THE REDIO BUTTONS
		StoreListener storeL = new StoreListener();
		
		//LISTENER FOR THE SCROLL LIST
		ListListener listL = new ListListener();

		//SETTING FRAME LAYOUT
		pane.setLayout(new BorderLayout());
		
		//NORTH PANEL OF THE FRAME ---------------
		JPanel northP = new JPanel();
		northP.setLayout(new BorderLayout());
		
		JLabel img = new JLabel(new ImageIcon("storeName.png"));
		img.setHorizontalAlignment(JLabel.LEFT);
		northP.add(img, BorderLayout.NORTH);

		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1,3));
		RButtonLis rbl = new RButtonLis();
		
		// CREATING ALL READIO BUTTONS AND ADDING IT TO A GROUP
		ButtonGroup btnGroup = new ButtonGroup();
		int i = -1;
		while(++i < 3){
			radioButtons[i] = new JRadioButton(names[i]);
			radioButtons[0].setSelected(true);
			selected = true;
			radioButtons[i].addItemListener(rbl);
			btnGroup.add(radioButtons[i]);
			north.add(radioButtons[i]);
		}
		northP.add(north, BorderLayout.CENTER);

		textl = new JTextField();
		textl.addActionListener(storeL);
		northP.add(textl, BorderLayout.SOUTH);
		pane.add(northP, BorderLayout.NORTH);
		
		
		//CENTER PANEL OF THE FRAME -----------------------
		JPanel centerP = new JPanel();
		search = new JButton("Search");
		search.addActionListener(storeL);

		centerP.setLayout(new BorderLayout());
		centerP.add(search, BorderLayout.NORTH);

		scrollList = new JScrollPane();
		scrollList.getViewport().add(list);
		list.addListSelectionListener(listL);
		
		
		//TITLE / AUTHOR / MEDIA / AND PRICE OF A BOOK SELECTED
		bookDetails = new JPanel(new GridLayout(4,1));
		i = -1;
		while(++i < 4){
			detailsL[i] = new JPanel( new BorderLayout());
			detailsTF[i] = new JTextField(35);
			detailsL[i].add(new JLabel(names[i] + " :"), BorderLayout.CENTER);
			detailsL[i].add(detailsTF[i], BorderLayout.EAST);
			bookDetails.add(detailsL[i]);
		}

		centerP.add(scrollList, BorderLayout.CENTER);
		centerP.add(bookDetails, BorderLayout.SOUTH);
		pane.add(centerP, BorderLayout.CENTER);

		
		//SOUTH PANEL OF THE FRAME ---------------------
		JPanel southP = new JPanel(new GridLayout(2,1));
		JPanel south = new JPanel(new FlowLayout());
		
		buy = new JButton("Buy");
		buy.setEnabled(false);
		buy.addActionListener(storeL);

		rent = new JButton("Rent");
		rent.setEnabled(false);
		rent.addActionListener(storeL);

		south.add(buy);
		south.add(rent);

		txtL = new JTextField();
		southP.add(south);
		southP.add(txtL);
		pane.add(southP, BorderLayout.SOUTH);
	}




	/**
	 * main(String[] args), this function is creating frame named
	 * concentration. This frame is a GUI that simulates a game 
	 * named concentration. Most of JFrame and Swing functions are 
	 * used for this game.
	 *
	 * @param       String[], args are being ignored.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		BarneysBooks tmp = new BarneysBooks( new Store());
		new GViewControl(tmp).setTitle("Barnyes Book");
	}

	
	

	/**
	 * This class performs the action of the button being pressed
	 * at the time. 
	 *
	 * @author  Piter Garcia  ID :  pzg8794    RIT ID : 110006833
	 */
	class StoreListener implements ActionListener{

		/**
		 * This method performs the action of the button being pressed
		 * at the time. The action is perform by calling the method to
		 * which the button is referred to.
		 * 
		 * @param event, the event sent by a button.
		 */
		public void actionPerformed(ActionEvent event){

			// reset button action.

			txtL.setText(" ");
			if( textl == event.getSource()){
				
				if(selected){
					setUpList();
				}else{
					txtL.setText("Please Selecte a Search Type " +
							"Title, Author or Media");
				}
				
			}else if( search == event.getSource()){
				
				if(selected){
					setUpList();;
				}else{
					txtL.setText("Please Selecte a Search Type " +
							"Title, Author or Media");
				}
				
			}else if( buy == event.getSource()){
				
				txtL.setText("have bought " + tempBook.getTitle() +
						" for US$ " + model.getStore().getPrice(tempBook));
				
			}else if( rent == event.getSource()){

				txtL.setText("have rented " + tempBook.getTitle() + 
						" for US$ " + model.getStore().getPrice(tempBook));
			}
		}

		
		
		
		
		/**
		 * this method checks which radio button, or search type,
		 * has been selected and makes the search of books accordently.
		 */
		private void setUpList() {
			
			list.resetKeyboardActions();
			String[] list2 = null;
			
			if( radioButtons[0].isSelected()){
				
				list2 = model.getTitleBooks(textl.getText());
			
				radioButtons[1].setSelected(false);
				radioButtons[2].setSelected(false);
				
			}else if(radioButtons[1].isSelected()){
				
				list2 = model.getAuthorBooks(textl.getText());
	
				radioButtons[0].setSelected(false);
				radioButtons[2].setSelected(false);;
				
			}else if(radioButtons[2].isSelected()){
				
				list2 = model.getMediaBooks(textl.getText());

				radioButtons[0].setSelected(false);
				radioButtons[1].setSelected(false);
				
			}
			if( list != null){
				list.setListData(list2);	
				repaint();
			}
		}
	}



	/**
	 * This class performs the action of the radio buttons being pressed
	 * one at the time. 
	 *
	 * @author  Piter Garcia  ID :  pzg8794    RIT ID : 110006833
	 */
	class RButtonLis implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {


			selected = (e.getStateChange() == ItemEvent.SELECTED);
			AbstractButton button = (AbstractButton) e.getItemSelectable();
			String command = button.getActionCommand();
			
			list.resetKeyboardActions();
			txtL.setText(" ");
			String[] list2 = null;
			
			
			if (command.equals(names[0])) {
				
				list2 = model.getTitleBooks(textl.getText());				

			} else if (command.equals(names[1])) {
				
				list2 = model.getAuthorBooks(textl.getText());

			} else {
				
				list2 = model.getMediaBooks(textl.getText());

			}
			if( list != null){
				list.setListData(list2);	
				repaint();
			}
		}
	}
	
	
	
	
	 /**
	 * This class performs the action of the button being pressed
	 * at the time. 
	 *
	 * @author  Piter Garcia  ID :  pzg8794    RIT ID : 110006833
	 */
	class ListListener implements ListSelectionListener{
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			if(radioButtons[0].isSelected() || radioButtons[1].isSelected() 
					|| radioButtons[2].isSelected()){
				
				if(!list.isSelectionEmpty()){
					
					txtL.setText(" ");
					tempBook = model.getBooks().get(list.getSelectedIndex()); 
							
					detailsTF[0].removeAll();
					detailsTF[0].setText(tempBook.getTitle());
					
					detailsTF[1].removeAll();
					detailsTF[1].setText(tempBook.getAuthor());
					
					detailsTF[2].removeAll();
					detailsTF[2].setText(tempBook.getMedia());
					
					detailsTF[3].removeAll();
					detailsTF[3].setText("US$ " + model.getStore().getPrice(tempBook));
			
					repaint();
				}
				
				if( tempBook.isForSale()){
					
					buy.setEnabled(true);
					rent.setEnabled(false);
					
				}else {
					
					buy.setEnabled(false);
					rent.setEnabled(true);
				}
			}
		}
	
	}
}