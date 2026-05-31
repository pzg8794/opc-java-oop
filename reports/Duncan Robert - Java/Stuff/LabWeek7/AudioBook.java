/*
 * AudioBook.java
 * $Id: AudioBook.java,v 1.3 2012/03/30 19:06:25 bks Exp $
 */

/**
 * AudioBook is a book delivered as audio compact discs (CD).
 * @author ben k steele bks@cs.rit.edu
 */
public class AudioBook extends Book {

    /** number of discs that are part of this book */
    private final Integer numDiscs;

    /**
     * The constructor instantiates this particular kind of Book instance.
     * @param title of the book
     * @param author of the book
     * @param cost of the book
     * @param numDiscs of the audiobook
     */
    public AudioBook( String title, String author
                    , Integer cost, Integer numDiscs ) {
        super( title, author, cost, Media.Audio );
        this.numDiscs = numDiscs;
    }

    /**
     * The toString represents an AudioBook by adding 
     * ": {n} disks." to the standard string representation of a Book.
     * The {n} in this case is the number of discs.
     * @return string representation including the number of discs for the book.
     */
    @Override
    public String toString() {
        return super.toString() + ": " + numDiscs + " discs." ;
    }

    /**
     * AudioBook instances are never offered for sale; they are rental only.
     * @see Book#isForSale()
     */
    public boolean isForSale() {
        return false;
    }

    /**
     * The getMedia method adds the number of discs to the string 
     * representation of this instance's media format.
     * @see Book#getMedia()
     */
    @Override
    public String getMedia() {
        return super.getMedia() + ": " + numDiscs + " discs." ;
    }

}

/*
 * $Log: AudioBook.java,v $
 * Revision 1.3  2012/03/30 19:06:25  bks
 * beefed up javadocs
 *
 * Revision 1.2  2012/03/27 21:09:32  bks
 * fixed see reference to getMedia
 *
 * Revision 1.1  2012/03/24 20:41:12  bks
 * Initial revision
 *
 */
