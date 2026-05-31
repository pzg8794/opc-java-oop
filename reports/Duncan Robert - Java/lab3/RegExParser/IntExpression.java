import java.util.Scanner;

/*
* IntExpression.java *
* Version:
* $Id: IntExpression.java,v 1.3 2013/03/26 17:07:45 pzg8794 Exp $
*
* Revisions:
* $Log: IntExpression.java,v $
* Revision 1.3  2013/03/26 17:07:45  pzg8794
* Updated Lab3
*
* Revision 1.2  2013/03/26 15:51:42  pzg8794
* Lab3 updated
*
*/


/** 
 * Represents integer expressions in Interp. Instances differ by the integer.
 * 
 * @author Piter Garcia
 *
 */
public class IntExpression implements Expression{


        /*
         * variable containing the complete expression.
         */
	private Expression expression;
	
	
	
	/**
	 * Construct an Int expression from the supplied string.
	 *
	 */  
	public IntExpression(String valString) {
		
		expression = Parse.parseString(valString);
	}
	
	
	
	
	/**
         * Generates code for an complete string value expression. 
         *
         * @param expression.emit(), A string that represents 
         * the code for the expression.
         */
	public String	emit(){
		 
		 return expression.emit();
	}
	
	
	
	/**
         * Causes evaluation of a complete integer expression. The value is the sum of the 
         * values of the subexpressions generated while parsing the parse tree.
         *
         * @param expression.evaluate(), An Integer, the sum of the values of a complete
         * expression
         */
	public Integer	evaluate(){
		 
		 return expression.evaluate();
	}

	
	
	/**
	 * The main method, it scans an expression to test the IntExpression class
	 * methods
	 * 
	 * @param args command line arguments (ignored).
	 *
	 */
	public static void main( String[] args){
		 
			String exp1;
			Scanner in = new Scanner(System.in);
			
	   		System.out.println("Tesing the IntExpression");
	   		System.out.println("Please Enter An Expression: ");
	   		
	   		System.out.print(" Expression : ");
	   		exp1 = in.nextLine();
	   		
	   		System.out.println("\nThe Expression Given is : " + exp1);
	   		Expression e = new IntExpression(exp1);
	   		
	   		System.out.print(" The Emitted Expression is :");
	   		System.out.println(e.emit());
	   		
	   		System.out.print(" The Evaluated Expression is :");
	   		System.out.println(e.evaluate());	
	}
	 
}
