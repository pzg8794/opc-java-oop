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
 * 
 * @author piter garcia
 *
 */
public class CassetteBook extends Book  {

	/*
	 * 
	 */
	private String cassettes;

	
	/**
	 * The constructor instantiates this particular kind of Book instance.
	 * 
	 * @param title,  the title of the book
	 * @param author,  the author of the book
	 * @param cost, the cost of the book in cents
	 * @param numcassettes, numcassettes of the audio-book
	 */
	public CassetteBook(String title, String author, int cost, String numCassettes) {
		super(title, author, cost, Media.Audio);
		this.cassettes = numCassettes;
	}


	/**
	 * The getMedia method adds the number of cassettes to the string
	 *  representation of this instance's media format.
	 *  
	 *  overrides - getMedia in class Book
	 *  
	 *  @return the book's media.
	 */
	public String getMedia(){
		return super.getMedia().toString() + ": " + super.getMedia() +" cassetts.";
	}


	/**
	 * CassettesBook instances are never offered for sale; they are rental only.
	 * Specified by: isForSale in class Book
	 * 
	 * @return true if this instance is for a final sale.
	 * 
	 */
	public boolean isForSale(){
		return false;
	}

	
	
	/**
	 * The standard string representation prints the title on the first line, 
	 * followed by the author on the second line, and the book media on the
	 * third line. The second and subsequent lines are indented by a single
	 * TAB character. Each line should be terminated with a period(.). 
	 * 
	 * returns the book's media.
	 */
	public String toString(){
		return super.toString() +  this.cassettes + " cassetts.";
	}
}
