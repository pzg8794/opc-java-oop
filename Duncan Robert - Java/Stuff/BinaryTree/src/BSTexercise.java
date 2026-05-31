import java.io.*;  // Needed for BufferedReader etc.

/**
 * Exercise the methods in BST --- full-feature version
 *
 * @author  Timothy Rolfe
 * @version 2010 April 22
 */
public class BSTexercise
{
   /**
    * Driver for the BST methods:  build a tree,
    * traverse it several ways when finished, and then
    * allow multiple find operations.
    */
// NOTE:  This assumes friend-like access to BSTnode fields
//        through package-level access.
   public static void main ( String[] args )
   {  BST        tree = new BST();
      Comparable item;            // Find returns a cell pointer
      String     lineIn = "";     // readLine used for user interaction
      int        value;           // decoded via "atoi"

      java.util.Scanner console = new java.util.Scanner(System.in);

   // *****  Tree building phase ***** //
      System.out.println ("\nBuilding the binary search tree");
      while (true) // Will break out on input of 666
      {  System.out.print ("Value (666 exits):  ");
         value = console.nextInt();
         if (value == 666)
            break;
         tree.insert((Integer)value);
         value = tree.size();   // For debugging convenience.
         System.out.println ("Tree (size " + value + ") after insert:");
         tree.walk();
      }

   // *****  Tree display phase ***** //
      System.out.println ("\nAverage depth of nodes:  " + tree.avgDepth()
           + "\n           Tree height:  " + tree.height());
      System.out.println ("\nLevel-order traversal for \"pretty-print\":");
      tree.pretty();

   // *****  Tree thinning phase ***** //
      System.out.println ("\nExercising Find and Delete on the tree\n");
      while (true) // Will break out on input of 666
      {  System.out.print ("Value (666 exits):  ");
         value = console.nextInt();  console.nextLine();
         if (value == 666)
            break;
         item = tree.find((Integer)value);
         if (item != null)
         {  System.out.print ("Found " + item);
            System.out.println();

            System.out.print ("Do you wish it deleted?  (Y/N) ");
            lineIn = console.nextLine();
            if ( lineIn.length() > 0 &&
                 Character.toUpperCase(lineIn.charAt(0)) == 'Y' )
            {  tree.delete(item);
               value = tree.size();
               System.out.println ("Tree (size " + value + ") after delete");
               tree.walk();   System.out.println();
               tree.pretty(); System.out.println();
            }

         }
         else
            System.out.println (value + " not found in the tree");
      }

   // ***** Shut-down phase:  characteristics of remaining tree *****  //
      System.out.println ("\nAverage depth of nodes:  " + tree.avgDepth()
           + "\n           Tree height:  " + tree.height());
      tree.clear();
      System.out.println ("\nTree has been emptied:"
           + "\nAverage depth of nodes:  " + tree.avgDepth()
           + "\n           Tree height:  " + tree.height() );
      System.out.print   ("\nPress ENTER to exit.\t");
      lineIn = console.nextLine();
   }
} // end class BSTexercise