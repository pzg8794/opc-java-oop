/*
 * HardcoverBook.java
 * $Id: HardcoverBook.java,v 1.2 2012/03/30 19:06:25 bks Exp $
 */

/**
 * HardcoverBook represents books with hard covers.
 *
 * @author ben k steele bks@cs.rit.edu
 */
public class HardcoverBook extends Book {

    /** the kind of the hard cover, "cloth" or "leather" */
    private final String coverMaterial;

    /**
     * The HardcoverBook constructor instantiates the instance by
     * initializing all the fields with the specified values.
     * @param title of the book
     * @param author of the book
     * @param cost of the book
     * @param coverMaterial of the book
     */
    public HardcoverBook( String title, String author
                        , Integer cost, String coverMaterial ) {
        super( title, author, cost, Media.Hardcover );
        this.coverMaterial = coverMaterial;
    }

    /**
     * The toString represents an HardcoverBook by adding
     * " {cover material}." to the standard string representation of a Book.
     * The {cover material} in this case is the instance's cover material.
     * There is a trailing period(.) at the end of the returned text.
     *
     * @return string representation of this hardcover book.
     */
    @Override
    public String toString() {
        return super.toString() + " " + coverMaterial + "." ;
    }

    /**
     * @return string representation of the hardcover material.
     */
    public String getCoverMaterial() {
        return coverMaterial;
    }

    /*
     * HardcoverBook instances are always offered for sale.
     * @see Book.isForSale()
     */
    public boolean isForSale() {
        return true;
    }

    /**
     * The getMedia method extends the standard media information string
     * by adding text describing the cover material of this instance.
     * There is a trailing period(.) at the end of the returned text.
     *
     * @return string representation of this book's media.
     */
    @Override
    public String getMedia() {
        return super.getMedia() + " " + coverMaterial + "." ;
    }

}

/*
 * $Log: HardcoverBook.java,v $
 * Revision 1.2  2012/03/30 19:06:25  bks
 * beefed up javadocs
 *
 * Revision 1.1  2012/03/24 20:41:12  bks
 * Initial revision
 *
 */
