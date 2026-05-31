import java.util.Random;


public class Test {

	//
//  main.cpp
//  cryptohw6
//
//  Created by Piter Garcia on 7/20/13.
//  Copyright (c) 2013 Piter Garcia. All rights reserved.
//

static int[] sBox = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
static int[] revSbox = {8, 4, 2, 1, 12, 6, 3, 13, 10, 5, 14, 7, 15, 8, 9, 0};

static int approxTable[][] = new int [16][16];

static int applyMask(int value, int mask)
{
    int interValue = value & mask;
    int total = 0;
    
    while(interValue > 0)
    {
        int temp = interValue % 2;
        interValue /= 2;
        
        if (temp == 1)
            total = total ^ 1;
    }
    return total;
}

static void findApprox()
{
    int c, d, e;
    
    for(c = 1; c < 16; c++)                                         //output mask
        for(d = 1; d < 16; d++)                                     //input mask
            for(e = 0; e < 16; e++)                                 //input
                if (applyMask(e, d) == applyMask(sBox[e], c))
                    approxTable[d][c]++;
}

static void showApprox()
{
    System.out.println("Strong Linear Approximations: \n");
    int c, d, e;
    for(c = 1; c < 16; c++)
        for(d = 1; d < 16; d++)
            if (approxTable[c][d] == 14)
            	System.out.println(" : -> \n " 
            + approxTable[c][d] + " " + c + " " + " " + d);
    
    System.out.println("\n");
}

static int roundFunc(int input, int subkey)
{
    return sBox[input ^ subkey];
}

static int knownP[] = new int[500];
static int knownC[] = new int[500];
static int numKnown = 0;
static Random rand = new Random();

static void fillKnowns()
{
	

    int subKey1 = rand.nextInt(16);
    int subKey2 = rand.nextInt(16);
    
    System.out.println("Data Generator:  K1 = " + subKey1 + ", K2 = " + subKey2 + " \n ");
    
    int total = 0;
    
    int c;
    for(c = 0; c < numKnown; c++)
    {
        knownP[c] = rand.nextInt(16);
        knownC[c] = roundFunc(roundFunc(knownP[c], subKey1), subKey2);
    }
    
    System.out.println("Data Generator:  Generated " + numKnown + " Known Pairs\n\n " );
    
}

void testKeys(int k1, int k2)
{
    int c;
    for(c = 0; c < numKnown; c++)
        if (roundFunc(roundFunc(knownP[c], k1), k2) != knownC[c])
            break;
    
    System.out.println("* ");
}


public static void  main( String[] args)
{
    System.out.println("JK's Linear Cryptanalysis Test Program\n");
    System.out.println("-----------------------------------------------\n\n");
    
//    srand(time(null));
    
    findApprox();
    showApprox();
    
    int inputApprox = 11;
    int outputApprox = 11;
    
    numKnown = 16;
    fillKnowns();
    
    int keyScore[] = new int[16];
    int sofar1 = 0;
    
    System.out.println("Linear Attack:  Using Linear Approximation = " + inputApprox + " -> " + outputApprox);
    
    int c, d;
    for(c = 0; c < 16; c++)
    {
        keyScore[c] = 0;
        for(d = 0; d < numKnown; d++)
        {
            sofar1++;
            int midRound = roundFunc(knownP[d], c);         //Find Xi by guessing at K1
            
            if ((applyMask(midRound, inputApprox) == applyMask(knownC[d], outputApprox)))
                keyScore[c]++;
            else
                keyScore[c]--;
            
        }
        
    }
    
    int maxScore = 0;
    
    for(c = 0; c < 16; c++)
    {
        int score = keyScore[c] * keyScore[c];
        if (score > maxScore) maxScore = score;
    }
    
    int goodKeys[] = new int[16];
    
    for(d = 0; d < 16; d++)
        goodKeys[d] = -1;
    
    d = 0;
    
    for(c = 0; c < 16; c++)
        if ((keyScore[c] * keyScore[c]) == maxScore)
        {
            goodKeys[d] = c;
            System.out.println("Linear Attack:  Candidate for K1 = "  + goodKeys[d] + "\n ");
            d++;
        }
    
    int guessK1, guessK2;
    
    for(d = 0; d < 16; d++)
    {
        if (goodKeys[d] != -1)
        {
            int k1test = roundFunc(knownP[0], goodKeys[d]) ^ revSbox[knownC[0]];
            
            int tested = 0;
            int e;
            int bad = 0;
            for(e = 0;e < numKnown; e++)
            {
                sofar1 += 2;
                int testOut = roundFunc(roundFunc(knownP[e], goodKeys[d]), k1test);
                if (testOut != knownC[e])
                    bad = 1;
            }
            if (bad == 0)
            {
            	System.out.println("Linear Attack:  Found Keys! K1 = " + goodKeys[d] + ", K2 = " + + k1test + " \n ");
                guessK1 = goodKeys[d];
                guessK2 = k1test;
                System.out.println("Linear Attack:  Computations Until Key Found = " +  sofar1 + " \n ");
                
            }
            
        }
    }
    
    System.out.println("Linear Attack:  Computations Total = "  + sofar1 + "\n\n ");
    
    sofar1 = 0;
    
    for(d = 0; d < 16; d++)
    {
        for(c = 0; c < 16; c++)
        {
            
            int e;
            int bad = 0;
            for(e = 0;e < numKnown; e++)
            {
                sofar1 += 2;
                int testOut = roundFunc(roundFunc(knownP[e], d), c);
                if (testOut != knownC[e])
                    bad = 1;
            }
            if (bad == 0)
            {
            	 System.out.println("Brute Force:  Found Keys! K1 = " + d + ", K2 = " + c + "\n ");
            	 System.out.println("Brute Force:  Computations Until Key Found =" + sofar1 + "\n ");
            }
        }
        
    }
    
    System.out.println("Brute Force:  Computations Total = " + sofar1 + "\n " );
}

}
