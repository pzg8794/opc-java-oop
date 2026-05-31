
/*
* Parse.java *
* Version:
* $Id: Parse.java,v 1.2 2013/03/26 15:51:41 pzg8794 Exp $
*
* Revisions:
* $Log: Parse.java,v $
* Revision 1.2  2013/03/26 15:51:41  pzg8794
* Lab3 updated
*
*/


import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.Scanner;



/** 
 * A container for the static methods to parse Interp expressions.
 * 
 * @author Piter Garcia
 *
 */
public class Parse implements Expression {

      /*
       * variable containing the first subexpression.
       */
	  private Expression exp1;
	
      /*
       * variable containing the second subexpression.
       */
	  private Expression exp2;
	
      /*
       * variable containing given expression for analysis purpose.
       */
	  ParseTree root = null;
	
	  
	  
	   /**
	   	* Main constructor to initialize the Parse class parameters.
	   	*/
	   public Parse(){

			  exp1 = null;
			  exp2 = null;
			  root = null;
	   }
	   
	   
		
	  /** 
	   * An internal class used to create a Tree that is then used by the 
	   * Parse class to build a tree of expressions.
	   *
	   */
	   private static class ParseTree {
		   
		   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
			* Five Main Pieces of States that belong to this tree    		*
			* 																*
			* @param value, contains the value of the child (leaf)  	 	*																*
			* @param leaf, variable to check if the child is a leaf or not  *
			* @param operator, contains an arithmetic operator (+, -, or *) *
			* @param left, contains the left subexpression - internal node  *
			* @param right, contains the right subexpression - internal node*
			* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		   private int value; 
		   private final boolean   leaf;   
		   private final char  operator;      
		   private ParseTree  left, right;    


		   
			/**
		     * This is the tree constructor to created an empty tree.
		     *
		     * @param leaf, operator, value 
		     *
		     */
		   private ParseTree ( boolean leaf, char operator, int value ) {
	         
			   this.leaf     = leaf;
			   this.operator = operator;
			   this.value    = value;
			   this.left     = null;   
			   this.right    = null;
		   }

		   
		   
			/**
		     * it converts the leaf and nodes to a string value, leaf are 
		     * converted to its integer-value(string) and nodes to its 
		     * operator(string)
		     *
		     * @return leaf, operator these values are return as a string.
		     *
		     */
		   public String toString() {  
	    	  
		   return leaf ?Integer.toString(value) : Character.toString(operator);  
		   }
	   }



	   /**
	    * This is a constructor used to append the given prifix function to 
	    * the root of the tree that is built by the Parse class.
	    *
	    * @param input  The scanner with the expression.
	    */
	   public Parse ( Scanner input ){
		   root = build(input);  
	   }

	   
	   
	   
	   /**
	    * Based on a white-space delimited prefix expression, build the
	    * corresponding binary expression tree.
	    * 
	    * @param  input, the scanner with the expression
	    * @return node, a reference to the corresponding binary expression
	    */
	   private ParseTree build ( Scanner input ){
		   
	   		boolean  leaf;
	   		String   token;
	   		int   value;
	   		ParseTree node;
	   		leaf = input.hasNextDouble();
	   		
	   		if ( leaf ){
	         
	   			value = input.nextInt();
	   			node = new ParseTree ( leaf, '\0', value );
	   		}
	   		else{
	         
	   			token = input.next();
	   			node  = new ParseTree ( leaf, token.charAt(0), 0 );
	   			node.left  = build ( input );
	   			node.right = build ( input );
	   		}
	   	return node;
	   }

	   
	   
	   /**
	   	* Show the expression tree as a parenthesized infix expression.
	   	* All the work is done in the private recursive method.
	   	* 
	   	* @return emit(root), a recursive function to completely 
	   	* translate an expression to its corresponding string.
	   	*/
	   public String emit() {
		  //System.out.println(" In emit() ");
	      emit ( root );
	      
	      return emit ( root );
	   }

	   
	   
		/**
	     * Generates code for an Int expression. The code is the valString (the
	     * digits that were used to write it) of the int.
	     *
	     * @param node, the operator containing the two children( integers ).
	     * @return expression, a string that represents the expression obtained 
	     * according the node operator.
	     */
	   protected String emit ( ParseTree node ) {
	   		String expression = "";
		   
	   		if ( node != null ) {
	   			// Note:  do NOT parenthesize leaf nodes
	   			if ( ! node.leaf ){
	   			// System.out.print ("( ");        // Pre-order position
	   				expression = expression + "(";
	   			}
	         
	   			expression = expression + emit ( node.left );
	   			// System.out.print ( node + " " ); // In-order position
	   			expression = ( expression + node + " ");
	   			expression = expression + emit ( node.right );
	         
	   			if ( ! node.leaf ) {                // Post-order position
	   				// System.out.print (") ");
	   				expression = expression + ")";
	   			}
	   		}
	   		return expression;
	   }

	   
	   
