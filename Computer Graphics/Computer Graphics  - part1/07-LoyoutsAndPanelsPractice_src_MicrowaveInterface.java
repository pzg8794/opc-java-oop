/** An example GUI implementing a microwave interface.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MicrowaveInterface extends JFrame 
					implements ActionListener{

	private Timer timer ;  // for countdown

	private JButton[] number = new JButton[10];  // ten digits
	private JButton btnStop = new JButton("Stop");
	private JButton btnStart = new JButton("Start");
	private JButton btnClear =   new JButton("Clear");
	private JTextField tfTime = new  JTextField(""); // seconds displayed in field

	private int seconds = 0;
	
	/**Sets up the window with GUI controls; 
	 * creates a timer   */
	public MicrowaveInterface() {

		// set up window properties
		this.setTitle("The Front View of a Microwave Oven");
		this.setSize(400, 250);
		this.setLocationRelativeTo(null); // Center the frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// create a timer to be used later
		this.timer  = new Timer(1000, this);

		// Create a panel for the buttons  
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(4, 3));

		// Add buttons to the panel
		for (int i = 1; i <= 9; i++) {
			number[i] = new JButton(""+i);
			buttonsPanel.add(number[i]);
			number[i].addActionListener(this);
		}
		number[0] =new JButton("" + 0);
		buttonsPanel.add(number[0]);number[0].addActionListener(this);
		buttonsPanel.add(btnStart); btnStart.addActionListener(this);
		buttonsPanel.add(btnStop); btnStop.addActionListener(this);

		// Create panel for text field and buttonPanel
		JPanel rightPanel = new JPanel(new BorderLayout());
		tfTime.setEditable(false);   
		rightPanel.add(tfTime,   BorderLayout.NORTH);
		rightPanel.add(buttonsPanel, BorderLayout.CENTER);

		// add contents into the frame
		this.setLayout(new GridLayout(1,2));
		this.add(btnClear) ;
		this.add(rightPanel);
		
		// make the frame listen to Clear button
		btnClear.addActionListener(this);
		this.setVisible(true);	
	}


	/**  public void actionPerformed(ActionEvent e)
	 * Method processes ActionEvents from buttons and 
	 * the timer  */
	public void actionPerformed(ActionEvent e) {

		if  (e.getSource() == btnClear){ // set text to 0
			this.seconds=0;
			tfTime.setText(this.seconds+"");
		}		
		else if	(e.getSource() == btnStart){ //start countdown
			if (tfTime.getText()!= null && 
					tfTime.getText().isEmpty()== false)	{

				timer.start();
			}
		}	else if  (e.getSource() == btnStop){ // stop countdown
			timer.stop();
			JOptionPane.showMessageDialog(this, "Stopping the timer");
		} else if (e.getSource() == timer)
		{  // timer tick - comes every second; update the seconds, if >0
			this.seconds = Integer.parseInt(tfTime.getText());
			if (this.seconds >0){
				this.seconds--;			
				tfTime.setText(this.seconds+"");
			}
		}else { // one of the 1,2,3, .... buttons
			// append the number on button to the text in text field
			JButton btn = (JButton) e.getSource();			
			String secondsDisplayed = tfTime.getText();
			String buttonText = btn.getText();		  
			tfTime.setText(secondsDisplayed+buttonText);
		}
	}
	
	/** Main method */
	public static void main(String[] args) {
		MicrowaveInterface frame = new MicrowaveInterface();

	}
}

