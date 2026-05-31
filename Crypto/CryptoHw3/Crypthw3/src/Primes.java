import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Primes implements Comparable<Primes>{


    private static long[] as2 = {2, 7, 12, 17, 22, 27, 32, 61};
    // random integer in (1, n-1)
	double prime = 0;
	double nPrime = 0;
	double pesentage = 0;
    private long a = 0;
    private long n = 0;
	private ArrayList<Integer> as = new ArrayList<Integer>();
	private int error;

	static // Create file 
	FileWriter fstream = null;
	static BufferedWriter cout;
    
    public static void main( String[] args){
    	
    	
		
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
//		@SuppressWarnings("resource")
//		Scanner sc = new Scanner(br);
		
		try {
			fstream = new FileWriter("out.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		cout = new BufferedWriter(fstream);

//		TreeSet<Primes> set = new TreeSet<Primes>();
//		for( int j = 100000; j < 101001; j++ ){
//			
//			Primes pPrime = new Primes();
//
//			// simple cases: 0, 1, 2, or evevn
//			if (pPrime.n == 0) 
//				pPrime.as.add(0);
//			if (pPrime.n == 1) 
//				pPrime.as.add(0);
//			if (pPrime.n == 2) 
//				pPrime.as.add(0);
//			if (pPrime.n%2 == 0) 
//				pPrime.as.add(0);
//
//			boolean temp;
//			// check if it's a witness
//			pPrime.error = 1000;
//			for( int i= 0; i< pPrime.error; i++){
//				pPrime.a = (new Random((pPrime.n)).nextLong());
//				temp=IsPrime(pPrime.n); 
//
//				if(temp)
//					pPrime.as.add(1);
//				else
//					pPrime.as.add(0);
//			}
//
//
//			int x = 0;
//			while( x != pPrime.as.size()){
//				if( pPrime.as.get(x) == 0)
//					pPrime.nPrime++;
//				else
//					pPrime.prime++;
//				x++;
//			}
//			
//			set.add(pPrime);
//			System.out.println("For n = " + j + " :");
//			System.out.println("PrimeC = "+ pPrime.prime + " NotPrimeC = "+ pPrime.nPrime);
//			System.out.println("PC% = "+ (pPrime.prime/pPrime.error) + " NPC% = " + (pPrime.nPrime/pPrime.error) + "\n");
//			
//			try {
//				cout.write("For n = " + j + " : \n");			
//				cout.write("PrimeC = "+ pPrime.prime + " NotPrimeC = "+ pPrime.nPrime + "\n");
//				cout.write("PC% = "+ (pPrime.prime/pPrime.error) + " NPC% = " + (pPrime.nPrime/pPrime.error) + "\n\n");
//				
//				//	for( int s=0; s<10; s++){
//				System.out.println(set);
//				cout.write(set.toString() + "\n");
//				
//				//	}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		int[] array = pPrime.primes(100000, 101001);
//		int i = 0;
//		while(i != array.length){
//		System.out.println(array[i]);
//		i++;
//		}
	
		TreeSet<Primes> set = new TreeSet<Primes>();
		Primes pPrime = new Primes();
		for( int j = 100000; j < 100010; j++ ){
			
			Primes pPrime1 = new Primes();
			System.out.println(pPrime1.IsPrime(j));
			System.out.println(pPrime1.as);

		}
		
		int[] array = pPrime.primes(100000, 101001);
		int i = 0;
		while(i != array.length){
		System.out.println(array[i]);
		i++;
		}
	
    }

    private static long modpow(long x, long c, long m) {
        long result = 1;
        long aktpot = x;
        while (c > 0) {
            if (c % 2 == 1) {
                result = (result * aktpot) % m;
            }
            aktpot = (aktpot * aktpot) % m; 
            c /= 2;
        }
        return result;
    }

    private boolean millerRabin(long n) {
        outer:
        for (int i=0; i< 10; i++) {
        	do{
        	a= (new Random(n)).nextLong();
        	}while( a > (n-1));
        	
            if (a < n) {
                long s = 0;
                long d = n - 1;
                while (d % 2 == 0) {
                    s++;
                    d /= 2;
                }

                long x = modpow(a, d, n);
                if (x != 1 && x != n - 1) {
                    for (long r = 1; r < s; r++) {
                        x = (x * x) % n;
                        if (x == 1) {
                        	as.add(0);
                            return false;
                        }
                        if (x == n - 1) {
                            continue outer;
                        }
                    }
                    as.add(0);
                    return false;
                }
            }
        }
    	as.add(1);
        return true;
    }


    public boolean IsPrime(long num) {
        if (num <= 1) {
            return false;
        } else if (num <= 3) {
            return true;
        } else if (num % 2 == 0) {
            return false;
        } else {
            return millerRabin(num);
        }
    }
    public int[] primes(int min, int max) {
           ArrayList<Integer> primesList = new ArrayList<Integer>();

           for( int i=min; i<max; i++ ){
                if( IsPrime(i) ){
                   primesList.add(i);
                }
           }

           int[] primesArray = new int[primesList.size()];
           for(int i=0; i<primesArray.length; i++){
               primesArray[i] = (int) primesList.get(i);
           }

           return primesArray;
        }


    public static String tostring (int [] arr){
        String ans="";
        for (int i=0; i<arr.length;i++){
            ans= ans+arr[i]+ " ";
        }
        return ans;
    }
     public int closestPrime(int num) {
            int count=1;    
            for (int i=num;;i++){

                int plus=num+count, minus=num-count;
                if (IsPrime(minus)){

                    return minus;

                }

                if (IsPrime(plus)) {
                    return plus;

                }
                count=count+1;
            }
        }

 	@Override
 	public int compareTo(Primes o) {
 		Primes obj = (Primes)o;
 		if((this.prime > obj.prime))
 			return -1;
 		else if(this.prime > obj.prime)
 			return 1;
 		
 		return 0;
 	}
 	
 	public boolean equals( Object obj){
 		Primes temp = (Primes)obj;
 		return(( temp.nPrime == this.nPrime) && (this.prime == temp.prime));
 	}
 	
 	public String toString(){
 		return "PrimeC = "+ prime + " NotPrimeC = "+ nPrime
 		+ " PC% = "+ (prime/error) + " NPC% = " + (nPrime/error);
 	}
    }   

//end class