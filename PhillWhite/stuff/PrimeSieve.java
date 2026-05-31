/* A class that computes prime numbers between 2 and N, using the Sieve of Eratosthenes algorithm.
*/

public class PrimeSieve
{

    Scanner sc = new Scanner(System.in);
    Integer maximumN; 
    

    ArrayList listOfIntegers = new ArrayList();

    PrimeSieve 
    {

        maximumN = 0;

    }




    public static ArrayList findPrimes( int N)
    {

         for( int i = 2; i<maximumN; i++)
         {
                  listOfIntegers.add(i);
         }
        
        listOfIntegers = secondMultiples(listOfIntegers);
        listOfIntegers = thirdMultiples(listOfIntegers);
        listOfIntegers = fifthMultiples(listOfIntegers);
   
         for( int i = 0; i < listOfIntegers.length() ; i++)
         {
                 if( listOfIntegers.get(i) == 0)
                     listOfIntegers.remove(i);
         }
        
    }



    public static ArrauList secondMultiples( ArrayList listOfIntegers)
    {

         for( int i = 2; i<maximumN; i++)
         {
              if( i > 2 && (i%2) == 0)
                  listOfIntegers.remove(i);
         }

    }





    public static ArrauList thirdMultiples( ArrayList listOfIntegers)
    {

         for( int i = 2; i<maximumN; i++)
         {
              if( i > 3 && (i%3) == 0)
                  listOfIntegers.add(i,0);
         }

    }

  


   
    public static ArrauList fifthMultiples( ArrayList listOfIntegers)
    {

         for( int i = 2; i<maximumN; i++)
         {
              if( i > 5 && (i%5) == 0)
                  listOfIntegers.add(i,0);
         }

    }



    public static ArrauList fifthMultiples( ArrayList listOfIntegers)
    {

         for( int i = 2; i<maximumN; i++)
         {
              if( i > 7 && (i%7) == 0)
                  listOfIntegers.add(i,0);
         }

    }



    public static void display()
    {

        while( i != listOfIntegers.lenth())
        {
           System.out.println(listOfIntegers.get(i));
           i++;
        }

    }




    public static void main(String[] args)
    {
         System.out.println("Please Enter the Maximum N Value Greater than 2: ");
         maximumN = sc.nextInteger();

         findPrimes(maximumN);	

         display();
    }

}
