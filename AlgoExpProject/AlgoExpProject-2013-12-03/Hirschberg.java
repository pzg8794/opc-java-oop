class randomStringGenerator{
	char letters[]={'0','1','A','G','T','C'};
	public String GenerateTestcases()
	{
		int i;
		int strlen=(int) (Math.random()*20);
		char str[]=new char[strlen];
		
		for(i=0;i<strlen;i++)
		{	
			str[i]=this.letters[(int) (Math.random()*5)];
			
		}
		return String.valueOf(str);
		
	}
}


public class Hirschberg {
	double recursiveCalls=0;
	String string1,string2;
	public int[] NWScore(int string1len, int string2len,String string1,String string2 )
	{
		int[][] Score=new int[2][string2len+1];
		for(int i=0;i<=string2len;i++)
		{
			Score[1][i]=0;
		}
		
		for(int i=1;i<=string1len;i++)
		{
			for(int j=0;j<=string2len;j++)
			{
				Score[0][j]=Score[1][j];
			}
			for(int j=1;j<=string2len;j++)
			{
				if(string1.charAt(i-1)==string2.charAt(j-1))
				{
					Score[1][j]=Score[0][j-1]+1;
					
				}
				else
				{
					Score[1][j]=max	(Score[1][j-1],Score[0][j]);
				}
			}
		}
		return Score[1];
	}
	public int max(int x, int y)
	{
		if(x > y)
			return x;
		else
			return y;
	}
	public String reverseString(String s)
	{
		String reverse = "";
        
        for(int i=s.length()-1; i>=0; i--) {
                reverse = reverse+s.charAt(i);
        }
        
        return reverse;
	}
	public String startHirschberg(String string1, String string2,int string1len, int string2len)
	{
		this.recursiveCalls++;
		String temp="";
		if(string2len==0 || string1len==0)
		{
			temp="";
		}
		else if(string1len==1)
		{
			temp="";
			for(int i=0;i<string2len;i++)
			{
				if(string1.charAt(0)==string2.charAt(i))
				{
					temp=""+string1.charAt(0);
					break;
				}
				
			}
		}
		else
		{
			int mid=string1len/2;
			int[] ScoreL= NWScore(mid,string2len,string1.substring(0, mid),string2);
			int[] ScoreR= NWScore(string1len-mid,string2len,reverseString(string1.substring(mid)),reverseString(string2));
			int ymid=PartitionY(ScoreL,ScoreR,string2len);
			
			String temp1=startHirschberg(string1.substring(0, mid),string2.substring(0, ymid),mid,ymid);
			String temp2=startHirschberg(string1.substring(mid),string2.substring(ymid),string1len-mid,string2len-ymid);
			
			temp=temp1+temp2;
			
		}
		return temp;
	}
	public int PartitionY(int [] ScoreL, int[] ScoreR, int n)
	{
		int m=0,k=0;
		for(int j=0;j<=n;j++)
		{ 
			if(m<(ScoreL[j]+ScoreR[n-j]))
			{
				m = ScoreL[j]+ScoreR[n-j];
				k=j;
			}
		}
		return k;
			
	}
	
	public static void main(String args[])
	{
		Hirschberg obj=new Hirschberg();
		randomStringGenerator rsg=new randomStringGenerator();
		int NumberofTestcases=1;
		for(int i=0;i<NumberofTestcases;i++)
		{
			obj.string1=rsg.GenerateTestcases();
			obj.string2=rsg.GenerateTestcases();
			long startTime = System.currentTimeMillis();
			System.out.println("String1: "+obj.string1);
			System.out.println("String2: "+obj.string2);
			String LCS = obj.startHirschberg(obj.string1, obj.string2, obj.string1.length(),obj.string2.length());
			System.out.println("LCS : "+LCS);
			System.out.println("LCS Length :"+LCS.length());
			long endTime   = System.currentTimeMillis();
			System.out.println(endTime-startTime);
			System.out.println(obj.recursiveCalls);
			System.out.println("----------------------------------------------");
			obj.recursiveCalls=0;
		}
	}

}
