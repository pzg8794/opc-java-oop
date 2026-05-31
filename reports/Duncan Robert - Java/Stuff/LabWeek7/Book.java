/*
 * Book.java
 *
 * $Id: Book.java,v 1.2 2012/03/30 19:06:25 bks Exp $
 */

/**
 * The Book class represents the common characteristics of a book,
 * which may be produced in various media formats.
 *
 * @author ben k steele bks@cs.rit.edu
 */
public abstract class Book {

    /** title of this Book instance */
    private final String title; 

    /** author of this Book instance */
    private final String author; 

    /** integer cost of this Book instance. Units are whole US cents. */
    private final int cost; 

    /** media of this Book instance */
    private final Media media; 

    /**
     * The Book constructor instantiates the instance with the specified values.
     *
     * @param title  the title of the book
     * @param author  the author of the book
     * @param cost  the cost of the book in cents
     * @param media  the media format of the book
     * @see Media
     */
    public Book( String title, String author, int cost, Media media ) {
        this.title = title ;
        this.author = author ;
        this.cost = cost ;
        this.media = media ;
    }

    /**
     * The standard string represention prints the title on the first line,
     * followed by the author on the second line, and
     * the book media on the third line.
     * The second and subsequent lines are indented by a single TAB character.
     * Each line should be terminated with a period(.).
     * @return standard string representation of Book
     */
    @Override
    public String toString() {
        return getTitle() + ". \n\t" + author + ". \n\t" + getMedia() ;
    }

    /**
     * The isForSale implementation depends on the book's media.
     * Some kinds of books are offered only for rent, not for sale.
     * @return true if this instance is for a final sale.
     */
    public abstract boolean isForSale();

    /** @return the book title enclosed in double quotes("). */
    public String getTitle() {
        return "\"" + this.title + "\"";
    }

    /**
     * @return the book author name.
     */
    public String getAuthor() {
        return this.author ;
    }

    /**
     * @return the book's cost converted into dollars and cents.
     */
    public double getCost() {
        return (this.cost / 100) + (double)( this.cost % 100 ) / 100 ;
    }

    /**
     * getMedia gets the 'display string representation' of the book's media.
     * @return the book's media.
     */
    public String getMedia() {
        return this.media.toString() ;
    }

}

/*
 * $Log: Book.java,v $
 * Revision 1.2  2012/03/30 19:06:25  bks
 * beefed up javadocs
 *
 * Revision 1.1  2012/03/24 20:41:12  bks
 * Initial revision
 *
 */
