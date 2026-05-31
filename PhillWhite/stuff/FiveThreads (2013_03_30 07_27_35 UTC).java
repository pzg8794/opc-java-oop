import java.util.Random;

//Write a program that runs 5 threads, each thread randomizes a number between 1 and 10. 
//The main thread waits for all the others to finish, calculates the sum of the numbers 
//which were randomized and prints that sum. You will need to implement a Runnable class 
//that randomizes a number and store it in a member field. When all the threads have done, 
//your main program can go over all the objects and check the stored values in each object.

public class FiveThreads implements Runnable{

	int randomNumber;
	static int sum;
	
	
	@Override
	public void run() {
		Random num = new Random();
		randomNumber = num.nextInt(10);
		System.out.println(randomNumber);
		
		sum = randomNumber + sum;
		
	}
	
	public static int getSum(){
		
		
		return sum;
	}
	
	public static void main ( String[] args){
		
		for( int i = 0; i < 5; i++){
			
			Thread test = new Thread(new FiveThreads());
			test.start();
			
			try {
				test.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		System.out.println(getSum());
		
	}

}
