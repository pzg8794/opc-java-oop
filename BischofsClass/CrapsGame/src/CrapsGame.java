/*
 *  Created:      10/19/2012
 *  Last Changed: 10/19/2012
 *  
 *  Test.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */
import java.util.Random;
import java.util.Scanner;

public class CrapsGame {

	//sum= sum of dies
	static int sum;
	static int sum2;
	static int round=0;
	static int key=1;
	static int bet_number;
	static int die1;
	static int die2;
	private double Money_bet;
	static double Total_Money2;
	static double Total_Money=0;
	
	public void moneyToBet( ){
		
	 	@SuppressWarnings("resource")
	 	Scanner in = new Scanner(System.in);
	 	
		//Starting Game
	 	System.out.println(" << Welcome to the Craps Game >>\n ");
	 	System.out.println(" -----------------------------------\n\n ");

	 	Total_Money2 = Total_Money = 1000;
	 	System.out.println("You have a fund of $" + Total_Money2 + " dollars \n Do you want to play ? \n If so enter any number, otherwise enter 0\n ");
	 	Money_bet = in.nextInt();	// key variable to exit or play again	
	 	
		if(Money_bet!=0){								//Testing if you are playing or not
			
			System.out.println(" Please enter below the Money you wish to bet \n ");
			Money_bet = in.nextInt();
		

			if(Money_bet>Total_Money || Money_bet<1){	//Testing you do not exceed your funds
				
					while(key!=0){
					
						if(Money_bet<1){						//Testing for minimum to bet
							
							System.out.print("\n\n Please enter another amount \n In this Casino a $1 dollar is the minimum you can bet \n ");
							Money_bet = in.nextInt();

							if(Money_bet>1 && Money_bet<Total_Money)
									key=0;
								else if(Money_bet>1 && Money_bet>Total_Money){
								
									System.out.print("\n\n  Sorry! You do not have enough funds to bet $"+ Money_bet +" dollars \n ");
									System.out.print(" You have $"+Total_Money+" dollars in funds to play \n ");
									System.out.print(" Please enter an amount that do not exceed your funds \n  ");
									Money_bet = in.nextInt();
							
									if(Money_bet<=Total_Money)
										key=0;
								}
							}
							else if (Money_bet>Total_Money){
						
								System.out.print("\n\n  Sorry! You do not have enough funds to bet $"+ Money_bet +" dollars \n ");
								System.out.print(" You have $1000 dollars in funds to play \n ");
								System.out.print(" Please enter an amount that do not exceed your funds \n  ");
								Money_bet = in.nextInt();
						
								if(Money_bet<=Total_Money)
									key=0;	
							}
						}	
					}		
			}
				System.out.print("\n\n");									//Main Loop
	}
	
