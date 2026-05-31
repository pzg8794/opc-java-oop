/*
 * PaperbackBook.java
 * $Id: PaperbackBook.java,v 1.2 2012/03/30 19:06:25 bks Exp $
 */

/**
 * PaperbackBook represents a basic paperback book.
 *
 * @author ben k steele bks@cs.rit.edu
 */
public class PaperbackBook extends Book {

    /**
     * The PaperbackBook constructor instantiates and initializes the instance.
     * @param title of the book
     * @param author of the book
     * @param cost of the book
     */
    public PaperbackBook( String title, String author, Integer cost ) {
        super( title, author, cost, Media.Paperback );
    }

    /**
     * toString adds a trailing period(.) at the end of the returned text.
     * @return string representation of this book
     */
    @Override
    public String toString() {
        return super.toString() + "." ;
    }

    /*
     * PaperbackBook instances are always offered for sale.
     * @see Book.isForSale()
     */
    public boolean isForSale() {
        return true;
    }

}

/*
 * $Log: PaperbackBook.java,v $
 * Revision 1.2  2012/03/30 19:06:25  bks
 * beefed up javadocs
 *
 * Revision 1.1  2012/03/24 20:41:12  bks
 * Initial revision
 *
 */
