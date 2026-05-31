import java.util.List;

/**
 * 
 * @author piter garcia
 *
 */
public class InverseGF 
{

	static int[][] matrix = { 
		{1, 0, 0, 0, 1, 0, 0, 0},
		{1, 1, 0, 0, 0, 0, 0, 0},
		{1, 1, 1, 0, 0, 0, 0, 0}, 
		{1, 1, 1, 1, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 0, 0, 0},
		{0, 1, 1, 1, 1, 0, 0, 0},
		{0, 0, 1, 1, 1, 0, 0, 0},
		{0, 0, 0, 1, 1, 0, 0, 0}};

	public static void main ( String [ ] args ) 
	{
//		H.msg( "Problem 1 : " ) ;
		System.out.println("\n Inverse Problem Calculation:");
		System.out.println(" Extended Euclidean Algorithm Approach:");
		System.out.println(" gcd(x,y) = 1");
		System.out.println(" gcd, where x*a + y*b = 1 \n where a = x^-1 and b = y^-1\n");
		
		//Set up the upper bound of the coefficients
		Integral N = new Integral (2) ;
		
		//Set up the zero coefficients .
		System.out.println("\n Input Polynomial Values:");
		GaloisField<Integral> zero = new GaloisField<Integral>(N) ;
		Poly p1 = new Poly ( zero ) ;
		p1.setCoefficients( 1, 1, 0, 1, 1, 0, 0, 1, 1) ;
		System.out.println ( " x = " + p1 ) ;
		Poly p2 = new Poly ( zero ) ;
		p2.setCoefficients (0, 1, 0, 0, 1, 1, 0, 1, 0) ;
		System.out.println ( " y = " + p2 ) ;
		
		// Carry out the extended GCD algorithm .
		GCD<Poly> GCD = new GCD<Poly >() ;
		List<Poly> results = GCD.gcd(p1, p2) ;
		Poly d = results.get (0) ;
		Poly g = results.get (1) ;
		Poly h = results.get (2) ;
		System.out.println( "\n Division Remainder = " + d) ;
		System.out.println( " b = " + h) ;
		System.out.println( " a = " + g ) ;
		int[] inverse = {0, 0, 0, 0, 0, 0, 0, 0};
		int[][] temp = new int[matrix.length][matrix.length];
		int[] key = {1, 1, 0, 0, 0, 1, 1, 0};
		int x = 0;
		
		for( GaloisField<Integral> i: g.getCoefficients())
			inverse[x++] = Integer.parseInt(i.toString());
		
		System.out.println("\n Inverse Input Binary Value: ");
		
		for(int j= inverse.length-1; j>= 0; j-- )
			System.out.print(" " +inverse[j]);
		
		System.out.println("\n\n Matrix:");
		for( int x1 =0; x1 < 8; x1++)
		{
			System.out.print(" ");
			for( int y=0; y < 8; y++)
			{
				temp[x1][y] = matrix[x1][y]*inverse[y];
				System.out.print(temp[x1][y]);
			}
			
			System.out.println();
		}
		
		int tp = 0;
		int output1[] =  new int[8];
		
		System.out.println("\n Matrix * Inverse");
		
		for( int x1 =0; x1 < matrix.length; x1++)
		{
			tp = xor(temp[x1][0], temp[x1][1]);
			
			for( int y=2; y < matrix.length; y++)
			{
				tp = xor(tp, temp[x1][y]);
			}
			
			output1[x1] = tp;
			System.out.print(" " + tp);
			System.out.println();
		}
		
		
		tp = 0;
		int output2[] =  new int[8];
		
		System.out.println("\n Output:");
		
		for( int x1 =0; x1 < matrix.length; x1++){
			tp = xor(output1[x1], key[x1]);
			System.out.print(" " + tp);
			output2[x1] = tp;
			System.out.println();
		}
	}
	
	
	public static int xor(int x, int y)
	{
		if( x == y)
			return 0;
		else
			return 1;
	}
}