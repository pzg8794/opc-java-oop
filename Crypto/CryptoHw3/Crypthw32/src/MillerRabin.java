import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;



public class MillerRabin{

	private  ArrayList<Long> at = new ArrayList<Long>();
	private  ArrayList<Long> as = new ArrayList<Long>();
	private ArrayList<Integer> isPrime = new ArrayList<Integer>();
	
	
	public static void main(String[] args){
		
		TreeSet<MillerRabin> set = new TreeSet<MillerRabin>();
		
		for( long n = 1; n < 100010 ; n++ ){
			MillerRabin prime = new MillerRabin();
			prime.millerR(0, n);
			System.out.println(prime.isPrime);
		}

	}
	
	
	
	public ArrayList<Integer> millerR( long a, long n){
		
		
		for( long t = 1; t < 10 ; t++ ){

			a = n-1;
			long k = 0;

			if (n <= 1){
				as.add((long) 0);
				as.add(k);
				isPrime.add(0);
				System.out.println("1");
				return isPrime;

			}else if (n <= 3){ 
				as.add((long) 0);
				as.add(k);
				isPrime.add(1);
				System.out.println("2");
				return isPrime;

			}else if (n%2 == 0){ 
				as.add((long) 0);
				as.add(k);
				isPrime.add(0);
					System.out.println("3");
				return isPrime;
			}else {


				long m = n - 1;
				while (m % 2 == 0) {
					k++;
					m /= 2;
				}

				do{
					a= (new Random(a)).nextInt()%(n-1);

				}while(!((a <= (n-1)) && (a != 0) && (!at.contains(a))));
				at.add(a);

				if(a < 0)
					a = -1*a;

				as.add(a);
				as.add(k);
				System.out.println(a);

				long b = modpow(a, m, n);
				if( (b%n != 1) && b != n-1){
					int i = 1;
					while( i>(k-1)){
						b = modpow(b,2,n);
						if( b == 1){
							isPrime.add(0);
							return isPrime;
						}
						i++;
					}
					if( b != (n-1)){
						isPrime.add(0);
						return isPrime;
					}
				}
			}
		}

		isPrime.add(1);	
		return isPrime;
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
	
	
}