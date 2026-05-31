import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class TestAlgorithms {

	private int choice = 0;


	public void setCh(int x){
		choice = x;
		System.out.println(x);
	}

	public static void main(String[] args){

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		TestAlgorithms test = new TestAlgorithms();

		System.out.println("Please Enter the Algorithm You Wish to Run:");
		test.setCh(in.nextInt());
		try {
			test.test();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test() throws FileNotFoundException	{

		long startTime = System.currentTimeMillis();

		int NumberofTestcases=4;
		long totalTime=0;

		GenerateInputs nr1 = new GenerateInputs();

		File file = null;

		if(choice == 0)
			file = new File("/Users/pitergarcia/SkyDrive/workspace/AlgoExpProject/src/NaiveRecursive.txt");

		else if( choice == 1)
			file = new File("/Users/pitergarcia/SkyDrive/workspace/AlgoExpProject/src/DynamicAV.txt");

		else if(choice == 2)
			file = new File("/Users/pitergarcia/SkyDrive/workspace/AlgoExpProject/src/MemoizationAV.txt");
		else
			file = new File("/Users/pitergarcia/SkyDrive/workspace/AlgoExpProject/src/QuadraticAV.txt");



		// if file doesnt exists, then create it
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);

		try {
			bw.write("Recursive Calls" + " " + "CPU TIME (ms)" + " " + " LCS" +  "\t" + "ISL1" + "\t " + "ISL2\n");
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		for(int i=0; i<NumberofTestcases; i++){	

			try {

				nr1.GenerateTestcases();

			} catch (Exception e1) {
				System.out.println("The Java Heap Source is Out of Memory");
				System.exit(0);
			}

			long t = 0;
			long t1 = System.currentTimeMillis();

			LCS lcs = null;
			try{
				lcs = new LCS(nr1.getStr1(), nr1.getStr2(), choice);

			}catch(Exception e){

				System.out.println("The Java Heap Source is Out of Memory");
				System.exit(0);
			}

			long t2 = System.currentTimeMillis();
			t = t2 - t1;
			totalTime+=t;
			//long endTime=System.nanoTime();

			try {
				bw.write(lcs.getRC() + "\t   " + (t) + "\t" + lcs.getLCS() + "\t" +  nr1.getStr1l() + "\t  " + nr1.getStr2l() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	

		long endTime = System.currentTimeMillis();
		
		try {
			bw.write("\n Total Time:");
			bw.write(totalTime + "mSec");

			bw.write("\n Total Time Elapsed:");
			bw.write((endTime - startTime) + "mSec\n");
			bw.write("\n Total Time Elapsed:");
			bw.write((endTime - startTime)/100 + "Sec");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
