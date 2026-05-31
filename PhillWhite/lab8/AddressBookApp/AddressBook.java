import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/* 
 *  Created:      02/07/2012
 *  Last Changed: 02/07/2012
 *  
 *  AddressBook.java
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */
/**
 * This class is the View of the AddressBook application.
 * This function control the data presentation and the GUI
 * 
 * @author  Wander Bravo	ID :  wmb1306 	RIT ID : 110006833
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 *
 */

@SuppressWarnings("serial")
public class AddressBook extends JFrame{
	
	/**
	 * MenuBar for the MENU
	 */
	public JMenuBar menuBar;
	
	/**
	 * Array with the three MenuItems for the MENU
	 */
	public JMenuItem [] menuItem;
	
	/**
	 * JMenu for the MENU
	 */
	public JMenu menu;
	
	/**
	 * JList to show the data
	 */
	public JList list;
	
	/**
	 * JTextField to filter the search
	 */
	public JTextField text;	
	
	/**
	 * Constructor for the AddressBook class
	 * @param title
	 */
	public AddressBook(String title)
	{
		 // set title in JFrame class
		super(title);
        setSize(500, 500);
        setLocation(100, 100);        
        addComponentsToPane(this.getContentPane());
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}	
	
	 /**
	  * Function which adds all the elements in the Pane's Frame
	 * @param pane
	 */
	public void addComponentsToPane(Container pane) {
			
			pane.setLayout(new BorderLayout());
			JPanel head = new JPanel(new BorderLayout());			
			menu = new JMenu("Action");
	        menu.setMnemonic(KeyEvent.VK_A);
	        menu.getAccessibleContext().setAccessibleDescription(
	                "The only menu in this program that has menu items");
			menuBar = new JMenuBar();
			menuItem = new JMenuItem[3];
			menuBar.add(menu);
			
			menuItem[0] = new JMenuItem("Add Contact");
			menuItem[0].addActionListener(										
					    	new ActionListener() {
						    public void actionPerformed( ActionEvent event ) {
						    	String str = JOptionPane.showInputDialog(null, "Enter contact info (name:number)", 
						    			"wmb1306", 1);
						    			  if(str != null)
						    			  { 
						    				  Contacts.addElement(str);
						    				  list.setListData(Contacts.vector.toArray());
						    			  }						    	
						    } 
					    	}				    
					);
			
			menu.add(menuItem[0]);
			menuItem[1] = new JMenuItem("Delete Contact");
			
			menuItem[1].addActionListener(										
			    	new ActionListener() {
				    public void actionPerformed( ActionEvent event ) {
				    	int n = list.getSelectedIndex();
				    	if(n!=-1)
				    	{
				    		Contacts.removeElement(n);
	    				  	list.setListData(Contacts.vector.toArray());
				    	}
				    } 
			    	}				    
			);
			menu.add(menuItem[1]);
			menuItem[2] = new JMenuItem("Exit");
			
			menuItem[2].addActionListener(										
			    	new ActionListener() {
				    public void actionPerformed( ActionEvent event ) {
				    	System.exit(0);
				    } 
			    	}				    
			);
			
			menu.add(menuItem[2]);
			
			head.add(menuBar,BorderLayout.NORTH);
	    		
	    	
	    	text = new JTextField(30);
	    	
	    	head.add(text,BorderLayout.CENTER);	    	
	    	    	
	    	pane.add(head,BorderLayout.NORTH);
	    		    		    	    	
	    	
	    	JPanel listPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    	list = new JList();
	    		
	    	Contacts.getMergeFriendsAndWorkers();
	    	list.setListData(Contacts.vector.toArray());
	    	
	    	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    	list.setLayoutOrientation(JList.VERTICAL);
	    	list.setSelectedIndex(0);	    	
	    	list.setVisibleRowCount(17);
	    	JScrollPane listScroller = new JScrollPane(list);
	    	listPanel.add(listScroller);
	    	pane.add(listPanel,BorderLayout.CENTER);
	    	
	    	
	    	text.addKeyListener(new KeyAdapter() {

	    		public void keyTyped(KeyEvent ke) {
	    			String compareString;
	    			if(ke.getKeyChar()!='\b')
	    				compareString= text.getText()+ke.getKeyChar();
	    			else 
	    				compareString = text.getText();
	    			
	    			if(compareString.compareTo("")!=0)
	    			{
	    				
		    			Contacts.searchForWord(compareString);
		    			
		    			list.setListData(Contacts.vector.toArray());
	    			}	    			
	    		}
	    	});	    		    		    		       	    	
	    }
			
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		AddressBook obj = new AddressBook("Homework 8");
	}

}