	public void throwingDies(){
		
	 	@SuppressWarnings("resource")
	 	Scanner in = new Scanner(System.in);
	 	// setprecision(2);
	 	Random generator = new Random(); // activating random numbers for dies
	 	
		while(Money_bet!=0){   //THIS LOOP HAS TO GO IN ORDER FOR TO PLAYERS TO PLAY FAIRLY
			//You will be playing untill you have no money.
			  //Hint: I also use this function to allow you
			  //to leave the game at anytime.
			Total_Money-=Money_bet;

			round++;
			System.out.print("\n Round "+ round + " \n"); 
			System.out.print(" Please enter the number to which you are betting \n ");
			bet_number = in.nextInt();	;
			System.out.print(" Press any number to throw the dies \n ");
			sum = in.nextInt();	;
			System.out.print("\n Dies Rolling... \n ");//random sum of 2 dies
			die1= generator.nextInt(6) +1;	 
			die2= generator.nextInt(6) +1;	
			System.out.print("\n Die#1 \t Die#2 \n ");
			System.out.print(die1 + " \t " + die2 +"\n");
			sum=die1+die2;
			System.out.print("\n You have got a "+sum+" \n\n ");



			if(sum==7 || sum==11){				// winning loop

				System.out.print("You have won Round "+ round+" ! \n");

				System.out.print(" Now you have  won $"+Money_bet+" dollars \n\n");
				Money_bet*= 2;
			}

			else if(sum==2 || sum==3 || sum==12){ //lossing loop
	
				System.out.print("You have Lost Round "+ round +" !\n");
				System.out.print(" Therefore you have lost $"+Money_bet+" dollars \n\n");
				Money_bet= 0;
			}
			else{								 // points loop

				System.out.println("Round "+ round);
				System.out.print(" You Have "+sum+" Points \n You still can win! \n ");	
				System.out.print("\n Press any number to throw the dies \n ");
				sum2 = in.nextInt();
				System.out.print("\n Dies Rolling... \n ");//random sum of 2 dies
				die1= generator.nextInt(6) + 1;	 
				die2= generator.nextInt(6) + 1;	
				System.out.print("\n Die#1 \t Die#2 \n ");
				System.out.print(die1 + " \t" + die2 + "\n");
				sum2=die1+die2;
				System.out.print("\n You have got a "+sum2+" \n\n ");

				while(sum2!=0){	
					// Starting repeated loop untill winning or losing the round

					if (sum2!=7 && sum2!=sum){// Testing if it is a winning or losing sum 
	
						System.out.println("Round "+ round);
						System.out.print(" You Have "+sum+" Points \n\n Still can win! \n");	
						System.out.print(" Press any number to throw the dies \n ");
						sum2 = in.nextInt();
						System.out.print("\n Dies Rolling... \n ");//random sum of 2 dies
						die1= generator.nextInt(6)+1;	 
						die2= generator.nextInt(6)+1;	
						System.out.print("\n Die#1 \t Die#2 \n ");
						System.out.print(die1+ " \t "+die2+"\n");
						sum2=die1+die2;
						System.out.print("\n You have got a "+sum2+" \n\n ");
					}
					else if(sum2==sum){// Winning Loop
	
						System.out.print("You have won Round "+ round + " !\n");
		
						System.out.print(" Now you have won $"+ Money_bet +" dollars \n\n");
						Money_bet*= 2;
						sum2=0;
					}
					else if (sum2==7){//Losing Loop
	
						System.out.print("You have Lost Round "+ round +" !\n");
						System.out.print(" Therefore you have lost $"+Money_bet+" dollars \n\n");
						Money_bet= 0;
						sum2=0;
					}	
				}// Ending repeted loop 
			}				
													 
		} //End of Main Loop
	}
	
	
	public void keepPlaying(){
		
	 	@SuppressWarnings("resource")
	 	Scanner in = new Scanner(System.in);
		
		Total_Money+=Money_bet;

		if(Total_Money<1){					// Loop to keep playing After loosing funds

			System.out.println("\n\n The Round "+round+" is Over! ");// End of the Round
			System.out.print(" You have lost a fund of $"+Total_Money2+" dollars \n Do you want to refill your funds to keep playing ? \n If so enter any number, otherwise enter 0\n ");
			Money_bet = in.nextInt();		// key variable to exit or play again

			if(Money_bet!=0){// Loop to refill funds

				System.out.print("\n\n Please enter the amount you want to refill your funds with \n ");
				Total_Money = in.nextInt();	
				Total_Money2=Total_Money;
			}
		}
		else if (Total_Money!=0){								// Loop to keep playing After without loosing funds

			System.out.println("\n\n The Round "+round+" is Over! "); //End of the Round
			System.out.print(" You have $"+ Total_Money +" dollars left to Play \n Do you want to keep playing ? \n If so enter any number, otherwise enter 0\n ");
			Money_bet = in.nextInt();	// key variable to play again
		}

		if (Money_bet>0){					// Testing to play again until Money_bet = 0	

			// Starting a new game after first round

			System.out.print("\n\n  Welcome to the Craps Game Once Again \n "); 
			System.out.print(" You have $"+ Total_Money +" dollars in funds to play \n ");
			System.out.print(" Please enter below the Money you wish to bet \n  ");
			Money_bet = in.nextInt();	

			if	(Total_Money<Money_bet || Money_bet<1){		//Checking bet money does not exceed money in funds

				key=1;
				while(key!=0){ 
					//Repeated loop until the money you bet does not exceed your funds
					if (Money_bet<1){ //Minimum amount to bet
						System.out.print("\n\n Please enter another amount \n In this Casino a $1 dollar is the minimum you can bet \n ");
						Money_bet = in.nextInt();	

						if(Money_bet>1 && Money_bet<Total_Money)
							key=0;
						else if(Money_bet>1 && Money_bet>Total_Money){
		
							System.out.print("\n\n  Sorry! You do not have enough funds to bet $"+ Money_bet +" dollars \n ");
							System.out.print(" You have $"+Total_Money+" dollars in funds to play \n ");
							System.out.print(" Please enter an amount that do not exceed your funds \n  ");
							Money_bet = in.nextInt();	
	
							if(Money_bet<=Total_Money)
								key=0;
						}
					}
					else if (Money_bet>Total_Money){
	
						System.out.print("\n\n  Sorry! You do not have enough funds to bet $"+ Money_bet +" dollars \n ");
						System.out.print(" You have $"+Total_Money+" dollars in funds to play \n ");
						System.out.print(" Please enter an amount that do not exceed your funds \n  ");
						Money_bet = in.nextInt();	
	
						if(Money_bet<=Total_Money)
							key=0;
					}
				} // Ending repeated loop
			}//Ending new game test loop

		}	
	}
	public void endingGame(){
		//END  OF THE GAME
		
		if ( Total_Money>0 && Money_bet<=0 && round>0)	
		{
			if(Total_Money<Total_Money2)// Ending Game for winners
			{
				System.out.print("\n\n Sorry You have lost part of your funds! \n\n You have played a total of "+ round +" rounds \n");
				System.out.print(" You have won $0 dollars and your remaining funds are $" + Total_Money +" dollars \n\n");
				System.out.print(" Better luck nex time \n\n Thanks for Playing \n\n");
			}
			else// Ending Game for those who lose part of their funds
			{
				System.out.print("\n\n Congratulations ! \n\n You have played a total of "+ round +" rounds \n");
				System.out.print(" You have kept your funds of $" + Total_Money2 +" dollars \n");
				System.out.print(" You also have won a total of $" + (Total_Money - Total_Money2) + " dollars \n");
				System.out.print(" Therefore, you are leaving with a total amount of $"+ Total_Money +" dollars \n\n Thanks for Playing \n\n");
			}
		}
		else if( Total_Money<=0 && Money_bet<=0 || round<=0)// Ending Game for losers and those who decide to not play
			if(round<=0)// Testing if you want to start playing the first round or not
			{
				System.out.print(" You have played a total of "+ round +" round(s) \n");
				System.out.print(" Therefore you have not won or lost anything \n");
				System.out.print(" We hope to see you around soon \n\n Have a nice day! \n\n ");
			}
			else //Ending Game for losers 
			{
				System.out.print("\n\n Sorry ! \n\n You have played a total of "+ round +" round(s) \n");
				System.out.print(" and You have lost everything \n Good Luck for the next time! \n\n Thanks  for Playing \n\n ");
			}
	}
	 public static void main(String[] args){
	
		 CrapsGame playerOne = new CrapsGame();
		// CrapsGame playerTwo = new CrapsGame();
		 
		
				
				playerOne.moneyToBet();
			//	playerTwo.moneyToBet();
				playerOne.throwingDies();
			//	playerTwo.throwingDies();
				playerOne.keepPlaying();
			//	playerTwo.keepPlaying();
				playerOne.endingGame();	
			//	playerTwo.endingGame();	
		
	 }

}
