
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;



public class MillerR implements Comparable<MillerR> {

	private double prime = 0;
	private double nPrime = 0;
	private int counter = -1;
	private BigDecimal counter1;
	private BigDecimal counter2;
	private long n =0;
	private ArrayList<Long> isPrime = new ArrayList<Long>();
	private static TreeSet<MillerR> list = new TreeSet<MillerR>();
	private ArrayList<A> a = new ArrayList<A>();
	private A as;



	public boolean checkWitness(long a, long n) {

		int k = 1;
		long m = (n-1)/2;
		while (m%2 == 0) {
			m = m/2;
			k++;
		}

		long b = modpow(a, m, n);
		for (int i = 0; i < k; i++) {
			long bnew = (b*b)%(n);
			if (bnew == 1 && !(b == 1) && !(b == (n - 1))){
				// System.out.println(1);
				prime++;
				this.a.get(counter).prime++;
				return true;
			}
			b = bnew;
		}

		if(b == 1){
			prime++;
			this.a.get(counter).prime++;
			//        	System.out.println(1);
		}else{
			nPrime++;
			this.a.get(counter).nPrime++;
		}
		return !(b==1);

	}

	private static long modpow(long a, long e, long n) {
		long result = 1;
		long temp = a;
		while (e > 0) {
			
			if (e % 2 == 1) 
				result = (result * temp) % n;
			
			temp = (temp * temp) % n; 
			e /= 2;
		}
		return result;
	}


	public boolean is_pPrime(long n) {
		if (n <= 1 || (n%2 == 0)) {
			//  System.out.println(0);
			nPrime++;
			return false;

		}else if (n <= 3){
			// System.out.println(1);
			prime++;
			return true;
		}
		else{
			// repeat 100 times for error 2^1000
			for (int i = 0; i < 10000; i++) {

				long a = 0;
				do {
					
					a = new Random().nextInt((int) (n-1));
					
				} while (a <= 1 || a >= (n-1));

				as = new A(a);
				this.a.add(as);
				counter++;
				isPrime.add(a);
				// check if it's a witness
				if (checkWitness(a, n)) {
					// System.out.println(0);
					this.a.get(counter).nPrime++;
					nPrime++;
					
					return false;   // for definitely composite
				}
			}

			//        System.out.println(1);
			this.a.get(counter).prime++;
			prime++;
			return true;   
		}// for probably prime
	}


	public static void main(String[] args) throws IOException {

		PrintStream cout = new PrintStream(new FileOutputStream("output.txt"));
		System.setOut(cout);
		
		try {

			for( long n = 100000; n< 101000; n++ ){

				MillerR pPrime = new MillerR();
				pPrime.n = n;
				boolean answer = pPrime.is_pPrime(n);
				
				BigDecimal prime = BigDecimal.valueOf(pPrime.prime);
				BigDecimal notPrime = BigDecimal.valueOf(pPrime.nPrime);

				pPrime.counter2 = new BigDecimal(pPrime.prime/4);
				pPrime.counter1 =  new BigDecimal(pPrime.nPrime/4);
				
				list.add(pPrime);
		
				cout.println(" Is " + n + " Prime ? " + answer);
				cout.println("Not Prime Counter (NP)C: "+ notPrime);
				cout.println("Prime Counter (P)C:"+ prime);
				
				cout.println("%Counter NP: "+ pPrime.counter1);
				cout.println("%Counter P:"+ pPrime.counter2 + "\n");	

				
				for(A i: pPrime.a){
					cout.println(" A's " + i );
					cout.println("Not Prime Counter: "+ i.nPrime);
					cout.println("Prime Counter :"+ i.prime+"\n\n");
				}
			}
			
			cout.println(list.descendingSet());
			for( int i=0; i<10; i++)
				cout.println("Biggest Porcentage Error Numbers : " + list.pollLast());
			
			ArrayList<MillerR> temp = new ArrayList<MillerR>();
			for( MillerR s: list.descendingSet()){
				
				if(!temp.contains(s) && temp.size()<=10)
					temp.add(s);
			}
			
			
			cout.println("\n\nhighest Probabilities According to its Percentage and Lowest Value: ");
			cout.println("\n"+temp);
			
			cout.println("\n\nPrime Numbers According to highest Probabilities : ");
			for( MillerR s: list.descendingSet()){
				
				if( s.prime == temp.get(0).prime){
					cout.println(s.n);
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (cout != null) {
				cout.flush();
				cout.close();
			}
		}
	}

	@Override
	public int compareTo(MillerR o) {
		if( (this.counter1.add(this.counter2)).compareTo(o.counter1.add(o.counter2)) == 1){
			return 1;
		}if( (this.counter1.add(this.counter2)).compareTo(o.counter1.add(o.counter2)) == -1)
			return -1;
		else {
			if( this.n < o.n)
				return 1;
			else
				return -1;
		}
	}
	
	public boolean equals(Object obj){
		MillerR tp = (MillerR)obj;
		return( this.counter1.add(this.counter2).equals(tp.counter1.add(tp.counter2)));
	}
	
	
	public String toString(){
		return n +  " NP: " + counter1 + "  P: " + counter2;
	}

}