import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;


public class LinearAttack {

	int[] table = {8,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  2, -2,  0,  2,  0,  0, -2, -4, -2, -2,  0,  2,  0, -4,  2,
			0,  2,  0,  2, -2,  0, -2,  0, -2,  0,  2, -4, -4, -2,  0,  2,
			0,  0,  2,  2,  0,  4,  2, -2, -2, -2,  0,  0,  2, -2,  4,  0,
			0,  2,  0, -2,  0,  2,  0, -2,  2, -4,  2,  0, -2,  0, -2, -4,
			0,  4, -2, -2,  2,  2,  0,  4, -2,  2,  0,  0,  0,  0,  2, -2,
			0,  0,  4,  0,  2,  2, -2,  2,  0,  0,  0,  4, -2, -2, -2,  2,
			0, -2, -2,  0,  4, -2,  2,  0,  0, -2, -2,  0, -4, -2,  2,  0,
			0,  2,  2,  0,  0, -2, -2,  0,  2,  0, -4, -2,  2, -4,  0, -2,
			0,  0,  0,  4,  2,  2, -2,  2,  2, -2, -2, -2,  0,  4,  0,  0, 
			0,  4,  2,  2, -2, -2,  4,  0,  0,  0, -2,  2, -2,  2,  0,  0,
			0, -2,  4, -2,  0, -2,  0,  2, -4, -2,  0, -2,  0,  2,  0, -2,
			0,  0,  2,  2,  4,  0,  2, -2,  0,  4,  2, -2,  0,  0, -2, -2,
			0, -2,  0, -2, -2,  4,  2,  0,  0,  2, -4, -2, -2,  0, -2,  0,
			0, -2, -2,  4, -2,  0,  0,  2, -2,  0,  0,  2,  0, -2, -2, -4,
			0,  0,  0,  0,  0,  0,  4,  4,  2, -2,  2, -2,  2, -2, -2,  2};

	int[] hex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
	char[] hex1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	LinkedList<Integer> list = new LinkedList<Integer>();
	BigDecimal[][] bias1 = new BigDecimal[16][16];
	ArrayList<Double> bias = new ArrayList<Double>();

	
	
	int[][]sTable = new int[16][16];

	public LinearAttack(){

		PrintWriter writer = null;
		try {
			writer = new PrintWriter("LinearAttackO.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int j=-1;
		for(int i= 0; i< 16; i++){

			for( int x = 0; x<16; x++){
				sTable[i][x] = table[++j];
			}
		}


		System.out.println("\t  S-Boxes or Look-Up Table");
		writer.println("\t  S-Boxes or Look-Up Table");
		for(int i= 0; i< 16; i++){

			for( int x = 0; x<16; x++){
				if(sTable[i][x] < 0 ){
					System.out.print(" " + sTable[i][x]);
					writer.print(" " + sTable[i][x]);
				}else{
					System.out.print("  "+sTable[i][x]);
					writer.print("  "+sTable[i][x]);
				}
			}
			System.out.println();
			writer.println();
		}

		for(Integer c: hex)
			list.add(c);


		for( int j1=0; j1< 16; j1++){
			
			for( int x = 0; x< 16 ; x++){
				
				bias1[j1][x] = BigDecimal.valueOf(sTable[j1][x]).divide(new BigDecimal("16"));
			}

		}
		
		System.out.println("\n\t\t\t\t\t\t\t\t\tBias Table");
		writer.println("\n\t\t\t\t\t\t\t\t\tBias Table");
		
		for( int j1=0; j1< 16; j1++){
			
			for( int x = 0; x< 16 ; x++){
				
				if(bias1[j1][x].toString().length() == 5){

						System.out.print("  |"+hex1[j1] + hex1[x]+ "   "+ bias1[j1][x]);
						writer.print("  |"+hex1[j1] + hex1[x]+ "   "+ bias1[j1][x]);
					
					
				}else if(bias1[j1][x].toString().length() == 4){
	
						System.out.print("  |"+hex1[j1] + hex1[x] +"    "+ bias1[j1][x]);
						writer.print("  |"+hex1[j1] + hex1[x] +"    "+ bias1[j1][x]);
					
				}else if(bias1[j1][x].toString().length() == 3){
					

						System.out.print("  |"+hex1[j1] + hex1[x] +"     "+ bias1[j1][x]);
						writer.print("  |"+hex1[j1] + hex1[x] +"     "+ bias1[j1][x]);
					
					
				}else if(bias1[j1][x].toString().length() == 2){
	
						System.out.print("  |"+j1 + x +"      "+ bias1[j1][x]);
						writer.print("  |"+j1 + x +"      "+ bias1[j1][x]);
					
				}else if(bias1[j1][x].toString().length() == 1){
	
						System.out.print("  |"+hex1[j1] + hex1[x] +"       "+ bias1[j1][x]);
						writer.print("  |"+hex1[j1] + hex1[x] +"       "+ bias1[j1][x]);
						
				}
				
			}
			System.out.println();
			writer.println();
		}
		
		writer.close();

	}

	public static void main(String[] arg){
		LinearAttack attack = new LinearAttack();
	}

}
