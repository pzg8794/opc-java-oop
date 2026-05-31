/**
 *  
 * @author piter garcia
 *
 */
public class LCS {

	private static char DIAGONAL = 'd'; 
	private static char LEFT = 'l';
	private static char UP = 'u';
	private char[][] b; //the b table
	private int[][] c; //the c table

	private String str1,str2;
	private int lcs;
	private String Slcs;
	private int recursiveCalls=0;

	/**
	 * 
	 * @return
	 */
	public LCS(String x, String y, int ch){
		str1 = x;
		str2 = y;
		recursiveCalls=0;

		//long startTime=System.nanoTime();
		try{

			if( ch == 0)
				lcs = LCSn(x.length()-1, y.length()-1);

			if( ch == 1){
				lcsLength(x, y);
				Slcs = LCSd(x, x.length()-1, y.length()-1);
				lcs = Slcs.length();

			}
			if( ch == 2){
				Memoization(str1, str2);
				lcs = LCSm(str1.length()-1, str2.length()-1); //computes & prints out the result
			}

		}catch(Exception e){

			System.out.println("The Java Heap Source is Out of Memory");
			System.exit(0);
		}
	}


	/**
	 * 
	 * @param xLen
	 * @param yLen
	 * @return
	 */
	public int LCSn(int xLen, int yLen)throws Exception{

		recursiveCalls++;

		if (xLen==-1||yLen==-1)
			return 0;

		else 
			if(this.str1.charAt(xLen) == this.str2.charAt(yLen))
				return 1+LCSn(xLen-1, yLen-1);

			else
				return max(LCSn(xLen-1,yLen),LCSn(xLen,yLen-1));	
	}


	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int max(int a, int b){

		return (a >b)?a :b;
	}



	/**
	 * Constructs the c and b tables. 
	 * The c tablewill be used in order to find the length of the LCS
	 * The b table will be used in order to construct the LCS.
	 * 
	 * @param x the first input string
	 * @param y the second input string
	 */
	public void lcsLength(String x, String y) {
		int m = x.length();
		int n = y.length();
		b = new char[m][n];
		c = new int[m+1][n+1];

		// Initialize the c table.
		for(int i=1; i<=m; i++) {
			c[i][0] = 0;
			for(int j=0; j<=n; j++) {
				c[0][j] = 0;
			}
		}

		// Construct the c and b tables.
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(x.charAt(i) == y.charAt(j)) {
					c[i+1][j+1] = c[i][j]+1;
					b[i][j] = DIAGONAL;
				}else if(c[i][j+1] >= c[i+1][j]) {
					c[i+1][j+1] = c[i][j+1];
					b[i][j] = UP;
				}else{
					c[i+1][j+1] = c[i+1][j];
					b[i][j] = LEFT;
				}
			}
		}
	}//lcsLength

	
	
	/**
	 * Recursive method to print out the resulting LCS
	 * @param X the string so far
	 * @param i the current row
	 * @param j the current column
	 * @return The resulting LCS
	 */
	public String LCSd(String X, int i, int j) {
		recursiveCalls++; //keeps track of calls to printLCS 
		String retVal = "";
		
		if(i < 0 || j < 0) {
			retVal = "" + retVal;

		} else {
			if(b[i][j] == DIAGONAL) {
				retVal += LCSd(X, i-1, j-1);
				retVal += str1.charAt(i); 

			} else if(b[i][j] == UP){
				retVal += LCSd(X, i-1, j);

			} else {
				retVal += LCSd(X, i, j-1);
			}
		}

		return retVal;
	}//printLCS



	/**
	 * This method computes the LLCS. It first checks to see if an
	 * answer to the specific subproblem exists and and skips recomputing
	 * it. If an answer has not been found, it computes one.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int LCSm(int i, int j){

		recursiveCalls++; //keeps track of calls to printLCS 

		if(i == -1 || j == -1) {
			return 0;
		}else if(c[i][j] == -1) {
			if (str1.charAt(i) == str2.charAt(j)){
				c[i][j] = 1 + LCSm(i-1,j-1);
			}
			else {
				c[i][j] = Math.max(LCSm(i-1,j), LCSm(i,j-1));
			}
		}
		return c[i][j];
	}



	/**
	 * Generic constructor
	 * @param x string X
	 * @param y string Y
	 */
	public void Memoization(String x, String y) {
		str1 = x;
		str2 = y;
		c = new int[str1.length()][str2.length()];

		for(int i=0; i<str1.length(); i++) {
			for(int j=0; j<str2.length(); j++) {
				c[i][j] = -1;
			}
		}
		recursiveCalls = 0;
	}


	/**
	 * Return the # of recursive calls
	 * @return how many times printLCS was called.
	 */
	public long getRC(){
		return recursiveCalls++;
	}//getLCSCounter


	public int getLCS() {
		return lcs;
	}

	public String getSLCS() {
		return Slcs;
	}
	
	public String getStr1(){
		return str1;
	}
	
	public String getStr2(){
		return str2;
	}
}