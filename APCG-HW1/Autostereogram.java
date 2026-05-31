import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.Random;

public class Autostereogram extends Component {
	BufferedImage img;
	
    public Autostereogram() {
    	Random random = new Random();
    	File outImage = new File("autoStereogram.jpg");
    	BufferedImage outputImage;
    	Color color; 
    	int displacementVal;
    	int x;
    	try {
    		img = ImageIO.read(new File("test_pattern.gif"));
    		int width = img.getWidth();
    		int height = img.getHeight();
    		
    		Raster imgRaster = img.getData();
    		
    		outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		int[][] depthMap = new int[height][width];
    		int[][] outputResult = new int [height][width];
    		x = 1;
    		
    		for (int row = 0; row < height; row++) {
    			for (int column = 0; column < width; column++) {
        			while (outputResult[row][column] == 0){
        				x = column;
        				// Randomly pick color C
        				color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        				int rgbVal = color.getRGB();
        				while (x < width){
        					// Color location x with color C
        					outputResult[row][x] = rgbVal;
        					
        					outputImage.setRGB(x, row, rgbVal);

        					// Look up the depth (Z) for the corresponding location in the depth map
        					depthMap[row][column] = imgRaster.getSample(x, row, 0);
            				displacementVal = (100 + depthMap[row][column])/2;
            				x = x + displacementVal;
        				}
        			}
    			}
	      	}      
    		System.out.println("end of program");
    		ImageIO.write(outputImage, "jpg", outImage);    		
	   	} catch (IOException e){}
    }
 
	public static void main(String[] args) {
		JFrame f = new JFrame("Load Image Sample");
        
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
 
        f.add(new Autostereogram());
        f.pack();
        f.setVisible(true);
	}
}
