
/**
 * Simulates a regular expression class that parse each string in 
 * a regular expression.
 * @author piter garcia
 *
 * @param <T>
 */
public class RegExp<T> {

	static int size = 0;
	static char[] exp;


	public RegExp(String string) {

		exp = string.toCharArray();
		size = exp.length;

		String temp = "";
		int i = -1;
		while( i++ != size ){

			if(i!= exp.length){

				if(i != 0 && Character.isLetter(exp[i-1]) && Character.isLetter(exp[i])){

					temp += "." + exp[i];

				}else if (i != 0 && exp[i-1] == '*' && Character.isLetter(exp[i])){

					temp += "." + exp[i];

				}else if (i != 0 && exp[i-1] == '*' && exp[i] == '('){

					temp += "." + exp[i];

				}else if (i != 0 && exp[i-1] == ')' && exp[i] == '('){

					temp += "." + exp[i];

				}else{
					temp += exp[i];
				}

			}else{
				break;
			}
		}

		exp = temp.toCharArray();
		size = exp.length;

		System.out.println("EXPRESSION : ");
		System.out.println(exp);
		System.out.println("\n");
		System.out.println("First  Step - STAR Operation : ");
		System.out.println(doStar());
		System.out.println("\n");
		System.out.println("Second Step - CONCATINATION Operation : ");
		System.out.println(doAND());
		System.out.println("\n");
		System.out.println("Third  Step - UNION Operation : ");
		System.out.println(doOR());
		System.out.println("\n");


	}


	private String doAND() {

		String AND = "";

		int i = -1;
		while( i++ != size ){

			int x = i-1;
			if(i!= exp.length){

				if(exp[i] == '.'){

					AND = ANDNode(exp, x);
					exp = AND.toCharArray();
					size = exp.length;
				}

			}else{
				break;
			}
		}

		return AND;
	}



	private String doOR() {

		String OR = "";
		int i = -1;
		while( i++ != size ){

			int x = i-1;
			if(i!= exp.length){
				if(exp[i] == '|'){

					OR = ORNode(exp, x);
					exp = OR.toCharArray();
					size = exp.length;
				}
			}else{
				break;
			}
		}
		return OR;
	}



	private String doStar() {

		String star = "";

		int i=-1;
		while( i++ != size){

			int x = i-1;

			if(i < size){

				if(exp[i] == '*'){

					star = starNode(exp, x);
					exp = star.toCharArray();
					size = exp.length;

				}
			}else{
				break;
			}
		}
		return star;
	}



	public String ORNode(char[] exp2, int x){

		String temp = "";
		int i = 0;

		int left = -1, right = -1, l1 =-1, r1 = -1;
		boolean star = false, skipr = false;
		boolean skip = false, skipb = false;
		boolean skipl = false, skipAll = false;


		if( exp2[x] == 'r'){

			star = true;
			left = x;
		}

		if( exp2[x+2] == '{'){

			right = x+2;
			skipr = true;
		}

		if( exp2[x] == ']'){
			skipAll =true;
		}

		if( Character.isLetter(exp[x])){
			skipl = true;
		}

		if( Character.isLetter(exp2[x]) && Character.isLetter(exp2[x+2])){
			skip = true;
		}

		else if( exp2[x] == ']' && Character.isLetter(exp2[x+2])){
			skip = true;
			skipb = true;
		}


		for( int i1 = x; i1>=0; i1--){

			//			System.out.println("Test");
			if(star){

				if(exp2[i] == '{'){

					l1++;
					//					 System.out.println(l1 + "Test" + r1);
				}

				if(exp2[i] == '}'){

					r1++;
					//					 System.out.println(l1 + "Test" + r1);
				}

				if( left == i1){
					temp += exp2[x] + "]";

				}else if(exp2[i1] == '{'){

					//					 System.out.println(l1 + "Test" + r1);
					if(l1-r1 == 0 || l1-r1 == 1){
						star = false;
						temp = "[" + exp2[i1] + temp;

						left = -1;
						right = -1;
					}else{
						temp = exp2[i1] + temp;
						//						System.out.println("TEst");
					}

				}else{

					temp = exp2[i1] + temp;
				}

			}else{

				if( skip || skipl){

					if( i1 == x && !skipb){

						temp = "["  + exp2[i1] + "]" +  temp; 

					}else{
						temp = exp2[i1] + temp;
					}

				}else if(!skipl){

					if( left == right  && left >= 0 ){

						if(!skipAll)
							temp = exp2[i1] + "["  + temp; 
						else
							temp = exp2[i1] + temp; 

						left = -1;
						right = -1;

					}else{

						if(exp2[i1] == ')' ){

							if( i1 == x){
								right++;

								if(!skipAll)
									temp += exp2[x] + "]";
								else
									temp += exp2[x]; 


							}else{
								right++;
								temp = exp2[i1] + temp;

							}

						}else if(exp2[i1] == '(' ){

							left++;
							if( left == right  && left >= 0 ){

								if(!skipAll)
									temp = "["  +  exp2[i1] + temp; 
								else
									temp = exp2[i1] + temp; 

								left = -1;
								right = -1;
							}else{
								temp = exp2[i1] + temp; 
							}

						}else
							temp = exp2[i1] + temp;
					}
				}
			}
			i++;
		}

		temp += " OR ";


		boolean stop = false;
		int l = -1, r = -1;

		while( ++i != exp2.length){

			if(skip){

				if( i == x+2){
					//					System.out.println("test");
					temp +=  "["  + exp2[i] + "]";
				}else{
					temp += exp2[i];
				}

			}else{

				if(skipr){

					if(x+2 == i){
						temp += "[" + exp2[i];

					}else if(exp2[i] == 'r' && (l == r)){

						temp += exp2[i] + "]";
						stop = true;
						skipr =false;
						l = -1;
						r = -1;

					}else{
						temp += exp2[i];
					}

					if(exp2[i] == '{')
						l++;
					if(exp2[i] == '}')
						r++;

				}else{

					if(i == x+2)
						temp += "[";

					if(exp2[i] == '(' ){

						l++;
						temp += exp2[i];

					}else if(exp2[i] == ')'){

						r++;
						if( (l == r) && !stop){
							//						System.out.println("test" + exp2[i+1]);
							temp += exp2[i] + "]";
							l = -1;
							r = -1;
							stop = true;

						}else{

							temp += exp2[i] ;
						}

					}else{

						temp += exp2[i];
					}
				}
			}
		}


		System.out.println("Char : " + temp);
		return temp;
	}


