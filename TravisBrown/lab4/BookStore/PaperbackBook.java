/*
 * 	Book.java
 *
 * Version:
 *  $Id: 
 *
 * Revisions:
 *  $Log: 
 *
 */



/**
 * PaperbackBook represents a basic paperback book.
 * @author piter garcia
 *
 */
public class PaperbackBook extends Book {

	
	/**
	 * The PaperbackBook constructor instantiates and initializes the 
	 * instance.
	 * 
	 * @param title,  the title of the book
	 * @param author,  the author of the book
	 * @param cost, the cost of the book in cents
	 * @param media, the media format of the book
	 */
	public PaperbackBook(String title, String author, Integer cost) {
		super(title, author, cost, Media.Paperback);

	}

	
	
	/**
	 * The isForSale implementation depends on the book's media. Some kinds
	 * of books are offered only for rent, not for sale.
	 * 
	 * @return true if this instance is for a final sale.
	 */
	public boolean isForSale(){
		return true;
	}
	
	
	
	/**
	 * toString adds a trailing period(.) at the end of the returned text.
	 * 
	 *  Overrides:
	 *  toString in class Book
	 *  
	 *  @return string representation of this book.
	 */
	public String toString(){
		return super.toString() +".";
	}
}
