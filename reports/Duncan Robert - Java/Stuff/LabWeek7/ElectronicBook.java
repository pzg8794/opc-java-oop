/*
 * ElectronicBook.java
 * $Id: ElectronicBook.java,v 1.3 2012/03/30 19:16:09 bks Exp $
 */

/**
 * ElectronicBook represents an electronic book instance, an e-book.
 *
 * @author ben k steele bks@cs.rit.edu
 */
public class ElectronicBook extends Book {

    /** Uniform Resource Locator for this book. */
    private final String theURL;

    /**
     * The ElectronicBook constructor instantiates the instance and
     * initializes all its fields using the supplied arguments.
     * 
     * @param title of the book
     * @param author of the book
     * @param cost of the book
     * @param theURL of the book, which must contain the text "://". 
     */
    public ElectronicBook( String title, String author
                         , Integer cost, String theURL ) {
        super( title, author, cost, Media.Electronic );
        this.theURL = theURL;
    }

    /**
     * The toString represents an ElectronicBook by adding
     * " from {URL}" to the standard string representation of a Book.
     * The {URL} in this case is the resource locator of this instance.
     * @return string representation of this electronic book
     */
    @Override
    public String toString() {
        return super.toString() + " from " + this.theURL ;
    }

    /**
     * ElectronicBook instances are never offered for sale.
     * You may rent them instead.
     * @see Book#isForSale()
     */
    public boolean isForSale() {
        return false;
    }

    /**
     * The getMedia method returns the standard string media representation
     * plus the text " : {URL}".
     * The {URL} in this case is the resource locator of this instance.
     * @return string representation of the media of this book
     */
    @Override
    public String getMedia() {
        return super.getMedia() + " : " + this.theURL ;
    }

}

/*
 * $Log: ElectronicBook.java,v $
 * Revision 1.3  2012/03/30 19:16:09  bks
 * fixed error in javadocs
 *
 * Revision 1.2  2012/03/30 19:06:25  bks
 * beefed up javadocs
 *
 * Revision 1.1  2012/03/24 20:41:12  bks
 * Initial revision
 *
 */
