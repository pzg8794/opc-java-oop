import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Memoization.java
 * This class provides the implementation for the Recursive Algorithm with 
 * Memoization that solves the Longest Common Subsequence (LCS) problem. 
 * This implementation finds the LLCS.
 * 
 */
public class MemoizationAV {


	/**
	 * The main method for the algorithm
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		int NumberofTestcases=12;
		long totalTime=0;

		GenerateInputs nr1 = new GenerateInputs();

		PrintStream cout = null;
		try {
			cout = new PrintStream(new FileOutputStream("MemoizationAV.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(cout);

		cout.println("Recursive Calls" + " " + "CPU TIME (ms)" + " " + " LCS" +  "\t" + "ISL1" + "\t " + "ISL2");

		for(int i=0; i<NumberofTestcases; i++){	

			try {
				nr1.GenerateTestcases();
			} catch (Exception e1) {
				System.out.println("The Java Heap Source is Out of Memory");
				System.exit(0);
			}

			long t = 0;
			long t1 = System.currentTimeMillis();
			//			int lcs = 0;
			//long startTime=System.nanoTime();

			LCS lcs = null;
			try{

				lcs = new LCS(nr1.getStr1(), nr1.getStr2(), 2);

			}catch(Exception e){

				cout.println("The Java Heap Source is Out of Memory");
				System.exit(0);
			}
			long t2   = System.currentTimeMillis();
			t = t2 - t1;
			totalTime+=t;
			//long endTime=System.nanoTime();

			cout.println(lcs.getRC() + "\t   " + (t) + "\t" + lcs.getLCS() + "\t" +  nr1.getStr1l() + "\t  " + nr1.getStr2l());
		}	

		long endTime = System.currentTimeMillis();

		System.out.println("\n Total Time:");
		System.out.printf ("%d msec%n", totalTime);

		System.out.println("\n Total Time Elapsed:");
		System.out.printf ("%d msec%n", endTime - startTime);
		System.out.printf ("%d sec%n", (endTime - startTime)/1000);

	}

}