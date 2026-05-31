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
import java.util.Scanner;


/**
 * This program creates a class called Matrix.
 * This class multiplies to Matrix of M rows and n columns with 
 * another matrix of n rows and m or n columns. 
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
class Matrix extends Thread{
  
  static int matrix1 = 0;	// initializing value of first matrix
  static int matrix2 = 0;	// initializing value of second matrix
  static int i = 0;			// counter
  static int sum = 0;		// initializing the sume of the value of the sum of both matrixes.
  static int multiply[][] = new int[100][100];	// initializing result matrix; 

  

  /**
  * @Function: Matrix (matrix1 value, matrix2 value)
  * Assigns a value to the variable matrix 1 and 2 to be  multiplied
  * and summed to later add the result in the result matrix (mulitply [][])
  * @param Object Designates the object which has to be added.
  */
  	public Matrix (int matrix1, int matrix2) {
                Matrix.matrix1 = matrix1;
                Matrix.matrix2 = matrix2;
  	}
  
  	
 
    /**
  	  * @Function: run()
  	  * runs function to be handle by each thread and executed by the scheduler.
  	  * 
  	  * @param designates the value to be added in the new matrix.
  	  */
  	public void run () {
  		multiplyingMatrixes(matrix1, matrix2);
  	}
  
  	
  	
    /**
  	  * @Function: multiplyingMatrixes(matrix1 value, matrix2 value){
  	  * multiplies both first and second matrix values three times.
  	  * Adds each multiple and saves it in the a variable called sum.
  	  * 
  	  * @param Designates the value to be placed in the new matrix.
  	  */
  	public void multiplyingMatrixes(int matrix1, int matrix2){
    
        sum = sum + matrix1*matrix2;                  
    }
  
  	
  	
    /**
  	  * @Function: displayResult([][] multiply)
  	  * Displayes values of the new matrix obtained from the previous first and second matrixes.
  	  * 
  	  * @param outputs matrix result.
  	  */
  	public static void displayResult(int [][] multiply){
    
  		System.out.println("Product of Entered Matrices: ");
 
         for ( int c = 0 ; c < 1 ; c++ ){
            for ( int d = 0 ; d < 3 ; d++ )
               System.out.print(multiply[c][d]+"\t");
         }
  	}
  
  	
  	
    /**
  	  * @Function: main(String[] args)
  	  * ask the user to input data for m x n matrixes, then multiplies them.
  	  * creates threads to multiply each value in each matrix and sum them up
  	  * create a matrix called multiply that will contained the result matrix.
  	  * 
  	  * @param Desgnates the resulting matrix, from the multiplying the first and second matrixes,
  	  * in a new matrix called multiply.
  	  */
  	public static void main(String[] args) throws InterruptedException {
      int m, n, p, q, c, d, k;
 
      
      //Getting size for the first matrix
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the Number of Rows and Columns of first matrix");
      m = in.nextInt();
      n = in.nextInt();
 
      int first[][] = new int[m][n]; // Creating first matrix
      
      // Geting values for the first matrix from the user.
      System.out.println("Enter the Values for the First Matrix");
 
      for ( c = 0 ; c < m ; c++ )
         for ( d = 0 ; d < n ; d++ )
        	 	first[c][d] = in.nextInt();
 
      // Getting size for the second matrix
      System.out.println("Enter Number of Rows and Columns of Second Matrix");
      p = in.nextInt();
      q = in.nextInt();
 
      
      
      //Getting values for the second matrix and making sure the second matrix has the same amount
      // of rows as the same amount of colums of the first matrix.
      if ( n != p )
         System.out.println("Note: Matrices with different number of columns and rows" +
         										" cannot be multiplied with each other.");
      else {
    	  
    	  int second[][] = new int[p][q];
    	  System.out.println("Enter Values for Second matrix");
 
    	  for ( c = 0 ; c < p ; c++ )
    		  for ( d = 0 ; d < q ; d++ )
                  second[c][d] = in.nextInt();
         
         
    	  //Loops to create a thread that will multiply each value of the matrixes and save
    	  // in a variable called sum to be added to a new matrix later.
    	  for ( c = 0 ; c < m ; c++ ){
        	 	for ( d = 0 ; d < q ; d++ ){   
                   		 for ( k = 0 ; k < 3 ; k++ ){
                   			 	int temp1 =  first[c][k];
                   			 	int temp2 = second[k][d];
                     
                   			 	Matrix obj= new Matrix(temp1, temp2);
                   			 	obj.start();
                   			 	obj.join();
                   		 }  
                   		 
                   		 //adding value in the new matrix
                   		 multiply[c][d] = sum;
                   		 sum = 0;
                   	 }
          		  }
    	  			// displaying new matrix
          		  displayResult(multiply);
      }
   }
}
