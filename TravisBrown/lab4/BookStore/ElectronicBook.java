/*
 * Electronic.java
 *
 * Version:
 *  $Id: 
 *
 * Revisions:
 *  $Log: 
 *
 */



/**
 * ElectronicBook represents an electronic book instance, an e-book.
 * 
 * @author piter garcia
 *
 */
public class ElectronicBook extends Book {
	
	/*
	 * theURL - of the book, which must contain the text "://".
	 */
	private String theURL;
	
	/*
	 * Media format of the audio book
	 */
	private Media media;
	
	
	
	
	/**
	 *  * The ElectronicBook constructor instantiates the instance and initializes
	 * all its fields using the supplied arguments.
	 * 
	 * @param title,  the title of the book
	 * @param author,  the author of the book
	 * @param cost, the cost of the book in cents
	 * @param theURL, the URL of the book, which must contain the text "://".
	 */
	public ElectronicBook(String title, String author, Integer cost, String theURL) {
		super(title, author, cost, Media.Electronic);
		media = Media.Electronic;
		this.theURL = theURL;
	}

	
	
	/**
	 * The getMedia method returns the standard string media representation plus 
	 * the text " : {URL}". The {URL} in this case is the resource locator 
	 * of this instance.
	 * 
	 * overrides - getMedia in class Book
	 *  
	 *  @return the book's media.
	 */
	public String getMedia(){
		return media.toString() + ": " + theURL;
	}

	
	
	/**
	 * ElectronicBook instances are never offered for sale. 
	 * You may rent them instead.
	 * 
	 * Specified by: isForSale in class Book
	 * 
	 * @return true if this instance is for a final sale.
	 */
	public boolean isForSale(){
		return false;
	}

	
	
	/**
	 * The toString represents an ElectronicBook by adding " from {URL}" 
	 * to the standard string representation of a Book. The {URL} in this 
	 * case is the resource locator of this instance.
	 * 
	 * Overrides:
	 * toString in class Book
	 *  
	 * @return string representation of this electronic book.
	 */
	public String toString(){
		return super.toString() + this.theURL + ".";

	}
}
