/*
 * Store.java
 * 
 * $Id: Store.java,v 1.1 2013/07/16 22:52:25 pzg8794 Exp $
 */

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Store is the main class for a book store.
 *
 * @author ben k steele bks@cs.rit.edu
 */
public class Store {

    /** name of the store */
    private String name;

    /** inventory list of book objects */
    private ArrayList<Book> inventory;

    /** input processor for customer inquiries */
    private Scanner in;

    /** rental price for non-salable items */
    private double rentalPrice;

    /** search code for title searches */
    public final static Character TITLE_SEARCH = 't';

    /** search code for author searches */
    public final static Character AUTHOR_SEARCH = 'a';

    /** search code for media format searches */
    public final static Character MEDIA_SEARCH = 'm';

    /** search code set */
    private final static char[] ccc 
                          = { TITLE_SEARCH , AUTHOR_SEARCH , MEDIA_SEARCH } ; 

    private final static String CODES = new String( ccc );

    /**
     * Store default constructor initializes all its instance fields.
     */
    public Store() {
        this.name = "Barney's Books 'N' Bytes";
        this.inventory = new ArrayList<Book>();
        this.in = new Scanner( System.in );
        this.rentalPrice = 3.95;
    }

    /**
     * @param aBook the book to add to the store inventory
     */
    public void addBook( Book aBook ) {
        inventory.add( aBook );
    }

    /**
     * fillInventory loads the inventory from a named, formatted file.
     *
     * @param filename source of the inventory content descriptions
     * @throws FileNotFoundException
     *
     */
    public void fillInventory( String filename ) throws FileNotFoundException {

        File file = new File( filename );
        Scanner in = new Scanner( file );    // a local, one-shot scanner.

        // parse each line in the input file
        while ( in.hasNextLine() ) {

            String line = in.nextLine();

            Book book = produceBook( line );
            if ( book != null ) {
                this.addBook( book );
            }
        }
    }

    /**
     * produceBook consumes a line of text and produces a Book instance.
     * produceBook expects the text to have specific tilde-delimited fields.
     *
     * @param line of text containing a single inventory item description
     * @return a valid Book instance or null if code detected errors in text
     * <p>
     * Expected File Format: A tilde (~) is the field delimiter for the text.
     * </p>
     * <br>
     * TITLE~AUTHOR~COST[~media format information]<br>
     * The last field is optional and may be empty.
     * <p>
     * The media format information depends on the particular kind of book.<br>
     * </p>
     * <pre>
     * (empty) means a paperback book medium.
     * cloth means a hardcover book medium.
     * leather means a hardcover book medium.
     * ...://... means an electronic book medium (from the URI format).
     * (integer) means an audio-book medium; the number is the number of discs.
     * 4993 means a cost of $49.93.
     * </pre>
     */
    public Book produceBook( String line ) {

        Book book = null;

        String[] fields = line.split( "~" );  // tilde is field delimiter

        if ( fields.length < 2 ) {
            return book;            // ignore lines of incorrect structure
        }
        if ( fields.length == 3 ) {  // Paperback has no added attributes
            book = new PaperbackBook( fields[0], fields[1]
                                    , new Integer( fields[2] ) ) ;

        // otherwise there are 4 fields: title, author, cost, media info
        } else if ( fields[3].equals( "cloth" ) 
                 || fields[3].equals( "leather" ) ) {
            book = new HardcoverBook( fields[0], fields[1]
                                    , new Integer( fields[2] )
                                    , new String( fields[3] ) );

        } else if ( fields[3].contains( "://" ) ) {  // is a URL?
            book = new ElectronicBook( fields[0], fields[1]
                                     , new Integer( fields[2] )
                                     , new String( fields[3] ) ) ;

        } else if ( fields[3].matches( "[0-9]*" ) ) {  // is a number?
            book = new AudioBook( fields[0], fields[1]
                                , new Integer( fields[2] )
                                , new Integer( fields[3] ) ) ;

        } else {
            System.out.println( "skipping entry: " + fields[0] 
                       + ". unrecognized media format: " + fields[3] );
        }
        return book;
    }

    /**
     * getMarkup returns the cost multiplier for price production.
     * @return the cost multiplier for price production.
     */
    public double getMarkup() {
        return 2.0;
    }

    /**
     * print the entire store inventory.
     */
    public void printInventory() {
        System.out.println( "\nThe inventory of " + this.name + ": " );
        int item = 1;
        for ( Book book : this.inventory ) {
            System.out.println( item + ". " + book );
            item += 1;
        }
        System.out.println();
    }

    /**
     * list the inventory's titles for a partial text string.
     * @param bk the book under consideration.
     * @param code the code identifying the attribute to search
     */
    private static String selectField( Book bk, char code ) {
        String result = null;
        // init to a default
        if ( code == TITLE_SEARCH ) {
            result = bk.getTitle().toLowerCase();
        } else if ( code == AUTHOR_SEARCH ) {
            result = bk.getAuthor().toLowerCase();
        } else if ( code == MEDIA_SEARCH ) {
            result = bk.getMedia().toLowerCase();
        }
        return result;
    }

