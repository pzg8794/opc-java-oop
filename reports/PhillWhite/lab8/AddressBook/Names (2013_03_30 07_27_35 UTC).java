 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 *  Created:      02/07/2012
 *  Last Changed: 02/07/2012
 *  
 *  Names.java  
 /*
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */



/**
 * This program simulates a list of contacts and creates a GUI.
 * The user is able to see all contacts, contacts repeated on the list
 * and contacts that are not repeated on the list.
 *
 * @author  Wander Bravo	ID :  wmb1306 	RIT ID : 110006833
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 */

 
/* ListDemo.java requires no other files. */
@SuppressWarnings("serial")
public class Names extends JPanel implements ListSelectionListener {
    private JList list;
    private Contacts listModel;
    private JList list2;
    @SuppressWarnings("unused")
	private Contacts listModel2;
    private JList list3;
    private Contacts listModel3;
    
    public JMenuBar menuBar;
    public JMenuItem [] menuItem;
    public JMenu menu;
    
    
    public JLabel label1, label2, label3;
    
    @SuppressWarnings("static-access")
	public Names() {
        super(new BorderLayout());
 
        
        JPanel head = new JPanel(new FlowLayout(FlowLayout.LEFT));	
        menu = new JMenu("Action");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
        "The only menu in this program that has menu items");
        menuBar = new JMenuBar();
        menuItem = new JMenuItem[4];
        menuBar.add(menu);

        final MenuSelec menu1 = new MenuSelec();
        menuItem[0] = new JMenuItem("All");
        
        menuItem[0].addActionListener(										
		    	new ActionListener() {
		    		public void actionPerformed( ActionEvent event ) {
		    			list3.setListData(menu1.allContacts());
			    	
			    	} 
		    	}				    
		);
        menu.add(menuItem[0]);
        
        menuItem[1] = new JMenuItem("Commun");
        
        menuItem[1].addActionListener(										
		    	new ActionListener() {
		    		public void actionPerformed( ActionEvent event ) {
		    			list3.setListData(menu1.communContacts());
			    	
			    	} 
		    	}				    
		);
        menu.add(menuItem[1]);
        
        
        menuItem[2] = new JMenuItem("Unique");
        menuItem[2].addActionListener(										
		    	new ActionListener() {
		    		public void actionPerformed( ActionEvent event ) {
		    			list3.setListData(menu1.uniqueContacts());
			    	
			    	} 
		    	}				    
		);
        menu.add(menuItem[2]);
        
        menuItem[3] = new JMenuItem("Exit");
        menuItem[3].addActionListener(										
		    	new ActionListener() {
		    		public void actionPerformed( ActionEvent event ) {
		    			System.exit(0);
			    	
			    	} 
		    	}				    
		);
        menu.add(menuItem[3]);
        menu.addSeparator();
        
        
        head.add(menuBar); 
        add(head, BorderLayout.NORTH);
        
 
 
        //Create the list and put it in a scroll pane.
        list = new JList(listModel.workNames);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        
        JScrollPane listScrollPane = new JScrollPane(list);
        
        listScrollPane.getViewport().setView(list);
        
        //Create the list and put it in a scroll pane.
        list2 = new JList(listModel.friendNames);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.addListSelectionListener(this);
        
        JScrollPane listScrollPane2 = new JScrollPane(list2);
        listScrollPane2.getViewport().setView(list2);
 
        
        //Create the list and put it in a scroll pane.
        list3 = new JList();
        list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list3.addListSelectionListener(this);
        
        
        
        JScrollPane listScrollPane3 = new JScrollPane(list3);
        listScrollPane3.getViewport().setView(list3);

       
        JPanel buttonPane1 = new JPanel();
        buttonPane1.setLayout(new BorderLayout());
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(1,3));
        
        JPanel buttonPane2 = new JPanel();
        buttonPane2.setLayout(new BorderLayout());
        
        JPanel buttonPane3 = new JPanel();
        buttonPane3.setLayout(new BorderLayout());
        
        JPanel buttonPane4 = new JPanel();
        buttonPane4.setLayout(new BorderLayout());
        
        label1 = new JLabel("Work");
        label2 = new JLabel("Friends");
        label3 = new JLabel("All");
        
        buttonPane2.add(label1, BorderLayout.NORTH);
        buttonPane2.add(listScrollPane, BorderLayout.CENTER);
        
        buttonPane3.add(label2, BorderLayout.NORTH);
        buttonPane3.add(listScrollPane2, BorderLayout.CENTER);
        
        buttonPane4.add(label3, BorderLayout.NORTH);
        buttonPane4.add(listScrollPane3, BorderLayout.CENTER);
        
        buttonPane1.add(buttonPane2, BorderLayout.WEST);
        buttonPane1.add(buttonPane3, BorderLayout.CENTER);
        buttonPane1.add(buttonPane4, BorderLayout.EAST);
      
        buttonPane.add(buttonPane1);
        add(buttonPane, BorderLayout.CENTER);        
      
    }
 
    class FireListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
      
        }
    }

 
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("wmb1306 ID : 110006833 AND pzg8794 ID : 110006833");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new Names();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}