	public String ANDNode(char[] exp2, int x){

		String temp = "";
		int i = 0;

		int left = -1, right = -1;
		boolean skipl = true, skipr = true, star = false, skip = false;
		boolean skipAll = false;
		temp = "";


		if( exp2[x] == 'r'){
			star = true;
			left = x;

		}
		if( exp2[x] != ')' ){
			left = x;
			skipl = true;

		}else{
			skipl = false;
		}

		if( exp2[x+2] != '('){
			right = x+2;

		}else{
			skipr = false;
		}

		if( Character.isLetter(exp2[x]) && Character.isLetter(exp2[x+2])){
			skip = true;

		}else if(exp2[x] == ')' && exp2[x+2] == '('){

			skipAll = true;
		}

		for( int i1 = x; i1>=0; i1--){

			if( skipAll){

				temp =  exp2[i1] + temp; 

			}else{
				if(star){

					if(exp2[i1] == '{'){
						//					temp =   exp2[i1] + temp; 
						temp = "(" +  exp2[i1] +  temp; 
						star = false;

					}else{
						temp =  exp2[i1] + temp; 
					}
					//				System.out.println("test");

				}else if( (left > 0 && left == i1 && skipl) || skip){

					if(Character.isLetter(exp2[x]))
						temp =   "("  + exp2[i1] + temp; 

					else if(!Character.isLetter(exp2[x]))
						temp =  exp2[i1] + "("  + temp; 
					else
						temp =  exp2[i1] + temp;


					skip = false;

				}else{

					if ( !skipl ){

						if(exp2[i1] == '('){

							temp =  exp2[i1] + temp; 
							temp = '(' + temp;
							skipr = true;
							skipl = true;

						}else{

							temp = exp2[i1] + temp;
						}

					}else{

						temp = exp2[i1] + temp;
					}
				}
			}
			i++;
		}

		temp += " CONCATINATED WITH ";
		char tp = 0;

		while( ++i != exp2.length){

			if( right >= 0 && right == i){

				if(exp2[right] != '{'){

					temp += exp2[right] + ")";

				}else{
					temp += exp2[right];
					tp = exp2[right];
				}
				skipr = true;

			}else if( skipr ){

				if(tp > 0 && exp2[i] == 'r'){
					temp += exp2[i] + ")"; 
					tp =0;
				}else if( i != left){
					temp += exp2[i];
				}

			}else{
				temp += exp2[i];
			}
		}

		System.out.println("Char : " + temp);
		return temp;
	}

	public String starNode(char[] exp, int x){

		String temp = "";
		int i = 0;

		int left = -1, right = -1;
		boolean skipl = false, skipr = false;
		temp = "";


		if( exp[x] != ')' ){
			left = x;

		}else{

			right = x;
		}


		int l = -1, r=-1;
		boolean stop = false;

		for( int i1 = x; i1>=0; i1--){

			if( left >= 0 && left == i1){

				temp = "{" + exp[i1] + "}" + temp; 
				skipl = true;
				skipr = false;

			}else if( right >= 0 && right == i1){

				skipr = true;
				skipl = false;
			}

			if( skipl ){

				if( i1 != left)
					temp = exp[i1] + temp;

			}else if(skipr){


				if(exp[i1] == '('){

					l++;
					if( l == r  && !stop){
						//						System.out.println( l + "test" + r );
						temp =  exp[i1] + temp; 
						temp = '{' + temp;
						skipr = false;
						skipl = false;
						stop = true;
						l =-1;
						r = -1;

					}else{

						temp =  exp[i1] + temp; 
					}

				}else if(right == i1){

					r++;
					temp +=  exp[i1]; 
					temp +=  "}"; 

				}else if(exp[i1] == ')'){

					r++;
					temp =  exp[i1] + temp; 

				}else{
					temp = exp[i1] + temp;
				}

			}else{
				temp = exp[i1] + temp;
			}
			i++;
		}
		temp += "Star";


		while( ++i != exp.length){
			temp += exp[i];
		}
		System.out.println("Char : " + temp);
		return temp;
	}



	public String symbols(char[] exp, int x, int sim1){

		String temp = "" + exp[x];

		int i = 0;
		while( i != sim1){

			if(x >=1 ){
				if(exp[--x] == '(')
					i++;

				temp = exp[x] + temp;
			}
		}
		return temp;
	}


	public static void main(String[] args){

		if( args.length <= 0){
			System.err.println( "Usage: java RegExp (regular expression)" );
			System.exit(0);
		}else{
			
			System.out.println("Note: This Regular Espression parser class is sensitive to spaces." +
					"\n Every regular expression entered must not include any space." +
					"\n Also, this regular expression class does not detect not well written expression" +
					" such as )a|b( \n You must provide a resonable expression in order for it to work.\n\n");
			// Terminate the program here somehow, or see below.
			new RegExp<String>(args[0]);
		}
	
	}

}