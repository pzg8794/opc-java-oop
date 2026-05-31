
public class GCD {

	
	
	public static void main(String[] args){
		
		int a = 1;
		int i = 0;
		int b = 26;
		int keyNum = 0;
		int x[] = {26, 29, 99, 1024};


		do{
			a = 1;
			b= x[i];
			keyNum = 0;
			
			while( a != b){

				int tmp = gcd(a, b);

				if( tmp == 1){
					//	System.out.println(tmp+"\n");
					keyNum++;
				}
				//	else
				//	System.out.println("None\n");
				a++;
			}

			System.out.println("For Zm = " + b + ",  # with Same GCD: " + keyNum + ",  Number of Keys: "+ keyNum*b);
		
		}while( i++ != x.length-1);

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
}
