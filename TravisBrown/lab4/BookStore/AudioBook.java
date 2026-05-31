/*
 * Audio.java
 *
 * Version:
 *  $Id: 
 *
 * Revisions:
 *  $Log: 
 *
 */


/**
 * The Book class represents the common characteristics of a book, 
 * which may be produced in various media formats.
 * 
 * @author piter garcia
 *
 */
public class AudioBook extends Book {

	/*
	 * discs of audio - book
	 */
	private String discs;
	
	/*
	 * Media format of the audio book
	 */
	private Media media;
	
	
	
	/**
	 * The constructor instantiates this particular kind of Book instance.
	 * 
	 * @param title,  the title of the book
	 * @param author,  the author of the book
	 * @param cost, the cost of the book in cents
	 * @param numDiscs, numDiscs of the audio-book
	 */
	public AudioBook(String title, String author, Integer cost, String numDiscs) {
		super(title, author, cost, Media.Audio);
		media = Media.Audio;
		discs = numDiscs;
	}

	
	
	
	/**
	 * The getMedia method adds the number of discs to the string
	 *  representation of this instance's media format.
	 *  
	 *  overrides - getMedia in class Book
	 *  
	 *  @return the book's media.
	 */
	public String getMedia(){
		return media.toString() + ": " + discs +" discs.";
	}

	
	
	
	/**
	 * AudioBook instances are never offered for sale; they are rental only.
	 * Specified by: isForSale in class Book
	 * 
	 * @return true if this instance is for a final sale.
	 * 
	 */
	public boolean isForSale(){
		return false;
	}

	
	
	/**
	 * The toString represents an AudioBook by adding ": {n} disks."
	 *  to the standard string representation of a Book. The {n} in 
	 *  this case is the number of discs.
	 *  
	 *  Overrides:
	 *  toString in class Book
	 *  
	 *  @return string representation including the number of discs for the book.
	 */
	public String toString(){
		return super.toString() + this.discs + " dics.";
	}

}
