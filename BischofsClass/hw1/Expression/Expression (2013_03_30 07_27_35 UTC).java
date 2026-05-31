
import java.util.Stack;
import java.util.Vector;

class Expression {

	

	public static void main (String args []) {  // main program
		 
		 
		 String[] expression = {"2","+","4","*","2","+","6","/","2"};
		 
         Stack<String> operators = new Stack<String>();
         
         Stack<String> operands = new Stack<String>();
         
         Stack<Integer> Temp = new Stack<Integer>();
         
        int temp= 0;
         String temp2=" ";
         
         String array1[] ;
         String array2[];
         
        
         //	temp= Integer.parseInt(operands.pop()); 
         // Let's add some items to it
         for (int i = 0; i < expression.length; i++)
         {
        	      	 
        	 if(i%2 == 1)
        	 {
        		 if(expression[i].equals("*"))
        		 { 
        			 temp = Integer.parseInt(operands.pop()) * Integer.parseInt(expression[i]);
        			 String s= new String( Integer.toString(temp));
        			 operators.push(s);
        			 
        		 }else
        			 operators.push ( expression[i] );
                 
        	 }
        	 else
        	 {
        		 
        		 operands.push ( expression[i]);
        		 
        	 }
        	 
        	  
         }

         
         // Last in first out means reverse order
         while ( !operands.empty() )
         {
        	                     
                System.out.print ( operands.pop() ); 
               	System.out.print ( ',' ); 
               
               	if( !operators.empty()){ 
               	
               	System.out.print ( operators.pop() );
               	System.out.print ( ',' ); 
               	
                }
               	else{ 
              		break;
              		}
               
               	
                
         }
                
         System.out.println (" ");
         
 
         // Empty, let's lift off!
         System.out.println (" LIFT-OFF!");
		 
		 
	 }
	
}