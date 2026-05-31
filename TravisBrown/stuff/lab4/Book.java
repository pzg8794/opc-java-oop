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
 *  The Book class represents the common characteristics of a book, which
 *  may be produced in various media formats.
 * @author piter garcia
 *
 */
public abstract class Book {

	/*
	 * title - the title of the book
	 */
	private String title;
	
	/*
	 * author - the author of the book
	 */
	private String author;
	
	/*
	 * cost - the cost of the book in cents
	 */
	private double cost;
	
	/*
	 * media - the media format of the book
	 */
	private Media media;
	
	
	
	
	/**
	 *  The Book constructor instantiates the instance with the specified 
	 *  values.
	 *  
	 * @param title,  the title of the book
	 * @param author,  the author of the book
	 * @param cost, the cost of the book in cents
	 * @param media, the media format of the book
	 */
	public Book(String title, String author, Integer cost, Media media) {
		this.title = title;
		this.author = author;
		this.media = media;
		this.cost = cost/100;
		
	}

	
	
	
	/**
	 * Gets the title of a book.
	 * @return title, the book title enclosed in double quotes(").
	 */
	public String getTitle(){
		return title;
		
	}

	
	
	
	/**
	 * Gets the author of a book.
	 * @return author, the book author name.
	 */
	public String getAuthor(){
		return author;
		
	}

	
	
	
	/**
	 * Gets the cost of a book
	 * @return cost, the book's cost converted into dollars and cents.
	 */
	public double getCost(){
		return cost;
	}

	
	
	
	/**
	 * Gets the 'display string representation' of the book's media.
	 * @return media, media format of a book.
	 */
	public String getMedia() {
		return media.toString();
	}

	
	
	
	/**
	 * The isForSale implementation depends on the book's media. Some kinds
	 * of books are offered only for rent, not for sale.
	 * 
	 * @return true if this instance is for a final sale.
	 */
	public abstract boolean  isForSale();
	
	
	
	
	/**
	 * The standard string representation prints the title on the first line, 
	 * followed by the author on the second line, and the book media on the
	 * third line. The second and subsequent lines are indented by a single
	 * TAB character. Each line should be terminated with a period(.). 
	 * 
	 * returns the book's media.
	 */
	public String toString(){
		return "\"" + getTitle() + "\". \n\t"+ getAuthor()+"."
				+ "\n\t" + media + " ";
	}

}