	   /**
	   	* Evaluate the expression and return its integer value.
	   	* All the work is done in the private recursive method.
	   	* @return 0, if the expression is empty.
	   	* @return evaluate(root),  the value of the expression tree.
	   	*/
	   public Integer evaluate (){  
	   		return (int) (root == null ? 0 : evaluate ( root )) ;  
	   }

	   

		/**
	     * Evaluate the expression: for internal nodes, this amounts to a post-
	     * order traversal, in which the processing is doing the actual arithme
	     * tic. For leaf nodes, it is simply the value of the node.
	     *
	     * @param node, , a reference to the corresponding binary expression.
	     * @return result, an integer that represents the expression obtained 
	     * according the node reference.
	     */
	   private Integer evaluate ( ParseTree node ) {
	   		Integer result;    // Value to be returned
	   		
	   		if ( node.leaf )        // Just get the value of the leaf
	   			result = node.value;
	   		else {
	   			// We've got work to do, evaluating the expression
	   			Integer left, right;
	   			char   operator = node.operator;

	   			// Capture the values of the left and right subexpressions
	   			left  = evaluate ( node.left );
	   			right = evaluate ( node.right );

	   			// Do the arithmetic, based on the operator
	   			switch ( operator ) {
	   				case '-':  
	   					result = left - right;  break;
	   				case '*':  
	   					result = left * right;  break;

	   				// NOTE:  allow fall-through from default to case '+'
	   				default:   System.out.println ("Unrecognized operator " +
	                          operator + " treated as +.");
	   				case '+':  
	   					result = left + right;  break;
	   			}
	   		}
	   		// Return either the leaf's value or the one we just calculated.
	   	return result;
	   }

	   
	   
	  /**
	    * The main method, it scans an expression to test the Parse class
	    * methods
	    * @param args command line arguments (ignored).
	    * @exception IOException, to test input expression.
	    *
	    */
	   public static void main( String[] args) throws IOException{

	   		Scanner in = new Scanner("+ 2 * 3 4");
		 
			System.out.print("Interp> ");
			String s = in.nextLine();

			Expression e = null;
			e = Parse.parseString(s);
	
			System.out.println("Infix expression: " + e.emit());
			Integer result = e.evaluate();
			    	
			System.out.println("Evaluation: " + result);
			System.out.println("Goodbye Interp.");
	   }
	
	

	   
		/**
	     * Construct a complete Integer expression from the supplied string.
	     *
	     * @exception IOException, to test input expression.
	     *
	     */
	   public static Expression parseString(String s) {
	
		   Scanner sc = new Scanner(s);
		   Parse test = new Parse(sc);

			StreamTokenizer st = new StreamTokenizer(new StringReader(s));
			st.ordinaryChar('/');
			
			Expression exp = null;
			try {
				exp = parse(st);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			int type = 0;
			try {
				while ((type = st.nextToken()) == ')' ) continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			if (type != StreamTokenizer.TT_EOF) {
					System.out.println("Error in expression: Unexpected " +
							"input at end of expression.");
					System.exit(-1);
			return null;
			}
			
			else {
				exp = test;
				return exp;
			}	
	   }
	   
	   
	   
	   
		/**
	     * Construct a Integer sub-expression from the supplied string, and re-
	     * cursively returns all sub-expressions that compose a complete expre-
	     * ssion that is then returned to the method parseString(s).
	     *
	     * @param st, the given expression divided into tokens.
	     * @exception IOException, to test input expression.
	     *
	     */
	   private static Expression parse(StreamTokenizer st) throws IOException {
			Parse test = new Parse();
			
			int type = '(';
			while (type == '(' || type == ')') {
				type = st.nextToken();
			}
			
			if (type == StreamTokenizer.TT_EOF) {
				System.out.println("Unexpected end of input.");
				return null;
			}
			
			else if (type == StreamTokenizer.TT_WORD) {
				System.out.println("Unknown operator: " + st.sval);
				return null;
			}
			
			else if (type == '+' || type == '-' || type == '*') {
				test.exp1 = parse(st);
				test.exp2 = parse(st);
			
	
			switch (type) {
				case '+':
					return new AddExpression(test.exp1, test.exp2);
				case '-':
					return new SubExpression(test.exp1, test.exp2);
				case '*':
					return new MulExpression(test.exp1, test.exp2);	
				default:
					return new AddExpression(test.exp1, test.exp2);
			}
		}
		return test;
	}
}