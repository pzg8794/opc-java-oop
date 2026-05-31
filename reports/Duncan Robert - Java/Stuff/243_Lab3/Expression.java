/*
 * Expression.java *
 * Version:
 * $Id: Expression.java,v 1.3 2013/03/26 17:07:45 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Expression.java,v $
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
public interface Expression {
	

    
    /**
     * Generates code for an complete string value expression.
     * Note: this function can be performed if and only if a class 
     * implements it.
     *
     * @param expression.emit(), A string that represents
     * the code for the expression.
     */
	 public String	emit();
    
    
    
    /**
     * Causes evaluation of a complete integer expression. The value is the sum of the
     * values of the subexpressions generated while parsing the parse tree.
     * Note: this function can be performed if and only if a class
     * implements it.
     *
     * @param expression.evaluate(), An Integer, the sum of the values of a complete
     * expression
     */
	 public Integer	evaluate();

}
