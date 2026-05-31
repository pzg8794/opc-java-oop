
public class GenerateInputs {

	
	
	private int str1l, str2l;
	private char letters[]={'0','1','A','G','T','C'};
	private static String str1;
	private static String str2;
	private int str1len = 1, str2len = 1,i;


	/**
	 * @param cout 
	 * 
	 */
	public void GenerateTestcases()throws Exception{

		str1len *= 2;
		str2len *= 2;
		//		cout.println("INPUT LENGTHs " + str1len + " " + str2len);

		char str1[] = new char[str1len];
		char str2[] = new char[str2len];

		//		System.out.println("First String : ");
		for(i=0;i<str1len;i++){
			str1[i]=this.letters[(int) (Math.random()*5)];
			//			System.out.print(str1[i] + " ");
		}

		//		System.out.println("\n Second String : ");
		for(i=0;i<str2len;i++){
			str2[i]=this.letters[(int) (Math.random()*5)];
			//			System.out.print(str2[i] + " ");
		}

		GenerateInputs.str1 = String.valueOf(str1);
		GenerateInputs.str2 = String.valueOf(str2);
		//		System.out.println(this.str1 + " " + this.str2);
		str1l = str1len;
		str2l = str2len;
	}
	
	public String getStr1(){
		return str1;
	}
	
	public String getStr2(){
		return str2;
	}
	
	public int getStr1l(){
		return str1l;
	}
	
	public int getStr2l(){
		return str2l;
	}
	
}
