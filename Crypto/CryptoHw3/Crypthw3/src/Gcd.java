import java.math.BigInteger;

public class Gcd {

	private Integer gcd;
	private final static BigInteger one = new BigInteger("1");
	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger modulus;

	// N-bit (roughly) public and private key
	Gcd(int N) {
		BigInteger p = BigInteger.valueOf(7919);
		BigInteger q = BigInteger.valueOf(17389);
		BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

		modulus    = p.multiply(q);                                  
		publicKey  = new BigInteger("66909025");    
		privateKey = publicKey.modInverse(phi);
	}

	public Gcd(Integer a, Integer b){
		setGcd(gcd(a, b));
	}

	public static void main(String[] args){
		
		int e = 66909025;
		int[] m = {12345};
		int p = 7919;
		int q = 17389;
		int n= p*q;
		int phi = (p-1)*(q-1);
	
		System.out.println("For n = " + n + "" +
				"\nMeassure of Integers that Share the Factor of 1: " + phi);

		System.out.println("N = PQ = "+ pq(n, phi));
		int d = findD(phi, e);

		System.out.println("P = "+ p);
		System.out.println("Q = "+ q);
		System.out.println("d = "+ d);

		System.out.println("Message is: ");
		System.out.println("C =: "+m[0]);

		Gcd key = new Gcd(n);
		System.out.println(e);

		BigInteger message = BigInteger.valueOf(12345);

		BigInteger encrypt = key.encrypt(message);
		BigInteger decrypt = key.decrypt(encrypt);
		System.out.println("message   = " + message);
		System.out.println("encrpyted = " + encrypt);
		System.out.println("decrypted = " + decrypt);

		BigInteger encrypt1 = key.encrypt(encrypt);
		BigInteger decrypt1 = key.decrypt(encrypt1);
		System.out.println("message   = " + encrypt);
		System.out.println("encrpyted = " + encrypt1);
		System.out.println("decrypted = " + decrypt1);
	}

	
	public BigInteger encrypt(BigInteger message) {
		return message.modPow(publicKey, modulus);
	}

	public BigInteger decrypt(BigInteger encrypted) {
		return encrypted.modPow(privateKey, modulus);
	}


	public static int sQaM(int m, int e, int n){
		
		int temp = 1;
		
		while( e > 0){
			if( e%2 == 1)
				temp = mod((temp*m), n);
		
			e = e/2;
			m = mod((m*m), n);
			if ( m < 0)
				m += n;
		}

	return temp;
	}

	public static int encrypt(int m, int e, int n){

		Integer c = 0;

			int temp = sQaM(m, e, n);
			c = temp;
			
		return c;
	}


	public static int phi(int n){
		int phi = 0;
		int a = 1;
		while( a != n){

			int tmp = gcd(a, n);

			if( tmp == 1)
				phi++;

			a++;
		}
		return phi;
	}

	public static int inverse(int alp){
		int x=0;
		alp %= 26;
		while(true){

			if((alp*x)%26 == 1%26){
				return x;
			}
			x++;
		}
	}

	public static int findD(int phN, int e){
		int d = 0;
		// d = 5349

		while( d*e%phN != (1%phN)){
			d++;
		}

		return d;
	}
	public static Integer mod(Integer a, Integer b){
		Integer temp = 0;

		temp = a/b;
		temp *= b;
		temp = a - temp;

		return temp;
	}

	public static int pq(Integer n, Integer phn){

		int temp = ( n - phn + 1);
		//	System.out.println(temp);	
		//	temp = (int) Math.pow((Math.pow(n - phn + 1, 2) - 4*n), 0.5);
		//	System.out.println(temp);

		int p1= (int)((temp + Math.pow((Math.pow((n - phn + 1), 2) 
				- 4*n), 0.5))/2);
		int q1= (int)((temp - Math.pow((Math.pow((n - phn + 1), 2)
				- 4*n), 0.5))/2);
		return p1*q1;
	}


	public static int gcd(int a, int b){

		//	System.out.println(" number is : " + a);
		while( a != b){

			if( a > b)
				a -= b;
			else
				b -= a;

		}
		return a;

	}

	public Integer getGcd() {
		return gcd;
	}

	public void setGcd(Integer gcd) {
		this.gcd = gcd;
	}
}
