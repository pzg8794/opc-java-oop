import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class ImgSplit {

	int row, col;
	String pic;
	private int cols;
	private BufferedImage imgs[];

	public ImgSplit(int row, int col, String pic){
		this.pic = pic;
		this.row = row;
		this.col = col;

		try {
			imgs = getImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImg(String pic){
		
		File file = new File(pic); // I have bear.jpg in my working directory
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(fis);
		} catch (IOException e) {
			e.printStackTrace();
		} //reading the image file
		
		return image;
	}
	


	public BufferedImage[] getImages() throws IOException {
		File file = new File(pic+".jpg"); // I have bear.jpg in my working directory
		FileInputStream fis = new FileInputStream(file);
		BufferedImage image = ImageIO.read(fis); //reading the image file

		row = 4; //You should decide the values for rows and cols variables
		cols = 4;
		int chunks = row * cols;

		int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
		int chunkHeight = image.getHeight() / row;
		int count = 0;
		imgs = new BufferedImage[chunks]; //Image array to hold image chunks

		for (int x = 0; x < row; x++) {
			for (int y = 0; y < cols; y++) {
				//Initialize the image array with image chunks
				imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

				// draws the image chunk
				Graphics2D gr = imgs[count++].createGraphics();
				gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
				gr.dispose();
			}
		}

		//writing mini images into image files
		for (int i = 0; i < imgs.length; i++) {
			ImageIO.write(imgs[i], "jpg", new File(pic+i+".jpg"));
		}
		
		return imgs;
	}


	public static void main(String[] args) throws IOException {

//		File file = new File("A.jpg"); // I have bear.jpg in my working directory
//		FileInputStream fis = new FileInputStream(file);
//		BufferedImage image = ImageIO.read(fis); //reading the image file
//
//		int rows = 4; //You should decide the values for rows and cols variables
//		int cols = 4;
//		int chunks = rows * cols;
//
//		int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
//		int chunkHeight = image.getHeight() / rows;
//		int count = 0;
//		imgs = new BufferedImage[chunks]; //Image array to hold image chunks
//
//		for (int x = 0; x < rows; x++) {
//			for (int y = 0; y < cols; y++) {
//				//Initialize the image array with image chunks
//				imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
//
//				// draws the image chunk
//				Graphics2D gr = imgs[count++].createGraphics();
//				gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
//				gr.dispose();
//			}
//		}
//		System.out.println("Splitting done");
//
//		//writing mini images into image files
//		for (int i = 0; i < imgs.length; i++) {
//			ImageIO.write(imgs[i], "jpg", new File("img" + i + ".jpg"));
//		}
//		System.out.println("Mini images created");
	}
}
