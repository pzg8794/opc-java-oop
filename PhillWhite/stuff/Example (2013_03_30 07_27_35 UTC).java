import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Example implements ActionListener{
	TextField text = new TextField(5);
	
	
	public Example(){
		
		JFrame frame = new JFrame ("Piter Test");
		
		
		JPanel bottons = new JPanel();
		JPanel bottons1 = new JPanel();
		JPanel bottons12 = new JPanel();
		JPanel bottons13 = new JPanel();
		
		JButton b = new JButton("1");
		bottons.setLayout(new GridLayout(3,1));
		bottons1.setLayout(new FlowLayout());
		bottons1.add(b);
		b.addActionListener(this);
		
		b = new JButton("2");
		bottons12.setLayout(new FlowLayout());
		bottons12.add(b);
		b.addActionListener(this);
		
		b = new JButton("3");
		bottons13.setLayout(new FlowLayout());
		bottons13.add(b);
		b.addActionListener(this);
		
	
		bottons.add(bottons1);
		bottons.add(bottons12);
		bottons.add(bottons13);
		
		
		JPanel bottons2 = new JPanel();
		bottons2.setLayout(new GridLayout( 2, 1));
		String imgStr = "/Users/pitergarcia/Desktop/ColorHistogramFolder/costclasses.jpg";
		ImageIcon image = new ImageIcon(imgStr);


		
		JPanel bottons21 = new JPanel();
		JPanel bottons22 = new JPanel();
		
		JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
		bottons21.add(label1);
		bottons22.add(text, JLabel.CENTER);
		bottons2.add(bottons21);
		bottons2.add(bottons22);
		
		frame.setLayout(new GridLayout( 1, 2));
		frame.add(bottons);
		frame.add(bottons2);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(650, 200);
		frame.setLocation(100, 100);
		frame.setVisible(true);
		
	}

	
	public static void main (String args[]) {
		new Example();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JButton b = (JButton) arg0.getSource();
		
		
		text.setText("Piter" + b.getText());
		
	}
	
}