    /**
     * list the inventory's titles for a partial text string.
     * @param partText the text to find in book titles.
     */
    /* package */ ArrayList<Book> listMatching( String partText, char code ) {

        ArrayList<Book> foundItems = new ArrayList<Book>();
        // System.out.println();
        for ( Book book : this.inventory ) {
            String field = selectField( book, code );
            if ( field != null ) {
                if ( field.contains( partText ) ) {
                    foundItems.add( book );
                }
            }
        }
        return foundItems;
    }

    /**
     * offerPurchase offers the user a choice from the list of books found.
     * If the list is empty, the method prints the message
     * <BR>Sorry. No matches were found.
     * <BR>and returns.
     * @param bookList the list of books found by a search
     */
    private void offerPurchase( ArrayList<Book> bookList ) {

        if ( bookList.size() == 0 ) {
            System.out.println( "Sorry. No matches were found." );
        } else {
            for ( int j = 1; j <= bookList.size(); ++j ) {
                Book book = bookList.get( j - 1 );
                System.out.print( j + ". " + book.getTitle()
                                  + "\n  " + book.getMedia() );
                // print the marked-up sale price in $ dd.cc format
                System.out.printf( " $ %3.2f\n"
                                 , (book.getCost() * this.getMarkup()) );
            }
            System.out.println();
            System.out.print( "Which would you like to buy(ENTER to quit): " );
            String content = this.in.nextLine().trim().toLowerCase() ;
            if ( ! content.isEmpty() ) {
                Scanner sc = new Scanner( content );  // to scan for a number
                if ( ! sc.hasNextInt() ) {
                    System.out.println(
                               "\nSorry. You needed to enter an integer." );
                    return;
                }
                int j = new Integer( content );
                if ( j < 1 || j > bookList.size() ) {
                    System.out.println(
                               "\nSorry. Your choice was out of range." );
                } else {
                    Book book = bookList.get( j - 1 );
                    String title = book.getTitle();
                    if ( book.isForSale() ) {
                        double price = book.getCost() * this.getMarkup();
                        System.out.printf( "The price of %s is $ %3.2f\n"
                                         , title, price );
                    } else {
                        System.out.println( "\nSorry. " + title 
                                   + "\n\t" + book.getMedia()
                                   + " is not for sale."
                                   + "\n\tThe rental price is $ "
                                   + this.rentalPrice + " per week." );
                    }
                    System.out.println();
                }
            }
        }
        return;
    }

    /**
     * offerSearch offers the user a way to search by title, author, or media.
     * Based on the search code entered ('t', 'a', or 'm'), the search
     * scans the inventory for values matching an entered phrase.
     * <p>
     * The matching process is case-insensitive.
     * The user may enter a blank line or a 'q' to quit.
     * </p>
     * <p>
     * If there are any matches, the user has the chance to choose
     * which one to buy.
     * </p>
     */
    public void offerSearch() {

        do {
            System.out.println();
            System.out.println( 
            "Enter one of these codes ("
            + TITLE_SEARCH + ", " + AUTHOR_SEARCH + ", " + MEDIA_SEARCH 
            + ")=(title, author, media) \nand a portion of the desired text."
            + " Or enter a blank line to quit." );
            System.out.println( 
               "\t(Note: media format is paper, hard, audio, or electronic)." );
            System.out.print( "How would you like to search?" );
            System.out.print( " : " );

            String content = this.in.nextLine().trim().toLowerCase() ;
            if ( content.isEmpty() 
              || content.charAt( 0 ) == 'q' ) {
                System.out.println( "\nSearch again any time!\n" );
                break;
            } else {
                char code = content.charAt( 0 ) ;
                if ( Store.CODES.indexOf( code ) != -1 ) {
                    ArrayList<Book> booksFound =
                          listMatching( content.substring( 1 ).trim(), code );
                    offerPurchase( booksFound );
                } else {
                    System.out.println( "Sorry. but " + code 
                                      + " is not a valid search code." );
                }
            }
        } while ( true );
    }

    /**
     * getPrice returns the price of a specified book using the store's markup.
     * @param book the book to price
     * @return double precision representation of the book's price 
     */
    public double getPrice( Book book ) {
        double price = book.getCost() * this.getMarkup();
        if (!book.isForSale())
        	return this.rentalPrice;
        else
        	return price;
    }

    /**
     * main method
     * @param args must contain the name of the file that holds the inventory.
     * @throws FileNotFoundException 
     */
    public static void main( String[] args ) throws FileNotFoundException {

        if ( args.length != 1 ) {
            System.out.println( "Usage: java Store filename" );

        } else {
            Store store = new Store();

            // read the inventory, which may throw a FileNotFoundException.
            store.fillInventory( args[0] );

            store.printInventory();

            store.offerSearch();

        }
    }

	public ArrayList<Book> getInventory() {
		return this.inventory;
	}

}

/*
 * $Log: Store.java,v $
 * Revision 1.1  2013/07/16 22:52:25  pzg8794
 * lab6
 *
 * Revision 1.3.1.1  2012/04/17 19:49:37  bks
 * changes made for GUIBookStore (mostly getPrice and accessibility)
 *
 * Revision 1.3  2012/03/27 21:47:56  bks
 * solution code
 *
 * Revision 1.2  2012/03/27 21:04:43  bks
 * removed printInventoryCost. added docs. improved prompts.
 *
 * Revision 1.1  2012/03/24 20:41:12  bks
 * Initial revision
 *
 */
