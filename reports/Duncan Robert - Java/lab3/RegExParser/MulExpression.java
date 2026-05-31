import java.util.Scanner;

/*
* MulExpression.java *
* Version:
* $Id: MulExpression.java,v 1.3 2013/03/26 17:07:45 pzg8794 Exp $
*
* Revisions:
* $Log: MulExpression.java,v $
* Revision 1.3  2013/03/26 17:07:45  pzg8794
* Updated Lab3
*
* Revision 1.2  2013/03/26 15:51:41  pzg8794
* Lab3 updated
*
*/


/** 
 * Represents multiplied expressions in Interp. Instances differ by the 
 * expressions whose values are to be multiplied.
 * 
 * @author Piter Garcia
 *
 */
public class MulExpression implements Expression {


          /*
           * variable containing the first subexpression.
           */
	  private Expression exp1;
	
          /*
           * variable containing the second subexpression.
           */
	  private Expression exp2;

	
	
	/**
	 * Construct a multiplied expression from the supplied subexpressions.
	 *
	 * @param exp1,exp2, parameters that contain first & second subexpressions.
	 */  
	public MulExpression( Expression exp1, Expression exp2){
		
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	
	
	/**
         * Generates code for a multiplied expression. The code is the code for each 
         * subexpression, surrounded by parentheses with a multiplication sign 
         * in between.
         *
         * @param (exp1.emit() + "*" + exp2.emit()), A string that represents 
         * the code for the expression.
         */
	public String	emit(){
		
		 return exp1.emit() + "*" + exp2.emit();
	}
	
	
	
	/**
         * Causes evaluation of a multiplied expression. The value is the 
         * multiplication of the values of the subexpressions.
         *
         * @param (exp1.emit() + "*" + exp2.emit()), An Integer, the 
         * multiplication of the values of the sub-expressions.
         */
	public Integer	evaluate(){
		 
		 return exp1.evaluate() * exp2.evaluate();
	}
	 
	
	
	/**
	 * The main method, it scans an expression to test the MulExpression class
	 * methods
	 * 
	 * @param args command line arguments (ignored).
	 *
	 */
	public static void main( String[] args){
		 
		String exp1, exp2;
		Scanner in = new Scanner(System.in);
		
   		System.out.println("Tesing the MulExpression");
   		System.out.println("Please Enter Two Expressions: ");
   		
   		System.out.print(" First Exp: ");
   		exp1 = in.nextLine();
   		
   		System.out.print(" Second Exp: ");
   		exp2 = in.nextLine();
   		
   		System.out.println("\nThe Two Expressions to Multiply are : " + exp1 + " and " + exp2);
   		Expression e = new MulExpression(Parse.parseString(exp1), Parse.parseString(exp2));
   		
   		System.out.print(" The Emitted Expression is :");
   		System.out.println(e.emit());
   		
   		System.out.print(" The Evaluated Expression is :");
   		System.out.println(e.evaluate());	
	} 
}
